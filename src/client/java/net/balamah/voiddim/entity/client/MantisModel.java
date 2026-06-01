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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.balamah.voiddim.VoidDimension;

public class MantisModel extends EntityModel<BasicRenderState> {
    public static final ModelLayerLocation MANTIS =
		new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "mantis"), "main");

	private final KeyframeAnimation walkingAnimation;
	private final KeyframeAnimation idleAnimation;

    private final ModelPart root;
    private final ModelPart mantis;
    private final ModelPart head;

    public MantisModel(ModelPart root) {
        super(root);
        this.root = root.getChild("root");
        this.mantis = this.root.getChild("mantis");
		this.head = this.mantis.getChild("head");

		this.walkingAnimation = MantisAnimations.ANIM_MANTIS_WALK.bake(root);
		this.idleAnimation = MantisAnimations.ANIM_MANTIS_IDLE.bake(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition mantis = root.addOrReplaceChild("mantis", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -3.0F, -28.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition body = mantis.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.0F, 8.75F, 5.0F, 2.0F, 25.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.5F, -2.0F, -51.25F, 7.0F, 4.0F, 60.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 26.25F));

        PartDefinition head = mantis.addOrReplaceChild("head", CubeListBuilder.create().texOffs(31, 27).addBox(-3.5F, -1.0F, 0.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(35, 20).addBox(-2.5F, 3.0F, 0.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -13.0F, 60.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition antenna1 = head.addOrReplaceChild("antenna1", CubeListBuilder.create().texOffs(56, 71).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -1.0F, 3.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition antenna2 = head.addOrReplaceChild("antenna2", CubeListBuilder.create().texOffs(56, 71).addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -1.0F, 3.0F, 0.4363F, 0.0F, 0.0F));

        PartDefinition mouth1 = head.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(14, 0).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 5.0F, 1.0F));

        PartDefinition mouth2 = head.addOrReplaceChild("mouth2", CubeListBuilder.create().texOffs(6, 0).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 5.0F, 1.0F));

        PartDefinition wing1 = mantis.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(56, 0).addBox(-3.5F, 0.0F, -60.0F, 7.0F, 0.0F, 60.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.04F, 35.0F));

        PartDefinition wing2 = mantis.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(56, 0).mirror().addBox(-3.5F, 0.0F, -60.0F, 7.0F, 0.0F, 60.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -14.03F, 35.0F));

        PartDefinition wing3 = mantis.addOrReplaceChild("wing3", CubeListBuilder.create().texOffs(28, 0).addBox(-3.5F, 0.0F, -60.0F, 7.0F, 0.0F, 60.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.02F, 35.0F));

        PartDefinition wing4 = mantis.addOrReplaceChild("wing4", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(-3.5F, 0.0F, -60.0F, 7.0F, 0.0F, 60.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -14.01F, 35.0F));

        PartDefinition arm1 = mantis.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -11.0F, 51.0F));

        PartDefinition arm1seg2 = arm1.addOrReplaceChild("arm1seg2", CubeListBuilder.create().texOffs(0, 64).addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 25.0F, new CubeDeformation(0.0F))
                .texOffs(31, 62).addBox(0.0F, 1.5F, -2.0F, 0.0F, 6.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.5F, 0.0F));

        PartDefinition arm1seg3 = arm1seg2.addOrReplaceChild("arm1seg3", CubeListBuilder.create().texOffs(10, 27).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 20.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(0.0F, 0.0F, -3.5F, 0.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 21.5F));

        PartDefinition arm2 = mantis.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(8, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 20.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, -11.0F, 51.0F));

        PartDefinition arm2seg2 = arm2.addOrReplaceChild("arm2seg2", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(31, 62).addBox(0.0F, 1.5F, -2.0F, 0.0F, 6.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.5F, 0.0F));

        PartDefinition arm2seg3 = arm2seg2.addOrReplaceChild("arm2seg3", CubeListBuilder.create().texOffs(10, 27).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 25).addBox(0.0F, 0.0F, -3.5F, 0.0F, 20.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 21.5F));

        PartDefinition frontLeg1 = mantis.addOrReplaceChild("frontLeg1", CubeListBuilder.create().texOffs(39, 34).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -10.0F, 27.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition frontLeg1seg2 = frontLeg1.addOrReplaceChild("frontLeg1seg2", CubeListBuilder.create().texOffs(54, 94).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(36, 76).addBox(0.0F, 0.5F, 0.0F, 0.0F, 5.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.5F, 0.0F, 0.3927F, -0.4363F, 0.0F));

        PartDefinition frontLeg1seg3 = frontLeg1seg2.addOrReplaceChild("frontLeg1seg3", CubeListBuilder.create().texOffs(18, 27).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 17.5F, 0.2618F, 0.0F, 0.0F));

        PartDefinition frontLeg2 = mantis.addOrReplaceChild("frontLeg2", CubeListBuilder.create().texOffs(39, 34).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -10.0F, 27.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition frontLeg2seg2 = frontLeg2.addOrReplaceChild("frontLeg2seg2", CubeListBuilder.create().texOffs(54, 94).mirror().addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(36, 76).addBox(0.0F, 0.5F, 0.0F, 0.0F, 5.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.5F, 0.0F, 0.3927F, 0.4363F, 0.0F));

        PartDefinition frontLeg2seg3 = frontLeg2seg2.addOrReplaceChild("frontLeg2seg3", CubeListBuilder.create().texOffs(18, 27).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.5F, 17.5F, 0.2618F, 0.0F, 0.0F));

        PartDefinition backLeg1 = mantis.addOrReplaceChild("backLeg1", CubeListBuilder.create().texOffs(35, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -10.0F, 20.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition backLeg1seg2 = backLeg1.addOrReplaceChild("backLeg1seg2", CubeListBuilder.create().texOffs(83, 65).addBox(-0.5F, -0.5F, -25.0F, 1.0F, 1.0F, 25.0F, new CubeDeformation(0.0F))
                .texOffs(31, 45).addBox(0.0F, 0.5F, -25.0F, 0.0F, 6.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.5F, 0.0F, 0.0F, 0.4363F, 0.5236F));

        PartDefinition backLeg1seg3 = backLeg1seg2.addOrReplaceChild("backLeg1seg3", CubeListBuilder.create().texOffs(4, 97).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 35.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -24.5F));

        PartDefinition backLeg2 = mantis.addOrReplaceChild("backLeg2", CubeListBuilder.create().texOffs(35, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -10.0F, 20.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition backLeg2seg2 = backLeg2.addOrReplaceChild("backLeg2seg2", CubeListBuilder.create().texOffs(83, 65).mirror().addBox(-0.5F, -0.5F, -25.0F, 1.0F, 1.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(31, 45).addBox(0.0F, 0.5F, -25.0F, 0.0F, 6.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.5F, 0.0F, 0.0F, -0.4363F, -0.5236F));

        PartDefinition backLeg2seg3 = backLeg2seg2.addOrReplaceChild("backLeg2seg3", CubeListBuilder.create().texOffs(4, 97).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 35.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.5F, -24.5F));
        return LayerDefinition.create(modelData, 256, 256);
    }

    @Override
    public void setupAnim(BasicRenderState state) {
        super.setupAnim(state);

		this.setHeadAngles(state.yRot, state.xRot);

		this.walkingAnimation.applyWalk(
			state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f
		);

		this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
        headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

        this.head.yRot = headYaw * 0.017453292F;
        this.head.xRot = headPitch * 0.017453292F;
    }
	
}
