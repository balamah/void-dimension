package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.VoidHarbingerEntity;
import net.balamah.voiddim.VoidDimension;

public class VoidHarbingerRenderer
	extends MobEntityRenderer<VoidHarbingerEntity,
							  VoidHarbingerRenderState,
							  VoidHarbingerModel> 
{
	public VoidHarbingerRenderer(EntityRendererFactory.Context context) {
		super(context, new VoidHarbingerModel(context.getPart(VoidHarbingerModel.VOID_HARBINGER)), 0.75f);

		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/void_harbinger_glow.png")
		);
	}

	@Override
	public VoidHarbingerRenderState createRenderState() {
		return new VoidHarbingerRenderState();
	}

	@Override
	public Identifier getTexture(VoidHarbingerRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/void_harbinger.png");
	}

	@Override
	public void updateRenderState(VoidHarbingerEntity entity,
								  VoidHarbingerRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);

		renderState.shootAnimationState.copyFrom(entity.shootAnimationState);
		renderState.summonAnimationState.copyFrom(entity.summonAnimationState);
		renderState.summonEndAnimationState.copyFrom(entity.summonEndAnimationState);
	}
}
