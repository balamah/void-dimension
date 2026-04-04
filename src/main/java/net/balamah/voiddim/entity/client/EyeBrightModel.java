package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.Identifier;
import net.minecraft.client.model.*;

import net.balamah.voiddim.VoidDimension;

public class EyeBrightModel extends EntityModel<EyeBrightRenderState> {
    public static final EntityModelLayer EYE_BRIGHT =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "eye_bright"),
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

	private final Animation idleAnimationState;
	private final Animation walkAnimationState;
	private final Animation attack1AnimationState;
	private final Animation attack2AnimationState;
	private final Animation attack3AnimationState;
	private final Animation shoot1AnimationState;
	private final Animation shoot2AnimationState;
	private final Animation shoot3AnimationState;
	private final Animation shoot4AnimationState;
	private final Animation magnettedAttackAnimationState;

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

		this.idleAnimationState = EyeBrightAnimations.IDLE.createAnimation(root);
		this.walkAnimationState = EyeBrightAnimations.WALK.createAnimation(root);
		this.attack1AnimationState = EyeBrightAnimations.ATTACK_1.createAnimation(root);
		this.attack2AnimationState = EyeBrightAnimations.ATTACK_2.createAnimation(root);
		this.attack3AnimationState = EyeBrightAnimations.ATTACK_3.createAnimation(root);
		this.shoot1AnimationState = EyeBrightAnimations.SHOOT_1.createAnimation(root);
		this.shoot2AnimationState = EyeBrightAnimations.SHOOT_2.createAnimation(root);
		this.shoot3AnimationState = EyeBrightAnimations.SHOOT_3.createAnimation(root);
		this.shoot4AnimationState = EyeBrightAnimations.SHOOT_4.createAnimation(root);
		this.magnettedAttackAnimationState =
			EyeBrightAnimations.MAGNETTED_ATTACK.createAnimation(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 1.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(44, 35).cuboid(-5.0F, -18.0F, -3.0F, 9.0F, 18.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -12.0F, 0.0F));

		ModelPartData heads = body.addChild("heads", ModelPartBuilder.create().uv(0, 0).cuboid(-12.0F, -8.0F, -10.0F, 13.0F, 20.0F, 15.0F, new Dilation(0.0F))
		.uv(60, 113).cuboid(1.0F, 1.0F, 0.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F))
		.uv(56, 22).cuboid(-5.0F, 12.0F, -4.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 35).cuboid(-11.0F, 12.0F, -8.0F, 10.0F, 4.0F, 12.0F, new Dilation(0.0F))
		.uv(32, 51).cuboid(1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -18.0F, 0.0F, 0.0F, -0.1309F, 0.0F));

		ModelPartData cube_r1 = heads.addChild("cube_r1", ModelPartBuilder.create().uv(80, 21).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -7.25F, 0.0F, -1.5328F, 1.1746F, 0.0593F));

		ModelPartData cube_r2 = heads.addChild("cube_r2", ModelPartBuilder.create().uv(56, 10).cuboid(-2.0F, -5.0F, -4.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -8.25F, -5.0F, -1.7062F, 1.1897F, 0.0591F));

		ModelPartData cube_r3 = heads.addChild("cube_r3", ModelPartBuilder.create().uv(22, 80).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-9.0F, -8.25F, -5.0F, -1.9322F, 1.1458F, -0.3752F));

		ModelPartData cube_r4 = heads.addChild("cube_r4", ModelPartBuilder.create().uv(70, 34).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -7.25F, -1.0F, -1.5328F, 1.1746F, 0.0593F));

		ModelPartData cube_r5 = heads.addChild("cube_r5", ModelPartBuilder.create().uv(110, 78).cuboid(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, 15.5F, 1.0F, -0.4394F, -0.699F, 0.0331F));

		ModelPartData cube_r6 = heads.addChild("cube_r6", ModelPartBuilder.create().uv(0, 110).cuboid(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, 11.5F, 3.0F, -0.134F, -0.699F, 0.0331F));

		ModelPartData cube_r7 = heads.addChild("cube_r7", ModelPartBuilder.create().uv(88, 102).cuboid(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, 5.5F, 3.0F, 0.1258F, -0.7003F, -0.3672F));

		ModelPartData cube_r8 = heads.addChild("cube_r8", ModelPartBuilder.create().uv(66, 102).cuboid(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -0.5F, 3.0F, -0.3054F, -0.6511F, 0.3062F));

		ModelPartData cube_r9 = heads.addChild("cube_r9", ModelPartBuilder.create().uv(70, 45).cuboid(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -6.0F, 4.0F, 0.1567F, -0.6075F, 0.0344F));

		ModelPartData cube_r10 = heads.addChild("cube_r10", ModelPartBuilder.create().uv(0, 77).cuboid(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -6.0F, 5.0F, 0.0431F, 0.1707F, 0.1466F));

		ModelPartData cube_r11 = heads.addChild("cube_r11", ModelPartBuilder.create().uv(80, 10).cuboid(-2.0F, -5.0F, -2.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -6.0F, 4.0F, 0.1445F, 0.4733F, 0.1906F));

		ModelPartData cube_r12 = heads.addChild("cube_r12", ModelPartBuilder.create().uv(0, 65).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, 16.5F, -6.25F, 0.6525F, -0.593F, 0.0951F));

		ModelPartData cube_r13 = heads.addChild("cube_r13", ModelPartBuilder.create().uv(56, 57).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, 16.25F, -6.25F, 0.4746F, -0.059F, -0.0534F));

		ModelPartData cube_r14 = heads.addChild("cube_r14", ModelPartBuilder.create().uv(32, 57).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-9.5F, 16.75F, -6.5F, 0.3507F, 0.7254F, 0.0224F));

		ModelPartData cube_r15 = heads.addChild("cube_r15", ModelPartBuilder.create().uv(22, 91).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.5F, 11.25F, -8.5F, 0.139F, 0.7396F, -0.0331F));

		ModelPartData cube_r16 = heads.addChild("cube_r16", ModelPartBuilder.create().uv(66, 91).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 11.25F, -8.75F, 0.1724F, -0.1404F, -0.1153F));

		ModelPartData cube_r17 = heads.addChild("cube_r17", ModelPartBuilder.create().uv(44, 91).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 5.25F, -9.25F, 0.1456F, -0.1681F, 0.0591F));

		ModelPartData cube_r18 = heads.addChild("cube_r18", ModelPartBuilder.create().uv(0, 88).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -0.5F, -8.75F, 0.0388F, -0.1759F, -0.0748F));

		ModelPartData cube_r19 = heads.addChild("cube_r19", ModelPartBuilder.create().uv(92, 43).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, 12.25F, -8.25F, 0.3222F, -0.5429F, -0.1151F));

		ModelPartData cube_r20 = heads.addChild("cube_r20", ModelPartBuilder.create().uv(92, 32).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, 6.5F, -9.25F, 0.1476F, -0.5429F, -0.1151F));

		ModelPartData cube_r21 = heads.addChild("cube_r21", ModelPartBuilder.create().uv(88, 91).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 0.0F, -9.25F, 0.0405F, -0.5594F, 0.089F));

		ModelPartData cube_r22 = heads.addChild("cube_r22", ModelPartBuilder.create().uv(46, 69).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.25F, -6.0F, -9.0F, -0.1445F, -0.4733F, 0.1906F));

		ModelPartData cube_r23 = heads.addChild("cube_r23", ModelPartBuilder.create().uv(66, 80).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -4.0F, 4.25F, -2.7704F, 1.3349F, -2.6574F));

		ModelPartData cube_r24 = heads.addChild("cube_r24", ModelPartBuilder.create().uv(0, 99).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -4.0F, 0.25F, 2.0435F, 1.4703F, 2.1599F));

		ModelPartData cube_r25 = heads.addChild("cube_r25", ModelPartBuilder.create().uv(22, 102).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-8.5F, 13.75F, -2.75F, 1.6711F, 1.025F, 1.2949F));

		ModelPartData cube_r26 = heads.addChild("cube_r26", ModelPartBuilder.create().uv(102, 11).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-8.75F, 13.75F, 0.25F, -2.7151F, 1.5136F, -3.0761F));

		ModelPartData cube_r27 = heads.addChild("cube_r27", ModelPartBuilder.create().uv(102, 54).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-9.75F, 2.5F, -4.75F, 1.0061F, 1.3943F, 0.9351F));

		ModelPartData cube_r28 = heads.addChild("cube_r28", ModelPartBuilder.create().uv(44, 102).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-9.75F, 8.75F, -4.75F, 1.137F, 1.3943F, 0.9351F));

		ModelPartData cube_r29 = heads.addChild("cube_r29", ModelPartBuilder.create().uv(102, 0).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-9.5F, 7.75F, 0.25F, -2.8896F, 1.5136F, -3.0761F));

		ModelPartData cube_r30 = heads.addChild("cube_r30", ModelPartBuilder.create().uv(80, 56).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, 2.0F, 0.25F, -3.0641F, 1.5136F, -3.0761F));

		ModelPartData cube_r31 = heads.addChild("cube_r31", ModelPartBuilder.create().uv(44, 80).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -4.0F, -5.75F, -2.4066F, 1.5092F, -2.2872F));

		ModelPartData flying_head_1 = heads.addChild("flying_head_1", ModelPartBuilder.create(), ModelTransform.origin(-7.0F, -6.0F, -9.75F));

		ModelPartData cube_r32 = flying_head_1.addChild("cube_r32", ModelPartBuilder.create().uv(68, 69).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0431F, -0.1707F, 0.1466F));

		ModelPartData flying_head_2 = heads.addChild("flying_head_2", ModelPartBuilder.create(), ModelTransform.origin(-10.5F, -0.5F, -8.5F));

		ModelPartData cube_r33 = flying_head_2.addChild("cube_r33", ModelPartBuilder.create().uv(88, 80).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0518F, 0.7396F, -0.0331F));

		ModelPartData flying_head_3 = heads.addChild("flying_head_3", ModelPartBuilder.create(), ModelTransform.origin(-10.5F, 5.5F, -8.5F));

		ModelPartData cube_r34 = flying_head_3.addChild("cube_r34", ModelPartBuilder.create().uv(90, 67).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.169F, 0.7251F, 0.142F));

		ModelPartData flying_head_4 = heads.addChild("flying_head_4", ModelPartBuilder.create(), ModelTransform.origin(-10.0F, -6.0F, -8.75F));

		ModelPartData cube_r35 = flying_head_4.addChild("cube_r35", ModelPartBuilder.create().uv(24, 69).cuboid(-2.0F, -5.0F, -3.0F, 6.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1567F, 0.6075F, 0.0344F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 12.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(110, 89).cuboid(1.0F, 0.0F, -2.5F, 3.0F, 16.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(3.0F, -30.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -18.0F, -1.0F));

		ModelPartData upper_head = head.addChild("upper_head", ModelPartBuilder.create().uv(0, 51).cuboid(-4.0F, -6.0F, -8.0F, 8.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -2.0F, 4.0F));

		ModelPartData upper_head_teeth = upper_head.addChild("upper_head_teeth", ModelPartBuilder.create().uv(36, 55).cuboid(-3.75F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(40, 55).cuboid(-1.75F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(80, 32).cuboid(0.25F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(80, 67).cuboid(2.25F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData head_jaw = head.addChild("head_jaw", ModelPartBuilder.create().uv(56, 0).cuboid(-4.0F, 0.0F, -8.0F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -2.0F, 4.0F));

		ModelPartData head_jaw_teeth = head_jaw.addChild("head_jaw_teeth", ModelPartBuilder.create().uv(84, 32).cuboid(-3.75F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(84, 67).cuboid(-1.75F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(88, 32).cuboid(0.25F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(90, 78).cuboid(2.25F, -0.25F, -7.75F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.5F, -0.5F, 0.0F));

		ModelPartData jaw = body.addChild("jaw", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -18.0F, -1.0F));

		ModelPartData upper_jaw = jaw.addChild("upper_jaw", ModelPartBuilder.create().uv(102, 25).cuboid(-3.0F, 0.0F, -4.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData upper_teeth = upper_jaw.addChild("upper_teeth", ModelPartBuilder.create().uv(32, 54).cuboid(-2.75F, 2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.0001F))
		.uv(88, 0).cuboid(-1.25F, 2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.0001F))
		.uv(88, 3).cuboid(1.75F, 2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.0001F))
		.uv(88, 6).cuboid(0.25F, 2.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.0001F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData lower_jaw = jaw.addChild("lower_jaw", ModelPartBuilder.create().uv(112, 65).cuboid(-3.0F, -2.0F, -4.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(102, 29).cuboid(-3.0F, 0.0F, -4.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 4.0F, 0.0F));

		ModelPartData lower_teeth = lower_jaw.addChild("lower_teeth", ModelPartBuilder.create().uv(38, 51).cuboid(-2.25F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new Dilation(-0.0001F))
		.uv(24, 65).cuboid(1.25F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new Dilation(-0.0001F))
		.uv(28, 65).cuboid(-0.5F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new Dilation(-0.0001F)), ModelTransform.origin(0.0F, 0.25F, 0.0F));

		ModelPartData jaw_sides = jaw.addChild("jaw_sides", ModelPartBuilder.create().uv(48, 113).cuboid(0.0F, 0.0F, 1.0F, 1.0F, 16.0F, 2.0F, new Dilation(0.0F))
		.uv(54, 113).cuboid(7.0F, 0.0F, 1.0F, 1.0F, 16.0F, 2.0F, new Dilation(0.0F))
		.uv(112, 69).cuboid(1.0F, 15.0F, 1.0F, 6.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(102, 22).cuboid(0.0F, 16.0F, 2.0F, 8.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(-4.0F, 0.0F, -5.0F));

		ModelPartData body_decoration = jaw.addChild("body_decoration", ModelPartBuilder.create().uv(36, 113).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 16.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.25F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -12.0F, -2.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(110, 108).cuboid(-2.0F, 0.0F, -3.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(3.0F, 0.0F, 2.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(22, 113).cuboid(-1.0F, 0.0F, -3.0F, 3.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(-4.0F, 0.0F, 2.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}

	@Override
	public void setAngles(EyeBrightRenderState state) {
		super.setAngles(state);

		this.setHeadAngles(state.relativeHeadYaw, state.pitch);
		this.walkAnimationState.applyWalking(
			state.limbSwingAnimationProgress, state.limbSwingAmplitude, 2f, 2.5f
		);

		this.idleAnimationState.apply(state.idleAnimationState, state.age);
		this.attack1AnimationState.apply(state.attack1AnimationState, state.age);
		this.attack2AnimationState.apply(state.attack2AnimationState, state.age);
		this.attack3AnimationState.apply(state.attack3AnimationState, state.age);
		this.shoot1AnimationState.apply(state.shoot1AnimationState, state.age);
		this.shoot2AnimationState.apply(state.shoot2AnimationState, state.age);
		this.shoot3AnimationState.apply(state.shoot3AnimationState, state.age);
		this.shoot4AnimationState.apply(state.shoot4AnimationState, state.age);
		this.magnettedAttackAnimationState.apply(state.magnettedAttackAnimationState, state.age);
	}

	protected void setHeadAngles(float headYaw, float headPitch) {
		float yawRad   = headYaw   * ((float)Math.PI / 180F);
		float pitchRad = headPitch * ((float)Math.PI / 180F);

		pitchRad = MathHelper.clamp(
			pitchRad,
			-25F * ((float)Math.PI / 180F),
			45F  * ((float)Math.PI / 180F)
		);

		this.head.yaw  = yawRad;
		this.head.roll = pitchRad * -1;
		this.head.pitch = 0.0F;
	}
}
