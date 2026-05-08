package net.balamah.voiddim.effect.custom;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.PoisonMobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.balamah.voiddim.effect.ModDamageSources;
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
		super(MobEffectCategory.HARMFUL, 0xFF564D66);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	// TODO: Implement the effect
	// Healing is reduced (natural regen + potions + golden apples)
	// Every time the player takes damage, they take a small extra
	// “soul tick” damage (like 0.5 hearts)
	// Player becomes slightly slower (like Slowness 1)
	// Their screen gets a subtle dark pulse / vignette effect
	// (client-side optional)
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
