package net.balamah.voiddim;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.client.render.BlockRenderLayer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;

import net.balamah.voiddim.custom.NonLivingEntitySpecs;
import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.custom.EntitySpecs;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.entity.custom.*;
import net.balamah.voiddim.entity.client.*;

import java.util.List;

public class VoidDimensionRendering implements ClientModInitializer {
	protected Block[] blocksForTransparency = {
		ModBlocks.VOID_FLOWER, ModBlocks.POTTED_VOID_FLOWER,
		ModBlocks.CORRUPTED_FIRE
	};

	protected List<EntitySpecs<? extends LivingEntity>> entitySpecs =
		List.of(new EntitySpecs<>(ModEntities.MANTIS,             				
								  MantisEntity.createAttributes(),
								  MantisModel.MANTIS,
								  MantisModel.getTexturedModelData(),
								  MantisRenderer::new),
				new EntitySpecs<>(ModEntities.SNOWMAN,             				
								  SnowmanEntity.createAttributes(),
								  SnowmanModel.SNOWMAN,
								  SnowmanModel.getTexturedModelData(),
								  SnowmanRenderer::new),
				new EntitySpecs<>(ModEntities.CORRUPTED_STALKER,             				
								  CorruptedStalkerEntity.createAttributes(),
								  CorruptedStalkerModel.CORRUPTED_STALKER,
								  CorruptedStalkerModel.getTexturedModelData(),
								  CorruptedStalkerRenderer::new),
				new EntitySpecs<>(ModEntities.SHATTERED_SENTINEL,             				
								  ShatteredSentinelEntity.createAttributes(),
								  ShatteredSentinelModel.SHATTERED_SENTINEL,
								  ShatteredSentinelModel.getTexturedModelData(),
								  ShatteredSentinelRenderer::new),
				new EntitySpecs<>(ModEntities.VOID_MAW,             				
								  VoidMawEntity.createAttributes(),
								  VoidMawModel.VOID_MAW,
								  VoidMawModel.getTexturedModelData(),
								  VoidMawRenderer::new),
				new EntitySpecs<>(ModEntities.VOID_HARBINGER,             				
								  VoidHarbingerEntity.createAttributes(),
								  VoidHarbingerModel.VOID_HARBINGER,
								  VoidHarbingerModel.getTexturedModelData(),
								  VoidHarbingerRenderer::new),
				new EntitySpecs<>(ModEntities.SHATTERED_SENTINEL_MASTER,
								  ShatteredSentinelMasterEntity.createAttributes(),
								  ShatteredSentinelMasterModel.SHATTERED_SENTINEL_MASTER,
								  ShatteredSentinelMasterModel.getTexturedModelData(),
								  ShatteredSentinelMasterRenderer::new),
				new EntitySpecs<>(ModEntities.WEREWOLF,             				
								  WerewolfEntity.createAttributes(),
								  WerewolfModel.WEREWOLF,
								  WerewolfModel.getTexturedModelData(),
								  WerewolfRenderer::new),
				new EntitySpecs<>(ModEntities.WORM_OF_CORRUPTION,             				
								  WormOfCorruptionEntity.createAttributes(),
								  WormOfCorruptionModel.WORM_OF_CORRUPTION,
								  WormOfCorruptionModel.getTexturedModelData(),
								  WormOfCorruptionRenderer::new),
				new EntitySpecs<>(ModEntities.HOLLOWED_BEAST,             				
								  HollowedBeastEntity.createAttributes(),
								  HollowedBeastModel.HOLLOWED_BEAST,
								  HollowedBeastModel.getTexturedModelData(),
								  HollowedBeastRenderer::new),
				new EntitySpecs<CorruptedBlazeEntity>(ModEntities.CORRUPTED_BLAZE,
													 CorruptedBlazeEntity.createAttributes(),
													 CorruptedBlazeModel.CORRUPTED_BLAZE,
													 CorruptedBlazeModel.getTexturedModelData(),
													  CorruptedBlazeRenderer::new),
				new EntitySpecs<CorruptedCreeperEntity>(ModEntities.CORRUPTED_CREEPER,
													 CorruptedCreeperEntity.createAttributes(),
													 CorruptedCreeperModel.CORRUPTED_CREEPER,
													 CorruptedCreeperModel.getTexturedModelData(),
													CorruptedCreeperRenderer::new),
				new EntitySpecs<CorruptedSpiderEntity>(ModEntities.CORRUPTED_SPIDER,
													 CorruptedSpiderEntity.createAttributes(),
													 CorruptedSpiderModel.CORRUPTED_SPIDER,
													 CorruptedSpiderModel.getTexturedModelData(),
													 CorruptedSpiderRenderer::new)
				);


	protected List<NonLivingEntitySpecs<? extends Entity>> nonLivingEntitySpecs =
		List.of(new NonLivingEntitySpecs<>(ModEntities.VOID_SPHERE,
										   VoidSphereModel.VOID_SPHERE,
										   VoidSphereModel.getTexturedModelData(),
										   VoidSphereRenderer::new),
				new NonLivingEntitySpecs<>(ModEntities.SMALL_CORRUPTED_FIREBALL,
										   SmallCorruptedFireballModel.SMALL_CORRUPTED_FIREBALL,
										   SmallCorruptedFireballModel.getTexturedModelData(),
										   SmallCorruptedFireballRenderer::new)
				);

	@Override
	public void onInitializeClient() {
		VoidDimension.LOGGER.info(
			"Registering mod rendering for " + VoidDimension.MOD_ID
		);

		this.createLivingEntityRenders(this.entitySpecs);
		this.createEntityRenders(this.nonLivingEntitySpecs);
		this.createBlocksTransparency(this.blocksForTransparency);

		EntityRendererFactories.register(ModEntities.BEDROCK_BOMB, BedrockBombRenderer::new);

		ModParticleTypes.registerModParticles();
	}

	protected <T extends Entity> void registerSpec(NonLivingEntitySpecs<T> spec) {
		EntityModelLayerRegistry.registerModelLayer(spec.modelLayer(),
													() -> spec.texturedModelData());

		EntityRendererFactories.register(spec.entity(), spec.entityRendererFactory());
	}

	protected <T extends LivingEntity> void registerLivingSpec(EntitySpecs<T> spec) {
		FabricDefaultAttributeRegistry.register(spec.entity(), spec.attributes());
		EntityModelLayerRegistry.registerModelLayer(spec.modelLayer(),
													() -> spec.texturedModelData());

		EntityRendererFactories.register(spec.entity(), spec.entityRendererFactory());
	}

	protected void createLivingEntityRenders(
		List<? extends EntitySpecs<? extends LivingEntity>> entitySpecs
	) {
		for (EntitySpecs<? extends LivingEntity> spec : entitySpecs) {
			this.registerLivingSpec(spec);
		}
	}

	protected void createEntityRenders(
		List<? extends NonLivingEntitySpecs<? extends Entity>> entitySpecs
	) {
		for (NonLivingEntitySpecs<? extends Entity> spec : entitySpecs) {
			this.registerSpec(spec);
		}
	}

	protected void createBlocksTransparency(Block[] blocks) {
		for (Block block : blocks) {
			BlockRenderLayerMap.putBlock(block, BlockRenderLayer.CUTOUT);
		}
	}
}
