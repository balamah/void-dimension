// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class VoidHangmanModel extends EntityModel<VoidHangmanEntity> {
	private final ModelPart root;
	private final ModelPart chain;
	private final ModelPart hanging_chain;
	private final ModelPart hanging_chain_1;
	private final ModelPart hanging_chain_3;
	private final ModelPart hanging_chain_2;
	private final ModelPart head;
	private final ModelPart upper_head;
	private final ModelPart jaw;
	private final ModelPart body;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart lower_body;
	private final ModelPart skirt;
	private final ModelPart miasma;
	private final ModelPart right_elbow2;
	public VoidHangmanModel(ModelPart root) {
		this.root = root.getChild("root");
		this.chain = this.root.getChild("chain");
		this.hanging_chain = this.chain.getChild("hanging_chain");
		this.hanging_chain_1 = this.hanging_chain.getChild("hanging_chain_1");
		this.hanging_chain_3 = this.hanging_chain_1.getChild("hanging_chain_3");
		this.hanging_chain_2 = this.hanging_chain_3.getChild("hanging_chain_2");
		this.head = this.root.getChild("head");
		this.upper_head = this.head.getChild("upper_head");
		this.jaw = this.head.getChild("jaw");
		this.body = this.root.getChild("body");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");
		this.lower_body = this.body.getChild("lower_body");
		this.skirt = this.lower_body.getChild("skirt");
		this.miasma = this.lower_body.getChild("miasma");
		this.right_elbow2 = root.getChild("right_elbow2");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData chain = root.addChild("chain", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.75F));

		ModelPartData cube_r1 = chain.addChild("cube_r1", ModelPartBuilder.create().uv(46, 39).cuboid(-1.5659F, -2.1495F, -0.198F, 4.0F, 3.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(3.75F, -27.25F, 3.0F, 0.2786F, -0.4071F, -3.0287F));

		ModelPartData cube_r2 = chain.addChild("cube_r2", ModelPartBuilder.create().uv(46, 36).cuboid(-2.4341F, -2.1495F, -0.198F, 4.0F, 3.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(-3.75F, -27.25F, 3.0F, 0.2786F, 0.4071F, 3.0287F));

		ModelPartData cube_r3 = chain.addChild("cube_r3", ModelPartBuilder.create().uv(46, 27).cuboid(-4.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.25F, -25.5F, 1.0F, -0.3927F, -1.1781F, -1.5708F));

		ModelPartData cube_r4 = chain.addChild("cube_r4", ModelPartBuilder.create().uv(46, 24).cuboid(-2.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.25F, -25.5F, 1.0F, -0.3927F, 1.1781F, 1.5708F));

		ModelPartData cube_r5 = chain.addChild("cube_r5", ModelPartBuilder.create().uv(46, 33).cuboid(-3.0F, -2.0F, 0.0F, 5.0F, 3.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(2.933F, -22.75F, -2.25F, 0.0583F, -0.0603F, 2.6651F));

		ModelPartData cube_r6 = chain.addChild("cube_r6", ModelPartBuilder.create().uv(46, 30).cuboid(-3.0F, -2.0F, 0.0F, 5.0F, 3.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(-3.75F, -21.5F, -2.0F, 0.1135F, -0.0653F, 0.5199F));

		ModelPartData cube_r7 = chain.addChild("cube_r7", ModelPartBuilder.create().uv(46, 21).cuboid(-4.0F, -2.0F, 0.0F, 6.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -21.5F, -2.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData hanging_chain = chain.addChild("hanging_chain", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F, -40.25F, 4.5F));

		ModelPartData hanging_chain_1 = hanging_chain.addChild("hanging_chain_1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 14.0F, -1.0F));

		ModelPartData cube_r8 = hanging_chain_1.addChild("cube_r8", ModelPartBuilder.create().uv(24, 19).cuboid(-1.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.75F, 0.0F, -0.0873F, 0.0F, 0.0F));

		ModelPartData hanging_chain_3 = hanging_chain_1.addChild("hanging_chain_3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -4.75F, 0.0F));

		ModelPartData cube_r9 = hanging_chain_3.addChild("cube_r9", ModelPartBuilder.create().uv(46, 11).cuboid(-1.0F, -10.0F, -1.0F, 3.0F, 10.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.5F, 1.0F, 0.0F, 0.0F, -0.0873F));

		ModelPartData hanging_chain_2 = hanging_chain_3.addChild("hanging_chain_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -9.25F, 0.0F));

		ModelPartData cube_r10 = hanging_chain_2.addChild("cube_r10", ModelPartBuilder.create().uv(8, 35).cuboid(-1.8716F, -12.038F, -1.0F, 3.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0873F));

		ModelPartData head = root.addChild("head", ModelPartBuilder.create(), ModelTransform.of(0.0F, -24.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		ModelPartData upper_head = head.addChild("upper_head", ModelPartBuilder.create().uv(0, 14).cuboid(-3.0F, -4.0F, -6.0F, 6.0F, 4.0F, 6.0F, new Dilation(0.0F))
		.uv(24, 32).cuboid(-2.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.01F))
		.uv(24, 35).cuboid(-0.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.01F))
		.uv(20, 43).cuboid(1.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.01F)), ModelTransform.pivot(0.0F, -0.75F, 3.5F));

		ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(22, 0).cuboid(-3.0F, 0.0F, -6.0F, 6.0F, 2.0F, 6.0F, new Dilation(0.0F))
		.uv(46, 42).cuboid(-1.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.01F))
		.uv(20, 46).cuboid(0.5F, -1.0F, -6.0F, 1.0F, 2.0F, 1.0F, new Dilation(-0.01F)), ModelTransform.pivot(0.0F, -0.75F, 3.5F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.0F, -1.0F, 8.0F, 11.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(38, 8).cuboid(-2.0F, 0.0F, -0.5F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, 0.0F, 0.0472F, -0.3923F, 0.331F));

		ModelPartData right_elbow = right_arm.addChild("right_elbow", ModelPartBuilder.create(), ModelTransform.of(-1.0F, 8.0F, 1.0F, -0.3054F, 0.0F, 0.0F));

		ModelPartData cube_r11 = right_elbow.addChild("cube_r11", ModelPartBuilder.create().uv(0, 35).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, -0.5F, -0.3054F, 0.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(32, 38).cuboid(0.0F, 0.0F, -0.5F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, 0.0F, 0.3938F, -0.114F, -0.2146F));

		ModelPartData left_elbow = left_arm.addChild("left_elbow", ModelPartBuilder.create(), ModelTransform.of(1.0F, 8.0F, 1.0F, -0.3491F, 0.0F, 0.0F));

		ModelPartData cube_r12 = left_elbow.addChild("cube_r12", ModelPartBuilder.create().uv(24, 38).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, -0.5F, -0.2618F, 0.0F, 0.0F));

		ModelPartData lower_body = body.addChild("lower_body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.0F, 0.0F));

		ModelPartData skirt = lower_body.addChild("skirt", ModelPartBuilder.create().uv(40, 38).cuboid(-4.0F, -4.0F, -1.0F, 0.0F, 8.0F, 3.0F, new Dilation(0.001F))
		.uv(40, 27).cuboid(4.0F, -4.0F, -1.0F, 0.0F, 8.0F, 3.0F, new Dilation(0.001F))
		.uv(30, 19).cuboid(-4.0F, -4.0F, 2.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.001F))
		.uv(14, 24).cuboid(-4.0F, -4.0F, -1.0F, 8.0F, 8.0F, 0.0F, new Dilation(0.001F)), ModelTransform.pivot(0.0F, 4.0F, 0.0F));

		ModelPartData miasma = lower_body.addChild("miasma", ModelPartBuilder.create().uv(0, 24).cuboid(-3.25F, -11.0F, 1.5F, 7.0F, 11.0F, 0.0F, new Dilation(-0.001F))
		.uv(24, 8).cuboid(-3.25F, -11.0F, -0.75F, 7.0F, 11.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.pivot(0.0F, 11.0F, 0.0F));

		ModelPartData cube_r13 = miasma.addChild("cube_r13", ModelPartBuilder.create().uv(46, 0).cuboid(-2.0F, 0.0F, 0.0F, 3.0F, 11.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(3.25F, -11.0F, 0.0F, 0.0F, 1.5708F, -0.0873F));

		ModelPartData cube_r14 = miasma.addChild("cube_r14", ModelPartBuilder.create().uv(14, 43).cuboid(-1.0F, 0.0F, 0.0F, 3.0F, 11.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(-3.25F, -11.0F, 0.0F, 0.0F, -1.5708F, 0.0873F));

		ModelPartData cube_r15 = miasma.addChild("cube_r15", ModelPartBuilder.create().uv(14, 32).cuboid(-3.0F, -11.0F, 0.0F, 5.0F, 11.0F, 0.0F, new Dilation(-0.001F)), ModelTransform.of(0.25F, 0.0F, 0.0F, 0.0F, 0.48F, 0.0F));

		ModelPartData right_elbow2 = modelPartData.addChild("right_elbow2", ModelPartBuilder.create(), ModelTransform.pivot(-5.0F, 8.0F, 1.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(VoidHangmanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		right_elbow2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}