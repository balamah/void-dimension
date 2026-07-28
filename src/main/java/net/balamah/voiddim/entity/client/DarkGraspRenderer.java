package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.DarkGraspEntity;
import net.minecraft.client.model.effects.EvokerFangsModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EvokerFangsRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.balamah.voiddim.VoidDimension;

public class DarkGraspRenderer extends EntityRenderer<DarkGraspEntity, EvokerFangsRenderState> {
	private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
		VoidDimension.MOD_ID, "textures/entity/dark_grasp.png"
	);

	private final EvokerFangsModel model;

	public DarkGraspRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new EvokerFangsModel(context.bakeLayer(ModelLayers.EVOKER_FANGS));
	}

	@Override
	public void submit(
		EvokerFangsRenderState evokerFangsEntityRenderState,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		float f = evokerFangsEntityRenderState.biteProgress;
		if (f != 0.0F) {
			matrixStack.pushPose();
			matrixStack.mulPose(Axis.YP.rotationDegrees(90.0F - evokerFangsEntityRenderState.yRot));
			matrixStack.scale(-1.0F, -1.0F, 1.0F);
			matrixStack.translate(0.0F, -1.501F, 0.0F);
			orderedRenderCommandQueue.submitModel(
				this.model,
				evokerFangsEntityRenderState,
				matrixStack,
				this.model.renderType(TEXTURE),
				evokerFangsEntityRenderState.lightCoords,
				OverlayTexture.NO_OVERLAY,
				evokerFangsEntityRenderState.outlineColor,
				null
			);
			matrixStack.popPose();
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
		evokerFangsEntityRenderState.yRot = evokerFangsEntity.getYRot();
		evokerFangsEntityRenderState.biteProgress = evokerFangsEntity.getAnimationProgress(f);
	}
}
