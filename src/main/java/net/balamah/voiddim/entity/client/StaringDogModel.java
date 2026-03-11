package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.Dilation;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class StaringDogModel extends WolfEntityModel {
    public static final EntityModelLayer STARING_DOG =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "staring_dog"), "main");

	public StaringDogModel(ModelPart modelPart) {
		super(modelPart);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = WolfEntityModel.getTexturedModelData(Dilation.NONE);

		return TexturedModelData.of(modelData, 64, 32);
	}
}
