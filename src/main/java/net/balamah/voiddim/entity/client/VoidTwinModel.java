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

public class VoidTwinModel extends EntityModel<VoidTwinRenderState> {
	public static final ModelLayerLocation VOID_TWIN =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_twin"),
							 "main");

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

	private final KeyframeAnimation walkAnimation;
	private final KeyframeAnimation attack1Animation;
	private final KeyframeAnimation attack2Animation;
	private final KeyframeAnimation attack3Animation;

	public VoidTwinModel(ModelPart root) {
		super(root);

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

		// WALK_OLD and WALK_FAIL exist in the animation class but are not used here.
		this.walkAnimation = VoidTwinModelAnimation.WALK.bake(root);
		this.attack1Animation = VoidTwinModelAnimation.ATTACK_1.bake(root);
		this.attack2Animation = VoidTwinModelAnimation.ATTACK_2.bake(root);
		this.attack3Animation = VoidTwinModelAnimation.ATTACK_3.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.723F, -12.0504F, 3.7667F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 74).addBox(-2.2551F, 0.5913F, -2.1671F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0F, -2.0F, -2.0F, 0.0F, -0.48F, -0.1309F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.4247F, -2.2164F, -3.1153F, 0.1044F, 0.9538F, 0.3324F));

		PartDefinition cube_r1 = left_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(64, 72).addBox(-2.0064F, 0.0F, -1.419F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1978F, 0.6946F, 0.0308F, 0.0252F, -0.523F, -0.0504F));

		PartDefinition bodies = root.addOrReplaceChild("bodies", CubeListBuilder.create(), PartPose.offset(1.0F, -14.0F, 0.0F));

		PartDefinition shared_leg = bodies.addOrReplaceChild("shared_leg", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -4.0F));

		PartDefinition cube_r2 = shared_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 74).addBox(-1.0F, 0.0F, -3.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, 0.0F, 2.25F, -0.0873F, 0.0F, 0.0F));

		PartDefinition yoke = bodies.addOrReplaceChild("yoke", CubeListBuilder.create().texOffs(80, 72).addBox(0.0F, -10.5F, -1.5F, 3.0F, 15.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.25F, -10.5F, 4.0F, 19.0F, 14.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(66, 14).addBox(-3.25F, -10.5F, 2.0F, 10.0F, 15.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(34, 38).addBox(-7.25F, 2.5F, 4.0F, 18.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -3.0F, -3.0F));

		PartDefinition left_body = bodies.addOrReplaceChild("left_body", CubeListBuilder.create().texOffs(34, 19).addBox(-5.0F, -7.0F, -2.0F, 12.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -7.0F, 0.0F, 0.0F, 0.48F, 0.1309F));

		PartDefinition left_arm = left_body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 69).addBox(-4.0F, 0.0F, -2.75F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -7.0F, 1.0F));

		PartDefinition left_head = left_body.addOrReplaceChild("left_head", CubeListBuilder.create(), PartPose.offset(1.0F, -6.5F, 0.25F));

		PartDefinition cube_r3 = left_head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 39).addBox(-3.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, 0.0F, 0.0F, 0.3999F, 0.2342F, -0.0925F));

		PartDefinition right_body = bodies.addOrReplaceChild("right_body", CubeListBuilder.create().texOffs(0, 19).addBox(-7.0F, -7.0F, -2.0F, 12.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, -7.0F, 0.0F, 0.0F, -0.48F, -0.1309F));

		PartDefinition right_arm = right_body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 69).addBox(0.0F, 0.0F, -2.75F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -7.0F, 1.0F));

		PartDefinition right_head = right_body.addOrReplaceChild("right_head", CubeListBuilder.create(), PartPose.offset(-1.0F, -6.5F, 0.25F));

		PartDefinition cube_r4 = right_head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(32, 44).addBox(-5.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 0.0F, 0.0F, 0.3999F, -0.2342F, 0.0925F));

		PartDefinition decoration = root.addOrReplaceChild("decoration", CubeListBuilder.create().texOffs(64, 58).addBox(-7.0F, -14.0F, 2.0F, 15.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = decoration.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 88).addBox(-3.0F, -1.0F, 1.0F, 6.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.25F, -13.0F, 1.0F, 0.0F, 1.5708F, -0.0436F));

		PartDefinition cube_r6 = decoration.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(80, 0).addBox(-3.0F, -1.0F, 1.0F, 6.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.25F, -13.0F, 1.0F, 0.0F, -1.5708F, 0.0436F));

		PartDefinition cube_r7 = decoration.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 55).addBox(-8.0F, -14.0F, 1.0F, 16.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r8 = decoration.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(48, 0).addBox(-8.0F, -14.0F, 1.0F, 16.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 1.0F, 0.0F, 0.3927F, 0.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	@Override
	public void setupAnim(VoidTwinRenderState state) {
		super.setupAnim(state);

		// Looping walk cycle, driven by how far/fast the entity is moving.
		this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0F, 2.5F);

		this.attack1Animation.apply(state.attack1AnimationState, state.ageInTicks);
		this.attack2Animation.apply(state.attack2AnimationState, state.ageInTicks);
		this.attack3Animation.apply(state.attack3AnimationState, state.ageInTicks);
	}
}
