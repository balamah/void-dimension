package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.Identifier;
import net.minecraft.client.model.*;

import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.VoidDimension;

public class SnowmanModel extends EntityModel<BasicRenderState> {
	public static final EntityModelLayer SNOWMAN =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "snowman"), "main");

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

	private final Animation poseAnimation;

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

		this.poseAnimation = SnowmanAnimations.POSE.createAnimation(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.origin(1.0F, 24.0F, -3.0F));

		ModelPartData base = root.addChild("base", ModelPartBuilder.create(), ModelTransform.origin(-1.0F, 0.0F, 4.0F));

		ModelPartData lower_sphere = base.addChild("lower_sphere", ModelPartBuilder.create().uv(0, 56).cuboid(-12.0F, -13.0F, 0.0F, 13.0F, 13.0F, 13.0F, new Dilation(0.0F))
		.uv(56, 36).cuboid(-12.0F, -14.0F, 0.0F, 13.0F, 1.0F, 13.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-12.0F, -13.0F, -1.0F, 13.0F, 13.0F, 15.0F, new Dilation(0.0F))
		.uv(56, 0).cuboid(-14.0F, -11.0F, 2.0F, 17.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.origin(6.0F, 0.0F, -7.0F));

		ModelPartData round_0_2_r1 = lower_sphere.addChild("round_0_2_r1", ModelPartBuilder.create().uv(56, 18).cuboid(-12.0F, -11.0F, 1.0F, 17.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-11.0F, 0.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData round_3_r1 = lower_sphere.addChild("round_3_r1", ModelPartBuilder.create().uv(0, 28).cuboid(-12.0F, -1.0F, -3.0F, 13.0F, 13.0F, 15.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, -12.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData body = base.addChild("body", ModelPartBuilder.create().uv(56, 50).cuboid(-11.0F, -12.0F, 0.0F, 11.0F, 11.0F, 11.0F, new Dilation(0.0F))
		.uv(52, 72).cuboid(-12.0F, -11.0F, 1.0F, 13.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.origin(6.0F, -13.0F, -6.0F));

		ModelPartData roundation_r1 = body.addChild("roundation_r1", ModelPartBuilder.create().uv(84, 90).cuboid(-1.0F, -9.0F, 3.0F, 15.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 11.0F, 0.0F, -3.1416F, 0.0F));

		ModelPartData roundation_r2 = body.addChild("roundation_r2", ModelPartBuilder.create().uv(44, 90).cuboid(-14.0F, -9.0F, 3.0F, 15.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-11.0F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData roundation_r3 = body.addChild("roundation_r3", ModelPartBuilder.create().uv(0, 82).cuboid(-12.0F, -11.0F, 1.0F, 13.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-11.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData head = base.addChild("head", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -25.0F, 0.0F));

		ModelPartData ball = head.addChild("ball", ModelPartBuilder.create().uv(96, 72).cuboid(-3.0F, -7.0F, -4.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 100).cuboid(-2.0F, -6.0F, -5.0F, 5.0F, 5.0F, 9.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData roundation_r4 = ball.addChild("roundation_r4", ModelPartBuilder.create().uv(28, 100).cuboid(-6.0F, -9.0F, 1.0F, 5.0F, 7.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 1.0F, -4.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData roundation_r5 = ball.addChild("roundation_r5", ModelPartBuilder.create().uv(101, 51).cuboid(1.0F, -7.0F, -1.0F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 1.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData roundation_r6 = ball.addChild("roundation_r6", ModelPartBuilder.create().uv(101, 51).cuboid(1.0F, -7.0F, -1.0F, 5.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 1.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData nose = head.addChild("nose", ModelPartBuilder.create(), ModelTransform.origin(7.5F, 2.0F, -7.0F));

		ModelPartData end_r1 = nose.addChild("end_r1", ModelPartBuilder.create().uv(68, 100).cuboid(-0.75F, 0.75F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-6.75F, -6.0F, 0.0F, 0.4363F, 0.0F, 0.0F));

		ModelPartData middle_r1 = nose.addChild("middle_r1", ModelPartBuilder.create().uv(100, 64).cuboid(-1.0F, 0.0F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-6.5F, -6.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		ModelPartData beginning_r1 = nose.addChild("beginning_r1", ModelPartBuilder.create().uv(44, 82).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -4.0F, 1.25F, 0.1745F, 0.0F, 0.0F));

		ModelPartData left_arm = root.addChild("left_arm", ModelPartBuilder.create().uv(96, 86).cuboid(0.0F, -0.75F, 0.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(6.0F, -23.0F, 3.0F));

		ModelPartData left_elbow = left_arm.addChild("left_elbow", ModelPartBuilder.create().uv(96, 88).cuboid(0.0F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(44, 86).cuboid(5.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(100, 68).cuboid(7.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(74, 100).cuboid(5.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(5.0F, -0.75F, 1.0F));

		ModelPartData right_arm = root.addChild("right_arm", ModelPartBuilder.create().uv(100, 57).cuboid(-5.0F, -0.75F, 0.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(-7.0F, -23.0F, 3.0F));

		ModelPartData right_elbow = right_arm.addChild("right_elbow", ModelPartBuilder.create().uv(100, 59).cuboid(-5.0F, 0.0F, -1.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(100, 61).cuboid(-7.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(80, 100).cuboid(-8.0F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(86, 100).cuboid(-6.0F, 0.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-5.0F, -0.75F, 1.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(BasicRenderState state) {
		super.setAngles(state);

		this.poseAnimation.apply(state.idleAnimationState, state.age);
	}

	protected void setHeadAngles(float headYaw, float headPitch) {}
}
