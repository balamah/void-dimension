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

public class ShatteredSentinelModel extends BasicLivingEntityModel<ShatteredSentinelRenderState> {
    public static final ModelLayerLocation SHATTERED_SENTINEL =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID,
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

	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation attackAnimation;
	private final KeyframeAnimation idleAnimation;
	private final KeyframeAnimation stonesFloatAnimation;

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

		this.walkAnimation = ShatteredSentinelMasterAnimations.WALK.bake(root);
		this.attackAnimation = ShatteredSentinelMasterAnimations.ATTACK.bake(root);
		this.idleAnimation = ShatteredSentinelMasterAnimations.IDLE.bake(root);
		this.stonesFloatAnimation = ShatteredSentinelMasterAnimations.STONES_FLOAT.bake(root);

	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 13).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -25.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(14, 29).addBox(-0.75F, 0.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-2.25F, 0.0F, -4.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.25F, 1.0F, 2.5F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -22.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(20, 19).addBox(-3.0F, -22.0F, -3.0F, 2.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(14, 22).addBox(-3.0F, -21.0F, 2.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 5).addBox(4.0F, -16.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(4.0F, -21.0F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(26, 31).addBox(4.0F, -22.0F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(14, 31).addBox(-5.0F, -22.0F, -3.0F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 33).addBox(-5.0F, -20.0F, 1.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 5).addBox(-4.0F, -20.0F, 2.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 17).addBox(-5.0F, -19.0F, 2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 25).addBox(-4.0F, -22.0F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition stones = body.addOrReplaceChild("stones", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, 0.0F));

		PartDefinition cube_r1 = stones.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(34, 28).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -3.0F, 3.0F, 0.0452F, 0.2615F, 0.2299F));

		PartDefinition cube_r2 = stones.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 39).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -8.0F, 6.0F, 0.1745F, 0.2182F, 0.0F));

		PartDefinition cube_r3 = stones.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(42, 37).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -4.0F, 6.0F, 0.1273F, -0.5932F, -0.1632F));

		PartDefinition cube_r4 = stones.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(42, 39).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -2.0F, 0.0F, -0.0324F, 1.0763F, -0.2542F));

		PartDefinition cube_r5 = stones.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(42, 41).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -6.0F, 4.0F, 0.4403F, 0.8869F, 0.2924F));

		PartDefinition cube_r6 = stones.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(36, 11).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -6.0F, 0.0F, 0.1273F, 0.5932F, 0.1632F));

		PartDefinition cube_r7 = stones.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(42, 22).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 1.0F, 1.0F, 0.1904F, 0.9812F, 0.2507F));

		PartDefinition cube_r8 = stones.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(16, 42).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, -4.0F, 3.0F, 0.315F, 0.7769F, 0.1298F));

		PartDefinition cube_r9 = stones.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(8, 42).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -6.0F, 4.0F, 0.315F, -0.7769F, -0.1298F));

		PartDefinition pelvis = body.addOrReplaceChild("pelvis", CubeListBuilder.create().texOffs(20, 13).addBox(-3.0F, -15.0F, -2.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(40, 9).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition left_leg_calves = left_leg.addOrReplaceChild("left_leg_calves", CubeListBuilder.create().texOffs(0, 33).addBox(-4.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 6.0F, 0.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition right_leg_calves = right_leg.addOrReplaceChild("right_leg_calves", CubeListBuilder.create().texOffs(34, 19).addBox(2.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 6.0F, 0.0F));

		PartDefinition arms = root.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -22.0F, 0.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(34, 37).addBox(-3.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

		PartDefinition left_arm_elbow = left_arm.addOrReplaceChild("left_arm_elbow", CubeListBuilder.create().texOffs(26, 37).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 7.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(38, 28).addBox(1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, 0.0F));

		PartDefinition right_arm_elbow = right_arm.addOrReplaceChild("right_arm_elbow", CubeListBuilder.create().texOffs(40, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 7.0F, 0.0F));
		return LayerDefinition.create(modelData, 52, 52);
	}

	    @Override
	    public void setupAnim(ShatteredSentinelRenderState state) {
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

		this.stonesFloatAnimation.apply(
			state.stonesFloatAnimationState, state.ageInTicks
		);
    }
}
