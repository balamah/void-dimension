package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.ShatterGroundUser;
import net.balamah.voiddim.interfaces.ShockWaveUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;

public class ShatteredSentinelMasterEntity extends BossEntity
	implements ShockWaveUser, ShatterGroundUser
{
	public AnimationState walkAnimationState = new AnimationState();
	public AnimationState attackAnimationState = new AnimationState();
	public AnimationState idleAnimationState = new AnimationState();
	public AnimationState shatterGroundBeginAnimationState = new AnimationState();
	public AnimationState shatterGroundPushAnimationState = new AnimationState();
	public AnimationState shatterEndAnimationStateGround = new AnimationState();
	public AnimationState shockWaveInvokeState = new AnimationState();
	public AnimationState throwBlockState = new AnimationState();
	public AnimationState stonesFloatAnimationState = new AnimationState();

	public boolean breakShield = true;

	protected final int shockWaveCooldown = 300;
	protected final int shatterGroundCooldown = 200;
	protected int shockWaveTicks = 0;
	protected int shatterGroundTicks = 0;

	public ShatteredSentinelMasterEntity(
		EntityType<? extends HostileEntity> entityType, World world
	) {
		super(entityType, world);

		this.experiencePoints = 450;
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.FOLLOW_RANGE, 64)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.25F)
			.add(EntityAttributes.MAX_HEALTH, 400)
			.add(EntityAttributes.ATTACK_DAMAGE, 15.5F)
			.add(EntityAttributes.KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(EntityAttributes.STEP_HEIGHT, 2.0);
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		world.sendEntityStatus(this, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_ATTACK);

		DamageSource damageSource = this.getDamageSources().mobAttack(this);
		float f = this.getAttackDamage();
		float g = (int)f > 0 ? f / 2.0F + this.random.nextInt((int)f) : f;
		boolean bl = target.damage(world, damageSource, g);
		if (bl) {
			double d = (target instanceof LivingEntity livingEntity) ?
				livingEntity.getAttributeValue(EntityAttributes.KNOCKBACK_RESISTANCE) :
				0.0;

			double e = Math.max(0.0, 1.0 - d);
			target.setVelocity(target.getVelocity().add(0.0, 0.4F * e, 0.0));
			EnchantmentHelper.onTargetDamaged(world, target, damageSource);
		}

		this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		return bl;
	}

	@Override
	public void handleStatus(byte status) {
		switch (status) {
			case ModEntityStatuses.SHATTERED_SENTINEL_MASTER_ATTACK:
				this.attackAnimationState.start(this.age);
				break;
			case ModEntityStatuses.SHOCK_WAVE_INVOKE:
				this.shockWaveInvokeState.start(this.age);
				break;
			case ModEntityStatuses.SHOCK_WAVE_INVOKE_STOP:
				this.shockWaveInvokeState.stop();
				break;
			case ModEntityStatuses.THROW_BLOCK:
				this.throwBlockState.start(this.age);
				break;
			case ModEntityStatuses.THROW_BLOCK_STOP:
				this.throwBlockState.stop();
				break;
			case ModEntityStatuses.GROUND_MANIPULATION_BEGIN:
				this.shatterGroundBeginAnimationState.start(this.age);
				break;
			case ModEntityStatuses.GROUND_MANIPULATION_PROCESS:
				this.shatterGroundPushAnimationState.start(this.age);
				break;
			case ModEntityStatuses.GROUND_MANIPULATION_END:
				this.shatterGroundBeginAnimationState.stop();
				this.shatterGroundPushAnimationState.stop();
				break;
			default: super.handleStatus(status);
				break;
		}
	}

	@Override
	public void tick() {
		super.tick();

		World world = this.getEntityWorld();

		if (this.shockWaveTicks > 0) this.shockWaveTicks--;
		if (this.shatterGroundTicks > 0) this.shatterGroundTicks--;

		if (world.isClient()) {
			this.stonesFloatAnimationState.startIfNotRunning(this.age);
		}
	}

	public int getShockWaveCooldown() {
		return this.shockWaveCooldown;
	}

	public int getShockWaveTicks() {
		return this.shockWaveTicks;
	}

	public void setShockWaveTicks(int shockWaveTicks) {
		this.shockWaveTicks = shockWaveTicks;
	}

	public int getShatterGroundCooldown() {
		return this.shatterGroundCooldown;
	}

	public int getShatterGroundTicks() {
		return this.shatterGroundTicks;
	}

	public void setShatterGroundTicks(int shatterGroundTicks) {
		this.shatterGroundTicks = shatterGroundTicks;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SHATTERED_SENTINEL_MASTER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.SHATTERED_SENTINEL_MASTER_HIT;
	}

	@Override
	protected void initGoals() {
		// TODO: Add throw block goal

		super.initGoals();

		this.goalSelector.add(1, new ShatteredSentinelMasterShootGoal(this));
		this.goalSelector.add(2, new ShockWaveInvokeGoal<ShatteredSentinelMasterEntity>(this, 12, 25));
		this.goalSelector.add(6, new ShatterGroundGoal<ShatteredSentinelMasterEntity>(this));
		this.goalSelector.add(
			7,
			new SummonEntitiesGoal<ShatteredSentinelMasterEntity, ShatteredSentinelEntity>(
				this, ShatteredSentinelEntity.class, ModEntities.SHATTERED_SENTINEL, 11
			)
		);
	}

	protected float getAttackDamage() {
		return (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
	}
}
