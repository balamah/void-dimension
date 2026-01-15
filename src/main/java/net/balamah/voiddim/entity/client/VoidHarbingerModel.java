package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.Identifier;
import net.minecraft.client.model.*;

import net.balamah.voiddim.VoidDimension;

public class VoidHarbingerModel extends EntityModel<VoidHarbingerRenderState> {
    public static final EntityModelLayer VOID_HARBINGER =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "void_harbinger"),
							 "main");

	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart body;
	private final ModelPart lats;
	private final ModelPart body_decoration;
	private final ModelPart right_body_horn;
	private final ModelPart left_body_horn;
	private final ModelPart miasma;
	private final ModelPart side;
	private final ModelPart front;
	private final ModelPart back;
	private final ModelPart decoration;

	private final Animation summonAnimation;
	private final Animation summonEndAnimation;

	public VoidHarbingerModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.arms = this.root.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");
		this.body = this.root.getChild("body");
		this.lats = this.body.getChild("lats");
		this.body_decoration = this.body.getChild("body_decoration");
		this.right_body_horn = this.body_decoration.getChild("right_body_horn");
		this.left_body_horn = this.body_decoration.getChild("left_body_horn");
		this.miasma = this.root.getChild("miasma");
		this.side = this.miasma.getChild("side");
		this.front = this.miasma.getChild("front");
		this.back = this.miasma.getChild("back");
		this.decoration = this.miasma.getChild("decoration");

		this.summonAnimation = VoidHarbingerAnimations.SUMMON.createAnimation(root);
		this.summonEndAnimation = VoidHarbingerAnimations.SUMMON_END.createAnimation(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 21).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -39.0F, 0.0F));

		ModelPartData arms = root.addChild("arms", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -24.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(68, 28).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 15.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -14.0F, 7.5F));

		ModelPartData right_elbow = right_arm.addChild("right_elbow", ModelPartBuilder.create().uv(68, 62).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 14.0F, 1.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(68, 45).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 15.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -14.0F, -7.5F));

		ModelPartData left_elbow = left_arm.addChild("left_elbow", ModelPartBuilder.create().uv(0, 69).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 14.0F, -1.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(40, 0).cuboid(-2.0F, -34.0F, -4.0F, 4.0F, 10.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -39.0F, -8.0F, 4.0F, 5.0F, 16.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData lats = body.addChild("lats", ModelPartBuilder.create().uv(8, 69).cuboid(-1.0F, -10.0F, -4.0F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F))
		.uv(72, 22).cuboid(-1.0F, -10.0F, 5.0F, 3.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, -24.0F, -1.0F));

		ModelPartData body_decoration = body.addChild("body_decoration", ModelPartBuilder.create(), ModelTransform.origin(0.0F, -54.0F, 0.0F));

		ModelPartData right_body_horn = body_decoration.addChild("right_body_horn", ModelPartBuilder.create().uv(56, 75).cuboid(1.0F, 15.0F, 8.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(8, 75).cuboid(1.0F, 15.0F, 10.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = right_body_horn.addChild("cube_r1", ModelPartBuilder.create().uv(28, 68).cuboid(0.0F, -4.0F, 1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 13.0F, 8.0F, -1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r2 = right_body_horn.addChild("cube_r2", ModelPartBuilder.create().uv(28, 76).cuboid(0.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 12.0F, 9.0F, -1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r3 = right_body_horn.addChild("cube_r3", ModelPartBuilder.create().uv(40, 18).cuboid(0.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 13.0F, 10.0F, -1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r4 = right_body_horn.addChild("cube_r4", ModelPartBuilder.create().uv(72, 0).cuboid(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 10.0F, 13.0F, -1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r5 = right_body_horn.addChild("cube_r5", ModelPartBuilder.create().uv(72, 12).cuboid(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 13.0F, 12.0F, -1.5708F, 0.0F, 0.0F));

		ModelPartData left_body_horn = body_decoration.addChild("left_body_horn", ModelPartBuilder.create().uv(76, 28).cuboid(1.0F, 15.0F, -10.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(48, 75).cuboid(1.0F, 15.0F, -13.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r6 = left_body_horn.addChild("cube_r6", ModelPartBuilder.create().uv(28, 72).cuboid(0.0F, -4.0F, -2.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 13.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r7 = left_body_horn.addChild("cube_r7", ModelPartBuilder.create().uv(76, 35).cuboid(0.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 12.0F, -9.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r8 = left_body_horn.addChild("cube_r8", ModelPartBuilder.create().uv(76, 32).cuboid(0.0F, -3.0F, -2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 13.0F, -10.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r9 = left_body_horn.addChild("cube_r9", ModelPartBuilder.create().uv(72, 6).cuboid(0.0F, -2.0F, -3.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 10.0F, -13.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData cube_r10 = left_body_horn.addChild("cube_r10", ModelPartBuilder.create().uv(72, 17).cuboid(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 13.0F, -12.0F, 1.5708F, 0.0F, 0.0F));

		ModelPartData miasma = root.addChild("miasma", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData side = miasma.addChild("side", ModelPartBuilder.create(), ModelTransform.origin(0.5F, -24.25F, 3.5F));

		ModelPartData cube_r11 = side.addChild("cube_r11", ModelPartBuilder.create().uv(64, 28).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -7.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r12 = side.addChild("cube_r12", ModelPartBuilder.create().uv(64, 0).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData front = miasma.addChild("front", ModelPartBuilder.create(), ModelTransform.origin(-1.25F, -24.25F, -3.5F));

		ModelPartData cube_r13 = front.addChild("cube_r13", ModelPartBuilder.create().uv(60, 46).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, 0.25F, 1.75F, -0.1787F, -0.2148F, 0.3439F));

		ModelPartData cube_r14 = front.addChild("cube_r14", ModelPartBuilder.create().uv(60, 18).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, 0.25F, 5.25F, 0.1787F, 0.2148F, 0.3439F));

		ModelPartData cube_r15 = front.addChild("cube_r15", ModelPartBuilder.create().uv(56, 47).cuboid(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-0.25F, 0.25F, 3.25F, 0.0F, 0.0F, 0.3054F));

		ModelPartData cube_r16 = front.addChild("cube_r16", ModelPartBuilder.create().uv(44, 53).cuboid(-0.6482F, 0.0003F, -0.6465F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 7.0F, 0.2533F, 0.7519F, 0.3622F));

		ModelPartData cube_r17 = front.addChild("cube_r17", ModelPartBuilder.create().uv(40, 53).cuboid(-0.6482F, 0.0003F, -0.3535F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2533F, -0.7519F, 0.3622F));

		ModelPartData back = miasma.addChild("back", ModelPartBuilder.create(), ModelTransform.origin(1.25F, -24.25F, -3.5F));

		ModelPartData cube_r18 = back.addChild("cube_r18", ModelPartBuilder.create().uv(24, 68).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, 0.25F, 1.75F, -0.1787F, 0.2148F, -0.3439F));

		ModelPartData cube_r19 = back.addChild("cube_r19", ModelPartBuilder.create().uv(20, 68).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, 0.25F, 5.25F, 0.1787F, -0.2148F, -0.3439F));

		ModelPartData cube_r20 = back.addChild("cube_r20", ModelPartBuilder.create().uv(16, 68).cuboid(0.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.25F, 0.25F, 3.25F, 0.0F, 0.0F, -0.3054F));

		ModelPartData cube_r21 = back.addChild("cube_r21", ModelPartBuilder.create().uv(68, 0).cuboid(-0.3518F, 0.0003F, -0.6465F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 7.0F, 0.2533F, -0.7519F, -0.3622F));

		ModelPartData cube_r22 = back.addChild("cube_r22", ModelPartBuilder.create().uv(64, 56).cuboid(-0.3518F, 0.0003F, -0.3535F, 1.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2533F, 0.7519F, -0.3622F));

		ModelPartData decoration = miasma.addChild("decoration", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r23 = decoration.addChild("cube_r23", ModelPartBuilder.create().uv(0, 37).cuboid(1.0F, -1.0F, -4.0F, 0.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		ModelPartData cube_r24 = decoration.addChild("cube_r24", ModelPartBuilder.create().uv(32, 53).cuboid(-1.0F, -1.0F, -3.0F, 0.0F, 24.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -23.0F, -3.0F, -1.5708F, -1.4399F, 1.5708F));

		ModelPartData cube_r25 = decoration.addChild("cube_r25", ModelPartBuilder.create().uv(48, 47).cuboid(-1.0F, -1.0F, -1.0F, 0.0F, 24.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -23.0F, 3.0F, 1.5708F, 1.4399F, 1.5708F));

		ModelPartData cube_r26 = decoration.addChild("cube_r26", ModelPartBuilder.create().uv(48, 18).cuboid(-1.0F, 0.0F, -3.0F, 0.0F, 23.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -23.0F, -1.0F, -0.0366F, -0.6973F, 0.0569F));

		ModelPartData cube_r27 = decoration.addChild("cube_r27", ModelPartBuilder.create().uv(16, 37).cuboid(-1.0F, 0.0F, -4.0F, 0.0F, 23.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -23.0F, 0.0F, 0.0366F, 0.6973F, 0.0569F));

		ModelPartData cube_r28 = decoration.addChild("cube_r28", ModelPartBuilder.create().uv(32, 21).cuboid(-1.0F, -1.0F, -4.0F, 0.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -23.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
		return TexturedModelData.of(modelData, 96, 96);
	}

    @Override
    public void setAngles(VoidHarbingerRenderState state) {
        super.setAngles(state);

		this.setHeadAngles(state.relativeHeadYaw, state.pitch);

		this.summonAnimation.apply(
			state.summonAnimationState, state.age
		);

		this.summonEndAnimation.apply(
			state.summonEndAnimationState, state.age
		);
    }

	protected void setHeadAngles(float headYaw, float headPitch) {
		float yawRad   = headYaw   * ((float)Math.PI / 180F);
		float pitchRad = headPitch * ((float)Math.PI / 180F);

		pitchRad = MathHelper.clamp(
			pitchRad,
			-25F * ((float)Math.PI / 180F),
			45F  * ((float)Math.PI / 180F)
		);

		// if your head is rotated sideways in Blockbench, apply pitch to roll instead:
		this.head.yaw  = yawRad;    // left/right
		this.head.roll = pitchRad * -1;  // up/down (compensating for sideways model)
		this.head.pitch = 0.0F;     // reset old pitch
	}
}
