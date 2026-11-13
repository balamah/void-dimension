// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class VoidTwinModel extends EntityModel<VoidTwinEntity> {
	private final ModelPart root;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart bodies;
	private final ModelPart shared_leg;
	private final ModelPart yoke;
	private final ModelPart left_body;
	private final ModelPart left_arm;
	private final ModelPart left_head;
	private final ModelPart right_body;
	private final ModelPart right_arm;
	private final ModelPart right_head;
	private final ModelPart decoration;
	public VoidTwinModel(ModelPart root) {
		this.root = root.getChild("root");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.left_leg = this.legs.getChild("left_leg");
		this.bodies = this.root.getChild("bodies");
		this.shared_leg = this.bodies.getChild("shared_leg");
		this.yoke = this.bodies.getChild("yoke");
		this.left_body = this.bodies.getChild("left_body");
		this.left_arm = this.left_body.getChild("left_arm");
		this.left_head = this.left_body.getChild("left_head");
		this.right_body = this.bodies.getChild("right_body");
		this.right_arm = this.right_body.getChild("right_arm");
		this.right_head = this.right_body.getChild("right_head");
		this.decoration = this.root.getChild("decoration");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.723F, -12.0504F, 3.7667F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(32, 74).cuboid(-2.2551F, 0.5913F, -2.1671F, 4.0F, 15.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, -2.0F, -2.0F, 0.0F, -0.48F, -0.1309F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.of(-9.4247F, -2.2164F, -3.1153F, 0.1044F, 0.9538F, 0.3324F));

		ModelPartData cube_r1 = left_leg.addChild("cube_r1", ModelPartBuilder.create().uv(64, 72).cuboid(-2.0064F, 0.0F, -1.419F, 4.0F, 15.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.1978F, 0.6946F, 0.0308F, 0.0252F, -0.523F, -0.0504F));

		ModelPartData bodies = root.addChild("bodies", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, -14.0F, 0.0F));

		ModelPartData shared_leg = bodies.addChild("shared_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, -4.0F));

		ModelPartData cube_r2 = shared_leg.addChild("cube_r2", ModelPartBuilder.create().uv(48, 74).cuboid(-1.0F, 0.0F, -3.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, 0.0F, 2.25F, -0.0873F, 0.0F, 0.0F));

		ModelPartData yoke = bodies.addChild("yoke", ModelPartBuilder.create().uv(80, 72).cuboid(0.0F, -10.5F, -1.5F, 3.0F, 15.0F, 4.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-8.25F, -10.5F, 4.0F, 19.0F, 14.0F, 5.0F, new Dilation(0.001F))
		.uv(66, 14).cuboid(-3.25F, -10.5F, 2.0F, 10.0F, 15.0F, 2.0F, new Dilation(0.0F))
		.uv(34, 38).cuboid(-7.25F, 2.5F, 4.0F, 18.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.0F, -3.0F, -3.0F));

		ModelPartData left_body = bodies.addChild("left_body", ModelPartBuilder.create().uv(34, 19).cuboid(-5.0F, -7.0F, -2.0F, 12.0F, 15.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -7.0F, 0.0F, 0.0F, 0.48F, 0.1309F));

		ModelPartData left_arm = left_body.addChild("left_arm", ModelPartBuilder.create().uv(0, 69).cuboid(-4.0F, 0.0F, -2.75F, 4.0F, 15.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -7.0F, 1.0F));

		ModelPartData left_head = left_body.addChild("left_head", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, -6.5F, 0.25F));

		ModelPartData cube_r3 = left_head.addChild("cube_r3", ModelPartBuilder.create().uv(0, 39).cuboid(-3.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, 0.0F, 0.0F, 0.3999F, 0.2342F, -0.0925F));

		ModelPartData right_body = bodies.addChild("right_body", ModelPartBuilder.create().uv(0, 19).cuboid(-7.0F, -7.0F, -2.0F, 12.0F, 15.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(6.75F, -7.0F, 0.0F, 0.0F, -0.48F, -0.1309F));

		ModelPartData right_arm = right_body.addChild("right_arm", ModelPartBuilder.create().uv(16, 69).cuboid(0.0F, 0.0F, -2.75F, 4.0F, 15.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -7.0F, 1.0F));

		ModelPartData right_head = right_body.addChild("right_head", ModelPartBuilder.create(), ModelTransform.pivot(-1.0F, -6.5F, 0.25F));

		ModelPartData cube_r4 = right_head.addChild("cube_r4", ModelPartBuilder.create().uv(32, 44).cuboid(-5.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(2.5F, 0.0F, 0.0F, 0.3999F, -0.2342F, 0.0925F));

		ModelPartData decoration = root.addChild("decoration", ModelPartBuilder.create().uv(64, 58).cuboid(-7.0F, -14.0F, 2.0F, 15.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r5 = decoration.addChild("cube_r5", ModelPartBuilder.create().uv(0, 88).cuboid(-3.0F, -1.0F, 1.0F, 6.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.25F, -13.0F, 1.0F, 0.0F, 1.5708F, -0.0436F));

		ModelPartData cube_r6 = decoration.addChild("cube_r6", ModelPartBuilder.create().uv(80, 0).cuboid(-3.0F, -1.0F, 1.0F, 6.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-5.25F, -13.0F, 1.0F, 0.0F, -1.5708F, 0.0436F));

		ModelPartData cube_r7 = decoration.addChild("cube_r7", ModelPartBuilder.create().uv(0, 55).cuboid(-8.0F, -14.0F, 1.0F, 16.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 1.0F, 0.0F, -0.3927F, 0.0F));

		ModelPartData cube_r8 = decoration.addChild("cube_r8", ModelPartBuilder.create().uv(48, 0).cuboid(-8.0F, -14.0F, 1.0F, 16.0F, 14.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 1.0F, 0.0F, 0.3927F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(VoidTwinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}