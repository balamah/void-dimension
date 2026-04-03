package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.AggressiveNullEntity;
import net.balamah.voiddim.VoidDimension;

public class AggressiveNullRenderer
<E extends AggressiveNullEntity, S extends HollowedAlphaSteveRenderState, M extends HumanModel<S>>
	extends BipedEntityRenderer<E, S, M>
{
	public AggressiveNullRenderer(Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);

		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/aggressive_null.png")
		);
	}

	@Override
	public Identifier getTexture(S state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/aggressive_null.png");
	}

	@Override
	@SuppressWarnings("unchecked")
	public S createRenderState() {
		return (S) new HollowedAlphaSteveRenderState();
	}
}
