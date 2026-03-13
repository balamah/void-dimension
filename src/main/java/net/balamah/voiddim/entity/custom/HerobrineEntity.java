package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class HerobrineEntity extends BossEntity {
	public AnimationState lightningInvokeAnimationState = new AnimationState();
	public AnimationState groundCorruptionAnimationState = new AnimationState();
	public AnimationState shockwaveInvokeAnimationState = new AnimationState();

	protected int lightningCooldown;

	public HerobrineEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 32)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.2F)
			.add(EntityAttributes.ATTACK_DAMAGE, 13.0F)
			.add(EntityAttributes.MAX_HEALTH, 315);
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.HEROBRINE_LIGHTNING_INVOKE:
				this.lightningInvokeAnimationState.start(this.age);
				break;
			case ModEntityStatuses.HEROBRINE_SHOCKWAVE_INVOKE:
				this.shockwaveInvokeAnimationState.start(this.age);
				break;
			case ModEntityStatuses.HEROBRINE_GROUND_CORRUPTION:
				this.groundCorruptionAnimationState.start(this.age);
				break;
			default:
				super.handleStatus(status);
				break;
		}
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
