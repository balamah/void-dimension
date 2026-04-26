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
import net.minecraft.util.Mth;
import net.balamah.voiddim.VoidDimension;

public class VoidHarbingerModel extends EntityModel<VoidHarbingerRenderState> {
    public static final ModelLayerLocation VOID_HARBINGER =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_harbinger"),
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

	private final KeyframeAnimation summonAnimation;
	private final KeyframeAnimation summonEndAnimation;

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

		this.summonAnimation = VoidHarbingerAnimations.SUMMON.bake(root);
		this.summonEndAnimation = VoidHarbingerAnimations.SUMMON_END.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 21).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -39.0F, 0.0F));

		PartDefinition arms = root.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(68, 28).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 7.5F));

		PartDefinition right_elbow = right_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(68, 62).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, 1.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(68, 45).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, -7.5F));

		PartDefinition left_elbow = left_arm.addOrReplaceChild("left_elbow", CubeListBuilder.create().texOffs(0, 69).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 14.0F, -1.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(40, 0).addBox(-2.0F, -34.0F, -4.0F, 4.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -39.0F, -8.0F, 4.0F, 5.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition lats = body.addOrReplaceChild("lats", CubeListBuilder.create().texOffs(8, 69).addBox(-1.0F, -10.0F, -4.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(72, 22).addBox(-1.0F, -10.0F, 5.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -1.0F));

		PartDefinition body_decoration = body.addOrReplaceChild("body_decoration", CubeListBuilder.create(), PartPose.offset(0.0F, -54.0F, 0.0F));

		PartDefinition right_body_horn = body_decoration.addOrReplaceChild("right_body_horn", CubeListBuilder.create().texOffs(56, 75).addBox(1.0F, 15.0F, 8.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 75).addBox(1.0F, 15.0F, 10.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = right_body_horn.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 68).addBox(0.0F, -4.0F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, 8.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = right_body_horn.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 76).addBox(0.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 12.0F, 9.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r3 = right_body_horn.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(40, 18).addBox(0.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, 10.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_body_horn.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(72, 0).addBox(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 10.0F, 13.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r5 = right_body_horn.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(72, 12).addBox(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, 12.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_body_horn = body_decoration.addOrReplaceChild("left_body_horn", CubeListBuilder.create().texOffs(76, 28).addBox(1.0F, 15.0F, -10.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 75).addBox(1.0F, 15.0F, -13.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = left_body_horn.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(28, 72).addBox(0.0F, -4.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, -8.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r7 = left_body_horn.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(76, 35).addBox(0.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 12.0F, -9.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r8 = left_body_horn.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(76, 32).addBox(0.0F, -3.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, -10.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r9 = left_body_horn.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(72, 6).addBox(0.0F, -2.0F, -3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 10.0F, -13.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r10 = left_body_horn.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(72, 17).addBox(0.0F, -2.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 13.0F, -12.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition miasma = root.addOrReplaceChild("miasma", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition side = miasma.addOrReplaceChild("side", CubeListBuilder.create(), PartPose.offset(0.5F, -24.25F, 3.5F));

		PartDefinition cube_r11 = side.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(64, 28).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r12 = side.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(64, 0).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition front = miasma.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offset(-1.25F, -24.25F, -3.5F));

		PartDefinition cube_r13 = front.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(60, 46).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.25F, 1.75F, -0.1787F, -0.2148F, 0.3439F));

		PartDefinition cube_r14 = front.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(60, 18).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.25F, 5.25F, 0.1787F, 0.2148F, 0.3439F));

		PartDefinition cube_r15 = front.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(56, 47).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.25F, 3.25F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r16 = front.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(44, 53).addBox(-0.6482F, 0.0003F, -0.6465F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, 0.2533F, 0.7519F, 0.3622F));

		PartDefinition cube_r17 = front.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(40, 53).addBox(-0.6482F, 0.0003F, -0.3535F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2533F, -0.7519F, 0.3622F));

		PartDefinition back = miasma.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(1.25F, -24.25F, -3.5F));

		PartDefinition cube_r18 = back.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(24, 68).addBox(0.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.25F, 1.75F, -0.1787F, 0.2148F, -0.3439F));

		PartDefinition cube_r19 = back.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(20, 68).addBox(0.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.25F, 5.25F, 0.1787F, -0.2148F, -0.3439F));

		PartDefinition cube_r20 = back.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(16, 68).addBox(0.0F, 0.0F, -0.5F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.25F, 3.25F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r21 = back.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(68, 0).addBox(-0.3518F, 0.0003F, -0.6465F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 7.0F, 0.2533F, -0.7519F, -0.3622F));

		PartDefinition cube_r22 = back.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(64, 56).addBox(-0.3518F, 0.0003F, -0.3535F, 1.0F, 27.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2533F, 0.7519F, -0.3622F));

		PartDefinition decoration = miasma.addOrReplaceChild("decoration", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r23 = decoration.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 37).addBox(1.0F, -1.0F, -4.0F, 0.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -23.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r24 = decoration.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(32, 53).addBox(-1.0F, -1.0F, -3.0F, 0.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -23.0F, -3.0F, -1.5708F, -1.4399F, 1.5708F));

		PartDefinition cube_r25 = decoration.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(48, 47).addBox(-1.0F, -1.0F, -1.0F, 0.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -23.0F, 3.0F, 1.5708F, 1.4399F, 1.5708F));

		PartDefinition cube_r26 = decoration.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(48, 18).addBox(-1.0F, 0.0F, -3.0F, 0.0F, 23.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -23.0F, -1.0F, -0.0366F, -0.6973F, 0.0569F));

		PartDefinition cube_r27 = decoration.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(16, 37).addBox(-1.0F, 0.0F, -4.0F, 0.0F, 23.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -23.0F, 0.0F, 0.0366F, 0.6973F, 0.0569F));

		PartDefinition cube_r28 = decoration.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(32, 21).addBox(-1.0F, -1.0F, -4.0F, 0.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -23.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
		return LayerDefinition.create(modelData, 96, 96);
	}

	    @Override
	    public void setupAnim(VoidHarbingerRenderState state) {
	        super.setupAnim(state);

		this.setHeadAngles(state.yRot, state.xRot);

		this.summonAnimation.apply(
			state.summonAnimationState, state.ageInTicks
		);

		this.summonEndAnimation.apply(
			state.summonEndAnimationState, state.ageInTicks
		);
    }

	protected void setHeadAngles(float headYaw, float headPitch) {
		float yawRad   = headYaw   * ((float)Math.PI / 180F);
		float pitchRad = headPitch * ((float)Math.PI / 180F);

		pitchRad = Mth.clamp(
			pitchRad,
			-25F * ((float)Math.PI / 180F),
			45F  * ((float)Math.PI / 180F)
		);

		// if your head is rotated sideways in Blockbench, apply pitch to roll instead:
		this.head.yRot  = yawRad;    // left/right
		this.head.zRot = pitchRad * -1;  // up/down (compensating for sideways model)
		this.head.xRot = 0.0F;     // reset old pitch
	}
}
