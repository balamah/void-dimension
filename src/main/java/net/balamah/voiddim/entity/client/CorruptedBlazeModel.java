package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.resources.Identifier;

public class CorruptedBlazeModel extends BlazeModel {
    public static final ModelLayerLocation CORRUPTED_BLAZE =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "corrupted_blaze"),
							 "main");

	public CorruptedBlazeModel(ModelPart modelPart) {
		super(modelPart);
	}
}
