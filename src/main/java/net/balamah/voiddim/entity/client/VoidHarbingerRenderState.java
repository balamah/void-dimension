package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class VoidHarbingerRenderState extends LivingEntityRenderState {
	public final AnimationState shootAnimationState = new AnimationState();
	public final AnimationState summonAnimationState = new AnimationState();
	public final AnimationState summonEndAnimationState = new AnimationState();
}
