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
import net.minecraft.util.Mth;
import net.balamah.voiddim.VoidDimension;

public class EyeBrightModel extends EntityModel<EyeBrightRenderState> {
    public static final ModelLayerLocation EYE_BRIGHT =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "eye_bright"),
							 "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart heads;
	private final ModelPart flying_head_1;
	private final ModelPart flying_head_2;
	private final ModelPart flying_head_3;
	private final ModelPart flying_head_4;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart head;
	private final ModelPart upper_head;
	private final ModelPart upper_head_teeth;
	private final ModelPart head_jaw;
	private final ModelPart head_jaw_teeth;
	private final ModelPart jaw;
	private final ModelPart upper_jaw;
	private final ModelPart upper_teeth;
	private final ModelPart lower_jaw;
	private final ModelPart lower_teeth;
	private final ModelPart jaw_sides;
	private final ModelPart body_decoration;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	private final KeyframeAnimation idleAnimationState;
	private final KeyframeAnimation walkAnimationState;
	private final KeyframeAnimation attack1AnimationState;
	private final KeyframeAnimation attack2AnimationState;
	private final KeyframeAnimation attack3AnimationState;
	private final KeyframeAnimation shoot1AnimationState;
	private final KeyframeAnimation shoot2AnimationState;
	private final KeyframeAnimation shoot3AnimationState;
	private final KeyframeAnimation shoot4AnimationState;
	private final KeyframeAnimation magnettedAttackAnimationState;

	public EyeBrightModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.heads = this.body.getChild("heads");
		this.flying_head_1 = this.heads.getChild("flying_head_1");
		this.flying_head_2 = this.heads.getChild("flying_head_2");
		this.flying_head_3 = this.heads.getChild("flying_head_3");
		this.flying_head_4 = this.heads.getChild("flying_head_4");
		this.arms = this.body.getChild("arms");
		this.left_arm = this.arms.getChild("left_arm");
		this.head = this.body.getChild("head");
		this.upper_head = this.head.getChild("upper_head");
		this.upper_head_teeth = this.upper_head.getChild("upper_head_teeth");
		this.head_jaw = this.head.getChild("head_jaw");
		this.head_jaw_teeth = this.head_jaw.getChild("head_jaw_teeth");
		this.jaw = this.body.getChild("jaw");
		this.upper_jaw = this.jaw.getChild("upper_jaw");
		this.upper_teeth = this.upper_jaw.getChild("upper_teeth");
		this.lower_jaw = this.jaw.getChild("lower_jaw");
		this.lower_teeth = this.lower_jaw.getChild("lower_teeth");
		this.jaw_sides = this.jaw.getChild("jaw_sides");
		this.body_decoration = this.jaw.getChild("body_decoration");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.left_leg = this.legs.getChild("left_leg");

		this.idleAnimationState = EyeBrightAnimations.IDLE.bake(root);
		this.walkAnimationState = EyeBrightAnimations.WALK.bake(root);
		this.attack1AnimationState = EyeBrightAnimations.ATTACK_1.bake(root);
		this.attack2AnimationState = EyeBrightAnimations.ATTACK_2.bake(root);
		this.attack3AnimationState = EyeBrightAnimations.ATTACK_3.bake(root);
		this.shoot1AnimationState = EyeBrightAnimations.SHOOT_1.bake(root);
		this.shoot2AnimationState = EyeBrightAnimations.SHOOT_2.bake(root);
		this.shoot3AnimationState = EyeBrightAnimations.SHOOT_3.bake(root);
		this.shoot4AnimationState = EyeBrightAnimations.SHOOT_4.bake(root);
		this.magnettedAttackAnimationState =
			EyeBrightAnimations.MAGNETTED_ATTACK.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 1.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(44, 35).addBox(-5.0F, -18.0F, -3.0F, 9.0F, 18.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition heads = body.addOrReplaceChild("heads", CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -8.0F, -10.0F, 13.0F, 20.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(60, 113).addBox(1.0F, 1.0F, 0.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 22).addBox(-5.0F, 12.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 35).addBox(-11.0F, 12.0F, -8.0F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(32, 51).addBox(1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -18.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r1 = heads.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(80, 21).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.25F, 0.0F, -1.5328F, 1.1746F, 0.0593F));

		PartDefinition cube_r2 = heads.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 10).addBox(-2.0F, -5.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -8.25F, -5.0F, -1.7062F, 1.1897F, 0.0591F));

		PartDefinition cube_r3 = heads.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(22, 80).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -8.25F, -5.0F, -1.9322F, 1.1458F, -0.3752F));

		PartDefinition cube_r4 = heads.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(70, 34).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -7.25F, -1.0F, -1.5328F, 1.1746F, 0.0593F));

		PartDefinition cube_r5 = heads.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(110, 78).addBox(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 15.5F, 1.0F, -0.4394F, -0.699F, 0.0331F));

		PartDefinition cube_r6 = heads.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 110).addBox(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 11.5F, 3.0F, -0.134F, -0.699F, 0.0331F));

		PartDefinition cube_r7 = heads.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(88, 102).addBox(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 5.5F, 3.0F, 0.1258F, -0.7003F, -0.3672F));

		PartDefinition cube_r8 = heads.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(66, 102).addBox(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -0.5F, 3.0F, -0.3054F, -0.6511F, 0.3062F));

		PartDefinition cube_r9 = heads.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(70, 45).addBox(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -6.0F, 4.0F, 0.1567F, -0.6075F, 0.0344F));

		PartDefinition cube_r10 = heads.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 77).addBox(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -6.0F, 5.0F, 0.0431F, 0.1707F, 0.1466F));

		PartDefinition cube_r11 = heads.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(80, 10).addBox(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.0F, 4.0F, 0.1445F, 0.4733F, 0.1906F));

		PartDefinition cube_r12 = heads.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 65).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 16.5F, -6.25F, 0.6525F, -0.593F, 0.0951F));

		PartDefinition cube_r13 = heads.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(56, 57).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 16.25F, -6.25F, 0.4746F, -0.059F, -0.0534F));

		PartDefinition cube_r14 = heads.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(32, 57).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, 16.75F, -6.5F, 0.3507F, 0.7254F, 0.0224F));

		PartDefinition cube_r15 = heads.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(22, 91).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.5F, 11.25F, -8.5F, 0.139F, 0.7396F, -0.0331F));

		PartDefinition cube_r16 = heads.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(66, 91).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 11.25F, -8.75F, 0.1724F, -0.1404F, -0.1153F));

		PartDefinition cube_r17 = heads.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(44, 91).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 5.25F, -9.25F, 0.1456F, -0.1681F, 0.0591F));

		PartDefinition cube_r18 = heads.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 88).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -0.5F, -8.75F, 0.0388F, -0.1759F, -0.0748F));

		PartDefinition cube_r19 = heads.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(92, 43).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, 12.25F, -8.25F, 0.3222F, -0.5429F, -0.1151F));

		PartDefinition cube_r20 = heads.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(92, 32).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 6.5F, -9.25F, 0.1476F, -0.5429F, -0.1151F));

		PartDefinition cube_r21 = heads.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(88, 91).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, -9.25F, 0.0405F, -0.5594F, 0.089F));

		PartDefinition cube_r22 = heads.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(46, 69).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, -6.0F, -9.0F, -0.1445F, -0.4733F, 0.1906F));

		PartDefinition cube_r23 = heads.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(66, 80).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -4.0F, 4.25F, -2.7704F, 1.3349F, -2.6574F));

		PartDefinition cube_r24 = heads.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(0, 99).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -4.0F, 0.25F, 2.0435F, 1.4703F, 2.1599F));

		PartDefinition cube_r25 = heads.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(22, 102).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 13.75F, -2.75F, 1.6711F, 1.025F, 1.2949F));

		PartDefinition cube_r26 = heads.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(102, 11).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.75F, 13.75F, 0.25F, -2.7151F, 1.5136F, -3.0761F));

		PartDefinition cube_r27 = heads.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(102, 54).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.75F, 2.5F, -4.75F, 1.0061F, 1.3943F, 0.9351F));

		PartDefinition cube_r28 = heads.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(44, 102).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.75F, 8.75F, -4.75F, 1.137F, 1.3943F, 0.9351F));

		PartDefinition cube_r29 = heads.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(102, 0).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, 7.75F, 0.25F, -2.8896F, 1.5136F, -3.0761F));

		PartDefinition cube_r30 = heads.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(80, 56).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, 2.0F, 0.25F, -3.0641F, 1.5136F, -3.0761F));

		PartDefinition cube_r31 = heads.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(44, 80).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -4.0F, -5.75F, -2.4066F, 1.5092F, -2.2872F));

		PartDefinition flying_head_1 = heads.addOrReplaceChild("flying_head_1", CubeListBuilder.create(), PartPose.offset(-7.0F, -6.0F, -9.75F));

		PartDefinition cube_r32 = flying_head_1.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(68, 69).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0431F, -0.1707F, 0.1466F));

		PartDefinition flying_head_2 = heads.addOrReplaceChild("flying_head_2", CubeListBuilder.create(), PartPose.offset(-10.5F, -0.5F, -8.5F));

		PartDefinition cube_r33 = flying_head_2.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(88, 80).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0518F, 0.7396F, -0.0331F));

		PartDefinition flying_head_3 = heads.addOrReplaceChild("flying_head_3", CubeListBuilder.create(), PartPose.offset(-10.5F, 5.5F, -8.5F));

		PartDefinition cube_r34 = flying_head_3.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(90, 67).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.169F, 0.7251F, 0.142F));

		PartDefinition flying_head_4 = heads.addOrReplaceChild("flying_head_4", CubeListBuilder.create(), PartPose.offset(-10.0F, -6.0F, -8.75F));

		PartDefinition cube_r35 = flying_head_4.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(24, 69).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1567F, 0.6075F, 0.0344F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(110, 89).addBox(1.0F, 0.0F, -2.5F, 3.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -30.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, -1.0F));

		PartDefinition upper_head = head.addOrReplaceChild("upper_head", CubeListBuilder.create().texOffs(0, 51).addBox(-4.0F, -6.0F, -8.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 4.0F));

		PartDefinition upper_head_teeth = upper_head.addOrReplaceChild("upper_head_teeth", CubeListBuilder.create().texOffs(36, 55).addBox(-3.75F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 55).addBox(-1.75F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 32).addBox(0.25F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 67).addBox(2.25F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head_jaw = head.addOrReplaceChild("head_jaw", CubeListBuilder.create().texOffs(56, 0).addBox(-4.0F, 0.0F, -8.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 4.0F));

		PartDefinition head_jaw_teeth = head_jaw.addOrReplaceChild("head_jaw_teeth", CubeListBuilder.create().texOffs(84, 32).addBox(-3.75F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(84, 67).addBox(-1.75F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(88, 32).addBox(0.25F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(90, 78).addBox(2.25F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -0.5F, 0.0F));

		PartDefinition jaw = body.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, -1.0F));

		PartDefinition upper_jaw = jaw.addOrReplaceChild("upper_jaw", CubeListBuilder.create().texOffs(102, 25).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper_teeth = upper_jaw.addOrReplaceChild("upper_teeth", CubeListBuilder.create().texOffs(32, 54).addBox(-2.75F, 2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(88, 0).addBox(-1.25F, 2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(88, 3).addBox(1.75F, 2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(88, 6).addBox(0.25F, 2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.0001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition lower_jaw = jaw.addOrReplaceChild("lower_jaw", CubeListBuilder.create().texOffs(112, 65).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(102, 29).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition lower_teeth = lower_jaw.addOrReplaceChild("lower_teeth", CubeListBuilder.create().texOffs(38, 51).addBox(-2.25F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(24, 65).addBox(1.25F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(28, 65).addBox(-0.5F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.0001F)), PartPose.offset(0.0F, 0.25F, 0.0F));

		PartDefinition jaw_sides = jaw.addOrReplaceChild("jaw_sides", CubeListBuilder.create().texOffs(48, 113).addBox(0.0F, 0.0F, 1.0F, 1.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(54, 113).addBox(7.0F, 0.0F, 1.0F, 1.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(112, 69).addBox(1.0F, 15.0F, 1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(102, 22).addBox(0.0F, 16.0F, 2.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, -5.0F));

		PartDefinition body_decoration = jaw.addOrReplaceChild("body_decoration", CubeListBuilder.create().texOffs(36, 113).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.25F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, -2.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(110, 108).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 2.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(22, 113).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 2.0F));
		return LayerDefinition.create(modelData, 256, 256);
	}

	@Override
	public void setupAnim(EyeBrightRenderState state) {
		super.setupAnim(state);

		this.setHeadAngles(state.yRot, state.xRot);
		this.walkAnimationState.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);

		this.idleAnimationState.apply(state.idleAnimationState, state.ageInTicks);
		this.attack1AnimationState.apply(state.attack1AnimationState, state.ageInTicks);
		this.attack2AnimationState.apply(state.attack2AnimationState, state.ageInTicks);
		this.attack3AnimationState.apply(state.attack3AnimationState, state.ageInTicks);
		this.shoot1AnimationState.apply(state.shoot1AnimationState, state.ageInTicks);
		this.shoot2AnimationState.apply(state.shoot2AnimationState, state.ageInTicks);
		this.shoot3AnimationState.apply(state.shoot3AnimationState, state.ageInTicks);
		this.shoot4AnimationState.apply(state.shoot4AnimationState, state.ageInTicks);
		this.magnettedAttackAnimationState.apply(state.magnettedAttackAnimationState, state.ageInTicks);
	}

	protected void setHeadAngles(float headYaw, float headPitch) {
		float yawRad   = headYaw   * ((float)Math.PI / 180F);
		float pitchRad = headPitch * ((float)Math.PI / 180F);

		pitchRad = Mth.clamp(
			pitchRad,
			-25F * ((float)Math.PI / 180F),
			45F  * ((float)Math.PI / 180F)
		);

		this.head.yRot  = yawRad;
		this.head.zRot = pitchRad * -1;
		this.head.xRot = 0.0F;
	}
}
