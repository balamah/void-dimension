package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.data.loot.ModLootTables;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.custom.McGenHelper;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionChestLootTableProvider extends SimpleFabricLootTableSubProvider {
	public VoidDimensionChestLootTableProvider(
		FabricPackOutput output,
		CompletableFuture<HolderLookup.Provider> registryLookup
	) {
		super(output, registryLookup, LootContextParamSets.CHEST);
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		LootPool.Builder cobwebPool =
			McGenHelper.getPool(McGenHelper.constantNumber(2))
			.add(McGenHelper.getItemEntry(Items.COBWEB, 2, 5));

		LootTable.Builder illusionerChestTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1)).add(LootItem.lootTableItem(Items.BOW))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1)).add(LootItem.lootTableItem(Items.SPLASH_POTION))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 4))
				.add(McGenHelper.getItemEntry(Items.ARROW, 15, 20))
				.add(McGenHelper.getItemEntry(Items.NETHER_WART, 4, 9))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.5f)
				.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 1, 2))
			)
		;

		LootTable.Builder illusionerSecretChestTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.60f)
				.add(McGenHelper.getItemEntry(ModItems.VOID_INGOT, 1, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 4, 6))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 4))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 3))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.75f)
				.add(McGenHelper.getItemEntry(Items.EMERALD, 1, 3))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 2, 4))
			)
			.withPool(cobwebPool)
		;

		LootTable.Builder illusionerKitchenChestTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(4))
				.add(McGenHelper.getItemEntry(Items.COOKED_CHICKEN, 9, 11))
				.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 5, 9))
				.add(McGenHelper.getItemEntry(Items.PORKCHOP, 9, 11))
				.add(McGenHelper.getItemEntry(Items.BEETROOT, 12, 18))
				.add(McGenHelper.getItemEntry(Items.CARROT, 6, 12))
				.add(McGenHelper.getItemEntry(Items.BAKED_POTATO, 3, 12))
				.add(McGenHelper.getItemEntry(Items.COAL, 6, 36))
			)
		;

		LootTable.Builder illusionerWormChestTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(ModBlocks.CORRUPT_BLOCK, 2, 5))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.COBWEB, 2, 3))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.EMERALD, 3, 7))
			)
		;

		LootTable.Builder illusionerSecretTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 3))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 9))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 6))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.COBWEB, 2, 3))
			)
		;

		LootTable.Builder illusionerAltarTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(4))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 5, 9))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 7, 11))
				.add(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 1, 2))
				.add(McGenHelper.getItemEntry(Items.REDSTONE, 2, 4))
			)
		;

		LootTable.Builder illusionerItemsTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.BOW, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.ARROW, 9, 24))
			)
		;

		LootTable.Builder illusionerAlchemyTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(4))
				.add(McGenHelper.getItemEntry(Items.BLAZE_POWDER, 10, 12))
				.add(McGenHelper.getItemEntry(Items.NETHER_WART, 15, 26))
				.add(McGenHelper.getItemEntry(Items.SPLASH_POTION, 1, 2))
				.add(McGenHelper.getItemEntry(Items.MAGMA_CREAM, 5, 7))
			)
		;

		LootTable.Builder abandonedChestTable = LootTable.lootTable().withPool(cobwebPool);

		LootTable.Builder skullTreeChestTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.BONE, 1, 3))
				.add(McGenHelper.getItemEntry(Items.COAL, 1, 4))
				.add(McGenHelper.getItemEntry(Items.COBWEB, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2), 0.12f)
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
			)
		;

		LootTable.Builder failedRailwayChestTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(3))
				.add(McGenHelper.getItemEntry(Items.RAIL, 3, 6))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 2, 3))
				.add(McGenHelper.getItemEntry(Items.POWERED_RAIL, 1, 2))
				.add(McGenHelper.getItemEntry(Items.TNT, 2, 4))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
				.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
		;	

		LootTable.Builder ruinedHouseTable =
			LootTable.lootTable()
			.withPool(cobwebPool)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
				.add(McGenHelper.getItemEntry(Items.GOLD_NUGGET, 2, 4))
			)
		;	

		LootTable.Builder abandonedHouseTable =
			LootTable.lootTable()
			.withPool(cobwebPool)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 3))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 3, 4))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.45f)
				.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 3))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
				.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1, 2))
			)
		;

		LootTable.Builder normalHouseTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.6f)
				.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 3))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 6, 7))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 4, 5))
				.add(McGenHelper.getItemEntry(Items.EMERALD, 3, 4))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 6))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
				.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
		;

		LootTable.Builder cemeteryChestTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1))
						.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
						.add(McGenHelper.getItemEntry(Items.DIAMOND_SWORD, 1))
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SWORD, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 3))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 3, 8))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 1, 2))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.35f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.05f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.5f)
						.add(McGenHelper.getItemEntry(ModItems.LATIN_CROSS, 1))
						.add(McGenHelper.getItemEntry(ModItems.ORTHODOX_CROSS, 1))
						.add(McGenHelper.getItemEntry(ModItems.WOOL_PRAYER_ROPE, 1))
						.add(McGenHelper.getItemEntry(ModItems.WOOD_PRAYER_ROPE, 1))
						.add(McGenHelper.getItemEntry(ModItems.SKULL_PRAYER_ROPE, 1))
				)
		;

		LootTable.Builder infectedRuinedHouseTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.6f)
				.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 4, 5))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(3))
				.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 6, 7))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 3))
				.add(McGenHelper.getItemEntry(Items.EMERALD, 1))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 6))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
				.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
			.withPool(cobwebPool)
		;

		LootTable.Builder infectedTowerHouseWeaponsTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
					.add(McGenHelper.getItemEntry(Items.DIAMOND_SWORD, 1))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_SWORD, 1))
					.add(McGenHelper.getItemEntry(Items.IRON_AXE, 1))
					.add(McGenHelper.getItemEntry(Items.DIAMOND_AXE, 1))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_AXE, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.IRON_PICKAXE, 1))
					.add(McGenHelper.getItemEntry(Items.DIAMOND_PICKAXE, 1))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_PICKAXE, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.BOW, 1))
					.add(McGenHelper.getItemEntry(Items.CROSSBOW, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.ARROW, 12, 32))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 5))
					.add(McGenHelper.getItemEntry(Items.EMERALD, 3, 7))
					.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 4, 10))
					.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 6, 12))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 4, 10))
					.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 6))
						.add(McGenHelper.getItemEntry(Items.BREAD, 3, 8))
			)
			.withPool(cobwebPool)
			;

		LootTable.Builder infdevPyramidRemains =
			LootTable.lootTable()
			.withPool(cobwebPool)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.9f)
				.add(McGenHelper.getItemEntry(ModItems.MUSIC_DISC_CALM4, 1))
			)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 4))
					.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 7))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 5, 7))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 3))
				)
		;

		LootTable.Builder michael1Table =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
					.add(McGenHelper.getItemEntry(Items.IRON_AXE, 1))
					.add(McGenHelper.getItemEntry(Items.BOW, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.ARROW, 12, 32))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
					.add(McGenHelper.getItemEntry(Items.BREAD, 3, 8))
					.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 3, 8))
					.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 2, 6))
					.add(McGenHelper.getItemEntry(Items.EMERALD, 1, 3))
			)
			.withPool(cobwebPool);

		LootTable.Builder michael2Table =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(4))
					.add(McGenHelper.getItemEntry(Items.ROTTEN_FLESH, 6, 16))
					.add(McGenHelper.getItemEntry(ModItems.SPOILED_FLESH, 3, 8))
					.add(McGenHelper.getItemEntry(Items.BONE, 2, 6))
					.add(McGenHelper.getItemEntry(Items.SPIDER_EYE, 1, 4))
			)
			.withPool(cobwebPool);

		LootTable.Builder michaelSecretTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
					.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.50f)
					.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(3))
					.add(McGenHelper.getItemEntry(Items.DIAMOND, 3, 7))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 5))
			)
			.withPool(cobwebPool);

		LootTable.Builder paulTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
					.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.50f)
					.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
					.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 5))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 4))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
					.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
					.add(McGenHelper.getItemEntry(Items.BOW, 1))
					.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 4, 8))
					.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 5))
			)
			.withPool(cobwebPool);

		LootTable.Builder nameless1Table =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
					.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.50f)
					.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
					.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 5))
					.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 4))
			)
			.withPool(cobwebPool);

		LootTable.Builder timothyKitchenTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(4))
					.add(McGenHelper.getItemEntry(Items.ROTTEN_FLESH, 4, 12))
					.add(McGenHelper.getItemEntry(ModItems.SPOILED_FLESH, 3, 8))
					.add(McGenHelper.getItemEntry(Items.BEETROOT, 2, 6))
					.add(McGenHelper.getItemEntry(Items.POTATO, 3, 8))
					.add(McGenHelper.getItemEntry(Items.COAL, 2, 8))
			)
			.withPool(cobwebPool);

		LootTable.Builder timothy2Table =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
					.add(McGenHelper.getItemEntry(Items.IRON_PICKAXE, 1))
					.add(McGenHelper.getItemEntry(Items.BOW, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.ARROW, 12, 24))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
					.add(McGenHelper.getItemEntry(Items.BREAD, 3, 8))
					.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 4, 8))
					.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 2, 5))
			)
			.withPool(cobwebPool);

		LootTable.Builder timothy1Table =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.IRON_AXE, 1))
					.add(McGenHelper.getItemEntry(Items.BOW, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.add(McGenHelper.getItemEntry(Items.ARROW, 8, 20))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
					.add(McGenHelper.getItemEntry(Items.BREAD, 3, 8))
					.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 4))
					.add(McGenHelper.getItemEntry(Items.EMERALD, 1, 3))
			)
			.withPool(cobwebPool);

		LootTable.Builder scholarBedTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.BOOK, 2, 4))
				.add(McGenHelper.getItemEntry(Items.WRITTEN_BOOK, 1))
				.add(McGenHelper.getItemEntry(Items.MAP, 1))
				.add(McGenHelper.getItemEntry(Items.COMPASS, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 5))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 3, 6))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 3))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.35f)
				.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 1, 2))
				.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
				.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
		;

		LootTable.Builder scholarArcherTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.BOW, 1))
				.add(McGenHelper.getItemEntry(Items.CROSSBOW, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.ARROW, 12, 24))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
				.add(McGenHelper.getItemEntry(Items.SPECTRAL_ARROW, 4, 8))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.FEATHER, 3, 6))
				.add(McGenHelper.getItemEntry(Items.FLINT, 2, 5))
				.add(McGenHelper.getItemEntry(Items.STRING, 3, 6))
			)
		;

		LootTable.Builder scholarBeaconTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.add(McGenHelper.getItemEntry(Items.TORCH, 6, 12))
				.add(McGenHelper.getItemEntry(Items.SOUL_TORCH, 2, 5))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.LAVA_BUCKET, 1, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.COAL, 4, 8))
				.add(McGenHelper.getItemEntry(Items.STICK, 4, 8))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.25f)
				.add(McGenHelper.getItemEntry(Items.GLOWSTONE, 2, 4))
			)
		;

		LootTable.Builder brokenBeaconBedTable =
			LootTable.lootTable()
			.withPool(cobwebPool)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.BOOK, 1, 2))
				.add(McGenHelper.getItemEntry(Items.PAPER, 2, 5))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 2, 4))
				.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
				.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 1, 2))
			)
		;

		LootTable.Builder brokenBeaconArcherTable =
			LootTable.lootTable()
			.withPool(cobwebPool)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.BOW, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.ARROW, 6, 16))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.FLINT, 1, 3))
				.add(McGenHelper.getItemEntry(Items.STRING, 1, 3))
			)
		;

		LootTable.Builder brokenBeaconBeaconTable =
			LootTable.lootTable()
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.TORCH, 3, 6))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.50f)
				.add(McGenHelper.getItemEntry(Items.LAVA_BUCKET, 1))
			)
			.withPool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.add(McGenHelper.getItemEntry(Items.COAL, 2, 5))
			)
			.withPool(cobwebPool)
		;
LootTable.Builder fortressKitchenCoalFoodTable =
	LootTable.lootTable()
		.withPool(
			McGenHelper.getPool(McGenHelper.constantNumber(5))
				.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 4, 8))
				.add(McGenHelper.getItemEntry(Items.COOKED_CHICKEN, 4, 8))
				.add(McGenHelper.getItemEntry(Items.BREAD, 4, 10))
				.add(McGenHelper.getItemEntry(Items.BAKED_POTATO, 6, 12))
				.add(McGenHelper.getItemEntry(Items.CARROT, 5, 12))
				.add(McGenHelper.getItemEntry(Items.BEETROOT, 6, 14))
				.add(McGenHelper.getItemEntry(Items.POTATO, 8, 16))
				.add(McGenHelper.getItemEntry(Items.COAL, 12, 32))
		)
		.withPool(
			McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
				.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 5))
		);

		LootTable.Builder fortressMazeSecretTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(3))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 6))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 4, 9))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 6, 14))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.35f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressWoolTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(6))
						.add(McGenHelper.getItemEntry(Items.WHITE_WOOL, 4, 12))
						.add(McGenHelper.getItemEntry(Items.BLACK_WOOL, 4, 12))
						.add(McGenHelper.getItemEntry(Items.GRAY_WOOL, 4, 12))
						.add(McGenHelper.getItemEntry(Items.LIGHT_GRAY_WOOL, 4, 12))
						.add(McGenHelper.getItemEntry(Items.RED_WOOL, 4, 12))
						.add(McGenHelper.getItemEntry(Items.BLUE_WOOL, 4, 12))
						.add(McGenHelper.getItemEntry(Items.YELLOW_WOOL, 4, 12))
						.add(McGenHelper.getItemEntry(Items.LIME_WOOL, 4, 12))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.STRING, 6, 16))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.25f)
						.add(McGenHelper.getItemEntry(Items.SHEARS, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
						.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 4))
				);

		LootTable.Builder fortressWoolSecretTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(3))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 2, 5))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 3, 8))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 10))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.35f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 3))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressArmoryTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
						.add(McGenHelper.getItemEntry(Items.IRON_AXE, 1))
						.add(McGenHelper.getItemEntry(Items.IRON_PICKAXE, 1))
						.add(McGenHelper.getItemEntry(Items.BOW, 1))
						.add(McGenHelper.getItemEntry(Items.CROSSBOW, 1))
						.add(McGenHelper.getItemEntry(Items.SHIELD, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.ARROW, 16, 48))
						.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 4, 12))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 3, 8))
						.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 5))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 1, 2))
				);

		LootTable.Builder fortressLibraryTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.BOOK, 3, 8))
						.add(McGenHelper.getItemEntry(Items.PAPER, 6, 16))
						.add(McGenHelper.getItemEntry(Items.WRITTEN_BOOK, 1))
						.add(McGenHelper.getItemEntry(Items.MAP, 1))
						.add(McGenHelper.getItemEntry(Items.COMPASS, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 5))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 3, 6))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 3))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 1, 2))
						.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressMusicTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.JUKEBOX, 1))
						.add(McGenHelper.getItemEntry(Items.NOTE_BLOCK, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(ModItems.MUSIC_DISC_CALM4, 1))
						.add(McGenHelper.getItemEntry(Items.MUSIC_DISC_CAT, 1))
						.add(McGenHelper.getItemEntry(Items.MUSIC_DISC_CHIRP, 1))
						.add(McGenHelper.getItemEntry(Items.MUSIC_DISC_13, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 2, 5))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
				);

		LootTable.Builder fortressThirdFloorTowerTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.SPYGLASS, 1))
						.add(McGenHelper.getItemEntry(Items.BOW, 1))
						.add(McGenHelper.getItemEntry(Items.CROSSBOW, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.ARROW, 16, 48))
						.add(McGenHelper.getItemEntry(Items.TORCH, 8, 16))
						.add(McGenHelper.getItemEntry(Items.SOUL_TORCH, 4, 8))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.BREAD, 3, 8))
						.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 3, 8))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 1, 3))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressThirdFloorJewelerChestTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(3))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 3, 7))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 5, 10))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 8, 16))
						.add(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 1, 2))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.35f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressThirdFloorCarpenterChestTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(4))
						.add(McGenHelper.getItemEntry(Items.OAK_PLANKS, 16, 48))
						.add(McGenHelper.getItemEntry(Items.SPRUCE_PLANKS, 16, 48))
						.add(McGenHelper.getItemEntry(Items.STICK, 16, 32))
						.add(McGenHelper.getItemEntry(Items.CHEST, 1, 2))
						.add(McGenHelper.getItemEntry(Items.CRAFTING_TABLE, 1))
						.add(McGenHelper.getItemEntry(Items.LADDER, 8, 24))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.IRON_AXE, 1))
						.add(McGenHelper.getItemEntry(Items.IRON_PICKAXE, 1))
						.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 3, 8))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
						.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 5))
				);

		LootTable.Builder fortressThirdFloorBuilderChestTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(5))
						.add(McGenHelper.getItemEntry(Items.STONE_BRICKS, 16, 48))
						.add(McGenHelper.getItemEntry(Items.STONE_BRICK_STAIRS, 8, 24))
						.add(McGenHelper.getItemEntry(Items.STONE_BRICK_SLAB, 8, 24))
						.add(McGenHelper.getItemEntry(Items.COBBLESTONE, 32, 64))
						.add(McGenHelper.getItemEntry(Items.TORCH, 8, 24))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.IRON_PICKAXE, 1))
						.add(McGenHelper.getItemEntry(Items.IRON_SHOVEL, 1))
						.add(McGenHelper.getItemEntry(Items.COAL, 8, 24))
						.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 3, 8))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 4))
				);

		LootTable.Builder fortressThirdFloorProvisionerChestTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(6))
						.add(McGenHelper.getItemEntry(Items.BREAD, 4, 12))
						.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 4, 10))
						.add(McGenHelper.getItemEntry(Items.COOKED_CHICKEN, 4, 10))
						.add(McGenHelper.getItemEntry(Items.BAKED_POTATO, 6, 16))
						.add(McGenHelper.getItemEntry(Items.CARROT, 8, 16))
						.add(McGenHelper.getItemEntry(Items.BEETROOT, 8, 16))
						.add(McGenHelper.getItemEntry(Items.APPLE, 4, 10))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.COAL, 8, 16))
						.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 5))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 1, 3))
				);

		LootTable.Builder fortressThirdFloorWeaponerChestTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(3))
						.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
						.add(McGenHelper.getItemEntry(Items.DIAMOND_SWORD, 1))
						.add(McGenHelper.getItemEntry(Items.IRON_AXE, 1))
						.add(McGenHelper.getItemEntry(Items.DIAMOND_AXE, 1))
						.add(McGenHelper.getItemEntry(Items.BOW, 1))
						.add(McGenHelper.getItemEntry(Items.CROSSBOW, 1))
						.add(McGenHelper.getItemEntry(Items.SHIELD, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.ARROW, 24, 64))
						.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 4, 12))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 3))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.30f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 1, 3))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressThirdFloorSecretChestTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(4))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 4, 8))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 6, 12))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 10, 20))
						.add(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 2, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.40f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 5))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressThirdFloorTentChestTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(5))
						.add(McGenHelper.getItemEntry(Items.BREAD, 4, 10))
						.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 4, 8))
						.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 5))
						.add(McGenHelper.getItemEntry(Items.MAP, 1))
						.add(McGenHelper.getItemEntry(Items.COMPASS, 1))
						.add(McGenHelper.getItemEntry(Items.ARROW, 12, 32))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.BOW, 1))
						.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
				);

		LootTable.Builder fortressWaresMaterialsTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(5))
						.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 6, 16))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 4, 12))
						.add(McGenHelper.getItemEntry(Items.COAL, 12, 32))
						.add(McGenHelper.getItemEntry(Items.REDSTONE, 8, 24))
						.add(McGenHelper.getItemEntry(Items.LAPIS_LAZULI, 8, 24))
						.add(McGenHelper.getItemEntry(Items.QUARTZ, 8, 24))
						.add(McGenHelper.getItemEntry(Items.COPPER_INGOT, 8, 24))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 6))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 3))
				);

		LootTable.Builder fortressWaresWeaponsTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(3))
						.add(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
						.add(McGenHelper.getItemEntry(Items.IRON_AXE, 1))
						.add(McGenHelper.getItemEntry(Items.BOW, 1))
						.add(McGenHelper.getItemEntry(Items.CROSSBOW, 1))
						.add(McGenHelper.getItemEntry(Items.SHIELD, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.add(McGenHelper.getItemEntry(Items.ARROW, 24, 64))
						.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 4, 12))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 1, 2))
				);

		LootTable.Builder fortressWaresTreasuresTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(4))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 3, 8))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 6, 14))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 8, 18))
						.add(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 1, 3))
						.add(McGenHelper.getItemEntry(Items.IRON_INGOT, 6, 12))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.35f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressWaresProvisionsTable =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(6))
						.add(McGenHelper.getItemEntry(Items.BREAD, 6, 12))
						.add(McGenHelper.getItemEntry(Items.COOKED_BEEF, 4, 10))
						.add(McGenHelper.getItemEntry(Items.COOKED_CHICKEN, 4, 10))
						.add(McGenHelper.getItemEntry(Items.BAKED_POTATO, 8, 16))
						.add(McGenHelper.getItemEntry(Items.CARROT, 8, 16))
						.add(McGenHelper.getItemEntry(Items.BEETROOT, 8, 16))
						.add(McGenHelper.getItemEntry(Items.APPLE, 4, 10))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(3))
						.add(McGenHelper.getItemEntry(Items.COAL, 8, 24))
						.add(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 5))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 2, 4))
				);

		LootTable.Builder fortressCaineRoom1Table =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(4))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 4, 8))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 8, 16))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 12, 24))
						.add(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 2, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.40f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 5))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		LootTable.Builder fortressCaineRoom2Table =
			LootTable.lootTable()
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(5))
						.add(McGenHelper.getItemEntry(Items.DIAMOND, 5, 10))
						.add(McGenHelper.getItemEntry(Items.EMERALD, 10, 20))
						.add(McGenHelper.getItemEntry(Items.GOLD_INGOT, 16, 32))
						.add(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 2, 5))
						.add(McGenHelper.getItemEntry(Items.IRON_BLOCK, 2, 4))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.50f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 3, 6))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.25f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.20f)
						.add(McGenHelper.getItemEntry(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1))
				)
				.withPool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
						.add(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				);

		consumer.accept(ModLootTables.ILLUSIONER_HOUSE_WORM_ROOM_LOOT, illusionerWormChestTable);
		consumer.accept(ModLootTables.ILLUSIONER_HOUSE_KITCHEN_LOOT, illusionerKitchenChestTable);
		consumer.accept(ModLootTables.ILLUSIONER_HOUSE_SECRET_LOOT, illusionerSecretTable);
		consumer.accept(ModLootTables.ILLUSIONER_HOUSE_ALTAR_LOOT, illusionerAltarTable);
		consumer.accept(ModLootTables.ILLUSIONER_HOUSE_ALCHEMY_LOOT, illusionerAlchemyTable);
		consumer.accept(ModLootTables.ABANDONED_CHEST_LOOT, abandonedChestTable);
		consumer.accept(ModLootTables.SKULL_TREE_LOOT, skullTreeChestTable);
		consumer.accept(ModLootTables.FAILED_RAILWAY_LOOT, failedRailwayChestTable);
		consumer.accept(ModLootTables.RUINED_HOUSE_LOOT, ruinedHouseTable);
		consumer.accept(ModLootTables.ABANDONED_HOUSE_LOOT, abandonedHouseTable);
		consumer.accept(ModLootTables.NORMAL_HOUSE_LOOT, normalHouseTable);
		consumer.accept(
			ModLootTables.ILLUSIONER_HOUSE_ILLUSIONER_ITEMS_LOOT, illusionerItemsTable
		);

		consumer.accept(ModLootTables.ILLUSIONER_CHURCH_CHEST_LOOT, illusionerChestTable);

		consumer.accept(
			ModLootTables.ILLUSIONER_CHURCH_SECRET_CHEST_LOOT, illusionerSecretChestTable
		);

		consumer.accept(ModLootTables.CEMETERY_CHEST_LOOT, cemeteryChestTable);
		consumer.accept(ModLootTables.INFECTED_RUINED_HOUSE_LOOT, infectedRuinedHouseTable);
		consumer.accept(
			ModLootTables.INFECTED_TOWER_HOUSE_WEAPONS_LOOT, infectedTowerHouseWeaponsTable
		);

		consumer.accept(ModLootTables.INFDEV_PYRAMID_REMAINS_LOOT, infdevPyramidRemains);

		consumer.accept(ModLootTables.MICHAEL_1_LOOT, michael1Table);
		consumer.accept(ModLootTables.MICHAEL_2_LOOT, michael2Table);
		consumer.accept(ModLootTables.MICHAEL_SECRET_LOOT, michaelSecretTable);
		consumer.accept(ModLootTables.PAUL_LOOT, paulTable);
		consumer.accept(ModLootTables.NAMELESS_1_LOOT, nameless1Table);
		consumer.accept(ModLootTables.TIMOTHY_KITCHEN_LOOT, timothyKitchenTable);
		consumer.accept(ModLootTables.TIMOTHY_2_LOOT, timothy2Table);
		consumer.accept(ModLootTables.TIMOTHY_1_LOOT, timothy1Table);

		consumer.accept(ModLootTables.SCHOLAR_BED_LOOT, scholarBedTable);
		consumer.accept(ModLootTables.SCHOLAR_ARCHER_LOOT, scholarArcherTable);
		consumer.accept(ModLootTables.SCHOLAR_BEACON_LOOT, scholarBeaconTable);
		consumer.accept(ModLootTables.BROKEN_BEACON_BED_LOOT, brokenBeaconBedTable);
		consumer.accept(ModLootTables.BROKEN_BEACON_ARCHER_LOOT, brokenBeaconArcherTable);
		consumer.accept(ModLootTables.BROKEN_BEACON_BEACON_LOOT, brokenBeaconBeaconTable);

		consumer.accept(
			ModLootTables.FORTRESS_KITCHEN_COAL_FOOD_LOOT, fortressKitchenCoalFoodTable
		);

		consumer.accept(ModLootTables.FORTRESS_MAZE_SECRET_LOOT, fortressMazeSecretTable);
		consumer.accept(ModLootTables.FORTRESS_WOOL_LOOT, fortressWoolTable);
		consumer.accept(ModLootTables.FORTRESS_WOOL_SECRET_LOOT, fortressWoolSecretTable);
		consumer.accept(ModLootTables.FORTRESS_ARMORY_LOOT, fortressArmoryTable);

		consumer.accept(ModLootTables.FORTRESS_LIBRARY_LOOT, fortressLibraryTable);
		consumer.accept(ModLootTables.FORTRESS_MUSIC_LOOT, fortressMusicTable);

		consumer.accept(
			ModLootTables.FORTRESS_THIRD_FLOOR_TOWER_LOOT, 
			fortressThirdFloorTowerTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_THIRD_FLOOR_JEWELER_CHEST_LOOT, 
			fortressThirdFloorJewelerChestTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_THIRD_FLOOR_CARPENTER_CHEST_LOOT, 
			fortressThirdFloorCarpenterChestTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_THIRD_FLOOR_BUILDER_CHEST_LOOT, 
			fortressThirdFloorBuilderChestTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_THIRD_FLOOR_PROVISIONER_CHEST_LOOT, 
			fortressThirdFloorProvisionerChestTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_THIRD_FLOOR_WEAPONER_CHEST_LOOT, 
			fortressThirdFloorWeaponerChestTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_THIRD_FLOOR_SECRET_CHEST_LOOT, 
			fortressThirdFloorSecretChestTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_THIRD_FLOOR_TENT_CHEST_LOOT, 
			fortressThirdFloorTentChestTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_WARES_MATERIALS_LOOT, fortressWaresMaterialsTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_WARES_WEAPONS_LOOT, fortressWaresWeaponsTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_WARES_TREASURES_LOOT, fortressWaresTreasuresTable
		);

		consumer.accept(
			ModLootTables.FORTRESS_WARES_PROVISIONS_LOOT, fortressWaresProvisionsTable
		);

		consumer.accept(ModLootTables.FORTRESS_CAINE_ROOM_1_LOOT, fortressCaineRoom1Table);
		consumer.accept(ModLootTables.FORTRESS_CAINE_ROOM_2_LOOT, fortressCaineRoom2Table);
	}
}
