package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.BlazeEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class CorruptedBlazeModel extends BlazeEntityModel {
    public static final EntityModelLayer CORRUPTED_BLAZE =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "corrupted_blaze"),
							 "main");

	public CorruptedBlazeModel(ModelPart modelPart) {
		super(modelPart);
	}
}
