package net.balamah.voiddim.event;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.balamah.voiddim.event.custom.*;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.Level;
import net.balamah.voiddim.effect.ModEffects;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.custom.McCodeHelper;

public class ModEvents {
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
	}
}
