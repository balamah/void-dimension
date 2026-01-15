package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

import net.balamah.voiddim.entity.custom.CorruptedWarriorEntity;
import net.balamah.voiddim.VoidDimension;

public class CorruptedWarriorRenderer
	extends MobEntityRenderer<CorruptedWarriorEntity, CorruptedWarriorRenderState, CorruptedWarriorModel> 
{
	public CorruptedWarriorRenderer(EntityRendererFactory.Context context) {
		super(context, new CorruptedWarriorModel(context.getPart(CorruptedWarriorModel.CORRUPTED_WARRIOR)), 0.75f);
	}

	@Override
	public CorruptedWarriorRenderState createRenderState() {
		return new CorruptedWarriorRenderState();
	}

	@Override
	public Identifier getTexture(CorruptedWarriorRenderState state) {
		return Identifier.of(VoidDimension.MOD_ID, "textures/entity/corrupted_warrior.png");
	}

	@Override
	public void updateRenderState(
		CorruptedWarriorEntity entity, CorruptedWarriorRenderState renderState,
		float f
	) {
		super.updateRenderState(entity, renderState, f);

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
