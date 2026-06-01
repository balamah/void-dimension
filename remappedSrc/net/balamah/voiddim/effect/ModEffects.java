package net.balamah.voiddim.effect;

import net.balamah.voiddim.effect.custom.*;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class ModEffects {
	public static final RegistryKey<DamageType> CORRUPTION_DAMAGE =
		registerDamageType("corruption");

	public static final RegistryKey<DamageType> SOUL_BURN_DAMAGE =
		registerDamageType("soul_burn");

	public static final RegistryKey<DamageType> SHOCKWAVE_DAMAGE =
		registerDamageType("shockwave");

	public static final RegistryKey<DamageType> THROWN_BLOCK_DAMAGE =
		registerDamageType("thrown_block");

	public static final RegistryKey<DamageType> VOID_SLASH_DAMAGE =
		registerDamageType("void_slash");

	public static final RegistryKey<DamageType> EYE_BRIGHT_HEAD_DAMAGE =
		registerDamageType("eye_bright_head");

	public static final RegistryEntry<StatusEffect> CORRUPTION =
		register("corruption", new CorruptionEffect());

	public static final RegistryEntry<StatusEffect> VOID_SALVATION =
		register("void_salvation", new VoidSalvationEffect());

	public static final RegistryEntry<StatusEffect> DIVINE_PROTECTION =
		register("divine_protection", new DivineProtectionEffect());

	public static final RegistryEntry<StatusEffect> SOUL_BURN =
		register("soul_burn", new SoulburnEffect());

	public static void registerModEffects() {
		VoidDimension.LOGGER.info("Registering mod effects for " + VoidDimension.MOD_ID);
	}
		
	protected static RegistryEntry<StatusEffect> register(String name, StatusEffect entry) {
		return Registry.registerReference(
			Registries.STATUS_EFFECT,
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name),
			entry
		);
	}

	protected static RegistryKey<DamageType> registerDamageType(String name) {
		return RegistryKey.of(
			RegistryKeys.DAMAGE_TYPE,
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
	}
}
