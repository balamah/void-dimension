package net.balamah.voiddim.entity.client;

import net.balamah.voiddim.entity.client.renderFeature.GlowFeatureRenderer;
import net.balamah.voiddim.entity.custom.CorruptedWarriorEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.balamah.voiddim.VoidDimension;

public class CorruptedWarriorRenderer
	extends MobRenderer<CorruptedWarriorEntity, CorruptedWarriorRenderState, CorruptedWarriorModel> 
{
	public CorruptedWarriorRenderer(EntityRendererProvider.Context context) {
		super(context, new CorruptedWarriorModel(context.bakeLayer(CorruptedWarriorModel.CORRUPTED_WARRIOR)), 0.75f);

		this.addLayer(
			new GlowFeatureRenderer<>(this, "textures/entity/glow/corrupted_warrior.png")
		);
	}

	@Override
	public CorruptedWarriorRenderState createRenderState() {
		return new CorruptedWarriorRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(CorruptedWarriorRenderState state) {
		return ResourceLocation.fromNamespaceAndPath(VoidDimension.MOD_ID, "textures/entity/corrupted_warrior.png");
	}

	@Override
	public void extractRenderState(
		CorruptedWarriorEntity entity, CorruptedWarriorRenderState renderState,
		float f
	) {
		super.extractRenderState(entity, renderState, f);

		renderState.idleAnimationState.copyFrom(entity.idleAnimationState);
		renderState.walkAnimationState.copyFrom(entity.walkAnimationState);
		renderState.strongAttackAnimationState.copyFrom(entity.strongAttackAnimationState);
		renderState.strongestAttackAnimationState.copyFrom(entity.strongestAttackAnimationState);
		renderState.summonProjectileAnimationState.copyFrom(entity.summonProjectileAnimationState);
		renderState.specialAttackAnimationState.copyFrom(entity.specialAttackAnimationState);
		renderState.normalAttack1AnimationState.copyFrom(entity.normalAttack1AnimationState);
		renderState.normalAttack2AnimationState.copyFrom(entity.normalAttack2AnimationState);
		renderState.normalAttack3AnimationState.copyFrom(entity.normalAttack3AnimationState);
	}
}
