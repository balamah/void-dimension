package net.balamah.voiddim.block;

import com.google.common.base.Function;
import net.balamah.voiddim.block.custom.WindManipulationBlock;
import net.balamah.voiddim.block.custom.CorruptedFireBlock;
import net.balamah.voiddim.block.custom.CorruptionAscensionBlock;
import net.balamah.voiddim.block.custom.BedrockBombBlock;
import net.balamah.voiddim.block.custom.CorruptOreBlock;
import net.balamah.voiddim.block.custom.CorruptBlock;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.balamah.voiddim.block.custom.CorpseBlock;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.VoidDimension;

public class ModBlocks {
	protected static BlockBehaviour.Properties corpseBlockSettings = BlockBehaviour.Properties.of()
			.strength(1.2f)
			.sound(SoundType.BONE_BLOCK)
			.noOcclusion();

	protected static BlockBehaviour.Properties flowerBlockSettings = BlockBehaviour.Properties.of()
			.mapColor(MapColor.GRASS)
			.noCollision()
			.instabreak()
			.sound(SoundType.GRASS)
			.offsetType(BlockBehaviour.OffsetType.XZ)
			.pushReaction(PushReaction.DESTROY)
			.noOcclusion();

	protected static final IntProvider deepslateVoidShardOreIntProvider =
		UniformInt.of(4, 7);

	protected static final int corruptedFlowerLuminance = 3;

	public static final Block VOIDIUM_ORE =
		register(
			"voidium_ore",
			settings -> new DropExperienceBlock(UniformInt.of(2, 3), settings),
			BlockBehaviour.Properties.of().strength(3.0F, 3.0F).requiresCorrectToolForDrops()
			.sound(SoundType.DEEPSLATE),
			true, false
		);

	public static final Block DEEPSLATE_VOIDIUM_ORE =
		register(
			"deepslate_voidium_ore",
			settings -> new DropExperienceBlock(
				deepslateVoidShardOreIntProvider, settings
			),
			BlockBehaviour.Properties.of().strength(3.5F, 3.0F).requiresCorrectToolForDrops()
			.sound(SoundType.DEEPSLATE),
			true, false
		);

	public static final Block CORRUPT_VOIDIUM_ORE =
		register(
			"corrupt_voidium_ore",
			settings -> new CorruptOreBlock(
				deepslateVoidShardOreIntProvider, settings
			),
			BlockBehaviour.Properties.of().strength(3.5F, 3.0F).requiresCorrectToolForDrops()
			.sound(SoundType.DEEPSLATE),
			true, false
		);

	public static final Block DEEPSLATE_VOID_SHARD_ORE =
		register(
			"deepslate_void_shard_ore",
			settings -> new DropExperienceBlock(
				deepslateVoidShardOreIntProvider, settings
			),
			BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()
			.sound(SoundType.DEEPSLATE),
			true, false
		);

	public static final Block CORRUPT_VOID_SHARD_ORE =
		register(
			"corrupt_void_shard_ore",
			settings -> new CorruptOreBlock(
				deepslateVoidShardOreIntProvider, settings
			),
			BlockBehaviour.Properties.of().strength(4.5F, 3.0F).requiresCorrectToolForDrops()
			.sound(SoundType.SCULK),
			true, false
		);

	public static final Block VOID_SHARD_BLOCK =
		register(
			"void_shard_block",
			Block::new,
			BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(50.0F, 1200.0F)
			.sound(SoundType.NETHERITE_BLOCK),
			true, false
		);

	public static final Block VOID_FLOWER =
		register(
			"void_flower",
			settings -> new FlowerBlock(ModEffects.CORRUPTION, 3.0F, settings),
			flowerBlockSettings.lightLevel(state -> corruptedFlowerLuminance),
			true, false
		);

	public static final Block POTTED_VOID_FLOWER =
		register(
			"potted_void_flower",
			settings -> new FlowerPotBlock(VOID_FLOWER, settings),
			Blocks.flowerPotProperties()
			.lightLevel(state -> corruptedFlowerLuminance),
			false, false
		);

	public static final Block CORRUPTED_TORCH =
		register(
			"corrupted_torch",
			settings -> new TorchBlock(ModParticleTypes.CORRUPTED_FLAME, settings),
			BlockBehaviour.Properties.of().noCollision().instabreak()
			.lightLevel(state -> 14).sound(SoundType.WOOD)
			.pushReaction(PushReaction.DESTROY),
			false, false
		);

	public static final Block CORRUPTED_WALL_TORCH =
		register(
			"corrupted_wall_torch",
			settings -> new WallTorchBlock(
				ModParticleTypes.CORRUPTED_FLAME, settings
			),
			copyLootTable(CORRUPTED_TORCH, true).noCollision().instabreak()
			.lightLevel(state -> 14).sound(SoundType.WOOD)
			.pushReaction(PushReaction.DESTROY),
			false, false
		);

	public static final Block CORRUPTED_LANTERN =
		register(
			"corrupted_lantern",
			LanternBlock::new,
			BlockBehaviour.Properties.of()
				.mapColor(MapColor.METAL)
				.forceSolidOn()
				.strength(1.5F)
				.sound(SoundType.LANTERN)
				.lightLevel(state -> 15)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY),
			true, false
		);

	public static final Block CORRUPTED_FIRE =
		register(
			"corrupted_fire",
			CorruptedFireBlock::new,
			BlockBehaviour.Properties.of().mapColor(MapColor.FIRE)
			.replaceable().noCollision().instabreak()
			.lightLevel(state -> 15).sound(SoundType.WOOL)
			.pushReaction(PushReaction.DESTROY)
			.noOcclusion(),
			false, false
			);

	public static final Block WIND_MANIPULATION_BLOCK =
		register(
			"wind_manipulation_block",
			WindManipulationBlock::new,
			BlockBehaviour.Properties.of().sound(SoundType.STONE)
			.requiresCorrectToolForDrops().strength(2.6F, 4.7F),
			true, true
			);

	public static final Block CORRUPT_BLOCK =
		register(
			"corrupt_block",
			CorruptBlock::new,
			BlockBehaviour.Properties.of().sound(SoundType.SCULK)
			.requiresCorrectToolForDrops().strength(2.6F, 4.7F),
			true, false
			);

	public static final Block CORRUPTION_ASCENSION_BLOCK =
		register(
			"corruption_ascension_block",
			CorruptionAscensionBlock::new,
			BlockBehaviour.Properties.of().sound(SoundType.SCULK)
			.requiresCorrectToolForDrops().strength(2.6F, 4.7F),
			true, false
		);

	public static final Block BEDROCK_BOMB =
		register(
			"bedrock_bomb",
			BedrockBombBlock::new,
			BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_GRAY).instabreak()
			.sound(SoundType.GRASS),
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
		String name, Function<BlockBehaviour.Properties, Block> blockFactory,
		BlockBehaviour.Properties settings, boolean shouldRegisterItem,
		boolean isItemUnstackable
	) {
		ResourceKey<Block> blockKey = keyOfBlock(name);
		Block block = blockFactory.apply(settings.setId(blockKey));

		if (shouldRegisterItem) {
			registerItem(name, block, isItemUnstackable);
		}

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	protected static void registerItem(
		String name, Block block, boolean isItemUnstackable
	) {
		ResourceKey<Item> itemKey = keyOfItem(name);

		Item.Properties settings = new Item.Properties();

		if (isItemUnstackable) {
			settings = settings.stacksTo(1);
		}

		BlockItem blockItem = new BlockItem(block, settings.setId(itemKey));

		Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
	}

	@SuppressWarnings("unused")
	protected static BlockBehaviour.Properties copyLootTable(
		Block block, boolean copyTranslationKey
	) {
		/*
		 * NOTE: Don't touch this.
		 * This piece was borrowed from minecraft's code
		 */
		BlockBehaviour.Properties settings = block.properties();
		BlockBehaviour.Properties settings2 = BlockBehaviour.Properties.of()
			.overrideLootTable(block.getLootTable());

		if (copyTranslationKey) {
			settings2 = settings2.overrideDescription(block.getDescriptionId());
		}

		return settings2;
	}

	protected static ResourceKey<Block> keyOfBlock(String name) {
		return ResourceKey.create(
			Registries.BLOCK, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
	}
	
	protected static ResourceKey<Item> keyOfItem(String name) {
		return ResourceKey.create(
			Registries.ITEM, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);
	}
}
