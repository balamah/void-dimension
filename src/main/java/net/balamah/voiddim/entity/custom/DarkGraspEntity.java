package net.balamah.voiddim.entity.custom;

import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class DarkGraspEntity extends EvokerFangs {
	protected final float damage = 12f;

	private int warmup = 0;

	public DarkGraspEntity(
		EntityType<? extends EvokerFangs> entityType, Level world
	) {
		super(entityType, world);
	}

	public DarkGraspEntity(
		Level world, double x, double y, double z, float yaw, int warmup, LivingEntity owner
	) {
		super(ModEntities.DARK_GRASP, world);

		this.warmup = warmup;
		this.setOwner(owner);
		this.setYRot(yaw * (180.0F / (float)Math.PI));
		this.setPos(x, y, z);
	}

	@SuppressWarnings("deprecation")
	protected void dealDamageTo(LivingEntity target) {
		LivingEntity livingEntity = this.getOwner();
		if (target.isAlive() && !target.isInvulnerable() && target != livingEntity) {
			if (livingEntity == null) {
				target.hurt(this.damageSources().magic(), this.damage);
			} else {
				this.damageApplyCorruption(livingEntity, target);
			}
		}
	}

	protected void damageApplyCorruption(LivingEntity livingEntity, LivingEntity target) {
		DamageSource damageSource = this.damageSources().indirectMagic(this, livingEntity);

		if (this.level() instanceof ServerLevel serverWorld &&
			target.hurt(damageSource, this.damage))
		{
			MobEffectInstance effect = new MobEffectInstance(ModEffects.CORRUPTION, 60, 1);

			target.addEffect(effect);

			EnchantmentHelper.doPostAttackEffects(serverWorld, target, damageSource);
		}
	}
}
