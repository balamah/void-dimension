package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class AlphaSteveAnimations {
    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(0f).build();
    public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1f)
        .addAnimation("right_arm",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                new Keyframe(0.5f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                new Keyframe(1f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)
            ))
        .addAnimation("left_arm",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                new Keyframe(0.5f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                new Keyframe(1f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)
            ))
        .addAnimation("right_leg",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                new Keyframe(0.5f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                new Keyframe(1f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)
            ))
        .addAnimation("left_leg",
            new AnimationChannel(AnimationChannel.Targets.ROTATION,
                new Keyframe(0f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                new Keyframe(0.5f, KeyframeAnimations.degreeVec(60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                new Keyframe(1f, KeyframeAnimations.degreeVec(-60f, 0f, 0f), AnimationChannel.Interpolations.LINEAR)
            ))
        .build();
}
