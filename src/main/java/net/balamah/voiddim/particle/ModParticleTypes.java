package net.balamah.voiddim.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class ModParticleTypes {
	public static final SimpleParticleType CORRUPTED_FLAME =
		FabricParticleTypes.simple();

	public static final SimpleParticleType CORRUPTION = FabricParticleTypes.simple();
	public static final SimpleParticleType LIGHTNING = FabricParticleTypes.simple();

	public static void registerModParticles() {
		VoidDimension.LOGGER.info("Registering mod particles for " + VoidDimension.MOD_ID);

		Registry.register(
			Registries.PARTICLE_TYPE,
			Identifier.of(VoidDimension.MOD_ID, "corrupted_flame"),
			CORRUPTED_FLAME
		);

		Registry.register(
			Registries.PARTICLE_TYPE,
			Identifier.of(VoidDimension.MOD_ID, "corruption"),
			CORRUPTION
		);

		Registry.register(
			Registries.PARTICLE_TYPE,
			Identifier.of(VoidDimension.MOD_ID, "lightning"),
			LIGHTNING
		);

		ParticleFactoryRegistry.getInstance().register(CORRUPTED_FLAME, FlameParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(CORRUPTION, EndRodParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LIGHTNING, EndRodParticle.Factory::new);
	}
}
