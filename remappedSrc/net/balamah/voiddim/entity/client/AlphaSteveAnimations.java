package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class AlphaSteveAnimations {
    public static final Animation IDLE = Animation.Builder.create(0f).build();
    public static final Animation WALK = Animation.Builder.create(1f)
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
