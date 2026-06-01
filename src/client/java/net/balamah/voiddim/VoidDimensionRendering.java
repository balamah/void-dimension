package net.balamah.voiddim;

import net.balamah.voiddim.client.FallbackEntityRenderer;
import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.particle.ModParticleFactories;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public class VoidDimensionRendering implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		VoidDimension.LOGGER.info("Registering fallback client rendering for " + VoidDimension.MOD_ID);

		ModParticleFactories.registerModParticlesClient();

		registerFallback(ModEntities.MANTIS);
		registerFallback(ModEntities.SNOWMAN);
		registerFallback(ModEntities.CORRUPTED_STALKER);
		registerFallback(ModEntities.SHATTERED_SENTINEL);
		registerFallback(ModEntities.WEREWOLF);
		registerFallback(ModEntities.HOLLOWED_BEAST);
		registerFallback(ModEntities.SHATTERED_SENTINEL_MASTER);
		registerFallback(ModEntities.VOID_MAW);
		registerFallback(ModEntities.VOID_HARBINGER);
		registerFallback(ModEntities.WORM_OF_CORRUPTION);
		registerFallback(ModEntities.HOLLOWED_ALPHA_STEVE);
		registerFallback(ModEntities.ZOMBIFIED_ALPHA_STEVE);
		registerFallback(ModEntities.NULL);
		registerFallback(ModEntities.AGGRESSIVE_NULL);
		registerFallback(ModEntities.ENTITY303);
		registerFallback(ModEntities.CORRUPTED_PLAYER);
		registerFallback(ModEntities.VOID_LIGHTNING_BOLT);
		registerFallback(ModEntities.STARING_CAT);
		registerFallback(ModEntities.STARING_DOG);
		registerFallback(ModEntities.CORRUPTED_BLAZE);
		registerFallback(ModEntities.CORRUPTED_CREEPER);
		registerFallback(ModEntities.CORRUPTED_SPIDER);
		registerFallback(ModEntities.SMALL_CORRUPTED_FIREBALL);
		registerFallback(ModEntities.VOID_SPHERE);
		registerFallback(ModEntities.THROWN_BLOCK);
		registerFallback(ModEntities.CORRUPTED_WARRIOR);
		registerFallback(ModEntities.VOID_BOUND_SERVANT);
		registerFallback(ModEntities.DARK_GRASP);
		registerFallback(ModEntities.EYE_BRIGHT);
		registerFallback(ModEntities.EYE_BRIGHT_HEAD);
		registerFallback(ModEntities.BEDROCK_BOMB);
		registerFallback(ModEntities.CONSUMED_SOUL);
	}

	private static <T extends Entity> void registerFallback(EntityType<T> entityType) {
		EntityRendererRegistry.register(entityType, FallbackEntityRenderer::new);
	}
}
