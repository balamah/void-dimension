package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class SnowmanAnimations {
	public static final AnimationDefinition POSE = AnimationDefinition.Builder.withLength(0f)
		.addAnimation("left_arm",
			new AnimationChannel(AnimationChannel.Targets.ROTATION,
				new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 72.5f),
					AnimationChannel.Interpolations.LINEAR)))
		.addAnimation("right_arm",
			new AnimationChannel(AnimationChannel.Targets.ROTATION,
				new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -70f),
					AnimationChannel.Interpolations.LINEAR))).build();

	public static final AnimationDefinition BASED = AnimationDefinition.Builder.withLength(1.41667f)
		.addAnimation("left_arm",
			new AnimationChannel(AnimationChannel.Targets.ROTATION,
				new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 77.5f),
					AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.33333f, KeyframeAnimations.degreeVec(-41f, 40.53f, -55.72f),
					AnimationChannel.Interpolations.LINEAR)))
		.addAnimation("right_arm",
			new AnimationChannel(AnimationChannel.Targets.ROTATION,
				new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -77.5f),
					AnimationChannel.Interpolations.LINEAR))).build();
	}
