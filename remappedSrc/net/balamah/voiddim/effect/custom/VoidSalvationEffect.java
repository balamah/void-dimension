package net.balamah.voiddim.effect.custom;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.entity.Relative;
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
	public boolean applyEffectTick(ServerWorld world, LivingEntity entity, int amplifier) {
		RegistryKey<World> currentDimension = entity.getWorld().getRegistryKey();
		String currentDimensionId = currentDimension.identifier().toString();
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
		EnumSet<Relative> flags = EnumSet.of(Relative.X, Relative.Y, Relative.Z);
		ServerWorld dimension =
			player.getWorld().getServer().getWorld(dimensionToTeleport);

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
