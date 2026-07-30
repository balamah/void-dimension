package net.balamah.voiddim.mixin;

import net.balamah.voiddim.world.dimension.ModDimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.CloudStatus;
import net.minecraft.world.phys.Vec3;
import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Inject(method = "addCloudsPass", at = @At("HEAD"), cancellable = true)
	private void disableVoidDimensionClouds(
		FrameGraphBuilder builder, CloudStatus cloudStatus, Vec3 cameraPos, long gameTime,
		float tickDelta, int cloudColor, float cloudHeight, int cloudFogColor,
		CallbackInfo ci
	) {
		if (Minecraft.getInstance().level != null && Minecraft.getInstance().level.dimension().equals(ModDimensions.VOID_WORLD)) {
			ci.cancel();
		}
	}
}
