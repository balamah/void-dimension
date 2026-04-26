package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.ShatteredSentinelEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.vertex.PoseStack;
import net.balamah.voiddim.VoidDimension;

public class ShatteredSentinelRenderer
	extends MobRenderer<ShatteredSentinelEntity, ShatteredSentinelRenderState, ShatteredSentinelModel> 
{
	public ShatteredSentinelRenderer(EntityRendererProvider.Context context) {
		super(context, new ShatteredSentinelModel(context.bakeLayer(ShatteredSentinelModel.SHATTERED_SENTINEL)), 0.75f);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/shattered_sentinel_eye.png")
		);
	}

	@Override
	public ShatteredSentinelRenderState createRenderState() {
		return new ShatteredSentinelRenderState();
	}

	@Override
	public Identifier getTextureLocation(ShatteredSentinelRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/shattered_sentinel.png");
	}

	@Override
	public void submit(
		ShatteredSentinelRenderState state,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
        if (state.isBaby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

		super.submit(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

	@Override
	public void extractRenderState(
		ShatteredSentinelEntity entity, ShatteredSentinelRenderState renderState, float f
	) {
		super.extractRenderState(entity, renderState, f);

		renderState.idleAnimationState.copyFrom(entity.idleAnimationState);
		renderState.attackAnimationState.copyFrom(entity.attackAnimationState);
		renderState.stonesFloatAnimationState.copyFrom(entity.stonesFloatAnimationState);
	}
}
