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
import net.minecraft.util.Mth;
import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.entity.client.base.BasicAnimations;
import net.balamah.voiddim.VoidDimension;
	
public class CorruptedStalkerModel extends EntityModel<BasicRenderState> {
    public static final ModelLayerLocation CORRUPTED_STALKER =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_stalker"),
							 "main");

	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart body;
	private final ModelPart legs;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	private final KeyframeAnimation walkingAnimation;

	public CorruptedStalkerModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.arms = this.root.getChild("arms");
		this.left_arm = this.arms.getChild("left_arm");
		this.right_arm = this.arms.getChild("right_arm");
		this.body = this.root.getChild("body");
		this.legs = this.root.getChild("legs");
		this.left_leg = this.legs.getChild("left_leg");
		this.right_leg = this.legs.getChild("right_leg");

		this.walkingAnimation = BasicAnimations.WALK.bake(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head",
			CubeListBuilder.create().texOffs(0, 22).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)),
			PartPose.offset(0.0F, -38.0F, 0.0F));

		PartDefinition arms = root.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = arms.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 28.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -38.0F, -4.0F));

		PartDefinition right_arm = arms.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 30).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 28.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -38.0F, 4.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -38.0F, -4.0F, 4.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 1.0F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 24.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}

    @Override
    public void setupAnim(BasicRenderState state) {
        super.setupAnim(state);

		this.setHeadAngles(state.yRot, state.xRot);

		this.walkingAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);
    }

	protected void setHeadAngles(float headYaw, float headPitch) {
		// convert to radians
		float yawRad   = headYaw * ((float)Math.PI / 180F);
		float pitchRad = headPitch * ((float)Math.PI / 180F);

		// clamp pitch
		pitchRad = Mth.clamp(pitchRad,
									-25F * ((float)Math.PI / 180F),
									45F * ((float)Math.PI / 180F));

		// apply correction so head faces forward instead of sideways
		this.head.yRot   = yawRad - (float)Math.PI / 2F;  // subtract 90°
		this.head.xRot = pitchRad;
	}
}
