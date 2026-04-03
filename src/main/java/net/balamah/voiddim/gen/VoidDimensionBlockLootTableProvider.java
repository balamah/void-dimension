package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.loot.LootTable;
import net.minecraft.item.Items;

import net.balamah.voiddim.custom.McGenHelper;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionBlockLootTableProvider extends FabricBlockLootTableProvider {
	public VoidDimensionBlockLootTableProvider(
		FabricDataOutput dataOutput,
		CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup
	) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		LootTable.Builder corpseLootTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.BONE, 2, 5))
			)
		;

		LootTable.Builder voidiumLootTable = 
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(ModItems.VOIDIUM, 1, 3))
			)
		;

		this.addDrop(ModBlocks.VOID_SHARD_BLOCK);
		this.addDrop(ModBlocks.VOID_FLOWER);
		this.addDrop(ModBlocks.CORRUPT_BLOCK);
		this.addDrop(ModBlocks.CORRUPTED_LANTERN);

		this.addDrop(ModBlocks.DEEPSLATE_VOIDIUM_ORE, voidiumLootTable);
		this.addDrop(ModBlocks.CORRUPT_VOIDIUM_ORE, voidiumLootTable);

		this.addDrop(
			ModBlocks.WIND_MANIPULATION_BLOCK,
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 2, 6))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1, 2))
			)
		);

		this.addDrop(ModBlocks.OLD_CORPSE, corpseLootTable);
		this.addDrop(ModBlocks.OLD_CORPSE_PILE, corpseLootTable);
	}
}
