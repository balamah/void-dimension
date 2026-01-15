package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.Dilation;
import net.minecraft.util.Identifier;
import net.minecraft.util.Arm;

import net.balamah.voiddim.VoidDimension;

public class VoidBoundServantModel
	extends EntityModel<VoidBoundServantRenderState> implements ModelWithArms
{
    public static final EntityModelLayer VOID_BOUND_SERVANT =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "void_bound_servant"),
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

	private final Animation suicideAnimation;

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

		this.suicideAnimation = VoidBoundServantAnimations.SUICIDE.createAnimation(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 5.0F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(30, 41).cuboid(-5.0F, -7.0F, -1.0F, 10.0F, 6.0F, 3.0F, new Dilation(0.0F))
		.uv(34, 24).cuboid(-6.5F, -13.0F, -1.0F, 13.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 7.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(32, 10).cuboid(-3.5F, -7.0F, -3.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -13.0F, 0.0F));

		ModelPartData helmet = head.addChild("helmet", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -3.0F, 0.0F));

		ModelPartData cube_r1 = helmet.addChild("cube_r1", ModelPartBuilder.create().uv(64, 50).cuboid(-3.0F, -1.0F, -3.0F, 5.0F, 1.0F, 3.0F, new Dilation(-0.001F)), ModelTransform.of(-4.0F, 0.0F, -3.0F, 1.5708F, 0.0F, -1.5708F));

		ModelPartData cube_r2 = helmet.addChild("cube_r2", ModelPartBuilder.create().uv(64, 36).cuboid(-2.0F, -1.0F, -3.0F, 5.0F, 1.0F, 3.0F, new Dilation(-0.001F)), ModelTransform.of(4.0F, 0.0F, -3.0F, 1.5708F, 0.0F, 1.5708F));

		ModelPartData cube_r3 = helmet.addChild("cube_r3", ModelPartBuilder.create().uv(0, 38).cuboid(-5.0F, -1.0F, -3.0F, 8.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -5.0F, 1.0F, 0.0F, -1.5708F, 3.1416F));

		ModelPartData cube_r4 = helmet.addChild("cube_r4", ModelPartBuilder.create().uv(34, 33).cuboid(-5.0F, -1.0F, -3.0F, 8.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 0.0F, 5.0F, 1.5708F, 0.0F, 1.5708F));

		ModelPartData cube_r5 = helmet.addChild("cube_r5", ModelPartBuilder.create().uv(32, 0).cuboid(-5.0F, -1.0F, -3.0F, 8.0F, 1.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-4.5F, 0.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

		ModelPartData cube_r6 = helmet.addChild("cube_r6", ModelPartBuilder.create().uv(0, 28).cuboid(-5.0F, -1.0F, -3.0F, 8.0F, 1.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(3.5F, 0.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

		ModelPartData crown = helmet.addChild("crown", ModelPartBuilder.create().uv(60, 21).cuboid(-6.0F, -12.0F, -2.0F, 9.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(20, 46).cuboid(1.0F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(66, 6).cuboid(-2.75F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 71).cuboid(-6.0F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(72, 62).cuboid(1.0F, -14.0F, 1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(72, 58).cuboid(-6.0F, -14.0F, 1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(72, 54).cuboid(-6.0F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(46, 71).cuboid(-2.25F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(38, 71).cuboid(1.0F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(64, 33).cuboid(-6.0F, -12.0F, 5.0F, 9.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(1.5F, 6.0F, -2.0F));

		ModelPartData cube_r7 = crown.addChild("cube_r7", ModelPartBuilder.create().uv(66, 0).cuboid(-6.0F, -1.0F, -1.0F, 5.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -11.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r8 = crown.addChild("cube_r8", ModelPartBuilder.create().uv(66, 3).cuboid(-6.0F, -1.0F, -1.0F, 5.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -11.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -13.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(20, 50).cuboid(-2.25F, -1.5F, -2.0F, 5.0F, 5.0F, 5.0F, new Dilation(-0.001F))
		.uv(66, 24).cuboid(-0.75F, 1.0F, -0.5F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-8.0F, 0.0F, 0.0F));

		ModelPartData right_elbow = right_arm.addChild("right_elbow", ModelPartBuilder.create().uv(60, 10).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(-0.001F))
		.uv(64, 54).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.25F, 7.0F, 0.5F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(40, 50).cuboid(-2.75F, -1.5F, -2.0F, 5.0F, 5.0F, 5.0F, new Dilation(-0.001F))
		.uv(0, 71).cuboid(-1.25F, 1.0F, -0.5F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(8.0F, 0.0F, 0.0F));

		ModelPartData left_elbow = left_arm.addChild("left_elbow", ModelPartBuilder.create().uv(38, 60).cuboid(-2.0F, 2.0F, -2.0F, 4.0F, 7.0F, 4.0F, new Dilation(-0.001F))
		.uv(64, 66).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-0.25F, 7.0F, 0.5F));

		ModelPartData lats = body.addChild("lats", ModelPartBuilder.create().uv(72, 66).cuboid(4.75F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.001F))
		.uv(72, 71).cuboid(-5.75F, -1.0F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.001F)), ModelTransform.origin(0.0F, -6.0F, 1.0F));

		ModelPartData bottom = root.addChild("bottom", ModelPartBuilder.create(), ModelTransform.origin(-4.75F, 0.0F, 1.0F));

		ModelPartData skirt = bottom.addChild("skirt", ModelPartBuilder.create().uv(56, 41).cuboid(-0.25F, 6.0F, -2.25F, 10.0F, 9.0F, 0.0F, new Dilation(0.0F))
		.uv(56, 41).cuboid(-0.25F, 6.0F, 0.75F, 10.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.25F));

		ModelPartData cube_r9 = skirt.addChild("cube_r9", ModelPartBuilder.create().uv(54, 69).cuboid(-1.0F, -2.0F, 1.0F, 3.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(8.75F, 8.0F, -0.25F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r10 = skirt.addChild("cube_r10", ModelPartBuilder.create().uv(54, 60).cuboid(-2.0F, -2.0F, 1.0F, 3.0F, 9.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.75F, 8.0F, -0.25F, 0.0F, -1.5708F, 0.0F));

		ModelPartData decoration = bottom.addChild("decoration", ModelPartBuilder.create().uv(60, 50).cuboid(-4.25F, -24.0F, -1.0F, 0.0F, 20.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(4.75F, 30.0F, -1.0F));

		ModelPartData cube_r11 = decoration.addChild("cube_r11", ModelPartBuilder.create().uv(26, 60).cuboid(1.0F, -1.0F, -1.0F, 0.0F, 20.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData cube_r12 = decoration.addChild("cube_r12", ModelPartBuilder.create().uv(16, 0).cuboid(-1.0F, -1.0F, -5.0F, 0.0F, 20.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -23.0F, 0.25F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r13 = decoration.addChild("cube_r13", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, -3.0F, 0.0F, 20.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -23.0F, 0.5F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r14 = decoration.addChild("cube_r14", ModelPartBuilder.create().uv(10, 46).cuboid(-1.0F, -1.0F, -3.0F, 0.0F, 20.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -23.0F, 1.5F, -0.0662F, -1.1332F, 0.0934F));

		ModelPartData cube_r15 = decoration.addChild("cube_r15", ModelPartBuilder.create().uv(0, 46).cuboid(-1.0F, -1.0F, -3.0F, 0.0F, 20.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -23.0F, 0.0F, 0.0436F, 0.8717F, 0.0668F));

		ModelPartData cube_r16 = decoration.addChild("cube_r16", ModelPartBuilder.create().uv(32, 60).cuboid(1.0F, -1.0F, -1.0F, 0.0F, 20.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		ModelPartData cube_r17 = decoration.addChild("cube_r17", ModelPartBuilder.create().uv(20, 60).cuboid(-1.0F, -1.0F, -2.0F, 0.0F, 20.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -23.0F, 1.0F, 0.0F, 0.0F, 0.1745F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(VoidBoundServantRenderState state) {
		super.setAngles(state);

		this.suicideAnimation.apply(state.suicideAnimationState, state.age);
	}

	public ModelPart getRightHand() {
		return this.right_elbow;
	}

	public ModelPart getLeftHand() {
		return this.left_elbow;
	}

	protected ModelPart getAttackingArm(Arm arm) {
		return arm == Arm.LEFT ? this.left_arm : this.right_arm;
	}

	@Override
	public void setArmAngle(EntityRenderState state, Arm arm, MatrixStack matrices) {}
}
