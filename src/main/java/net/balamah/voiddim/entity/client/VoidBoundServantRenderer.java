package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.base.WeaponArmedEntityRenderer;
import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.VoidBoundServantEntity;
import net.balamah.voiddim.VoidDimension;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class VoidBoundServantRenderer
	extends WeaponArmedEntityRenderer<VoidBoundServantEntity, VoidBoundServantRenderState, VoidBoundServantModel>
{
	public VoidBoundServantRenderer(EntityRendererProvider.Context context) {
		super(context, new VoidBoundServantModel(context.bakeLayer(VoidBoundServantModel.VOID_BOUND_SERVANT)), 0.75f);

		this.addLayer(new ItemInHandLayer<>(this));
		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/void_bound_servant.png")
		);
	}

	@Override
	public VoidBoundServantRenderState createRenderState() {
		return new VoidBoundServantRenderState();
	}

	@Override
	public Identifier getTextureLocation(VoidBoundServantRenderState state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/void_bound_servant.png");
	}
}
