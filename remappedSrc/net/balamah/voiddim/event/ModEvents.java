package net.balamah.voiddim.event;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.balamah.voiddim.event.custom.*;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.custom.McCodeHelper;

public class ModEvents {
	public static void registerModEvents() {
		VoidDimension.LOGGER.info("Registering mod events for " + VoidDimension.MOD_ID);

		CorruptionInDarknessCallback.EVENT.register((entity) -> {
			World world = entity.getWorld();

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
				McCodeHelper.isPlayerInSurvival(entity)
			) {
				entity.addStatusEffect(new StatusEffectInstance(ModEffects.CORRUPTION, 10, 1));

				return ActionResult.SUCCESS;
			}

			return ActionResult.PASS;
		});
	}
}
