package net.balamah.voiddim.world.feature;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.List;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.block.ModBlocks;

public class ModConfiguredFeatures {
	public static final RegistryKey<ConfiguredFeature<?, ?>> DEEPSLATE_VOID_SHARD_ORE_KEY =
		register("pink_garnet_ore");

	public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldDeepslateVoidShardOres =
			List.of(
				OreFeatureConfig.createTarget(
					deepslateReplaceables, ModBlocks.DEEPSLATE_VOID_SHARD_ORE.getDefaultState()
				)
			);

        register(
			context,
			DEEPSLATE_VOID_SHARD_ORE_KEY,
			Feature.ORE,
			new OreFeatureConfig(overworldDeepslateVoidShardOres, 12)
		);
	}

    public static RegistryKey<ConfiguredFeature<?, ?>> register(String name) {
        return RegistryKey.of(
			RegistryKeys.CONFIGURED_FEATURE, Identifier.of(VoidDimension.MOD_ID, name)
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
