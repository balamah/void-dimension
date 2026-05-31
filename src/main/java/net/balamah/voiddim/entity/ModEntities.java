package net.balamah.voiddim.entity;

import net.balamah.voiddim.entity.custom.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.balamah.voiddim.VoidDimension;

public class ModEntities {
	public static final EntityType<MantisEntity> MANTIS =
		register(
			"mantis",
			MantisEntity.class,
			EntityType.Builder.of(MantisEntity::new, MobCategory.CREATURE)
			.sized(1f, 2.5f)
		);

	public static final EntityType<SnowmanEntity> SNOWMAN =
		register(
			"snowman",
			SnowmanEntity.class,
			EntityType.Builder.of(SnowmanEntity::new, MobCategory.CREATURE)
			.sized(0.6f, 1.95f)
		);

	public static final EntityType<CorruptedStalkerEntity> CORRUPTED_STALKER =
		register(
			"corrupted_stalker",
			CorruptedStalkerEntity.class,
			EntityType.Builder.of(CorruptedStalkerEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 2.9F)
		);

	public static final EntityType<ShatteredSentinelEntity> SHATTERED_SENTINEL =
		register(
			"shattered_sentinel",
			ShatteredSentinelEntity.class,
			EntityType.Builder.of(ShatteredSentinelEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 1.95f)
		);

	public static final EntityType<WerewolfEntity> WEREWOLF =
		register(
			"werewolf",
			WerewolfEntity.class,
			EntityType.Builder.of(WerewolfEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 1.95f)
		);

	public static final EntityType<HollowedBeastEntity> HOLLOWED_BEAST =
		register(
			"hollowed_beast",
			HollowedBeastEntity.class,
			EntityType.Builder.of(HollowedBeastEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 1.95f)
		);

	public static final EntityType<ShatteredSentinelMasterEntity> SHATTERED_SENTINEL_MASTER =
		register(
			"shattered_sentinel_master",
			ShatteredSentinelMasterEntity.class,
			EntityType.Builder.of(ShatteredSentinelMasterEntity::new, MobCategory.CREATURE)
			.sized(1F, 2.8f)
		);

	public static final EntityType<VoidMawEntity> VOID_MAW =
		register(
			"void_maw",
			VoidMawEntity.class,
			EntityType.Builder.of(VoidMawEntity::new, MobCategory.CREATURE)
			.sized(0.8F, 0.8F)
		);

	public static final EntityType<VoidHarbingerEntity> VOID_HARBINGER =
		register(
			"void_harbinger",
			VoidHarbingerEntity.class,
			EntityType.Builder.of(VoidHarbingerEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 3.0F)
		);

	public static final EntityType<WormOfCorruptionEntity> WORM_OF_CORRUPTION =
		register(
			"worm_of_corruption",
			WormOfCorruptionEntity.class,
			EntityType.Builder.of(WormOfCorruptionEntity::new, MobCategory.CREATURE)
			.sized(1.2F, 2.5f)
		);

	public static final EntityType<HollowedAlphaSteveEntity> HOLLOWED_ALPHA_STEVE =
		register(
			"hollowed_alpha_steve",
			HollowedAlphaSteveEntity.class,
			EntityType.Builder.<HollowedAlphaSteveEntity>of(HollowedAlphaSteveEntity::new, MobCategory.CREATURE)
			.sized(0.6f, 2f)
		);

	public static final EntityType<ZombifiedAlphaSteveEntity> ZOMBIFIED_ALPHA_STEVE =
		register(
			"zombified_alpha_steve",
			ZombifiedAlphaSteveEntity.class,
			EntityType.Builder.<ZombifiedAlphaSteveEntity>of(ZombifiedAlphaSteveEntity::new, MobCategory.CREATURE)
			.sized(0.6f, 2f)
		);

	public static final EntityType<NullEntity> NULL =
		register(
			"null",
			NullEntity.class,
			EntityType.Builder.<NullEntity>of(NullEntity::new, MobCategory.CREATURE)
			.sized(0.6f, 2f)
		);

	public static final EntityType<AggressiveNullEntity> AGGRESSIVE_NULL =
		register(
			"aggressive_null",
			AggressiveNullEntity.class,
			EntityType.Builder.<AggressiveNullEntity>of(AggressiveNullEntity::new, MobCategory.CREATURE)
			.sized(0.6f, 2f)
		);

	public static final EntityType<Entity303> ENTITY303 =
		register(
			"entity303",
			Entity303.class,
			EntityType.Builder.<Entity303>of(Entity303::new, MobCategory.CREATURE)
			.sized(0.6f, 2f)
		);

	public static final EntityType<CorruptedPlayerEntity> CORRUPTED_PLAYER =
		register(
			"corrupted_player",
			CorruptedPlayerEntity.class,
			EntityType.Builder.<CorruptedPlayerEntity>of(CorruptedPlayerEntity::new, MobCategory.CREATURE)
			.sized(0.6f, 1.8f)
		);

	public static final EntityType<VoidLightningEntity> VOID_LIGHTNING_BOLT = register(
		"void_lightning_bolt",
		VoidLightningEntity.class,
		EntityType.Builder.of(VoidLightningEntity::new, MobCategory.MISC)
			.noLootTable()
			.noSave()
			.sized(0.0F, 0.0F)
			.clientTrackingRange(16)
			.updateInterval(Integer.MAX_VALUE)
	);

	public static final EntityType<StaringCatEntity> STARING_CAT = register(
		"staring_cat",
		StaringCatEntity.class,
		EntityType.Builder.of(StaringCatEntity::new, MobCategory.CREATURE)
		.sized(0.6F, 0.7F)
	);

	public static final EntityType<StaringDogEntity> STARING_DOG = register(
		"staring_dog",
		StaringDogEntity.class,
		EntityType.Builder.of(StaringDogEntity::new, MobCategory.CREATURE)
		.sized(0.6F, 0.85F)
	);

	public static final EntityType<CorruptedBlazeEntity> CORRUPTED_BLAZE = register(
		"corrupted_blaze",
		CorruptedBlazeEntity.class,
		EntityType.Builder.of(CorruptedBlazeEntity::new, MobCategory.MONSTER)
		.fireImmune().sized(0.6F, 1.8F).clientTrackingRange(8)
	);

	public static final EntityType<CorruptedCreeperEntity> CORRUPTED_CREEPER = register(
		"corrupted_creeper",
		CorruptedCreeperEntity.class,
		EntityType.Builder.of(CorruptedCreeperEntity::new, MobCategory.MONSTER)
		.sized(0.6F, 1.7F).clientTrackingRange(8)
	);

	public static final EntityType<CorruptedSpiderEntity> CORRUPTED_SPIDER = register(
		"corrupted_spider",
		CorruptedSpiderEntity.class,
		EntityType.Builder.of(CorruptedSpiderEntity::new, MobCategory.MONSTER)
		.sized(1.4F, 0.9F).eyeHeight(0.65F)
		.passengerAttachments(0.765F).clientTrackingRange(8)
	);

	public static final EntityType<SmallCorruptedFireballEntity> SMALL_CORRUPTED_FIREBALL =
		register(
			"small_fireball",
			SmallCorruptedFireballEntity.class,
				EntityType.Builder.
				<SmallCorruptedFireballEntity>
				of(SmallCorruptedFireballEntity::new, MobCategory.MISC)
				.noLootTable()
				.sized(0.3125F, 0.3125F)
				.clientTrackingRange(4)
				.updateInterval(10)
		);

	public static final EntityType<VoidSphereEntity> VOID_SPHERE =
		register("void_sphere",
				 VoidSphereEntity.class,
				 EntityType.Builder.
				 <VoidSphereEntity>of(VoidSphereEntity::new, MobCategory.MISC)
				 .noLootTable()
				 .sized(0.3125F, 0.3125F)
				 .eyeHeight(0.0F)
				 .clientTrackingRange(4)
				 .updateInterval(10));

	public static final EntityType<ThrownBlockEntity> THROWN_BLOCK = register(
		"thrown_block",
		ThrownBlockEntity.class,
		EntityType.Builder.<ThrownBlockEntity>of(ThrownBlockEntity::new, MobCategory.MISC)
			.noLootTable()
			.sized(1F, 1F)
			.clientTrackingRange(10)
			.updateInterval(20)
	);

	public static final EntityType<CorruptedWarriorEntity> CORRUPTED_WARRIOR =
		register("corrupted_warrior",
				 CorruptedWarriorEntity.class,
				 EntityType.Builder.of(CorruptedWarriorEntity::new, MobCategory.CREATURE)
				 .sized(0.6F, 2.9F));

	public static final EntityType<VoidBoundServantEntity> VOID_BOUND_SERVANT =
		register("void_bound_servant",
				 VoidBoundServantEntity.class,
				 EntityType.Builder.of(VoidBoundServantEntity::new, MobCategory.CREATURE)
				 .sized(0.8F, 2.3F));
	
	public static final EntityType<DarkGraspEntity> DARK_GRASP = register(
		"dark_grasp",
		DarkGraspEntity.class,
		EntityType.Builder.<DarkGraspEntity>of(DarkGraspEntity::new, MobCategory.MISC)
			.noLootTable()
			.sized(0.5F, 0.8F)
			.clientTrackingRange(6)
			.updateInterval(2)
	);

	public static final EntityType<EyeBrightEntity> EYE_BRIGHT =
		register(
			"eye_bright",
			EyeBrightEntity.class,
			EntityType.Builder.of(EyeBrightEntity::new, MobCategory.CREATURE)
			.sized(0.6F, 2.9F)
		);

	public static final EntityType<EyeBrightHeadEntity> EYE_BRIGHT_HEAD = register(
		"wither_skull",
		EyeBrightHeadEntity.class,
		EntityType.Builder.<EyeBrightHeadEntity>of(EyeBrightHeadEntity::new, MobCategory.MISC)
			.noLootTable()
			.sized(0.3125F, 0.3125F)
			.clientTrackingRange(4)
			.updateInterval(10)
	);

	public static final EntityType<BedrockBombEntity> BEDROCK_BOMB = register(
		"bedrock_bomb",
		BedrockBombEntity.class,
		EntityType.Builder.<BedrockBombEntity>of(BedrockBombEntity::new, MobCategory.MISC)
			.noLootTable()
			.fireImmune()
			.sized(0.98F, 0.98F)
			.eyeHeight(0.15F)
			.clientTrackingRange(10)
			.updateInterval(10)
	);

	public static final EntityType<ConsumedSoulEntity> CONSUMED_SOUL =
		register(
			"consumed_soul",
			ConsumedSoulEntity.class,
			EntityType.Builder.<ConsumedSoulEntity>of(ConsumedSoulEntity::new, MobCategory.MISC)
			.noLootTable().sized(0.5f, 0.5f)
			.eyeHeight(0.0F) .clientTrackingRange(4).updateInterval(10)
		);

	// Looks bad, but it should be this way.
	// Otherwise entities will not spawn on servers
	public static void registerEntityAttributes() {
		FabricDefaultAttributeRegistry.register(ModEntities.MANTIS, MantisEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SNOWMAN, SnowmanEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CORRUPTED_STALKER, CorruptedStalkerEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SHATTERED_SENTINEL, ShatteredSentinelEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WEREWOLF, WerewolfEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.HOLLOWED_BEAST, HollowedBeastEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SHATTERED_SENTINEL_MASTER, ShatteredSentinelMasterEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.VOID_MAW, VoidMawEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.VOID_HARBINGER, VoidHarbingerEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WORM_OF_CORRUPTION, WormOfCorruptionEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CORRUPTED_BLAZE, CorruptedBlazeEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CORRUPTED_CREEPER, CorruptedCreeperEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CORRUPTED_SPIDER, CorruptedSpiderEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.HOLLOWED_ALPHA_STEVE, HollowedAlphaSteveEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ZOMBIFIED_ALPHA_STEVE, ZombifiedAlphaSteveEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.NULL, NullEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.AGGRESSIVE_NULL, AggressiveNullEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.ENTITY303, Entity303.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CORRUPTED_PLAYER, CorruptedPlayerEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.STARING_CAT, StaringCatEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.STARING_DOG, StaringDogEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.EYE_BRIGHT, EyeBrightEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CORRUPTED_WARRIOR, CorruptedWarriorEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.VOID_BOUND_SERVANT, VoidBoundServantEntity.createAttributes());
	}

	@SuppressWarnings("unchecked")
	protected static final EntityType<? extends Mob>[] spawnRestrictedEntities =
		new EntityType[] {
			ModEntities.CORRUPTED_STALKER,
			ModEntities.SHATTERED_SENTINEL,
			ModEntities.WEREWOLF,
			ModEntities.HOLLOWED_BEAST,
			ModEntities.SHATTERED_SENTINEL_MASTER,
			ModEntities.VOID_HARBINGER,
			ModEntities.WORM_OF_CORRUPTION,
			ModEntities.CORRUPTED_BLAZE,
			ModEntities.CORRUPTED_CREEPER,
			ModEntities.CORRUPTED_SPIDER,
			ModEntities.HOLLOWED_ALPHA_STEVE,
			ModEntities.ZOMBIFIED_ALPHA_STEVE,
			ModEntities.NULL,
			ModEntities.AGGRESSIVE_NULL,
			ModEntities.STARING_CAT,
			ModEntities.STARING_DOG,
			ModEntities.EYE_BRIGHT
		};

	public static void registerModEntities() {
		VoidDimension.LOGGER.info("Registering mod entities for " + VoidDimension.MOD_ID);
	}

	public static void registerSpawnRestrictions() {
		VoidDimension.LOGGER.info(
			"Registering mob spawn restrictions for " + VoidDimension.MOD_ID
		);

		for (EntityType<? extends Mob> entityType : spawnRestrictedEntities) {
			SpawnPlacements.register(
				entityType,
				SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Mob::checkMobSpawnRules
			);
		}
	}

	protected static <T extends Entity> EntityType<T>
		register(String name, Class<T> entityClass, EntityType.Builder<T> entityBuilder)
	{
		ResourceKey<EntityType<?>> key =
			ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name)
		);

		return Registry.register(
			BuiltInRegistries.ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, name),
			entityBuilder.build(key)
		);
	}

}
