package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.animation.*;

public class HollowedAlphaSteveAnimations {
    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.create(0f).build();
    public static final AnimationDefinition WALK = AnimationDefinition.Builder.create(1f)
        .addBoneAnimation("right_arm",
            new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0f, AnimationHelper.createRotationalVector(-60f, 0f, 0f), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5f, AnimationHelper.createRotationalVector(60f, 0f, 0f), Transformation.Interpolations.LINEAR),
                new Keyframe(1f, AnimationHelper.createRotationalVector(-60f, 0f, 0f), Transformation.Interpolations.LINEAR)
            ))
        .addBoneAnimation("left_arm",
            new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0f, AnimationHelper.createRotationalVector(60f, 0f, 0f), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5f, AnimationHelper.createRotationalVector(-60f, 0f, 0f), Transformation.Interpolations.LINEAR),
                new Keyframe(1f, AnimationHelper.createRotationalVector(60f, 0f, 0f), Transformation.Interpolations.LINEAR)
            ))
        .addBoneAnimation("right_leg",
            new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0f, AnimationHelper.createRotationalVector(60f, 0f, 0f), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5f, AnimationHelper.createRotationalVector(-60f, 0f, 0f), Transformation.Interpolations.LINEAR),
                new Keyframe(1f, AnimationHelper.createRotationalVector(60f, 0f, 0f), Transformation.Interpolations.LINEAR)
            ))
        .addBoneAnimation("left_leg",
            new Transformation(Transformation.Targets.ROTATE,
                new Keyframe(0f, AnimationHelper.createRotationalVector(-60f, 0f, 0f), Transformation.Interpolations.LINEAR),
                new Keyframe(0.5f, AnimationHelper.createRotationalVector(60f, 0f, 0f), Transformation.Interpolations.LINEAR),
                new Keyframe(1f, AnimationHelper.createRotationalVector(-60f, 0f, 0f), Transformation.Interpolations.LINEAR)
            ))
        .build();
}
