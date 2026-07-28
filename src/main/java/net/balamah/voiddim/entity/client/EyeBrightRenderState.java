package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class EyeBrightRenderState extends LivingEntityRenderState {
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState walkAnimationState = new AnimationState();
	public final AnimationState attack1AnimationState = new AnimationState();
	public final AnimationState attack2AnimationState = new AnimationState();
	public final AnimationState attack3AnimationState = new AnimationState();
	public final AnimationState shoot1AnimationState = new AnimationState();
	public final AnimationState shoot2AnimationState = new AnimationState();
	public final AnimationState shoot3AnimationState = new AnimationState();
	public final AnimationState shoot4AnimationState = new AnimationState();
	public final AnimationState magnettedAttackAnimationState = new AnimationState();
}
