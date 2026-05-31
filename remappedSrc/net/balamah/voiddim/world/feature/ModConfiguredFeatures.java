package net.balamah.voiddim.world.feature;

import java.util.List;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ModConfiguredFeatures {
	public static final RegistryKey<ConfiguredFeature<?, ?>> VOIDIUM_ORE_KEY =
		register("voidium_ore");

	public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_VOIDIUM_ORE_KEY =
		register("deepslate_voidium_ore");

	public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldVoidiumOres =
			List.of(
				OreFeatureConfig.createTarget(
					stoneReplaceables, ModBlocks.VOIDIUM_ORE.getDefaultState()
				),
				OreFeatureConfig.createTarget(
					deepslateReplaceables, ModBlocks.DEEPSLATE_VOIDIUM_ORE.getDefaultState()
				)
			);

        register(
			context,
			VOIDIUM_ORE_KEY,
			Feature.ORE,
			new OreFeatureConfig(overworldVoidiumOres, 12)
		);

        register(
			context,
			DEEPSLATE_VOIDIUM_ORE_KEY,
			Feature.ORE,
			new OreFeatureConfig(overworldVoidiumOres, 12)
		);
	}

    public static RegistryKey<ConfiguredFeature<?, ?>> register(String name) {
        return RegistryKey.of(
			RegistryKeys.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
    }

    protected static <FC extends FeatureConfig, F extends Feature<FC>> void
		register(
			Registerable<ConfiguredFeature<?, ?>> context,
			RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration
		) {
		context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
