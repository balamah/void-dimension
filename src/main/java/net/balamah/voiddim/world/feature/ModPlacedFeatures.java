package net.balamah.voiddim.world.feature;

import java.util.List;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> DEEPSLATE_VOID_SHARD_ORE_PLACED_KEY =
		register("deepslate_voidium_ore_overworld");

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, DEEPSLATE_VOID_SHARD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DEEPSLATE_VOID_SHARD_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80))));
	}

    protected static ResourceKey<PlacedFeature> register(String name) {
        return ResourceKey.create(
			Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
    }

    protected static void register(
		BootstrapContext<PlacedFeature> context,
		ResourceKey<PlacedFeature> key,
		Holder<ConfiguredFeature<?, ?>> configuration,
		List<PlacementModifier> modifiers
	) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
