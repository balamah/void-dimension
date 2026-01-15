package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootPool;
import net.minecraft.item.Items;

import net.balamah.voiddim.custom.McGenHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionEntityLootTableProvider extends FabricEntityLootTableProvider {
	public VoidDimensionEntityLootTableProvider(
		FabricDataOutput output, CompletableFuture<WrapperLookup> registryLookup
	) {
		super(output, registryLookup);
	}

	@Override
	public void generate() {
		LootPool.Builder corruptedMobsPool =
			McGenHelper.getPool(McGenHelper.constantNumber(1))
			.with(McGenHelper.getItemEntry(ModBlocks.CORRUPT_BLOCK, 1, 3));

		LootTable.Builder voidMawTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.BONE, 1, 3))
			);

		LootTable.Builder corruptedStalkerTable =
			LootTable.builder()
			.pool(corruptedMobsPool)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 1, 2))
				.with(McGenHelper.getItemEntry(Items.FERMENTED_SPIDER_EYE, 1, 2))
			);

		LootTable.Builder voidHarbingerTable =
			LootTable.builder()
			.pool(corruptedMobsPool)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1, 3))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.25f)
				.with(McGenHelper.getItemEntry(ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, 1))
			);

		LootTable.Builder wormOfCorruptionTable =
			LootTable.builder()
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.9f)
					.with(McGenHelper.getItemEntry(Items.DIRT, 1, 16))
					.with(McGenHelper.getItemEntry(Items.OAK_LOG, 1, 8))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.35f)
					.with(McGenHelper.getItemEntry(Items.CHEST, 1))
					.with(McGenHelper.getItemEntry(Items.TRAPPED_CHEST, 1))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(2), 0.45f)
					.with(McGenHelper.getItemEntry(Items.IRON_INGOT, 1, 6))
					.with(McGenHelper.getItemEntry(Items.RAW_IRON, 1, 4))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.3f)
					.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 1, 4))
					.with(McGenHelper.getItemEntry(Items.RAW_GOLD, 1, 3))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.12f)
					.with(McGenHelper.getItemEntry(Items.DIAMOND, 1, 3))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(3), 0.05f)
					.with(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
					.with(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 1))
					.with(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 1))
					.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
					.with(McGenHelper.getItemEntry(Items.CHEST, 1))
				);

		LootTable.Builder werewolfTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(3))
				.with(McGenHelper.getItemEntry(Items.BONE, 1, 3))
				.with(McGenHelper.getItemEntry(Items.ROTTEN_FLESH, 1, 2))
				.with(McGenHelper.getItemEntry(Items.LEATHER, 0, 2))
			);

		LootTable.Builder hollowedBeastTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(3))
				.with(McGenHelper.getItemEntry(Items.BONE, 5, 9))
						);

		LootTable.Builder shatteredSentinelTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 2, 3))
				.with(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 0, 1))
			);

		LootTable.Builder shatteredSentinelMasterTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 10, 11))
				.with(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 6, 7))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1, 2))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.25f)
				.with(McGenHelper.getItemEntry(ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, 1, 2))
			)
			;

		this.register(ModEntities.CORRUPTED_STALKER, corruptedStalkerTable);
		this.register(ModEntities.VOID_MAW, voidMawTable);
		this.register(ModEntities.VOID_HARBINGER, voidHarbingerTable);
		this.register(ModEntities.WORM_OF_CORRUPTION, wormOfCorruptionTable);
		this.register(ModEntities.WEREWOLF, werewolfTable);
		this.register(ModEntities.HOLLOWED_BEAST, hollowedBeastTable);
		this.register(ModEntities.SHATTERED_SENTINEL, shatteredSentinelTable);
		this.register(ModEntities.SHATTERED_SENTINEL_MASTER, shatteredSentinelMasterTable);

		// Didn't add loot table for corrupted versions of vanilla mobs,
		// for i got tired and therefore copied them from minecraft files
	}
}
