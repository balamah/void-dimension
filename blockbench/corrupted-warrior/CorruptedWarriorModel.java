// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class CorruptedWarriorModel extends EntityModel<Entity> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart helmet;
	private final ModelPart main_helmet_part;
	private final ModelPart crown;
	private final ModelPart torso;
	private final ModelPart lats;
	private final ModelPart body_decoration;
	private final ModelPart cape;
	private final ModelPart skirt;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart sword;
	private final ModelPart grip;
	private final ModelPart guard;
	private final ModelPart blade;
	private final ModelPart left_arm;
	private final ModelPart left_elbow;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart right_calf;
	private final ModelPart left_leg;
	private final ModelPart left_calf;
	public CorruptedWarriorModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.helmet = this.head.getChild("helmet");
		this.main_helmet_part = this.helmet.getChild("main_helmet_part");
		this.crown = this.helmet.getChild("crown");
		this.torso = this.body.getChild("torso");
		this.lats = this.torso.getChild("lats");
		this.body_decoration = this.torso.getChild("body_decoration");
		this.cape = this.body_decoration.getChild("cape");
		this.skirt = this.body_decoration.getChild("skirt");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.sword = this.right_elbow.getChild("sword");
		this.grip = this.sword.getChild("grip");
		this.guard = this.grip.getChild("guard");
		this.blade = this.sword.getChild("blade");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_elbow = this.left_arm.getChild("left_elbow");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.right_calf = this.right_leg.getChild("right_calf");
		this.left_leg = this.legs.getChild("left_leg");
		this.left_calf = this.left_leg.getChild("left_calf");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -17.0F, 0.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 55).cuboid(-5.0F, -10.0F, -4.75F, 10.0F, 10.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -20.0F, -0.25F));

		ModelPartData helmet = head.addChild("helmet", ModelPartBuilder.create(), ModelTransform.of(-1.0F, 0.0F, 4.25F, 0.0F, 3.1416F, 0.0F));

		ModelPartData main_helmet_part = helmet.addChild("main_helmet_part", ModelPartBuilder.create().uv(54, 0).cuboid(-7.0F, -11.0F, -2.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(100, 70).cuboid(-6.0F, -10.5F, 9.0F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 101).cuboid(3.25F, -9.0F, 9.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(104, 102).cuboid(-6.25F, -9.0F, 9.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F))
		.uv(38, 100).cuboid(0.0F, -6.0F, 9.0F, 4.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 101).cuboid(-6.0F, -6.0F, 9.0F, 4.0F, 6.0F, 1.0F, new Dilation(0.0F))
		.uv(54, 13).cuboid(-7.0F, -10.0F, -2.0F, 12.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = main_helmet_part.addChild("cube_r1", ModelPartBuilder.create().uv(24, 89).cuboid(-6.0F, -10.0F, 0.0F, 11.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, 4.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData cube_r2 = main_helmet_part.addChild("cube_r2", ModelPartBuilder.create().uv(88, 27).cuboid(-6.0F, -10.0F, 0.0F, 11.0F, 10.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 0.0F, 4.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData crown = helmet.addChild("crown", ModelPartBuilder.create().uv(88, 99).cuboid(-7.0F, -12.0F, 8.0F, 12.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(100, 73).cuboid(-5.0F, -12.0F, -2.0F, 8.0F, 1.0F, 2.0F, new Dilation(0.0F))
		.uv(80, 20).cuboid(3.0F, -14.0F, 8.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(102, 0).cuboid(3.0F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(102, 4).cuboid(3.0F, -14.0F, 1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(102, 8).cuboid(3.0F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(48, 103).cuboid(-7.0F, -14.0F, 1.5F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(96, 102).cuboid(-7.0F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(88, 102).cuboid(-7.0F, -14.0F, 5.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(100, 80).cuboid(-0.25F, -14.0F, 8.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(10, 101).cuboid(-3.75F, -14.0F, 8.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(64, 103).cuboid(-0.25F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(56, 103).cuboid(-3.75F, -14.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(64, 81).cuboid(-7.0F, -14.0F, 8.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r3 = crown.addChild("cube_r3", ModelPartBuilder.create().uv(100, 67).cuboid(-4.0F, -1.0F, -1.0F, 10.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -11.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r4 = crown.addChild("cube_r4", ModelPartBuilder.create().uv(64, 100).cuboid(-5.0F, -1.0F, -1.0F, 10.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -11.0F, 3.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(40, 40).cuboid(1.0F, -3.0F, -5.0F, 16.0F, 11.0F, 6.0F, new Dilation(0.0F))
		.uv(40, 25).cuboid(0.0F, -12.0F, -5.0F, 18.0F, 9.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-9.0F, -8.0F, 2.0F));

		ModelPartData lats = torso.addChild("lats", ModelPartBuilder.create().uv(64, 74).cuboid(0.0F, -3.0F, -1.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F))
		.uv(80, 13).cuboid(17.0F, -3.0F, -1.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body_decoration = torso.addChild("body_decoration", ModelPartBuilder.create(), ModelTransform.pivot(9.0F, -12.0F, 1.0F));

		ModelPartData cape = body_decoration.addChild("cape", ModelPartBuilder.create().uv(0, 25).cuboid(-9.5F, 0.0F, 1.0F, 19.0F, 27.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -1.0F));

		ModelPartData skirt = body_decoration.addChild("skirt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 20.0F, -4.0F));

		ModelPartData arms = body.addChild("arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -20.0F, 0.0F));

		ModelPartData right_arm = arms.addChild("right_arm", ModelPartBuilder.create().uv(72, 57).cuboid(-6.5F, -1.0F, -3.75F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F))
		.uv(22, 100).cuboid(-5.0F, 2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-8.25F, 0.0F, 0.0F));

		ModelPartData right_elbow = right_arm.addChild("right_elbow", ModelPartBuilder.create().uv(0, 75).cuboid(-3.0F, -0.25F, -3.0F, 6.0F, 9.0F, 6.0F, new Dilation(0.0F))
		.uv(24, 75).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 10.0F, 0.0F));

		ModelPartData sword = right_elbow.addChild("sword", ModelPartBuilder.create(), ModelTransform.of(1.0F, 6.0F, 0.0F, 0.0F, 0.0F, -1.5708F));

		ModelPartData grip = sword.addChild("grip", ModelPartBuilder.create().uv(100, 76).cuboid(-6.0F, 16.0F, -6.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 90).cuboid(-4.0F, 16.0F, -4.0F, 2.0F, 2.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -18.0F, 0.0F));

		ModelPartData guard = grip.addChild("guard", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData blade = sword.addChild("blade", ModelPartBuilder.create().uv(0, 0).cuboid(-4.5F, 16.5F, -30.0F, 3.0F, 1.0F, 24.0F, new Dilation(0.0F))
		.uv(72, 103).cuboid(-3.5F, 16.5F, -33.0F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(80, 103).cuboid(-4.0F, 16.5F, -31.75F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -18.0F, 0.0F));

		ModelPartData left_arm = arms.addChild("left_arm", ModelPartBuilder.create().uv(72, 71).cuboid(-0.5F, -1.0F, -3.75F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F))
		.uv(100, 55).cuboid(1.0F, 2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(8.25F, 0.0F, 0.0F));

		ModelPartData left_elbow = left_arm.addChild("left_elbow", ModelPartBuilder.create().uv(84, 40).cuboid(-3.0F, -0.25F, -3.0F, 6.0F, 9.0F, 6.0F, new Dilation(0.0F))
		.uv(48, 89).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 10.0F, 0.0F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -16.0F, 0.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create().uv(88, 13).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -1.0F, 0.0F));

		ModelPartData right_calf = right_leg.addChild("right_calf", ModelPartBuilder.create().uv(40, 74).cuboid(-3.0F, 0.0F, 0.0F, 6.0F, 9.0F, 6.0F, new Dilation(0.0F))
		.uv(40, 57).cuboid(-4.0F, 0.0F, -1.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, -3.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create().uv(88, 85).cuboid(-3.0F, 0.0F, -3.0F, 6.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -1.0F, 0.0F));

		ModelPartData left_calf = left_leg.addChild("left_calf", ModelPartBuilder.create().uv(64, 85).cuboid(-3.0F, 0.0F, -1.0F, 6.0F, 9.0F, 6.0F, new Dilation(0.0F))
		.uv(40, 57).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 9.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.0F, -2.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}