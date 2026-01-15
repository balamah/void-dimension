package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.util.Identifier;
import net.minecraft.client.model.*;

import net.balamah.voiddim.entity.client.base.BasicLivingEntityModel;
import net.balamah.voiddim.VoidDimension;

public class ShatteredSentinelModel extends BasicLivingEntityModel<ShatteredSentinelRenderState> {
    public static final EntityModelLayer SHATTERED_SENTINEL =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID,
										   "shattered_sentinel_model"),
							 "main");

	private final ModelPart root;
	private final ModelPart jaw;
	private final ModelPart body;
	private final ModelPart stones;
	private final ModelPart pelvis;
	private final ModelPart legs;
	private final ModelPart left_leg;
	private final ModelPart left_leg_calves;
	private final ModelPart right_leg;
	private final ModelPart right_leg_calves;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart left_arm_elbow;
	private final ModelPart right_arm;
	private final ModelPart right_arm_elbow;

	private final Animation walkAnimation;
	private final Animation attackAnimation;
	private final Animation idleAnimation;
	private final Animation stonesFloatAnimation;

	public ShatteredSentinelModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.jaw = this.head.getChild("jaw");
		this.body = this.root.getChild("body");
		this.stones = this.body.getChild("stones");
		this.pelvis = this.body.getChild("pelvis");
		this.legs = this.root.getChild("legs");
		this.left_leg = this.legs.getChild("left_leg");
		this.left_leg_calves = this.left_leg.getChild("left_leg_calves");
		this.right_leg = this.legs.getChild("right_leg");
		this.right_leg_calves = this.right_leg.getChild("right_leg_calves");
		this.arms = this.root.getChild("arms");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_arm_elbow = this.left_arm.getChild("left_arm_elbow");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_arm_elbow = this.right_arm.getChild("right_arm_elbow");

		this.walkAnimation = ShatteredSentinelMasterAnimations.WALK.createAnimation(root);
		this.attackAnimation = ShatteredSentinelMasterAnimations.ATTACK.createAnimation(root);
		this.idleAnimation = ShatteredSentinelMasterAnimations.IDLE.createAnimation(root);
		this.stonesFloatAnimation = ShatteredSentinelMasterAnimations.STONES_FLOAT.createAnimation(root);

	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 13).cuboid(-2.5F, -3.0F, -2.5F, 5.0F, 4.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -25.0F, 0.0F));

		ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(14, 29).cuboid(-0.75F, 0.0F, -5.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(22, 0).cuboid(-2.25F, 0.0F, -4.0F, 5.0F, 1.0F, 4.0F, new Dilation(-0.001F)), ModelTransform.origin(-0.25F, 1.0F, 2.5F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -22.0F, -3.0F, 5.0F, 7.0F, 6.0F, new Dilation(0.0F))
		.uv(20, 19).cuboid(-3.0F, -22.0F, -3.0F, 2.0F, 7.0F, 5.0F, new Dilation(0.0F))
		.uv(14, 22).cuboid(-3.0F, -21.0F, 2.0F, 2.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(22, 5).cuboid(4.0F, -16.0F, -3.0F, 1.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 22).cuboid(4.0F, -21.0F, -3.0F, 1.0F, 5.0F, 6.0F, new Dilation(0.0F))
		.uv(26, 31).cuboid(4.0F, -22.0F, -3.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(14, 31).cuboid(-5.0F, -22.0F, -3.0F, 2.0F, 7.0F, 4.0F, new Dilation(0.0F))
		.uv(8, 33).cuboid(-5.0F, -20.0F, 1.0F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(36, 5).cuboid(-4.0F, -20.0F, 2.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(42, 17).cuboid(-5.0F, -19.0F, 2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F))
		.uv(42, 25).cuboid(-4.0F, -22.0F, 1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData stones = body.addChild("stones", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -18.0F, 0.0F));

		ModelPartData cube_r1 = stones.addChild("cube_r1", ModelPartBuilder.create().uv(34, 28).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-9.0F, -3.0F, 3.0F, 0.0452F, 0.2615F, 0.2299F));

		ModelPartData cube_r2 = stones.addChild("cube_r2", ModelPartBuilder.create().uv(8, 39).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -8.0F, 6.0F, 0.1745F, 0.2182F, 0.0F));

		ModelPartData cube_r3 = stones.addChild("cube_r3", ModelPartBuilder.create().uv(42, 37).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -4.0F, 6.0F, 0.1273F, -0.5932F, -0.1632F));

		ModelPartData cube_r4 = stones.addChild("cube_r4", ModelPartBuilder.create().uv(42, 39).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(10.0F, -2.0F, 0.0F, -0.0324F, 1.0763F, -0.2542F));

		ModelPartData cube_r5 = stones.addChild("cube_r5", ModelPartBuilder.create().uv(42, 41).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -6.0F, 4.0F, 0.4403F, 0.8869F, 0.2924F));

		ModelPartData cube_r6 = stones.addChild("cube_r6", ModelPartBuilder.create().uv(36, 11).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -6.0F, 0.0F, 0.1273F, 0.5932F, 0.1632F));

		ModelPartData cube_r7 = stones.addChild("cube_r7", ModelPartBuilder.create().uv(42, 22).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 1.0F, 1.0F, 0.1904F, 0.9812F, 0.2507F));

		ModelPartData cube_r8 = stones.addChild("cube_r8", ModelPartBuilder.create().uv(16, 42).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -4.0F, 3.0F, 0.315F, 0.7769F, 0.1298F));

		ModelPartData cube_r9 = stones.addChild("cube_r9", ModelPartBuilder.create().uv(8, 42).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -6.0F, 4.0F, 0.315F, -0.7769F, -0.1298F));

		ModelPartData pelvis = body.addChild("pelvis", ModelPartBuilder.create().uv(20, 13).cuboid(-3.0F, -15.0F, -2.0F, 6.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -13.0F, 0.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(40, 9).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-3.0F, 0.0F, 0.0F));

		ModelPartData left_leg_calves = left_leg.addChild("left_leg_calves", ModelPartBuilder.create().uv(0, 33).cuboid(-4.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(3.0F, 6.0F, 0.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(0, 42).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(3.0F, 0.0F, 0.0F));

		ModelPartData right_leg_calves = right_leg.addChild("right_leg_calves", ModelPartBuilder.create().uv(34, 19).cuboid(2.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-3.0F, 6.0F, 0.0F));

		ModelPartData arms = root.addChild("arms", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -22.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(34, 37).cuboid(-3.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-4.0F, 0.0F, 0.0F));

		ModelPartData left_arm_elbow = left_arm.addChild("left_arm_elbow", ModelPartBuilder.create().uv(26, 37).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-2.0F, 7.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(38, 28).cuboid(1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(4.0F, 0.0F, 0.0F));

		ModelPartData right_arm_elbow = right_arm.addChild("right_arm_elbow", ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, 7.0F, 0.0F));
		return TexturedModelData.of(modelData, 52, 52);
	}

    @Override
    public void setAngles(ShatteredSentinelRenderState state) {
        super.setAngles(state);

		this.walkAnimation.applyWalking(
			state.limbSwingAnimationProgress, state.limbSwingAmplitude, 2f, 2.5f
		);

		this.idleAnimation.apply(
			state.idleAnimationState, state.age, 1f
		);

		this.attackAnimation.apply(
			state.attackAnimationState, state.age, 1f
		);

		this.stonesFloatAnimation.apply(
			state.stonesFloatAnimationState, state.age
		);
    }
}
