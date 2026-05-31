package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class CorruptedBlazeModel extends BlazeModel {
    public static final EntityModelLayer CORRUPTED_BLAZE =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_blaze"),
							 "main");

	public CorruptedBlazeModel(ModelPart modelPart) {
		super(modelPart);
	}
}
