package net.balamah.voiddim.block;

import com.google.common.base.Function;

import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.util.Identifier;
import net.minecraft.item.BlockItem;
import net.minecraft.block.MapColor;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import net.balamah.voiddim.block.custom.WindManipulationBlock;
import net.balamah.voiddim.block.custom.CorruptedFireBlock;
import net.balamah.voiddim.block.custom.BedrockBombBlock;
import net.balamah.voiddim.block.custom.CorruptOreBlock;
import net.balamah.voiddim.block.custom.CorruptBlock;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.block.custom.CorpseBlock;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.VoidDimension;

public class ModBlocks {
	protected static final AbstractBlock.Settings corpseBlockSettings =
		AbstractBlock.Settings.create()
		.strength(1.2f).sounds(BlockSoundGroup.BONE)
		.nonOpaque()
		;

	protected static final AbstractBlock.Settings flowerBlockSettings =
		AbstractBlock.Settings.create()
		.mapColor(MapColor.PALE_GREEN)
		.noCollision()
		.breakInstantly()
		.sounds(BlockSoundGroup.GRASS)
		.offset(AbstractBlock.OffsetType.XZ)
		.pistonBehavior(PistonBehavior.DESTROY)
		.nonOpaque();

	protected static final IntProvider deepslateVoidShardOreIntProvider =
		UniformIntProvider.create(4, 7);

	protected static final int corruptedFlowerLuminance = 3;

	public static final Block DEEPSLATE_VOIDIUM_ORE =
		register(
			"deepslate_voidium_ore",
			settings -> new ExperienceDroppingBlock(
				deepslateVoidShardOreIntProvider, settings
			),
			AbstractBlock.Settings.create().strength(3.5F, 3.0F).requiresTool()
			.sounds(BlockSoundGroup.DEEPSLATE),
			true, false
		);

	public static final Block CORRUPT_VOIDIUM_ORE =
		register(
			"corrupt_voidium_ore",
			settings -> new CorruptOreBlock(
				deepslateVoidShardOreIntProvider, settings
			),
			AbstractBlock.Settings.create().strength(3.5F, 3.0F).requiresTool()
			.sounds(BlockSoundGroup.DEEPSLATE),
			true, false
		);

	public static final Block DEEPSLATE_VOID_SHARD_ORE =
		register(
			"deepslate_void_shard_ore",
			settings -> new ExperienceDroppingBlock(
				deepslateVoidShardOreIntProvider, settings
			),
			AbstractBlock.Settings.create().strength(4.5F, 3.0F).requiresTool()
			.sounds(BlockSoundGroup.DEEPSLATE),
			true, false
		);

	public static final Block CORRUPT_VOID_SHARD_ORE =
		register(
			"corrupt_void_shard_ore",
			settings -> new CorruptOreBlock(
				deepslateVoidShardOreIntProvider, settings
			),
			AbstractBlock.Settings.create().strength(4.5F, 3.0F).requiresTool()
			.sounds(BlockSoundGroup.SCULK),
			true, false
		);

	public static final Block VOID_SHARD_BLOCK =
		register(
			"void_shard_block",
			Block::new,
			AbstractBlock.Settings.create().requiresTool().strength(50.0F, 1200.0F)
			.sounds(BlockSoundGroup.NETHERITE),
			true, false
		);

	public static final Block VOID_FLOWER =
		register(
			"void_flower",
			settings -> new FlowerBlock(ModEffects.CORRUPTION, 3.0F, settings),
			flowerBlockSettings.luminance(state -> corruptedFlowerLuminance),
			true, false
		);

	public static final Block POTTED_VOID_FLOWER =
		register(
			"potted_void_flower",
			settings -> new FlowerPotBlock(VOID_FLOWER, settings),
			Blocks.createFlowerPotSettings()
			.luminance(state -> corruptedFlowerLuminance),
			false, false
		);

	public static final Block CORRUPTED_TORCH =
		register(
			"corrupted_torch",
			settings -> new TorchBlock(ModParticleTypes.CORRUPTED_FLAME, settings),
			AbstractBlock.Settings.create().noCollision().breakInstantly()
			.luminance(state -> 14).sounds(BlockSoundGroup.WOOD)
			.pistonBehavior(PistonBehavior.DESTROY),
			false, false
		);

	public static final Block CORRUPTED_WALL_TORCH =
		register(
			"corrupted_wall_torch",
			settings -> new WallTorchBlock(
				ModParticleTypes.CORRUPTED_FLAME, settings
			),
			copyLootTable(CORRUPTED_TORCH, true).noCollision().breakInstantly()
			.luminance(state -> 14).sounds(BlockSoundGroup.WOOD)
			.pistonBehavior(PistonBehavior.DESTROY),
			false, false
		);

	public static final Block CORRUPTED_LANTERN =
		register(
			"corrupted_lantern",
			LanternBlock::new,
			AbstractBlock.Settings.create()
				.mapColor(MapColor.IRON_GRAY)
				.solid()
				.strength(1.5F)
				.sounds(BlockSoundGroup.LANTERN)
				.luminance(state -> 15)
				.nonOpaque()
				.pistonBehavior(PistonBehavior.DESTROY),
			true, false
		);

	public static final Block CORRUPTED_FIRE =
		register(
			"corrupted_fire",
			CorruptedFireBlock::new,
			AbstractBlock.Settings.create().mapColor(MapColor.BRIGHT_RED)
			.replaceable().noCollision().breakInstantly()
			.luminance(state -> 15).sounds(BlockSoundGroup.WOOL)
			.pistonBehavior(PistonBehavior.DESTROY)
			.nonOpaque(),
			false, false
			);

	public static final Block WIND_MANIPULATION_BLOCK =
		register(
			"wind_manipulation_block",
			WindManipulationBlock::new,
			AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)
			.requiresTool().strength(2.6F, 4.7F),
			true, true
			);

	public static final Block CORRUPT_BLOCK =
		register(
			"corrupt_block",
			CorruptBlock::new,
			AbstractBlock.Settings.create().sounds(BlockSoundGroup.SCULK)
			.requiresTool().strength(2.6F, 4.7F),
			true, false
			);

	public static final Block BEDROCK_BOMB =
		register(
			"bedrock_bomb",
			BedrockBombBlock::new,
			AbstractBlock.Settings.create()
			.mapColor(MapColor.GRAY).breakInstantly()
			.sounds(BlockSoundGroup.GRASS),
			true, false
		);

	public static final Block OLD_CORPSE =
		register("old_corpse", CorpseBlock::new, corpseBlockSettings, true, false);

	public static final Block OLD_CORPSE_PILE =
		register("old_corpse_pile", CorpseBlock::new, corpseBlockSettings, true, false);

	public static void registerModBlocks() {
		VoidDimension.LOGGER.info("Registering mod blocks for " + VoidDimension.MOD_ID);
	}

	protected static Block register(
		String name, Function<AbstractBlock.Settings, Block> blockFactory,
		AbstractBlock.Settings settings, boolean shouldRegisterItem,
		boolean isItemUnstackable
	) {
		RegistryKey<Block> blockKey = keyOfBlock(name);
		Block block = blockFactory.apply(settings.registryKey(blockKey));

		if (shouldRegisterItem) {
			registerItem(name, block, isItemUnstackable);
		}

		return Registry.register(Registries.BLOCK, blockKey, block);
	}

	protected static void registerItem(
		String name, Block block, boolean isItemUnstackable
	) {
		RegistryKey<Item> itemKey = keyOfItem(name);

		Item.Settings settings = new Item.Settings();

		if (isItemUnstackable) {
			settings = settings.maxCount(1);
		}

		BlockItem blockItem = new BlockItem(block, settings.registryKey(itemKey));

		Registry.register(Registries.ITEM, itemKey, blockItem);
	}

	protected static AbstractBlock.Settings copyLootTable(
		Block block, boolean copyTranslationKey
	) {
		/*
		 * NOTE: Don't touch this.
		 * This piece was borrowed from minecraft's code
		 */
		AbstractBlock.Settings settings = block.getSettings();
		AbstractBlock.Settings settings2 = AbstractBlock.Settings.create()
			.lootTable(block.getLootTableKey());

		if (copyTranslationKey) {
			settings2 = settings2.overrideTranslationKey(block.getTranslationKey());
		}

		return settings2;
	}

	protected static RegistryKey<Block> keyOfBlock(String name) {
		return RegistryKey.of(
			RegistryKeys.BLOCK, Identifier.of(VoidDimension.MOD_ID, name)
		);
	}
	
	protected static RegistryKey<Item> keyOfItem(String name) {
		return RegistryKey.of(
			RegistryKeys.ITEM, Identifier.of(VoidDimension.MOD_ID, name)
		);
	}
}
