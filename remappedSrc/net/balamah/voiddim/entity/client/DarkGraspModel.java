package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.effects.EvokerFangsModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class DarkGraspModel extends EvokerFangsModel {
    public static final EntityModelLayer DARK_GRASP =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "dark_grasp"),
							 "main");

	public DarkGraspModel(ModelPart modelPart) {
		super(modelPart);
	}
}
