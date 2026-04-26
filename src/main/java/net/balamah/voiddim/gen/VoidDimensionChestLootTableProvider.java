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
	}
}
