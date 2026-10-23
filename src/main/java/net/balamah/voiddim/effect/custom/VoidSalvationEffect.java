package net.balamah.voiddim.effect.custom;

import net.balamah.voiddim.VoidDimension;
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

		int minimumTeleportHeight = this.getMinimumTeleportHeight(currentDimensionId);

		if (entity.getY() < minimumTeleportHeight && dimensionToTeleport != null) {
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
			String dimensionToTeleportID = dimension.dimension().identifier().toString();
			int teleportHeight = this.getTeleportationHeight(dimensionToTeleportID);

			entity.teleportTo(
				dimension, entity.getX(), teleportHeight, entity.getZ(),
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
			case "minecraft:the_nether":
				return ModDimensions.ABYSS_WORLD;
			default:
				return null;
		}
	}

	protected int getTeleportationHeight(String dimensionToTeleportID) {
		switch (dimensionToTeleportID) {
			case "void-dimension:void": return 250;
			case "void-dimension:abyss": return 5;
			default: return 255;
		}
	}

	protected int getMinimumTeleportHeight(String dimensionID) {
		switch (dimensionID) {
			case "minecraft:overworld":
			case "void-dimension:void":
				return -120;
			case "minecraft:the_nether":
				return -60;
			default:
				return -120;
		}
	}
}
