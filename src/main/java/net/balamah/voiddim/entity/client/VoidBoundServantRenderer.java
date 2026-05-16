package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.base.WeaponArmedEntityRenderer;
import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.VoidBoundServantEntity;
import net.balamah.voiddim.VoidDimension;

import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class VoidBoundServantRenderer
	extends WeaponArmedEntityRenderer<VoidBoundServantEntity, VoidBoundServantRenderState, VoidBoundServantModel>
{
	public VoidBoundServantRenderer(EntityRendererProvider.Context context) {
		super(context, new VoidBoundServantModel(context.bakeLayer(VoidBoundServantModel.VOID_BOUND_SERVANT)), 0.75f);

		this.addLayer(new ItemInHandLayer<>(this));
		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/void_bound_servant.png")
		);
	}

	@Override
	public VoidBoundServantRenderState createRenderState() {
		return new VoidBoundServantRenderState();
	}

	@Override
	public Identifier getTextureLocation(VoidBoundServantRenderState state) {
		return Identifier.fromNamespaceAndPath(
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
