package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.sound.SoundEvent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.sound.ModSounds;

public class ShatteredSentinelEntity extends CorruptedHostileEntity {
	public AnimationState idleAnimationState = new AnimationState();
	public AnimationState attackAnimationState = new AnimationState();
	public AnimationState stonesFloatAnimationState = new AnimationState();
    protected int idleAnimationTimeout = 0;

	public ShatteredSentinelEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);

		this.experiencePoints = 15;
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.MAX_HEALTH, 22)
			.add(EntityAttributes.FOLLOW_RANGE, 64)
			.add(EntityAttributes.MOVEMENT_SPEED, 0.4F)
			.add(EntityAttributes.ATTACK_DAMAGE, 6.0F)
			.add(EntityAttributes.STEP_HEIGHT, 1.0);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.getEntityWorld().isClient()) this.setupAnimationStates();
	}

	@Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		world.sendEntityStatus(this, ModEntityStatuses.ATTACK);

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
			case ModEntityStatuses.ATTACK:
				this.attackAnimationState.start(this.age);
				break;
			default: super.handleStatus(status);
				break;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.SHATTERED_SENTINEL_HIT;
	}

    @Override
	protected SoundEvent getDeathSound() {
		return ModSounds.SHATTERED_SENTINEL_DEATH;
	}

	protected void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

	protected float getAttackDamage() {
		return (float) this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);
	}
}
