package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.EyeBrightEntity;
import net.balamah.voiddim.VoidDimension;

public class EyeBrightRenderer extends MobEntityRenderer<EyeBrightEntity, EyeBrightRenderState, EyeBrightModel> {
	public EyeBrightRenderer(EntityRendererFactory.Context context) {
		super(context, new EyeBrightModel(context.getPart(EyeBrightModel.EYE_BRIGHT)), 0.75f);
		
		this.addFeature(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/eye_bright.png")
		);
	}

	@Override
	public Identifier getTexture(EyeBrightRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/eye_bright.png");
	}

	@Override
	public EyeBrightRenderState createRenderState() {
		return new EyeBrightRenderState();
	}

	@Override
	public void updateRenderState(
		EyeBrightEntity entity, EyeBrightRenderState renderState, float f
	) {
		super.updateRenderState(entity, renderState, f);

		renderState.idleAnimationState.copyFrom(entity.idleAnimationState);
		renderState.walkAnimationState.copyFrom(entity.walkAnimationState);
		renderState.attack1AnimationState.copyFrom(entity.attack1AnimationState);
		renderState.attack2AnimationState.copyFrom(entity.attack2AnimationState);
		renderState.attack3AnimationState.copyFrom(entity.attack3AnimationState);
		renderState.shoot1AnimationState.copyFrom(entity.shoot1AnimationState);
		renderState.shoot2AnimationState.copyFrom(entity.shoot2AnimationState);
		renderState.shoot3AnimationState.copyFrom(entity.shoot3AnimationState);
		renderState.shoot4AnimationState.copyFrom(entity.shoot4AnimationState);
		renderState.magnettedAttackAnimationState.copyFrom(entity.magnettedAttackAnimationState);
	}
}
