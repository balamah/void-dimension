package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.ConsumedSoulEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class ConsumedSoulRenderer
	extends EntityRenderer<ConsumedSoulEntity, ConsumedSoulRenderState>
{
	private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
		VoidDimension.MOD_ID, "textures/entity/consumed_soul.png"
	);

	private final ConsumedSoulModel model;

	public ConsumedSoulRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new ConsumedSoulModel(context.bakeLayer(ConsumedSoulModel.CONSUMED_SOUL));
	}

	// TODO: Rotate the soul
	@Override
	public void submit(
		ConsumedSoulRenderState state,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.pushPose();

		matrixStack.translate(0f, 0f + 1.5, 0f);

		matrixStack.mulPose(Axis.ZP.rotationDegrees(180f));

		orderedRenderCommandQueue.submitModel(
			this.model,
			state,
			matrixStack,
			this.model.renderType(TEXTURE),
			state.lightCoords,
			OverlayTexture.NO_OVERLAY,
			state.outlineColor,
			null
		);

		matrixStack.popPose();
		super.submit(state, matrixStack, orderedRenderCommandQueue, cameraRenderState);
	}

	@Override
	public ConsumedSoulRenderState createRenderState() {
		return new ConsumedSoulRenderState();
	}

	@Override
	public void extractRenderState(
		ConsumedSoulEntity entity, ConsumedSoulRenderState renderState, float f
	) {
		super.extractRenderState(entity, renderState, f);
		renderState.yaw = entity.getYRot(f);
		renderState.pitch = entity.getXRot(f);

		renderState.x = entity.getX();
		renderState.y = entity.getY();
		renderState.z = entity.getZ();
	}
}
