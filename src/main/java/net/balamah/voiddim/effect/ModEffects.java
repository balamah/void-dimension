package net.balamah.voiddim.effect;

import net.balamah.voiddim.effect.custom.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;

import net.balamah.voiddim.VoidDimension;

public class ModEffects {
	public static final ResourceKey<DamageType> CORRUPTION_DAMAGE =
		registerDamageType("corruption");

	public static final ResourceKey<DamageType> SOUL_BURN_DAMAGE =
		registerDamageType("soul_burn");

	public static final ResourceKey<DamageType> SHOCKWAVE_DAMAGE =
		registerDamageType("shockwave");

	public static final ResourceKey<DamageType> THROWN_BLOCK_DAMAGE =
		registerDamageType("thrown_block");

	public static final ResourceKey<DamageType> VOID_SLASH_DAMAGE =
		registerDamageType("void_slash");

	public static final ResourceKey<DamageType> EYE_BRIGHT_HEAD_DAMAGE =
		registerDamageType("eye_bright_head");

	public static final Holder<MobEffect> CORRUPTION =
		register("corruption", new CorruptionEffect());

	public static final Holder<MobEffect> VOID_SALVATION =
		register("void_salvation", new VoidSalvationEffect());

	public static final Holder<MobEffect> DIVINE_PROTECTION =
		register("divine_protection", new DivineProtectionEffect());

	public static final Holder<MobEffect> SOUL_BURN =
		register("soul_burn", new SoulburnEffect());

	public static void registerModEffects() {
		VoidDimension.LOGGER.info("Registering mod effects for " + VoidDimension.MOD_ID);
	}
		
	protected static Holder<MobEffect> register(String name, MobEffect entry) {
		return Registry.registerForHolder(
			BuiltInRegistries.MOB_EFFECT,
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, name),
			entry
		);
	}

	protected static ResourceKey<DamageType> registerDamageType(String name) {
		return ResourceKey.create(
			Registries.DAMAGE_TYPE,
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
	}
}
