package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.DodgeAttackUser;
import net.balamah.voiddim.interfaces.ShockWaveUser;
import net.balamah.voiddim.interfaces.ShootLightningUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Entity303 extends BossEntity
	implements ShockWaveUser, DodgeAttackUser, ShootLightningUser
{
	public AnimationState lightningInvokeAnimationState = new AnimationState();
	public AnimationState groundCorruptionAnimationState = new AnimationState();
	public AnimationState shockwaveInvokeAnimationState = new AnimationState();

	protected int lightningCooldown;
	protected int shockwaveCooldown = 200;
	protected int groundCorruptionCooldown;
	protected int dodgeAttackCooldown;
	protected String[] deathMessages = {
		"I am not gone, I am waiting",
		"Every end is temporary, even mine",
		"Do not be fooled, I am never truly gone"
	};

	public Entity303(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32)
			.add(EntityAttributes.GENERIC_STEP_HEIGHT, 2)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0F)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 315);
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.LIGHTNING_INVOKE:
				this.lightningInvokeAnimationState.start(this.age);
				break;
			case ModEntityStatuses.SHOCK_WAVE_INVOKE:
				this.shockwaveInvokeAnimationState.start(this.age);
				break;
			case ModEntityStatuses.GROUND_MANIPULATION_BEGIN:
				this.groundCorruptionAnimationState.start(this.age);
				break;
			default:
				super.handleStatus(status);
				break;
		}
	}

	@Override
	public void tick() {
		super.tick();

		if (this.lightningCooldown > 0) {
			this.lightningCooldown--;
		}

		if (this.shockwaveCooldown > 0) {
			this.shockwaveCooldown--;
		}

		if (this.groundCorruptionCooldown > 0) {
			this.groundCorruptionCooldown--;
		}

		if (this.dodgeAttackCooldown > 0) {
			this.dodgeAttackCooldown--;
		}
	}
	
	@Override
	public void tickMovement() {
		super.tickMovement();

		if (this.isSecondPhase()) {
			BlockPos blockToCorruptPos = this.getBlockPos().add(0, -1, 0);
			World world = this.getWorld();
			Block blockToCorrupt = McCodeHelper.getBlock(world, blockToCorruptPos);
			if (McCodeHelper.isBlockReplaceable(blockToCorrupt)) {
				this.corruptBlock(this.getWorld(), blockToCorruptPos);
			}
		}
	}

	public int getLightningCooldown() {
		return this.lightningCooldown;
	}

	public void setLightningCooldown(int lightningCooldown) {
		this.lightningCooldown = lightningCooldown;
	}


	@Override
	public int getShockWaveCooldown() {
		return 350;
	}

	@Override
	public int getShockWaveTicks() {
		return this.shockwaveCooldown;
	}

	@Override
	public void setShockWaveTicks(int ticks) {
		this.shockwaveCooldown = ticks;
	}

	@Override
	public void onDamaged(DamageSource damageSource) {
		super.onDamaged(damageSource);
	}

	@Override
	public boolean isFireImmune() {
		return true;
	}

	@Override
	public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
		this.heal(5);
	}

	@Override
	public int getDodgeAttackCooldown() {
		return 100;
	}

	@Override
	public int getDodgeAttackTicks() {
		return this.dodgeAttackCooldown;
	}

	@Override
	public void setDodgeAttackTicks(int ticks) {
		this.dodgeAttackCooldown = ticks;
	}

	@Override
	public void onDeath(DamageSource damageSource) {
		super.onDeath(damageSource);

		if (this.getWorld() instanceof ServerWorld serverWorld) {
			int randomMessageIndex = this.random.nextInt(this.deathMessages.length);

			McCodeHelper.sendMessageToNearbyPlayers(
				serverWorld, this.getPos(), 10, this.deathMessages[randomMessageIndex]
			);
		}
	}

	@Override
	protected void initGoals() {
		super.initGoals();

		this.goalSelector.add(1, new DodgeAttackGoal<Entity303>(this, 15, 2.0, 13f));
		this.goalSelector.add(2, new ShockWaveInvokeGoal<Entity303>(this, 12, 10));
		this.goalSelector.add(3, new ShootLightningGoal<Entity303>(this));
		this.goalSelector.add(
			2,
			new SummonEntitiesGoal<Entity303, AggressiveNullEntity>(
				this, AggressiveNullEntity.class, ModEntities.AGGRESSIVE_NULL, 5
			)
		);
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.ENTITY303_DEATH;
	}
}
