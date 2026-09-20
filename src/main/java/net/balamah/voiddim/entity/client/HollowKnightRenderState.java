package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class HollowKnightRenderState extends LivingEntityRenderState {
	public final AnimationState walkingState = new AnimationState();
	public final AnimationState attack1State = new AnimationState();
	public final AnimationState attack2State = new AnimationState();
	public final AnimationState attack3State = new AnimationState();
	public final AnimationState attack4State = new AnimationState();
	public final AnimationState vengefulSpiritState = new AnimationState();
}
