package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.EyeBrightHeadEntity;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.object.skull.SkullModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class EyeBrightHeadRenderer
	extends EntityRenderer<EyeBrightHeadEntity, EyeBrightHeadRenderState>
{
	private final SkullModel model;

	public EyeBrightHeadRenderer(EntityRendererFactory.Context context) {
		super(context);
		this.model = new SkullModel(context.getPart(EntityModelLayers.WITHER_SKULL));
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

	@Override
	public void submit(
		EyeBrightHeadRenderState eyeBrightHeadRenderState,
		MatrixStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.push();
		matrixStack.scale(-1.0F, -1.0F, 1.0F);
		orderedRenderCommandQueue.submitModel(
			this.model,
			eyeBrightHeadRenderState.skullState,
			matrixStack,
			this.model.renderType(this.getTexture(eyeBrightHeadRenderState)),
			eyeBrightHeadRenderState.lightCoords,
			OverlayTexture.DEFAULT_UV,
			eyeBrightHeadRenderState.outlineColor,
			null
		);
		matrixStack.pop();

		super.submit(
			eyeBrightHeadRenderState, matrixStack,
			orderedRenderCommandQueue, cameraRenderState
		);
	}

	private ResourceLocation getTexture(EyeBrightHeadRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/eye_bright_head.png");
	}

	@Override
	public EyeBrightHeadRenderState createRenderState() {
		return new EyeBrightHeadRenderState();
	}

	@Override
	public void extractRenderState(
		EyeBrightHeadEntity eyeBrightHeadEntity,
		EyeBrightHeadRenderState eyeBrightHeadRenderState,
		float f
	) {
		super.extractRenderState(eyeBrightHeadEntity, eyeBrightHeadRenderState, f);
		eyeBrightHeadRenderState.skullState.animationPos = 0.0F;
		eyeBrightHeadRenderState.skullState.yRot = eyeBrightHeadEntity.getYRot(f);
		eyeBrightHeadRenderState.skullState.xRot = eyeBrightHeadEntity.getXRot(f);
	}
}
