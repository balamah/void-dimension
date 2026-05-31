package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class VoidBoundServantRenderState extends ArmedEntityRenderState {
	public final AnimationState suicideAnimationState = new AnimationState();
	public final AnimationState useShieldAnimationState = new AnimationState();
	public final AnimationState attack1AnimationState = new AnimationState();
	public final AnimationState attack2AnimationState = new AnimationState();
	public final AnimationState attack3AnimationState = new AnimationState();
	public final AnimationState attack4AnimationState = new AnimationState();
}
