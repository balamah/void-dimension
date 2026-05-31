package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryWrapper;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.custom.McGenHelper;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionBlockLootTableProvider extends FabricBlockLootSubProvider {
	public VoidDimensionBlockLootTableProvider(
		FabricPackOutput dataOutput,
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

		this.dropSelf(ModBlocks.VOID_SHARD_BLOCK);
		this.dropSelf(ModBlocks.VOID_FLOWER);
		this.dropSelf(ModBlocks.CORRUPT_BLOCK);
		this.dropSelf(ModBlocks.CORRUPTED_LANTERN);

		this.add(ModBlocks.VOIDIUM_ORE, voidiumLootTable);
		this.add(ModBlocks.DEEPSLATE_VOIDIUM_ORE, voidiumLootTable);
		this.add(ModBlocks.CORRUPT_VOIDIUM_ORE, voidiumLootTable);

		this.add(
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

		this.add(ModBlocks.OLD_CORPSE, corpseLootTable);
		this.add(ModBlocks.OLD_CORPSE_PILE, corpseLootTable);
	}
}
