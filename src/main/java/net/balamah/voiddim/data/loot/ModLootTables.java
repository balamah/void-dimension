package net.balamah.voiddim.data.loot;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTables {
	public static ResourceKey<LootTable> ILLUSIONER_CHURCH_CHEST_LOOT =
		register("chests/illusioner_church_chest_loot");

	public static ResourceKey<LootTable> ILLUSIONER_CHURCH_SECRET_CHEST_LOOT =
		register("chests/illusioner_church_secret_chest_loot");

	public static ResourceKey<LootTable> ILLUSIONER_HOUSE_ALCHEMY_LOOT =
		register("chests/illusioner_house_alchemy_loot");

	public static ResourceKey<LootTable> ILLUSIONER_HOUSE_SECRET_LOOT =
		register("chests/illusioner_house_secret_loot");

	public static ResourceKey<LootTable> ILLUSIONER_HOUSE_KITCHEN_LOOT =
		register("chests/illusioner_house_kitchen_loot");

	public static ResourceKey<LootTable> ILLUSIONER_HOUSE_WORM_ROOM_LOOT =
		register("chests/illusioner_house_worm_room_loot");

	public static ResourceKey<LootTable> ILLUSIONER_HOUSE_ALTAR_LOOT =
		register("chests/illusioner_house_altar_loot");

	public static ResourceKey<LootTable> ILLUSIONER_HOUSE_ILLUSIONER_ITEMS_LOOT =
		register("chests/illusioner_house_illusioner_items_loot");

	public static ResourceKey<LootTable> ABANDONED_CHEST_LOOT =
		register("chests/abandoned_chest_loot");

	public static ResourceKey<LootTable> ABANDONED_HOUSE_LOOT =
		register("chests/abandoned_house_loot");

	public static ResourceKey<LootTable> RUINED_HOUSE_LOOT =
		register("chests/ruined_house_loot");

	public static ResourceKey<LootTable> NORMAL_HOUSE_LOOT =
		register("chests/normal_house_loot");

	public static ResourceKey<LootTable> SKULL_TREE_LOOT =
		register("chests/skull_tree_loot");

	public static ResourceKey<LootTable> FAILED_RAILWAY_LOOT =
		register("chests/failed_railway_loot");

	public static ResourceKey<LootTable> CEMETERY_CHEST_LOOT =
		register("chests/cemetery_chest_loot");

	public static ResourceKey<LootTable> INFECTED_RUINED_HOUSE_LOOT =
		register("chests/infected_ruined_house_loot");

	public static ResourceKey<LootTable> INFECTED_TOWER_HOUSE_WEAPONS_LOOT =
		register("chests/infected_tower_house_weapons_loot");

	public static ResourceKey<LootTable> INFDEV_PYRAMID_REMAINS_LOOT =
		register("chests/infdev_pyramid_remains_loot");

	public static ResourceKey<LootTable> MICHAEL_1_LOOT =
		register("chests/expedition_bases/michael_1_loot");

	public static ResourceKey<LootTable> MICHAEL_2_LOOT =
		register("chests/expedition_bases/michael_2_loot");

	public static ResourceKey<LootTable> MICHAEL_SECRET_LOOT =
		register("chests/expedition_bases/michael_secret_loot");

	public static ResourceKey<LootTable> PAUL_LOOT =
		register("chests/expedition_bases/paul_loot");

	public static ResourceKey<LootTable> NAMELESS_1_LOOT =
		register("chests/expedition_bases/nameless_1_loot");

	public static ResourceKey<LootTable> TIMOTHY_KITCHEN_LOOT =
		register("chests/expedition_bases/timothy_kitchen_loot");

	public static ResourceKey<LootTable> TIMOTHY_2_LOOT =
		register("chests/expedition_bases/timothy_2_loot");

	public static ResourceKey<LootTable> TIMOTHY_1_LOOT =
		register("chests/expedition_bases/timothy_1_loot");

	public static ResourceKey<LootTable> BROKEN_BEACON_BED_LOOT =
		register("chests/broken_beacon_bed_loot");

	public static ResourceKey<LootTable> BROKEN_BEACON_ARCHER_LOOT =
		register("chests/broken_beacon_archer_loot");

	public static ResourceKey<LootTable> BROKEN_BEACON_BEACON_LOOT =
		register("chests/broken_beacon_beacon_loot");

	public static ResourceKey<LootTable> SCHOLAR_BED_LOOT =
		register("chests/expedition_bases/scholar_bed_loot");

	public static ResourceKey<LootTable> SCHOLAR_ARCHER_LOOT =
		register("chests/expedition_bases/scholar_archer_loot");

	public static ResourceKey<LootTable> SCHOLAR_BEACON_LOOT =
		register("chests/expedition_bases/scholar_beacon_loot");

	public static void registerModLootTables() {
		VoidDimension.LOGGER.info("Registering mod loot tables for " + VoidDimension.MOD_ID);
	}

	protected static ResourceKey<LootTable> register(String name) {
		return ResourceKey.create(
			Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
	}
}
