package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class VoidHarbingerAnimations {
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(0f).build();
	public static final AnimationDefinition SHOOT = AnimationDefinition.Builder.withLength(0.29167f)
	.addAnimation("right_arm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(0f, 0f, 27.5f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("right_elbow",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.08333f, KeyframeAnimations.posVec(1f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("right_elbow",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(0f, 0f, 90f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(0f, 0f, 60f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("left_arm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(0f, 0f, 27.5f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("left_elbow",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR), 
			new Keyframe(0.08333f, KeyframeAnimations.posVec(1f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("left_elbow",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08333f, KeyframeAnimations.degreeVec(0f, 0f, 90f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.16667f, KeyframeAnimations.degreeVec(0f, 0f, 60f),
				AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition SUMMON = AnimationDefinition.Builder.withLength(0.9167666f)
	.addAnimation("right_arm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(0f, 0f, 117.5f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 140f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("right_elbow",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125f, KeyframeAnimations.degreeVec(0f, 0f, 27.5f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(0f, 0f, 32.5f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.3433333f, KeyframeAnimations.degreeVec(0f, 0f, 48.41f),
				AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition SUMMON_END = AnimationDefinition.Builder.withLength(0.375f)
	.addAnimation("right_arm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 140f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("right_elbow",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 48.41f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR))).build();
}
