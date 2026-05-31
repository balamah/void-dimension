package net.balamah.voiddim.world.dimension;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensions {
	public static final RegistryKey<DimensionType> VOID = register("void");
	public static final ResourceLocation VOID_ID = ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "void");
	public static final RegistryKey<World> VOID_WORLD =
		RegistryKey.of(RegistryKeys.WORLD, VOID_ID);

	protected static RegistryKey<DimensionType> register(String id) {
		return RegistryKey.of(
			RegistryKeys.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, id)
		);
	}

	public static void registerModDimensions() {
		VoidDimension.LOGGER.info("Registering mod dimensions for " + VoidDimension.MOD_ID);
	}
}
