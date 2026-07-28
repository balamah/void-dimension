// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class ConsumedSoulModel extends EntityModel<ConsumedSoulEntity> {
	private final ModelPart root;
	private final ModelPart face;
	private final ModelPart jaw;
	private final ModelPart upper_jaw;
	private final ModelPart upper_jaw_teeth;
	private final ModelPart lower_jaw;
	private final ModelPart lower_jaw_teeth;
	public ConsumedSoulModel(ModelPart root) {
		this.root = root.getChild("root");
		this.face = this.root.getChild("face");
		this.jaw = this.root.getChild("jaw");
		this.upper_jaw = this.jaw.getChild("upper_jaw");
		this.upper_jaw_teeth = this.upper_jaw.getChild("upper_jaw_teeth");
		this.lower_jaw = this.jaw.getChild("lower_jaw");
		this.lower_jaw_teeth = this.lower_jaw.getChild("lower_jaw_teeth");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData face = root.addChild("face", ModelPartBuilder.create().uv(0, 24).cuboid(-4.0F, -3.0F, -3.0F, 8.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 17).cuboid(-4.0F, -5.0F, -2.0F, 8.0F, 2.0F, 5.0F, new Dilation(0.0F))
		.uv(26, 15).cuboid(-4.0F, -5.0F, 3.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 30).cuboid(-3.0F, -4.0F, 3.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 4).cuboid(-2.0F, -3.0F, 2.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-4.0F, -7.0F, -3.0F, 8.0F, 2.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 9).cuboid(-3.0F, -8.0F, -3.0F, 6.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(26, 17).cuboid(-4.0F, -8.0F, -2.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(26, 23).cuboid(3.0F, -8.0F, -2.0F, 1.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(18, 33).cuboid(-4.0F, -5.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(22, 33).cuboid(3.0F, -5.0F, -3.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 33).cuboid(-1.0F, -5.0F, -3.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData jaw = root.addChild("jaw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData upper_jaw = jaw.addChild("upper_jaw", ModelPartBuilder.create().uv(26, 9).cuboid(-2.0F, 1.0F, -5.0F, 4.0F, 1.0F, 5.0F, new Dilation(0.0F))
		.uv(0, 32).cuboid(-3.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(6, 32).cuboid(2.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, 2.0F));

		ModelPartData upper_jaw_teeth = upper_jaw.addChild("upper_jaw_teeth", ModelPartBuilder.create().uv(8, 35).cuboid(-1.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
		.uv(36, 6).cuboid(1.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData lower_jaw = jaw.addChild("lower_jaw", ModelPartBuilder.create().uv(26, 29).cuboid(0.0F, -1.0F, -5.0F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(30, 0).cuboid(-2.0F, -1.0F, -4.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(14, 30).cuboid(-3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(26, 34).cuboid(-2.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(30, 6).cuboid(2.0F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

		ModelPartData cube_r1 = lower_jaw.addChild("cube_r1", ModelPartBuilder.create().uv(20, 30).cuboid(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData lower_jaw_teeth = lower_jaw.addChild("lower_jaw_teeth", ModelPartBuilder.create().uv(30, 34).cuboid(-2.0F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
		.uv(34, 34).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
		.uv(0, 35).cuboid(-3.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F))
		.uv(4, 35).cuboid(2.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.001F)), ModelTransform.pivot(0.0F, -2.0F, -2.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(ConsumedSoulEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}