package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class GhostRenderState extends LivingEntityRenderState {
    public final AnimationState walkAnimationState = new AnimationState();
}
