package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.ShatteredSentinelEntity;
import net.balamah.voiddim.VoidDimension;

public class ShatteredSentinelRenderer
	extends MobEntityRenderer<ShatteredSentinelEntity, ShatteredSentinelRenderState, ShatteredSentinelModel> 
{
	public ShatteredSentinelRenderer(EntityRendererFactory.Context context) {
		super(context, new ShatteredSentinelModel(context.getPart(ShatteredSentinelModel.SHATTERED_SENTINEL)), 0.75f);

		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/shattered_sentinel_eye.png")
		);
	}

	@Override
	public ShatteredSentinelRenderState createRenderState() {
		return new ShatteredSentinelRenderState();
	}

	@Override
	public Identifier getTexture(ShatteredSentinelRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/shattered_sentinel.png");
	}

	@Override
	public void render(
		ShatteredSentinelRenderState state, MatrixStack matrixStack,
		OrderedRenderCommandQueue orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
        if (state.baby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

		super.render(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

	@Override
	public void updateRenderState(
		ShatteredSentinelEntity entity, ShatteredSentinelRenderState renderState, float f
	) {
		super.updateRenderState(entity, renderState, f);

		renderState.idleAnimationState.copyFrom(entity.idleAnimationState);
		renderState.attackAnimationState.copyFrom(entity.attackAnimationState);
		renderState.stonesFloatAnimationState.copyFrom(entity.stonesFloatAnimationState);
	}
}
