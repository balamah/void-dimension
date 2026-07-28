// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class CorruptedStalkerModel extends EntityModel<Entity> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart body;
	private final ModelPart legs;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	public CorruptedStalkerModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.arms = this.root.getChild("arms");
		this.left_arm = this.arms.getChild("left_arm");
		this.right_arm = this.arms.getChild("right_arm");
		this.body = this.root.getChild("body");
		this.legs = this.root.getChild("legs");
		this.left_leg = this.legs.getChild("left_leg");
		this.right_leg = this.legs.getChild("right_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, -7.0F, -5.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -38.0F, -1.5F, 0.0F, -1.5708F, 0.0F));

		ModelPartData arms = root.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(28, 0).cuboid(-1.0F, 0.0F, -2.0F, 2.0F, 28.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -38.0F, -4.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(28, 30).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 28.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -38.0F, 4.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -38.0F, -4.0F, 4.0F, 14.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -24.0F, 1.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(0, 36).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(36, 0).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -3.0F));
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