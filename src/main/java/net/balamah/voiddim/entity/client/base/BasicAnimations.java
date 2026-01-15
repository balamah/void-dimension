package net.balamah.voiddim.entity.client.base;

import net.minecraft.client.render.entity.animation.*;

public class BasicAnimations {
	public static final AnimationDefinition WALK = AnimationDefinition.Builder.create(0.7916766f).looping()
	.addBoneAnimation("left_arm",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -42.5f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.33333f, AnimationHelper.createRotationalVector(0f, 0f, 42f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, -42f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("right_arm",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 40f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.33333f, AnimationHelper.createRotationalVector(0f, 0f, -42f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 42f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("left_leg",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -42f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.33333f, AnimationHelper.createRotationalVector(0f, 0f, 42f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, -42f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("right_leg",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 42f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.33333f, AnimationHelper.createRotationalVector(0f, 0f, -42f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 42f),
				Transformation.Interpolations.LINEAR))).build();
}
