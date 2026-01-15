package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.TntMinecartEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.MathHelper;

import net.balamah.voiddim.entity.custom.BedrockBombEntity;
import net.balamah.voiddim.block.ModBlocks;

// FIXME: Texture of tnt
public class BedrockBombRenderer
	extends EntityRenderer<BedrockBombEntity, BedrockBombRenderState>
{
	public BedrockBombRenderer(EntityRendererFactory.Context context) {
		super(context);

		this.shadowRadius = 0.5F;
	}

	public void render(
		BedrockBombRenderState tntEntityRenderState,
		MatrixStack matrixStack,
		OrderedRenderCommandQueue orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.push();
		matrixStack.translate(0.0F, 0.5F, 0.0F);
		float f = tntEntityRenderState.fuse;
		if (tntEntityRenderState.fuse < 10.0F) {
			float g = 1.0F - tntEntityRenderState.fuse / 10.0F;
			g = MathHelper.clamp(g, 0.0F, 1.0F);
			g *= g;
			g *= g;
			float h = 1.0F + g * 0.3F;
			matrixStack.scale(h, h, h);
		}

		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
		matrixStack.translate(-0.5F, -0.5F, 0.5F);
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));

		if (tntEntityRenderState.blockState != null) {
			TntMinecartEntityRenderer.renderFlashingBlock(
				tntEntityRenderState.blockState, 
				matrixStack, 
				orderedRenderCommandQueue, 
				tntEntityRenderState.light, 
				(int)f / 5 % 2 == 0, 
				tntEntityRenderState.outlineColor
			);
		}

		matrixStack.pop();
		super.render(
			tntEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState
		);
	}

	public BedrockBombRenderState createRenderState() {
		return new BedrockBombRenderState();
	}

	public void updateRenderState(
		BedrockBombEntity bedrockBomb, BedrockBombRenderState bedrockBombRenderState, float f
	) {
		super.updateRenderState(bedrockBomb, bedrockBombRenderState, f);

		bedrockBombRenderState.fuse = bedrockBomb.getFuse() - f + 1.0F;
		bedrockBombRenderState.blockState = ModBlocks.BEDROCK_BOMB.getDefaultState();
	}
}
