package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;

public class CorruptedPlayerModel extends HumanoidModel<CorruptedPlayerRenderState> {
	public static final ModelLayerLocation CORRUPTED_PLAYER =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_player"), "main");

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

	public static LayerDefinition getTexturedModelData() {
		CubeDeformation deformation = CubeDeformation.NONE;
		MeshDefinition mesh = HumanoidModel.createMesh(deformation, 0.0F);
		PartDefinition root = mesh.getRoot();
		PartDefinition leftArm = root.addOrReplaceChild(
			"left_arm",
			CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
			PartPose.offset(5.0F, 2.0F, 0.0F)
		);
		PartDefinition rightArm = root.getChild("right_arm");
		PartDefinition leftLeg = root.addOrReplaceChild(
			"left_leg",
			CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation),
			PartPose.offset(1.9F, 12.0F, 0.0F)
		);
		PartDefinition rightLeg = root.getChild("right_leg");
		PartDefinition body = root.getChild("body");

		leftArm.addOrReplaceChild(
			"left_sleeve",
			CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.25F)),
			PartPose.ZERO
		);
		rightArm.addOrReplaceChild(
			"right_sleeve",
			CubeListBuilder.create().texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.25F)),
			PartPose.ZERO
		);
		leftLeg.addOrReplaceChild(
			"left_pants",
			CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.25F)),
			PartPose.ZERO
		);
		rightLeg.addOrReplaceChild(
			"right_pants",
			CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, deformation.extend(0.25F)),
			PartPose.ZERO
		);
		body.addOrReplaceChild(
			"jacket",
			CubeListBuilder.create().texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, deformation.extend(0.25F)),
			PartPose.ZERO
		);

		return LayerDefinition.create(mesh, 64, 64);
	}

	@Override
	public void setupAnim(CorruptedPlayerRenderState state) {
		this.hat.visible = state.showHat;
		this.jacket.visible = state.showJacket;
		this.leftPants.visible = state.showLeftPants;
		this.rightPants.visible = state.showRightPants;
		this.leftSleeve.visible = state.showLeftSleeve;
		this.rightSleeve.visible = state.showRightSleeve;
		super.setupAnim(state);
	}
}
