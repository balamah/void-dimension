package net.balamah.voiddim.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.StationaryAttackUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.sound.ModSounds;

public class WormOfCorruptionEntity extends CorruptedHostileEntity
	implements StationaryAttackUser
{
	public AnimationState idleAnimationState = new AnimationState();
	public AnimationState attackAnimationState = new AnimationState();
	public AnimationState shootAnimationState = new AnimationState();
	public AnimationState digUpAnimationState = new AnimationState();
	public AnimationState digDownAnimationState = new AnimationState();

	public WormOfCorruptionEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 64)
			.add(Attributes.MOVEMENT_SPEED, 0)
			.add(Attributes.ATTACK_DAMAGE, 14.0F)
			.add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.MAX_HEALTH, 55)
			.add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	protected void registerGoals() {
		super.initTargets();

		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 64F));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, AgeableMob.class, 64F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.goalSelector.addGoal(2, new VoidHostileEntityAttackGoal(this, 1.0, false));

		Goal shootingGoal = new ShootProjectileGoal<WormOfCorruptionEntity, VoidSphereEntity>(
			this, world -> new VoidSphereEntity(ModEntities.VOID_SPHERE, world),
			ModSounds.VOID_HARBINGER_SHOOT_PREPARE,
			ModSounds.VOID_HARBINGER_SHOOT,
			50, 70
		);

		this.goalSelector.addGoal(1, shootingGoal);
		this.goalSelector.addGoal(3, new WormOfCorruptionMoveGoal(this, 4));
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		world.broadcastEntityEvent(this, ModEntityStatuses.ATTACK);

		this.makeSound(ModSounds.WORM_OF_CORRUPTION_ATTACK);

		return super.doHurtTarget(world, target);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (!(this.level() instanceof ServerLevel serverWorld)) return;

		for (int i = 0; i < 4; i++) {
			BlockPos blockPos = McCodeHelper.getBlockPosUnderEntity(this, i);
			Block block = McCodeHelper.getBlock(this.level(), blockPos);

			if (McCodeHelper.isBlockReplaceable(block)) {
				serverWorld.setBlockAndUpdate(blockPos, ModBlocks.CORRUPT_BLOCK.defaultBlockState());
			}
		}
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.attackAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.SHOOT:
				this.shootAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN:
				this.digDownAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN_STOP:
				this.digDownAnimationState.stop();
				break;
			case ModEntityStatuses.WORM_OF_CORRUPTION_DIG_UP:
				this.digUpAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.WORM_OF_CORRUPTION_IDLE:
				this.idleAnimationState.start(this.tickCount);
				break;
			default: super.handleEntityEvent(status);
				break;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.WORM_OF_CORRUPTION_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.WORM_OF_CORRUPTION_DEATH;
	}
}
