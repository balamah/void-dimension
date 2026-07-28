package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.animal.feline.AdultCatModel;
import net.minecraft.client.model.animal.feline.AdultFelineModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.Identifier;

public class StaringCatModel extends AdultCatModel {
    public static final ModelLayerLocation STARING_CAT =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "staring_cat"), "main");

	public StaringCatModel(ModelPart modelPart) {
		super(modelPart);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = AdultFelineModel.createBodyMesh(CubeDeformation.NONE);

		return LayerDefinition.create(modelData, 64, 32);
	}
}
