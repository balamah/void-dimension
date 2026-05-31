package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.NullEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class NullEntityRenderer
<E extends NullEntity, S extends HollowedAlphaSteveRenderState, M extends HollowedAlphaSteveModel<S>>
	extends HumanoidMobRenderer<E, S, M>
{
	public NullEntityRenderer(Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/null.png")
		);
	}

	@Override
	public ResourceLocation getTextureLocation(S state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/null.png");
	}

	@Override
	public S createRenderState() {
		return (S) new HollowedAlphaSteveRenderState();
	}
}
