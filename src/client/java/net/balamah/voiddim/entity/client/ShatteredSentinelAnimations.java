package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ShatteredSentinelAnimations {
	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(0.6766666f).looping()
	.addAnimation("left_leg",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.33333f, KeyframeAnimations.degreeVec(-42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5834334f, KeyframeAnimations.degreeVec(42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("right_leg",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(-42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5834334f, KeyframeAnimations.degreeVec(-42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("left_arm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(-42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(43f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5834334f, KeyframeAnimations.degreeVec(-42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
	.addAnimation("right_arm",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(-42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5834334f, KeyframeAnimations.degreeVec(42f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR)))
		.build();
	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.4167667f)
	.addAnimation("arms",
		new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.125f, KeyframeAnimations.degreeVec(-155f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.2916767f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(1.0834333f).looping()
	.addAnimation("stones",
		new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.CATMULLROM), 
			new Keyframe(0.5416766f, KeyframeAnimations.posVec(0f, -1f, 0f),
				AnimationChannel.Interpolations.CATMULLROM), 
			new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
				AnimationChannel.Interpolations.CATMULLROM))).build();
	public static final AnimationDefinition STONES_FLOAT = AnimationDefinition.Builder.withLength(1.0834333f).looping()
		.addAnimation("stones",
			new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f),
					AnimationChannel.Interpolations.CATMULLROM), 
				new Keyframe(0.5416766f, KeyframeAnimations.posVec(0f, -1f, 0f),
					AnimationChannel.Interpolations.CATMULLROM), 
				new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f),
					AnimationChannel.Interpolations.CATMULLROM))).build();
}
