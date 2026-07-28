package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.EyeBrightHeadEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.object.skull.SkullModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

public class EyeBrightHeadRenderer
	extends EntityRenderer<EyeBrightHeadEntity, EyeBrightHeadRenderState>
{
	private final SkullModel model;

	public EyeBrightHeadRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new SkullModel(context.bakeLayer(ModelLayers.WITHER_SKULL));
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		modelPartData.addOrReplaceChild(
			PartNames.HEAD,
			CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
			PartPose.ZERO
		);

		return LayerDefinition.create(modelData, 64, 64);
	}

	protected int getBlockLight(EyeBrightHeadEntity eyeBrightHeadEntity, BlockPos blockPos) {
		return 15;
	}

	@Override
	public void submit(
		EyeBrightHeadRenderState eyeBrightHeadRenderState,
		PoseStack matrixStack,
		SubmitNodeCollector orderedRenderCommandQueue,
		CameraRenderState cameraRenderState
	) {
		matrixStack.pushPose();
		matrixStack.scale(-1.0F, -1.0F, 1.0F);
		orderedRenderCommandQueue.submitModel(
			this.model,
			eyeBrightHeadRenderState.skullState,
			matrixStack,
			this.model.renderType(this.getTexture(eyeBrightHeadRenderState)),
			eyeBrightHeadRenderState.lightCoords,
			OverlayTexture.NO_OVERLAY,
			eyeBrightHeadRenderState.outlineColor,
			null
		);
		matrixStack.popPose();

		super.submit(
			eyeBrightHeadRenderState, matrixStack,
			orderedRenderCommandQueue, cameraRenderState
		);
	}

	private Identifier getTexture(EyeBrightHeadRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/eye_bright_head.png");
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
