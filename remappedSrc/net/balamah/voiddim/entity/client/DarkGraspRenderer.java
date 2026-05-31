package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.DarkGraspEntity;
import net.minecraft.client.model.effects.EvokerFangsModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EvokerFangsRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.RotationAxis;
import net.balamah.voiddim.VoidDimension;

public class DarkGraspRenderer extends EntityRenderer<DarkGraspEntity, EvokerFangsRenderState> {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
		VoidDimension.MOD_ID, "textures/entity/dark_grasp.png"
	);

	private final EvokerFangsModel model;

	public DarkGraspRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new EvokerFangsModel(context.getPart(EntityModelLayers.EVOKER_FANGS));
	}

	@Override
	public void submit(
		EvokerFangsRenderState evokerFangsEntityRenderState,
		MatrixStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		float f = evokerFangsEntityRenderState.biteProgress;
		if (f != 0.0F) {
			matrixStack.push();
			matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F - evokerFangsEntityRenderState.yRot));
			matrixStack.scale(-1.0F, -1.0F, 1.0F);
			matrixStack.translate(0.0F, -1.501F, 0.0F);
			orderedRenderCommandQueue.submitModel(
				this.model,
				evokerFangsEntityRenderState,
				matrixStack,
				this.model.renderType(TEXTURE),
				evokerFangsEntityRenderState.lightCoords,
				OverlayTexture.DEFAULT_UV,
				evokerFangsEntityRenderState.outlineColor,
				null
			);
			matrixStack.pop();
		}

		super.submit(evokerFangsEntityRenderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

	@Override
	public EvokerFangsRenderState createRenderState() {
		return new EvokerFangsRenderState();
	}

	@Override
	public void extractRenderState(
		DarkGraspEntity evokerFangsEntity,
		EvokerFangsRenderState evokerFangsEntityRenderState,
		float f
	) {
		super.extractRenderState(evokerFangsEntity, evokerFangsEntityRenderState, f);
		evokerFangsEntityRenderState.yRot = evokerFangsEntity.getYaw();
		evokerFangsEntityRenderState.biteProgress = evokerFangsEntity.getAnimationProgress(f);
	}
}
