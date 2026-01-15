package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.render.fog.AtmosphericFogModifier;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.render.fog.FogData;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.render.Camera;

import net.balamah.voiddim.world.dimension.ModDimensions;

@Mixin(AtmosphericFogModifier.class)
public class AtmosphericFogModifierMixin {
	@Inject(method = "applyStartEndModifier", at = @At("TAIL"))
	protected void modifyFog(
		FogData data, Camera camera,
		ClientWorld world, float viewDistance,
		RenderTickCounter renderTickCounter,
		CallbackInfo ci
	) {
		if (world.getRegistryKey().equals(ModDimensions.VOID_WORLD)) {
			data.environmentalStart = viewDistance * 0.05f;
			data.environmentalEnd = viewDistance * 0.45f;
		}
	}
}
