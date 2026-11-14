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
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class FacelessModel extends EntityModel<FacelessRenderState> {
    public static final ModelLayerLocation FACELESS =
        new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "faceless"),
            "main"
        );

    private final ModelPart root;
    private final ModelPart cape_separation;
    private final ModelPart cape_up;
    private final ModelPart cape;
    private final ModelPart cape_back;
    private final ModelPart cape_left;
    private final ModelPart cape_right;
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart mouth_teeth;
    private final ModelPart arms;
    private final ModelPart right_arm;
    private final ModelPart right_elbow;
    private final ModelPart left_arm;
    private final ModelPart left_elbow;
    private final ModelPart jaw;
    private final ModelPart jaw_teeth;
    private final ModelPart decoration;

    private final KeyframeAnimation walkAnimation;
    private final KeyframeAnimation attackAnimation;
    private final KeyframeAnimation sufferingAnimation;

    public FacelessModel(ModelPart bakedRoot) {
        super(bakedRoot);

        this.root = bakedRoot.getChild("root");
        this.cape_separation = this.root.getChild("cape_separation");
        this.cape_up = this.cape_separation.getChild("cape_up");
        this.cape = this.root.getChild("cape");
        this.cape_back = this.cape.getChild("cape_back");
        this.cape_left = this.cape.getChild("cape_left");
        this.cape_right = this.cape.getChild("cape_right");
        this.head = this.root.getChild("head");
        this.mouth = this.head.getChild("mouth");
        this.mouth_teeth = this.mouth.getChild("mouth_teeth");
        this.arms = this.root.getChild("arms");
        this.right_arm = this.arms.getChild("right_arm");
        this.right_elbow = this.right_arm.getChild("right_elbow");
        this.left_arm = this.arms.getChild("left_arm");
        this.left_elbow = this.left_arm.getChild("left_elbow");
        this.jaw = this.root.getChild("jaw");
        this.jaw_teeth = this.jaw.getChild("jaw_teeth");
        this.decoration = this.root.getChild("decoration");

        this.walkAnimation = FacelessAnimations.WALK.bake(this.root);
        this.attackAnimation = FacelessAnimations.ATTACK.bake(this.root);
        this.sufferingAnimation = FacelessAnimations.SUFFERING.bake(this.root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition model = mesh.getRoot();

        PartDefinition root = model.addOrReplaceChild(
            "root",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        PartDefinition cape_separation = root.addOrReplaceChild(
            "cape_separation",
            CubeListBuilder.create(),
            PartPose.offset(-0.25F, -20.0F, 0.75F)
        );

        cape_separation.addOrReplaceChild(
            "cube_r1",
            CubeListBuilder.create()
                .texOffs(0, 57)
                .addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 5.0F),
            PartPose.offsetAndRotation(3.5F, 0.0F, -3.0F, 0.0F, 0.0F, -0.1745F)
        );

        cape_separation.addOrReplaceChild(
            "cube_r2",
            CubeListBuilder.create()
                .texOffs(58, 41)
                .addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 5.0F),
            PartPose.offsetAndRotation(-3.0F, 0.0F, -3.0F, 0.0F, 0.0F, 0.1745F)
        );

        cape_separation.addOrReplaceChild(
            "cube_r3",
            CubeListBuilder.create()
                .texOffs(42, 45)
                .addBox(-1.0F, -1.9128F, -2.9981F, 1.0F, 2.0F, 7.0F, new CubeDeformation(-0.01F)),
            PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F)
        );

        PartDefinition cape_up = cape_separation.addOrReplaceChild(
            "cape_up",
            CubeListBuilder.create(),
            PartPose.offset(0.25F, -1.0F, -0.75F)
        );

        cape_up.addOrReplaceChild(
            "cube_r4",
            CubeListBuilder.create()
                .texOffs(62, 0)
                .addBox(-3.0F, -1.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)),
            PartPose.offsetAndRotation(7.0F, 2.0F, -1.0F, 0.0F, 0.0F, 0.1309F)
        );

        cape_up.addOrReplaceChild(
            "cube_r5",
            CubeListBuilder.create()
                .texOffs(50, 61)
                .addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)),
            PartPose.offsetAndRotation(-7.0F, 2.0F, -1.0F, 0.0F, 0.0F, -0.1309F)
        );

        cape_up.addOrReplaceChild(
            "cube_r6",
            CubeListBuilder.create()
                .texOffs(38, 17)
                .addBox(-7.0F, 0.0F, -2.0F, 14.0F, 1.0F, 2.0F),
            PartPose.offsetAndRotation(0.0F, 1.0F, 1.5F, -0.2618F, 0.0F, 0.0F)
        );

        PartDefinition cape = root.addOrReplaceChild(
            "cape",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -21.0F, 0.0F)
        );

        PartDefinition cape_back = cape.addOrReplaceChild(
            "cape_back",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 1.0F, 0.0F)
        );

        cape_back.addOrReplaceChild(
            "cube_r7",
            CubeListBuilder.create()
                .texOffs(32, 45)
                .addBox(-2.0F, -1.0F, -0.5F, 4.0F, 21.0F, 1.0F),
            PartPose.offsetAndRotation(-7.0F, 1.0F, 1.75F, 0.1664F, -0.8946F, 0.0198F)
        );

        cape_back.addOrReplaceChild(
            "cube_r8",
            CubeListBuilder.create()
                .texOffs(22, 43)
                .addBox(-2.0F, -1.0F, -0.5F, 4.0F, 21.0F, 1.0F),
            PartPose.offsetAndRotation(-7.0F, 1.0F, 1.75F, 0.2766F, -0.5164F, -0.1004F)
        );

        cape_back.addOrReplaceChild(
            "cube_r9",
            CubeListBuilder.create()
                .texOffs(38, 23)
                .addBox(-2.0F, -1.0F, -0.5F, 4.0F, 21.0F, 1.0F),
            PartPose.offsetAndRotation(7.0F, 1.0F, 1.75F, 0.1664F, 0.8946F, -0.0198F)
        );

        cape_back.addOrReplaceChild(
            "cube_r10",
            CubeListBuilder.create()
                .texOffs(12, 31)
                .addBox(-2.0F, -1.0F, -0.5F, 4.0F, 21.0F, 1.0F),
            PartPose.offsetAndRotation(7.0F, 1.0F, 1.75F, 0.2766F, 0.5164F, 0.1004F)
        );

        cape_back.addOrReplaceChild(
            "cube_r11",
            CubeListBuilder.create()
                .texOffs(0, 9)
                .addBox(-6.0F, -1.0F, -0.5F, 12.0F, 21.0F, 1.0F),
            PartPose.offsetAndRotation(0.0F, 1.0F, 2.25F, 0.2618F, 0.0F, 0.0F)
        );

        PartDefinition cape_left = cape.addOrReplaceChild(
            "cape_left",
            CubeListBuilder.create(),
            PartPose.offset(8.0F, 1.0F, -1.0F)
        );

        cape_left.addOrReplaceChild(
            "cube_r12",
            CubeListBuilder.create()
                .texOffs(0, 31)
                .addBox(-1.0F, -1.0F, -2.0F, 1.0F, 21.0F, 5.0F),
            PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.1745F)
        );

        PartDefinition cape_right = cape.addOrReplaceChild(
            "cape_right",
            CubeListBuilder.create(),
            PartPose.offset(-7.0F, 0.0F, 0.0F)
        );

        cape_right.addOrReplaceChild(
            "cube_r13",
            CubeListBuilder.create()
                .texOffs(26, 17)
                .addBox(0.0F, -1.0F, -2.0F, 1.0F, 21.0F, 5.0F),
            PartPose.offsetAndRotation(-1.0F, 2.0F, -1.0F, 0.0F, 0.0F, 0.1745F)
        );

        PartDefinition head = root.addOrReplaceChild(
            "head",
            CubeListBuilder.create()
                .texOffs(40, 5).addBox(-4.0F, -2.75F, 1.5F, 8.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F))
                .texOffs(58, 5).addBox(-3.0F, -4.65F, 1.5F, 6.0F, 2.0F, 1.0F, new CubeDeformation(-0.001F))
                .texOffs(62, 61).addBox(-2.75F, -5.5F, 1.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
                .texOffs(48, 41).addBox(-2.0F, -6.3F, 1.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
                .texOffs(48, 43).addBox(-2.0F, -7.2F, 1.5F, 4.0F, 1.0F, 1.0F)
                .texOffs(0, 64).addBox(-1.5F, -7.95F, 1.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F)),
            PartPose.offset(0.0F, -22.0F, 0.0F)
        );

        head.addOrReplaceChild(
            "cube_r14",
            CubeListBuilder.create()
                .texOffs(48, 32)
                .addBox(-4.0F, -8.0F, 0.0F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.01F)),
            PartPose.offsetAndRotation(4.0F, 0.0F, -1.5F, 0.0F, 1.5708F, -0.3927F)
        );

        head.addOrReplaceChild(
            "cube_r15",
            CubeListBuilder.create()
                .texOffs(48, 23)
                .addBox(-3.0F, -8.0F, 0.0F, 7.0F, 8.0F, 1.0F, new CubeDeformation(0.01F)),
            PartPose.offsetAndRotation(-4.0F, 0.0F, -1.5F, 0.0F, -1.5708F, 0.3927F)
        );

        head.addOrReplaceChild(
            "cube_r16",
            CubeListBuilder.create()
                .texOffs(62, 8)
                .addBox(-2.0F, -6.0F, 0.0F, 3.0F, 7.0F, 1.0F),
            PartPose.offsetAndRotation(0.5F, -7.5F, 1.5F, 1.5708F, 0.0F, 0.0F)
        );

        PartDefinition mouth = head.addOrReplaceChild(
            "mouth",
            CubeListBuilder.create()
                .texOffs(42, 54)
                .addBox(-1.5F, -8.0F, -4.25F, 3.0F, 2.0F, 5.0F, new CubeDeformation(-0.1F)),
            PartPose.ZERO
        );

        mouth.addOrReplaceChild(
            "mouth_teeth",
            CubeListBuilder.create()
                .texOffs(64, 32).addBox(-0.75F, -6.5F, -4.0F, 1.0F, 2.0F, 1.0F)
                .texOffs(64, 35).addBox(-1.75F, -6.5F, -3.5F, 1.0F, 2.0F, 1.0F)
                .texOffs(64, 38).addBox(0.25F, -6.5F, -3.5F, 1.0F, 2.0F, 1.0F),
            PartPose.offset(0.25F, 0.0F, 0.0F)
        );

        PartDefinition arms = root.addOrReplaceChild(
            "arms",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -19.0F, -0.75F)
        );

        PartDefinition right_arm = arms.addOrReplaceChild(
            "right_arm",
            CubeListBuilder.create()
                .texOffs(58, 53)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F),
            PartPose.offset(-5.0F, 0.0F, 0.0F)
        );

        right_arm.addOrReplaceChild(
            "right_elbow",
            CubeListBuilder.create()
                .texOffs(12, 53)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F),
            PartPose.offset(0.0F, 6.0F, 0.0F)
        );

        PartDefinition left_arm = arms.addOrReplaceChild(
            "left_arm",
            CubeListBuilder.create()
                .texOffs(12, 61)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F),
            PartPose.offset(5.0F, 0.0F, 0.0F)
        );

        left_arm.addOrReplaceChild(
            "left_elbow",
            CubeListBuilder.create()
                .texOffs(42, 61)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F),
            PartPose.offset(0.0F, 6.0F, 0.0F)
        );

        PartDefinition jaw = root.addOrReplaceChild(
            "jaw",
            CubeListBuilder.create()
                .texOffs(40, 0)
                .addBox(-4.0F, -1.0F, -2.0F, 8.0F, 2.0F, 3.0F),
            PartPose.offset(0.0F, -5.0F, -1.0F)
        );

        jaw.addOrReplaceChild(
            "jaw_teeth",
            CubeListBuilder.create()
                .texOffs(22, 41).addBox(2.0F, -3.0F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
                .texOffs(32, 43).addBox(-5.0F, -3.0F, 0.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
                .texOffs(20, 65).addBox(1.0F, -3.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.001F))
                .texOffs(8, 64).addBox(0.0F, -4.75F, -0.5F, 1.0F, 3.0F, 1.0F)
                .texOffs(22, 31).addBox(-1.0F, -5.25F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.01F))
                .texOffs(64, 29).addBox(-4.0F, -3.5F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(-0.001F))
                .texOffs(64, 25).addBox(-3.0F, -4.75F, -0.5F, 1.0F, 3.0F, 1.0F)
                .texOffs(22, 36).addBox(-2.0F, -5.25F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(-0.01F)),
            PartPose.offset(1.0F, 1.0F, -1.0F)
        );

        root.addOrReplaceChild(
            "decoration",
            CubeListBuilder.create()
                .texOffs(0, 0).addBox(-10.0F, -8.0F, 1.0F, 20.0F, 9.0F, 0.0F)
                .texOffs(26, 9).addBox(-9.0F, -16.0F, 1.0F, 18.0F, 8.0F, 0.0F)
                .texOffs(58, 48).addBox(-3.25F, -25.5F, 0.25F, 7.0F, 5.0F, 0.0F)
                .texOffs(62, 63).addBox(-2.5F, -27.5F, 0.25F, 5.0F, 2.0F, 0.0F)
                .texOffs(64, 23).addBox(-2.0F, -29.5F, 0.25F, 4.0F, 2.0F, 0.0F)
                .texOffs(38, 20).addBox(-8.0F, -19.0F, 1.0F, 16.0F, 3.0F, 0.0F),
            PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(FacelessRenderState state) {
        super.setupAnim(state);

		this.walkAnimation.apply(state.walkAnimationState, state.ageInTicks);
		this.attackAnimation.apply(state.attackAnimationState, state.ageInTicks);
		this.sufferingAnimation.apply(state.sufferingAnimationState, state.ageInTicks);
    }
}
