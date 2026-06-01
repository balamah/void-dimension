package net.balamah.voiddim.effect.custom;

import java.util.Arrays;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.balamah.voiddim.effect.ModDamageSources;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.effect.ModEffects;

public class CorruptionEffect extends MobEffect {
	protected final EntityType<?>[] immuneEntities = {
		ModEntities.CORRUPTED_BLAZE,
		ModEntities.CORRUPTED_CREEPER,
		ModEntities.CORRUPTED_SPIDER,
		ModEntities.HOLLOWED_ALPHA_STEVE,
		ModEntities.ZOMBIFIED_ALPHA_STEVE,
		ModEntities.VOID_MAW,
		ModEntities.NULL,
		ModEntities.STARING_CAT,
		ModEntities.STARING_DOG,
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
			EntityType.WITHER_SKELETON
	};

	public CorruptionEffect() {
		super(MobEffectCategory.HARMFUL, 0xFF444444);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if (!(entity instanceof CorruptedHostileEntity) &&
			!entity.hasEffect(ModEffects.DIVINE_PROTECTION) &&
			!Arrays.asList(this.immuneEntities).contains(entity.getType())
		) {
			if (entity.level() instanceof net.minecraft.server.level.ServerLevel world) {
				DamageSource damageSource = ModDamageSources.corruption(world);
				entity.hurt(damageSource, 8.0f * (amplifier + 1));
			}
		}

		return super.applyEffectTick(entity, amplifier);
	}
}
