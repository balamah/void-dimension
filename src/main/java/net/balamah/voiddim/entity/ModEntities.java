package net.balamah.voiddim.entity;

import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.Registries;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.entity.Entity;

import net.balamah.voiddim.entity.custom.*;
import net.balamah.voiddim.VoidDimension;

public class ModEntities {
	public static final EntityType<MantisEntity> MANTIS =
		register(
			"mantis",
			MantisEntity.class,
			EntityType.Builder.create(MantisEntity::new, SpawnGroup.CREATURE)
			.dimensions(1f, 2.5f)
		);

	public static final EntityType<SnowmanEntity> SNOWMAN =
		register(
			"snowman",
			SnowmanEntity.class,
			EntityType.Builder.create(SnowmanEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.6f, 1.95f)
		);

	public static final EntityType<CorruptedStalkerEntity> CORRUPTED_STALKER =
		register(
			"corrupted_stalker",
			CorruptedStalkerEntity.class,
			EntityType.Builder.create(CorruptedStalkerEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.6F, 2.9F)
		);

	public static final EntityType<ShatteredSentinelEntity> SHATTERED_SENTINEL =
		register(
			"shattered_sentinel",
			ShatteredSentinelEntity.class,
			EntityType.Builder.create(ShatteredSentinelEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.6F, 1.95f)
		);

	public static final EntityType<WerewolfEntity> WEREWOLF =
		register(
			"werewolf",
			WerewolfEntity.class,
			EntityType.Builder.create(WerewolfEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.6F, 1.95f)
		);

	public static final EntityType<HollowedBeastEntity> HOLLOWED_BEAST =
		register(
			"hollowed_beast",
			HollowedBeastEntity.class,
			EntityType.Builder.create(HollowedBeastEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.6F, 1.95f)
		);

	public static final EntityType<ShatteredSentinelMasterEntity> SHATTERED_SENTINEL_MASTER =
		register(
			"shattered_sentinel_master",
			ShatteredSentinelMasterEntity.class,
			EntityType.Builder.create(ShatteredSentinelMasterEntity::new, SpawnGroup.CREATURE)
			.dimensions(1F, 2.8f)
		);

	public static final EntityType<VoidMawEntity> VOID_MAW =
		register(
			"void_maw",
			VoidMawEntity.class,
			EntityType.Builder.create(VoidMawEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.8F, 0.8F)
		);

	public static final EntityType<VoidHarbingerEntity> VOID_HARBINGER =
		register(
			"void_harbinger",
			VoidHarbingerEntity.class,
			EntityType.Builder.create(VoidHarbingerEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.6F, 3.0F)
		);

	public static final EntityType<WormOfCorruptionEntity> WORM_OF_CORRUPTION =
		register(
			"worm_of_corruption",
			WormOfCorruptionEntity.class,
			EntityType.Builder.create(WormOfCorruptionEntity::new, SpawnGroup.CREATURE)
			.dimensions(1.2F, 2.5f)
		);

	public static final EntityType<HollowedAlphaSteveEntity> HOLLOWED_ALPHA_STEVE =
		register(
			"hollowed_alpha_steve",
			HollowedAlphaSteveEntity.class,
			EntityType.Builder.<HollowedAlphaSteveEntity>create(HollowedAlphaSteveEntity::new, SpawnGroup.CREATURE)
			.dimensions(1.2F, 2.5f)
		);

	public static final EntityType<CorruptedBlazeEntity> CORRUPTED_BLAZE = register(
		"corrupted_blaze",
		CorruptedBlazeEntity.class,
		EntityType.Builder.create(CorruptedBlazeEntity::new, SpawnGroup.MONSTER)
		.makeFireImmune().dimensions(0.6F, 1.8F).maxTrackingRange(8)
	);

	public static final EntityType<CorruptedCreeperEntity> CORRUPTED_CREEPER = register(
		"corrupted_creeper",
		CorruptedCreeperEntity.class,
		EntityType.Builder.create(CorruptedCreeperEntity::new, SpawnGroup.MONSTER)
		.dimensions(0.6F, 1.7F).maxTrackingRange(8)
	);

	public static final EntityType<CorruptedSpiderEntity> CORRUPTED_SPIDER = register(
		"corrupted_spider",
		CorruptedSpiderEntity.class,
		EntityType.Builder.create(CorruptedSpiderEntity::new, SpawnGroup.MONSTER)
		.dimensions(1.4F, 0.9F).eyeHeight(0.65F)
		.passengerAttachments(0.765F).maxTrackingRange(8)
	);

	public static final EntityType<SmallCorruptedFireballEntity> SMALL_CORRUPTED_FIREBALL =
		register(
			"small_fireball",
			SmallCorruptedFireballEntity.class,
				EntityType.Builder.
				<SmallCorruptedFireballEntity>
				create(SmallCorruptedFireballEntity::new, SpawnGroup.MISC)
				.dropsNothing()
				.dimensions(0.3125F, 0.3125F)
				.maxTrackingRange(4)
				.trackingTickInterval(10)
		);

	public static final EntityType<VoidSphereEntity> VOID_SPHERE =
		register("void_sphere",
				 VoidSphereEntity.class,
				 EntityType.Builder.
				 <VoidSphereEntity>create(VoidSphereEntity::new, SpawnGroup.MISC)
				 .dropsNothing()
				 .dimensions(0.3125F, 0.3125F)
				 .eyeHeight(0.0F)
				 .maxTrackingRange(4)
				 .trackingTickInterval(10));

	public static final EntityType<ThrownBlockEntity> THROWN_BLOCK = register(
		"thrown_block",
		ThrownBlockEntity.class,
		EntityType.Builder.<ThrownBlockEntity>create(ThrownBlockEntity::new, SpawnGroup.MISC)
			.dropsNothing()
			.dimensions(1F, 1F)
			.maxTrackingRange(10)
			.trackingTickInterval(20)
	);

	// TODO: Restore the code down below
	// public static final EntityType<CorruptedWarriorEntity> CORRUPTED_WARRIOR =
	// 	register("corrupted_warrior",
	// 			 CorruptedWarriorEntity.class,
	// 			 EntityType.Builder.create(CorruptedWarriorEntity::new, SpawnGroup.CREATURE)
	// 			 .dimensions(0.6F, 2.9F));

	// public static final EntityType<VoidBoundServantEntity> VOID_BOUND_SERVANT =
	// 	register("void_bound_servant",
	// 			 VoidBoundServantEntity.class,
	// 			 EntityType.Builder.create(VoidBoundServantEntity::new, SpawnGroup.CREATURE)
	// 			 .dimensions(0.8F, 2.3F));
	
	// public static final EntityType<DarkGraspEntity> DARK_GRASP = register(
	// 	"evoker_fangs",
	// 	DarkGraspEntity.class,
	// 	EntityType.Builder.<DarkGraspEntity>create(DarkGraspEntity::new, SpawnGroup.MISC)
	// 		.dropsNothing()
	// 		.dimensions(0.5F, 0.8F)
	// 		.maxTrackingRange(6)
	// 		.trackingTickInterval(2)
	// );

	public static final EntityType<BedrockBombEntity> BEDROCK_BOMB = register(
		"bedrock_bomb",
		BedrockBombEntity.class,
		EntityType.Builder.<BedrockBombEntity>create(BedrockBombEntity::new, SpawnGroup.MISC)
			.dropsNothing()
			.makeFireImmune()
			.dimensions(0.98F, 0.98F)
			.eyeHeight(0.15F)
			.maxTrackingRange(10)
			.trackingTickInterval(10)
	);

	protected static final EntityType<? extends MobEntity>[] restrictedEntities =
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
			ModEntities.CORRUPTED_SPIDER
		};

	public static void registerModEntities() {
		VoidDimension.LOGGER.info("Registering mod entities for " + VoidDimension.MOD_ID);
	}

	public static void registerSpawnRestrictions() {
		VoidDimension.LOGGER.info(
			"Registering mob spawn restrictions for " + VoidDimension.MOD_ID
		);

		for (EntityType<? extends MobEntity> entityType : restrictedEntities) {
			SpawnRestriction.register(
				entityType,
				SpawnLocationTypes.ON_GROUND,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MobEntity::canMobSpawn
			);
		}
	}

	protected static <T extends Entity> EntityType<T>
		register(String name, Class<T> entityClass, EntityType.Builder<T> entityBuilder)
	{
		RegistryKey<EntityType<?>> key =
			RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(VoidDimension.MOD_ID, name)
		);

		return Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(VoidDimension.MOD_ID, name),
			entityBuilder.build(key)
		);
	}
}
