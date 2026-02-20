package net.balamah.voiddim.effect.custom;

import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;

import java.util.Arrays;

import net.balamah.voiddim.entity.custom.base.AbstractCorruptedHostileEntity;
import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.effect.ModEffects;

public class CorruptionEffect extends StatusEffect {
	protected final EntityType<?>[] immuneEntities = {
		ModEntities.CORRUPTED_BLAZE,
		ModEntities.CORRUPTED_CREEPER,
		ModEntities.CORRUPTED_SPIDER,
		ModEntities.HOLLOWED_ALPHA_STEVE,
		ModEntities.VOID_MAW,
		EntityType.ILLUSIONER,
		EntityType.VINDICATOR,
		EntityType.PILLAGER,
		EntityType.EVOKER,
		EntityType.ZOMBIE,
		EntityType.CREEPER,
		EntityType.SKELETON,
		EntityType.SKELETON_HORSE,
		EntityType.ZOMBIE_HORSE,
		EntityType.WITHER,
		EntityType.WITHER_SKELETON,
		EntityType.CREAKING
	};

	public CorruptionEffect() {
		super(StatusEffectCategory.HARMFUL, 0xFF444444);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyUpdateEffect(
		ServerWorld world, LivingEntity entity, int amplifier
	) {
		if (!(entity instanceof AbstractCorruptedHostileEntity) &&
			!entity.hasStatusEffect(ModEffects.DIVINE_PROTECTION) &&
			!Arrays.asList(this.immuneEntities).contains(entity.getType())
		) {
			DamageSource damageSource = ModDamageSources.corruption(world);
			entity.damage(world, damageSource, 8.0f * (amplifier + 1));
		}

		return super.applyUpdateEffect(world, entity, amplifier);
	}
}
