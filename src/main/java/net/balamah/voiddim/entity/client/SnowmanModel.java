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
import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.VoidDimension;

public class SnowmanModel extends EntityModel<BasicRenderState> {
	public static final ModelLayerLocation SNOWMAN =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "snowman"), "main");

	private final ModelPart root;
	private final ModelPart base;
	private final ModelPart lower_sphere;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart ball;
	private final ModelPart nose;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;

	private final KeyframeAnimation poseAnimation;

	public SnowmanModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.base = this.root.getChild("base");
		this.lower_sphere = this.base.getChild("lower_sphere");
		this.body = this.base.getChild("body");
		this.head = this.base.getChild("head");
		this.ball = this.head.getChild("ball");
		this.nose = this.head.getChild("nose");
		this.left_arm = this.root.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");
		this.right_arm = this.root.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");

		this.poseAnimation = SnowmanAnimations.POSE.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(1.0F, 24.0F, -3.0F));

		PartDefinition base = root.addOrReplaceChild("base", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 4.0F));

		PartDefinition lower_sphere = base.addOrReplaceChild("lower_sphere", CubeListBuilder.create().texOffs(0, 56).addBox(-12.0F, -13.0F, 0.0F, 13.0F, 13.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(56, 36).addBox(-12.0F, -14.0F, 0.0F, 13.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-12.0F, -13.0F, -1.0F, 13.0F, 13.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(-14.0F, -11.0F, 2.0F, 17.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 0.0F, -7.0F));

		PartDefinition round_0_2_r1 = lower_sphere.addOrReplaceChild("round_0_2_r1", CubeListBuilder.create().texOffs(56, 18).addBox(-12.0F, -11.0F, 1.0F, 17.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition round_3_r1 = lower_sphere.addOrReplaceChild("round_3_r1", CubeListBuilder.create().texOffs(0, 28).addBox(-12.0F, -1.0F, -3.0F, 13.0F, 13.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -12.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition body = base.addOrReplaceChild("body", CubeListBuilder.create().texOffs(56, 50).addBox(-11.0F, -12.0F, 0.0F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(52, 72).addBox(-12.0F, -11.0F, 1.0F, 13.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -13.0F, -6.0F));

		PartDefinition roundation_r1 = body.addOrReplaceChild("roundation_r1", CubeListBuilder.create().texOffs(84, 90).addBox(-1.0F, -9.0F, 3.0F, 15.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 11.0F, 0.0F, -3.1416F, 0.0F));

		PartDefinition roundation_r2 = body.addOrReplaceChild("roundation_r2", CubeListBuilder.create().texOffs(44, 90).addBox(-14.0F, -9.0F, 3.0F, 15.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition roundation_r3 = body.addOrReplaceChild("roundation_r3", CubeListBuilder.create().texOffs(0, 82).addBox(-12.0F, -11.0F, 1.0F, 13.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition head = base.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -25.0F, 0.0F));

		PartDefinition ball = head.addOrReplaceChild("ball", CubeListBuilder.create().texOffs(96, 72).addBox(-3.0F, -7.0F, -4.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 100).addBox(-2.0F, -6.0F, -5.0F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition roundation_r4 = ball.addOrReplaceChild("roundation_r4", CubeListBuilder.create().texOffs(28, 100).addBox(-6.0F, -9.0F, 1.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 1.0F, -4.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition roundation_r5 = ball.addOrReplaceChild("roundation_r5", CubeListBuilder.create().texOffs(101, 51).addBox(1.0F, -7.0F, -1.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 1.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition roundation_r6 = ball.addOrReplaceChild("roundation_r6", CubeListBuilder.create().texOffs(101, 51).addBox(1.0F, -7.0F, -1.0F, 5.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.offset(7.5F, 2.0F, -7.0F));

		PartDefinition end_r1 = nose.addOrReplaceChild("end_r1", CubeListBuilder.create().texOffs(68, 100).addBox(-0.75F, 0.75F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.75F, -6.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition middle_r1 = nose.addOrReplaceChild("middle_r1", CubeListBuilder.create().texOffs(100, 64).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -6.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition beginning_r1 = nose.addOrReplaceChild("beginning_r1", CubeListBuilder.create().texOffs(44, 82).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -4.0F, 1.25F, 0.1745F, 0.0F, 0.0F));

		PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(96, 86).addBox(0.0F, -0.75F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -23.0F, 3.0F));

		PartDefinition left_elbow = left_arm.addOrReplaceChild("left_elbow", CubeListBuilder.create().texOffs(96, 88).addBox(0.0F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 86).addBox(5.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(100, 68).addBox(7.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(74, 100).addBox(5.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -0.75F, 1.0F));

		PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(100, 57).addBox(-5.0F, -0.75F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, -23.0F, 3.0F));

		PartDefinition right_elbow = right_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(100, 59).addBox(-5.0F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(100, 61).addBox(-7.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(80, 100).addBox(-8.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(86, 100).addBox(-6.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -0.75F, 1.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(BasicRenderState state) {
		super.setupAnim(state);

		this.poseAnimation.apply(state.idleAnimationState, state.ageInTicks);
	}

	protected void setHeadAngles(float headYaw, float headPitch) {}
}
