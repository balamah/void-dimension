package net.balamah.voiddim.entity.client.base;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class BasicAnimations {
	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(0.7916766f).looping()
	.addAnimation("left_arm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -42.5f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.33333f, KeyframeAnimations.degreeVec(0f, 0f, 42f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, -42f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("right_arm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 40f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.33333f, KeyframeAnimations.degreeVec(0f, 0f, -42f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 42f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("left_leg",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, -42f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.33333f, KeyframeAnimations.degreeVec(0f, 0f, 42f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, -42f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("right_leg",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 42f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.33333f, KeyframeAnimations.degreeVec(0f, 0f, -42f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 42f),
				AnimationChannel.Interpolations.LINEAR))).build();
}
