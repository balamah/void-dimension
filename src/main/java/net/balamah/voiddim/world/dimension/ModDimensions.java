package net.balamah.voiddim.world.dimension;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class ModDimensions {
	public static final ResourceKey<DimensionType> VOID = register("void");
	public static final Identifier VOID_ID = Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "void");
	public static final ResourceKey<Level> VOID_WORLD =
		ResourceKey.create(Registries.DIMENSION, VOID_ID);

	protected static ResourceKey<DimensionType> register(String id) {
		return ResourceKey.create(
			Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, id)
		);
	}

	public static void registerModDimensions() {
		VoidDimension.LOGGER.info("Registering mod dimensions for " + VoidDimension.MOD_ID);
	}
}
