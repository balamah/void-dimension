package net.balamah.voiddim.world.dimension;

import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class ModDimensions {
	public static final RegistryKey<DimensionType> VOID = register("void");
	public static final Identifier VOID_ID = Identifier.of(VoidDimension.MOD_ID, "void");
	public static final RegistryKey<World> VOID_WORLD =
		RegistryKey.of(RegistryKeys.WORLD, VOID_ID);

	protected static RegistryKey<DimensionType> register(String id) {
		return RegistryKey.of(
			RegistryKeys.DIMENSION_TYPE, Identifier.of(VoidDimension.MOD_ID, id)
		);
	}

	public static void registerModDimensions() {
		VoidDimension.LOGGER.info("Registering mod dimensions for " + VoidDimension.MOD_ID);
	}
}
