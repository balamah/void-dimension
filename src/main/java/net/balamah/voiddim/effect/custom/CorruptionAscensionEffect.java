package net.balamah.voiddim.effect.custom;

import java.util.Arrays;

import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class CorruptionAscensionEffect extends MobEffect {
	protected EntityType<?>[] immuneEntities = {
		ModEntities.VOID_HARBINGER,
		ModEntities.SHATTERED_SENTINEL_MASTER,
		ModEntities.VOID_BOUND_SERVANT
	};

	public CorruptionAscensionEffect() {
		super(MobEffectCategory.HARMFUL, 0xFF444444);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
		if (!(Arrays.asList(this.immuneEntities).contains(entity.getType()))) {
			DamageSource damageSource = ModDamageSources.corruption(world);
			entity.hurtServer(world, damageSource, 8.0f * (amplifier + 1));
		}

		return super.applyEffectTick(world, entity, amplifier);
	}
}
