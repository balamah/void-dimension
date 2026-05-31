package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.custom.WormOfCorruptionEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class WormOfCorruptionRenderer
	extends MobRenderer<WormOfCorruptionEntity, WormOfCorruptionRenderState,
								WormOfCorruptionModel> 
{
	public WormOfCorruptionRenderer(EntityRendererProvider.Context context) {
		super(context, new WormOfCorruptionModel(context.bakeLayer(WormOfCorruptionModel.WORM_OF_CORRUPTION)), 0.75f);
	}

	@Override
	public WormOfCorruptionRenderState createRenderState() {
		return new WormOfCorruptionRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(WormOfCorruptionRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/worm_of_corruption.png");
	}

	@Override
	public void extractRenderState(WormOfCorruptionEntity entity,
								  WormOfCorruptionRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);

		renderState.attackAnimationState.copyFrom(entity.attackAnimationState);
		renderState.shootAnimationState.copyFrom(entity.shootAnimationState);
		renderState.digUpAnimationState.copyFrom(entity.digUpAnimationState);
		renderState.digDownAnimationState.copyFrom(entity.digDownAnimationState);
	}
}
