package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class VoidMawModel extends EntityModel<VoidMawRenderState> {
    public static final ModelLayerLocation VOID_MAW =
		new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_maw"),
							 "main");

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

	private final KeyframeAnimation attackAnimation;

	public VoidMawModel(ModelPart root) {
		super(root);

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

		this.attackAnimation = VoidMawAnimations.ATTACK.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition mouth = root.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 0.0F));

		PartDefinition upper = mouth.addOrReplaceChild("upper", CubeListBuilder.create().texOffs(56, 18).addBox(-6.0F, -6.0F, -12.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 6.0F));

		PartDefinition horns = upper.addOrReplaceChild("horns", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -6.0F));

		PartDefinition left = horns.addOrReplaceChild("left", CubeListBuilder.create().texOffs(56, 36).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(56, 54).addBox(-0.5F, -6.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(56, 48).addBox(-0.5F, -7.0F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.0001F))
		.texOffs(52, 58).addBox(-0.5F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(56, 58).addBox(-0.5F, -5.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.0001F)), PartPose.offsetAndRotation(-6.0F, -6.75F, -5.5F, 0.0F, -1.5708F, -0.2618F));

		PartDefinition right = horns.addOrReplaceChild("right", CubeListBuilder.create().texOffs(56, 42).addBox(-0.5F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(0, 58).addBox(-0.5F, -6.0F, -2.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(56, 51).addBox(-0.5F, -7.0F, -4.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(-0.0001F))
		.texOffs(60, 36).addBox(-0.5F, -6.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.0001F))
		.texOffs(60, 38).addBox(-0.5F, -5.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.0001F)), PartPose.offsetAndRotation(6.0F, -6.75F, -5.5F, 0.0F, 1.5708F, 0.2618F));

		PartDefinition upper_teeth = upper.addOrReplaceChild("upper_teeth", CubeListBuilder.create().texOffs(28, 58).addBox(-1.25F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 58).addBox(8.0F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(36, 58).addBox(6.25F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 58).addBox(0.75F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(44, 58).addBox(2.75F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 58).addBox(4.5F, 5.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -6.0F, -10.0F));

		PartDefinition lower = mouth.addOrReplaceChild("lower", CubeListBuilder.create().texOffs(56, 0).addBox(-6.0F, 0.0F, -12.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -18.0F, 6.0F));

		PartDefinition lower_teeth = lower.addOrReplaceChild("lower_teeth", CubeListBuilder.create().texOffs(4, 58).addBox(-1.25F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 58).addBox(8.0F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 58).addBox(6.25F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 58).addBox(0.75F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 58).addBox(2.75F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 58).addBox(4.5F, -7.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 6.0F, -10.0F));

		PartDefinition main_decoration = root.addOrReplaceChild("main_decoration", CubeListBuilder.create(), PartPose.offset(-0.75F, 0.0F, 0.0F));

		PartDefinition overlay = main_decoration.addOrReplaceChild("overlay", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));

		PartDefinition cube_r1 = overlay.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(1.0F, -3.0F, -14.0F, 0.0F, 30.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, 0.0F, 1.0F, 0.0F, -1.5708F, 0.0F));
		return LayerDefinition.create(modelData, 112, 112);
	}

	    @Override
	    public void setupAnim(VoidMawRenderState state) {
	        super.setupAnim(state);

			this.attackAnimation.apply(state.attackAnimationState, state.ageInTicks);
	    }
}
