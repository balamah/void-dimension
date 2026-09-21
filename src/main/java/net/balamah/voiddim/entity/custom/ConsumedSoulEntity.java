package net.balamah.voiddim.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.balamah.voiddim.entity.custom.base.ModProjectile;

import java.util.Arrays;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.entity.ModEntities;

public class ConsumedSoulEntity extends ModProjectile {
	protected EntityType<?>[] immuneEntities = {
		ModEntities.DARK_GRASP, ModEntities.VOID_BOUND_SERVANT, ModEntities.CORRUPTED_WARRIOR
	};

	public ConsumedSoulEntity(
		EntityType<? extends ConsumedSoulEntity> entityType, Level level
	) {
		super(entityType, level);
	}

	@Override
	protected void onHitEntity(EntityHitResult hitResult) {
		if (this.level() instanceof ServerLevel world) {
			LivingEntity attackerEntity =
				(this.getOwner() instanceof LivingEntity livingEntity) ?
				livingEntity : null;

			Entity target = hitResult.getEntity();

			if (Arrays.asList(this.immuneEntities).contains(target.getType())) {
				return;
			}

			if (attackerEntity != null) {
				attackerEntity.setLastHurtMob(target);
			}

			DamageSource damageSource = ModDamageSources.corruption(world);
			boolean damage = target.hurtServer(world, damageSource, 18.5f);

			if (damage && target instanceof LivingEntity targetLivingEntity) {
				targetLivingEntity.addEffect(
					new MobEffectInstance(ModEffects.SOUL_BURN, 100, 1)
				);
				
				EnchantmentHelper.doPostAttackEffects(world, targetLivingEntity, damageSource);
			}
			
			this.disappear();
		}
	}
}
