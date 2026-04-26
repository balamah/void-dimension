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
import net.balamah.voiddim.entity.client.base.BasicLivingEntityModel;
import net.balamah.voiddim.VoidDimension;

public class ShatteredSentinelMasterModel
	extends BasicLivingEntityModel<ShatteredSentinelMasterRenderState>
{
	public static final ModelLayerLocation SHATTERED_SENTINEL_MASTER =
		new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "shattered_sentinel_master"),
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

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-8.0F, -2.0F, -4.0F, 16.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -15.0F, -3.5F, 14.0F, 13.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).addBox(-7.0F, -20.0F, -3.5F, 14.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 42).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, 0.0F));

		PartDefinition stones = body.addOrReplaceChild("stones", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -20.0F, 4.0F, -0.131F, -0.0433F, 0.0057F));

		PartDefinition cube_r1 = stones.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 62).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -3.0F, 3.0F, 0.0452F, 0.2615F, 0.2299F));

		PartDefinition cube_r2 = stones.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 15).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 6.0F, 0.1745F, 0.2182F, 0.0F));

		PartDefinition cube_r3 = stones.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 50).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -4.0F, 6.0F, 0.1273F, -0.5932F, -0.1632F));

		PartDefinition cube_r4 = stones.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(28, 64).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -2.0F, 0.0F, -0.0324F, 1.0763F, -0.2542F));

		PartDefinition cube_r5 = stones.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(28, 62).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -6.0F, 4.0F, 0.4403F, 0.8869F, 0.2924F));

		PartDefinition cube_r6 = stones.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(42, 18).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -6.0F, 0.0F, 0.1273F, 0.5932F, 0.1632F));

		PartDefinition cube_r7 = stones.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(42, 12).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 1.0F, 1.0F, 0.1904F, 0.9812F, 0.2507F));

		PartDefinition cube_r8 = stones.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 58).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, -4.0F, 3.0F, 0.315F, 0.7769F, 0.1298F));

		PartDefinition cube_r9 = stones.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(56, 50).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -6.0F, 4.0F, 0.315F, -0.7769F, -0.1298F));

		PartDefinition lats = body.addOrReplaceChild("lats", CubeListBuilder.create().texOffs(64, 66).addBox(7.0F, -31.0F, -0.5F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 71).addBox(-8.0F, -31.0F, -0.5F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition arms = lats.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -35.0F, 0.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 12).addBox(-1.0F, -2.0F, -3.5F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(66, 24).addBox(0.5F, 3.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 1.0F, 0.0F));

		PartDefinition right_elbow = left_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(16, 66).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 10.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 42).addBox(-5.0F, -2.0F, -3.5F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(66, 0).addBox(-4.5F, 3.0F, -1.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 1.0F, 0.0F));

		PartDefinition left_elbow = right_arm.addOrReplaceChild("left_elbow", CubeListBuilder.create().texOffs(0, 66).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 10.0F, 0.0F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, -2.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 66).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 54).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -1.0F, 2.0F));

		PartDefinition right_calf = right_leg.addOrReplaceChild("right_calf", CubeListBuilder.create().texOffs(42, 0).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(48, 24).addBox(-2.0F, 6.0F, -3.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 1.0F));

		PartDefinition right_foot = right_calf.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(56, 42).addBox(-2.0F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, -1.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 66).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(54, 54).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -1.0F, 2.0F));

		PartDefinition left_calf = left_leg.addOrReplaceChild("left_calf", CubeListBuilder.create().texOffs(66, 34).addBox(-2.0F, 6.0F, -3.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(42, 30).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 1.0F));

		PartDefinition left_foot = left_calf.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(0, 58).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -1.0F));
		return LayerDefinition.create(modelData, 88, 88);
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
