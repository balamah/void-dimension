package net.balamah.voiddim.component;

import com.mojang.serialization.Codec;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModComponents {
	public static final DataComponentType<Integer> KILL_COUNT =
		Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "kill_count"),
			DataComponentType.<Integer>builder().persistent(Codec.INT).build()
		);

	public static void registerModComponents() {
		VoidDimension.LOGGER.info("Registering mod components for " + VoidDimension.MOD_ID);
	}
}
