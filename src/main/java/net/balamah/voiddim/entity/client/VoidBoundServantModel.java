package net.balamah.voiddim.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
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

public class VoidBoundServantModel
	extends EntityModel<VoidBoundServantRenderState>
	implements ArmedModel<VoidBoundServantRenderState>
{
    public static final ModelLayerLocation VOID_BOUND_SERVANT =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_bound_servant"),
							 "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart helmet;
	private final ModelPart crown;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart lats;
	private final ModelPart bottom;
	private final ModelPart skirt;
	private final ModelPart decoration;

	private final KeyframeAnimation suicideAnimation;

	public VoidBoundServantModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.helmet = this.head.getChild("helmet");
		this.crown = this.helmet.getChild("crown");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");
		this.lats = this.body.getChild("lats");
		this.bottom = this.root.getChild("bottom");
		this.skirt = this.bottom.getChild("skirt");
		this.decoration = this.bottom.getChild("decoration");

		this.suicideAnimation = VoidBoundServantAnimations.SUICIDE.bake(root);
	}

	@Override
	public void translateToHand(
		VoidBoundServantRenderState state, HumanoidArm arm, PoseStack poseStack
	) {
		this.root.translateAndRotate(poseStack);
		this.body.translateAndRotate(poseStack);
		this.arms.translateAndRotate(poseStack);

		if (arm == HumanoidArm.RIGHT) {
			this.right_arm.translateAndRotate(poseStack);
			this.right_elbow.translateAndRotate(poseStack);

			poseStack.translate(0.08F, -0.1F, 0.0F);
		} else {
			this.left_arm.translateAndRotate(poseStack);
			this.left_elbow.translateAndRotate(poseStack);

			poseStack.translate(-0.08F, -0.1F, 0.0F);
		}
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(30, 41).addBox(-5.0F, -7.0F, -1.0F, 10.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(34, 24).addBox(-6.5F, -13.0F, -1.0F, 13.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 10).addBox(-3.5F, -7.0F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition cube_r1 = helmet.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 50).addBox(-3.0F, -1.0F, -3.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -3.0F, 1.5708F, 0.0F, -1.5708F));

		PartDefinition cube_r2 = helmet.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(64, 36).addBox(-2.0F, -1.0F, -3.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(4.0F, 0.0F, -3.0F, 1.5708F, 0.0F, 1.5708F));

		PartDefinition cube_r3 = helmet.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 38).addBox(-5.0F, -1.0F, -3.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -5.0F, 1.0F, 0.0F, -1.5708F, 3.1416F));

		PartDefinition cube_r4 = helmet.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 33).addBox(-5.0F, -1.0F, -3.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 5.0F, 1.5708F, 0.0F, 1.5708F));

		PartDefinition cube_r5 = helmet.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 0).addBox(-5.0F, -1.0F, -3.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, 0.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r6 = helmet.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 28).addBox(-5.0F, -1.0F, -3.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 0.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition crown = helmet.addOrReplaceChild("crown", CubeListBuilder.create().texOffs(60, 21).addBox(-6.0F, -12.0F, -2.0F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 46).addBox(1.0F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(66, 6).addBox(-2.75F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 71).addBox(-6.0F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(72, 62).addBox(1.0F, -14.0F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(72, 58).addBox(-6.0F, -14.0F, 1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(72, 54).addBox(-6.0F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(46, 71).addBox(-2.25F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(38, 71).addBox(1.0F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(64, 33).addBox(-6.0F, -12.0F, 5.0F, 9.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 6.0F, -2.0F));

		PartDefinition cube_r7 = crown.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(66, 0).addBox(-6.0F, -1.0F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -11.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r8 = crown.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(66, 3).addBox(-6.0F, -1.0F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -11.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(20, 50).addBox(-2.25F, -1.5F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.001F))
		.texOffs(66, 24).addBox(-0.75F, 1.0F, -0.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, 0.0F, 0.0F));

		PartDefinition right_elbow = right_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(60, 10).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.001F))
		.texOffs(64, 54).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, 7.0F, 0.5F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 50).addBox(-2.75F, -1.5F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(-0.001F))
		.texOffs(0, 71).addBox(-1.25F, 1.0F, -0.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.0F, 0.0F));

		PartDefinition left_elbow = left_arm.addOrReplaceChild("left_elbow", CubeListBuilder.create().texOffs(38, 60).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(-0.001F))
		.texOffs(64, 66).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, 7.0F, 0.5F));

		PartDefinition lats = body.addOrReplaceChild("lats", CubeListBuilder.create().texOffs(72, 66).addBox(4.75F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.001F))
		.texOffs(72, 71).addBox(-5.75F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -6.0F, 1.0F));

		PartDefinition bottom = root.addOrReplaceChild("bottom", CubeListBuilder.create(), PartPose.offset(-4.75F, 0.0F, 1.0F));

		PartDefinition skirt = bottom.addOrReplaceChild("skirt", CubeListBuilder.create().texOffs(56, 41).addBox(-0.25F, 6.0F, -2.25F, 10.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(56, 41).addBox(-0.25F, 6.0F, 0.75F, 10.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.25F));

		PartDefinition cube_r9 = skirt.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(54, 69).addBox(-1.0F, -2.0F, 1.0F, 3.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.75F, 8.0F, -0.25F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r10 = skirt.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(54, 60).addBox(-2.0F, -2.0F, 1.0F, 3.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 8.0F, -0.25F, 0.0F, -1.5708F, 0.0F));

		PartDefinition decoration = bottom.addOrReplaceChild("decoration", CubeListBuilder.create().texOffs(60, 50).addBox(-4.25F, -24.0F, -1.0F, 0.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.75F, 30.0F, -1.0F));

		PartDefinition cube_r11 = decoration.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(26, 60).addBox(1.0F, -1.0F, -1.0F, 0.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r12 = decoration.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(16, 0).addBox(-1.0F, -1.0F, -5.0F, 0.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -23.0F, 0.25F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r13 = decoration.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -3.0F, 0.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -23.0F, 0.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r14 = decoration.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(10, 46).addBox(-1.0F, -1.0F, -3.0F, 0.0F, 20.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -23.0F, 1.5F, -0.0662F, -1.1332F, 0.0934F));

		PartDefinition cube_r15 = decoration.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -1.0F, -3.0F, 0.0F, 20.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -23.0F, 0.0F, 0.0436F, 0.8717F, 0.0668F));

		PartDefinition cube_r16 = decoration.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(32, 60).addBox(1.0F, -1.0F, -1.0F, 0.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition cube_r17 = decoration.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(20, 60).addBox(-1.0F, -1.0F, -2.0F, 0.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -23.0F, 1.0F, 0.0F, 0.0F, 0.1745F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(VoidBoundServantRenderState state) {
		super.setupAnim(state);

		this.suicideAnimation.apply(state.suicideAnimationState, state.ageInTicks);

		this.setHeadAngles(state);
	}

	protected void setHeadAngles(VoidBoundServantRenderState state) {
		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
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
