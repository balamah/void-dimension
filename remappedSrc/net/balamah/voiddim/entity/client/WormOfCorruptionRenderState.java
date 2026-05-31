package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class WormOfCorruptionRenderState extends LivingEntityRenderState {
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState shootAnimationState = new AnimationState();
	public final AnimationState digUpAnimationState = new AnimationState();
	public final AnimationState digDownAnimationState = new AnimationState();
}
