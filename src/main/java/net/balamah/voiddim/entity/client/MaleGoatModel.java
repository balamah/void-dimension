package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.animal.goat.GoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.Identifier;

public class MaleGoatModel extends GoatModel {
    public static final ModelLayerLocation MALE_GOAT =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "male_goat"),
							 "main");

	public MaleGoatModel(ModelPart root) {
		super(root);
	}
}
