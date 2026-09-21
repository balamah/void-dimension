package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.VengefulSpiritEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class VengefulSpiritRenderer
	extends EntityRenderer<VengefulSpiritEntity, VengefulSpiritRenderState>
{
	private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
		VoidDimension.MOD_ID, "textures/entity/vengeful_spirit.png"
	);

	private final VengefulSpiritModel model;

	public VengefulSpiritRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new VengefulSpiritModel(
			context.bakeLayer(VengefulSpiritModel.VENGEFUL_SPIRIT)
		);
	}

	@Override
	public void submit(
		VengefulSpiritRenderState state,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.pushPose();

		matrixStack.translate(0.0f, 1.5f, 0.0f);

		// Apply entity rotation
		matrixStack.mulPose(Axis.YP.rotationDegrees(-state.yaw));
		matrixStack.mulPose(Axis.XP.rotationDegrees(state.pitch));

		// Keep your existing flip if needed
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
	public VengefulSpiritRenderState createRenderState() {
		return new VengefulSpiritRenderState();
	}

	@Override
	public void extractRenderState(
		VengefulSpiritEntity entity, VengefulSpiritRenderState renderState, float f
	) {
		super.extractRenderState(entity, renderState, f);

		renderState.yaw = entity.getYRot(f);
		renderState.pitch = entity.getXRot(f);

		renderState.x = entity.getX();
		renderState.y = entity.getY();
		renderState.z = entity.getZ();
	}
}
