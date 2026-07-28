package net.balamah.voiddim.entity.client.base;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class AbstractWerewolfRenderState extends LivingEntityRenderState {
    public final AnimationState attackHitAnimationState = new AnimationState();
    public final AnimationState attackBiteAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
}
