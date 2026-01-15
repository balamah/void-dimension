package net.balamah.voiddim.component;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import com.mojang.serialization.Codec;

import net.balamah.voiddim.VoidDimension;

public class ModComponents {
	public static final ComponentType<Integer> KILL_COUNT =
		Registry.register(
			Registries.DATA_COMPONENT_TYPE,
			Identifier.of(VoidDimension.MOD_ID, "kill_count"),
			ComponentType.<Integer>builder().codec(Codec.INT).build()
		);

	public static void registerModComponents() {
		VoidDimension.LOGGER.info("Registering mod components for " + VoidDimension.MOD_ID);
	}
}
