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
			data.environmentalStart = viewDistance * 0.05f;
			data.environmentalEnd = viewDistance * 0.45f;
		}
	}
}
