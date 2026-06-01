package net.balamah.voiddim.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageSources {
	protected static DamageSource getBasicDamageSource(
		ServerLevel world, ResourceKey<DamageType> damageType
	) {
		return new DamageSource(
			world.registryAccess()
			.lookupOrThrow(Registries.DAMAGE_TYPE)
			.get(damageType)
			.orElseThrow()
		);
	}

	public static DamageSource corruption(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.CORRUPTION_DAMAGE);
	}

	public static DamageSource soulBurn(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.SOUL_BURN_DAMAGE);
	}

	public static DamageSource shockWave(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.SHOCKWAVE_DAMAGE);
	}

	public static DamageSource thrownBlock(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.THROWN_BLOCK_DAMAGE);
	}

	public static DamageSource voidSlash(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.VOID_SLASH_DAMAGE);
	}

	public static DamageSource eyeBrightHead(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.EYE_BRIGHT_HEAD_DAMAGE);
	}
}
