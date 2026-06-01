package net.balamah.voiddim.world.feature;

import java.util.List;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> VOIDIUM_ORE_PLACED_KEY =
		register("voidium_ore_overworld");

    public static final RegistryKey<PlacedFeature> DEEPSLATE_VOIDIUM_ORE_PLACED_KEY =
		register("deepslate_voidium_ore_overworld");

	public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, VOIDIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.VOIDIUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));

        register(context, DEEPSLATE_VOIDIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_VOIDIUM_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
	}

    protected static RegistryKey<PlacedFeature> register(String name) {
        return RegistryKey.of(
			RegistryKeys.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
    }

    protected static void register(
		Registerable<PlacedFeature> context,
		RegistryKey<PlacedFeature> key,
		RegistryEntry<ConfiguredFeature<?, ?>> configuration,
		List<PlacementModifier> modifiers
	) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
