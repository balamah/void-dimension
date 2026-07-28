package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class WormOfCorruptionAnimations {
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(0f).build();
	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.45833f)
	.addAnimation("head",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.16667f, KeyframeAnimations.posVec(0f, 0f, -9f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("head",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("upper",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_3",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_4",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.16667f, KeyframeAnimations.posVec(0f, 0f, -2f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_4",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_5",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.16667f, KeyframeAnimations.posVec(0f, 0f, -4f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_5",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition SHOOT = AnimationDefinition.Builder.withLength(0.45833f)
	.addAnimation("head",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("upper",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(27.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition DIG_UP = AnimationDefinition.Builder.withLength(0.5f)
	.addAnimation("root",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, -50f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("head",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(-92.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.29167f, KeyframeAnimations.degreeVec(-97.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.375f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("upper",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.29167f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("lower",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.29167f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("miasma",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.08333f, KeyframeAnimations.posVec(0f, 44f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.29167f, KeyframeAnimations.posVec(0f, 50.25f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("worm",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.16667f, KeyframeAnimations.posVec(0f, 51.71f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.29167f, KeyframeAnimations.posVec(0f, 50.25f, 0f),
				AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition DIG_DOWN = AnimationDefinition.Builder.withLength(0.875f)
	.addAnimation("head",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.08333f, KeyframeAnimations.posVec(0f, -0.75f, 4.25f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("head",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(-12.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_2",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(-5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_3",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.08333f, KeyframeAnimations.posVec(0f, 0f, 0.5f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_3",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_4",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.08333f, KeyframeAnimations.posVec(0f, 0f, 1f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_4",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(-7.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_5",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.08333f, KeyframeAnimations.posVec(0f, 0f, 2f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("body_5",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(-10f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("worm",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.08333f, KeyframeAnimations.posVec(0f, 39f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.20833f, KeyframeAnimations.posVec(0f, 61f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.66667f, KeyframeAnimations.posVec(0f, -84f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("worm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.375f, KeyframeAnimations.degreeVec(180f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("miasma",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.16667f, KeyframeAnimations.posVec(0f, -24.25f, 0f),
				AnimationChannel.Interpolations.LINEAR))).build();
}
