package net.balamah.voiddim.data.loot;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resources.ResourceLocation;

public class ModLootTables {
	public static RegistryKey<LootTable> ILLUSIONER_CHURCH_CHEST_LOOT =
		register("chests/illusioner_church_chest_loot");

	public static RegistryKey<LootTable> ILLUSIONER_CHURCH_SECRET_CHEST_LOOT =
		register("chests/illusioner_church_secret_chest_loot");

	public static RegistryKey<LootTable> ILLUSIONER_HOUSE_ALCHEMY_LOOT =
		register("chests/illusioner_house_alchemy_loot");

	public static RegistryKey<LootTable> ILLUSIONER_HOUSE_SECRET_LOOT =
		register("chests/illusioner_house_secret_loot");

	public static RegistryKey<LootTable> ILLUSIONER_HOUSE_KITCHEN_LOOT =
		register("chests/illusioner_house_kitchen_loot");

	public static RegistryKey<LootTable> ILLUSIONER_HOUSE_WORM_ROOM_LOOT =
		register("chests/illusioner_house_worm_room_loot");

	public static RegistryKey<LootTable> ILLUSIONER_HOUSE_ALTAR_LOOT =
		register("chests/illusioner_house_altar_loot");

	public static RegistryKey<LootTable> ILLUSIONER_HOUSE_ILLUSIONER_ITEMS_LOOT =
		register("chests/illusioner_house_illusioner_items_loot");

	public static RegistryKey<LootTable> ABANDONED_CHEST_LOOT =
		register("chests/abandoned_chest_loot");

	public static RegistryKey<LootTable> ABANDONED_HOUSE_LOOT =
		register("chests/abandoned_house_loot");

	public static RegistryKey<LootTable> RUINED_HOUSE_LOOT =
		register("chests/ruined_house_loot");

	public static RegistryKey<LootTable> NORMAL_HOUSE_LOOT =
		register("chests/normal_house_loot");

	public static RegistryKey<LootTable> SKULL_TREE_LOOT =
		register("chests/skull_tree_loot");

	public static RegistryKey<LootTable> FAILED_RAILWAY_LOOT =
		register("chests/failed_railway_loot");

	public static RegistryKey<LootTable> CEMETERY_CHEST_LOOT =
		register("chests/cemetery_chest_loot");

	public static RegistryKey<LootTable> INFECTED_RUINED_HOUSE_LOOT =
		register("chests/infected_ruined_house_loot");

	public static RegistryKey<LootTable> INFECTED_TOWER_HOUSE_WEAPONS_LOOT =
		register("chests/infected_tower_house_weapons_loot");

	public static RegistryKey<LootTable> INFDEV_PYRAMID_REMAINS_LOOT =
		register("chests/infdev_pyramid_remains_loot");

	public static void registerModLootTables() {
		VoidDimension.LOGGER.info("Registering mod loot tables for " + VoidDimension.MOD_ID);
	}

	protected static RegistryKey<LootTable> register(String name) {
		return RegistryKey.of(
			RegistryKeys.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
	}
}
