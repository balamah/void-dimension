package net.balamah.voiddim.effect.custom;

import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import net.balamah.voiddim.world.dimension.ModDimensions;

import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class VoidSalvationEffect extends StatusEffect {
	public VoidSalvationEffect() {
		super(StatusEffectCategory.BENEFICIAL, 0xFFFFFFFF);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
		RegistryKey<World> currentDimension = entity.getEntityWorld().getRegistryKey();
		String currentDimensionId = currentDimension.getValue().toString();
		RegistryKey<World> dimensionToTeleport = this.getDimensionToTeleport(currentDimensionId);

		if (entity instanceof ServerPlayerEntity player && entity.getY() < -120 &&
			dimensionToTeleport != null
		) {
			this.teleportPlayer(player, dimensionToTeleport);
		}

		return super.applyUpdateEffect(world, entity, amplifier);
	}

	protected void teleportPlayer(
		ServerPlayerEntity player, RegistryKey<World> dimensionToTeleport
	) {
		EnumSet<PositionFlag> flags = EnumSet.of(PositionFlag.X, PositionFlag.Y, PositionFlag.Z);
		ServerWorld dimension =
			player.getEntityWorld().getServer().getWorld(dimensionToTeleport);

		if (dimension != null) {
			player.teleport(
				dimension, 100, 250, 100, flags, player.getYaw(), player.getPitch(), true
			);

			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 350, 1));
		}
	}

	@Nullable 
	protected RegistryKey<World> getDimensionToTeleport(String dimensionId) {
		switch (dimensionId) {
			case "minecraft:overworld":
				return ModDimensions.VOID_WORLD;
			case "void-dimension:void":
				return World.NETHER;
			default:
				return null;
		}
	}
}
