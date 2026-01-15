package net.balamah.voiddim.entity.client.renderFeature;

import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.VoidDimension;

public class GlowFeatureRenderer
	<S extends EntityRenderState, M extends EntityModel<S>>
	extends EyesFeatureRenderer<S, M>
{
	protected final String texturePath;

	public GlowFeatureRenderer(
		FeatureRendererContext<S, M> featureRendererContext, String texturePath
	) {
		super(featureRendererContext);

		this.texturePath = texturePath;
	}

	@Override
	public RenderLayer getEyesTexture() {
		return RenderLayers.eyes(Identifier.of(VoidDimension.MOD_ID, this.texturePath));
	}
}
