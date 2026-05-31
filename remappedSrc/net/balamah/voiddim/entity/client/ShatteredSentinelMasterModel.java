package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.entity.client.base.BasicLivingEntityModel;
import net.balamah.voiddim.VoidDimension;

public class ShatteredSentinelMasterModel
	extends BasicLivingEntityModel<ShatteredSentinelMasterRenderState>
{
	public static final EntityModelLayer SHATTERED_SENTINEL_MASTER =
		new EntityModelLayer(
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "shattered_sentinel_master"),
			"main"
		);

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart stones;
	private final ModelPart lats;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart right_elbow;
	private final ModelPart right_arm;
	private final ModelPart left_elbow;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart right_calf;
	private final ModelPart right_foot;
	private final ModelPart left_leg;
	private final ModelPart left_calf;
	private final ModelPart left_foot;

	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation attackAnimation;
	private final KeyframeAnimation idleAnimation;
	private final KeyframeAnimation stonesFloatAnimation;

	private final KeyframeAnimation shatterGroundBeginAnimation;
	private final KeyframeAnimation shatterGroundPushAnimation;
	private final KeyframeAnimation shatterGroundEndAnimation;
	private final KeyframeAnimation shockWaveInvokeAnimation;
	private final KeyframeAnimation throwBlockAnimation;

	public ShatteredSentinelMasterModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.stones = this.body.getChild("stones");
		this.lats = this.body.getChild("lats");
		this.arms = this.lats.getChild("arms");
		this.left_arm = this.arms.getChild("left_arm");
		this.right_elbow = this.left_arm.getChild("right_elbow");
		this.right_arm = this.arms.getChild("right_arm");
		this.left_elbow = this.right_arm.getChild("left_elbow");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.right_calf = this.right_leg.getChild("right_calf");
		this.right_foot = this.right_calf.getChild("right_foot");
		this.left_leg = this.legs.getChild("left_leg");
		this.left_calf = this.left_leg.getChild("left_calf");
		this.left_foot = this.left_calf.getChild("left_foot");

		this.walkAnimation = ShatteredSentinelMasterAnimations.WALK.bake(root);
		this.attackAnimation = ShatteredSentinelMasterAnimations.ATTACK.bake(root);
		this.idleAnimation = ShatteredSentinelMasterAnimations.IDLE.bake(root);
		this.stonesFloatAnimation = ShatteredSentinelMasterAnimations.STONES_FLOAT.bake(root);

		this.shatterGroundBeginAnimation = ShatteredSentinelMasterAnimations.SHATTER_GROUND_BEGIN.bake(root);
		this.shatterGroundPushAnimation = ShatteredSentinelMasterAnimations.SHATTER_GROUND_PUSH.bake(root);
		this.shatterGroundEndAnimation = ShatteredSentinelMasterAnimations.SHATTER_GROUND_END.bake(root);
		this.shockWaveInvokeAnimation = ShatteredSentinelMasterAnimations.SHOCK_WAVE_INVOKE.bake(root);
		this.throwBlockAnimation = ShatteredSentinelMasterAnimations.THROW_BLOCK.bake(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 20).cuboid(-8.0F, -2.0F, -4.0F, 16.0F, 2.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-7.0F, -15.0F, -3.5F, 14.0F, 13.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 30).cuboid(-7.0F, -20.0F, -3.5F, 14.0F, 5.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -16.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 42).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -20.0F, 0.0F));

		ModelPartData stones = body.addChild("stones", ModelPartBuilder.create(), ModelTransform.of(-0.5F, -20.0F, 4.0F, -0.131F, -0.0433F, 0.0057F));

		ModelPartData cube_r1 = stones.addChild("cube_r1", ModelPartBuilder.create().uv(24, 62).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-9.0F, -3.0F, 3.0F, 0.0452F, 0.2615F, 0.2299F));

		ModelPartData cube_r2 = stones.addChild("cube_r2", ModelPartBuilder.create().uv(42, 15).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -8.0F, 6.0F, 0.1745F, 0.2182F, 0.0F));

		ModelPartData cube_r3 = stones.addChild("cube_r3", ModelPartBuilder.create().uv(64, 50).cuboid(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -4.0F, 6.0F, 0.1273F, -0.5932F, -0.1632F));

		ModelPartData cube_r4 = stones.addChild("cube_r4", ModelPartBuilder.create().uv(28, 64).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(10.0F, -2.0F, 0.0F, -0.0324F, 1.0763F, -0.2542F));

		ModelPartData cube_r5 = stones.addChild("cube_r5", ModelPartBuilder.create().uv(28, 62).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -6.0F, 4.0F, 0.4403F, 0.8869F, 0.2924F));

		ModelPartData cube_r6 = stones.addChild("cube_r6", ModelPartBuilder.create().uv(42, 18).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, -6.0F, 0.0F, 0.1273F, 0.5932F, 0.1632F));

		ModelPartData cube_r7 = stones.addChild("cube_r7", ModelPartBuilder.create().uv(42, 12).cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 1.0F, 1.0F, 0.1904F, 0.9812F, 0.2507F));

		ModelPartData cube_r8 = stones.addChild("cube_r8", ModelPartBuilder.create().uv(24, 58).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -4.0F, 3.0F, 0.315F, 0.7769F, 0.1298F));

		ModelPartData cube_r9 = stones.addChild("cube_r9", ModelPartBuilder.create().uv(56, 50).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -6.0F, 4.0F, 0.315F, -0.7769F, -0.1298F));

		ModelPartData lats = body.addChild("lats", ModelPartBuilder.create().uv(64, 66).cuboid(7.0F, -31.0F, -0.5F, 1.0F, 5.0F, 4.0F, new Dilation(0.0F))
		.uv(32, 71).cuboid(-8.0F, -31.0F, -0.5F, 1.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

		ModelPartData arms = lats.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -35.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(48, 12).cuboid(-1.0F, -2.0F, -3.5F, 5.0F, 5.0F, 7.0F, new Dilation(0.0F))
		.uv(66, 24).cuboid(0.5F, 3.0F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(8.0F, 1.0F, 0.0F));

		ModelPartData right_elbow = left_arm.addChild("right_elbow", ModelPartBuilder.create().uv(16, 66).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 10.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(32, 42).cuboid(-5.0F, -2.0F, -3.5F, 5.0F, 5.0F, 7.0F, new Dilation(0.0F))
		.uv(66, 0).cuboid(-4.5F, 3.0F, -1.5F, 3.0F, 7.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.0F, 1.0F, 0.0F));

		ModelPartData left_elbow = right_arm.addChild("left_elbow", ModelPartBuilder.create().uv(0, 66).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 10.0F, 0.0F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -15.0F, -2.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(32, 66).cuboid(-2.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(32, 54).cuboid(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -1.0F, 2.0F));

		ModelPartData right_calf = right_leg.addChild("right_calf", ModelPartBuilder.create().uv(42, 0).cuboid(-3.0F, 0.0F, -4.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(48, 24).cuboid(-2.0F, 6.0F, -3.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, 1.0F));

		ModelPartData right_foot = right_calf.addChild("right_foot", ModelPartBuilder.create().uv(56, 42).cuboid(-2.0F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, 7.0F, -1.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(48, 66).cuboid(-2.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(54, 54).cuboid(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -1.0F, 2.0F));

		ModelPartData left_calf = left_leg.addChild("left_calf", ModelPartBuilder.create().uv(66, 34).cuboid(-2.0F, 6.0F, -3.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(42, 30).cuboid(-3.0F, 0.0F, -4.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, 1.0F));

		ModelPartData left_foot = left_calf.addChild("left_foot", ModelPartBuilder.create().uv(0, 58).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, -1.0F));
		return TexturedModelData.of(modelData, 88, 88);
	}

	@Override
	public void setupAnim(ShatteredSentinelMasterRenderState state) {
		super.setupAnim(state);

		this.walkAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);

		this.idleAnimation.apply(
			state.idleAnimationState, state.ageInTicks, 1f
		);

		this.attackAnimation.apply(
			state.attackAnimationState, state.ageInTicks, 1f
		);

		this.shockWaveInvokeAnimation.apply(
			state.shockWaveInvokeState, state.ageInTicks, 1f
		);

		this.shatterGroundBeginAnimation.apply(
			state.shatterGroundBeginAnimationState, state.ageInTicks, 1f
		);

		this.stonesFloatAnimation.apply(
			state.stonesFloatAnimationState, state.ageInTicks
		);

		this.shatterGroundPushAnimation.apply(
			state.shatterGroundPushAnimationState, state.ageInTicks, 1f
		);

		this.shatterGroundEndAnimation.apply(
			state.shatterEndAnimationStateGround, state.ageInTicks, 1f
		);

		this.stonesFloatAnimation.apply(
			state.stonesFloatAnimationState,
			state.ageInTicks
		);
	}
}
