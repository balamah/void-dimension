package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.Entity303;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class Entity303Renderer
<E extends Entity303, S extends Entity303RenderState, M extends HumanModel<S>>
	extends BipedEntityRenderer<E, S, M>
{
	public Entity303Renderer(Context context, M model, float shadowRadius) {
		super(context, model, shadowRadius);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/entity_303.png")
		);
	}

	@Override
	public ResourceLocation getTextureLocation(S state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/entity_303.png");
	}

	@Override
	@SuppressWarnings("unchecked")
	public S createRenderState() {
		return (S) new Entity303RenderState();
	}

	@Override
	public void extractRenderState(E entity, S renderState, float f) {
		super.extractRenderState(entity, renderState, f);

		renderState.lightningInvokeAnimation.copyFrom(entity.lightningInvokeAnimationState);
		renderState.groundCorruptionAnimation.copyFrom(entity.groundCorruptionAnimationState);
		renderState.shockwaveInvokeAnimation.copyFrom(entity.shockwaveInvokeAnimationState);
	}
}
