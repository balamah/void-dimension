// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class UnravelerModel extends EntityModel<UnravelerEntity> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart chains;
	private final ModelPart chain_1;
	private final ModelPart chain_2;
	private final ModelPart torso_back;
	private final ModelPart torso_aa;
	private final ModelPart claws;
	private final ModelPart claws_right;
	private final ModelPart claw_right_1;
	private final ModelPart claw_right_1_end;
	private final ModelPart claw_right_1_tip;
	private final ModelPart claw_right_2;
	private final ModelPart claw_right_2_end;
	private final ModelPart claws_left;
	private final ModelPart claw_left_1;
	private final ModelPart claw_left_1_end;
	private final ModelPart claw_left_1_tip;
	private final ModelPart claw_left_2;
	private final ModelPart claw_left_2_end;
	private final ModelPart claw_left_2_tip;
	private final ModelPart claw_left_3;
	private final ModelPart claw_left_3_end1;
	private final ModelPart claw_left_3_end1_tip;
	private final ModelPart claw_left_3_end2;
	private final ModelPart claw_left_3_end2_tip;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart right_lower_leg;
	private final ModelPart left_leg;
	private final ModelPart left_lower_leg;
	public UnravelerModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.torso = this.body.getChild("torso");
		this.chains = this.torso.getChild("chains");
		this.chain_1 = this.chains.getChild("chain_1");
		this.chain_2 = this.chains.getChild("chain_2");
		this.torso_back = this.torso.getChild("torso_back");
		this.torso_aa = this.torso.getChild("torso_aa");
		this.claws = this.body.getChild("claws");
		this.claws_right = this.claws.getChild("claws_right");
		this.claw_right_1 = this.claws_right.getChild("claw_right_1");
		this.claw_right_1_end = this.claw_right_1.getChild("claw_right_1_end");
		this.claw_right_1_tip = this.claw_right_1_end.getChild("claw_right_1_tip");
		this.claw_right_2 = this.claws_right.getChild("claw_right_2");
		this.claw_right_2_end = this.claw_right_2.getChild("claw_right_2_end");
		this.claws_left = this.claws.getChild("claws_left");
		this.claw_left_1 = this.claws_left.getChild("claw_left_1");
		this.claw_left_1_end = this.claw_left_1.getChild("claw_left_1_end");
		this.claw_left_1_tip = this.claw_left_1_end.getChild("claw_left_1_tip");
		this.claw_left_2 = this.claws_left.getChild("claw_left_2");
		this.claw_left_2_end = this.claw_left_2.getChild("claw_left_2_end");
		this.claw_left_2_tip = this.claw_left_2_end.getChild("claw_left_2_tip");
		this.claw_left_3 = this.claws_left.getChild("claw_left_3");
		this.claw_left_3_end1 = this.claw_left_3.getChild("claw_left_3_end1");
		this.claw_left_3_end1_tip = this.claw_left_3_end1.getChild("claw_left_3_end1_tip");
		this.claw_left_3_end2 = this.claw_left_3.getChild("claw_left_3_end2");
		this.claw_left_3_end2_tip = this.claw_left_3_end2.getChild("claw_left_3_end2_tip");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.right_lower_leg = this.right_leg.getChild("right_lower_leg");
		this.left_leg = this.legs.getChild("left_leg");
		this.left_lower_leg = this.left_leg.getChild("left_lower_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -26.0F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 55).cuboid(-6.75F, 7.75F, -6.25F, 14.0F, 4.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 43).cuboid(-7.75F, 3.75F, -7.25F, 16.0F, 4.0F, 8.0F, new Dilation(0.0F))
		.uv(48, 43).cuboid(-7.75F, -8.25F, -7.25F, 16.0F, 4.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 28).cuboid(-9.75F, -4.25F, -6.25F, 20.0F, 8.0F, 7.0F, new Dilation(0.0F))
		.uv(86, 18).cuboid(-9.75F, -4.25F, -7.25F, 7.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(52, 86).cuboid(3.25F, -4.25F, -7.25F, 7.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(40, 55).cuboid(-5.75F, -10.25F, -7.25F, 12.0F, 2.0F, 8.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.75F, -11.25F, -6.25F, 10.0F, 17.0F, 11.0F, new Dilation(0.01F))
		.uv(80, 55).cuboid(-1.75F, -11.25F, -7.25F, 10.0F, 5.0F, 1.0F, new Dilation(0.01F))
		.uv(96, 71).cuboid(-1.25F, -2.75F, -8.0F, 3.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.25F, 0.75F, 6.25F));

		ModelPartData chains = torso.addChild("chains", ModelPartBuilder.create(), ModelTransform.pivot(0.25F, 25.25F, -6.25F));

		ModelPartData chain_1 = chains.addChild("chain_1", ModelPartBuilder.create(), ModelTransform.pivot(6.0F, -30.0F, 11.0F));

		ModelPartData cube_r1 = chain_1.addChild("cube_r1", ModelPartBuilder.create().uv(46, 99).cuboid(-1.0408F, -5.6194F, -0.0286F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.25F, -7.5F, -2.5F, 1.2243F, 0.0302F, 0.6955F));

		ModelPartData cube_r2 = chain_1.addChild("cube_r2", ModelPartBuilder.create().uv(6, 98).cuboid(-1.0408F, -5.6194F, -0.0286F, 3.0F, 6.0F, 0.0F, new Dilation(0.001F)), ModelTransform.of(3.5F, -3.75F, 0.75F, 0.5698F, 0.0302F, 0.6955F));

		ModelPartData cube_r3 = chain_1.addChild("cube_r3", ModelPartBuilder.create().uv(0, 98).cuboid(-1.0F, -4.0F, 0.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.75F, -0.5F, 0.25F, -0.1719F, 0.0302F, 0.6955F));

		ModelPartData chain_2 = chains.addChild("chain_2", ModelPartBuilder.create(), ModelTransform.of(-3.75F, -30.0F, 11.0F, -0.4784F, -0.0403F, 0.6207F));

		ModelPartData cube_r4 = chain_2.addChild("cube_r4", ModelPartBuilder.create().uv(40, 97).cuboid(-1.9592F, -6.6194F, -0.0286F, 3.0F, 7.0F, 0.0F, new Dilation(0.001F)), ModelTransform.of(-7.75F, -6.75F, -2.25F, 0.6844F, 0.3168F, -1.1488F));

		ModelPartData cube_r5 = chain_2.addChild("cube_r5", ModelPartBuilder.create().uv(94, 97).cuboid(-1.9592F, -6.6194F, -0.0286F, 3.0F, 7.0F, 0.0F, new Dilation(0.001F)), ModelTransform.of(-3.5F, -3.75F, 0.75F, 0.5645F, 0.0874F, -0.8795F));

		ModelPartData cube_r6 = chain_2.addChild("cube_r6", ModelPartBuilder.create().uv(52, 99).cuboid(-2.0F, -4.0F, 0.0F, 3.0F, 6.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.75F, -0.5F, 0.25F, -0.1719F, -0.0302F, -0.6955F));

		ModelPartData torso_back = torso.addChild("torso_back", ModelPartBuilder.create().uv(54, 27).cuboid(-8.0F, -5.0F, 4.0F, 18.0F, 8.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 65).cuboid(-7.0F, -5.0F, 7.0F, 16.0F, 8.0F, 1.0F, new Dilation(0.0F))
		.uv(34, 71).cuboid(-6.0F, 3.0F, 4.0F, 14.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(94, 12).cuboid(-6.0F, -9.0F, 4.0F, 6.0F, 4.0F, 2.0F, new Dilation(0.0F))
		.uv(16, 86).cuboid(-5.0F, -7.0F, 5.0F, 4.0F, 12.0F, 2.0F, new Dilation(-0.001F)), ModelTransform.pivot(-0.75F, 0.75F, -3.25F));

		ModelPartData torso_aa = torso.addChild("torso_aa", ModelPartBuilder.create().uv(72, 81).cuboid(-6.0F, -6.0F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(-0.001F))
		.uv(42, 0).cuboid(-8.0F, -2.0F, -4.0F, 18.0F, 1.0F, 8.0F, new Dilation(-0.01F))
		.uv(42, 9).cuboid(-8.0F, 7.0F, -4.0F, 18.0F, 1.0F, 8.0F, new Dilation(-0.01F))
		.uv(42, 18).cuboid(-6.0F, 11.0F, -4.0F, 14.0F, 1.0F, 8.0F, new Dilation(-0.001F)), ModelTransform.pivot(-0.75F, -3.25F, -3.25F));

		ModelPartData claws = body.addChild("claws", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 1.0F, 4.0F));

		ModelPartData claws_right = claws.addChild("claws_right", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, -0.5F));

		ModelPartData claw_right_1 = claws_right.addChild("claw_right_1", ModelPartBuilder.create().uv(96, 31).cuboid(1.0F, -2.0F, -2.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 0.25F, 2.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData cube_r7 = claw_right_1.addChild("cube_r7", ModelPartBuilder.create().uv(54, 38).cuboid(0.0F, -3.4142F, -1.0F, 10.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(9.0F, 0.0F, -0.5F, 0.0F, 0.48F, -1.5708F));

		ModelPartData claw_right_1_end = claw_right_1.addChild("claw_right_1_end", ModelPartBuilder.create(), ModelTransform.of(7.0F, -8.5F, -4.5F, -0.4137F, 0.5315F, -0.8429F));

		ModelPartData cube_r8 = claw_right_1_end.addChild("cube_r8", ModelPartBuilder.create().uv(70, 65).cuboid(-3.2785F, -2.8216F, -1.5776F, 14.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.8998F, 1.7588F, -0.3112F, 0.0F, 0.0F, 1.0472F));

		ModelPartData claw_right_1_tip = claw_right_1_end.addChild("claw_right_1_tip", ModelPartBuilder.create(), ModelTransform.pivot(7.1498F, 9.5088F, -0.3112F));

		ModelPartData cube_r9 = claw_right_1_tip.addChild("cube_r9", ModelPartBuilder.create().uv(80, 90).cuboid(-0.0151F, -0.0064F, -1.5883F, 8.0F, 1.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(-1.5F, 10.0F, 0.0F, 0.1232F, -0.0709F, 2.0252F));

		ModelPartData cube_r10 = claw_right_1_tip.addChild("cube_r10", ModelPartBuilder.create().uv(72, 77).cuboid(-0.0151F, -1.0064F, -1.5883F, 10.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.1355F, -0.0427F, 1.8085F));

		ModelPartData claw_right_2 = claws_right.addChild("claw_right_2", ModelPartBuilder.create(), ModelTransform.of(7.0F, 5.75F, -0.75F, 0.1522F, 0.3351F, 0.1144F));

		ModelPartData cube_r11 = claw_right_2.addChild("cube_r11", ModelPartBuilder.create().uv(80, 61).cuboid(-1.0F, -2.0F, -1.0F, 8.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.25F, -1.0F, 0.0F, 0.0F, 0.48F));

		ModelPartData claw_right_2_end = claw_right_2.addChild("claw_right_2_end", ModelPartBuilder.create(), ModelTransform.of(6.75F, 2.5F, -1.25F, -0.136F, 0.3479F, 0.2437F));

		ModelPartData cube_r12 = claw_right_2_end.addChild("cube_r12", ModelPartBuilder.create().uv(28, 94).cuboid(-0.0151F, -0.0064F, -1.5883F, 7.0F, 1.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(5.3998F, 4.7588F, 0.9388F, 0.1089F, -0.1383F, 1.5363F));

		ModelPartData cube_r13 = claw_right_2_end.addChild("cube_r13", ModelPartBuilder.create().uv(94, 8).cuboid(-2.2785F, -1.8216F, -1.5776F, 7.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.1498F, 2.0088F, 0.9388F, 0.0F, 0.0F, 0.8727F));

		ModelPartData claws_left = claws.addChild("claws_left", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData claw_left_1 = claws_left.addChild("claw_left_1", ModelPartBuilder.create(), ModelTransform.of(-8.0F, -5.75F, -1.0F, 0.0F, -0.3054F, 0.3491F));

		ModelPartData cube_r14 = claw_left_1.addChild("cube_r14", ModelPartBuilder.create().uv(94, 0).cuboid(-6.0F, -2.0F, -1.0F, 7.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(-1.0F, 1.75F, 2.0F, 0.0F, 0.0F, 0.6981F));

		ModelPartData claw_left_1_end = claw_left_1.addChild("claw_left_1_end", ModelPartBuilder.create(), ModelTransform.of(-4.0F, -2.5F, 2.5F, -0.2097F, 0.4938F, -0.4517F));

		ModelPartData cube_r15 = claw_left_1_end.addChild("cube_r15", ModelPartBuilder.create().uv(66, 71).cuboid(-10.0F, -3.0F, -2.0F, 12.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		ModelPartData claw_left_1_tip = claw_left_1_end.addChild("claw_left_1_tip", ModelPartBuilder.create(), ModelTransform.of(-6.75F, 6.75F, -0.75F, 0.0F, 0.0F, -0.2618F));

		ModelPartData cube_r16 = claw_left_1_tip.addChild("cube_r16", ModelPartBuilder.create().uv(62, 95).cuboid(-0.499F, -0.1123F, -1.0F, 1.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-3.5F, 7.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

		ModelPartData cube_r17 = claw_left_1_tip.addChild("cube_r17", ModelPartBuilder.create().uv(96, 42).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.25F, 1.5F, 0.0F, 0.0F, 0.0F, 0.4363F));

		ModelPartData claw_left_2 = claws_left.addChild("claw_left_2", ModelPartBuilder.create(), ModelTransform.of(-8.0F, -6.0F, -2.5F, 0.0F, -0.3491F, 0.0F));

		ModelPartData cube_r18 = claw_left_2.addChild("cube_r18", ModelPartBuilder.create().uv(94, 4).cuboid(-7.4746F, -0.6488F, -1.0F, 7.0F, 2.0F, 2.0F, new Dilation(-0.01F)), ModelTransform.of(-4.0F, 3.25F, -1.0F, 0.0F, 0.0F, 0.7418F));

		ModelPartData cube_r19 = claw_left_2.addChild("cube_r19", ModelPartBuilder.create().uv(28, 97).cuboid(-3.0F, -2.0F, -1.0F, 4.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 3.25F, -1.0F, 0.0F, 0.0F, -0.3491F));

		ModelPartData claw_left_2_end = claw_left_2.addChild("claw_left_2_end", ModelPartBuilder.create(), ModelTransform.of(-10.75F, -1.25F, -1.5F, 0.0097F, -0.218F, -0.3065F));

		ModelPartData cube_r20 = claw_left_2_end.addChild("cube_r20", ModelPartBuilder.create().uv(34, 65).cuboid(-13.0F, -2.0F, -1.0F, 15.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		ModelPartData claw_left_2_tip = claw_left_2_end.addChild("claw_left_2_tip", ModelPartBuilder.create(), ModelTransform.of(-9.25F, 8.75F, 0.0F, -0.1739F, -0.0151F, -0.0859F));

		ModelPartData cube_r21 = claw_left_2_tip.addChild("cube_r21", ModelPartBuilder.create().uv(16, 74).cuboid(-6.9267F, -0.5487F, -1.0F, 7.0F, 1.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(-2.25F, 8.75F, 0.0F, 0.0F, 0.0F, -1.4835F));

		ModelPartData cube_r22 = claw_left_2_tip.addChild("cube_r22", ModelPartBuilder.create().uv(78, 38).cuboid(-8.9267F, -0.5487F, -1.0F, 9.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.309F));

		ModelPartData claw_left_3 = claws_left.addChild("claw_left_3", ModelPartBuilder.create(), ModelTransform.of(-8.0F, 6.0F, 1.0F, -0.053F, 0.1664F, -0.3099F));

		ModelPartData cube_r23 = claw_left_3.addChild("cube_r23", ModelPartBuilder.create().uv(96, 27).cuboid(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 1.0F, 0.0F, 0.1793F, -0.5872F, -0.3161F));

		ModelPartData claw_left_3_end1 = claw_left_3.addChild("claw_left_3_end1", ModelPartBuilder.create(), ModelTransform.of(-4.0038F, -0.25F, -2.5755F, -0.0873F, -0.9599F, 0.0F));

		ModelPartData cube_r24 = claw_left_3_end1.addChild("cube_r24", ModelPartBuilder.create().uv(94, 93).cuboid(-4.5473F, -2.2111F, -1.0218F, 6.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(-1.8225F, 1.25F, -0.0545F, 0.0284F, -0.0418F, 0.5541F));

		ModelPartData claw_left_3_end1_tip = claw_left_3_end1.addChild("claw_left_3_end1_tip", ModelPartBuilder.create(), ModelTransform.pivot(-5.6029F, -1.0F, -0.0858F));

		ModelPartData cube_r25 = claw_left_3_end1_tip.addChild("cube_r25", ModelPartBuilder.create().uv(96, 52).cuboid(-4.7978F, -0.0676F, -1.0436F, 5.0F, 1.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(-4.25F, 4.0F, 0.0F, 0.0F, 0.0F, -1.5272F));

		ModelPartData cube_r26 = claw_left_3_end1_tip.addChild("cube_r26", ModelPartBuilder.create().uv(28, 90).cuboid(-5.6798F, -1.6184F, -1.0436F, 8.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.8727F));

		ModelPartData claw_left_3_end2 = claw_left_3.addChild("claw_left_3_end2", ModelPartBuilder.create(), ModelTransform.of(-1.0F, 2.0F, -1.5F, -0.3863F, -0.0557F, -0.0553F));

		ModelPartData cube_r27 = claw_left_3_end2.addChild("cube_r27", ModelPartBuilder.create().uv(46, 95).cuboid(-6.0794F, -0.9255F, -1.0218F, 6.0F, 2.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(-2.25F, -1.0F, -1.0F, 0.47F, -0.5928F, -0.9292F));

		ModelPartData claw_left_3_end2_tip = claw_left_3_end2.addChild("claw_left_3_end2_tip", ModelPartBuilder.create(), ModelTransform.pivot(-5.75F, 3.5F, -4.75F));

		ModelPartData cube_r28 = claw_left_3_end2_tip.addChild("cube_r28", ModelPartBuilder.create().uv(96, 35).cuboid(-4.7978F, -0.0676F, -1.0436F, 5.0F, 1.0F, 2.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.8933F, 0.2409F, -1.7604F));

		ModelPartData legs = root.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(-4.0F, -17.0F, -2.0F));

		ModelPartData right_leg = legs.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 1.0F, 0.0F, 0.1309F, 0.0F));

		ModelPartData knee_r1 = right_leg.addChild("knee_r1", ModelPartBuilder.create().uv(90, 81).cuboid(-2.0653F, -2.9644F, -1.6805F, 4.0F, 5.0F, 3.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 7.5F, -3.75F, 0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r29 = right_leg.addChild("cube_r29", ModelPartBuilder.create().uv(0, 74).cuboid(-1.0653F, -9.2549F, -0.3408F, 5.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, 5.5F, -3.75F, -0.8727F, 0.0F, 0.0F));

		ModelPartData right_lower_leg = right_leg.addChild("right_lower_leg", ModelPartBuilder.create().uv(16, 77).cuboid(-2.4347F, 7.7057F, 0.6875F, 5.0F, 4.0F, 5.0F, new Dilation(0.001F)), ModelTransform.pivot(0.0979F, 7.0F, -3.7479F));

		ModelPartData cube_r30 = right_lower_leg.addChild("cube_r30", ModelPartBuilder.create().uv(0, 87).cuboid(-1.0F, -9.0F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.001F)), ModelTransform.of(-0.5F, 9.0F, 4.5F, 0.48F, 0.0F, 0.0F));

		ModelPartData left_leg = legs.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.of(8.0F, 0.5F, 1.25F, 0.0F, -0.1309F, 0.0F));

		ModelPartData knee_r2 = left_leg.addChild("knee_r2", ModelPartBuilder.create().uv(80, 93).cuboid(-1.9347F, -2.9644F, -1.6805F, 4.0F, 5.0F, 3.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 7.0F, -4.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r31 = left_leg.addChild("cube_r31", ModelPartBuilder.create().uv(36, 77).cuboid(-1.9347F, -9.2549F, -0.3408F, 5.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 5.0F, -4.0F, -0.8727F, 0.0F, 0.0F));

		ModelPartData left_lower_leg = left_leg.addChild("left_lower_leg", ModelPartBuilder.create().uv(52, 77).cuboid(-2.5653F, 7.7073F, 0.3948F, 5.0F, 4.0F, 5.0F, new Dilation(0.001F)), ModelTransform.pivot(0.0979F, 6.5F, -3.9979F));

		ModelPartData cube_r32 = left_lower_leg.addChild("cube_r32", ModelPartBuilder.create().uv(68, 90).cuboid(-1.0F, -9.0F, -2.0F, 3.0F, 8.0F, 3.0F, new Dilation(0.001F)), ModelTransform.of(-0.5F, 9.0F, 4.5F, 0.48F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(UnravelerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}