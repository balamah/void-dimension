package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.EyeBrightHeadEntity;

public class EyeBrightHeadRenderer
	extends EntityRenderer<EyeBrightHeadEntity, EyeBrightHeadRenderState>
{
	private final SkullEntityModel model;

	public EyeBrightHeadRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new SkullEntityModel(context.getPart(EntityModelLayers.WITHER_SKULL));
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(
			EntityModelPartNames.HEAD,
			ModelPartBuilder.create().uv(0, 35).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
			ModelTransform.NONE
		);

		return TexturedModelData.of(modelData, 64, 64);
	}

	protected int getBlockLight(EyeBrightHeadEntity eyeBrightHeadEntity, BlockPos blockPos) {
		return 15;
	}

	public void render(
		EyeBrightHeadRenderState eyeBrightHeadRenderState,
		MatrixStack matrixStack,
		OrderedRenderCommandQueue orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.push();
		matrixStack.scale(-1.0F, -1.0F, 1.0F);
		orderedRenderCommandQueue.submitModel(
			this.model,
			eyeBrightHeadRenderState.skullState,
			matrixStack,
			this.model.getLayer(this.getTexture(eyeBrightHeadRenderState)),
			eyeBrightHeadRenderState.light,
			OverlayTexture.DEFAULT_UV,
			eyeBrightHeadRenderState.outlineColor,
			null
		);
		matrixStack.pop();

		super.render(
			eyeBrightHeadRenderState, matrixStack,
			orderedRenderCommandQueue, cameraRenderState
		);
	}

	private Identifier getTexture(EyeBrightHeadRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/eye_bright_head.png");
	}

	public EyeBrightHeadRenderState createRenderState() {
		return new EyeBrightHeadRenderState();
	}

	public void updateRenderState(
		EyeBrightHeadEntity eyeBrightHeadEntity,
		EyeBrightHeadRenderState eyeBrightHeadRenderState,
		float f
	) {
		super.updateRenderState(eyeBrightHeadEntity, eyeBrightHeadRenderState, f);
		eyeBrightHeadRenderState.skullState.poweredTicks = 0.0F;
		eyeBrightHeadRenderState.skullState.yaw = eyeBrightHeadEntity.getLerpedYaw(f);
		eyeBrightHeadRenderState.skullState.pitch = eyeBrightHeadEntity.getLerpedPitch(f);
	}
}
