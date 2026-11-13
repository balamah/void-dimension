package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

import net.minecraft.resources.Identifier;

public class HoveringInfernoModel extends EntityModel<HoveringInfernoRenderState> {
    public static final ModelLayerLocation HOVERING_INFERNO =
        new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "hovering_inferno"),
            "main"
        );

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart crown;
    private final ModelPart shields;
    private final ModelPart shield_1;
    private final ModelPart shield_2;
    private final ModelPart shield_3;
    private final ModelPart shield_4;

    private final KeyframeAnimation idleAnimation;
    private final KeyframeAnimation phase2TransitionAnimation;
    private final KeyframeAnimation phase3TransitionAnimation;
    private final KeyframeAnimation phase4TransitionAnimation;
    private final KeyframeAnimation phase5TransitionAnimation;
    private final KeyframeAnimation stunAnimation;
    private final KeyframeAnimation recoverAnimation;

    public HoveringInfernoModel(ModelPart root) {
        super(root);

        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
        this.head = this.body.getChild("head");
        this.crown = this.head.getChild("crown");
        this.shields = this.root.getChild("shields");
        this.shield_1 = this.shields.getChild("shield_1");
        this.shield_2 = this.shields.getChild("shield_2");
        this.shield_3 = this.shields.getChild("shield_3");
        this.shield_4 = this.shields.getChild("shield_4");

        this.idleAnimation = HoveringInfernoAnimations.IDLE.bake(root);
        this.phase2TransitionAnimation = HoveringInfernoAnimations.PHASE_2_TRANSITION.bake(root);
        this.phase3TransitionAnimation = HoveringInfernoAnimations.PHASE_3_TRANSITION.bake(root);
        this.phase4TransitionAnimation = HoveringInfernoAnimations.PHASE_4_TRANSITION.bake(root);
        this.phase5TransitionAnimation = HoveringInfernoAnimations.PHASE_5_TRANSITION.bake(root);
        this.stunAnimation = HoveringInfernoAnimations.STUN.bake(root);
        this.recoverAnimation = HoveringInfernoAnimations.RECOVER.bake(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition root = modelPartData.addOrReplaceChild(
            "root",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        PartDefinition body = root.addOrReplaceChild(
            "body",
            CubeListBuilder.create()
                .texOffs(44, 36)
                .addBox(-1.5F, -23.0F, -1.5F, 3.0F, 21.0F, 3.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition head = body.addOrReplaceChild(
            "head",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-3.5F, -8.0F, -4.25F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
            PartPose.offset(-0.5F, -23.0F, 0.25F)
        );

        PartDefinition crown = head.addOrReplaceChild(
            "crown",
            CubeListBuilder.create()
                .texOffs(32, 0)
                .addBox(-3.5F, -9.0F, -4.5F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(48, 0)
                .addBox(-3.5F, -9.0F, 4.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(52, 27)
                .addBox(3.75F, -9.0F, 4.0F, 1.0F, 9.0F, 0.0F, new CubeDeformation(-0.001F))
                .texOffs(54, 27)
                .addBox(-3.75F, -9.0F, 4.0F, 1.0F, 9.0F, 0.0F, new CubeDeformation(-0.001F))
                .texOffs(2, 56)
                .addBox(-3.75F, -9.0F, -4.5F, 1.0F, 9.0F, 0.0F, new CubeDeformation(0.001F))
                .texOffs(0, 56)
                .addBox(3.75F, -9.0F, -4.5F, 1.0F, 9.0F, 0.0F, new CubeDeformation(0.001F)),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        crown.addOrReplaceChild(
            "cube_r1",
            CubeListBuilder.create()
                .texOffs(50, 27)
                .addBox(-4.0F, -9.0F, 1.0F, 1.0F, 9.0F, 0.0F, new CubeDeformation(-0.001F)),
            PartPose.offsetAndRotation(3.75F, 0.0F, -7.5F, 0.0F, 1.5708F, 0.0F)
        );

        crown.addOrReplaceChild(
            "cube_r2",
            CubeListBuilder.create()
                .texOffs(48, 18)
                .addBox(-4.0F, -9.0F, 1.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.001F)),
            PartPose.offsetAndRotation(3.75F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F)
        );

        crown.addOrReplaceChild(
            "cube_r3",
            CubeListBuilder.create()
                .texOffs(48, 27)
                .addBox(3.0F, -9.0F, 1.0F, 1.0F, 9.0F, 0.0F, new CubeDeformation(-0.001F)),
            PartPose.offsetAndRotation(-2.75F, 0.0F, -7.5F, 0.0F, -1.5708F, 0.0F)
        );

        crown.addOrReplaceChild(
            "cube_r4",
            CubeListBuilder.create()
                .texOffs(48, 9)
                .addBox(-4.0F, -9.0F, 1.0F, 8.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-2.75F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F)
        );

        PartDefinition shields = root.addOrReplaceChild(
            "shields",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -24.0F, 0.0F)
        );

        PartDefinition shield_1 = shields.addOrReplaceChild(
            "shield_1",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 21.0F, -11.0F)
        );

        shield_1.addOrReplaceChild(
            "cube_r5",
            CubeListBuilder.create()
                .texOffs(24, 16)
                .addBox(-5.0F, 0.0F, -1.0F, 11.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-0.5F, -19.0F, 4.0F, -0.1745F, 0.0F, 0.0F)
        );

        PartDefinition shield_2 = shields.addOrReplaceChild(
            "shield_2",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 20.5F, 10.75F)
        );

        shield_2.addOrReplaceChild(
            "cube_r6",
            CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-5.0F, 0.0F, 0.0F, 11.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-0.5F, -18.5F, -3.75F, 0.1745F, 0.0F, 0.0F)
        );

        PartDefinition shield_3 = shields.addOrReplaceChild(
            "shield_3",
            CubeListBuilder.create(),
            PartPose.offset(-10.0F, 20.5F, 0.0F)
        );

        shield_3.addOrReplaceChild(
            "cube_r7",
            CubeListBuilder.create()
                .texOffs(0, 36)
                .addBox(-5.0F, 0.0F, 0.0F, 10.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(3.0F, -18.75F, 0.0F, 0.0F, -1.5708F, 0.1745F)
        );

        PartDefinition shield_4 = shields.addOrReplaceChild(
            "shield_4",
            CubeListBuilder.create(),
            PartPose.offset(10.0F, 19.5F, 0.0F)
        );

        shield_4.addOrReplaceChild(
            "cube_r8",
            CubeListBuilder.create()
                .texOffs(22, 36)
                .addBox(-5.0F, 0.0F, 0.0F, 10.0F, 19.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-3.0F, -17.75F, 0.0F, 0.0F, 1.5708F, -0.1745F)
        );

        return LayerDefinition.create(modelData, 100, 100);
    }

    @Override
    public void setupAnim(HoveringInfernoRenderState state) {
        super.setupAnim(state);

        this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
        this.phase2TransitionAnimation.apply(state.phase2TransitionAnimationState, state.ageInTicks);
        this.phase3TransitionAnimation.apply(state.phase3TransitionAnimationState, state.ageInTicks);
        this.phase4TransitionAnimation.apply(state.phase4TransitionAnimationState, state.ageInTicks);
        this.phase5TransitionAnimation.apply(state.phase5TransitionAnimationState, state.ageInTicks);
        this.stunAnimation.apply(state.stunAnimationState, state.ageInTicks);
        this.recoverAnimation.apply(state.recoverAnimationState, state.ageInTicks);
    }
}
