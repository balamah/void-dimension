package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.animal.feline.AdultCatModel;
import net.minecraft.client.model.animal.feline.AdultFelineModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class StaringCatModel extends AdultCatModel {
    public static final EntityModelLayer STARING_CAT =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "staring_cat"), "main");

	public StaringCatModel(ModelPart modelPart) {
		super(modelPart);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = AdultFelineModel.createBodyMesh(Dilation.NONE);

		return TexturedModelData.of(modelData, 64, 32);
	}
}
