package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class WerewolfRenderState extends LivingEntityRenderState {
    public final AnimationState attackHitAnimationState = new AnimationState();
    public final AnimationState attackBiteAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
}
