package net.balamah.voiddim.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.VoidDimension;
import java.util.function.Supplier;

public class ModItemGroups {
	private static final ItemLike[] TAB_ITEMS = {
		ModItems.VOIDIUM, ModBlocks.DEEPSLATE_VOIDIUM_ORE, ModBlocks.CORRUPT_VOIDIUM_ORE,
		ModItems.VOID_SHARD, ModItems.VOID_INGOT,
		ModItems.VOID_UPGRADE_SMITHING_TEMPLATE, ModBlocks.DEEPSLATE_VOID_SHARD_ORE,
		ModBlocks.CORRUPT_VOID_SHARD_ORE, ModBlocks.VOID_FLOWER, ModItems.CORRUPTED_TORCH,
		ModBlocks.CORRUPTED_LANTERN, ModBlocks.CORRUPT_BLOCK, ModBlocks.VOID_SHARD_BLOCK,
		ModBlocks.WIND_MANIPULATION_BLOCK,
		ModItems.VOID_EXPLOSION_CORE, ModItems.VOID_EXPLOSION_UPGRADE, ModBlocks.BEDROCK_BOMB,
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
		ModItems.HOLLOWED_ALPHA_STEVE_SPAWN_EGG, ModItems.ZOMBIFIED_ALPHA_STEVE_SPAWN_EGG,
		ModItems.CORRUPTED_SPIDER_SPAWN_EGG, ModItems.NULL_SPAWN_EGG,
		ModItems.AGGRESSIVE_NULL_SPAWN_EGG, ModItems.STARING_DOG_SPAWN_EGG,
		ModItems.STARING_CAT_SPAWN_EGG, ModItems.ENTITY303_SPAWN_EGG,
		ModBlocks.OLD_CORPSE, ModBlocks.OLD_CORPSE_PILE
	};
	
	private static final CreativeModeTab VOID_DIMENSION_TAB =
		getCreativeTab(
			() -> new ItemStack(ModItems.VOID_SHARD),
			Component.translatable("itemgroup.void-dimension.items"), 
			TAB_ITEMS
		);

	public static final CreativeModeTab VOID_DIMENSION_ITEMS_GROUP =
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
						  Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_dimension_items"),
						  VOID_DIMENSION_TAB);

	public static void registerModItemGroups() {
		VoidDimension.LOGGER.info("Registering mod groups for " + VoidDimension.MOD_ID);
	}

	protected static CreativeModeTab getCreativeTab(
		Supplier<ItemStack> iconFactory, Component displayName, ItemLike[] items
	) {
		return FabricCreativeModeTab.builder()
			.icon(iconFactory)
			.title(displayName)
			.displayItems((displayParams, output) -> {
				for (ItemLike item : items) {
					output.accept(item);
				}
			})
			.build();
	}
}
