package net.balamah.voiddim.effect.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.balamah.voiddim.entity.ModEntities;

public class SoulburnEffect extends MobEffect {
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
		EntityTypes.ILLUSIONER,
		EntityTypes.VINDICATOR,
		EntityTypes.PILLAGER,
		EntityTypes.EVOKER,
		EntityTypes.ZOMBIE,
		EntityTypes.CREEPER,
		EntityTypes.SKELETON,
		EntityTypes.SKELETON_HORSE,
		EntityTypes.ZOMBIE_HORSE,
		EntityTypes.WITHER,
		EntityTypes.WITHER_SKELETON,
		EntityTypes.CREAKING
	};

	public SoulburnEffect() {
		super(MobEffectCategory.HARMFUL, 0xFF564D66);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
		if (!(entity instanceof CorruptedHostileEntity)) {
			// DamageSource damageSource = ModDamageSources.soulBurn(world);
			// entity.hurtServer(world, damageSource, 8.0f * (amplifier + 1));
			entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 15, amplifier + 1));
		}

		return true;
	}
}
