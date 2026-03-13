package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.HerobrineEntity;
import net.balamah.voiddim.VoidDimension;

public class HerobrineRenderer
<E extends HerobrineEntity, S extends HerobrineRenderState, M extends HumanModel<S>>
	extends BipedEntityRenderer<E, S, M>
{
	public HerobrineRenderer(Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);

		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/herobrine_glow.png")
		);
	}

	@Override
	public Identifier getTexture(S state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/herobrine.png");
	}

	@Override
	@SuppressWarnings("unchecked")
	public S createRenderState() {
		return (S) new HerobrineRenderState();
	}

	@Override
	public void updateRenderState(E entity, S renderState, float f) {
		super.updateRenderState(entity, renderState, f);

		renderState.lightningInvokeAnimation.copyFrom(entity.lightningInvokeAnimationState);
		renderState.groundCorruptionAnimation.copyFrom(entity.groundCorruptionAnimationState);
		renderState.shockwaveInvokeAnimation.copyFrom(entity.shockwaveInvokeAnimationState);
	}
}
