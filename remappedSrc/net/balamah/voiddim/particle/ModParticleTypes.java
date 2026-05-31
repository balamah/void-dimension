package net.balamah.voiddim.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class ModParticleTypes {
	public static final SimpleParticleType CORRUPTED_FLAME = FabricParticleTypes.simple();
	public static final SimpleParticleType CORRUPTION = FabricParticleTypes.simple();
	public static final SimpleParticleType LIGHTNING = FabricParticleTypes.simple();
	public static final SimpleParticleType VOID_SLASH_AREA = FabricParticleTypes.simple();

	public static void registerModParticles() {
		VoidDimension.LOGGER.info("Registering mod particles for " + VoidDimension.MOD_ID);

		ParticleProviderRegistry.getInstance().register(CORRUPTED_FLAME, FlameParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(CORRUPTION, EndRodParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(LIGHTNING, EndRodParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(VOID_SLASH_AREA, EndRodParticle.Factory::new);
	}

	public static void registerParticlesServer() {
		VoidDimension.LOGGER.info(
			"Registering mod particles on server and client side for " + VoidDimension.MOD_ID
		);

		registerParticle("corrupted_flame", CORRUPTED_FLAME);
		registerParticle("corruption", CORRUPTION);
		registerParticle("lightning", LIGHTNING);
		registerParticle("void_slash_area", VOID_SLASH_AREA);
	}

	protected static void registerParticle(String particleName, SimpleParticleType particleType) {
		Registry.register(
			Registries.PARTICLE_TYPE,
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, particleName),
			particleType
		);
	}
}
