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

public class GloomShepherdModel extends EntityModel<GloomShepherdRenderState> {
    public static final ModelLayerLocation LAYER_LOCATION =
        new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "gloom_shepherd"),
            "main"
        );

    private final ModelPart root;

    private final KeyframeAnimation walkAnimation;
    private final KeyframeAnimation summonAnimation;
    private final KeyframeAnimation attackAnimation;

    public GloomShepherdModel(ModelPart root) {
        super(root);

        this.root = root.getChild("root");

        this.walkAnimation = GloomShepherdAnimations.WALK.bake(this.root);
        this.summonAnimation = GloomShepherdAnimations.SUMMON.bake(this.root);
        this.attackAnimation = GloomShepherdAnimations.ATTACK.bake(this.root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition modelPartData = mesh.getRoot();

        PartDefinition root = modelPartData.addOrReplaceChild(
            "root",
            CubeListBuilder.create(),
            PartPose.offset(0.5F, 24.0F, 0.0F)
        );

        PartDefinition legs = root.addOrReplaceChild(
            "legs",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -13.0F, 0.0F)
        );

        PartDefinition skirt = legs.addOrReplaceChild(
            "skirt",
            CubeListBuilder.create(),
            PartPose.offsetAndRotation(
                0.0021F, 12.0229F, 4.9447F,
                0.3957F, -0.1209F, -0.0503F
            )
        );

        skirt.addOrReplaceChild(
            "cube_r1",
            CubeListBuilder.create()
                .texOffs(14, 27)
                .addBox(-1.0F, -2.0F, -1.0F, 0.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                -2.8951F, -11.9896F, 1.1329F,
                -0.2618F, 0.1309F, 0.0F
            )
        );

        skirt.addOrReplaceChild(
            "cube_r2",
            CubeListBuilder.create()
                .texOffs(38, 36)
                .addBox(-2.7038F, -0.3912F, 0.1714F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                -3.75F, -13.75F, 1.5F,
                -0.4312F, -1.3682F, 0.5931F
            )
        );

        skirt.addOrReplaceChild(
            "cube_r3",
            CubeListBuilder.create()
                .texOffs(36, 25)
                .addBox(-4.2962F, -1.3912F, 0.1714F, 7.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                0.25F, -13.25F, 1.0F,
                0.3602F, 0.0885F, 0.017F
            )
        );

        skirt.addOrReplaceChild(
            "cube_r4",
            CubeListBuilder.create()
                .texOffs(0, 39)
                .addBox(-0.9839F, -0.1512F, 0.2373F, 1.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                0.0F, -13.0F, 1.0F,
                -0.2943F, 0.0885F, 0.017F
            )
        );

        skirt.addOrReplaceChild(
            "cube_r5",
            CubeListBuilder.create()
                .texOffs(16, 27)
                .addBox(-0.9839F, -0.1512F, -0.2374F, 1.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                0.0F, -14.0F, -0.75F,
                -0.3948F, 0.1455F, -0.0018F
            )
        );

        PartDefinition pieces = skirt.addOrReplaceChild(
            "pieces",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightSkirtPiece2 = pieces.addOrReplaceChild(
            "right_skirt_piece2",
            CubeListBuilder.create(),
            PartPose.offset(-3.3951F, -11.9896F, -1.1171F)
        );

        rightSkirtPiece2.addOrReplaceChild(
            "cube_r6",
            CubeListBuilder.create()
                .texOffs(0, 27)
                .addBox(-0.8695F, -1.7434F, -1.9577F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                0.0F, 0.0F, 1.0F,
                -0.2595F, 0.0044F, 0.0338F
            )
        );

        PartDefinition leftSkirtPiece = pieces.addOrReplaceChild(
            "left_skirt_piece",
            CubeListBuilder.create(),
            PartPose.offset(3.8549F, -11.9896F, -1.3671F)
        );

        leftSkirtPiece.addOrReplaceChild(
            "cube_r7",
            CubeListBuilder.create()
                .texOffs(24, 0)
                .addBox(-1.0F, -2.0F, -1.0F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                0.0F, 0.0F, 0.0F,
                -0.3927F, 0.1309F, 0.0F
            )
        );

        PartDefinition leftSkirtPieces = pieces.addOrReplaceChild(
            "left_skirt_pieces",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -13.0F, 1.0F)
        );

        PartDefinition leftBackwardSkirtPiece = leftSkirtPieces.addOrReplaceChild(
            "left_backward_skirt_piece",
            CubeListBuilder.create(),
            PartPose.offset(2.0F, 0.0F, 0.0F)
        );

        leftBackwardSkirtPiece.addOrReplaceChild(
            "cube_r8",
            CubeListBuilder.create()
                .texOffs(36, 37)
                .addBox(0.0161F, -0.1512F, 0.2373F, 3.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                -2.0F, 0.0F, 0.0F,
                -0.2943F, 0.0885F, 0.017F
            )
        );

        PartDefinition leftForwardSkirtPiece = leftSkirtPieces.addOrReplaceChild(
            "left_forward_skirt_piece",
            CubeListBuilder.create(),
            PartPose.offset(1.0F, -1.0F, -1.75F)
        );

        leftForwardSkirtPiece.addOrReplaceChild(
            "cube_r9",
            CubeListBuilder.create()
                .texOffs(30, 37)
                .addBox(0.0161F, -0.1512F, -0.2374F, 3.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                -1.0F, 0.0F, 0.0F,
                -0.3948F, 0.1455F, -0.0018F
            )
        );

        PartDefinition rightSkirtPieces = pieces.addOrReplaceChild(
            "right_skirt_pieces",
            CubeListBuilder.create(),
            PartPose.offset(-2.0F, -13.0F, 1.0F)
        );

        PartDefinition rightForwardSkirtPieces = rightSkirtPieces.addOrReplaceChild(
            "right_forward_skirt_pieces",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -0.5F, -1.25F)
        );

        rightForwardSkirtPieces.addOrReplaceChild(
            "cube_r10",
            CubeListBuilder.create()
                .texOffs(38, 0)
                .addBox(-3.9839F, -0.1512F, -0.2374F, 3.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                2.0F, -0.5F, -0.5F,
                -0.3948F, 0.1455F, -0.0018F
            )
        );

        PartDefinition rightBackwardSkirtPiece = rightSkirtPieces.addOrReplaceChild(
            "right_backward_skirt_piece",
            CubeListBuilder.create(),
            PartPose.offset(-1.0F, 0.0F, 0.0F)
        );

        rightBackwardSkirtPiece.addOrReplaceChild(
            "cube_r11",
            CubeListBuilder.create()
                .texOffs(38, 27)
                .addBox(-3.9839F, -0.1512F, 0.2373F, 3.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                3.0F, 0.0F, 0.0F,
                -0.2943F, 0.0885F, 0.017F
            )
        );

        PartDefinition body = legs.addOrReplaceChild(
            "body",
            CubeListBuilder.create()
                .texOffs(0, 12)
                .addBox(-4.0F, -13.0F, -1.0F, 7.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(42, 36)
                .addBox(-4.25F, -10.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F))
                .texOffs(42, 40)
                .addBox(1.25F, -10.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(-0.01F)),
            PartPose.offsetAndRotation(
                0.25F, 0.0F, 0.0F,
                0.3957F, -0.1209F, -0.0503F
            )
        );

        PartDefinition arms = body.addOrReplaceChild(
            "arms",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -13.0F, 0.0F)
        );

        PartDefinition rightArm = arms.addOrReplaceChild(
            "right_arm",
            CubeListBuilder.create()
                .texOffs(22, 12)
                .addBox(-2.0F, -2.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(36, 17)
                .addBox(-1.5F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                -5.0F, 1.0F, 0.0F,
                -1.2361F, 0.1445F, 0.0048F
            )
        );

        PartDefinition rightElbow = rightArm.addOrReplaceChild(
            "right_elbow",
            CubeListBuilder.create(),
            PartPose.offset(-1.0F, 6.0F, 0.0F)
        );

        rightElbow.addOrReplaceChild(
            "cube_r12",
            CubeListBuilder.create()
                .texOffs(30, 0)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.01F)),
            PartPose.offsetAndRotation(
                0.5F, 0.0F, 0.0F,
                -0.7418F, 0.0F, 0.0F
            )
        );

        rightElbow.addOrReplaceChild(
            "rod",
            CubeListBuilder.create()
                .texOffs(18, 12)
                .addBox(0.0F, -5.0F, -1.0F, 1.0F, 26.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 9)
                .addBox(0.0F, -6.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(2, 39)
                .addBox(0.0F, -10.0F, 1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 37)
                .addBox(0.0F, -11.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 39)
                .addBox(0.0F, -12.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(44, 0)
                .addBox(0.0F, -11.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 43)
                .addBox(0.0F, -10.0F, -5.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 43)
                .addBox(0.0F, -7.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                0.0F, 3.8754F, -3.2847F,
                0.829F, 0.0F, 0.0F
            )
        );

        PartDefinition leftArm = arms.addOrReplaceChild(
            "left_arm",
            CubeListBuilder.create()
                .texOffs(22, 20)
                .addBox(-1.0F, -2.0F, -2.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(22, 37)
                .addBox(-0.5F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                4.0F, 1.0F, 0.0F,
                0.0865F, 0.0114F, -0.1304F
            )
        );

        PartDefinition leftElbow = leftArm.addOrReplaceChild(
            "left_elbow",
            CubeListBuilder.create(),
            PartPose.offset(1.0F, 6.0F, 0.0F)
        );

        leftElbow.addOrReplaceChild(
            "cube_r13",
            CubeListBuilder.create()
                .texOffs(30, 28)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.01F)),
            PartPose.offsetAndRotation(
                -0.5F, 0.0F, 0.0F,
                -0.2182F, 0.0F, 0.0F
            )
        );

        PartDefinition head = body.addOrReplaceChild(
            "head",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, -13.0F, 0.0F)
        );

        head.addOrReplaceChild(
            "cube_r14",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-2.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(
                -1.5F, 0.0F, 0.0F,
                0.1309F, 0.0F, 0.0F
            )
        );

        PartDefinition rightLeg = legs.addOrReplaceChild(
            "right_leg",
            CubeListBuilder.create()
                .texOffs(6, 36)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(-3.0F, 0.0F, 0.0F)
        );

        rightLeg.addOrReplaceChild(
            "right_calf",
            CubeListBuilder.create()
                .texOffs(6, 27)
                .addBox(-1.0F, 0.25F, -0.5F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 5.75F, -0.5F)
        );

        PartDefinition leftLeg = legs.addOrReplaceChild(
            "left_leg",
            CubeListBuilder.create()
                .texOffs(36, 9)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(2.0F, 0.0F, 0.0F)
        );

        leftLeg.addOrReplaceChild(
            "left_calf",
            CubeListBuilder.create()
                .texOffs(22, 28)
                .addBox(-1.0F, 0.25F, -0.5F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 5.75F, -0.5F)
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(GloomShepherdRenderState state) {
        super.setupAnim(state);

		this.attackAnimation.apply(state.attackAnimationState, state.ageInTicks);
		this.summonAnimation.apply(state.summonAnimationState, state.ageInTicks);
		this.walkAnimation.apply(state.walkAnimationState, state.ageInTicks);
    }
}
