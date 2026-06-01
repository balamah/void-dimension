package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;

public class HumanModel
	<T extends HumanoidRenderState> extends HumanoidModel<T>
{
    public static final ModelLayerLocation AGGRESSIVE_NULL =
		new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "aggressive_null"), "main");

    public static final ModelLayerLocation HEROBRINE =
		new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "herobrine"), "main");

	public HumanModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);

		return LayerDefinition.create(modelData, 64, 64);
	}
}
