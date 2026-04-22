package net.balamah.voiddim;

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
import net.balamah.voiddim.custom.BipedEntitySpecs;
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
								  MantisModel.MANTIS,
								  MantisModel.getTexturedModelData(),
								  MantisRenderer::new),
				new EntitySpecs<>(ModEntities.SNOWMAN,             				
								  SnowmanModel.SNOWMAN,
								  SnowmanModel.getTexturedModelData(),
								  SnowmanRenderer::new),
				new EntitySpecs<>(ModEntities.CORRUPTED_STALKER,             				
								  CorruptedStalkerModel.CORRUPTED_STALKER,
								  CorruptedStalkerModel.getTexturedModelData(),
								  CorruptedStalkerRenderer::new),
				new EntitySpecs<>(ModEntities.SHATTERED_SENTINEL,             				
								  ShatteredSentinelModel.SHATTERED_SENTINEL,
								  ShatteredSentinelModel.getTexturedModelData(),
								  ShatteredSentinelRenderer::new),
				new EntitySpecs<>(ModEntities.VOID_MAW,             				
								  VoidMawModel.VOID_MAW,
								  VoidMawModel.getTexturedModelData(),
								  VoidMawRenderer::new),
				new EntitySpecs<>(ModEntities.VOID_HARBINGER,             				
								  VoidHarbingerModel.VOID_HARBINGER,
								  VoidHarbingerModel.getTexturedModelData(),
								  VoidHarbingerRenderer::new),
				new EntitySpecs<>(ModEntities.SHATTERED_SENTINEL_MASTER,
								  ShatteredSentinelMasterModel.SHATTERED_SENTINEL_MASTER,
								  ShatteredSentinelMasterModel.getTexturedModelData(),
								  ShatteredSentinelMasterRenderer::new),
				new EntitySpecs<>(ModEntities.WEREWOLF,             				
								  WerewolfModel.WEREWOLF,
								  WerewolfModel.getTexturedModelData(),
								  WerewolfRenderer::new),
				new EntitySpecs<>(ModEntities.WORM_OF_CORRUPTION,             				
								  WormOfCorruptionModel.WORM_OF_CORRUPTION,
								  WormOfCorruptionModel.getTexturedModelData(),
								  WormOfCorruptionRenderer::new),
				new EntitySpecs<>(ModEntities.HOLLOWED_BEAST,             				
								  HollowedBeastModel.HOLLOWED_BEAST,
								  HollowedBeastModel.getTexturedModelData(),
								  HollowedBeastRenderer::new),
				new EntitySpecs<CorruptedBlazeEntity>(ModEntities.CORRUPTED_BLAZE,
													  CorruptedBlazeModel.CORRUPTED_BLAZE,
													  CorruptedBlazeModel.getTexturedModelData(),
													  CorruptedBlazeRenderer::new),
				new EntitySpecs<CorruptedCreeperEntity>(ModEntities.CORRUPTED_CREEPER,
														CorruptedCreeperModel.CORRUPTED_CREEPER,
														CorruptedCreeperModel.getTexturedModelData(),
														CorruptedCreeperRenderer::new),
				new EntitySpecs<CorruptedSpiderEntity>(ModEntities.CORRUPTED_SPIDER,
													   CorruptedSpiderModel.CORRUPTED_SPIDER,
													   CorruptedSpiderModel.getTexturedModelData(),
													   CorruptedSpiderRenderer::new),
				new EntitySpecs<StaringDogEntity>(ModEntities.STARING_DOG,
												  StaringDogModel.STARING_DOG,
												  StaringDogModel.getTexturedModelData(),
												  StaringDogRenderer::new),
				new EntitySpecs<StaringCatEntity>(ModEntities.STARING_CAT,
												  StaringCatModel.STARING_CAT,
												  StaringCatModel.getTexturedModelData(),
												  StaringCatRenderer::new),
				new EntitySpecs<EyeBrightEntity>(ModEntities.EYE_BRIGHT,
												  EyeBrightModel.EYE_BRIGHT,
												  EyeBrightModel.getTexturedModelData(),
												  EyeBrightRenderer::new),
				new EntitySpecs<CorruptedWarriorEntity>(ModEntities.CORRUPTED_WARRIOR,
														CorruptedWarriorModel.CORRUPTED_WARRIOR,
														CorruptedWarriorModel.getTexturedModelData(),
														CorruptedWarriorRenderer::new),
				new EntitySpecs<VoidBoundServantEntity>(ModEntities.VOID_BOUND_SERVANT,
														VoidBoundServantModel.VOID_BOUND_SERVANT,
														VoidBoundServantModel.getTexturedModelData(),
														VoidBoundServantRenderer::new)
				);


	protected List<BipedEntitySpecs<? extends Entity>> bipedEntitySpecs =
		List.of(new BipedEntitySpecs<>(ModEntities.HOLLOWED_ALPHA_STEVE,
									   HollowedAlphaSteveModel.HOLLOWED_ALPHA_STEVE,
									   HollowedAlphaSteveModel.getTexturedModelData(),
									   context -> new HollowedAlphaSteveRenderer<>(
										   context,
										   new HollowedAlphaSteveModel<>(
											   context.getPart(HollowedAlphaSteveModel.HOLLOWED_ALPHA_STEVE)
										   ),
										   0.5f
									   )),
				new BipedEntitySpecs<>(ModEntities.ZOMBIFIED_ALPHA_STEVE,
									   HollowedAlphaSteveModel.ZOMBIFIED_ALPHA_STEVE,
									   HollowedAlphaSteveModel.getTexturedModelData(),
									   context -> new ZombifiedAlphaSteveRenderer<>(
										   context,
										   new HollowedAlphaSteveModel<>(
											   context.getPart(HollowedAlphaSteveModel.ZOMBIFIED_ALPHA_STEVE)
										   ),
										   0.5f
									   )),
				new BipedEntitySpecs<>(ModEntities.NULL,
									   HollowedAlphaSteveModel.NULL,
									   HollowedAlphaSteveModel.getTexturedModelData(),
									   context -> new NullEntityRenderer<>(
										   context,
										   new HollowedAlphaSteveModel<>(
											   context.getPart(HollowedAlphaSteveModel.NULL)
										   ),
										   0.5f
									   )),
				new BipedEntitySpecs<>(ModEntities.AGGRESSIVE_NULL,
									   HumanModel.AGGRESSIVE_NULL,
									   HumanModel.getTexturedModelData(),
									   context -> new AggressiveNullRenderer<>(
										   context,
										   new HumanModel<>(
											   context.getPart(HumanModel.AGGRESSIVE_NULL)
										   ),
										   0.5f
									   )),
				new BipedEntitySpecs<>(ModEntities.ENTITY303,
									   HumanModel.HEROBRINE,
									   HumanModel.getTexturedModelData(),
									   context -> new Entity303Renderer<>(
										   context,
										   new HumanModel<>(
											   context.getPart(HumanModel.HEROBRINE)
										   ),
										   0.5f
									   ))
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
		VoidDimension.LOGGER.info("Registering mod rendering for " + VoidDimension.MOD_ID);

		this.createLivingEntityRenders(this.entitySpecs);
		this.createBipedEntityRenders(this.bipedEntitySpecs);
		this.createEntityRenders(this.nonLivingEntitySpecs);
		this.createBlocksTransparency(this.blocksForTransparency);

		ModParticleTypes.registerModParticles();

		EntityRendererFactories.register(ModEntities.BEDROCK_BOMB, BedrockBombRenderer::new);
		EntityRendererFactories.register(ModEntities.VOID_LIGHTNING_BOLT, VoidLightningEntityRenderer::new);
		EntityRendererFactories.register(ModEntities.EYE_BRIGHT_HEAD, EyeBrightHeadRenderer::new);
		EntityRendererFactories.register(ModEntities.DARK_GRASP, DarkGraspRenderer::new);
	}

	protected <T extends Entity> void registerSpec(NonLivingEntitySpecs<T> spec) {
		EntityModelLayerRegistry.registerModelLayer(
			spec.modelLayer(), () -> spec.texturedModelData()
		);

		EntityRendererFactories.register(spec.entity(), spec.entityRendererFactory());
	}

	protected <T extends LivingEntity> void registerLivingSpec(EntitySpecs<T> spec) {
		EntityModelLayerRegistry.registerModelLayer(
			spec.modelLayer(), () -> spec.texturedModelData()
		);

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

	protected void createBipedEntityRenders(
		List<? extends BipedEntitySpecs<? extends Entity>> entitySpecs
	) {
		for (BipedEntitySpecs<? extends Entity> spec : entitySpecs) {
			this.registerBipedEntitySpec(spec);
		}
	}

	protected void createBlocksTransparency(Block[] blocks) {
		for (Block block : blocks) {
			BlockRenderLayerMap.putBlock(block, BlockRenderLayer.CUTOUT);
		}
	}

	protected <T extends LivingEntity> void registerBipedEntitySpec(BipedEntitySpecs<T> specs) {
		EntityModelLayerRegistry.registerModelLayer(specs.modelLayer(), specs::texturedModelData);

		EntityRendererFactories.register(specs.entity(), specs.entityRendererFactory());
	}
}
