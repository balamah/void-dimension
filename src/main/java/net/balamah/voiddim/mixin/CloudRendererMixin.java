package net.balamah.voiddim.mixin;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CloudRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CloudRenderer.class)
public class CloudRendererMixin {
	@Inject(
		method = {
			"prepare(ILnet/minecraft/client/CloudStatus;FILnet/minecraft/world/phys/Vec3;JF)V",
			"render(Lnet/minecraft/client/CloudStatus;Lcom/mojang/renderpearl/api/commands/RenderPass;)V",
			"renderOit(Lnet/minecraft/client/CloudStatus;Lnet/minecraft/client/renderer/oit/OitStage;Lcom/mojang/renderpearl/api/textures/GpuTextureView;Lnet/minecraft/client/renderer/oit/OitRenderPassProvider$Parameters;)V"
		},
		at = @At("HEAD"),
		cancellable = true
	)
	private void disableVoidDimensionClouds(CallbackInfo ci) {
		if (Minecraft.getInstance().level != null
			&& Minecraft.getInstance().level.dimension().equals(ModDimensions.VOID_WORLD)) {
			ci.cancel();
		}
	}
}
