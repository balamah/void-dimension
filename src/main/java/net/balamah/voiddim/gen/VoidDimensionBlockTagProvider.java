package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.tag.ModBlockTags;
import net.balamah.voiddim.block.ModBlocks;
import net.minecraft.world.level.block.Block;

public class VoidDimensionBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public VoidDimensionBlockTagProvider(
		FabricPackOutput output,
		CompletableFuture<HolderLookup.Provider> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		this.builder(ModBlockTags.CORRUPTED_FIRE_BASE_BLOCKS)
			.add(this.key(ModBlocks.CORRUPT_BLOCK));

		this.builder(ModBlockTags.CORRUPT_ORE_REPLACEABLES)
			.add(this.key(ModBlocks.CORRUPT_BLOCK));
	}

	private ResourceKey<Block> key(Block block) {
		return BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
	}
}
