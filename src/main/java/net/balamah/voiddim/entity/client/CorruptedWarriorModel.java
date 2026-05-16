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

public class CorruptedWarriorModel extends EntityModel<CorruptedWarriorRenderState> {
    public static final ModelLayerLocation CORRUPTED_WARRIOR =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_warrior"),
							 "main");

	private final KeyframeAnimation idleAnimation;
	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation strongAttackAnimation;
	private final KeyframeAnimation strongestAttackAnimation;
	private final KeyframeAnimation summonProjectileAnimation;
	private final KeyframeAnimation specialAttackAnimation;
	private final KeyframeAnimation normalAttack1Animation;
	private final KeyframeAnimation normalAttack2Animation;
	private final KeyframeAnimation normalAttack3Animation;

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart helmet;
	private final ModelPart main_helmet_part;
	private final ModelPart crown;
	private final ModelPart torso;
	private final ModelPart lats;
	private final ModelPart body_decoration;
	private final ModelPart cape;
	private final ModelPart skirt;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart sword;
	private final ModelPart grip;
	private final ModelPart guard;
	private final ModelPart blade;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart right_calf;
	private final ModelPart left_leg;
	private final ModelPart left_calf;

	public CorruptedWarriorModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.helmet = this.head.getChild("helmet");
		this.main_helmet_part = this.helmet.getChild("main_helmet_part");
		this.crown = this.helmet.getChild("crown");
		this.torso = this.body.getChild("torso");
		this.lats = this.torso.getChild("lats");
		this.body_decoration = this.torso.getChild("body_decoration");
		this.cape = this.body_decoration.getChild("cape");
		this.skirt = this.body_decoration.getChild("skirt");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.sword = this.right_elbow.getChild("sword");
		this.grip = this.sword.getChild("grip");
		this.guard = this.grip.getChild("guard");
		this.blade = this.sword.getChild("blade");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.right_calf = this.right_leg.getChild("right_calf");
		this.left_leg = this.legs.getChild("left_leg");
		this.left_calf = this.left_leg.getChild("left_calf");

		this.idleAnimation = CorruptedWarriorAnimations.IDLE.bake(root);
		this.walkAnimation = CorruptedWarriorAnimations.WALK.bake(root);
		this.strongAttackAnimation = CorruptedWarriorAnimations.STRONG_ATTACK.bake(root);
		this.strongestAttackAnimation = CorruptedWarriorAnimations.STRONGEST_ATTACK.bake(root);
		this.summonProjectileAnimation = CorruptedWarriorAnimations.SUMMON_PROJECTILE.bake(root);
		this.specialAttackAnimation = CorruptedWarriorAnimations.SPECIAL_ATTACK.bake(root);
		this.normalAttack1Animation = CorruptedWarriorAnimations.NORMAL_ATTACK_1.bake(root);
		this.normalAttack2Animation = CorruptedWarriorAnimations.NORMAL_ATTACK_2.bake(root);
		this.normalAttack3Animation = CorruptedWarriorAnimations.NORMAL_ATTACK_3.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 55).addBox(-5.0F, -10.0F, -4.75F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -20.0F, -0.25F));

		PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 0.0F, 4.25F, 0.0F, 3.1416F, 0.0F));

		PartDefinition main_helmet_part = helmet.addOrReplaceChild("main_helmet_part", CubeListBuilder.create().texOffs(54, 0).addBox(-7.0F, -11.0F, -2.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(100, 70).addBox(-6.0F, -10.5F, 9.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 101).addBox(3.25F, -9.0F, 9.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(104, 102).addBox(-6.25F, -9.0F, 9.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 100).addBox(0.0F, -6.0F, 9.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 101).addBox(-6.0F, -6.0F, 9.0F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(54, 13).addBox(-7.0F, -10.0F, -2.0F, 12.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = main_helmet_part.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 89).addBox(-6.0F, -10.0F, 0.0F, 11.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = main_helmet_part.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(88, 27).addBox(-6.0F, -10.0F, 0.0F, 11.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 0.0F, 4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition crown = helmet.addOrReplaceChild("crown", CubeListBuilder.create().texOffs(88, 99).addBox(-7.0F, -12.0F, 8.0F, 12.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(100, 73).addBox(-5.0F, -12.0F, -2.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(80, 20).addBox(3.0F, -14.0F, 8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(102, 0).addBox(3.0F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(102, 4).addBox(3.0F, -14.0F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(102, 8).addBox(3.0F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 103).addBox(-7.0F, -14.0F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(96, 102).addBox(-7.0F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(88, 102).addBox(-7.0F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(100, 80).addBox(-0.25F, -14.0F, 8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 101).addBox(-3.75F, -14.0F, 8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(64, 103).addBox(-0.25F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 103).addBox(-3.75F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(64, 81).addBox(-7.0F, -14.0F, 8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = crown.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(100, 67).addBox(-4.0F, -1.0F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -11.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = crown.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(64, 100).addBox(-5.0F, -1.0F, -1.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -11.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(40, 40).addBox(1.0F, -3.0F, -5.0F, 16.0F, 11.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(40, 25).addBox(0.0F, -12.0F, -5.0F, 18.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -8.0F, 2.0F));

		PartDefinition lats = torso.addOrReplaceChild("lats", CubeListBuilder.create().texOffs(64, 74).addBox(0.0F, -3.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(80, 13).addBox(17.0F, -3.0F, -1.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_decoration = torso.addOrReplaceChild("body_decoration", CubeListBuilder.create(), PartPose.offset(9.0F, -12.0F, 1.0F));

		PartDefinition cape = body_decoration.addOrReplaceChild("cape", CubeListBuilder.create().texOffs(0, 25).addBox(-9.5F, 0.0F, 1.0F, 19.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition skirt = body_decoration.addOrReplaceChild("skirt", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, -4.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(72, 57).addBox(-6.5F, -1.0F, -3.75F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(22, 100).addBox(-5.0F, 2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.25F, 0.0F, 0.0F));

		PartDefinition right_elbow = right_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(0, 75).addBox(-3.0F, -0.25F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 75).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 10.0F, 0.0F));

		PartDefinition sword = right_elbow.addOrReplaceChild("sword", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition grip = sword.addOrReplaceChild("grip", CubeListBuilder.create().texOffs(100, 76).addBox(-6.0F, 16.0F, -6.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 90).addBox(-4.0F, 16.0F, -4.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -18.0F, 0.0F));

		PartDefinition guard = grip.addOrReplaceChild("guard", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition blade = sword.addOrReplaceChild("blade", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, 16.5F, -30.0F, 3.0F, 1.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(72, 103).addBox(-3.5F, 16.5F, -33.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(80, 103).addBox(-4.0F, 16.5F, -31.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -18.0F, 0.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(72, 71).addBox(-0.5F, -1.0F, -3.75F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(100, 55).addBox(1.0F, 2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(8.25F, 0.0F, 0.0F));

		PartDefinition left_elbow = left_arm.addOrReplaceChild("left_elbow", CubeListBuilder.create().texOffs(84, 40).addBox(-3.0F, -0.25F, -3.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(48, 89).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 10.0F, 0.0F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -16.0F, 0.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(88, 13).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -1.0F, 0.0F));

		PartDefinition right_calf = right_leg.addOrReplaceChild("right_calf", CubeListBuilder.create().texOffs(40, 74).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(40, 57).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -3.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(88, 85).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -1.0F, 0.0F));

		PartDefinition left_calf = left_leg.addOrReplaceChild("left_calf", CubeListBuilder.create().texOffs(64, 85).addBox(-3.0F, 0.0F, -1.0F, 6.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(40, 57).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -2.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(CorruptedWarriorRenderState state) {
		super.setupAnim(state);

		this.walkAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);

		this.strongAttackAnimation.apply(state.strongAttackAnimationState, state.ageInTicks, 1f);

		this.strongestAttackAnimation.apply(
			state.strongestAttackAnimationState, state.ageInTicks, 1f
		);

		this.summonProjectileAnimation.apply(
			state.summonProjectileAnimationState, state.ageInTicks, 1f
		);

		this.specialAttackAnimation.apply(state.specialAttackAnimationState, state.ageInTicks, 1f);
		this.normalAttack1Animation.apply(state.normalAttack1AnimationState, state.ageInTicks, 1f);
		this.normalAttack2Animation.apply(state.normalAttack2AnimationState, state.ageInTicks, 1f);
		this.normalAttack3Animation.apply(state.normalAttack3AnimationState, state.ageInTicks, 1f);

        this.setHeadAngles(state.yRot, state.xRot);
	}

	protected void setHeadAngles(float headYaw, float headPitch) {
		float yawRad = headYaw * ((float)Math.PI / 180F);
		float pitchRad = headPitch * ((float)Math.PI / 180F);

		this.head.yRot = yawRad;
		this.head.xRot = pitchRad;
	}
}
