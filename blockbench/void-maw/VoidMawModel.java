// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class VoidMawModel extends EntityModel<Entity> {
	private final ModelPart root;
	private final ModelPart mouth;
	private final ModelPart upper;
	private final ModelPart horns;
	private final ModelPart left;
	private final ModelPart right;
	private final ModelPart upper_teeth;
	private final ModelPart lower;
	private final ModelPart lower_teeth;
	private final ModelPart main_decoration;
	private final ModelPart overlay;
	public VoidMawModel(ModelPart root) {
		this.root = root.getChild("root");
		this.mouth = this.root.getChild("mouth");
		this.upper = this.mouth.getChild("upper");
		this.horns = this.upper.getChild("horns");
		this.left = this.horns.getChild("left");
		this.right = this.horns.getChild("right");
		this.upper_teeth = this.upper.getChild("upper_teeth");
		this.lower = this.mouth.getChild("lower");
		this.lower_teeth = this.lower.getChild("lower_teeth");
		this.main_decoration = this.root.getChild("main_decoration");
		this.overlay = this.main_decoration.getChild("overlay");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 19.0F, 0.0F));

		ModelPartData mouth = root.addChild("mouth", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 17.0F, 0.0F));

		ModelPartData upper = mouth.addChild("upper", ModelPartBuilder.create().uv(56, 18).cuboid(-6.0F, -6.0F, -12.0F, 12.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -18.0F, 6.0F));

		ModelPartData horns = upper.addChild("horns", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, -6.0F));

		ModelPartData left = horns.addChild("left", ModelPartBuilder.create().uv(56, 36).cuboid(-0.5F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new Dilation(-0.0001F))
		.uv(56, 54).cuboid(-0.5F, -6.0F, -2.0F, 1.0F, 3.0F, 1.0F, new Dilation(-0.0001F))
		.uv(56, 48).cuboid(-0.5F, -7.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(-0.0001F))
		.uv(52, 58).cuboid(-0.5F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.0001F))
		.uv(56, 58).cuboid(-0.5F, -5.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.0001F)), ModelTransform.of(-6.0F, -6.75F, -5.5F, 0.0F, -1.5708F, -0.2618F));

		ModelPartData right = horns.addChild("right", ModelPartBuilder.create().uv(56, 42).cuboid(-0.5F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new Dilation(-0.0001F))
		.uv(0, 58).cuboid(-0.5F, -6.0F, -2.0F, 1.0F, 3.0F, 1.0F, new Dilation(-0.0001F))
		.uv(56, 51).cuboid(-0.5F, -7.0F, -4.0F, 1.0F, 1.0F, 2.0F, new Dilation(-0.0001F))
		.uv(60, 36).cuboid(-0.5F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.0001F))
		.uv(60, 38).cuboid(-0.5F, -5.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(-0.0001F)), ModelTransform.of(6.0F, -6.75F, -5.5F, 0.0F, 1.5708F, 0.2618F));

		ModelPartData upper_teeth = upper.addChild("upper_teeth", ModelPartBuilder.create().uv(28, 58).cuboid(-1.25F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(32, 58).cuboid(8.0F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(36, 58).cuboid(6.25F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(40, 58).cuboid(0.75F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(44, 58).cuboid(2.75F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(48, 58).cuboid(4.5F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -6.0F, -10.0F));

		ModelPartData lower = mouth.addChild("lower", ModelPartBuilder.create().uv(56, 0).cuboid(-6.0F, 0.0F, -12.0F, 12.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -18.0F, 6.0F));

		ModelPartData lower_teeth = lower.addChild("lower_teeth", ModelPartBuilder.create().uv(4, 58).cuboid(-1.25F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(8, 58).cuboid(8.0F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 58).cuboid(6.25F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(16, 58).cuboid(0.75F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(20, 58).cuboid(2.75F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(24, 58).cuboid(4.5F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 6.0F, -10.0F));

		ModelPartData main_decoration = root.addChild("main_decoration", ModelPartBuilder.create(), ModelTransform.pivot(-0.75F, 0.0F, 0.0F));

		ModelPartData overlay = main_decoration.addChild("overlay", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -14.0F, 0.0F));

		ModelPartData cube_r1 = overlay.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(1.0F, -3.0F, -14.0F, 0.0F, 30.0F, 28.0F, new Dilation(0.0F)), ModelTransform.of(1.25F, 0.0F, 1.0F, 0.0F, -1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 112, 112);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}