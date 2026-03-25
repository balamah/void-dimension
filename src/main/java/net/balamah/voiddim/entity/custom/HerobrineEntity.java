package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.ShockWaveUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.entity.ModEntities;

public class HerobrineEntity extends BossEntity implements ShockWaveUser {
	public AnimationState lightningInvokeAnimationState = new AnimationState();
	public AnimationState groundCorruptionAnimationState = new AnimationState();
	public AnimationState shockwaveInvokeAnimationState = new AnimationState();

	protected int lightningCooldown;
	protected int shockwaveCooldown;
	protected int groundCorruptionCooldown;

	public HerobrineEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 32)
			.add(EntityAttributes.ATTACK_DAMAGE, 7.0F)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.3f)
			.add(EntityAttributes.MAX_HEALTH, 315);
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
	}
	
	public int getLightningCooldown() {
		return this.lightningCooldown;
	}

	public void setLightningCooldown(int lightningCooldown) {
		this.lightningCooldown = lightningCooldown;
	}

	public int getGroundCorruptionCooldown() {
		return this.groundCorruptionCooldown;
	}

	public void setGroundCorruptionCooldown(int groundCorruptionCooldown) {
		this.groundCorruptionCooldown = groundCorruptionCooldown;
	}

	@Override
	public int getShockWaveCooldown() {
		return 200;
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
	protected void initGoals() {
		/*
		 * TODO: Add goals for HerobrineEntity
		 * - ShootLightning
		 * - HeavyJump
		 * - Attack
		 * - ShockWaveInvoke
		 * - GroundCorruption
		 */
		super.initGoals();

		// this.goalSelector.add(2, new HeavyJumpTargetGoal<HerobrineEntity>(this));

		this.goalSelector.add(1, new GroundCorruptionGoal<HerobrineEntity>(this, 10, 15, 10));
		this.goalSelector.add(2, new ShockWaveInvokeGoal<HerobrineEntity>(this, 12, 10));
		this.goalSelector.add(3, new ShootLightningGoal<HerobrineEntity>(this));
		this.goalSelector.add(
			2,
			new SummonEntitiesGoal<HerobrineEntity, AggressiveNullEntity>(
				this, AggressiveNullEntity.class, ModEntities.AGGRESSIVE_NULL
			)
		);
	}

	@Override
	protected SoundEvent getDeathSound() {
		// TODO Auto-generated method stub
		return super.getDeathSound();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		// TODO Auto-generated method stub
		return super.getHurtSound(source);
	}
}
