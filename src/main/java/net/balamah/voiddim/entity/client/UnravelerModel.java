package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
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
import net.balamah.voiddim.VoidDimension;

public class UnravelerModel extends EntityModel<UnravelerRenderState> {
	public static final ModelLayerLocation UNRAVELER =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "unraveler"),
							 "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart chains;
	private final ModelPart chain_1;
	private final ModelPart chain_2;
	private final ModelPart torso_back;
	private final ModelPart torso_aa;
	private final ModelPart claws;
	private final ModelPart claws_right;
	private final ModelPart claw_right_1;
	private final ModelPart claw_right_1_end;
	private final ModelPart claw_right_1_tip;
	private final ModelPart claw_right_2;
	private final ModelPart claw_right_2_end;
	private final ModelPart claws_left;
	private final ModelPart claw_left_1;
	private final ModelPart claw_left_1_end;
	private final ModelPart claw_left_1_tip;
	private final ModelPart claw_left_2;
	private final ModelPart claw_left_2_end;
	private final ModelPart claw_left_2_tip;
	private final ModelPart claw_left_3;
	private final ModelPart claw_left_3_end1;
	private final ModelPart claw_left_3_end1_tip;
	private final ModelPart claw_left_3_end2;
	private final ModelPart claw_left_3_end2_tip;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart right_lower_leg;
	private final ModelPart left_leg;
	private final ModelPart left_lower_leg;

	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation terrainShift1Animation;
	private final KeyframeAnimation terrainShift2Animation;
	private final KeyframeAnimation attack1Animation;
	private final KeyframeAnimation attack2Animation;
	private final KeyframeAnimation attack3Animation;
	private final KeyframeAnimation attack4Animation;

	public UnravelerModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.torso = this.body.getChild("torso");
		this.chains = this.torso.getChild("chains");
		this.chain_1 = this.chains.getChild("chain_1");
		this.chain_2 = this.chains.getChild("chain_2");
		this.torso_back = this.torso.getChild("torso_back");
		this.torso_aa = this.torso.getChild("torso_aa");
		this.claws = this.body.getChild("claws");
		this.claws_right = this.claws.getChild("claws_right");
		this.claw_right_1 = this.claws_right.getChild("claw_right_1");
		this.claw_right_1_end = this.claw_right_1.getChild("claw_right_1_end");
		this.claw_right_1_tip = this.claw_right_1_end.getChild("claw_right_1_tip");
		this.claw_right_2 = this.claws_right.getChild("claw_right_2");
		this.claw_right_2_end = this.claw_right_2.getChild("claw_right_2_end");
		this.claws_left = this.claws.getChild("claws_left");
		this.claw_left_1 = this.claws_left.getChild("claw_left_1");
		this.claw_left_1_end = this.claw_left_1.getChild("claw_left_1_end");
		this.claw_left_1_tip = this.claw_left_1_end.getChild("claw_left_1_tip");
		this.claw_left_2 = this.claws_left.getChild("claw_left_2");
		this.claw_left_2_end = this.claw_left_2.getChild("claw_left_2_end");
		this.claw_left_2_tip = this.claw_left_2_end.getChild("claw_left_2_tip");
		this.claw_left_3 = this.claws_left.getChild("claw_left_3");
		this.claw_left_3_end1 = this.claw_left_3.getChild("claw_left_3_end1");
		this.claw_left_3_end1_tip = this.claw_left_3_end1.getChild("claw_left_3_end1_tip");
		this.claw_left_3_end2 = this.claw_left_3.getChild("claw_left_3_end2");
		this.claw_left_3_end2_tip = this.claw_left_3_end2.getChild("claw_left_3_end2_tip");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.right_lower_leg = this.right_leg.getChild("right_lower_leg");
		this.left_leg = this.legs.getChild("left_leg");
		this.left_lower_leg = this.left_leg.getChild("left_lower_leg");

		this.walkAnimation = UnravelerAnimations.WALK.bake(root);
		this.terrainShift1Animation = UnravelerAnimations.TERRAIN_SHIFT_1.bake(root);
		this.terrainShift2Animation = UnravelerAnimations.TERRAIN_SHIFT_2.bake(root);
		this.attack1Animation = UnravelerAnimations.ATTACK_1.bake(root);
		this.attack2Animation = UnravelerAnimations.ATTACK_2.bake(root);
		this.attack3Animation = UnravelerAnimations.ATTACK_3.bake(root);
		this.attack4Animation = UnravelerAnimations.ATTACK_4.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -26.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 55).addBox(-6.75F, 7.75F, -6.25F, 14.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 43).addBox(-7.75F, 3.75F, -7.25F, 16.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(48, 43).addBox(-7.75F, -8.25F, -7.25F, 16.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-9.75F, -4.25F, -6.25F, 20.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(86, 18).addBox(-9.75F, -4.25F, -7.25F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 86).addBox(3.25F, -4.25F, -7.25F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 55).addBox(-5.75F, -10.25F, -7.25F, 12.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.75F, -11.25F, -6.25F, 10.0F, 17.0F, 11.0F, new CubeDeformation(0.01F))
		.texOffs(80, 55).addBox(-1.75F, -11.25F, -7.25F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.01F))
		.texOffs(96, 71).addBox(-1.25F, -2.75F, -8.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 0.75F, 6.25F));

		PartDefinition chains = torso.addOrReplaceChild("chains", CubeListBuilder.create(), PartPose.offset(0.25F, 25.25F, -6.25F));

		PartDefinition chain_1 = chains.addOrReplaceChild("chain_1", CubeListBuilder.create(), PartPose.offset(6.0F, -30.0F, 11.0F));

		PartDefinition cube_r1 = chain_1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 99).addBox(-1.0408F, -5.6194F, -0.0286F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.25F, -7.5F, -2.5F, 1.2243F, 0.0302F, 0.6955F));

		PartDefinition cube_r2 = chain_1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(6, 98).addBox(-1.0408F, -5.6194F, -0.0286F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(3.5F, -3.75F, 0.75F, 0.5698F, 0.0302F, 0.6955F));

		PartDefinition cube_r3 = chain_1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 98).addBox(-1.0F, -4.0F, 0.0F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, -0.5F, 0.25F, -0.1719F, 0.0302F, 0.6955F));

		PartDefinition chain_2 = chains.addOrReplaceChild("chain_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.75F, -30.0F, 11.0F, -0.4784F, -0.0403F, 0.6207F));

		PartDefinition cube_r4 = chain_2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(40, 97).addBox(-1.9592F, -6.6194F, -0.0286F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-7.75F, -6.75F, -2.25F, 0.6844F, 0.3168F, -1.1488F));

		PartDefinition cube_r5 = chain_2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(94, 97).addBox(-1.9592F, -6.6194F, -0.0286F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-3.5F, -3.75F, 0.75F, 0.5645F, 0.0874F, -0.8795F));

		PartDefinition cube_r6 = chain_2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(52, 99).addBox(-2.0F, -4.0F, 0.0F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -0.5F, 0.25F, -0.1719F, -0.0302F, -0.6955F));

		PartDefinition torso_back = torso.addOrReplaceChild("torso_back", CubeListBuilder.create().texOffs(54, 27).addBox(-8.0F, -5.0F, 4.0F, 18.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 65).addBox(-7.0F, -5.0F, 7.0F, 16.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 71).addBox(-6.0F, 3.0F, 4.0F, 14.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 12).addBox(-6.0F, -9.0F, 4.0F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 86).addBox(-5.0F, -7.0F, 5.0F, 4.0F, 12.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.75F, 0.75F, -3.25F));

		PartDefinition torso_aa = torso.addOrReplaceChild("torso_aa", CubeListBuilder.create().texOffs(72, 81).addBox(-6.0F, -6.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(-0.001F))
		.texOffs(42, 0).addBox(-8.0F, -2.0F, -4.0F, 18.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F))
		.texOffs(42, 9).addBox(-8.0F, 7.0F, -4.0F, 18.0F, 1.0F, 8.0F, new CubeDeformation(-0.01F))
		.texOffs(42, 18).addBox(-6.0F, 11.0F, -4.0F, 14.0F, 1.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.75F, -3.25F, -3.25F));

		PartDefinition claws = body.addOrReplaceChild("claws", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 4.0F));

		PartDefinition claws_right = claws.addOrReplaceChild("claws_right", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -0.5F));

		PartDefinition claw_right_1 = claws_right.addOrReplaceChild("claw_right_1", CubeListBuilder.create().texOffs(96, 31).addBox(1.0F, -2.0F, -2.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 0.25F, 2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r7 = claw_right_1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(54, 38).addBox(0.0F, -3.4142F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, 0.0F, -0.5F, 0.0F, 0.48F, -1.5708F));

		PartDefinition claw_right_1_end = claw_right_1.addOrReplaceChild("claw_right_1_end", CubeListBuilder.create(), PartPose.offsetAndRotation(7.0F, -8.5F, -4.5F, -0.4137F, 0.5315F, -0.8429F));

		PartDefinition cube_r8 = claw_right_1_end.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(70, 65).addBox(-3.2785F, -2.8216F, -1.5776F, 14.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.8998F, 1.7588F, -0.3112F, 0.0F, 0.0F, 1.0472F));

		PartDefinition claw_right_1_tip = claw_right_1_end.addOrReplaceChild("claw_right_1_tip", CubeListBuilder.create(), PartPose.offset(7.1498F, 9.5088F, -0.3112F));

		PartDefinition cube_r9 = claw_right_1_tip.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(80, 90).addBox(-0.0151F, -0.0064F, -1.5883F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.5F, 10.0F, 0.0F, 0.1232F, -0.0709F, 2.0252F));

		PartDefinition cube_r10 = claw_right_1_tip.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(72, 77).addBox(-0.0151F, -1.0064F, -1.5883F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1355F, -0.0427F, 1.8085F));

		PartDefinition claw_right_2 = claws_right.addOrReplaceChild("claw_right_2", CubeListBuilder.create(), PartPose.offsetAndRotation(7.0F, 5.75F, -0.75F, 0.1522F, 0.3351F, 0.1144F));

		PartDefinition cube_r11 = claw_right_2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(80, 61).addBox(-1.0F, -2.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.25F, -1.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition claw_right_2_end = claw_right_2.addOrReplaceChild("claw_right_2_end", CubeListBuilder.create(), PartPose.offsetAndRotation(6.75F, 2.5F, -1.25F, -0.136F, 0.3479F, 0.2437F));

		PartDefinition cube_r12 = claw_right_2_end.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(28, 94).addBox(-0.0151F, -0.0064F, -1.5883F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(5.3998F, 4.7588F, 0.9388F, 0.1089F, -0.1383F, 1.5363F));

		PartDefinition cube_r13 = claw_right_2_end.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(94, 8).addBox(-2.2785F, -1.8216F, -1.5776F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1498F, 2.0088F, 0.9388F, 0.0F, 0.0F, 0.8727F));

		PartDefinition claws_left = claws.addOrReplaceChild("claws_left", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition claw_left_1 = claws_left.addOrReplaceChild("claw_left_1", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -5.75F, -1.0F, 0.0F, -0.3054F, 0.3491F));

		PartDefinition cube_r14 = claw_left_1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(94, 0).addBox(-6.0F, -2.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.0F, 1.75F, 2.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition claw_left_1_end = claw_left_1.addOrReplaceChild("claw_left_1_end", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, -2.5F, 2.5F, -0.2097F, 0.4938F, -0.4517F));

		PartDefinition cube_r15 = claw_left_1_end.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(66, 71).addBox(-10.0F, -3.0F, -2.0F, 12.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition claw_left_1_tip = claw_left_1_end.addOrReplaceChild("claw_left_1_tip", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.75F, 6.75F, -0.75F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r16 = claw_left_1_tip.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(62, 95).addBox(-0.499F, -0.1123F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 7.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition cube_r17 = claw_left_1_tip.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(96, 42).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, 1.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition claw_left_2 = claws_left.addOrReplaceChild("claw_left_2", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -6.0F, -2.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r18 = claw_left_2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(94, 4).addBox(-7.4746F, -0.6488F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-4.0F, 3.25F, -1.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition cube_r19 = claw_left_2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(28, 97).addBox(-3.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 3.25F, -1.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition claw_left_2_end = claw_left_2.addOrReplaceChild("claw_left_2_end", CubeListBuilder.create(), PartPose.offsetAndRotation(-10.75F, -1.25F, -1.5F, 0.0097F, -0.218F, -0.3065F));

		PartDefinition cube_r20 = claw_left_2_end.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(34, 65).addBox(-13.0F, -2.0F, -1.0F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition claw_left_2_tip = claw_left_2_end.addOrReplaceChild("claw_left_2_tip", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.25F, 8.75F, 0.0F, -0.1739F, -0.0151F, -0.0859F));

		PartDefinition cube_r21 = claw_left_2_tip.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(16, 74).addBox(-6.9267F, -0.5487F, -1.0F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.25F, 8.75F, 0.0F, 0.0F, 0.0F, -1.4835F));

		PartDefinition cube_r22 = claw_left_2_tip.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(78, 38).addBox(-8.9267F, -0.5487F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

		PartDefinition claw_left_3 = claws_left.addOrReplaceChild("claw_left_3", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, 6.0F, 1.0F, -0.053F, 0.1664F, -0.3099F));

		PartDefinition cube_r23 = claw_left_3.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(96, 27).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.1793F, -0.5872F, -0.3161F));

		PartDefinition claw_left_3_end1 = claw_left_3.addOrReplaceChild("claw_left_3_end1", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0038F, -0.25F, -2.5755F, -0.0873F, -0.9599F, 0.0F));

		PartDefinition cube_r24 = claw_left_3_end1.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(94, 93).addBox(-4.5473F, -2.2111F, -1.0218F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.8225F, 1.25F, -0.0545F, 0.0284F, -0.0418F, 0.5541F));

		PartDefinition claw_left_3_end1_tip = claw_left_3_end1.addOrReplaceChild("claw_left_3_end1_tip", CubeListBuilder.create(), PartPose.offset(-5.6029F, -1.0F, -0.0858F));

		PartDefinition cube_r25 = claw_left_3_end1_tip.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(96, 52).addBox(-4.7978F, -0.0676F, -1.0436F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-4.25F, 4.0F, 0.0F, 0.0F, 0.0F, -1.5272F));

		PartDefinition cube_r26 = claw_left_3_end1_tip.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(28, 90).addBox(-5.6798F, -1.6184F, -1.0436F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		PartDefinition claw_left_3_end2 = claw_left_3.addOrReplaceChild("claw_left_3_end2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 2.0F, -1.5F, -0.3863F, -0.0557F, -0.0553F));

		PartDefinition cube_r27 = claw_left_3_end2.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(46, 95).addBox(-6.0794F, -0.9255F, -1.0218F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-2.25F, -1.0F, -1.0F, 0.47F, -0.5928F, -0.9292F));

		PartDefinition claw_left_3_end2_tip = claw_left_3_end2.addOrReplaceChild("claw_left_3_end2_tip", CubeListBuilder.create(), PartPose.offset(-5.75F, 3.5F, -4.75F));

		PartDefinition cube_r28 = claw_left_3_end2_tip.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(96, 35).addBox(-4.7978F, -0.0676F, -1.0436F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8933F, 0.2409F, -1.7604F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-4.0F, -17.0F, -2.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition knee_r1 = right_leg.addOrReplaceChild("knee_r1", CubeListBuilder.create().texOffs(90, 81).addBox(-2.0653F, -2.9644F, -1.6805F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 7.5F, -3.75F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r29 = right_leg.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(0, 74).addBox(-1.0653F, -9.2549F, -0.3408F, 5.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.5F, -3.75F, -0.8727F, 0.0F, 0.0F));

		PartDefinition right_lower_leg = right_leg.addOrReplaceChild("right_lower_leg", CubeListBuilder.create().texOffs(16, 77).addBox(-2.4347F, 7.7057F, 0.6875F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0979F, 7.0F, -3.7479F));

		PartDefinition cube_r30 = right_lower_leg.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 87).addBox(-1.0F, -9.0F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.5F, 9.0F, 4.5F, 0.48F, 0.0F, 0.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, 0.5F, 1.25F, 0.0F, -0.1309F, 0.0F));

		PartDefinition knee_r2 = left_leg.addOrReplaceChild("knee_r2", CubeListBuilder.create().texOffs(80, 93).addBox(-1.9347F, -2.9644F, -1.6805F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 7.0F, -4.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r31 = left_leg.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(36, 77).addBox(-1.9347F, -9.2549F, -0.3408F, 5.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 5.0F, -4.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition left_lower_leg = left_leg.addOrReplaceChild("left_lower_leg", CubeListBuilder.create().texOffs(52, 77).addBox(-2.5653F, 7.7073F, 0.3948F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0979F, 6.5F, -3.9979F));

		PartDefinition cube_r32 = left_lower_leg.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(68, 90).addBox(-1.0F, -9.0F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.5F, 9.0F, 4.5F, 0.48F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(UnravelerRenderState state) {
		super.setupAnim(state);

		// Looping walk cycle, driven by how far/fast the entity is moving.
		this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0F, 2.5F);

		this.terrainShift1Animation.apply(state.terrainShift1AnimationState, state.ageInTicks);
		this.terrainShift2Animation.apply(state.terrainShift2AnimationState, state.ageInTicks);
		this.attack1Animation.apply(state.attack1AnimationState, state.ageInTicks);
		this.attack2Animation.apply(state.attack2AnimationState, state.ageInTicks);
		this.attack3Animation.apply(state.attack3AnimationState, state.ageInTicks);
		this.attack4Animation.apply(state.attack4AnimationState, state.ageInTicks);
	}
}
