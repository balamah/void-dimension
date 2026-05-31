package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class VoidHarbingerAnimations {
	public static final Animation IDLE = Animation.Builder.create(0f).build();
	public static final Animation SHOOT = Animation.Builder.create(0.29167f)
	.addBoneAnimation("right_arm",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.16667f, AnimationHelper.createRotationalVector(0f, 0f, 27.5f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("right_elbow",
		new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR), 
			new Keyframe(0.08333f, AnimationHelper.createTranslationalVector(1f, 0f, 0f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("right_elbow",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.08333f, AnimationHelper.createRotationalVector(0f, 0f, 90f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.16667f, AnimationHelper.createRotationalVector(0f, 0f, 60f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("left_arm",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.16667f, AnimationHelper.createRotationalVector(0f, 0f, 27.5f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("left_elbow",
		new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR), 
			new Keyframe(0.08333f, AnimationHelper.createTranslationalVector(1f, 0f, 0f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("left_elbow",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.08333f, AnimationHelper.createRotationalVector(0f, 0f, 90f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.16667f, AnimationHelper.createRotationalVector(0f, 0f, 60f),
				Transformation.Interpolations.LINEAR))).build();
	public static final Animation SUMMON = Animation.Builder.create(0.9167666f)
	.addBoneAnimation("right_arm",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.20834334f, AnimationHelper.createRotationalVector(0f, 0f, 117.5f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, 140f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("right_elbow",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.125f, AnimationHelper.createRotationalVector(0f, 0f, 27.5f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.20834334f, AnimationHelper.createRotationalVector(0f, 0f, 32.5f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.3433333f, AnimationHelper.createRotationalVector(0f, 0f, 48.41f),
				Transformation.Interpolations.LINEAR))).build();
	public static final Animation SUMMON_END = Animation.Builder.create(0.375f)
	.addBoneAnimation("right_arm",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 140f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.2916767f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR)))
	.addBoneAnimation("right_elbow",
		new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 48.41f),
				Transformation.Interpolations.LINEAR),
			new Keyframe(0.2916767f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
				Transformation.Interpolations.LINEAR))).build();
}
