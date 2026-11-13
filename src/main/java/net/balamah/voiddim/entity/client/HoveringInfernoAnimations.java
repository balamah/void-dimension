package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class HoveringInfernoAnimations {
    private static AnimationChannel rotation(Keyframe... keyframes) {
        return new AnimationChannel(AnimationChannel.Targets.ROTATION, keyframes);
    }

    private static AnimationChannel position(Keyframe... keyframes) {
        return new AnimationChannel(AnimationChannel.Targets.POSITION, keyframes);
    }

    private static AnimationChannel scale(Keyframe... keyframes) {
        return new AnimationChannel(AnimationChannel.Targets.SCALE, keyframes);
    }

    private static Keyframe rot(float time, float x, float y, float z) {
        return new Keyframe(time, KeyframeAnimations.degreeVec(x, y, z),
            AnimationChannel.Interpolations.LINEAR);
    }

    private static Keyframe pos(float time, float x, float y, float z) {
        return new Keyframe(time, KeyframeAnimations.posVec(x, y, z),
            AnimationChannel.Interpolations.LINEAR);
    }

    private static Keyframe scaleKey(float time, float x, float y, float z) {
        return new Keyframe(time, KeyframeAnimations.scaleVec(x, y, z),
            AnimationChannel.Interpolations.LINEAR);
    }

    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(1.0417F)
        .looping()
        .addAnimation("shields", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.25F, 0.0F, -90.0F, 0.0F),
            rot(0.5F, 0.0F, -180.0F, 0.0F),
            rot(0.75F, 0.0F, -270.0F, 0.0F),
            rot(1.0F, 0.0F, -360.0F, 0.0F)
        ))
        .build();

    public static final AnimationDefinition PHASE_2_TRANSITION = AnimationDefinition.Builder.withLength(0.3333F)
        .addAnimation("shield_2", position(
            pos(0.0F, 0.0F, 0.0F, 0.0F),
            pos(0.125F, 0.0F, 0.0F, 0.25F),
            pos(0.2917F, 0.0F, 0.0F, -8.75F)
        ))
        .addAnimation("shield_2", scale(
            scaleKey(0.0F, 1.0F, 1.0F, 1.0F),
            scaleKey(0.125F, 0.2F, 1.0F, 1.0F),
            scaleKey(0.2083F, 0.2F, 0.1F, 1.0F),
            scaleKey(0.25F, 0.0F, -0.1F, 1.0F)
        ))
        .build();

    public static final AnimationDefinition PHASE_3_TRANSITION = AnimationDefinition.Builder.withLength(0.4167F)
        .addAnimation("shield_3", position(
            pos(0.0F, 0.0F, 0.0F, 0.0F),
            pos(0.2083F, -1.0F, 0.0F, 0.0F),
            pos(0.3333F, 2.0F, 0.0F, 0.0F),
            pos(0.375F, 9.5F, 23.0F, 0.0F)
        ))
        .addAnimation("shield_3", scale(
            scaleKey(0.0F, 1.0F, 1.0F, 1.0F),
            scaleKey(0.125F, 1.0F, 1.0F, 0.4F),
            scaleKey(0.2083F, 0.5F, 0.0F, 0.4F),
            scaleKey(0.375F, 0.1F, 0.0F, 0.4F)
        ))
        .addAnimation("shield_2", position(
            pos(0.0F, 0.0F, 0.0F, -3.0F)
        ))
        .addAnimation("shield_2", scale(
            scaleKey(0.0F, 0.0F, 0.0F, 1.0F)
        ))
        .build();

    public static final AnimationDefinition PHASE_4_TRANSITION = AnimationDefinition.Builder.withLength(0.4167F)
        .addAnimation("shield_4", position(
            pos(0.0F, 0.0F, 0.0F, 0.0F),
            pos(0.125F, 1.25F, 0.0F, 0.0F),
            pos(0.3333F, -7.75F, 21.0F, 0.0F)
        ))
        .addAnimation("shield_4", scale(
            scaleKey(0.0F, 1.0F, 1.0F, 1.0F),
            scaleKey(0.125F, 1.0F, 1.0F, 0.2F),
            scaleKey(0.2083F, 1.0F, 0.0F, 0.2F)
        ))
        .addAnimation("shield_3", position(
            pos(0.0F, 9.0F, 0.0F, 0.0F),
            pos(0.375F, 9.0F, 22.0F, 0.0F)
        ))
        .addAnimation("shield_3", scale(
            scaleKey(0.0F, 1.0F, -0.1F, 0.0F)
        ))
        .addAnimation("shield_2", position(
            pos(0.0F, 0.0F, 0.0F, -9.75F)
        ))
        .addAnimation("shield_2", scale(
            scaleKey(0.0F, 0.0F, 0.1F, 1.0F)
        ))
        .build();

    public static final AnimationDefinition PHASE_5_TRANSITION = AnimationDefinition.Builder.withLength(0.375F)
        .addAnimation("shield_4", position(
            pos(0.0F, -7.75F, 24.0F, 0.0F)
        ))
        .addAnimation("shield_4", scale(
            scaleKey(0.0F, 1.0F, -0.3F, 0.2F)
        ))
        .addAnimation("shield_3", position(
            pos(0.0F, 9.0F, 21.0F, 0.0F)
        ))
        .addAnimation("shield_3", scale(
            scaleKey(0.0F, 1.0F, -0.1F, 0.0F)
        ))
        .addAnimation("shield_2", position(
            pos(0.0F, 0.0F, 0.0F, -9.75F)
        ))
        .addAnimation("shield_2", scale(
            scaleKey(0.0F, 0.0F, 0.1F, 1.0F)
        ))
        .addAnimation("shield_1", position(
            pos(0.0F, 0.0F, 0.0F, 0.0F),
            pos(0.125F, 0.0F, 0.0F, -1.0F),
            pos(0.2083F, 0.0F, 24.0F, 8.0F)
        ))
        .addAnimation("shield_1", scale(
            scaleKey(0.0F, 1.0F, 1.0F, 1.0F),
            scaleKey(0.125F, 0.4F, 1.0F, 1.0F),
            scaleKey(0.2083F, 0.4F, 0.0F, 1.0F)
        ))
        .build();

    public static final AnimationDefinition STUN = AnimationDefinition.Builder.withLength(0.3333F)
        .addAnimation("head", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.125F, 20.0F, 0.0F, 0.0F)
        ))
        .addAnimation("shield_4", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.2083F, 0.0F, 0.0F, 42.5F),
            rot(0.2917F, 0.0F, 0.0F, 47.5F)
        ))
        .addAnimation("shield_3", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.2083F, 0.0F, 0.0F, -35.0F),
            rot(0.2917F, 0.0F, 0.0F, -40.0F)
        ))
        .addAnimation("shield_2", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.2083F, -32.5F, 0.0F, 0.0F),
            rot(0.2917F, -42.5F, 0.0F, 0.0F)
        ))
        .addAnimation("shield_1", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.2083F, 40.0F, 0.0F, 0.0F),
            rot(0.2917F, 52.5F, 0.0F, 0.0F)
        ))
        .addAnimation("crown", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.2083F, 10.0F, 0.0F, 0.0F)
        ))
        .build();

    public static final AnimationDefinition RECOVER = AnimationDefinition.Builder.withLength(0.9583F)
        .addAnimation("shields", position(
            pos(0.0F, 0.0F, 0.0F, 0.0F),
            pos(0.25F, 0.0F, -0.75F, 0.0F),
            pos(0.625F, 0.0F, 9.63F, 0.0F),
            pos(0.6667F, 0.0F, 11.0F, 0.0F),
            pos(0.7083F, 0.0F, -2.0F, 0.0F),
            pos(0.9167F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("head", rotation(
            rot(0.0F, 20.0F, 0.0F, 0.0F),
            rot(0.25F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("shield_4", rotation(
            rot(0.0F, 0.0F, 0.0F, 47.5F),
            rot(0.25F, 0.0F, 0.0F, 9.0F),
            rot(0.875F, 0.0F, 0.0F, 9.0F),
            rot(0.9167F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("shield_3", rotation(
            rot(0.0F, 0.0F, 0.0F, -40.0F),
            rot(0.25F, 0.0F, 0.0F, -9.0F),
            rot(0.875F, 0.0F, 0.0F, -9.0F),
            rot(0.9167F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("shield_2", rotation(
            rot(0.0F, -42.5F, 0.0F, 0.0F),
            rot(0.25F, -9.0F, 0.0F, 0.0F),
            rot(0.875F, -9.0F, 0.0F, 0.0F),
            rot(0.9167F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("shield_1", rotation(
            rot(0.0F, 52.5F, 0.0F, 0.0F),
            rot(0.25F, 9.0F, 0.0F, 0.0F),
            rot(0.875F, 9.0F, 0.0F, 0.0F),
            rot(0.9167F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("crown", rotation(
            rot(0.0F, 10.0F, 0.0F, 0.0F),
            rot(0.25F, 0.0F, 0.0F, 0.0F),
            rot(0.2917F, -5.0F, 0.0F, 0.0F),
            rot(0.4167F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("crown", position(
            pos(0.0F, 0.0F, 0.0F, 0.0F),
            pos(0.2917F, 0.0F, 0.0F, -0.25F),
            pos(0.5F, 0.0F, 0.0F, 0.0F)
        ))
        .build();
}
