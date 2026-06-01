package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.registry.RegistryWrapper;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.tag.ModBlockTags;
import net.balamah.voiddim.block.ModBlocks;

public class VoidDimensionBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public VoidDimensionBlockTagProvider(
		FabricPackOutput output,
		CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
	) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(RegistryWrapper.WrapperLookup wrapperLookup) {
		this.valueLookupBuilder(ModBlockTags.CORRUPTED_FIRE_BASE_BLOCKS)
			.add(ModBlocks.CORRUPT_BLOCK);

		this.valueLookupBuilder(ModBlockTags.CORRUPT_ORE_REPLACEABLES)
			.add(ModBlocks.CORRUPT_BLOCK);
	}
}
