package net.balamah.voiddim.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.entity.client.base.AbstractWerewolfRenderState;
import net.balamah.voiddim.entity.client.base.AbstractWerewolfModel;
import net.balamah.voiddim.VoidDimension;
	
public class WerewolfModel extends AbstractWerewolfModel<AbstractWerewolfRenderState> {
    public static final EntityModelLayer WEREWOLF =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "werewolf"), "main");

	private final ModelPart ears;

	public WerewolfModel(ModelPart root) {
		super(root);

		this.ears = this.head.getChild("ears");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 17.0F, 3.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(24, 6).cuboid(-3.0F, -10.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.2119F, 4.1513F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r2 = body.addChild("cube_r2", ModelPartBuilder.create().uv(16, 12).cuboid(-3.0F, -10.0F, -1.0F, 6.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.5381F, 2.9013F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r3 = body.addChild("cube_r3", ModelPartBuilder.create().uv(38, 30).cuboid(-2.0F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.2881F, -3.3487F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r4 = body.addChild("cube_r4", ModelPartBuilder.create().uv(16, 26).cuboid(-2.0F, -2.0F, -1.0F, 4.0F, 9.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.2881F, -2.3487F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r5 = body.addChild("cube_r5", ModelPartBuilder.create().uv(0, 12).cuboid(-3.0F, -11.0F, -1.0F, 6.0F, 11.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.0381F, 1.4013F, 0.3054F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create(), ModelTransform.of(0.0F, -10.5F, -1.0F, -0.3491F, 0.0F, 0.0F));

		ModelPartData cube_r6 = head.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -6.0F, -2.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.75F, -2.5F, 0.3054F, 0.0F, 0.0F));

		ModelPartData ears = head.addChild("ears", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 18.0F, -2.0F));

		ModelPartData cube_r7 = ears.addChild("cube_r7", ModelPartBuilder.create().uv(20, 41).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(-0.001F))
		.uv(40, 6).cuboid(-5.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new Dilation(-0.001F)), ModelTransform.of(2.0F, -22.5F, -2.25F, 0.3054F, 0.0F, 0.0F));

		ModelPartData mouth = head.addChild("mouth", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -5.0F));

		ModelPartData jaw = mouth.addChild("jaw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, 1.0F));

		ModelPartData cube_r8 = jaw.addChild("cube_r8", ModelPartBuilder.create().uv(24, 0).cuboid(-2.0F, -1.0F, -4.0F, 4.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.25F, -0.5F, 0.3054F, 0.0F, 0.0F));

		ModelPartData jaw_teeth = jaw.addChild("jaw_teeth", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 17.0F, 2.0F));

		ModelPartData cube_r9 = jaw_teeth.addChild("cube_r9", ModelPartBuilder.create().uv(34, 24).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
		.uv(28, 10).cuboid(-1.5F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
		.uv(24, 10).cuboid(-3.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F)), ModelTransform.of(1.0F, -16.75F, -5.5F, 0.3054F, 0.0F, 0.0F));

		ModelPartData upper_mouth = mouth.addChild("upper_mouth", ModelPartBuilder.create(), ModelTransform.of(0.0F, -0.75F, 0.5F, 0.6109F, 0.0F, 0.0F));

		ModelPartData cube_r10 = upper_mouth.addChild("cube_r10", ModelPartBuilder.create().uv(16, 19).cuboid(-2.0F, -1.0F, -4.0F, 4.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.25F, -0.5F, -0.3054F, 0.0F, 0.0F));

		ModelPartData upper_teeth = upper_mouth.addChild("upper_teeth", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -16.0F, 1.75F));

		ModelPartData cube_r11 = upper_teeth.addChild("cube_r11", ModelPartBuilder.create().uv(42, 11).cuboid(0.0F, -1.1041F, -1.1761F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
		.uv(42, 9).cuboid(-1.5F, -1.1041F, -1.1761F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
		.uv(42, 3).cuboid(-3.0F, -1.1041F, -1.1761F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F)), ModelTransform.of(1.0F, 16.75F, -5.5F, -0.3054F, 0.0F, 0.0F));

		ModelPartData horns = body.addChild("horns", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.7119F, -3.5987F));

		ModelPartData cube_r12 = horns.addChild("cube_r12", ModelPartBuilder.create().uv(16, 36).cuboid(0.25F, -1.0F, -1.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, -10.25F, 4.5F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r13 = horns.addChild("cube_r13", ModelPartBuilder.create().uv(8, 39).cuboid(0.25F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, -13.25F, 6.5F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r14 = horns.addChild("cube_r14", ModelPartBuilder.create().uv(38, 34).cuboid(0.25F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, -15.0F, 6.75F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r15 = horns.addChild("cube_r15", ModelPartBuilder.create().uv(42, 0).cuboid(0.25F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, -16.75F, 6.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(16, 41).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 2.0F));

		ModelPartData front_paws = modelPartData.addChild("front_paws", ModelPartBuilder.create(), ModelTransform.of(0.0F, 8.0F, -1.25F, 0.0873F, 0.0F, 0.0F));

		ModelPartData right_front_paw = front_paws.addChild("right_front_paw", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -1.25F, 0.5F));

		ModelPartData right_shoulder_bicep = right_front_paw.addChild("right_shoulder_bicep", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 5.0F, 2.0F));

		ModelPartData cube_r16 = right_shoulder_bicep.addChild("cube_r16", ModelPartBuilder.create().uv(26, 26).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.25F, 0.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r17 = right_shoulder_bicep.addChild("cube_r17", ModelPartBuilder.create().uv(34, 38).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.5F, 0.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData right_elbow = right_front_paw.addChild("right_elbow", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, 6.0F, 2.0F));

		ModelPartData cube_r18 = right_elbow.addChild("cube_r18", ModelPartBuilder.create().uv(0, 25).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(-0.001F)), ModelTransform.of(0.0F, 1.75F, 0.0F, -0.2182F, 0.0F, 0.0F));

		ModelPartData left_front_paw = front_paws.addChild("left_front_paw", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -1.25F, 0.5F));

		ModelPartData left_shoulder_bicep = left_front_paw.addChild("left_shoulder_bicep", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 4.5F, 2.0F));

		ModelPartData cube_r19 = left_shoulder_bicep.addChild("cube_r19", ModelPartBuilder.create().uv(26, 32).cuboid(-1.0F, -2.0F, -2.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.75F, 0.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r20 = left_shoulder_bicep.addChild("cube_r20", ModelPartBuilder.create().uv(0, 39).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.3054F, 0.0F, 0.0F));

		ModelPartData left_elbow = left_front_paw.addChild("left_elbow", ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 6.0F, 2.0F));

		ModelPartData cube_r21 = left_elbow.addChild("cube_r21", ModelPartBuilder.create().uv(8, 25).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(-0.001F)), ModelTransform.of(-1.0F, 1.75F, 0.0F, -0.2182F, 0.0F, 0.0F));

		ModelPartData back_paws = modelPartData.addChild("back_paws", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 15.0F, 4.0F));

		ModelPartData right_back_paw = back_paws.addChild("right_back_paw", ModelPartBuilder.create().uv(38, 24).cuboid(-5.0F, -9.0F, 4.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 9.0F, -5.0F));

		ModelPartData right_calf = right_back_paw.addChild("right_calf", ModelPartBuilder.create().uv(34, 10).cuboid(-5.0F, -5.0F, 4.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_back_paw = back_paws.addChild("left_back_paw", ModelPartBuilder.create().uv(26, 38).cuboid(3.0F, -9.0F, 4.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 9.0F, -5.0F));

		ModelPartData left_calf = left_back_paw.addChild("left_calf", ModelPartBuilder.create().uv(34, 17).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -5.0F, 4.0F));
		return TexturedModelData.of(modelData, 48, 48);
	}
}
