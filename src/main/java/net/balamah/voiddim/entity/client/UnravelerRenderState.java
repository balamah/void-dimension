package net.balamah.voiddim.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class UnravelerRenderState extends LivingEntityRenderState {
	public final AnimationState terrainShift1AnimationState = new AnimationState();
	public final AnimationState terrainShift2AnimationState = new AnimationState();
	public final AnimationState attack1AnimationState = new AnimationState();
	public final AnimationState attack2AnimationState = new AnimationState();
	public final AnimationState attack3AnimationState = new AnimationState();
	public final AnimationState attack4AnimationState = new AnimationState();
}
