package net.balamah.voiddim.item;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.balamah.voiddim.item.custom.CorruptedFireChargeItem;
import net.balamah.voiddim.item.custom.ModSmithingTemplateItem;
import net.balamah.voiddim.material.armor.VoidArmorMaterial;
import net.balamah.voiddim.item.custom.PrayerItem;
import net.balamah.voiddim.material.ModMaterials;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.sound.ModSounds;
import net.balamah.voiddim.VoidDimension;

public class ModItems {
	public static final String MOD_ID = VoidDimension.MOD_ID;

	protected static Item.Properties prayerItemSettings() {
		return new Item.Properties().stacksTo(1);
	}

	protected static final Function<Item.Properties, Item> crossItemSettings =
		settings -> new PrayerItem(settings, 60, 900, 3);

	protected static final Function<Item.Properties, Item> prayerRopeSettings =
		settings -> new PrayerItem(settings, 240, 7200, 3);

	public static final Item VOIDIUM =
		register("voidium", Item::new, ModItems::getVoidItemSettings);

	public static final Item VOID_SHARD = register(
		"void_shard", Item::new, ModItems::getVoidItemSettings
	);

	public static final Item VOID_INGOT = register(
		"void_ingot", Item::new, ModItems::getVoidItemSettings
	);

	public static final Item RAW_FLESH =
		registerFoodItem("raw_flesh", ModFoodComponents.RAW_FLESH);

	public static final Item COOKED_FLESH =
		registerFoodItem("cooked_flesh", ModFoodComponents.COOKED_FLESH);

	public static final Item SPOILED_FLESH =
		registerFoodItem(
			"spoiled_flesh",
			ModFoodComponents.SPOILED_FLESH,
			Consumables.ROTTEN_FLESH
		);

	// This sux
	public static final Item VOID_UPGRADE_SMITHING_TEMPLATE =
		register(
			"void_upgrade_smithing_template",
			ModSmithingTemplateItem::createVoidUpgrade,
			ModItems::getVoidItemSettings
		);

	public static final Item VOID_AXE =
		register(
			"void_axe",
			settings -> new AxeItem(
				ModMaterials.VOID_TOOL_MATERIAL, 8.0F, -3.0F, settings
			),
			ModItems::getVoidItemSettings
		);

	public static final Item VOID_HOE =
		register(
			"void_hoe",
			settings -> new HoeItem(
				ModMaterials.VOID_TOOL_MATERIAL, -5F, 0.0F, settings
			),
			ModItems::getVoidItemSettings
		);

	public static final Item VOID_PICKAXE =
		register(
			"void_pickaxe",
			Item::new,
			() -> getVoidItemSettings()
				.pickaxe(ModMaterials.VOID_TOOL_MATERIAL, 2.0f, -2.8f)
		);

	public static final Item VOID_SHOVEL =
		register(
			"void_shovel",
			settings -> new ShovelItem(
				ModMaterials.VOID_TOOL_MATERIAL, 1.5F, -3.0F, settings
			),
			ModItems::getVoidItemSettings
		);

	public static final Item VOID_SWORD =
		register(
			"void_sword",
			Item::new,
			() -> getVoidItemSettings()
				.sword(ModMaterials.VOID_TOOL_MATERIAL, 6.5f, -2.4f)
		);

	public static final Item VOID_SPEAR =
		register(
			"void_spear",
			Item::new,
			() -> getVoidItemSettings().spear(
					ModMaterials.VOID_TOOL_MATERIAL,
					1.0F, 1.075F, 0.4F,
					2.5F, 6.5F, 5.5F, 5.1F, 7.75F, 4.6F
				)
		);

	public static final Item VOID_HELMET =
		registerArmor("void_helmet", VoidArmorMaterial.INSTANCE, ArmorType.HELMET, true);

	public static final Item VOID_CHESTPLATE =
		registerArmor(
			"void_chestplate", VoidArmorMaterial.INSTANCE, ArmorType.CHESTPLATE, true
		);

	public static final Item VOID_LEGGINGS =
		registerArmor("void_leggings", VoidArmorMaterial.INSTANCE, ArmorType.LEGGINGS, true);

	public static final Item VOID_BOOTS =
		registerArmor(
			"void_boots", VoidArmorMaterial.INSTANCE, ArmorType.BOOTS, true
		);

	public static final Item LATIN_CROSS =
		register("latin_cross", crossItemSettings, ModItems::prayerItemSettings);

	public static final Item ORTHODOX_CROSS =
		register("orthodox_cross", crossItemSettings, ModItems::prayerItemSettings);

	public static final Item WOOL_PRAYER_ROPE =
		register("wool_prayer_rope", prayerRopeSettings, ModItems::prayerItemSettings);

	public static final Item WOOD_PRAYER_ROPE =
		register("wooden_prayer_rope", prayerRopeSettings, ModItems::prayerItemSettings);

	public static final Item SKULL_PRAYER_ROPE =
		register("skull_prayer_rope", prayerRopeSettings, ModItems::prayerItemSettings);

	public static final Item CORRUPTED_TORCH =
		registerBlockItem(
			ModBlocks.CORRUPTED_TORCH,
			(BiFunction<Block, Item.Properties, Item>)(
				(block, settings) -> new StandingAndWallBlockItem(
					block, ModBlocks.CORRUPTED_WALL_TORCH, Direction.DOWN, settings
				)),
			Item.Properties::new
		);

	public static final Item CORRUPTED_FIRE_CHARGE =
		register("corrupted_fire_charge", CorruptedFireChargeItem::new, Item.Properties::new);

	public static final Item CORRUPTED_CREEPER_SPAWN_EGG =
		registerSpawnEgg("corrupted_creeper_spawn_egg", ModEntities.CORRUPTED_CREEPER);

	public static final Item CORRUPTED_SPIDER_SPAWN_EGG =
		registerSpawnEgg("corrupted_spider_spawn_egg", ModEntities.CORRUPTED_SPIDER);

	public static final Item CORRUPTED_BLAZE_SPAWN_EGG =
		registerSpawnEgg("corrupted_blaze_spawn_egg", ModEntities.CORRUPTED_BLAZE);

	public static final Item CORRUPTED_STALKER_SPAWN_EGG =
		registerSpawnEgg("corrupted_stalker_spawn_egg", ModEntities.CORRUPTED_STALKER);

	public static final Item VOID_MAW_SPAWN_EGG =
		registerSpawnEgg("void_maw_spawn_egg", ModEntities.VOID_MAW);
	
	public static final Item VOID_HARBINGER_SPAWN_EGG =
		registerSpawnEgg("void_harbinger_spawn_egg", ModEntities.VOID_HARBINGER);
	
	public static final Item WORM_OF_CORRUPTION_SPAWN_EGG =
		registerSpawnEgg("worm_of_corruption_spawn_egg", ModEntities.WORM_OF_CORRUPTION);
	
	public static final Item SHATTERED_SENTINEL_SPAWN_EGG =
		registerSpawnEgg("shattered_sentinel_spawn_egg", ModEntities.SHATTERED_SENTINEL);
	
	public static final Item SHATTERED_SENTINEL_MASTER_SPAWN_EGG =
		registerSpawnEgg(
			"shattered_sentinel_master_spawn_egg", ModEntities.SHATTERED_SENTINEL_MASTER
		);

	public static final Item WEREWOLF_SPAWN_EGG =
		registerSpawnEgg("werewolf_spawn_egg", ModEntities.WEREWOLF);

	public static final Item HOLLOWED_BEAST_SPAWN_EGG =
		registerSpawnEgg("hollowed_beast_spawn_egg", ModEntities.HOLLOWED_BEAST);

	public static final Item ILLUSIONER_SPAWN_EGG =
		registerSpawnEgg("illusioner_spawn_egg", EntityType.ILLUSIONER);

	public static final Item NULL_SPAWN_EGG =
		registerSpawnEgg("null_spawn_egg", ModEntities.NULL);

	public static final Item AGGRESSIVE_NULL_SPAWN_EGG =
		registerSpawnEgg("aggressive_null_spawn_egg", ModEntities.AGGRESSIVE_NULL);

	public static final Item STARING_DOG_SPAWN_EGG =
		registerSpawnEgg("staring_dog_spawn_egg", ModEntities.STARING_DOG);

	public static final Item STARING_CAT_SPAWN_EGG =
		registerSpawnEgg("staring_cat_spawn_egg", ModEntities.STARING_CAT);

	public static final Item ENTITY303_SPAWN_EGG =
		registerSpawnEgg("entity303_spawn_egg", ModEntities.ENTITY303);

	public static final Item EYE_BRIGHT_SPAWN_EGG =
		registerSpawnEgg("eye_bright_spawn_egg", ModEntities.EYE_BRIGHT);

	public static final Item HOLLOWED_ALPHA_STEVE_SPAWN_EGG =
		registerSpawnEgg("hollowed_alpha_steve_spawn_egg", ModEntities.HOLLOWED_ALPHA_STEVE);

	public static final Item ZOMBIFIED_ALPHA_STEVE_SPAWN_EGG =
		registerSpawnEgg("zombified_alpha_steve_spawn_egg", ModEntities.ZOMBIFIED_ALPHA_STEVE);

	public static final Item VOID_HORSE_ARMOR =
		register(
			"void_horse_armor",
			Item::new,
			() -> getVoidItemSettings().horseArmor(VoidArmorMaterial.INSTANCE)
		);

	public static final Item VOID_NAUTILUS_ARMOR =
		register(
			"void_nautilus_armor",
			Item::new,
			() -> getVoidItemSettings().nautilusArmor(VoidArmorMaterial.INSTANCE)
		);

	public static final Item MUSIC_DISC_CALM4 =
		register(
			"music_disc_calm4",
			Item::new,
			() -> new Item.Properties()
				.rarity(Rarity.UNCOMMON)
				.jukeboxPlayable(ModSounds.MUSIC_CALM4_KEY)
				.stacksTo(1)
		);

	public static void registerModItems() {
		VoidDimension.LOGGER.info("Registering mod items for " + MOD_ID);
	}

	protected static Item register(
		String name,
		Function<Item.Properties, Item> itemFactory,
		Supplier<Item.Properties> settingsFactory
	) {
		ResourceKey<Item> itemKey = ResourceKey.create(
			Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, name)
		);

		Item.Properties settings = settingsFactory.get().setId(itemKey);
		Item item = itemFactory.apply(settings);
		return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
	}

	@SuppressWarnings("deprecation")
	protected static Item registerBlockItem(
		Block block,
		BiFunction<Block, Item.Properties, Item> factory,
		Supplier<Item.Properties> settingsFactory
	) {
		ResourceKey<Block> blockKey =
			BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();

		return registerByKey(
			keyOfBlock(blockKey),
			itemSettings -> (Item) factory.apply(block, itemSettings),
			settingsFactory
		);
	}

	protected static Item registerByKey(
		ResourceKey<Item> key,
		Function<Item.Properties, Item> factory,
		Supplier<Item.Properties> settingsFactory
	) {
		Item.Properties settings = settingsFactory.get().setId(key);
		Item item = (Item)factory.apply(settings);
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}

		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	protected static ResourceKey<Item> keyOfBlock(ResourceKey<Block> blockKey) {
		return ResourceKey.create(Registries.ITEM, blockKey.identifier());
	}

	protected static Item registerArmor(
		String name, ArmorMaterial material, ArmorType type, boolean isVoid
	) {
		int durability = type.getDurability(material.durability());
		return register(
			name,
			Item::new,
			() -> (isVoid ? getVoidItemSettings() : new Item.Properties())
				.durability(durability)
				.humanoidArmor(material, type)
		);
	}

	protected static Item registerSpawnEgg(String name, EntityType<? extends Mob> mob) {
		return register(name, SpawnEggItem::new, () -> new Item.Properties().spawnEgg(mob));
	}

	protected static Item registerFoodItem(String name, FoodProperties foodComponent) {
		return register(name, Item::new, () -> new Item.Properties().food(foodComponent));
	}

	protected static Item registerFoodItem(
		String name, FoodProperties foodComponent, Consumable consumableComponent
	) {
		return register(
			name,
			Item::new,
			() -> new Item.Properties().food(foodComponent, consumableComponent)
		);
	}

	protected static Item registerMusicDisc(String name, ResourceKey<JukeboxSong> song) {
		return register(
			name,
			Item::new,
			() -> new Item.Properties()
				.stacksTo(1)
				.rarity(Rarity.UNCOMMON)
				.jukeboxPlayable(song)
		);
	}

	protected static Item.Properties getVoidItemSettings() {
		return new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant();
	}
}
