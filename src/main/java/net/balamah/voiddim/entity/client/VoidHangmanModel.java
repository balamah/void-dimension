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

public class VoidHangmanModel extends EntityModel<VoidHangmanRenderState> {
	public static final ModelLayerLocation VOID_HANGMAN =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_hangman"),
							 "main");

	private final ModelPart root;
	private final ModelPart chain;
	private final ModelPart hanging_chain;
	private final ModelPart hanging_chain_1;
	private final ModelPart hanging_chain_3;
	private final ModelPart hanging_chain_2;
	private final ModelPart head;
	private final ModelPart upper_head;
	private final ModelPart jaw;
	private final ModelPart body;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart lower_body;
	private final ModelPart skirt;
	private final ModelPart miasma;
	private final ModelPart right_elbow2;

	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation attackAnimation;

	public VoidHangmanModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.chain = this.root.getChild("chain");
		this.hanging_chain = this.chain.getChild("hanging_chain");
		this.hanging_chain_1 = this.hanging_chain.getChild("hanging_chain_1");
		this.hanging_chain_3 = this.hanging_chain_1.getChild("hanging_chain_3");
		this.hanging_chain_2 = this.hanging_chain_3.getChild("hanging_chain_2");
		this.head = this.root.getChild("head");
		this.upper_head = this.head.getChild("upper_head");
		this.jaw = this.head.getChild("jaw");
		this.body = this.root.getChild("body");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");
		this.lower_body = this.body.getChild("lower_body");
		this.skirt = this.lower_body.getChild("skirt");
		this.miasma = this.lower_body.getChild("miasma");
		this.right_elbow2 = root.getChild("right_elbow2");

		this.walkAnimation = VoidHangmanAnimations.WALK.bake(root);
		this.attackAnimation = VoidHangmanAnimations.ATTACK.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition chain = root.addOrReplaceChild("chain", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.75F));

		PartDefinition cube_r1 = chain.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 39).addBox(-1.5659F, -2.1495F, -0.198F, 4.0F, 3.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.75F, -27.25F, 3.0F, 0.2786F, -0.4071F, -3.0287F));

		PartDefinition cube_r2 = chain.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(46, 36).addBox(-2.4341F, -2.1495F, -0.198F, 4.0F, 3.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.75F, -27.25F, 3.0F, 0.2786F, 0.4071F, 3.0287F));

		PartDefinition cube_r3 = chain.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(46, 27).addBox(-4.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.25F, -25.5F, 1.0F, -0.3927F, -1.1781F, -1.5708F));

		PartDefinition cube_r4 = chain.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(46, 24).addBox(-2.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -25.5F, 1.0F, -0.3927F, 1.1781F, 1.5708F));

		PartDefinition cube_r5 = chain.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(46, 33).addBox(-3.0F, -2.0F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(2.933F, -22.75F, -2.25F, 0.0583F, -0.0603F, 2.6651F));

		PartDefinition cube_r6 = chain.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(46, 30).addBox(-3.0F, -2.0F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.75F, -21.5F, -2.0F, 0.1135F, -0.0653F, 0.5199F));

		PartDefinition cube_r7 = chain.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(46, 21).addBox(-4.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -21.5F, -2.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition hanging_chain = chain.addOrReplaceChild("hanging_chain", CubeListBuilder.create(), PartPose.offset(-0.5F, -40.25F, 4.5F));

		PartDefinition hanging_chain_1 = hanging_chain.addOrReplaceChild("hanging_chain_1", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, -1.0F));

		PartDefinition cube_r8 = hanging_chain_1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 19).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.75F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition hanging_chain_3 = hanging_chain_1.addOrReplaceChild("hanging_chain_3", CubeListBuilder.create(), PartPose.offset(0.0F, -4.75F, 0.0F));

		PartDefinition cube_r9 = hanging_chain_3.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(46, 11).addBox(-1.0F, -10.0F, -1.0F, 3.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 1.0F, 0.0F, 0.0F, -0.0873F));

		PartDefinition hanging_chain_2 = hanging_chain_3.addOrReplaceChild("hanging_chain_2", CubeListBuilder.create(), PartPose.offset(0.0F, -9.25F, 0.0F));

		PartDefinition cube_r10 = hanging_chain_2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(8, 35).addBox(-1.8716F, -12.038F, -1.0F, 3.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0873F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition upper_head = head.addOrReplaceChild("upper_head", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 32).addBox(-2.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F))
		.texOffs(24, 35).addBox(-0.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F))
		.texOffs(20, 43).addBox(1.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -0.75F, 3.5F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(22, 0).addBox(-3.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(46, 42).addBox(-1.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F))
		.texOffs(20, 46).addBox(0.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -0.75F, 3.5F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(38, 8).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 0.0472F, -0.3923F, 0.331F));

		PartDefinition right_elbow = right_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 8.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r11 = right_elbow.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, -0.3054F, 0.0F, 0.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 38).addBox(0.0F, 0.0F, -0.5F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.3938F, -0.114F, -0.2146F));

		PartDefinition left_elbow = left_arm.addOrReplaceChild("left_elbow", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 8.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r12 = left_elbow.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(24, 38).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, -0.2618F, 0.0F, 0.0F));

		PartDefinition lower_body = body.addOrReplaceChild("lower_body", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition skirt = lower_body.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(40, 38).addBox(-4.0F, -4.0F, -1.0F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.001F))
		.texOffs(40, 27).addBox(4.0F, -4.0F, -1.0F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.001F))
		.texOffs(30, 19).addBox(-4.0F, -4.0F, 2.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.001F))
		.texOffs(14, 24).addBox(-4.0F, -4.0F, -1.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition miasma = lower_body.addOrReplaceChild("miasma", CubeListBuilder.create().texOffs(0, 24).addBox(-3.25F, -11.0F, 1.5F, 7.0F, 11.0F, 0.0F, new CubeDeformation(-0.001F))
		.texOffs(24, 8).addBox(-3.25F, -11.0F, -0.75F, 7.0F, 11.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, 11.0F, 0.0F));

		PartDefinition cube_r13 = miasma.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(46, 0).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 11.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(3.25F, -11.0F, 0.0F, 0.0F, 1.5708F, -0.0873F));

		PartDefinition cube_r14 = miasma.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(14, 43).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 11.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-3.25F, -11.0F, 0.0F, 0.0F, -1.5708F, 0.0873F));

		PartDefinition cube_r15 = miasma.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(14, 32).addBox(-3.0F, -11.0F, 0.0F, 5.0F, 11.0F, 0.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.25F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		// Empty helper bone exported as a sibling of "root"; kept so the structure matches the original.
		PartDefinition right_elbow2 = modelPartData.addOrReplaceChild("right_elbow2", CubeListBuilder.create(), PartPose.offset(-5.0F, 8.0F, 1.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(VoidHangmanRenderState state) {
		super.setupAnim(state);

		// Walk cycle, driven by how far/fast the entity is moving.
		this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0F, 2.5F);

		this.attackAnimation.apply(state.attackAnimationState, state.ageInTicks);
	}
}
