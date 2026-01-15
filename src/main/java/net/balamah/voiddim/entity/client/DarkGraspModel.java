package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.EvokerFangsEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class DarkGraspModel extends EvokerFangsEntityModel {
    public static final EntityModelLayer DARK_GRASP =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "dark_grasp"),
							 "main");

	public DarkGraspModel(ModelPart modelPart) {
		super(modelPart);
	}
}
