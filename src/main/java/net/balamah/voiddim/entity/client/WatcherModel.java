// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package com.example.mod;
   
public class watcher extends EntityModel<Entity> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart right_hand;
	private final ModelPart left_arm;
	private final ModelPart left_idk;
	private final ModelPart legs;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	public watcher(ModelPart root) {
		this.root = root.getChild("root");
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.arms = root.getChild("arms");
		this.right_arm = root.getChild("right_arm");
		this.right_elbow = root.getChild("right_elbow");
		this.right_hand = root.getChild("right_hand");
		this.left_arm = root.getChild("left_arm");
		this.left_idk = root.getChild("left_idk");
		this.legs = root.getChild("legs");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -14.0F, -4.0F, 4.0F, 14.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(8, 36).cuboid(-3.5F, -6.75F, -5.0F, 7.0F, 4.0F, 2.0F, new Dilation(0.001F))
		.uv(36, 14).cuboid(-3.5F, -6.75F, 3.0F, 7.0F, 4.0F, 2.0F, new Dilation(0.001F)), ModelTransform.pivot(0.0F, -13.75F, 0.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(24, 0).cuboid(-4.0F, -3.0F, -5.0F, 7.0F, 7.0F, 7.0F, new Dilation(-0.001F)), ModelTransform.of(-1.5F, -6.75F, 0.75F, -1.5708F, -0.7854F, 1.5708F));

		ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(0, 22).cuboid(-4.0F, -3.0F, -5.0F, 7.0F, 7.0F, 7.0F, new Dilation(-0.0001F)), ModelTransform.of(-1.5F, -3.25F, 0.75F, -1.5708F, -0.7854F, 1.5708F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(36, 20).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -38.0F, -3.75F, -0.3897F, 0.05F, 0.121F));

		ModelPartData right_elbow = right_arm.addChild("right_elbow", ModelPartBuilder.create().uv(26, 40).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.25F, -1.0F, 0.3778F, 0.1298F, -0.228F));

		ModelPartData right_hand = right_elbow.addChild("right_hand", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 7.25F, 0.0F));

		ModelPartData cube_r3 = right_hand.addChild("cube_r3", ModelPartBuilder.create().uv(40, 43).cuboid(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(-0.001F)), ModelTransform.of(0.0F, 3.75F, -3.25F, -0.6981F, 0.0F, 0.0F));

		ModelPartData cube_r4 = right_hand.addChild("cube_r4", ModelPartBuilder.create().uv(36, 32).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new Dilation(-0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(16, 42).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F, new Dilation(-0.001F)), ModelTransform.of(0.0F, -38.0F, 4.0F, 0.7418F, 0.0F, 0.0F));

		ModelPartData cube_r5 = left_arm.addChild("cube_r5", ModelPartBuilder.create().uv(8, 42).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 7.0F, 1.0F, -0.829F, 0.0F, 0.0F));

		ModelPartData left_idk = left_arm.addChild("left_idk", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.25F, 1.0F));

		ModelPartData cube_r6 = left_idk.addChild("cube_r6", ModelPartBuilder.create().uv(34, 43).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.346F, 0.151F, 0.0879F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -24.0F, 1.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(28, 14).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(0, 36).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -3.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}