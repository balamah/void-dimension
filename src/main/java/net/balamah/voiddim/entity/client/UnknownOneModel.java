// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class UnknownOneModel extends EntityModel<UnknownOneEntity> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	public UnknownOneModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.left_arm = this.arms.getChild("left_arm");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.left_leg = this.legs.getChild("left_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 20).cuboid(-3.5F, -29.0F, -1.5F, 7.0F, 16.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.25F, -14.0F, -3.0F, 8.0F, 14.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -29.0F, 0.0F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -27.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(20, 20).cuboid(0.0F, 2.0F, -1.0F, 2.0F, 16.0F, 2.0F, new Dilation(0.0F))
		.uv(36, 0).cuboid(-0.5F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -1.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(28, 0).cuboid(-3.0F, 1.0F, -1.0F, 2.0F, 16.0F, 2.0F, new Dilation(0.0F))
		.uv(36, 6).cuboid(-3.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, 0.0F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.25F, 0.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(28, 18).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 0.25F, 0.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(28, 33).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 0.25F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(UnknownOneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
