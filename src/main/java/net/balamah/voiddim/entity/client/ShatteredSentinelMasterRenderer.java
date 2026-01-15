package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.ShatteredSentinelMasterEntity;
import net.balamah.voiddim.VoidDimension;

public class ShatteredSentinelMasterRenderer
	extends MobEntityRenderer<ShatteredSentinelMasterEntity,
							  ShatteredSentinelMasterRenderState,
							  ShatteredSentinelMasterModel> 
{
	public ShatteredSentinelMasterRenderer(EntityRendererFactory.Context context) {
		super(context,
			  new ShatteredSentinelMasterModel(
					  context.getPart(ShatteredSentinelMasterModel.SHATTERED_SENTINEL_MASTER)),
			  0.75f
		);

		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/shattered_sentinel_master_eyes.png")
		);
	}

	@Override
	public ShatteredSentinelMasterRenderState createRenderState() {
		return new ShatteredSentinelMasterRenderState();
	}

	@Override
	public Identifier getTexture(ShatteredSentinelMasterRenderState state) {
		return Identifier.of(
			VoidDimension.MOD_ID, "textures/entity/shattered_sentinel_master.png"
		);
	}

	@Override
	public void updateRenderState(
		ShatteredSentinelMasterEntity entity,
		ShatteredSentinelMasterRenderState renderState, float f
	) {
		super.updateRenderState(entity, renderState, f);

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
