package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class CorruptedPlayerModel extends BipedEntityModel<CorruptedPlayerRenderState> {
	public static final EntityModelLayer CORRUPTED_PLAYER =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_player"), "main");

	public final ModelPart leftSleeve;
	public final ModelPart rightSleeve;
	public final ModelPart leftPants;
	public final ModelPart rightPants;
	public final ModelPart jacket;

	public CorruptedPlayerModel(ModelPart root) {
		super(root);

		this.leftSleeve = this.leftArm.getChild("left_sleeve");
		this.rightSleeve = this.rightArm.getChild("right_sleeve");
		this.leftPants = this.leftLeg.getChild("left_pants");
		this.rightPants = this.rightLeg.getChild("right_pants");
		this.jacket = this.body.getChild("jacket");
	}

	public static TexturedModelData getTexturedModelData() {
		Dilation deformation = Dilation.NONE;
		ModelData mesh = BipedEntityModel.getModelData(deformation, 0.0F);
		ModelPartData root = mesh.getRoot();
		ModelPartData leftArm = root.addChild(
			"left_arm",
			ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
			ModelTransform.pivot(5.0F, 2.0F, 0.0F)
		);
		ModelPartData rightArm = root.getChild("right_arm");
		ModelPartData leftLeg = root.addChild(
			"left_leg",
			ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
			ModelTransform.pivot(1.9F, 12.0F, 0.0F)
		);
		ModelPartData rightLeg = root.getChild("right_leg");
		ModelPartData body = root.getChild("body");

		leftArm.addChild(
			"left_sleeve",
			ModelPartBuilder.create().uv(48, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.add(0.25F)),
			ModelTransform.NONE
		);
		rightArm.addChild(
			"right_sleeve",
			ModelPartBuilder.create().uv(40, 32).cuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.add(0.25F)),
			ModelTransform.NONE
		);
		leftLeg.addChild(
			"left_pants",
			ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.add(0.25F)),
			ModelTransform.NONE
		);
		rightLeg.addChild(
			"right_pants",
			ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.add(0.25F)),
			ModelTransform.NONE
		);
		body.addChild(
			"jacket",
			ModelPartBuilder.create().uv(16, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation.add(0.25F)),
			ModelTransform.NONE
		);

		return TexturedModelData.of(mesh, 64, 64);
	}

	@Override
	public void setupAnim(CorruptedPlayerRenderState state) {
		this.hat.visible = state.showHat;
		this.jacket.visible = state.showJacket;
		this.leftPants.visible = state.showLeftPants;
		this.rightPants.visible = state.showRightPants;
		this.leftSleeve.visible = state.showLeftSleeve;
		this.rightSleeve.visible = state.showRightSleeve;
		super.setAngles(state);
	}
}
