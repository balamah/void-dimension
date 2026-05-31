package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.object.projectile.WindChargeModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.resources.ResourceLocation;

public class VoidSphereModel extends WindChargeModel {
    public static final EntityModelLayer VOID_SPHERE =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_sphere"),
							 "main");

	public VoidSphereModel(ModelPart modelPart) {
		super(modelPart);
	}
}
