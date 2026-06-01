package net.balamah.voiddim.particle;

import net.balamah.voiddim.VoidDimension;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.particle.FlameParticle;

public class ModParticleFactories {
    public static void registerModParticlesClient() {
        VoidDimension.LOGGER.info("Registering particle factories");

        ParticleFactoryRegistry.getInstance().register(
            ModParticleTypes.CORRUPTED_FLAME,
            FlameParticle.Provider::new
        );

        ParticleFactoryRegistry.getInstance().register(
            ModParticleTypes.CORRUPTION,
            EndRodParticle.Provider::new
        );

        ParticleFactoryRegistry.getInstance().register(
            ModParticleTypes.LIGHTNING,
            EndRodParticle.Provider::new
        );

        ParticleFactoryRegistry.getInstance().register(
            ModParticleTypes.VOID_SLASH_AREA,
            EndRodParticle.Provider::new
        );
    }
}
