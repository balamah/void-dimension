package net.balamah.voiddim.effect;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.world.ServerWorld;

public class ModDamageSources {
	protected static DamageSource getBasicDamageSource(ServerWorld world, ResourceLocation identifier) {
		return new DamageSource(
			world.getRegistryManager()
			.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE)
			.getOptional(ModEffects.CORRUPTION_DAMAGE.identifier())
			.orElseThrow()
		);
	}

	public static DamageSource corruption(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.CORRUPTION_DAMAGE.identifier());
	}

	public static DamageSource soulBurn(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.SOUL_BURN_DAMAGE.identifier());
	}

	public static DamageSource shockWave(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.SHOCKWAVE_DAMAGE.identifier());
	}

	public static DamageSource thrownBlock(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.THROWN_BLOCK_DAMAGE.identifier());
	}

	public static DamageSource voidSlash(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.VOID_SLASH_DAMAGE.identifier());
	}

	public static DamageSource eyeBrightHead(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.EYE_BRIGHT_HEAD_DAMAGE.identifier());
	}
}
