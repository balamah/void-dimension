package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.animation.*;

public class VoidMawAnimations {
	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.create(0.7083434f)
	.addBoneAnimation("upper",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.08343333f, AnimationHelper.createRotationalVector(-22.5f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.2916767f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("lower",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.08343333f, AnimationHelper.createRotationalVector(22.5f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.2916767f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR))).build();

}
