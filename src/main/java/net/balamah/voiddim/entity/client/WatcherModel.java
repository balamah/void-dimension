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

public class WatcherModel extends EntityModel<WatcherRenderState> {
	public static final ModelLayerLocation WATCHER =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "watcher"),
							 "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart right_arm;
	private final ModelPart right_elbow;
	private final ModelPart right_hand;
	private final ModelPart left_arm;
	private final ModelPart left_idk;
	private final ModelPart legs;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation rageBaitAnimation;

	public WatcherModel(ModelPart root) {
		super(root);

		// The original export looked every bone up on the top-level root, which fails at load time.
		// Each bone is now fetched from its real parent.
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.head = this.body.getChild("head");
		this.arms = this.body.getChild("arms");
		this.right_arm = this.arms.getChild("right_arm");
		this.right_elbow = this.right_arm.getChild("right_elbow");
		this.right_hand = this.right_elbow.getChild("right_hand");
		this.left_arm = this.arms.getChild("left_arm");
		this.left_idk = this.left_arm.getChild("left_idk");
		this.legs = this.root.getChild("legs");
		this.left_leg = this.legs.getChild("left_leg");
		this.right_leg = this.legs.getChild("right_leg");

		this.walkAnimation = WatcherAnimations.WALK.bake(root);
		this.rageBaitAnimation = WatcherAnimations.RAGEBAIT.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -14.0F, -4.0F, 4.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(8, 36).addBox(-3.5F, -6.75F, -5.0F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.001F))
		.texOffs(36, 14).addBox(-3.5F, -6.75F, 3.0F, 7.0F, 4.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -13.75F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 0).addBox(-4.0F, -3.0F, -5.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(-1.5F, -6.75F, 0.75F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 22).addBox(-4.0F, -3.0F, -5.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(-0.0001F)), PartPose.offsetAndRotation(-1.5F, -3.25F, 0.75F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 20).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -38.0F, -3.75F, -0.3897F, 0.05F, 0.121F));

		PartDefinition right_elbow = right_arm.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(26, 40).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.25F, -1.0F, 0.3778F, 0.1298F, -0.228F));

		PartDefinition right_hand = right_elbow.addOrReplaceChild("right_hand", CubeListBuilder.create(), PartPose.offset(0.0F, 7.25F, 0.0F));

		PartDefinition cube_r3 = right_hand.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(40, 43).addBox(-1.0F, 4.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 3.75F, -3.25F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_hand.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 32).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 42).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offsetAndRotation(0.0F, -38.0F, 4.0F, 0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r5 = left_arm.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(8, 42).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 1.0F, -0.829F, 0.0F, 0.0F));

		PartDefinition left_idk = left_arm.addOrReplaceChild("left_idk", CubeListBuilder.create(), PartPose.offset(0.0F, 5.25F, 1.0F));

		PartDefinition cube_r6 = left_idk.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(34, 43).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.346F, 0.151F, 0.0879F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 1.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(28, 14).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(WatcherRenderState state) {
		super.setupAnim(state);

		this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0F, 2.5F);
		this.rageBaitAnimation.apply(state.rageBaitAnimationState, state.ageInTicks);
	}
}
