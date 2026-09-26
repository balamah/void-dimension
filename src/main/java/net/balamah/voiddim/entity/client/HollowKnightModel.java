package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import org.joml.Quaternionfc;

import net.balamah.voiddim.VoidDimension;
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
import net.minecraft.world.entity.HumanoidArm;
   
public class HollowKnightModel
	extends EntityModel<HollowKnightRenderState>
	implements ArmedModel<HollowKnightRenderState>
{
    public static final ModelLayerLocation HOLLOW_KNIGHT =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "hollow_knight"),
							 "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart horns;
	private final ModelPart right_horn;
	private final ModelPart left_horn;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart cover;
	private final ModelPart right_cover;
	private final ModelPart right_front_cover;
	private final ModelPart left_cover;
	private final ModelPart left_front_cover;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	private final KeyframeAnimation walkingAnimation;
	private final KeyframeAnimation attack1Animation;
	private final KeyframeAnimation attack2Animation;
	private final KeyframeAnimation attack3Animation;
	private final KeyframeAnimation attack4Animation;
	private final KeyframeAnimation vengefulSpiritAnimation;

	public HollowKnightModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");

		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.horns = this.head.getChild("horns");
		this.right_horn = this.horns.getChild("right_horn");
		this.left_horn = this.horns.getChild("left_horn");

		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");

		this.cover = this.body.getChild("cover");
		this.right_cover = this.cover.getChild("right_cover");
		this.right_front_cover = this.right_cover.getChild("right_front_cover");
		this.left_cover = this.cover.getChild("left_cover");
		this.left_front_cover = this.left_cover.getChild("left_front_cover");

		this.legs = this.root.getChild("legs");

		this.right_leg = this.legs.getChild("right_leg");
		this.left_leg = this.legs.getChild("left_leg");

		this.walkingAnimation = HollowKnightAnimations.WALKING.bake(root);
		this.attack1Animation = HollowKnightAnimations.ATTACK_1.bake(root);
		this.attack2Animation = HollowKnightAnimations.ATTACK_2.bake(root);
		this.attack3Animation = HollowKnightAnimations.ATTACK_3.bake(root);
		this.attack4Animation = HollowKnightAnimations.ATTACK_4.bake(root);
		this.vengefulSpiritAnimation = HollowKnightAnimations.VENGEFUL_SPIRIT.bake(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -8.0F, -1.0F, 5.0F, 8.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -2.75F, 8.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -8.0F, -0.25F));

		PartDefinition horns = head.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(0.75F, 15.0F, 1.0F));

		PartDefinition right_horn = horns.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.75F, -21.75F, 0.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition cube_r1 = right_horn.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 29).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.75F, -3.75F, 0.5F, 0.0F, 0.0F, 1.309F));

		PartDefinition cube_r2 = right_horn.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(42, 31).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.25F, -4.25F, 0.0F, 0.0F, 0.0F, 1.1345F));

		PartDefinition left_horn = horns.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(42, 38).addBox(0.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -21.75F, 0.0F, 0.0F, 0.0F, 0.7418F));

		PartDefinition cube_r3 = left_horn.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 44).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.75F, -3.25F, 0.5F, 0.0F, 0.0F, -1.1345F));

		PartDefinition cube_r4 = left_horn.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(20, 44).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(1.25F, -4.25F, 0.0F, 0.0F, 0.0F, -1.1345F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(38, 17).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition right_elbow = right_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(38, 10).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 4.0F, 1.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(38, 24).addBox(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 0.0F, 0.0F));

		PartDefinition left_elbow = left_arm.addOrReplaceChild("left_elbow", CubeListBuilder.create().texOffs(10, 39).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 4.0F, 1.0F));

		PartDefinition cover = body.addOrReplaceChild("cover", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition right_cover = cover.addOrReplaceChild("right_cover", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = right_cover.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(42, 0).addBox(-2.0F, -3.0F, -3.0F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 3.5F, 1.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r6 = right_cover.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(26, 24).addBox(0.0F, 0.0F, 1.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-4.0F, 0.25F, 2.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r7 = right_cover.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(16, 14).addBox(-1.0F, 0.0F, -3.0F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.25F, 1.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition right_front_cover = right_cover.addOrReplaceChild("right_front_cover", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.25F, -2.0F));

		PartDefinition cube_r8 = right_front_cover.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(26, 14).addBox(0.0F, 0.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition left_cover = cover.addOrReplaceChild("left_cover", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition cube_r9 = left_cover.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(42, 5).addBox(1.0F, -3.0F, -3.0F, 1.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.25F, 3.75F, 1.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r10 = left_cover.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(30, 0).addBox(-7.0F, 0.0F, 1.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(5.75F, 0.25F, 2.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r11 = left_cover.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 25).addBox(1.0F, 0.0F, -3.0F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.75F, 0.5F, 1.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition left_front_cover = left_cover.addOrReplaceChild("left_front_cover", CubeListBuilder.create(), PartPose.offset(5.75F, 0.25F, -2.0F));

		PartDefinition cube_r12 = left_front_cover.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(10, 29).addBox(-7.0F, 0.0F, 0.0F, 6.0F, 10.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(22, 34).addBox(-1.0F, 1.0F, -2.0F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 1.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 34).addBox(0.0F, 1.0F, -1.0F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(HollowKnightRenderState state) {
		super.setupAnim(state);

		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);

		this.walkingAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);

		this.attack1Animation.apply(state.attack1State, state.ageInTicks);
		this.attack2Animation.apply(state.attack2State, state.ageInTicks);
		this.attack3Animation.apply(state.attack3State, state.ageInTicks);
		this.attack4Animation.apply(state.attack4State, state.ageInTicks);
		this.vengefulSpiritAnimation.apply(state.vengefulSpiritState, state.ageInTicks);
	}

	// TODO: Change fucking sword rotation
	@Override
	public void translateToHand(
		HollowKnightRenderState state, HumanoidArm arm, PoseStack poseStack
	) {
		this.root.translateAndRotate(poseStack);
		this.body.translateAndRotate(poseStack);
		this.arms.translateAndRotate(poseStack);

		if (state.isHurting && arm == HumanoidArm.RIGHT) {
			this.right_arm.translateAndRotate(poseStack);
			this.right_elbow.translateAndRotate(poseStack);
			poseStack.translate(0.08F, -0.3f, 0.0F);
		} else {
			poseStack.translate(0.8F, -0.1F, 0.25F);
			poseStack.mulPose(Axis.XP.rotationDegrees(90f));
			poseStack.mulPose(Axis.ZP.rotationDegrees(90f));
		}
	}

	public ModelPart getRightHand() {
		return this.right_elbow;
	}

	public ModelPart getLeftHand() {
		return this.left_elbow;
	}

	protected ModelPart getAttackingArm(HumanoidArm arm) {
		return arm == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
	}
}
