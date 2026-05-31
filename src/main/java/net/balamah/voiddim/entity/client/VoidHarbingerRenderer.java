package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.VoidHarbingerEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class VoidHarbingerRenderer
	extends MobRenderer<VoidHarbingerEntity,
							  VoidHarbingerRenderState,
							  VoidHarbingerModel> 
{
	public VoidHarbingerRenderer(EntityRendererProvider.Context context) {
		super(context, new VoidHarbingerModel(context.bakeLayer(VoidHarbingerModel.VOID_HARBINGER)), 0.75f);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/void_harbinger.png")
		);
	}

	@Override
	public VoidHarbingerRenderState createRenderState() {
		return new VoidHarbingerRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(VoidHarbingerRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/void_harbinger.png");
	}

	@Override
	public void extractRenderState(VoidHarbingerEntity entity,
								  VoidHarbingerRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.shootAnimationState.copyFrom(entity.shootAnimationState);
		renderState.summonAnimationState.copyFrom(entity.summonAnimationState);
		renderState.summonEndAnimationState.copyFrom(entity.summonEndAnimationState);
	}
}
