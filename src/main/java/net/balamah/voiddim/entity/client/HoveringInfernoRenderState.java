package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class HoveringInfernoRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState phase2TransitionAnimationState = new AnimationState();
    public final AnimationState phase3TransitionAnimationState = new AnimationState();
    public final AnimationState phase4TransitionAnimationState = new AnimationState();
    public final AnimationState phase5TransitionAnimationState = new AnimationState();
    public final AnimationState stunAnimationState = new AnimationState();
    public final AnimationState recoverAnimationState = new AnimationState();
}
