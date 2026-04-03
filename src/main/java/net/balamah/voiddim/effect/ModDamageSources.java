package net.balamah.voiddim.effect;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKeys;

public class ModDamageSources {
	protected static DamageSource getBasicDamageSource(ServerWorld world, Identifier identifier) {
		return new DamageSource(
			world.getRegistryManager()
			.getOrThrow(RegistryKeys.DAMAGE_TYPE)
			.getEntry(ModEffects.CORRUPTION_DAMAGE.getValue())
			.orElseThrow()
		);
	}

	public static DamageSource corruption(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.CORRUPTION_DAMAGE.getValue());
	}

	public static DamageSource shockWave(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.SHOCKWAVE_DAMAGE.getValue());
	}

	public static DamageSource thrownBlock(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.THROWN_BLOCK_DAMAGE.getValue());
	}

	public static DamageSource voidSlash(ServerWorld world) {
		return getBasicDamageSource(world, ModEffects.VOID_SLASH_DAMAGE.getValue());
	}
}
