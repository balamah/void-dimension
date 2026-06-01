package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.animal.wolf.AdultWolfModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class StaringDogModel extends AdultWolfModel {
    public static final EntityModelLayer STARING_DOG =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "staring_dog"), "main");

	public StaringDogModel(ModelPart modelPart) {
		super(modelPart);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = AdultWolfModel.createBodyLayer(Dilation.NONE);

		return TexturedModelData.of(modelData, 64, 32);
	}
}
