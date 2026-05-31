package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class ConsumedSoulModel extends EntityModel<ConsumedSoulRenderState> {
	public static final ModelLayerLocation CONSUMED_SOUL =
		new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "consumed_soul"),
			"main"
		);

	private final ModelPart root;
	private final ModelPart face;
	private final ModelPart jaw;
	private final ModelPart upper_jaw;
	private final ModelPart upper_jaw_teeth;
	private final ModelPart lower_jaw;
	private final ModelPart lower_jaw_teeth;

	public ConsumedSoulModel(ModelPart root) {
		super(root);

		this.root = root.getChild("root");
		this.face = this.root.getChild("face");
		this.jaw = this.root.getChild("jaw");
		this.upper_jaw = this.jaw.getChild("upper_jaw");
		this.upper_jaw_teeth = this.upper_jaw.getChild("upper_jaw_teeth");
		this.lower_jaw = this.jaw.getChild("lower_jaw");
		this.lower_jaw_teeth = this.lower_jaw.getChild("lower_jaw_teeth");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition rootPart = root.addOrReplaceChild(
			"root",
			CubeListBuilder.create(),
			PartPose.offset(0.0F, 24.0F, 0.0F)
		);

		rootPart.addOrReplaceChild(
			"face",
			CubeListBuilder.create()
				.texOffs(0, 24).addBox(-4.0F, -3.0F, -3.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(26, 15).addBox(-4.0F, -5.0F, 3.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 30).addBox(-3.0F, -4.0F, 3.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 4).addBox(-2.0F, -3.0F, 2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 9).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(26, 17).addBox(-4.0F, -8.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(26, 23).addBox(3.0F, -8.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(18, 33).addBox(-4.0F, -5.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(22, 33).addBox(3.0F, -5.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 33).addBox(-1.0F, -5.0F, -3.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
			PartPose.ZERO
		);

		PartDefinition jaw = rootPart.addOrReplaceChild(
			"jaw",
			CubeListBuilder.create(),
			PartPose.ZERO
		);

		PartDefinition upperJaw = jaw.addOrReplaceChild(
			"upper_jaw",
			CubeListBuilder.create()
				.texOffs(26, 9).addBox(-2.0F, 1.0F, -5.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(0, 32).addBox(-3.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(6, 32).addBox(2.0F, 1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
			PartPose.offset(0.0F, -3.0F, 2.0F)
		);

		upperJaw.addOrReplaceChild(
			"upper_jaw_teeth",
			CubeListBuilder.create()
				.texOffs(8, 35).addBox(-1.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
				.texOffs(36, 6).addBox(1.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)),
			PartPose.ZERO
		);

		PartDefinition lowerJaw = jaw.addOrReplaceChild(
			"lower_jaw",
			CubeListBuilder.create()
				.texOffs(26, 29).addBox(0.0F, -1.0F, -5.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(30, 0).addBox(-2.0F, -1.0F, -4.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(14, 30).addBox(-3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(26, 34).addBox(-2.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 6).addBox(2.0F, -1.0F, -3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
			PartPose.offset(0.0F, 0.0F, 2.0F)
		);

		lowerJaw.addOrReplaceChild(
			"cube_r1",
			CubeListBuilder.create()
				.texOffs(20, 30).addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
			PartPose.offsetAndRotation(-2.0F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F)
		);

		lowerJaw.addOrReplaceChild(
			"lower_jaw_teeth",
			CubeListBuilder.create()
				.texOffs(30, 34).addBox(-2.0F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
				.texOffs(34, 34).addBox(0.0F, 0.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
				.texOffs(0, 35).addBox(-3.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F))
				.texOffs(4, 35).addBox(2.0F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(-0.001F)),
			PartPose.offset(0.0F, -2.0F, -2.0F)
		);

		return LayerDefinition.create(mesh, 64, 64);
	}
}
