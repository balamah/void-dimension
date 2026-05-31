package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.ConsumedSoulEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.RotationAxis;

public class ConsumedSoulRenderer
	extends EntityRenderer<ConsumedSoulEntity, ConsumedSoulRenderState>
{
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
		VoidDimension.MOD_ID, "textures/entity/consumed_soul.png"
	);

	private final ConsumedSoulModel model;

	public ConsumedSoulRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new ConsumedSoulModel(context.getPart(ConsumedSoulModel.CONSUMED_SOUL));
	}

	// TODO: Rotate the soul
	@Override
	public void submit(
		ConsumedSoulRenderState state,
		MatrixStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.push();

		matrixStack.translate(0.0f, 1.5f, 0.0f);

		// Apply entity rotation
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-state.yaw));
		matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(state.pitch));

		// Keep your existing flip if needed
		matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180f));

		orderedRenderCommandQueue.submitModel(
			this.model,
			state,
			matrixStack,
			this.model.getLayer(TEXTURE),
			state.lightCoords,
			OverlayTexture.DEFAULT_UV,
			state.outlineColor,
			null
		);

		matrixStack.pop();
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
