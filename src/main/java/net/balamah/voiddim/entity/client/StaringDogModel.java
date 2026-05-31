package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.animal.wolf.AdultWolfModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.ResourceLocation;

public class StaringDogModel extends AdultWolfModel {
    public static final ModelLayerLocation STARING_DOG =
		new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "staring_dog"), "main");

	public StaringDogModel(ModelPart modelPart) {
		super(modelPart);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = AdultWolfModel.createBodyLayer(CubeDeformation.NONE);

		return LayerDefinition.create(modelData, 64, 32);
	}
}
