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
import net.balamah.voiddim.entity.client.base.BasicAnimations;

public class UnknownOneModel extends EntityModel<UnknownOneRenderState> {
	public static final ModelLayerLocation UNKNOWN_ONE =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "unknown_one"),
							 "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation rageBaitAnimation;

	public UnknownOneModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.left_arm = this.arms.getChild("left_arm");
		this.legs = this.root.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.left_leg = this.legs.getChild("left_leg");

		this.walkAnimation = BasicAnimations.WALK.bake(root);
		this.rageBaitAnimation = UnknownOneAnimations.RAGEBAIT.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-3.5F, -29.0F, -1.5F, 7.0F, 16.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.25F, -14.0F, -3.0F, 8.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -29.0F, 0.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -27.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(20, 20).addBox(0.0F, 2.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(36, 0).addBox(-0.5F, -1.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.0F, 0.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 0).addBox(-3.0F, 1.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(36, 6).addBox(-3.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -13.25F, 0.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(28, 18).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.25F, 0.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(28, 33).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.25F, 0.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(UnknownOneRenderState state) {
		super.setupAnim(state);

		this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0F, 2.5F);
		this.rageBaitAnimation.apply(state.rageBaitAnimationState, state.ageInTicks);
	}
}
