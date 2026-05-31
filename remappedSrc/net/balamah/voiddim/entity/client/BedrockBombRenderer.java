package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.BedrockBombEntity;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.balamah.voiddim.block.ModBlocks;

// FIXME: Texture of tnt
public class BedrockBombRenderer
	extends EntityRenderer<BedrockBombEntity, BedrockBombRenderState>
{
	private final BlockModelResolver blockModelResolver;

	public BedrockBombRenderer(EntityRendererFactory.Context context) {
		super(context);

		this.shadowRadius = 0.5F;
		this.blockModelResolver = context.getBlockModelResolver();
	}

	@Override
	public void submit(
		BedrockBombRenderState tntEntityRenderState,
		MatrixStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.push();
		matrixStack.translate(0.0F, 0.5F, 0.0F);
		float f = tntEntityRenderState.fuseRemainingInTicks;
		if (tntEntityRenderState.fuseRemainingInTicks < 10.0F) {
			float g = 1.0F - tntEntityRenderState.fuseRemainingInTicks / 10.0F;
			g = MathHelper.clamp(g, 0.0F, 1.0F);
			g *= g;
			g *= g;
			float h = 1.0F + g * 0.3F;
			matrixStack.scale(h, h, h);
		}

		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
		matrixStack.translate(-0.5F, -0.5F, 0.5F);
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));

		if (!tntEntityRenderState.blockState.isEmpty()) {
			TntMinecartEntityRenderer.submitWhiteSolidBlock(
				tntEntityRenderState.blockState,
				matrixStack,
				orderedRenderCommandQueue,
				tntEntityRenderState.lightCoords,
				(int)f / 5 % 2 == 0,
				tntEntityRenderState.outlineColor
			);
		}

		matrixStack.pop();
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
			ModBlocks.BEDROCK_BOMB.getDefaultState(),
			TntRenderer.BLOCK_DISPLAY_CONTEXT
		);
	}
}
