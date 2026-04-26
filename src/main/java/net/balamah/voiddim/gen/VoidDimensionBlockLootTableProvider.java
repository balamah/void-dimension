package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootTable;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.custom.McGenHelper;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionBlockLootTableProvider extends FabricBlockLootSubProvider {
	public VoidDimensionBlockLootTableProvider(
		FabricPackOutput dataOutput,
		CompletableFuture<HolderLookup.Provider> registryLookup
	) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		LootTable.Builder corpseLootTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.BONE, 2, 5))
			)
		;

		LootTable.Builder voidiumLootTable = 
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModItems.VOIDIUM, 1, 3))
			)
		;

		this.dropSelf(ModBlocks.VOID_SHARD_BLOCK);
		this.dropSelf(ModBlocks.VOID_FLOWER);
		this.dropSelf(ModBlocks.CORRUPT_BLOCK);
		this.dropSelf(ModBlocks.CORRUPTED_LANTERN);

		this.add(ModBlocks.DEEPSLATE_VOIDIUM_ORE, voidiumLootTable);
		this.add(ModBlocks.CORRUPT_VOIDIUM_ORE, voidiumLootTable);

		this.add(
			ModBlocks.WIND_MANIPULATION_BLOCK,
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 2, 6))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1, 2))
			)
		);

		this.add(ModBlocks.OLD_CORPSE, corpseLootTable);
		this.add(ModBlocks.OLD_CORPSE_PILE, corpseLootTable);
	}
}
