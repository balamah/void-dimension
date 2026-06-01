package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.StationaryAttackUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WormOfCorruptionEntity extends CorruptedHostileEntity
	implements StationaryAttackUser
{
	public AnimationState idleAnimationState = new AnimationState();
	public AnimationState attackAnimationState = new AnimationState();
	public AnimationState shootAnimationState = new AnimationState();
	public AnimationState digUpAnimationState = new AnimationState();
	public AnimationState digDownAnimationState = new AnimationState();

	public WormOfCorruptionEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 14.0F)
			.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 55)
			.add(EntityAttributes.GENERIC_STEP_HEIGHT, 1.0);
	}

	@Override
	protected void initGoals() {
		super.initTargets();

		this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 64F));
		this.goalSelector.add(7, new LookAtEntityGoal(this, PassiveEntity.class, 64F));
		this.goalSelector.add(8, new LookAroundGoal(this));

		this.goalSelector.add(2, new VoidHostileEntityAttackGoal(this, 1.0, false));

		Goal shootingGoal = new ShootProjectileGoal<WormOfCorruptionEntity, VoidSphereEntity>(
			this, world -> new VoidSphereEntity(ModEntities.VOID_SPHERE, world),
			ModSounds.VOID_HARBINGER_SHOOT_PREPARE,
			ModSounds.VOID_HARBINGER_SHOOT,
			50, 70
		);

		this.goalSelector.add(1, shootingGoal);
		this.goalSelector.add(3, new WormOfCorruptionMoveGoal(this, 4));
	}

	@Override
	public boolean doHurtTarget(ServerWorld world, Entity target) {
		world.sendEntityStatus(this, ModEntityStatuses.ATTACK);

		this.playSound(ModSounds.WORM_OF_CORRUPTION_ATTACK);

		return super.doHurtTarget(world, target);
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		if (!(this.getWorld() instanceof ServerWorld serverWorld)) return;

		for (int i = 0; i < 4; i++) {
			BlockPos blockPos = McCodeHelper.getBlockPosUnderEntity(this, i);
			Block block = McCodeHelper.getBlock(this.getWorld(), blockPos);

			if (McCodeHelper.isBlockReplaceable(block)) {
				serverWorld.setBlockState(blockPos, ModBlocks.CORRUPT_BLOCK.getDefaultState());
			}
		}
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.ATTACK:
				this.attackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.SHOOT:
				this.shootAnimationState.start(this.age);
				break;
			case ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN:
				this.digDownAnimationState.start(this.age);
				break;
			case ModEntityStatuses.WORM_OF_CORRUPTION_DIG_DOWN_STOP:
				this.digDownAnimationState.stop();
				break;
			case ModEntityStatuses.WORM_OF_CORRUPTION_DIG_UP:
				this.digUpAnimationState.start(this.age);
				break;
			case ModEntityStatuses.WORM_OF_CORRUPTION_IDLE:
				this.idleAnimationState.start(this.age);
				break;
			default: super.handleStatus(status);
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
