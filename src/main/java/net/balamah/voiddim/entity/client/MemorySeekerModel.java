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

public class MemorySeekerModel extends EntityModel<MemorySeekerRenderState> {
	public static final ModelLayerLocation MEMORY_SEEKER =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "memory_seeker"),
							 "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart foundation;
	private final ModelPart foundation_end;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart right_elbow_claw_2;
	private final ModelPart right_elbow_claw_1;
	private final ModelPart left_arm;
	private final ModelPart left_horn;
	private final ModelPart left_horn_tip;
	private final ModelPart left_arm_movable;
	private final ModelPart left_elbow;
	private final ModelPart miasma;
	private final ModelPart heads;
	private final ModelPart back;
	private final ModelPart left_head;
	private final ModelPart left_mask;
	private final ModelPart upper_head;
	private final ModelPart upper_mask;
	private final ModelPart right_head;
	private final ModelPart right_mask;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart right_calf;
	private final ModelPart left_leg;
	private final ModelPart left_calf;

	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation attack1Animation;
	private final KeyframeAnimation attack2Animation;

	public MemorySeekerModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.foundation = this.body.getChild("foundation");
		this.foundation_end = this.foundation.getChild("foundation_end");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.right_elbow_claw_2 = this.right_elbow.getChild("right_elbow_claw_2");
		this.right_elbow_claw_1 = this.right_elbow.getChild("right_elbow_claw_1");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_horn = this.left_arm.getChild("left_horn");
		this.left_horn_tip = this.left_horn.getChild("left_horn_tip");
		this.left_arm_movable = this.left_arm.getChild("left_arm_movable");
		this.left_elbow = this.left_arm_movable.getChild("left_elbow");
		this.miasma = this.body.getChild("miasma");
		this.heads = this.body.getChild("heads");
		this.back = this.heads.getChild("back");
		this.left_head = this.heads.getChild("left_head");
		this.left_mask = this.left_head.getChild("left_mask");
		this.upper_head = this.heads.getChild("upper_head");
		this.upper_mask = this.upper_head.getChild("upper_mask");
		this.right_head = this.heads.getChild("right_head");
		this.right_mask = this.right_head.getChild("right_mask");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.right_calf = this.right_leg.getChild("right_calf");
		this.left_leg = this.legs.getChild("left_leg");
		this.left_calf = this.left_leg.getChild("left_calf");

		this.walkAnimation = MemorySeekerAnimations.WALK.bake(root);
		this.attack1Animation = MemorySeekerAnimations.ATTACK_1.bake(root);
		this.attack2Animation = MemorySeekerAnimations.ATTACK_2.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, 1.0F));

		PartDefinition foundation = body.addOrReplaceChild("foundation", CubeListBuilder.create().texOffs(0, 18).addBox(-4.0F, -24.0F, 4.0F, 7.0F, 21.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 4.0F, -6.5F));

		PartDefinition foundation_end = foundation.addOrReplaceChild("foundation_end", CubeListBuilder.create().texOffs(94, 2).addBox(-3.0F, -8.0F, 4.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(52, 94).addBox(1.0F, -6.0F, 4.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 95).addBox(-1.0F, -7.0F, 4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 98).addBox(0.0F, -6.0F, 4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 6).addBox(-2.0F, -8.0F, 9.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 96).addBox(0.0F, -7.0F, 9.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 95).addBox(2.0F, -6.0F, 9.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 98).addBox(1.0F, -6.0F, 9.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition cube_r1 = foundation_end.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 99).addBox(2.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(88, 96).addBox(2.0F, -2.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 4).addBox(1.0F, -3.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, 3.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r2 = foundation_end.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 98).addBox(2.0F, -1.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -5.0F, 3.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r3 = foundation_end.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(94, 56).addBox(1.0F, -1.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -7.0F, 3.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = foundation_end.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(26, 90).addBox(2.0F, -1.0F, -1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 96).addBox(2.0F, -2.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -5.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, -6.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(88, 88).addBox(-10.75F, -33.0F, 6.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = right_arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(72, 88).addBox(-5.0F, -2.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-11.75F, -31.25F, 7.0F, 0.0F, 0.0F, 0.5672F));

		PartDefinition right_elbow = right_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create(), PartPose.offsetAndRotation(-16.5F, -33.25F, 7.0F, -0.3911F, -0.0403F, -0.3829F));

		PartDefinition cube_r6 = right_elbow.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(38, 0).addBox(-12.0F, -1.0F, -1.0F, 20.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3942F, 1.4396F, 0.0F, 0.0F, 0.0F, -1.0036F));

		PartDefinition cube_r7 = right_elbow.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(86, 67).addBox(-6.9331F, -1.0206F, 0.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.1442F, 18.6896F, 0.5F, 0.247F, 0.3085F, -1.0086F));

		PartDefinition cube_r8 = right_elbow.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(22, 97).addBox(-2.9331F, -0.0206F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.8942F, 12.1896F, -1.75F, 0.0181F, 0.3923F, -1.6545F));

		PartDefinition cube_r9 = right_elbow.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(46, 86).addBox(-7.9331F, -1.0206F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.8942F, 13.6896F, -1.0F, 0.0181F, 0.3923F, -1.6545F));

		PartDefinition cube_r10 = right_elbow.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(94, 62).addBox(-2.9331F, -0.0206F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-5.8942F, 11.6896F, -0.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r11 = right_elbow.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(94, 58).addBox(-2.9331F, -0.0206F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8942F, 14.4396F, -0.5F, 0.0F, 0.0F, -0.48F));

		PartDefinition cube_r12 = right_elbow.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(94, 60).addBox(-3.9331F, -0.0206F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6442F, 6.4396F, -0.75F, 0.0F, 0.0F, -2.0508F));

		PartDefinition right_elbow_claw_2 = right_elbow.addOrReplaceChild("right_elbow_claw_2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.8558F, -5.5605F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r13 = right_elbow_claw_2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(96, 20).addBox(-3.0675F, -0.2315F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 2.0F, -0.5F, 0.0F, -0.2618F, -1.0472F));

		PartDefinition cube_r14 = right_elbow_claw_2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(94, 8).addBox(-2.0675F, -1.2315F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.75F, 0.0F, -0.2182F, 0.0F, -0.6545F));

		PartDefinition cube_r15 = right_elbow_claw_2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(12, 90).addBox(-4.0675F, -1.2315F, -1.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition right_elbow_claw_1 = right_elbow.addOrReplaceChild("right_elbow_claw_1", CubeListBuilder.create().texOffs(96, 24).addBox(-3.0675F, -0.2315F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.8942F, 0.4395F, -0.5F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r16 = right_elbow_claw_1.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(96, 26).addBox(-3.0675F, -0.2315F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r17 = right_elbow_claw_1.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(96, 22).addBox(-3.0675F, -0.2315F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(42, 90).addBox(5.75F, -33.0F, 6.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_horn = left_arm.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(8, 96).addBox(-4.5F, -1.0F, 0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(13.75F, -36.0F, 6.5F));

		PartDefinition cube_r18 = left_horn.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(76, 94).addBox(-1.0F, -2.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-3.0F, 1.0F, 0.5F, 0.0F, 0.0F, 0.1309F));

		PartDefinition left_horn_tip = left_horn.addOrReplaceChild("left_horn_tip", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition cube_r19 = left_horn_tip.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(22, 94).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -3.75F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r20 = left_horn_tip.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(94, 12).addBox(-2.0F, -2.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -0.75F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition left_arm_movable = left_arm.addOrReplaceChild("left_arm_movable", CubeListBuilder.create(), PartPose.offset(10.0F, -32.0F, 7.0F));

		PartDefinition cube_r21 = left_arm_movable.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(94, 18).addBox(-3.25F, -0.9728F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.25F, 11.75F, 0.5F, -0.0021F, 0.0607F, 0.8685F));

		PartDefinition cube_r22 = left_arm_movable.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(88, 92).addBox(-1.75F, -0.9728F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, 12.5F, 1.0F, -0.1083F, 0.2089F, -1.1644F));

		PartDefinition cube_r23 = left_arm_movable.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(94, 0).addBox(-1.75F, -0.9728F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, 11.75F, 0.5F, 0.0137F, -0.0591F, -1.1308F));

		PartDefinition cube_r24 = left_arm_movable.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 94).addBox(-1.75F, -0.9728F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, 12.5F, -0.25F, -0.1655F, -0.874F, -1.0145F));

		PartDefinition cube_r25 = left_arm_movable.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(94, 16).addBox(-3.25F, -0.9728F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.25F, 12.5F, -1.25F, -0.139F, 0.1059F, 0.8653F));

		PartDefinition cube_r26 = left_arm_movable.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(76, 92).addBox(-1.75F, -0.9728F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, 12.5F, -1.25F, -0.1069F, -0.1382F, -1.1271F));

		PartDefinition cube_r27 = left_arm_movable.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(46, 69).addBox(-1.25F, -1.0F, -1.0F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition left_elbow = left_arm_movable.addOrReplaceChild("left_elbow", CubeListBuilder.create(), PartPose.offset(5.75F, 11.5F, -1.25F));

		PartDefinition cube_r28 = left_elbow.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(94, 64).addBox(-2.25F, -0.9728F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 13.5F, -1.5F, 0.0674F, 0.1611F, 2.1871F));

		PartDefinition cube_r29 = left_elbow.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(12, 94).addBox(-2.25F, -1.9728F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 11.25F, -1.5F, 0.0381F, 0.1704F, 2.0104F));

		PartDefinition cube_r30 = left_elbow.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(46, 73).addBox(-2.25F, -1.9728F, -1.0F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.25F, 0.0F, 0.1745F, 1.789F));

		PartDefinition miasma = body.addOrReplaceChild("miasma", CubeListBuilder.create().texOffs(88, 72).addBox(-2.5F, -14.0F, -0.25F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.0F, -2.0F));

		PartDefinition cube_r31 = miasma.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(82, 10).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 2.0F, 0.0F, 0.829F, 0.0F));

		PartDefinition cube_r32 = miasma.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(82, 0).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 2.0F, 0.0F, -0.829F, 0.0F));

		PartDefinition cube_r33 = miasma.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(30, 87).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 4.25F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r34 = miasma.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(46, 60).addBox(-1.0F, 0.0F, 0.0F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, -14.0F, 1.0F, 0.0F, -1.5708F, -0.0436F));

		PartDefinition cube_r35 = miasma.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(88, 80).addBox(-3.0F, 0.0F, 0.0F, 5.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -14.0F, 1.75F, 0.0F, 1.5708F, 0.1309F));

		PartDefinition heads = body.addOrReplaceChild("heads", CubeListBuilder.create(), PartPose.offset(-3.75F, -6.75F, 8.0F));

		PartDefinition back = heads.addOrReplaceChild("back", CubeListBuilder.create().texOffs(80, 21).addBox(6.25F, -12.25F, -6.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.01F))
		.texOffs(38, 4).addBox(6.25F, -15.25F, -9.0F, 2.0F, 8.0F, 6.0F, new CubeDeformation(-0.01F))
		.texOffs(54, 57).addBox(-2.0F, -16.25F, -9.75F, 12.0F, 8.0F, 4.0F, new CubeDeformation(-0.01F))
		.texOffs(0, 0).addBox(-2.75F, -10.25F, -10.5F, 13.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(78, 69).addBox(-1.25F, -17.25F, -7.75F, 10.0F, 1.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r36 = back.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(54, 40).addBox(-4.0F, -9.0F, -4.0F, 9.0F, 12.0F, 5.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(7.25F, 0.0F, -1.0F, 0.0F, 0.5672F, 0.0F));

		PartDefinition cube_r37 = back.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(54, 21).addBox(-5.0F, -10.0F, -5.0F, 7.0F, 13.0F, 6.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(5.0F, -10.0F, -1.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r38 = back.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(26, 39).addBox(-5.0F, -12.0F, -5.0F, 8.0F, 15.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3054F, 0.0F));

		PartDefinition left_head = heads.addOrReplaceChild("left_head", CubeListBuilder.create().texOffs(26, 18).addBox(-5.0F, -12.0F, -4.0F, 8.0F, 15.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(46, 79).addBox(3.0F, 1.0F, -4.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(26, 60).addBox(3.0F, -10.0F, -4.0F, 4.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -12.0F, 0.0F, 0.3054F, 0.0F));

		PartDefinition left_mask = left_head.addOrReplaceChild("left_mask", CubeListBuilder.create().texOffs(56, 90).addBox(-1.0F, 0.0F, -4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 37).addBox(1.5F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 54).addBox(2.5F, -5.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 87).addBox(-2.5F, -5.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 29).addBox(-1.5F, -5.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 31).addBox(-1.5F, -6.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 98).addBox(0.0F, -4.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(98, 74).addBox(1.5F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 52).addBox(-0.5F, -2.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(98, 72).addBox(-1.5F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 40).addBox(-2.5F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(98, 76).addBox(-1.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
		.texOffs(98, 78).addBox(1.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition upper_head = heads.addOrReplaceChild("upper_head", CubeListBuilder.create().texOffs(0, 45).addBox(-5.0F, -10.0F, -4.0F, 7.0F, 13.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(2.0F, -10.0F, -4.0F, 1.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(14, 77).addBox(-7.0F, -6.0F, -3.75F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(30, 77).addBox(-9.0F, -3.0F, -3.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(80, 29).addBox(5.0F, -3.0F, -2.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(82, 38).addBox(-6.0F, -8.0F, -3.75F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(72, 73).addBox(3.0F, -6.0F, -3.75F, 2.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(82, 46).addBox(3.0F, -8.0F, -3.75F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -9.0F, -11.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition upper_mask = upper_head.addOrReplaceChild("upper_mask", CubeListBuilder.create().texOffs(94, 94).addBox(-1.0F, 0.0F, -4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 46).addBox(1.5F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 97).addBox(2.5F, -5.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 98).addBox(-2.5F, -5.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 94).addBox(-1.5F, -5.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 54).addBox(-1.5F, -6.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(98, 86).addBox(0.0F, -4.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(88, 98).addBox(1.5F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 96).addBox(-0.5F, -2.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(92, 98).addBox(-1.5F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 49).addBox(-2.5F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 98).addBox(-1.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
		.texOffs(20, 99).addBox(1.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offset(-1.5F, -2.5F, -1.0F));

		PartDefinition right_head = heads.addOrReplaceChild("right_head", CubeListBuilder.create().texOffs(54, 4).addBox(-4.0F, -9.0F, -4.0F, 9.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.25F, 0.0F, -11.0F, 0.0F, -0.5672F, 0.0F));

		PartDefinition right_mask = right_head.addOrReplaceChild("right_mask", CubeListBuilder.create().texOffs(86, 94).addBox(-2.0F, 0.0F, -4.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 96).addBox(-3.5F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 96).addBox(-3.5F, -5.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 97).addBox(1.5F, -5.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 33).addBox(-2.5F, -5.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 35).addBox(-2.5F, -6.0F, -4.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 98).addBox(-1.0F, -4.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(98, 80).addBox(-2.5F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(76, 96).addBox(-1.5F, -2.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(98, 82).addBox(0.5F, -1.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(96, 43).addBox(0.5F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(84, 98).addBox(0.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
		.texOffs(98, 84).addBox(-2.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, 2.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.25F, 0.0F, 0.0F, 0.0F, 0.5672F, 0.0F));

		PartDefinition cube_r39 = right_leg.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(56, 92).addBox(-1.0F, -5.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1204F, 7.0F, -9.6518F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r40 = right_leg.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(14, 64).addBox(-1.0F, -9.0F, -1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.1204F, 2.75F, -8.6518F, -1.0908F, 0.0F, 0.0F));

		PartDefinition right_calf = right_leg.addOrReplaceChild("right_calf", CubeListBuilder.create(), PartPose.offset(0.6204F, 7.0F, -9.4018F));

		PartDefinition cube_r41 = right_calf.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(64, 79).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(3.25F, 0.0F, 0.0F, 0.0F, -0.5672F, 0.0F));

		PartDefinition cube_r42 = left_leg.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(66, 92).addBox(-2.0F, -5.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1204F, 7.0F, -9.6518F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r43 = left_leg.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 82).addBox(-2.0F, -9.0F, -1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-0.1204F, 2.75F, -8.6518F, -1.0908F, 0.0F, 0.0F));

		PartDefinition left_calf = left_leg.addOrReplaceChild("left_calf", CubeListBuilder.create(), PartPose.offset(-0.6204F, 7.0F, -10.1518F));

		PartDefinition cube_r44 = left_calf.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(86, 54).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.75F, 0.3491F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(MemorySeekerRenderState state) {
		super.setupAnim(state);

		// Looping walk cycle, driven by how far/fast the entity is moving.
		this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0F, 2.5F);

		this.attack1Animation.apply(state.attack1AnimationState, state.ageInTicks);
		this.attack2Animation.apply(state.attack2AnimationState, state.ageInTicks);
	}
}
