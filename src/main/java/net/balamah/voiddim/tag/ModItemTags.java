package net.balamah.voiddim.tag;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
	public static final TagKey<Item> PRAYER_ITEMS = getTagKey("prayer_items");
	public static final TagKey<Item> PRAYER_ROPE_ITEMS = getTagKey("prayer_rope_items");
	public static final TagKey<Item> CROSS_ITEMS = getTagKey("cross_items");

	public static void registerModItemTags() {
		VoidDimension.LOGGER.info("Registering mod item tags for " + VoidDimension.MOD_ID);
	}

	protected static TagKey<Item> getTagKey(String name) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name));
	}
}
