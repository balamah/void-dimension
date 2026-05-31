package net.balamah.voiddim.particle;

import net.balamah.voiddim.VoidDimension;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModParticleTypes {

    public static final SimpleParticleType CORRUPTED_FLAME = FabricParticleTypes.simple();
    public static final SimpleParticleType CORRUPTION = FabricParticleTypes.simple();
    public static final SimpleParticleType LIGHTNING = FabricParticleTypes.simple();
    public static final SimpleParticleType VOID_SLASH_AREA = FabricParticleTypes.simple();

    public static void registerParticlesServer() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE,
            ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_flame"),
            CORRUPTED_FLAME);

        Registry.register(BuiltInRegistries.PARTICLE_TYPE,
            ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "corruption"),
            CORRUPTION);

        Registry.register(BuiltInRegistries.PARTICLE_TYPE,
            ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "lightning"),
            LIGHTNING);

        Registry.register(BuiltInRegistries.PARTICLE_TYPE,
            ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_slash_area"),
            VOID_SLASH_AREA);
    }

    public static void registerModParticlesClient() {
        VoidDimension.LOGGER.info("Registering particle factories");

        ParticleFactoryRegistry.getInstance().register(
            CORRUPTED_FLAME,
            FlameParticle.Provider::new
        );

        ParticleFactoryRegistry.getInstance().register(
            CORRUPTION,
            EndRodParticle.Provider::new
        );

        ParticleFactoryRegistry.getInstance().register(
            LIGHTNING,
            EndRodParticle.Provider::new
        );

        ParticleFactoryRegistry.getInstance().register(
            VOID_SLASH_AREA,
            EndRodParticle.Provider::new
        );
    }
}
