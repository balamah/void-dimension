package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.creeper.CreeperModel;
import net.minecraft.resources.Identifier;

public class CorruptedCreeperModel extends CreeperModel {
    public static final ModelLayerLocation CORRUPTED_CREEPER =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_creeper"),
							 "main");

	public CorruptedCreeperModel(ModelPart modelPart) {
		super(modelPart);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		modelPartData.addOrReplaceChild(
			PartNames.HEAD,
			CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F),
			PartPose.offset(0.0F, 6.0F, 0.0F)
		);
		modelPartData.addOrReplaceChild(
			PartNames.BODY,
			CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F),
			PartPose.offset(0.0F, 6.0F, 0.0F)
		);
		CubeListBuilder modelPartBuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F);
		modelPartData.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, modelPartBuilder, PartPose.offset(-2.0F, 18.0F, 4.0F));
		modelPartData.addOrReplaceChild(PartNames.LEFT_HIND_LEG, modelPartBuilder, PartPose.offset(2.0F, 18.0F, 4.0F));
		modelPartData.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, modelPartBuilder, PartPose.offset(-2.0F, 18.0F, -4.0F));
		modelPartData.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, modelPartBuilder, PartPose.offset(2.0F, 18.0F, -4.0F));
		return LayerDefinition.create(modelData, 64, 32);
	}
}
