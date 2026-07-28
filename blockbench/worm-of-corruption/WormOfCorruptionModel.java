// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class WormOfCorruptionModel extends EntityModel<Entity> {
	private final ModelPart root;
	private final ModelPart worm;
	private final ModelPart head;
	private final ModelPart mouth;
	private final ModelPart upper;
	private final ModelPart upper_teeth;
	private final ModelPart lower;
	private final ModelPart lower_teeth;
	private final ModelPart head_horns;
	private final ModelPart horn_1;
	private final ModelPart horn_2;
	private final ModelPart horn_3;
	private final ModelPart horn_4;
	private final ModelPart body;
	private final ModelPart body_1;
	private final ModelPart body_2;
	private final ModelPart body_3;
	private final ModelPart body_4;
	private final ModelPart body_5;
	private final ModelPart horn_5;
	private final ModelPart miasma;
	private final ModelPart bone;
	private final ModelPart overlay;
	private final ModelPart horns;
	public WormOfCorruptionModel(ModelPart root) {
		this.root = root.getChild("root");
		this.worm = this.root.getChild("worm");
		this.head = this.worm.getChild("head");
		this.mouth = this.head.getChild("mouth");
		this.upper = this.mouth.getChild("upper");
		this.upper_teeth = this.upper.getChild("upper_teeth");
		this.lower = this.mouth.getChild("lower");
		this.lower_teeth = this.lower.getChild("lower_teeth");
		this.head_horns = this.head.getChild("head_horns");
		this.horn_1 = this.head_horns.getChild("horn_1");
		this.horn_2 = this.head_horns.getChild("horn_2");
		this.horn_3 = this.head_horns.getChild("horn_3");
		this.horn_4 = this.head_horns.getChild("horn_4");
		this.body = this.worm.getChild("body");
		this.body_1 = this.body.getChild("body_1");
		this.body_2 = this.body.getChild("body_2");
		this.body_3 = this.body.getChild("body_3");
		this.body_4 = this.body.getChild("body_4");
		this.body_5 = this.body.getChild("body_5");
		this.horn_5 = this.body_5.getChild("horn_5");
		this.miasma = this.root.getChild("miasma");
		this.bone = this.miasma.getChild("bone");
		this.overlay = this.miasma.getChild("overlay");
		this.horns = this.root.getChild("horns");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData worm = root.addChild("worm", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -18.25F, 1.75F));

		ModelPartData head = worm.addChild("head", ModelPartBuilder.create(), ModelTransform.of(0.0F, -13.0F, -5.0F, 0.2182F, 0.0F, 0.0F));

		ModelPartData mouth = head.addChild("mouth", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 29.9289F, -0.6493F));

		ModelPartData upper = mouth.addChild("upper", ModelPartBuilder.create().uv(0, 48).cuboid(-8.0F, -8.0F, 0.0F, 16.0F, 7.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -30.0F, 8.0F, 3.1416F, 0.0F, 3.1416F));

		ModelPartData upper_teeth = upper.addChild("upper_teeth", ModelPartBuilder.create().uv(42, 130).cuboid(-7.0F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(46, 130).cuboid(-5.0F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(128, 167).cuboid(-3.0F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(132, 167).cuboid(-1.0F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(136, 167).cuboid(4.25F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(140, 167).cuboid(2.5F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(144, 167).cuboid(6.25F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(148, 167).cuboid(0.75F, -35.0F, 6.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 34.0F, 8.0F));

		ModelPartData lower = mouth.addChild("lower", ModelPartBuilder.create().uv(64, 48).cuboid(-8.0F, -1.0F, -15.0F, 16.0F, 6.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -30.0F, 7.0F));

		ModelPartData lower_teeth = lower.addChild("lower_teeth", ModelPartBuilder.create().uv(52, 119).cuboid(-7.0F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(56, 119).cuboid(-5.0F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(60, 119).cuboid(-3.0F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(50, 129).cuboid(-1.0F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(54, 129).cuboid(4.25F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(38, 130).cuboid(2.5F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(34, 130).cuboid(6.25F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(58, 129).cuboid(0.75F, -35.0F, -7.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 32.0F, -7.0F));

		ModelPartData head_horns = head.addChild("head_horns", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData horn_1 = head_horns.addChild("horn_1", ModelPartBuilder.create().uv(118, 167).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 8.0F));

		ModelPartData horn_2 = head_horns.addChild("horn_2", ModelPartBuilder.create().uv(52, 113).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 8.0F));

		ModelPartData horn_3 = head_horns.addChild("horn_3", ModelPartBuilder.create().uv(50, 122).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 4.0F, 8.0F));

		ModelPartData horn_4 = head_horns.addChild("horn_4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.0F, 8.0F));

		ModelPartData body = worm.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 18.25F, -1.75F));

		ModelPartData body_1 = body.addChild("body_1", ModelPartBuilder.create().uv(64, 70).cuboid(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, -8.0F));

		ModelPartData body_2 = body.addChild("body_2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -5.0F, -8.0F));

		ModelPartData cube_r1 = body_2.addChild("cube_r1", ModelPartBuilder.create().uv(0, 71).cuboid(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData body_3 = body.addChild("body_3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -10.0F, -8.0F));

		ModelPartData cube_r2 = body_3.addChild("cube_r2", ModelPartBuilder.create().uv(64, 91).cuboid(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -0.5F, 0.1309F, 0.0F, 0.0F));

		ModelPartData cube_r3 = body_3.addChild("cube_r3", ModelPartBuilder.create().uv(102, 167).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -5.0F, 15.25F, 0.1309F, 0.0F, 0.0F));

		ModelPartData body_4 = body.addChild("body_4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -15.0F, -8.0F));

		ModelPartData cube_r4 = body_4.addChild("cube_r4", ModelPartBuilder.create().uv(34, 113).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.5F, 15.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r5 = body_4.addChild("cube_r5", ModelPartBuilder.create().uv(0, 92).cuboid(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -1.0F, 0.1309F, 0.0F, 0.0F));

		ModelPartData body_5 = body.addChild("body_5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -20.0F, -8.0F));

		ModelPartData cube_r6 = body_5.addChild("cube_r6", ModelPartBuilder.create().uv(34, 122).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.5F, 13.5F, 0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r7 = body_5.addChild("cube_r7", ModelPartBuilder.create().uv(64, 112).cuboid(-8.0F, -5.0F, 0.0F, 16.0F, 5.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -1.75F, 0.1745F, 0.0F, 0.0F));

		ModelPartData horn_5 = body_5.addChild("horn_5", ModelPartBuilder.create(), ModelTransform.of(0.0F, 16.25F, 15.75F, 0.1309F, 0.0F, 0.0F));

		ModelPartData miasma = root.addChild("miasma", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData bone = miasma.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, -9.0F));

		ModelPartData cube_r8 = bone.addChild("cube_r8", ModelPartBuilder.create().uv(34, 133).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 18.0F, -0.176F, -0.1289F, 0.0229F));

		ModelPartData cube_r9 = bone.addChild("cube_r9", ModelPartBuilder.create().uv(68, 133).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 0.0F, 18.0F, -0.1962F, -0.4721F, 0.0902F));

		ModelPartData cube_r10 = bone.addChild("cube_r10", ModelPartBuilder.create().uv(102, 133).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.0F, 18.0F, -0.2677F, -0.8547F, 0.2041F));

		ModelPartData cube_r11 = bone.addChild("cube_r11", ModelPartBuilder.create().uv(162, 116).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 0.0F, 13.0F, 0.0F, 1.3963F, 3.1416F));

		ModelPartData cube_r12 = bone.addChild("cube_r12", ModelPartBuilder.create().uv(0, 164).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 0.0F, 10.0F, 0.0F, 1.4835F, 3.1416F));

		ModelPartData cube_r13 = bone.addChild("cube_r13", ModelPartBuilder.create().uv(34, 167).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 0.0F, 7.0F, -3.1416F, 1.5272F, 0.0F));

		ModelPartData cube_r14 = bone.addChild("cube_r14", ModelPartBuilder.create().uv(68, 167).cuboid(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, 0.0F, 5.0F, 0.0F, -1.3963F, 3.1416F));

		ModelPartData cube_r15 = bone.addChild("cube_r15", ModelPartBuilder.create().uv(162, 99).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 7.0F, -3.1416F, -1.5272F, 0.0F));

		ModelPartData cube_r16 = bone.addChild("cube_r16", ModelPartBuilder.create().uv(162, 48).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 10.0F, 0.0F, -1.4835F, -3.1416F));

		ModelPartData cube_r17 = bone.addChild("cube_r17", ModelPartBuilder.create().uv(162, 65).cuboid(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 3.0F, 0.0F, 1.0908F, -3.1416F));

		ModelPartData cube_r18 = bone.addChild("cube_r18", ModelPartBuilder.create().uv(162, 82).cuboid(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 5.0F, 0.0F, 1.3963F, -3.1416F));

		ModelPartData cube_r19 = bone.addChild("cube_r19", ModelPartBuilder.create().uv(136, 150).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 13.0F, 0.0F, -1.3963F, -3.1416F));

		ModelPartData cube_r20 = bone.addChild("cube_r20", ModelPartBuilder.create().uv(102, 150).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 15.0F, 0.0F, -1.0908F, -3.1416F));

		ModelPartData cube_r21 = bone.addChild("cube_r21", ModelPartBuilder.create().uv(136, 133).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 18.0F, -0.2677F, 0.8547F, -0.2041F));

		ModelPartData cube_r22 = bone.addChild("cube_r22", ModelPartBuilder.create().uv(0, 147).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, 18.0F, -0.1962F, 0.4721F, -0.0902F));

		ModelPartData cube_r23 = bone.addChild("cube_r23", ModelPartBuilder.create().uv(34, 150).cuboid(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 18.0F, -0.176F, 0.1289F, -0.0229F));

		ModelPartData cube_r24 = bone.addChild("cube_r24", ModelPartBuilder.create().uv(68, 150).cuboid(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 18.0F, -0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r25 = bone.addChild("cube_r25", ModelPartBuilder.create().uv(128, 99).cuboid(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 0.0F, 0.0F, 0.176F, 0.1289F, 0.0229F));

		ModelPartData cube_r26 = bone.addChild("cube_r26", ModelPartBuilder.create().uv(128, 116).cuboid(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, 0.0F, 0.0F, 0.1962F, 0.4721F, 0.0902F));

		ModelPartData cube_r27 = bone.addChild("cube_r27", ModelPartBuilder.create().uv(0, 130).cuboid(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.0F, 0.0F, 0.2677F, 0.8547F, 0.2041F));

		ModelPartData cube_r28 = bone.addChild("cube_r28", ModelPartBuilder.create().uv(128, 82).cuboid(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(7.0F, 0.0F, 0.0F, 0.2677F, -0.8547F, -0.2041F));

		ModelPartData cube_r29 = bone.addChild("cube_r29", ModelPartBuilder.create().uv(128, 65).cuboid(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 0.0F, 0.0F, 0.1962F, -0.4721F, -0.0902F));

		ModelPartData cube_r30 = bone.addChild("cube_r30", ModelPartBuilder.create().uv(128, 48).cuboid(0.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 0.0F, 0.176F, -0.1289F, -0.0229F));

		ModelPartData cube_r31 = bone.addChild("cube_r31", ModelPartBuilder.create().uv(0, 113).cuboid(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		ModelPartData overlay = miasma.addChild("overlay", ModelPartBuilder.create().uv(0, 0).cuboid(-24.0F, 1.5F, -15.0F, 46.0F, 0.0F, 48.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -1.0F, -9.0F));

		ModelPartData horns = root.addChild("horns", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 208, 208);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}