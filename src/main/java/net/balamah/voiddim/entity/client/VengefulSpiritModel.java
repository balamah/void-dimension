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
import net.balamah.voiddim.VoidDimension;

public class VengefulSpiritModel extends EntityModel<VengefulSpiritRenderState> {
	public static final ModelLayerLocation VENGEFUL_SPIRIT =
		new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "vengeful_spirit"),
			"main"
		);

	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart trail;

	public VengefulSpiritModel(ModelPart root) {
		super(root);
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.trail = this.root.getChild("trail");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition root = modelPartData.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -9.0F, -7.0F, 6.0F, 6.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(16, 47).addBox(-3.0F, -9.0F, -8.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 0).addBox(3.0F, -9.0F, -7.0F, 1.0F, 6.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(38, 19).addBox(-4.0F, -9.0F, -7.0F, 1.0F, 6.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 19).addBox(-3.0F, -10.0F, -7.0F, 6.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(-3.0F, -3.0F, -7.0F, 6.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition trail = root.addOrReplaceChild("trail", CubeListBuilder.create().texOffs(38, 46).addBox(-3.0F, -9.0F, 6.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(38, 38).addBox(-3.0F, -10.0F, 6.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(54, 46).addBox(-4.0F, -9.0F, 6.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 55).addBox(-3.0F, -3.0F, 6.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(44, 54).addBox(3.0F, -9.0F, 6.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 54).addBox(-4.0F, -4.0F, 10.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(12, 61).addBox(3.0F, -7.5F, 10.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 47).addBox(1.0F, -6.25F, 8.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(30, 54).addBox(-0.25F, -4.5F, 8.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 58).addBox(0.0F, -8.0F, 8.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(54, 56).addBox(-2.5F, -5.5F, 8.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(30, 47).addBox(-1.25F, -6.75F, 8.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(-2, -2).addBox(-3.5F, -7.5F, 8.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(modelData, 128, 128);
	}
}
