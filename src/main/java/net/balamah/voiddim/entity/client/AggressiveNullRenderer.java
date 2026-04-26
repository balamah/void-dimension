package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.AggressiveNullEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.Identifier;
import net.balamah.voiddim.VoidDimension;

public class AggressiveNullRenderer
<E extends AggressiveNullEntity, S extends HollowedAlphaSteveRenderState, M extends HumanModel<S>>
	extends HumanoidMobRenderer<E, S, M>
{
	public AggressiveNullRenderer(Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/aggressive_null.png")
		);
	}

	@Override
	public Identifier getTextureLocation(S state) {
		return Identifier.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/aggressive_null.png");
	}

	@Override
	@SuppressWarnings("unchecked")
	public S createRenderState() {
		return (S) new HollowedAlphaSteveRenderState();
	}
}
