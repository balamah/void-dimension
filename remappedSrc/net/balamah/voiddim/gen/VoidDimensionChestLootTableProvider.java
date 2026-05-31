package net.balamah.voiddim.gen;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.balamah.voiddim.data.loot.ModLootTables;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.custom.McGenHelper;
import net.balamah.voiddim.item.ModItems;

public class VoidDimensionChestLootTableProvider extends SimpleFabricLootTableSubProvider {
	public VoidDimensionChestLootTableProvider(
		FabricPackOutput output,
		CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup
	) {
		super(output, registryLookup, LootContextTypes.CHEST);
	}

	@Override
	public void generate(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> consumer) {
		LootPool.Builder cobwebPool =
			McGenHelper.getPool(McGenHelper.constantNumber(2))
			.with(McGenHelper.getItemEntry(Items.COBWEB, 2, 5));

		LootTable.Builder illusionerChestTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1)).with(ItemEntry.builder(Items.BOW))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1)).with(ItemEntry.builder(Items.SPLASH_POTION))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 2, 4))
				.with(McGenHelper.getItemEntry(Items.ARROW, 15, 20))
				.with(McGenHelper.getItemEntry(Items.NETHER_WART, 4, 9))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.5f)
				.with(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 1, 2))
			)
		;

		LootTable.Builder illusionerSecretChestTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.60f)
				.with(McGenHelper.getItemEntry(ModItems.VOID_INGOT, 1, 2))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 4, 6))
				.with(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 4))
				.with(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 3))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.75f)
				.with(McGenHelper.getItemEntry(Items.EMERALD, 1, 3))
				.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 2, 4))
			)
			.pool(cobwebPool)
		;

		LootTable.Builder illusionerKitchenChestTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(4))
				.with(McGenHelper.getItemEntry(Items.COOKED_CHICKEN, 9, 11))
				.with(McGenHelper.getItemEntry(Items.COOKED_BEEF, 5, 9))
				.with(McGenHelper.getItemEntry(Items.PORKCHOP, 9, 11))
				.with(McGenHelper.getItemEntry(Items.BEETROOT, 12, 18))
				.with(McGenHelper.getItemEntry(Items.CARROT, 6, 12))
				.with(McGenHelper.getItemEntry(Items.BAKED_POTATO, 3, 12))
				.with(McGenHelper.getItemEntry(Items.COAL, 6, 36))
			)
		;

		LootTable.Builder illusionerWormChestTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(ModBlocks.CORRUPT_BLOCK, 2, 5))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.COBWEB, 2, 3))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.EMERALD, 3, 7))
			)
		;

		LootTable.Builder illusionerSecretTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 3))
				.with(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 2))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 9))
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 2, 6))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.COBWEB, 2, 3))
			)
		;

		LootTable.Builder illusionerAltarTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(4))
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 5, 9))
				.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 7, 11))
				.with(McGenHelper.getItemEntry(Items.GOLD_BLOCK, 1, 2))
				.with(McGenHelper.getItemEntry(Items.REDSTONE, 2, 4))
			)
		;

		LootTable.Builder illusionerItemsTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.BOW, 1))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.ARROW, 9, 24))
			)
		;

		LootTable.Builder illusionerAlchemyTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(4))
				.with(McGenHelper.getItemEntry(Items.BLAZE_POWDER, 10, 12))
				.with(McGenHelper.getItemEntry(Items.NETHER_WART, 15, 26))
				.with(McGenHelper.getItemEntry(Items.SPLASH_POTION, 1, 2))
				.with(McGenHelper.getItemEntry(Items.MAGMA_CREAM, 5, 7))
			)
		;

		LootTable.Builder abandonedChestTable = LootTable.builder().pool(cobwebPool);

		LootTable.Builder skullTreeChestTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.BONE, 1, 3))
				.with(McGenHelper.getItemEntry(Items.COAL, 1, 4))
				.with(McGenHelper.getItemEntry(Items.COBWEB, 2))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2), 0.12f)
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
				.with(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
			)
		;

		LootTable.Builder failedRailwayChestTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(3))
				.with(McGenHelper.getItemEntry(Items.RAIL, 3, 6))
				.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 2, 3))
				.with(McGenHelper.getItemEntry(Items.POWERED_RAIL, 1, 2))
				.with(McGenHelper.getItemEntry(Items.TNT, 2, 4))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
				.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
		;	

		LootTable.Builder ruinedHouseTable =
			LootTable.builder()
			.pool(cobwebPool)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 1, 2))
				.with(McGenHelper.getItemEntry(Items.GOLD_NUGGET, 2, 4))
			)
		;	

		LootTable.Builder abandonedHouseTable =
			LootTable.builder()
			.pool(cobwebPool)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 2, 3))
				.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 3, 4))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.45f)
				.with(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 3))
				.with(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 2))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
				.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1, 2))
			)
		;

		LootTable.Builder normalHouseTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.6f)
				.with(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 2, 3))
				.with(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 2))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(2))
				.with(McGenHelper.getItemEntry(Items.IRON_INGOT, 6, 7))
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 4, 5))
				.with(McGenHelper.getItemEntry(Items.EMERALD, 3, 4))
				.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 6))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.15f)
				.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
		;

		LootTable.Builder cemeteryChestTable =
			LootTable.builder()
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1))
						.with(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
						.with(McGenHelper.getItemEntry(Items.DIAMOND_SWORD, 1))
						.with(McGenHelper.getItemEntry(Items.NETHERITE_SWORD, 1))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(2))
						.with(McGenHelper.getItemEntry(Items.DIAMOND, 2, 3))
						.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 3, 8))
						.with(McGenHelper.getItemEntry(Items.EMERALD, 1, 2))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.35f)
						.with(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.05f)
						.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
				)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.5f)
						.with(McGenHelper.getItemEntry(ModItems.LATIN_CROSS, 1))
						.with(McGenHelper.getItemEntry(ModItems.ORTHODOX_CROSS, 1))
						.with(McGenHelper.getItemEntry(ModItems.WOOL_PRAYER_ROPE, 1))
						.with(McGenHelper.getItemEntry(ModItems.WOOD_PRAYER_ROPE, 1))
						.with(McGenHelper.getItemEntry(ModItems.SKULL_PRAYER_ROPE, 1))
				)
		;

		LootTable.Builder infectedRuinedHouseTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.6f)
				.with(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 4, 5))
				.with(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 2))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(3))
				.with(McGenHelper.getItemEntry(Items.IRON_INGOT, 6, 7))
				.with(McGenHelper.getItemEntry(Items.DIAMOND, 2, 3))
				.with(McGenHelper.getItemEntry(Items.EMERALD, 1))
				.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 6))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1), 0.10f)
				.with(McGenHelper.getItemEntry(ModItems.VOID_SHARD, 1))
			)
			.pool(cobwebPool)
		;

		LootTable.Builder infectedTowerHouseWeaponsTable =
			LootTable.builder()
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.with(McGenHelper.getItemEntry(Items.IRON_SWORD, 1))
					.with(McGenHelper.getItemEntry(Items.DIAMOND_SWORD, 1))
					.with(McGenHelper.getItemEntry(Items.NETHERITE_SWORD, 1))
					.with(McGenHelper.getItemEntry(Items.IRON_AXE, 1))
					.with(McGenHelper.getItemEntry(Items.DIAMOND_AXE, 1))
					.with(McGenHelper.getItemEntry(Items.NETHERITE_AXE, 1))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.with(McGenHelper.getItemEntry(Items.IRON_PICKAXE, 1))
					.with(McGenHelper.getItemEntry(Items.DIAMOND_PICKAXE, 1))
					.with(McGenHelper.getItemEntry(Items.NETHERITE_PICKAXE, 1))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.with(McGenHelper.getItemEntry(Items.BOW, 1))
					.with(McGenHelper.getItemEntry(Items.CROSSBOW, 1))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.with(McGenHelper.getItemEntry(Items.ARROW, 12, 32))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.with(McGenHelper.getItemEntry(Items.DIAMOND, 2, 5))
					.with(McGenHelper.getItemEntry(Items.EMERALD, 3, 7))
					.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 4, 10))
					.with(McGenHelper.getItemEntry(Items.IRON_INGOT, 6, 12))
			)
			.pool(
				McGenHelper.getPool(McGenHelper.constantNumber(1))
					.with(McGenHelper.getItemEntry(Items.COOKED_BEEF, 4, 10))
					.with(McGenHelper.getItemEntry(Items.GOLDEN_CARROT, 2, 6))
						.with(McGenHelper.getItemEntry(Items.BREAD, 3, 8))
			)
			.pool(cobwebPool)
			;

			LootTable.Builder infdevPyramidRemains =
				LootTable.builder()
				.pool(cobwebPool)
				.pool(
					McGenHelper.getPool(McGenHelper.constantNumber(1), 0.9f)
					.with(McGenHelper.getItemEntry(ModItems.MUSIC_DISC_CALM4, 1))
				)
					.pool(
						McGenHelper.getPool(McGenHelper.constantNumber(1))
						.with(McGenHelper.getItemEntry(Items.DIAMOND, 2, 4))
						.with(McGenHelper.getItemEntry(Items.GOLD_INGOT, 5, 7))
					)
					.pool(
						McGenHelper.getPool(McGenHelper.constantNumber(1))
						.with(McGenHelper.getItemEntry(Items.NETHERITE_SCRAP, 5, 7))
						.with(McGenHelper.getItemEntry(Items.NETHERITE_INGOT, 1, 3))
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
