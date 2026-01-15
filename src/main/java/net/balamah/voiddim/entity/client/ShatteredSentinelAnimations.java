package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.animation.*;

public class ShatteredSentinelAnimations {
	public static final AnimationDefinition WALK = AnimationDefinition.Builder.create(0.6766666f).looping()
	.addBoneAnimation("left_leg",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(42f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.33333f, AnimationHelper.createRotationalVector(-42f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.5834334f, AnimationHelper.createRotationalVector(42f, 0f, 0f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("right_leg",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(-42f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.2916767f, AnimationHelper.createRotationalVector(42f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.5834334f, AnimationHelper.createRotationalVector(-42f, 0f, 0f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("left_arm",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(-42f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.2916767f, AnimationHelper.createRotationalVector(43f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.5834334f, AnimationHelper.createRotationalVector(-42f, 0f, 0f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("right_arm",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(42f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.2916767f, AnimationHelper.createRotationalVector(-42f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.5834334f, AnimationHelper.createRotationalVector(42f, 0f, 0f),
				Transformation.Interpolations.LINEAR)))
		.build();
	public static final AnimationDefinition ATTACK = AnimationDefinition.Builder.create(0.4167667f)
	.addBoneAnimation("arms",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.125f, AnimationHelper.createRotationalVector(-155f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.2916767f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR))).build();
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.create(1.0834333f).looping()
	.addBoneAnimation("stones",
		new Transformation(Transformation.Targets.MOVE_ORIGIN, 
			new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
				Transformation.Interpolations.CUBIC), 
			new Keyframe(0.5416766f, AnimationHelper.createTranslationalVector(0f, -1f, 0f),
				Transformation.Interpolations.CUBIC), 
			new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
				Transformation.Interpolations.CUBIC))).build();
	public static final AnimationDefinition STONES_FLOAT = AnimationDefinition.Builder.create(1.0834333f).looping()
		.addBoneAnimation("stones",
			new Transformation(Transformation.Targets.MOVE_ORIGIN, 
				new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
					Transformation.Interpolations.CUBIC), 
				new Keyframe(0.5416766f, AnimationHelper.createTranslationalVector(0f, -1f, 0f),
					Transformation.Interpolations.CUBIC), 
				new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
					Transformation.Interpolations.CUBIC))).build();
}
