package net.balamah.voiddim.entity.client.renderFeature;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.ResourceLocation;

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
	public RenderType renderType() {
		return RenderTypes.eyes(ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, this.texturePath));
	}
}
