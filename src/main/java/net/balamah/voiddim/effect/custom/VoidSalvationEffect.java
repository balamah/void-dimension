package net.balamah.voiddim.effect.custom;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class VoidSalvationEffect extends MobEffect {
	public VoidSalvationEffect() {
		super(MobEffectCategory.BENEFICIAL, 0xFFFFFFFF);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
		ResourceKey<Level> currentDimension = entity.level().dimension();
		String currentDimensionId = currentDimension.identifier().toString();
		ResourceKey<Level> dimensionToTeleport = this.getDimensionToTeleport(currentDimensionId);

		if (entity.getY() < -120 && dimensionToTeleport != null) {
			this.teleportEntity(entity, dimensionToTeleport);
		}

		return super.applyEffectTick(world, entity, amplifier);
	}

	protected void teleportEntity(
		LivingEntity entity, ResourceKey<Level> dimensionToTeleport
	) {
		EnumSet<Relative> flags = EnumSet.of(Relative.X, Relative.Y, Relative.Z);
		ServerLevel dimension =
			entity.level().getServer().getLevel(dimensionToTeleport);

		if (dimension != null) {
			entity.teleportTo(
				dimension, entity.getX(), 250, entity.getZ(),
				flags, entity.getYRot(), entity.getXRot(), true
			);

			entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 350, 1));
		}
	}

	@Nullable 
	protected ResourceKey<Level> getDimensionToTeleport(String dimensionId) {
		switch (dimensionId) {
			case "minecraft:overworld":
				return ModDimensions.VOID_WORLD;
			case "void-dimension:void":
				return Level.NETHER;
			default:
				return null;
		}
	}
}
