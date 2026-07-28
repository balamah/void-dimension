package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.effects.EvokerFangsModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.Identifier;

public class DarkGraspModel extends EvokerFangsModel {
    public static final ModelLayerLocation DARK_GRASP =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "dark_grasp"),
							 "main");

	public DarkGraspModel(ModelPart modelPart) {
		super(modelPart);
	}
}
