package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.base.WeaponArmedEntityRenderer;
import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.VoidBoundServantEntity;
import net.balamah.voiddim.VoidDimension;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.resources.ResourceLocation;

public class VoidBoundServantRenderer
	extends WeaponArmedEntityRenderer<VoidBoundServantEntity, VoidBoundServantRenderState, VoidBoundServantModel>
{
	public VoidBoundServantRenderer(EntityRendererFactory.Context context) {
		super(context, new VoidBoundServantModel(context.getPart(VoidBoundServantModel.VOID_BOUND_SERVANT)), 0.75f);

		this.addLayer(new HeldItemFeatureRenderer<>(this));
		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/void_bound_servant.png")
		);
	}

	@Override
	public VoidBoundServantRenderState createRenderState() {
		return new VoidBoundServantRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(VoidBoundServantRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(
			VoidDimension.MOD_ID, "textures/entity/void_bound_servant.png"
		);
	}

	@Override
	public void extractRenderState(
		VoidBoundServantEntity entity, VoidBoundServantRenderState state, float f
	) {
		super.extractRenderState(entity, state, f);

		state.suicideAnimationState.copyFrom(entity.suicideAnimationState);
		state.useShieldAnimationState.copyFrom(entity.useShieldAnimationState);
		state.attack1AnimationState.copyFrom(entity.attack1AnimationState);
		state.attack2AnimationState.copyFrom(entity.attack2AnimationState);
		state.attack3AnimationState.copyFrom(entity.attack3AnimationState);
		state.attack4AnimationState.copyFrom(entity.attack4AnimationState);
	}
}
