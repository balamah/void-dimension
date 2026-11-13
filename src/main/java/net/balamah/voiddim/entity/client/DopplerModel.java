
package net.balamah.voiddim.entity.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.resources.Identifier;

import net.balamah.voiddim.VoidDimension;

public class DopplerModel extends EntityModel<EntityRenderState> {
    public static final ModelLayerLocation DOPPLER =
        new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "doppler"),
            "main"
        );

    private final ModelPart root;
    private final ModelPart part3;
    private final ModelPart part2;
    private final ModelPart part1;
    private final ModelPart eyes;
    private final ModelPart main_eye;
    private final ModelPart decorational;

    public DopplerModel(ModelPart root) {
        super(root);

        this.root = root.getChild("root");
        this.part3 = this.root.getChild("3");
        this.part2 = this.root.getChild("2");
        this.part1 = this.root.getChild("1");
        this.eyes = this.root.getChild("eyes");
        this.main_eye = this.eyes.getChild("main_eye");
        this.decorational = this.eyes.getChild("decorational");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition root = modelPartData.addOrReplaceChild(
            "root",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 24.0F, 0.0F)
        );

        PartDefinition part3 = root.addOrReplaceChild(
            "3",
            CubeListBuilder.create()
                .texOffs(38, 25)
                .addBox(-2.0F, -2.0F, -4.0F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 39)
                .addBox(2.0F, -1.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(38, 36)
                .addBox(-3.0F, -1.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, -4.0F, -2.0F)
        );

        PartDefinition part2 = root.addOrReplaceChild(
            "2",
            CubeListBuilder.create()
                .texOffs(0, 13)
                .addBox(-6.0F, -2.0F, -5.0F, 11.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(28, 39)
                .addBox(-5.0F, -2.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 25)
                .addBox(-4.0F, -4.0F, -6.0F, 8.0F, 3.0F, 11.0F, new CubeDeformation(-0.0001F)),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition part1 = root.addOrReplaceChild(
            "1",
            CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-6.0F, -1.0F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition eyes = root.addOrReplaceChild(
            "eyes",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition main_eye = eyes.addOrReplaceChild(
            "main_eye",
            CubeListBuilder.create()
                .texOffs(20, 39)
                .addBox(-1.0F, -3.75F, -6.75F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        PartDefinition decorational = eyes.addOrReplaceChild(
            "decorational",
            CubeListBuilder.create(),
            PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        decorational.addOrReplaceChild(
            "cube_r1",
            CubeListBuilder.create()
                .texOffs(32, 41)
                .addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-1.75F, -3.25F, -5.75F, 0.2618F, 0.3927F, 0.0F)
        );

        decorational.addOrReplaceChild(
            "cube_r2",
            CubeListBuilder.create()
                .texOffs(28, 41)
                .addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(1.75F, -4.25F, -5.75F, 0.2618F, -0.3927F, 0.0F)
        );

        decorational.addOrReplaceChild(
            "cube_r3",
            CubeListBuilder.create()
                .texOffs(32, 39)
                .addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(-1.75F, -4.75F, -5.75F, -0.2618F, 0.3927F, 0.0F)
        );

        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(EntityRenderState state) {
        super.setupAnim(state);
    }
}
