// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class ObsidianGluttonModel extends EntityModel<ObsidianGluttonEntity> {
	private final ModelPart root;
	private final ModelPart block;
	private final ModelPart upper;
	private final ModelPart upper_teeth;
	private final ModelPart lower;
	private final ModelPart tongue;
	private final ModelPart tongue_1;
	private final ModelPart tongue_2;
	private final ModelPart tongue_3;
	private final ModelPart tongue_4;
	private final ModelPart lower_teeth;
	private final ModelPart lower_body;
	private final ModelPart tentacles;
	private final ModelPart tentacles_group_1;
	private final ModelPart 1_1;
	private final ModelPart 1_2;
	private final ModelPart 1_3;
	private final ModelPart tentacles_group_2;
	private final ModelPart 2_1;
	private final ModelPart 2_2;
	private final ModelPart 2_3;
	private final ModelPart miasma;
	public ObsidianGluttonModel(ModelPart root) {
		this.root = root.getChild("root");
		this.block = this.root.getChild("block");
		this.upper = this.block.getChild("upper");
		this.upper_teeth = this.upper.getChild("upper_teeth");
		this.lower = this.block.getChild("lower");
		this.tongue = this.lower.getChild("tongue");
		this.tongue_1 = this.tongue.getChild("tongue_1");
		this.tongue_2 = this.tongue_1.getChild("tongue_2");
		this.tongue_3 = this.tongue_2.getChild("tongue_3");
		this.tongue_4 = this.tongue_3.getChild("tongue_4");
		this.lower_teeth = this.lower.getChild("lower_teeth");
		this.lower_body = this.root.getChild("lower_body");
		this.tentacles = this.lower_body.getChild("tentacles");
		this.tentacles_group_1 = this.tentacles.getChild("tentacles_group_1");
		this.1_1 = this.tentacles_group_1.getChild("1_1");
		this.1_2 = this.tentacles_group_1.getChild("1_2");
		this.1_3 = this.tentacles_group_1.getChild("1_3");
		this.tentacles_group_2 = this.tentacles.getChild("tentacles_group_2");
		this.2_1 = this.tentacles_group_2.getChild("2_1");
		this.2_2 = this.tentacles_group_2.getChild("2_2");
		this.2_3 = this.tentacles_group_2.getChild("2_3");
		this.miasma = this.lower_body.getChild("miasma");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData block = root.addChild("block", ModelPartBuilder.create().uv(0, 30).cuboid(-8.0F, -24.0F, 4.0F, 16.0F, 16.0F, 4.0F, new Dilation(0.0F))
		.uv(48, 0).cuboid(-8.0F, -24.0F, -4.0F, 2.0F, 16.0F, 8.0F, new Dilation(0.0F))
		.uv(40, 30).cuboid(6.0F, -24.0F, -8.0F, 2.0F, 16.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData upper = block.addChild("upper", ModelPartBuilder.create().uv(0, 15).cuboid(-6.0F, 0.0F, -4.0F, 12.0F, 3.0F, 12.0F, new Dilation(0.001F))
		.uv(68, 7).cuboid(-8.0F, 0.0F, -4.0F, 2.0F, 3.0F, 4.0F, new Dilation(0.001F)), ModelTransform.pivot(0.0F, -24.0F, -4.0F));

		ModelPartData upper_teeth = upper.addChild("upper_teeth", ModelPartBuilder.create().uv(58, 24).cuboid(-5.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.001F))
		.uv(70, 22).cuboid(-7.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.001F))
		.uv(62, 24).cuboid(-3.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.001F))
		.uv(66, 24).cuboid(-1.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.001F))
		.uv(48, 66).cuboid(1.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.001F))
		.uv(68, 14).cuboid(3.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.001F))
		.uv(68, 18).cuboid(5.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.001F)), ModelTransform.pivot(0.0F, 24.0F, 4.0F));

		ModelPartData lower = block.addChild("lower", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -2.0F, -8.0F, 12.0F, 3.0F, 12.0F, new Dilation(0.001F))
		.uv(68, 0).cuboid(-8.0F, -2.0F, -8.0F, 2.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -9.0F, 0.0F));

		ModelPartData tongue = lower.addChild("tongue", ModelPartBuilder.create().uv(54, 58).cuboid(-2.0F, -2.0F, -2.0F, 3.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, -1.0F));

		ModelPartData tongue_1 = tongue.addChild("tongue_1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = tongue_1.addChild("cube_r1", ModelPartBuilder.create().uv(54, 65).cuboid(-1.0F, -2.0F, -5.0F, 3.0F, 2.0F, 5.0F, new Dilation(0.001F)), ModelTransform.of(-1.0F, 0.0F, -2.0F, -0.6545F, 0.0F, 0.0F));

		ModelPartData tongue_2 = tongue_1.addChild("tongue_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -3.0F, -4.0F));

		ModelPartData cube_r2 = tongue_2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 58).cuboid(-1.0F, -0.5979F, -6.1846F, 4.0F, 2.0F, 6.0F, new Dilation(0.0001F)), ModelTransform.of(-1.5F, -1.25F, -0.5F, 0.0873F, 0.0F, 0.0F));

		ModelPartData tongue_3 = tongue_2.addChild("tongue_3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -6.0F));

		ModelPartData cube_r3 = tongue_3.addChild("cube_r3", ModelPartBuilder.create().uv(20, 58).cuboid(-1.0F, -0.5979F, -6.1846F, 3.0F, 2.0F, 6.0F, new Dilation(0.0001F)), ModelTransform.of(-1.0F, -1.0F, -0.25F, 0.6981F, 0.0F, 0.0F));

		ModelPartData tongue_4 = tongue_3.addChild("tongue_4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 3.0F, -5.0F));

		ModelPartData cube_r4 = tongue_4.addChild("cube_r4", ModelPartBuilder.create().uv(48, 24).cuboid(0.0F, -0.5979F, -4.1846F, 1.0F, 1.0F, 4.0F, new Dilation(0.0001F)), ModelTransform.of(-1.0F, 0.0F, 0.25F, 1.2217F, 0.0F, 0.0F));

		ModelPartData lower_teeth = lower.addChild("lower_teeth", ModelPartBuilder.create().uv(68, 28).cuboid(-6.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(68, 52).cuboid(-8.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(68, 32).cuboid(-4.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(68, 36).cuboid(-2.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(68, 40).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(68, 44).cuboid(2.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(68, 48).cuboid(4.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -3.0F, -8.0F));

		ModelPartData lower_body = root.addChild("lower_body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tentacles = lower_body.addChild("tentacles", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tentacles_group_1 = tentacles.addChild("tentacles_group_1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, -5.0F));

		ModelPartData 1_1 = tentacles_group_1.addChild("1_1", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, 0.0F, 1.0F));

		ModelPartData cube_r5 = 1_1.addChild("cube_r5", ModelPartBuilder.create().uv(16, 66).cuboid(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 1.5F, 0.2182F, 0.0F, 0.0F));

		ModelPartData 1_2 = tentacles_group_1.addChild("1_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r6 = 1_2.addChild("cube_r6", ModelPartBuilder.create().uv(0, 66).cuboid(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 1.0F, 0.2182F, 0.0F, 0.0F));

		ModelPartData 1_3 = tentacles_group_1.addChild("1_3", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 0.0F, 1.0F));

		ModelPartData cube_r7 = 1_3.addChild("cube_r7", ModelPartBuilder.create().uv(8, 66).cuboid(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.5F, 0.2182F, 0.0F, 0.0F));

		ModelPartData tentacles_group_2 = tentacles.addChild("tentacles_group_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.0F, 4.0F));

		ModelPartData 2_1 = tentacles_group_2.addChild("2_1", ModelPartBuilder.create(), ModelTransform.pivot(4.0F, 0.0F, -1.0F));

		ModelPartData cube_r8 = 2_1.addChild("cube_r8", ModelPartBuilder.create().uv(32, 66).cuboid(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.25F, 0.0F, 1.0F, 0.2182F, 0.0F, 0.0F));

		ModelPartData 2_2 = tentacles_group_2.addChild("2_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

		ModelPartData cube_r9 = 2_2.addChild("cube_r9", ModelPartBuilder.create().uv(40, 66).cuboid(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.75F, 0.0F, -0.5F, 0.2182F, 0.0F, 0.0F));

		ModelPartData 2_3 = tentacles_group_2.addChild("2_3", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, 0.0F, -1.0F));

		ModelPartData cube_r10 = 2_3.addChild("cube_r10", ModelPartBuilder.create().uv(24, 66).cuboid(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.75F, 0.0F, 1.0F, 0.2182F, 0.0F, 0.0F));

		ModelPartData miasma = lower_body.addChild("miasma", ModelPartBuilder.create().uv(38, 58).cuboid(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r11 = miasma.addChild("cube_r11", ModelPartBuilder.create().uv(20, 50).cuboid(-5.0F, -8.0F, 0.0F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.829F, 0.0F));

		ModelPartData cube_r12 = miasma.addChild("cube_r12", ModelPartBuilder.create().uv(0, 50).cuboid(-5.0F, -8.0F, 0.0F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.829F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(ObsidianGluttonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}