package net.balamah.voiddim.tag;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

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
		return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, id));
	}
}
