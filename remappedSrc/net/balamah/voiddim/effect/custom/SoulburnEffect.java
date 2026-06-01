package net.balamah.voiddim.effect.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.balamah.voiddim.entity.ModEntities;

public class SoulburnEffect extends StatusEffect {
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
		EntityType.WITHER_SKELETON,
		EntityType.CREAKING
	};

	public SoulburnEffect() {
		super(StatusEffectCategory.HARMFUL, 0xFF564D66);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerWorld world, LivingEntity entity, int amplifier) {
		if (!(entity instanceof CorruptedHostileEntity)) {
			// DamageSource damageSource = ModDamageSources.soulBurn(world);
			// entity.hurtServer(world, damageSource, 8.0f * (amplifier + 1));
			entity.addStatusEffect(new StatusEffectInstance(MobEffects.SLOWNESS, 15, amplifier + 1));
		}

		return true;
	}
}
