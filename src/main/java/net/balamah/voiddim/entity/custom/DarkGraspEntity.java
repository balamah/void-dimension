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

public class DarkGraspEntity extends EvokerFangsEntity {
	public DarkGraspEntity(
		EntityType<? extends EvokerFangsEntity> entityType, World world
	) {
		super(entityType, world);
	}

	public DarkGraspEntity(
		World world, double x, double y, double z, float yaw, int warmup, LivingEntity owner
	) {
		super(world, x, y, z, yaw, warmup, owner);
	}

	protected void damage(LivingEntity target) {
		LivingEntity livingEntity = this.getOwner();
		if (target.isAlive() && !target.isInvulnerable() && target != livingEntity) {
			if (livingEntity == null) {
				target.serverDamage(this.getDamageSources().magic(), 6.0F);
			} else {
				DamageSource damageSource =
					this.getDamageSources().indirectMagic(this, livingEntity);

				if (this.getEntityWorld() instanceof ServerWorld serverWorld &&
					target.damage(serverWorld, damageSource, 6.0F)
				) {
					StatusEffectInstance effect =
						new StatusEffectInstance(ModEffects.CORRUPTION, 60, 1);

					target.addStatusEffect(effect);

					EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);
				}
			}
		}
	}
}
