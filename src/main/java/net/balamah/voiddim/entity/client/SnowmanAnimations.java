package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.animation.*;

public class SnowmanAnimations {
	public static final AnimationDefinition POSE = AnimationDefinition.Builder.create(0f)
		.addBoneAnimation("left_arm",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 72.5f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("right_arm",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -70f),
					Transformation.Interpolations.LINEAR))).build();

	public static final AnimationDefinition BASED = AnimationDefinition.Builder.create(1.41667f)
		.addBoneAnimation("left_arm",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 77.5f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.33333f, AnimationHelper.createRotationalVector(-41f, 40.53f, -55.72f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("right_arm",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, -77.5f),
					Transformation.Interpolations.LINEAR))).build();
	}
