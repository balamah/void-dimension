package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;

public class HumanModel
	<T extends HumanoidRenderState> extends BipedEntityModel<T>
{
    public static final EntityModelLayer AGGRESSIVE_NULL =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "aggressive_null"), "main");

    public static final EntityModelLayer HEROBRINE =
		new EntityModelLayer(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "herobrine"), "main");

	public HumanModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
