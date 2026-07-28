package net.balamah.voiddim.world.feature;

import java.util.List;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> VOIDIUM_ORE_KEY =
		register("voidium_ore");

	public static final ResourceKey<ConfiguredFeature<?, ?>> DEEPSLATE_VOIDIUM_ORE_KEY =
		register("deepslate_voidium_ore");

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldVoidiumOres =
			List.of(
				OreConfiguration.target(
					stoneReplaceables, ModBlocks.VOIDIUM_ORE.defaultBlockState()
				),
				OreConfiguration.target(
					deepslateReplaceables, ModBlocks.DEEPSLATE_VOIDIUM_ORE.defaultBlockState()
				)
			);

        register(
			context,
			VOIDIUM_ORE_KEY,
			Feature.ORE,
			new OreConfiguration(overworldVoidiumOres, 12)
		);

        register(
			context,
			DEEPSLATE_VOIDIUM_ORE_KEY,
			Feature.ORE,
			new OreConfiguration(overworldVoidiumOres, 12)
		);
	}

    public static ResourceKey<ConfiguredFeature<?, ?>> register(String name) {
        return ResourceKey.create(
			Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
    }

    protected static <FC extends FeatureConfiguration, F extends Feature<FC>> void
		register(
			BootstrapContext<ConfiguredFeature<?, ?>> context,
			ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration
		) {
		context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
