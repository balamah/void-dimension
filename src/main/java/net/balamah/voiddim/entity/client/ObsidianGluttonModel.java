package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.VoidDimension;

public class ObsidianGluttonModel extends EntityModel<ObsidianGluttonRenderState> {
	public static final ModelLayerLocation OBSIDIAN_GLUTTON =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "obsidian_glutton"),
							 "main");

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
	private final ModelPart tentacle_1_1;
	private final ModelPart tentacle_1_2;
	private final ModelPart tentacle_1_3;
	private final ModelPart tentacles_group_2;
	private final ModelPart tentacle_2_1;
	private final ModelPart tentacle_2_2;
	private final ModelPart tentacle_2_3;
	private final ModelPart miasma;

	private final KeyframeAnimation idleAnimation;
	private final KeyframeAnimation attack1Animation;
	private final KeyframeAnimation attack2Animation;

	public ObsidianGluttonModel(ModelPart root) {
		super(root);

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
		this.tentacle_1_1 = this.tentacles_group_1.getChild("1_1");
		this.tentacle_1_2 = this.tentacles_group_1.getChild("1_2");
		this.tentacle_1_3 = this.tentacles_group_1.getChild("1_3");
		this.tentacles_group_2 = this.tentacles.getChild("tentacles_group_2");
		this.tentacle_2_1 = this.tentacles_group_2.getChild("2_1");
		this.tentacle_2_2 = this.tentacles_group_2.getChild("2_2");
		this.tentacle_2_3 = this.tentacles_group_2.getChild("2_3");
		this.miasma = this.lower_body.getChild("miasma");

		this.idleAnimation = ObsidianGluttonAnimations.IDLE.bake(root);
		this.attack1Animation = ObsidianGluttonAnimations.ATTACK_1.bake(root);
		this.attack2Animation = ObsidianGluttonAnimations.ATTACK_2.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition block = root.addOrReplaceChild("block", CubeListBuilder.create().texOffs(0, 30).addBox(-8.0F, -24.0F, 4.0F, 16.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-8.0F, -24.0F, -4.0F, 2.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(40, 30).addBox(6.0F, -24.0F, -8.0F, 2.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition upper = block.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(0, 15).addBox(-6.0F, 0.0F, -4.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.001F))
		.texOffs(68, 7).addBox(-8.0F, 0.0F, -4.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -24.0F, -4.0F));

		PartDefinition upper_teeth = upper.addOrReplaceChild("upper_teeth", CubeListBuilder.create().texOffs(58, 24).addBox(-5.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(70, 22).addBox(-7.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(62, 24).addBox(-3.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(66, 24).addBox(-1.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(48, 66).addBox(1.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(68, 14).addBox(3.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.001F))
		.texOffs(68, 18).addBox(5.0F, -21.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 24.0F, 4.0F));

		PartDefinition lower = block.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -2.0F, -8.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.001F))
		.texOffs(68, 0).addBox(-8.0F, -2.0F, -8.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition tongue = lower.addOrReplaceChild("tongue", CubeListBuilder.create().texOffs(54, 58).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition tongue_1 = tongue.addOrReplaceChild("tongue_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = tongue_1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(54, 65).addBox(-1.0F, -2.0F, -5.0F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.0F, -0.6545F, 0.0F, 0.0F));

		PartDefinition tongue_2 = tongue_1.addOrReplaceChild("tongue_2", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -4.0F));

		PartDefinition cube_r2 = tongue_2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 58).addBox(-1.0F, -0.5979F, -6.1846F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.5F, -1.25F, -0.5F, 0.0873F, 0.0F, 0.0F));

		PartDefinition tongue_3 = tongue_2.addOrReplaceChild("tongue_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition cube_r3 = tongue_3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(20, 58).addBox(-1.0F, -0.5979F, -6.1846F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -0.25F, 0.6981F, 0.0F, 0.0F));

		PartDefinition tongue_4 = tongue_3.addOrReplaceChild("tongue_4", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -5.0F));

		PartDefinition cube_r4 = tongue_4.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(48, 24).addBox(0.0F, -0.5979F, -4.1846F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0001F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.25F, 1.2217F, 0.0F, 0.0F));

		PartDefinition lower_teeth = lower.addOrReplaceChild("lower_teeth", CubeListBuilder.create().texOffs(68, 28).addBox(-6.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 52).addBox(-8.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 32).addBox(-4.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 36).addBox(-2.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 40).addBox(0.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 44).addBox(2.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(68, 48).addBox(4.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -8.0F));

		PartDefinition lower_body = root.addOrReplaceChild("lower_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tentacles = lower_body.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tentacles_group_1 = tentacles.addOrReplaceChild("tentacles_group_1", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, -5.0F));

		PartDefinition tentacle_1_1 = tentacles_group_1.addOrReplaceChild("1_1", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 1.0F));

		PartDefinition cube_r5 = tentacle_1_1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(16, 66).addBox(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.5F, 0.2182F, 0.0F, 0.0F));

		PartDefinition tentacle_1_2 = tentacles_group_1.addOrReplaceChild("1_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = tentacle_1_2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 66).addBox(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition tentacle_1_3 = tentacles_group_1.addOrReplaceChild("1_3", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 1.0F));

		PartDefinition cube_r7 = tentacle_1_3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(8, 66).addBox(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.5F, 0.2182F, 0.0F, 0.0F));

		PartDefinition tentacles_group_2 = tentacles.addOrReplaceChild("tentacles_group_2", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 4.0F));

		PartDefinition tentacle_2_1 = tentacles_group_2.addOrReplaceChild("2_1", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -1.0F));

		PartDefinition cube_r8 = tentacle_2_1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(32, 66).addBox(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, 0.0F, 1.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition tentacle_2_2 = tentacles_group_2.addOrReplaceChild("2_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition cube_r9 = tentacle_2_2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(40, 66).addBox(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, 0.0F, -0.5F, 0.2182F, 0.0F, 0.0F));

		PartDefinition tentacle_2_3 = tentacles_group_2.addOrReplaceChild("2_3", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, -1.0F));

		PartDefinition cube_r10 = tentacle_2_3.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 66).addBox(0.0F, -1.0F, -1.5F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, 0.0F, 1.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition miasma = lower_body.addOrReplaceChild("miasma", CubeListBuilder.create().texOffs(38, 58).addBox(-4.0F, -8.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r11 = miasma.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(20, 50).addBox(-5.0F, -8.0F, 0.0F, 10.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.829F, 0.0F));

		PartDefinition cube_r12 = miasma.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 50).addBox(-5.0F, -8.0F, 0.0F, 10.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.829F, 0.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(ObsidianGluttonRenderState state) {
		super.setupAnim(state);

		this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
		this.attack1Animation.apply(state.attack1AnimationState, state.ageInTicks);
		this.attack2Animation.apply(state.attack2AnimationState, state.ageInTicks);
	}
}
