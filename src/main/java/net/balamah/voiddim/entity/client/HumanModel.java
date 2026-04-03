package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.Dilation;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class HumanModel
	<T extends BipedEntityRenderState> extends BipedEntityModel<T>
{
    public static final EntityModelLayer AGGRESSIVE_NULL =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "aggressive_null"), "main");

    public static final EntityModelLayer HEROBRINE =
		new EntityModelLayer(Identifier.of(VoidDimension.MOD_ID, "herobrine"), "main");

	public HumanModel(ModelPart root) {
		super(root);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0f);

		return TexturedModelData.of(modelData, 64, 64);
	}
}
