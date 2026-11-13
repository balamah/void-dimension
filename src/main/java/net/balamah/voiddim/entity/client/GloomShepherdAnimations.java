package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class GloomShepherdAnimations {
    private static AnimationChannel rotation(Keyframe... keyframes) {
        return new AnimationChannel(AnimationChannel.Targets.ROTATION, keyframes);
    }

    private static AnimationChannel position(Keyframe... keyframes) {
        return new AnimationChannel(AnimationChannel.Targets.POSITION, keyframes);
    }

    private static Keyframe rot(float time, float x, float y, float z) {
        return new Keyframe(
            time,
            KeyframeAnimations.degreeVec(x, y, z),
            AnimationChannel.Interpolations.LINEAR
        );
    }

    private static Keyframe pos(float time, float x, float y, float z) {
        return new Keyframe(
            time,
            KeyframeAnimations.posVec(x, y, z),
            AnimationChannel.Interpolations.LINEAR
        );
    }

    public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1.0833F)
        .looping()
        .addAnimation("body", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.3333F, -27.5F, 0.0F, 0.0F),
            rot(0.4167F, -20.83F, 0.0F, 0.0F),
            rot(0.625F, 29.8718F, -13.5967F, -15.0095F),
            rot(0.7083F, 30.5378F, -10.9036F, -14.0396F),
            rot(0.7917F, 25.3585F, -7.2691F, -9.3597F),
            rot(0.9583F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("right_arm", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.3333F, -22.5F, 0.0F, 0.0F),
            rot(0.4167F, -20.83F, 0.0F, 0.0F),
            rot(0.625F, -31.2482F, 22.2343F, -3.6874F),
            rot(0.7917F, -25.6241F, 11.1172F, -1.8437F),
            rot(0.9583F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("head", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.4167F, -30.0F, 0.0F, 0.0F),
            rot(0.625F, 7.5F, 0.0F, 0.0F),
            rot(0.7083F, 7.5F, 0.0F, 0.0F),
            rot(0.9583F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("right_leg", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.3333F, -17.5F, 0.0F, 0.0F),
            rot(0.4167F, -25.0F, 0.0F, 0.0F),
            rot(0.625F, 0.0F, 0.0F, 0.0F),
            rot(0.9583F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("right_calf", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.9583F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("left_leg", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.4167F, 17.5F, 0.0F, 0.0F),
            rot(0.75F, 22.5F, 0.0F, 0.0F),
            rot(0.8333F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("left_arm", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.4167F, -22.5F, 0.0F, 0.0F),
            rot(0.625F, 5.0F, 0.0F, 0.0F),
            rot(1.0F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("right_forward_skirt_pieces", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.3333F, -17.5F, 0.0F, 0.0F),
            rot(0.4167F, -28.33F, 0.0F, 0.0F),
            rot(0.625F, 0.0F, 0.0F, 0.0F),
            rot(0.9583F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("right_backward_skirt_piece", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("left_forward_skirt_piece", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.4167F, 12.5F, 0.0F, 0.0F),
            rot(0.8333F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("left_backward_skirt_piece", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.4167F, 17.5F, 0.0F, 0.0F),
            rot(0.75F, 22.5F, 0.0F, 0.0F),
            rot(0.8333F, 0.0F, 0.0F, 0.0F)
        ))
        .build();

    public static final AnimationDefinition SUMMON = AnimationDefinition.Builder.withLength(1.75F)
        .addAnimation("body", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.25F, -27.5F, 0.0F, 0.0F),
            rot(0.7083F, -45.0F, 0.0F, 0.0F),
            rot(0.7917F, -10.0F, 0.0F, 0.0F),
            rot(1.4167F, -10.0F, 0.0F, 0.0F),
            rot(1.6667F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("arms", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.2917F, 5.0F, 0.0F, 0.0F),
            rot(0.7083F, -12.5F, 0.0F, 0.0F),
            rot(0.7917F, 37.5F, 0.0F, 0.0F),
            rot(1.4167F, 37.5F, 0.0F, 0.0F),
            rot(1.6667F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("right_arm", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.25F, -35.6758F, -26.4523F, 7.6161F),
            rot(1.4167F, -35.6758F, -26.4523F, 7.6161F),
            rot(1.6667F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("right_elbow", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.3333F, 20.0F, 0.0F, 0.0F),
            rot(1.4167F, 20.0F, 0.0F, 0.0F),
            rot(1.6667F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("head", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.0833F, -35.0F, -27.5F, 0.0F),
            rot(0.2083F, -57.0029F, 28.9935F, -4.8129F),
            rot(0.375F, -56.8836F, -40.4167F, 16.169F),
            rot(0.5833F, -37.0079F, 7.3526F, -20.1347F),
            rot(0.7083F, -27.6613F, -34.3304F, 6.0326F),
            rot(0.7917F, 9.2016F, -6.5756F, -4.7254F),
            rot(1.6667F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("left_arm", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.2083F, -121.7607F, 20.6902F, -9.0623F),
            rot(1.4167F, -121.7607F, 20.6902F, -9.0623F),
            rot(1.6667F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("rod", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.7083F, -11.1815F, 11.2143F, 23.5443F),
            rot(0.7917F, -9.3493F, 12.0769F, 25.3554F),
            rot(1.4167F, -9.3493F, 12.0769F, 25.3554F),
            rot(1.6667F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("rod", position(
            pos(0.0F, 0.0F, 0.0F, 0.0F),
            pos(0.7083F, 0.0F, 0.0F, -0.25F),
            pos(0.7917F, 0.0F, 0.0F, -0.5F),
            pos(1.4167F, 0.0F, 0.0F, -0.5F),
            pos(1.6667F, 0.0F, 0.0F, 0.0F)
        ))
        .build();

    public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.withLength(0.75F)
        .addAnimation("body", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.25F, -30.0172F, 14.8787F, -1.9095F),
            rot(0.3333F, -44.8284F, 6.9435F, -2.842F),
            rot(0.375F, 3.6937F, -26.6038F, -11.8058F),
            rot(0.4583F, 89.6716F, -58.2876F, -111.214F),
            rot(0.5833F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("right_arm", rotation(
            rot(0.0F, 0.0F, 0.0F, 0.0F),
            rot(0.25F, -36.8198F, 24.0653F, -52.4445F),
            rot(0.2917F, -54.3198F, 24.0653F, -52.4445F),
            rot(0.375F, 60.6802F, 24.0653F, -52.4445F),
            rot(0.4583F, 45.3429F, 14.6088F, -84.7366F),
            rot(0.5833F, 0.0F, 0.0F, 0.0F)
        ))
        .addAnimation("rod", position(
            pos(0.0F, 0.0F, 0.0F, 0.0F),
            pos(0.25F, 0.25F, 9.0F, -9.25F),
            pos(0.4583F, -0.25F, 13.25F, -14.75F),
            pos(0.5833F, 0.0F, 0.0F, 0.0F)
        ))
        .build();
}
