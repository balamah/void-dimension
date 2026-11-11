package net.balamah.voiddim.world.feature;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> VOIDIUM_ORE_PLACED_KEY =
		register("voidium_ore_overworld");

    public static final ResourceKey<PlacedFeature> DEEPSLATE_VOIDIUM_ORE_PLACED_KEY =
		register("deepslate_voidium_ore_overworld");

    protected static ResourceKey<PlacedFeature> register(String name) {
        return ResourceKey.create(
			Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
    }
}
