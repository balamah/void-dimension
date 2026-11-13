package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class VoidHangmanAnimations {
	// Small helpers so the keyframe tables below stay readable. Values are exactly the exported ones.
	private static Keyframe rot(float t, float x, float y, float z) {
		return new Keyframe(t, KeyframeAnimations.degreeVec(x, y, z), AnimationChannel.Interpolations.LINEAR);
	}

	private static Keyframe pos(float t, float x, float y, float z) {
		return new Keyframe(t, KeyframeAnimations.posVec(x, y, z), AnimationChannel.Interpolations.LINEAR);
	}

	private static AnimationChannel rotation(Keyframe... keyframes) {
		return new AnimationChannel(AnimationChannel.Targets.ROTATION, keyframes);
	}

	private static AnimationChannel position(Keyframe... keyframes) {
		return new AnimationChannel(AnimationChannel.Targets.POSITION, keyframes);
	}

	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(0.75F)
		.addAnimation("body", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4583F, -7.5F, 0.0F, 0.0F),
			rot(0.5833F, 15.0F, 0.0F, 0.0F),
			rot(0.7083F, 17.5F, 0.0F, 0.0F)))
		.addAnimation("arms", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.5F, -25.0F, 0.0F, 0.0F),
			rot(0.75F, 15.0F, 0.0F, 0.0F)))
		.addAnimation("lower_body", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4167F, -17.5F, 0.0F, 0.0F),
			rot(0.75F, 7.5F, 0.0F, 0.0F)))
		.addAnimation("right_elbow", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.5F, -10.0F, 0.0F, 0.0F),
			rot(0.75F, 37.5F, 0.0F, 0.0F)))
		.addAnimation("left_elbow", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.5F, -5.0F, 0.0F, 0.0F),
			rot(0.75F, 30.0F, 0.0F, 0.0F)))
		.addAnimation("hanging_chain_3", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 11.67F, 0.0F, 0.0F),
			rot(0.5F, 25.0F, 0.0F, 0.0F)))
		.addAnimation("hanging_chain_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -18.57F, 0.0F, 0.0F),
			rot(0.4167F, 7.5F, 0.0F, 0.0F)))
		.addAnimation("hanging_chain_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 0.83F, 0.0F, 0.0F),
			rot(0.5F, 12.5F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.7083F)
		.addAnimation("head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2917F, -20.0F, 0.0F, 0.0F),
			rot(0.3333F, -22.5F, 0.0F, 0.0F),
			rot(0.4583F, -12.5F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("arms", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2917F, -17.5F, 0.0F, 0.0F),
			rot(0.4583F, 17.5F, 0.0F, 0.0F),
			rot(0.5F, 40.62F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_elbow", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.3333F, -17.5F, 0.0F, 0.0F),
			rot(0.4583F, 10.0F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_elbow", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.3333F, -12.5F, 0.0F, 0.0F),
			rot(0.4583F, 27.5F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("hanging_chain", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2917F, 10.0F, 0.0F, 0.0F),
			rot(0.3333F, 17.5F, 0.0F, 0.0F),
			rot(0.4583F, -32.5F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("hanging_chain", position(
			pos(0.0F, 0.0F, 0.0F, 0.0F),
			pos(0.2917F, 0.0F, -1.5F, 0.0F),
			pos(0.3333F, 0.25F, -1.75F, 0.25F),
			pos(0.4583F, 0.25F, -3.0F, -1.25F),
			pos(0.5F, 0.25F, -3.0F, 0.25F),
			pos(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("jaw", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2917F, -0.5F, 0.0F, 0.0F),
			rot(0.4583F, 27.5F, 0.0F, 0.0F),
			rot(0.5F, 0.0F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("upper_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2917F, 0.5F, 0.0F, 0.0F),
			rot(0.4583F, -27.5F, 0.0F, 0.0F),
			rot(0.5F, 0.0F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.build();
}
