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
	
public class WormOfCorruptionModel extends EntityModel<WormOfCorruptionRenderState> {
    public static final ModelLayerLocation WORM_OF_CORRUPTION =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "worm_of_corruption"),
							 "main");

	private final ModelPart root;
	private final ModelPart worm;
	private final ModelPart head;
	private final ModelPart mouth;
	private final ModelPart upper;
	private final ModelPart upper_teeth;
	private final ModelPart lower;
	private final ModelPart lower_teeth;
	private final ModelPart head_horns;
	private final ModelPart horn_1;
	private final ModelPart horn_2;
	private final ModelPart horn_3;
	private final ModelPart horn_4;
	private final ModelPart body;
	private final ModelPart body_1;
	private final ModelPart body_2;
	private final ModelPart body_3;
	private final ModelPart body_4;
	private final ModelPart body_5;
	private final ModelPart horn_5;
	private final ModelPart miasma;
	private final ModelPart bone;
	private final ModelPart overlay;
	private final ModelPart horns;

	private final KeyframeAnimation idleAnimation;
	private final KeyframeAnimation attackAnimation;
	private final KeyframeAnimation shootAnimation;
	private final KeyframeAnimation digUpAnimation;
	private final KeyframeAnimation digDownAnimation;

	public WormOfCorruptionModel(ModelPart root) {
		super(root);
		
		this.root = root.getChild("root");
		this.worm = this.root.getChild("worm");
		this.head = this.worm.getChild("head");
		this.mouth = this.head.getChild("mouth");
		this.upper = this.mouth.getChild("upper");
		this.upper_teeth = this.upper.getChild("upper_teeth");
		this.lower = this.mouth.getChild("lower");
		this.lower_teeth = this.lower.getChild("lower_teeth");
		this.head_horns = this.head.getChild("head_horns");
		this.horn_1 = this.head_horns.getChild("horn_1");
		this.horn_2 = this.head_horns.getChild("horn_2");
		this.horn_3 = this.head_horns.getChild("horn_3");
		this.horn_4 = this.head_horns.getChild("horn_4");
		this.body = this.worm.getChild("body");
		this.body_1 = this.body.getChild("body_1");
		this.body_2 = this.body.getChild("body_2");
		this.body_3 = this.body.getChild("body_3");
		this.body_4 = this.body.getChild("body_4");
		this.body_5 = this.body.getChild("body_5");
		this.horn_5 = this.body_5.getChild("horn_5");
		this.miasma = this.root.getChild("miasma");
		this.bone = this.miasma.getChild("bone");
		this.overlay = this.miasma.getChild("overlay");
		this.horns = this.root.getChild("horns");

		this.idleAnimation = WormOfCorruptionAnimations.IDLE.bake(root);
		this.attackAnimation = WormOfCorruptionAnimations.ATTACK.bake(root);
		this.shootAnimation = WormOfCorruptionAnimations.SHOOT.bake(root);
		this.digUpAnimation = WormOfCorruptionAnimations.DIG_UP.bake(root);
		this.digDownAnimation = WormOfCorruptionAnimations.DIG_DOWN.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition worm = root.addOrReplaceChild("worm", CubeListBuilder.create(), PartPose.offset(0.0F, -18.25F, 1.75F));

		PartDefinition head = worm.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, -5.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 29.9289F, -0.6493F));

		PartDefinition upper = mouth.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(0, 48).addBox(-8.0F, -8.0F, 0.0F, 16.0F, 7.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -30.0F, 8.0F, 3.1416F, 0.0F, 3.1416F));

		PartDefinition upper_teeth = upper.addOrReplaceChild("upper_teeth", CubeListBuilder.create().texOffs(42, 130).addBox(-7.0F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(46, 130).addBox(-5.0F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(128, 167).addBox(-3.0F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(132, 167).addBox(-1.0F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(136, 167).addBox(4.25F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(140, 167).addBox(2.5F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(144, 167).addBox(6.25F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(148, 167).addBox(0.75F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 34.0F, 8.0F));

		PartDefinition lower = mouth.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(64, 48).addBox(-8.0F, -1.0F, -15.0F, 16.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -30.0F, 7.0F));

		PartDefinition lower_teeth = lower.addOrReplaceChild("lower_teeth", CubeListBuilder.create().texOffs(52, 119).addBox(-7.0F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(56, 119).addBox(-5.0F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(60, 119).addBox(-3.0F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(50, 129).addBox(-1.0F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(54, 129).addBox(4.25F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 130).addBox(2.5F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(34, 130).addBox(6.25F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(58, 129).addBox(0.75F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 32.0F, -7.0F));

		PartDefinition head_horns = head.addOrReplaceChild("head_horns", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition horn_1 = head_horns.addOrReplaceChild("horn_1", CubeListBuilder.create().texOffs(118, 167).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 8.0F));

		PartDefinition horn_2 = head_horns.addOrReplaceChild("horn_2", CubeListBuilder.create().texOffs(52, 113).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition horn_3 = head_horns.addOrReplaceChild("horn_3", CubeListBuilder.create().texOffs(50, 122).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 8.0F));

		PartDefinition horn_4 = head_horns.addOrReplaceChild("horn_4", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 8.0F));

		PartDefinition body = worm.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 18.25F, -1.75F));

		PartDefinition body_1 = body.addOrReplaceChild("body_1", CubeListBuilder.create().texOffs(64, 70).addBox(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition body_2 = body.addOrReplaceChild("body_2", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -8.0F));

		PartDefinition cube_r1 = body_2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 71).addBox(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition body_3 = body.addOrReplaceChild("body_3", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, -8.0F));

		PartDefinition cube_r2 = body_3.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(64, 91).addBox(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body_3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(102, 167).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 15.25F, 0.1309F, 0.0F, 0.0F));

		PartDefinition body_4 = body.addOrReplaceChild("body_4", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, -8.0F));

		PartDefinition cube_r4 = body_4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 113).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 15.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r5 = body_4.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 92).addBox(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition body_5 = body.addOrReplaceChild("body_5", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, -8.0F));

		PartDefinition cube_r6 = body_5.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(34, 122).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 13.5F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r7 = body_5.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(64, 112).addBox(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.75F, 0.1745F, 0.0F, 0.0F));

		PartDefinition horn_5 = body_5.addOrReplaceChild("horn_5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 16.25F, 15.75F, 0.1309F, 0.0F, 0.0F));

		PartDefinition miasma = root.addOrReplaceChild("miasma", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone = miasma.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -9.0F));

		PartDefinition cube_r8 = bone.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(34, 133).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 18.0F, -0.176F, -0.1289F, 0.0229F));

		PartDefinition cube_r9 = bone.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(68, 133).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 18.0F, -0.1962F, -0.4721F, 0.0902F));

		PartDefinition cube_r10 = bone.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(102, 133).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 18.0F, -0.2677F, -0.8547F, 0.2041F));

		PartDefinition cube_r11 = bone.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(162, 116).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 0.0F, 13.0F, 0.0F, 1.3963F, 3.1416F));

		PartDefinition cube_r12 = bone.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 164).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 0.0F, 10.0F, 0.0F, 1.4835F, 3.1416F));

		PartDefinition cube_r13 = bone.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(34, 167).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 0.0F, 7.0F, -3.1416F, 1.5272F, 0.0F));

		PartDefinition cube_r14 = bone.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(68, 167).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 0.0F, 5.0F, 0.0F, -1.3963F, 3.1416F));

		PartDefinition cube_r15 = bone.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(162, 99).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 7.0F, -3.1416F, -1.5272F, 0.0F));

		PartDefinition cube_r16 = bone.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(162, 48).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 10.0F, 0.0F, -1.4835F, -3.1416F));

		PartDefinition cube_r17 = bone.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(162, 65).addBox(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 3.0F, 0.0F, 1.0908F, -3.1416F));

		PartDefinition cube_r18 = bone.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(162, 82).addBox(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 5.0F, 0.0F, 1.3963F, -3.1416F));

		PartDefinition cube_r19 = bone.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(136, 150).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 13.0F, 0.0F, -1.3963F, -3.1416F));

		PartDefinition cube_r20 = bone.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(102, 150).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 15.0F, 0.0F, -1.0908F, -3.1416F));

		PartDefinition cube_r21 = bone.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(136, 133).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 18.0F, -0.2677F, 0.8547F, -0.2041F));

		PartDefinition cube_r22 = bone.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 147).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 18.0F, -0.1962F, 0.4721F, -0.0902F));

		PartDefinition cube_r23 = bone.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(34, 150).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 18.0F, -0.176F, 0.1289F, -0.0229F));

		PartDefinition cube_r24 = bone.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(68, 150).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 18.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r25 = bone.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(128, 99).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.176F, 0.1289F, 0.0229F));

		PartDefinition cube_r26 = bone.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(128, 116).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.1962F, 0.4721F, 0.0902F));

		PartDefinition cube_r27 = bone.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(0, 130).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 0.2677F, 0.8547F, 0.2041F));

		PartDefinition cube_r28 = bone.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(128, 82).addBox(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 0.0F, 0.0F, 0.2677F, -0.8547F, -0.2041F));

		PartDefinition cube_r29 = bone.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(128, 65).addBox(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.1962F, -0.4721F, -0.0902F));

		PartDefinition cube_r30 = bone.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(128, 48).addBox(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.176F, -0.1289F, -0.0229F));

		PartDefinition cube_r31 = bone.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(0, 113).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition overlay = miasma.addOrReplaceChild("overlay", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, 1.5F, -15.0F, 46.0F, 0.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, -9.0F));

		PartDefinition horns = root.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 208, 208);
	}

	    @Override
	    public void setupAnim(WormOfCorruptionRenderState state) {
	        super.setupAnim(state);

			this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
			this.attackAnimation.apply(state.attackAnimationState, state.ageInTicks);
			this.shootAnimation.apply(state.shootAnimationState, state.ageInTicks);
			this.digUpAnimation.apply(state.digUpAnimationState, state.ageInTicks);
			this.digDownAnimation.apply(state.digDownAnimationState, state.ageInTicks);
	    }
}
