package net.balamah.voiddim.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.balamah.voiddim.world.feature.ModPlacedFeatures;

public class ModWorldGeneration {
    public static void registerModWorldGeneration() {
        BiomeModifications.addFeature(
			BiomeSelectors.includeByKey(Biomes.PALE_GARDEN),
			GenerationStep.Feature.UNDERGROUND_ORES,
			ModPlacedFeatures.VOIDIUM_ORE_PLACED_KEY
		);

        BiomeModifications.addFeature(
			BiomeSelectors.includeByKey(Biomes.PALE_GARDEN),
			GenerationStep.Feature.UNDERGROUND_ORES,
			ModPlacedFeatures.DEEPSLATE_VOIDIUM_ORE_PLACED_KEY
		);
    }
}
