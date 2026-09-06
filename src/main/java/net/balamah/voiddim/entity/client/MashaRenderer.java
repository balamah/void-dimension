package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.client.base.BasicRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.entity.custom.MashaEntity;

public class MashaRenderer
	extends MobRenderer<MashaEntity, BasicRenderState, MashaModel> 
{
	public MashaRenderer(EntityRendererProvider.Context context) {
		super(context, new MashaModel(context.bakeLayer(MashaModel.MASHA)), 0.75f);
	}

	@Override
	public BasicRenderState createRenderState() {
		return new BasicRenderState();
	}

	@Override
	public Identifier getTextureLocation(BasicRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/masha.png");
	}
}
