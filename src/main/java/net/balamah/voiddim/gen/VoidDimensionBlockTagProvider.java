package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.block.Block;

import net.balamah.voiddim.tag.ModBlockTags;
import net.balamah.voiddim.block.ModBlocks;

public class VoidDimensionBlockTagProvider extends FabricTagProvider<Block> {
	public VoidDimensionBlockTagProvider(
		FabricDataOutput output,
		CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture
	) {
		super(output, RegistryKeys.BLOCK, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		this.getTagBuilder(ModBlockTags.CORRUPTED_FIRE_BASE_BLOCKS)
			.add(this.getBlockIdentifier(ModBlocks.CORRUPT_BLOCK));

		this.getTagBuilder(ModBlockTags.CORRUPT_ORE_REPLACEABLES)
			.add(this.getBlockIdentifier(ModBlocks.CORRUPT_BLOCK));
	}

	protected Identifier getBlockIdentifier(Block block) {
		return Registries.BLOCK.getId(block);
	}
}
