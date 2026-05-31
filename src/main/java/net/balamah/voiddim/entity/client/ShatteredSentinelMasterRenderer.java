package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.ShatteredSentinelMasterEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class ShatteredSentinelMasterRenderer
	extends MobRenderer<ShatteredSentinelMasterEntity,
							  ShatteredSentinelMasterRenderState,
							  ShatteredSentinelMasterModel> 
{
	public ShatteredSentinelMasterRenderer(EntityRendererProvider.Context context) {
		super(context,
			  new ShatteredSentinelMasterModel(
					  context.bakeLayer(ShatteredSentinelMasterModel.SHATTERED_SENTINEL_MASTER)),
			  0.75f
		);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/shattered_sentinel_master_eyes.png")
		);
	}

	@Override
	public ShatteredSentinelMasterRenderState createRenderState() {
		return new ShatteredSentinelMasterRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(ShatteredSentinelMasterRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "textures/entity/shattered_sentinel_master.png"
		);
	}

	@Override
	public void extractRenderState(
		ShatteredSentinelMasterEntity entity,
		ShatteredSentinelMasterRenderState renderState, float f
	) {
		super.extractRenderState(entity, renderState, f);

		renderState.attackAnimationState.copyFrom(entity.attackAnimationState);
		renderState.walkAnimationState.copyFrom(entity.walkAnimationState);
		renderState.idleAnimationState.copyFrom(entity.idleAnimationState);
		renderState.shatterGroundBeginAnimationState.copyFrom(
			entity.shatterGroundBeginAnimationState
		);

		renderState.shatterGroundPushAnimationState.copyFrom(
			entity.shatterGroundPushAnimationState
		);

		renderState.shatterEndAnimationStateGround.copyFrom(
			entity.shatterEndAnimationStateGround
		);

		renderState.shockWaveInvokeState.copyFrom(entity.shockWaveInvokeState);
		renderState.throwBlockState.copyFrom(entity.throwBlockState);
		renderState.stonesFloatAnimationState.copyFrom(entity.stonesFloatAnimationState);
	}
}
