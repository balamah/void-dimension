package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.AggressiveNullEntity;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class AggressiveNullRenderer
<E extends AggressiveNullEntity, S extends HollowedAlphaSteveRenderState, M extends HumanModel<S>>
	extends HumanoidMobRenderer<E, S, M>
{
	public AggressiveNullRenderer(
    net.minecraft.client.renderer.entity.EntityRendererProvider.Context context,
    M model,
    float shadowRadius
	) {
		super(context, model, shadowRadius);

		this.addLayer(
			new GlowFeatureRenderer<>(
				this,
				new ResourceLocation(VoidDimension.MOD_ID, "textures/entity/glow/aggressive_null.png")
			)
		);
	}

	@Override
	public ResourceLocation getTextureLocation(S state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/aggressive_null.png");
	}

	@Override
	@SuppressWarnings("unchecked")
	public S createRenderState() {
		return (S) new HollowedAlphaSteveRenderState();
	}
}
