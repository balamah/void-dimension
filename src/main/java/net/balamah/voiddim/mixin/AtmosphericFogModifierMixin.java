package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.AtmosphericFogEnvironment;

@Mixin(AtmosphericFogEnvironment.class)
public class AtmosphericFogModifierMixin {
	@Inject(method = "setupFog", at = @At("TAIL"))
	protected void modifyFog(
		FogData data, Camera camera,
		ClientLevel world, float viewDistance,
		DeltaTracker renderTickCounter,
		CallbackInfo ci
	) {
		if (world.dimension().equals(ModDimensions.VOID_WORLD)) {
			data.color.set(0.0f, 0.0f, 0.0f, 1.0f);
			data.environmentalStart = 0.0f;
			data.environmentalEnd = Math.min(viewDistance * 0.55f, 48.0f);
			data.skyEnd = Math.min(data.skyEnd, data.environmentalEnd);
			data.cloudEnd = Math.min(data.cloudEnd, data.environmentalEnd);
		}
	}
}
