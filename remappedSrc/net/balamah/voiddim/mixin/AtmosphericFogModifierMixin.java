package net.balamah.voiddim.mixin;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.AtmosphericFogEnvironment;
import net.minecraft.client.world.ClientWorld;

@Mixin(AtmosphericFogEnvironment.class)
public class AtmosphericFogModifierMixin {
	@Inject(method = "setupFog", at = @At("TAIL"))
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
