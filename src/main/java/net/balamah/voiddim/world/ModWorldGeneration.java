package net.balamah.voiddim.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.balamah.voiddim.world.feature.ModPlacedFeatures;

public class ModWorldGeneration {
    public static void registerModWorldGeneration() {
        BiomeModifications.addFeature(
			BiomeSelectors.includeByKey(Biomes.DARK_FOREST),
			GenerationStep.Decoration.UNDERGROUND_ORES,
			ModPlacedFeatures.VOIDIUM_ORE_PLACED_KEY
		);

        BiomeModifications.addFeature(
			BiomeSelectors.includeByKey(Biomes.DARK_FOREST),
			GenerationStep.Decoration.UNDERGROUND_ORES,
			ModPlacedFeatures.DEEPSLATE_VOIDIUM_ORE_PLACED_KEY
		);
    }
}
