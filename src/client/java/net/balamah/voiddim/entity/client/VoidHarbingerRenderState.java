package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class VoidHarbingerRenderState extends LivingEntityRenderState {
	public final AnimationState shootAnimationState = new AnimationState();
	public final AnimationState summonAnimationState = new AnimationState();
	public final AnimationState summonEndAnimationState = new AnimationState();
}
