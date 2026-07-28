package net.balamah.voiddim.entity.client.renderFeature;

import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class GlowFeatureRenderer
	<S extends EntityRenderState, M extends EntityModel<S>>
	extends EyesLayer<S, M>
{
	protected final String texturePath;

	public GlowFeatureRenderer(
		RenderLayerParent<S, M> featureRendererContext, String texturePath
	) {
		super(featureRendererContext);

		this.texturePath = texturePath;
	}

	@Override
	public RenderType renderType() {
		return RenderTypes.eyes(Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, this.texturePath));
	}
}
