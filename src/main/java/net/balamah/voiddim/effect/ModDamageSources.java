package net.balamah.voiddim.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSources {
	protected static DamageSource getBasicDamageSource(ServerLevel world, Identifier identifier) {
		return new DamageSource(
			world.registryAccess()
			.lookupOrThrow(Registries.DAMAGE_TYPE)
			.get(ModEffects.CORRUPTION_DAMAGE.identifier())
			.orElseThrow()
		);
	}

	public static DamageSource corruption(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.CORRUPTION_DAMAGE.identifier());
	}

	public static DamageSource soulBurn(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.SOUL_BURN_DAMAGE.identifier());
	}

	public static DamageSource shockWave(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.SHOCKWAVE_DAMAGE.identifier());
	}

	public static DamageSource thrownBlock(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.THROWN_BLOCK_DAMAGE.identifier());
	}

	public static DamageSource voidSlash(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.VOID_SLASH_DAMAGE.identifier());
	}

	public static DamageSource eyeBrightHead(ServerLevel world) {
		return getBasicDamageSource(world, ModEffects.EYE_BRIGHT_HEAD_DAMAGE.identifier());
	}
}
