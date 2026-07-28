package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootSubProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.custom.McGenHelper;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionEntityLootTableProvider extends FabricEntityLootSubProvider {
	public VoidDimensionEntityLootTableProvider(
		FabricPackOutput output, CompletableFuture<Provider> registryLookup
	) {
		super(output, registryLookup);
	}

	@Override
	public void generate() {
		LootPool.Builder corruptedMobsPool =
			McGenHelper.getPool(McGenHelper.constantNumber(1))
			.add(McGenHelper.getItemEntry(ModBlocks.CORRUPT_BLOCK, 1, 3));

		LootTable.Builder voidMawTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.BONE, 1, 3))
			);

		LootTable.Builder corruptedStalkerTable =
			LootTable.lootTable()
			.withPool(corruptedMobsPool)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 1, 2))
				.add(McGenHelper.getItemEntry(Items.FERMENTED_SPIDER_EYE, 1, 2))
			);

		LootTable.Builder voidHarbingerTable =
			LootTable.lootTable()
			.withPool(corruptedMobsPool)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1, 3))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.25f)
				.add(McGenHelper.getItemEntry(ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, 1))
			);

		LootTable.Builder wormOfCorruptionTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2), 0.9f)
					.add(McGenHelper.getItemEntry(Items.DIRT, 1, 16))
					.add(McGenHelper.getItemEntry(Items.OAK_LOG, 1, 8))
					.add(McGenHelper.getItemEntry(Items.CHEST, 1))
					.add(McGenHelper.getItemEntry(Items.TRAPPED_CHEST, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2), 0.55f)
					.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 1, 6))
					.add(McGenHelper.getItemEntry(Items.RAW_IRON, 1, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.4f)
					.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 1, 4))
					.add(McGenHelper.getItemEntry(Items.RAW_GOLD, 1, 3))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2), 0.3f)
					.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 3))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 4, 6))
					.add(McGenHelper.getItemEntry(Items.DIAMOND, 3, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(3), 0.15f)
					.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootPool.Builder bodyPartsPool = 
			McGenHelper.getPool(McGenHelper.constantNumber(3))
			.add(McGenHelper.getItemEntry(Items.BONE, 1, 3))
			.add(McGenHelper.getItemEntry(Items.ROTTEN_FLESH, 1, 2))
			.add(McGenHelper.getItemEntry(Items.LEATHER, 0, 2));

		LootTable.Builder werewolfTable =
			LootTable.lootTable().withPool(bodyPartsPool);

		LootTable.Builder hollowedBeastTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(3))
				.add(McGenHelper.getItemEntry(Items.BONE, 5, 9))
			);

		LootTable.Builder shatteredSentinelTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 2, 3))
				.add(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 0, 1))
			);

		LootTable.Builder shatteredSentinelMasterTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 10, 11))
				.add(McGenHelper.getItemEntry(Items.COBBLED_DEEPSLATE, 6, 7))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.25f)
				.add(McGenHelper.getItemEntry(ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, 1, 2))
			)
			;

		LootTable.Builder hollowedAlphaSteveTable = 
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModItems.RAW_FLESH, 1, 2))
			)
			;

		LootTable.Builder zombifiedAlphaSteveTable = 
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModItems.SPOILED_FLESH, 1, 2))
			)
			;

		LootTable.Builder entity303table =
			voidHarbingerTable.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModItems.CORRUPTED_FIRE_CHARGE, 1, 2))
			)
		;

		LootTable.Builder eyeBrightTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 3))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModItems.CORRUPTED_FIRE_CHARGE, 1, 2))
			)
		;

		LootTable.Builder corruptedWarriorTable =
			voidHarbingerTable.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.2f)
				.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 2, 4))
			);

		this.add(ModEntities.CORRUPTED_STALKER, corruptedStalkerTable);
		this.add(ModEntities.VOID_MAW, voidMawTable);
		this.add(ModEntities.VOID_HARBINGER, voidHarbingerTable);
		this.add(ModEntities.WORM_OF_CORRUPTION, wormOfCorruptionTable);
		this.add(ModEntities.WEREWOLF, werewolfTable);
		this.add(ModEntities.HOLLOWED_BEAST, hollowedBeastTable);
		this.add(ModEntities.SHATTERED_SENTINEL, shatteredSentinelTable);
		this.add(ModEntities.SHATTERED_SENTINEL_MASTER, shatteredSentinelMasterTable);
		this.add(ModEntities.HOLLOWED_ALPHA_STEVE, hollowedAlphaSteveTable);
		this.add(ModEntities.ZOMBIFIED_ALPHA_STEVE, zombifiedAlphaSteveTable);
		this.add(ModEntities.ENTITY303, entity303table);
		this.add(ModEntities.EYE_BRIGHT, eyeBrightTable);
		this.add(ModEntities.CORRUPTED_WARRIOR, corruptedWarriorTable);

		// Didn't add loot table for corrupted versions of vanilla mobs,
		// i got tired and therefore copied them from minecraft's files
	}
}
