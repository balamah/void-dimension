package net.balamah.voiddim.tag;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resources.ResourceLocation;

public class ModBlockTags {
	public static final TagKey<Block> INCORRECT_FOR_VOID_TOOL =
		register("incorrect_for_void_tool");

	public static final TagKey<Block> CORRUPTED_FIRE_BASE_BLOCKS =
		register("corrupted_fire_base_blocks");

	public static final TagKey<Block> CORRUPT_ORE_REPLACEABLES =
		register("corrupt_ore_replaceables");

	public static void registerModBlockTags() {
		VoidDimension.LOGGER.info("Registering mod block tags for " + VoidDimension.MOD_ID);
	}

	protected static TagKey<Block> register(String id) {
		return TagKey.of(RegistryKeys.BLOCK, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, id));
	}
}
