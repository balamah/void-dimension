package net.balamah.voiddim.entity.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.entity.ModEntities;

public class DarkGraspEntity extends EvokerFangsEntity {
	protected final float damage = 16f;

	private int warmup = 0;

	public DarkGraspEntity(
		EntityType<? extends EvokerFangsEntity> entityType, World world
	) {
		super(entityType, world);
	}

	public DarkGraspEntity(
		World world, double x, double y, double z, float yaw, int warmup, LivingEntity owner
	) {
		super(ModEntities.DARK_GRASP, world);

		this.warmup = warmup;
		this.setOwner(owner);
		this.setYaw(yaw * (180.0F / (float)Math.PI));
		this.setPosition(x, y, z);
	}

	@SuppressWarnings("deprecation")
	protected void damage(LivingEntity target) {
		LivingEntity livingEntity = this.getOwner();
		if (target.isAlive() && !target.isInvulnerable() && target != livingEntity) {
			if (livingEntity == null) {
				target.serverDamage(this.getDamageSources().magic(), this.damage);
			} else {
				this.damageApplyCorruption(livingEntity, target);
			}
		}
	}

	protected void damageApplyCorruption(LivingEntity livingEntity, LivingEntity target) {
		DamageSource damageSource = this.getDamageSources().indirectMagic(this, livingEntity);

		if (this.getEntityWorld() instanceof ServerWorld serverWorld &&
			target.damage(serverWorld, damageSource, this.damage))
		{
			StatusEffectInstance effect = new StatusEffectInstance(ModEffects.CORRUPTION, 60, 1);

			target.addStatusEffect(effect);

			EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);
		}
	}
}
