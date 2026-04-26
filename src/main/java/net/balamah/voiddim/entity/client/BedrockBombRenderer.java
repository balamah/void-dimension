package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.BedrockBombEntity;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntRenderer;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.balamah.voiddim.block.ModBlocks;

// FIXME: Texture of tnt
public class BedrockBombRenderer
	extends EntityRenderer<BedrockBombEntity, BedrockBombRenderState>
{
	private final BlockModelResolver blockModelResolver;

	public BedrockBombRenderer(EntityRendererProvider.Context context) {
		super(context);

		this.shadowRadius = 0.5F;
		this.blockModelResolver = context.getBlockModelResolver();
	}

	@Override
	public void submit(
		BedrockBombRenderState tntEntityRenderState,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.pushPose();
		matrixStack.translate(0.0F, 0.5F, 0.0F);
		float f = tntEntityRenderState.fuseRemainingInTicks;
		if (tntEntityRenderState.fuseRemainingInTicks < 10.0F) {
			float g = 1.0F - tntEntityRenderState.fuseRemainingInTicks / 10.0F;
			g = Mth.clamp(g, 0.0F, 1.0F);
			g *= g;
			g *= g;
			float h = 1.0F + g * 0.3F;
			matrixStack.scale(h, h, h);
		}

		matrixStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
		matrixStack.translate(-0.5F, -0.5F, 0.5F);
		matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F));

		if (!tntEntityRenderState.blockState.isEmpty()) {
			TntMinecartRenderer.submitWhiteSolidBlock(
				tntEntityRenderState.blockState,
				matrixStack,
				orderedRenderCommandQueue,
				tntEntityRenderState.lightCoords,
				(int)f / 5 % 2 == 0,
				tntEntityRenderState.outlineColor
			);
		}

		matrixStack.popPose();
		super.submit(
			tntEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState
		);
	}

	public BedrockBombRenderState createRenderState() {
		return new BedrockBombRenderState();
	}

	@Override
	public void extractRenderState(
		BedrockBombEntity bedrockBomb, BedrockBombRenderState bedrockBombRenderState, float f
	) {
		super.extractRenderState(bedrockBomb, bedrockBombRenderState, f);

		bedrockBombRenderState.fuseRemainingInTicks = bedrockBomb.getFuse() - f + 1.0F;
		this.blockModelResolver.update(
			bedrockBombRenderState.blockState,
			ModBlocks.BEDROCK_BOMB.defaultBlockState(),
			TntRenderer.BLOCK_DISPLAY_CONTEXT
		);
	}
}
