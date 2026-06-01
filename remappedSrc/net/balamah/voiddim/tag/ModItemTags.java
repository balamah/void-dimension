package net.balamah.voiddim.tag;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resources.ResourceLocation;

public class ModItemTags {
	public static final TagKey<Item> PRAYER_ITEMS = getTagKey("prayer_items");
	public static final TagKey<Item> PRAYER_ROPE_ITEMS = getTagKey("prayer_rope_items");
	public static final TagKey<Item> CROSS_ITEMS = getTagKey("cross_items");

	public static void registerModItemTags() {
		VoidDimension.LOGGER.info("Registering mod item tags for " + VoidDimension.MOD_ID);
	}

	protected static TagKey<Item> getTagKey(String name) {
		return TagKey.of(RegistryKeys.ITEM, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name));
	}
}
