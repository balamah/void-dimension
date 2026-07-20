package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.entity.custom.base.BossEntity;
import net.balamah.voiddim.interfaces.ShatterGroundUser;
import net.balamah.voiddim.interfaces.ShockWaveUser;
import net.balamah.voiddim.interfaces.ThrowBlockUser;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.*;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.sound.ModSounds;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class ShatteredSentinelMasterEntity extends BossEntity
	implements ShockWaveUser, ShatterGroundUser, ThrowBlockUser
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
	protected final int throwBlockCooldown = 80;
	protected int shockWaveTicks = 0;
	protected int shatterGroundTicks = 0;
	protected int throwBlockTicks = 35;

	public ShatteredSentinelMasterEntity(
		EntityType<? extends Monster> entityType, Level world
	) {
		super(entityType, world);

		this.xpReward = 450;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.FOLLOW_RANGE, 64)
			.add(Attributes.MOVEMENT_SPEED, 0.25F)
			.add(Attributes.MAX_HEALTH, 400)
			.add(Attributes.ATTACK_DAMAGE, 15.5F)
			.add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.STEP_HEIGHT, 2.0);
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		world.broadcastEntityEvent(this, ModEntityStatuses.SHATTERED_SENTINEL_MASTER_ATTACK);

		DamageSource damageSource = this.damageSources().mobAttack(this);
		float f = this.getAttackDamage();
		float g = (int)f > 0 ? f / 2.0F + this.random.nextInt((int)f) : f;
		boolean bl = target.hurtServer(world, damageSource, g);
		if (bl) {
			double d = (target instanceof LivingEntity livingEntity) ?
				livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) :
				0.0;

			double e = Math.max(0.0, 1.0 - d);
			target.setDeltaMovement(target.getDeltaMovement().add(0.0, 0.4F * e, 0.0));
			EnchantmentHelper.doPostAttackEffects(world, target, damageSource);
		}

		this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		return bl;
	}

	@Override
	public void handleEntityEvent(byte status) {
		switch (status) {
			case ModEntityStatuses.SHATTERED_SENTINEL_MASTER_ATTACK:
				this.attackAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.SHOCK_WAVE_INVOKE:
				this.shockWaveInvokeState.start(this.tickCount);
				break;
			case ModEntityStatuses.SHOCK_WAVE_INVOKE_STOP:
				this.shockWaveInvokeState.stop();
				break;
			case ModEntityStatuses.THROW_BLOCK:
				this.throwBlockState.start(this.tickCount);
				break;
			case ModEntityStatuses.THROW_BLOCK_STOP:
				this.throwBlockState.stop();
				break;
			case ModEntityStatuses.GROUND_MANIPULATION_BEGIN:
				this.shatterGroundBeginAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.GROUND_MANIPULATION_PROCESS:
				this.shatterGroundPushAnimationState.start(this.tickCount);
				break;
			case ModEntityStatuses.GROUND_MANIPULATION_END:
				this.shatterGroundBeginAnimationState.stop();
				this.shatterGroundPushAnimationState.stop();
				break;
			default: super.handleEntityEvent(status);
				break;
		}
	}

	@Override
	public void tick() {
		super.tick();

		Level world = this.level();

		if (this.shockWaveTicks > 0) this.shockWaveTicks--;
		if (this.shatterGroundTicks > 0) this.shatterGroundTicks--;
		if (this.throwBlockTicks > 0) this.throwBlockTicks--;

		if (world.isClientSide()) {
			this.stonesFloatAnimationState.startIfStopped(this.tickCount);
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
	public int getThrowBlockCooldown() {
		return this.throwBlockCooldown;
	}

	@Override
	public int getThrowBlockTicks() {
		return this.throwBlockTicks;
	}

	@Override
	public void setThrowBlockTicks(int ticks) {
		this.throwBlockTicks = ticks;
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
	protected void registerGoals() {
		// TODO: Add throw block goal

		super.registerGoals();

		this.goalSelector.addGoal(2, new ShatteredSentinelMasterShootGoal(this));
		this.goalSelector.addGoal(2, new ShockWaveInvokeGoal<>(this, 14, 25, 200));
		this.goalSelector.addGoal(6, new ShatterGroundGoal<>(this));
		this.goalSelector.addGoal(
			7,
			new SummonEntitiesGoal<ShatteredSentinelMasterEntity, ShatteredSentinelEntity>(
				this, ShatteredSentinelEntity.class, ModEntities.SHATTERED_SENTINEL, 11
			)
		);
	}

	protected float getAttackDamage() {
		return (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	}
}
