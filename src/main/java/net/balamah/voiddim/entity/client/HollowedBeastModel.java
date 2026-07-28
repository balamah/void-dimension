package net.balamah.voiddim.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.entity.client.base.AbstractWerewolfRenderState;
import net.balamah.voiddim.entity.client.base.AbstractWerewolfModel;
import net.balamah.voiddim.VoidDimension;

public class HollowedBeastModel extends AbstractWerewolfModel<AbstractWerewolfRenderState> {
    public static final ModelLayerLocation HOLLOWED_BEAST =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "hollowed_beast"),
							 "main");
	public HollowedBeastModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 6).addBox(-3.0F, -10.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.2119F, 4.1513F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 12).addBox(-3.0F, -10.0F, -1.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5381F, 2.9013F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 39).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.2881F, -3.3487F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 26).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.2881F, -2.3487F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, -11.0F, -1.0F, 6.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.0381F, 1.4013F, 0.3054F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -10.5F, -1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -2.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.75F, -2.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -5.0F));

		PartDefinition jaw = mouth.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 1.0F));

		PartDefinition cube_r7 = jaw.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.25F, -0.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition jaw_teeth = jaw.addOrReplaceChild("jaw_teeth", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 2.0F));

		PartDefinition cube_r8 = jaw_teeth.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 24).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
		.texOffs(28, 10).addBox(-1.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
		.texOffs(24, 10).addBox(-3.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.0F, -16.75F, -5.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition upper_mouth = mouth.addOrReplaceChild("upper_mouth", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -0.75F, 0.5F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r9 = upper_mouth.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 19).addBox(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -0.5F, -0.3054F, 0.0F, 0.0F));

		PartDefinition upper_teeth = upper_mouth.addOrReplaceChild("upper_teeth", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, 1.75F));

		PartDefinition cube_r10 = upper_teeth.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(22, 41).addBox(0.0F, -1.1041F, -1.1761F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
		.texOffs(10, 39).addBox(-1.5F, -1.1041F, -1.1761F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
		.texOffs(38, 36).addBox(-3.0F, -1.1041F, -1.1761F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.0F, 16.75F, -5.5F, -0.3054F, 0.0F, 0.0F));

		PartDefinition horns = body.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(0.0F, 6.7119F, -3.5987F));

		PartDefinition cube_r11 = horns.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(16, 36).addBox(0.25F, -1.0F, -1.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -10.25F, 4.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r12 = horns.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(10, 41).addBox(0.25F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -13.25F, 6.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r13 = horns.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(40, 6).addBox(0.25F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -15.0F, 6.75F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r14 = horns.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(42, 0).addBox(0.25F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -16.75F, 6.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(18, 41).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 2.0F));

		PartDefinition front_paws = modelPartData.addOrReplaceChild("front_paws", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 8.0F, -1.25F, 0.0873F, 0.0F, 0.0F));

		PartDefinition right_front_paw = front_paws.addOrReplaceChild("right_front_paw", CubeListBuilder.create(), PartPose.offset(-3.0F, -1.25F, 0.5F));

		PartDefinition right_shoulder_bicep = right_front_paw.addOrReplaceChild("right_shoulder_bicep", CubeListBuilder.create(), PartPose.offset(-1.0F, 5.0F, 2.0F));

		PartDefinition cube_r15 = right_shoulder_bicep.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(26, 26).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.25F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r16 = right_shoulder_bicep.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(38, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition right_elbow = right_front_paw.addOrReplaceChild("right_elbow", CubeListBuilder.create(), PartPose.offset(-1.0F, 6.0F, 2.0F));

		PartDefinition cube_r17 = right_elbow.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 25).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 1.75F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition left_front_paw = front_paws.addOrReplaceChild("left_front_paw", CubeListBuilder.create(), PartPose.offset(3.0F, -1.25F, 0.5F));

		PartDefinition left_shoulder_bicep = left_front_paw.addOrReplaceChild("left_shoulder_bicep", CubeListBuilder.create(), PartPose.offset(1.0F, 4.5F, 2.0F));

		PartDefinition cube_r18 = left_shoulder_bicep.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(26, 32).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.75F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r19 = left_shoulder_bicep.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(34, 38).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition left_elbow = left_front_paw.addOrReplaceChild("left_elbow", CubeListBuilder.create(), PartPose.offset(2.0F, 6.0F, 2.0F));

		PartDefinition cube_r20 = left_elbow.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(8, 25).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.0F, 1.75F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition back_paws = modelPartData.addOrReplaceChild("back_paws", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, 4.0F));

		PartDefinition right_back_paw = back_paws.addOrReplaceChild("right_back_paw", CubeListBuilder.create().texOffs(38, 24).addBox(-5.0F, -9.0F, 4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, -5.0F));

		PartDefinition right_calf = right_back_paw.addOrReplaceChild("right_calf", CubeListBuilder.create().texOffs(34, 10).addBox(-5.0F, -5.0F, 4.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_back_paw = back_paws.addOrReplaceChild("left_back_paw", CubeListBuilder.create().texOffs(26, 38).addBox(3.0F, -9.0F, 4.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, -5.0F));

		PartDefinition left_calf = left_back_paw.addOrReplaceChild("left_calf", CubeListBuilder.create().texOffs(34, 17).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, 4.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}
}
