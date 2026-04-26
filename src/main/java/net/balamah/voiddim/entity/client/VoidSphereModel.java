package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.object.projectile.WindChargeModel;
import net.minecraft.resources.Identifier;

public class VoidSphereModel extends WindChargeModel {
    public static final ModelLayerLocation VOID_SPHERE =
		new ModelLayerLocation(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "void_sphere"),
							 "main");

	public VoidSphereModel(ModelPart modelPart) {
		super(modelPart);
	}
}
