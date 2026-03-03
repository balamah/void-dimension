package net.balamah.voiddim.potion;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.potion.Potions;
import net.minecraft.potion.Potion;
import net.minecraft.item.Item;

import net.minecraft.registry.entry.RegistryEntry;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.item.ModItems;

public class ModPotions {
	public static final Potion VOID_SALVATION_POTION =
		register("void_salvation", ModEffects.VOID_SALVATION, 1200, 0);

	public static void registerModPotions() {
		VoidDimension.LOGGER.info("Registering mod potions for " + VoidDimension.MOD_ID);

		buildRecipe(Potions.AWKWARD, ModItems.VOIDIUM, VOID_SALVATION_POTION);
	}

	protected static Potion getPotion(
		String name, RegistryEntry<StatusEffect> effect, int duration, int amplifier
	) {
		return new Potion(name, new StatusEffectInstance(effect, duration, amplifier));
	}

	protected static Potion register(
		String name, RegistryEntry<StatusEffect> effect, int duration, int amplifier
	) {
		return Registry.register(
			Registries.POTION,
			Identifier.of(VoidDimension.MOD_ID, name),
			getPotion(name, effect, duration, amplifier)
		);
	}

	protected static void buildRecipe(
		RegistryEntry<Potion> input, Item ingredient, Potion output
	) {
		FabricBrewingRecipeRegistryBuilder.BUILD.register(
			builder -> {
				builder.registerPotionRecipe(
					input, ingredient, Registries.POTION.getEntry(output)
				);
			});
	}
}
