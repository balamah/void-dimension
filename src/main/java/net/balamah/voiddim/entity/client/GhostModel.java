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
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class GhostModel extends EntityModel<GhostRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "ghost"),
            "main"
        );

    private final ModelPart root;
    private final ModelPart upper_body;
    private final ModelPart miasma;
    private final ModelPart cloak;
    private final ModelPart front;
    private final ModelPart front_lower;
    private final ModelPart back;
    private final ModelPart back_lower;
    private final ModelPart head;
    private final ModelPart head_covering;
    private final ModelPart mask;

    private final KeyframeAnimation walkAnimation;

    public GhostModel(ModelPart bakedRoot) {
        super(bakedRoot);

        this.root = bakedRoot.getChild("root");
        this.upper_body = this.root.getChild("upper_body");
        this.miasma = this.upper_body.getChild("miasma");
        this.cloak = this.upper_body.getChild("cloak");
        this.front = this.cloak.getChild("front");
        this.front_lower = this.front.getChild("front_lower");
        this.back = this.cloak.getChild("back");
        this.back_lower = this.back.getChild("back_lower");
        this.head = this.upper_body.getChild("head");
        this.head_covering = this.head.getChild("head_covering");
        this.mask = this.head.getChild("mask");

        this.walkAnimation = GhostAnimations.WALK.bake(this.root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition model = mesh.getRoot();

        PartDefinition root = model.addOrReplaceChild(
            "root",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 25.0F, 0.0F)
        );

        PartDefinition upper_body = root.addOrReplaceChild(
            "upper_body",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -10.0F, 1.0F)
        );

        PartDefinition miasma = upper_body.addOrReplaceChild(
            "miasma",
            CubeListBuilder.create(),
            PartPose.ZERO
        );

        miasma.addOrReplaceChild(
            "cube_r1",
            CubeListBuilder.create().texOffs(24, 0)
                .addBox(0.0F, -1.0F, -2.0F, 0.0F, 17.0F, 4.0F),
            PartPose.offsetAndRotation(0.0F, -8.0F, -1.0F, 0.0F, -2.3998F, 0.0F)
        );

        miasma.addOrReplaceChild(
            "cube_r2",
            CubeListBuilder.create().texOffs(22, 21)
                .addBox(0.0F, -1.0F, -2.0F, 0.0F, 17.0F, 4.0F),
            PartPose.offsetAndRotation(0.0F, -8.0F, -1.0F, 0.0F, -0.8727F, 0.0F)
        );

        miasma.addOrReplaceChild(
            "cube_r3",
            CubeListBuilder.create().texOffs(8, 32)
                .addBox(0.0F, 0.0F, -2.0F, 0.0F, 16.0F, 3.0F),
            PartPose.offsetAndRotation(2.0F, -8.0F, -0.5F, 0.0F, 0.0F, -0.0873F)
        );

        miasma.addOrReplaceChild(
            "cube_r4",
            CubeListBuilder.create().texOffs(32, 0)
                .addBox(0.0F, 0.0F, -1.0F, 0.0F, 16.0F, 3.0F),
            PartPose.offsetAndRotation(-2.0F, -8.0F, -1.5F, 0.0F, 0.0F, 0.0873F)
        );

        miasma.addOrReplaceChild(
            "cube_r5",
            CubeListBuilder.create().texOffs(0, 32)
                .addBox(0.0F, 0.0F, -2.0F, 0.0F, 16.0F, 4.0F),
            PartPose.offsetAndRotation(0.0F, -8.0F, 0.75F, 1.5708F, 1.4835F, 1.5708F)
        );

        miasma.addOrReplaceChild(
            "cube_r6",
            CubeListBuilder.create().texOffs(30, 21)
                .addBox(0.0F, 0.0F, -2.0F, 0.0F, 16.0F, 4.0F),
            PartPose.offsetAndRotation(0.0F, -8.0F, -2.75F, -1.5708F, 1.4835F, -1.5708F)
        );

        PartDefinition cloak = upper_body.addOrReplaceChild(
            "cloak",
            CubeListBuilder.create(),
            PartPose.ZERO
        );

        cloak.addOrReplaceChild(
            "cube_r7",
            CubeListBuilder.create().texOffs(12, 0)
                .addBox(0.0F, 0.0F, -3.0F, 0.0F, 15.0F, 6.0F),
            PartPose.offsetAndRotation(-3.0F, -8.0F, -1.0F, 0.0F, 0.0F, 0.0873F)
        );

        cloak.addOrReplaceChild(
            "cube_r8",
            CubeListBuilder.create().texOffs(0, 0)
                .addBox(0.0F, 0.0F, -3.0F, 0.0F, 15.0F, 6.0F),
            PartPose.offsetAndRotation(3.0F, -8.0F, -1.0F, 0.0F, 0.0F, -0.0873F)
        );

        PartDefinition front = cloak.addOrReplaceChild(
            "front",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -8.0F, -4.0F)
        );

        front.addOrReplaceChild(
            "cube_r9",
            CubeListBuilder.create().texOffs(38, 7)
                .addBox(-3.0F, 0.0F, 0.0F, 6.0F, 8.0F, 0.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F)
        );

        PartDefinition front_lower = front.addOrReplaceChild(
            "front_lower",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 8.0F, -1.0F)
        );

        front_lower.addOrReplaceChild(
            "cube_r10",
            CubeListBuilder.create().texOffs(38, 23)
                .addBox(-3.0F, -0.0621F, -0.0528F, 6.0F, 7.0F, 0.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F)
        );

        PartDefinition back = cloak.addOrReplaceChild(
            "back",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -8.0F, 2.0F)
        );

        back.addOrReplaceChild(
            "cube_r11",
            CubeListBuilder.create().texOffs(38, 15)
                .addBox(-3.0F, 0.0F, 0.0F, 6.0F, 8.0F, 0.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F)
        );

        PartDefinition back_lower = back.addOrReplaceChild(
            "back_lower",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 8.0F, 1.0F)
        );

        back_lower.addOrReplaceChild(
            "cube_r12",
            CubeListBuilder.create().texOffs(38, 30)
                .addBox(-3.0F, -0.0621F, 0.0528F, 6.0F, 7.0F, 0.0F),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2182F, 0.0F, 0.0F)
        );

        PartDefinition head = upper_body.addOrReplaceChild(
            "head",
            CubeListBuilder.create().texOffs(0, 21)
                .addBox(-3.0F, -6.0F, -2.0F, 6.0F, 6.0F, 5.0F),
            PartPose.offset(0.0F, -8.0F, -1.0F)
        );

        head.addOrReplaceChild(
            "head_covering",
            CubeListBuilder.create(),
            PartPose.ZERO
        );

        head.addOrReplaceChild(
            "mask",
            CubeListBuilder.create().texOffs(38, 0)
                .addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 1.0F),
            PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(GhostRenderState state) {
        super.setupAnim(state);

        this.head.xRot = Mth.DEG_TO_RAD * 17.5F;

		this.walkAnimation.apply(state.walkAnimationState, state.ageInTicks);
    }
}
