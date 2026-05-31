package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.object.projectile.WindChargeModel;
import net.minecraft.resources.ResourceLocation;

public class VoidSphereModel extends WindChargeModel {
    public static final ModelLayerLocation VOID_SPHERE =
		new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_sphere"),
							 "main");

	public VoidSphereModel(ModelPart modelPart) {
		super(modelPart);
	}
}
