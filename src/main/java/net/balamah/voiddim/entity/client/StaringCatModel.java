package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.Dilation;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class StaringCatModel extends CatEntityModel {
    public static final EntityModelLayer STARING_CAT =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "staring_cat"), "main");

	public StaringCatModel(ModelPart modelPart) {
		super(modelPart);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = CatEntityModel.getModelData(Dilation.NONE);

		return TexturedModelData.of(modelData, 64, 32);
	}
}
