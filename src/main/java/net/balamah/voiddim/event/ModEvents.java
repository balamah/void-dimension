package net.balamah.voiddim.event;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ActionResult;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

import net.balamah.voiddim.event.custom.CorruptionInDarknessCallback;
import net.balamah.voiddim.world.dimension.ModDimensions;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.VoidDimension;

public class ModEvents {
	public static void registerModEvents() {
		VoidDimension.LOGGER.info("Registering mod events for " + VoidDimension.MOD_ID);

		CorruptionInDarknessCallback.EVENT.register((entity) -> {
			World world = entity.getEntityWorld();

			if (world.isClient()) {
				return ActionResult.PASS;
			}

			BlockPos pos = new BlockPos(
				MathHelper.floor(entity.getX()),
				MathHelper.floor(entity.getY() + entity.getHeight() / 2),
				MathHelper.floor(entity.getZ())
			);

			int lightLevel = world.getLightLevel(pos);
			if (lightLevel == 0 && world.getRegistryKey() == ModDimensions.VOID_WORLD &&
				isPlayerInSurvival(entity)
			) {
				entity.addStatusEffect(new StatusEffectInstance(ModEffects.CORRUPTION, 10, 1));

				return ActionResult.SUCCESS;
			}

			return ActionResult.PASS;
		});
	}

	protected static boolean isPlayerInSurvival(LivingEntity entity) {
		if (!(entity instanceof ServerPlayerEntity playerEntity)) {
			return true;
		}

		GameMode gamemode = playerEntity.interactionManager.getGameMode();
		return gamemode == GameMode.SURVIVAL || gamemode == GameMode.ADVENTURE;
	}
}
