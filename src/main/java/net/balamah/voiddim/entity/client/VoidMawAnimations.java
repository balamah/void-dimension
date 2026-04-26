package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class VoidMawAnimations {
	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.7083434f)
	.addAnimation("upper",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08343333f, KeyframeAnimations.degreeVec(-22.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("lower",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.08343333f, KeyframeAnimations.degreeVec(22.5f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR))).build();

}
