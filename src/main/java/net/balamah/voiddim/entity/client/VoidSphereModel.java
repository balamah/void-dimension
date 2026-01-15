package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.model.WindChargeEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class VoidSphereModel extends WindChargeEntityModel {
    public static final EntityModelLayer VOID_SPHERE =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "void_sphere"),
							 "main");

	public VoidSphereModel(ModelPart modelPart) {
		super(modelPart);
	}
}
