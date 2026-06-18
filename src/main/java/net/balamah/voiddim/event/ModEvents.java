package net.balamah.voiddim.event;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.event.custom.*;
import net.balamah.voiddim.VoidDimension;

public class ModEvents {
	@SuppressWarnings("unchecked")
	public static void registerModEvents() {
		VoidDimension.LOGGER.info("Registering mod events for " + VoidDimension.MOD_ID);

		CorruptionInDarknessCallback.EVENT.register((entity) -> {
			Level world = entity.level();

			if (world.isClientSide()) {
				return InteractionResult.PASS;
			}

			BlockPos pos = new BlockPos(
				Mth.floor(entity.getX()),
				Mth.floor(entity.getY() + entity.getBbHeight() / 2),
				Mth.floor(entity.getZ())
			);

			int lightLevel = world.getMaxLocalRawBrightness(pos);
			if (lightLevel == 0 && world.dimension() == ModDimensions.VOID_WORLD &&
				McCodeHelper.isPlayerInSurvival(entity)
			) {
				entity.addEffect(new MobEffectInstance(ModEffects.CORRUPTION, 10, 1));

				return InteractionResult.SUCCESS;
			}

			return InteractionResult.PASS;
		});

		ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
			if (!(entity instanceof ServerPlayer) && entity.hasEffect(ModEffects.CORRUPTION)) {
				ServerLevel world = (ServerLevel) entity.level();
				
				BlockPos entityBlockPos = entity.blockPosition();

				EntityType<? extends LivingEntity> corruptedEntityType =
					(EntityType<? extends LivingEntity>)
					McCodeHelper.entityCorruptionMap.get(entity.getType());

				if (corruptedEntityType == null) {
					return;
				}

				LivingEntity corruptedEntity = corruptedEntityType.create(
					world, null, entityBlockPos, EntitySpawnReason.MOB_SUMMONED, true, false
				);

				world.addFreshEntity(corruptedEntity);
			}
		});
	}
}
