package net.balamah.voiddim.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.VoidDimension;

public class ModItemGroups {
	private static final ItemConvertible[] TAB_ITEMS = {
		ModItems.VOIDIUM, ModBlocks.DEEPSLATE_VOIDIUM_ORE, ModBlocks.CORRUPT_VOIDIUM_ORE,
		ModItems.VOID_SHARD, ModItems.VOID_INGOT,
		ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DEEPSLATE_VOID_SHARD_ORE,
		ModBlocks.CORRUPT_VOID_SHARD_ORE, ModBlocks.VOID_FLOWER, ModItems.CORRUPTED_TORCH,
		ModBlocks.CORRUPTED_LANTERN, ModBlocks.CORRUPT_BLOCK, ModBlocks.VOID_SHARD_BLOCK,
		ModBlocks.WIND_MANIPULATION_BLOCK, ModBlocks.BEDROCK_BOMB,
		ModItems.VOID_AXE, ModItems.VOID_SWORD, ModItems.VOID_SPEAR,
		ModItems.VOID_PICKAXE, ModItems.VOID_SHOVEL,
		ModItems.VOID_HOE, ModItems.VOID_HELMET, ModItems.VOID_CHESTPLATE,
		ModItems.VOID_LEGGINGS, ModItems.VOID_BOOTS,
		ModItems.VOID_HORSE_ARMOR, ModItems.VOID_NAUTILUS_ARMOR,
		ModItems.CORRUPTED_FIRE_CHARGE,
		ModItems.LATIN_CROSS, ModItems.ORTHODOX_CROSS, ModItems.WOOL_PRAYER_ROPE,
		ModItems.WOOD_PRAYER_ROPE, ModItems.SKULL_PRAYER_ROPE,
		ModItems.ILLUSIONER_SPAWN_EGG,
		ModItems.WEREWOLF_SPAWN_EGG, ModItems.HOLLOWED_BEAST_SPAWN_EGG,
		ModItems.CORRUPTED_STALKER_SPAWN_EGG, ModItems.VOID_MAW_SPAWN_EGG,
		ModItems.WORM_OF_CORRUPTION_SPAWN_EGG, ModItems.SHATTERED_SENTINEL_SPAWN_EGG,
		ModItems.VOID_HARBINGER_SPAWN_EGG, ModItems.SHATTERED_SENTINEL_MASTER_SPAWN_EGG,
		ModItems.CORRUPTED_CREEPER_SPAWN_EGG, ModItems.CORRUPTED_BLAZE_SPAWN_EGG,
		ModItems.CORRUPTED_SPIDER_SPAWN_EGG,
		ModBlocks.OLD_CORPSE, ModBlocks.OLD_CORPSE_PILE
	};
	
	private static final ItemGroup VOID_DIMENSION_TAB =
		getCreativeTab(
			new ItemStack(ModItems.VOID_SHARD), 
			Text.translatable("itemgroup.void-dimension.items"), 
			TAB_ITEMS
		);

	public static final ItemGroup VOID_DIMENSION_ITEMS_GROUP =
		Registry.register(Registries.ITEM_GROUP,
						  Identifier.of(VoidDimension.MOD_ID, "void_dimension_items"),
						  VOID_DIMENSION_TAB);

	public static void registerModItemGroups() {
		VoidDimension.LOGGER.info("Registering mod groups for " + VoidDimension.MOD_ID);
	}

	protected static ItemGroup getCreativeTab(
		ItemStack icon, Text displayName, ItemConvertible[] items
	) {
		return FabricItemGroup.builder().icon(() -> icon)
			.displayName(displayName)
			.entries(((displayContext, entries) -> {
						for (ItemConvertible item : items) {
							entries.add(item);	
						}
					}))
			.build();
	}
}
