package net.balamah.voiddim.entity.client;

import net.minecraft.client.render.entity.animation.*;

public class HerobrineAnimations {
	public static final AnimationDefinition SUMMON_PROJECTILE = AnimationDefinition.Builder.create(0.5f)
		.addBoneAnimation("head",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.125f, AnimationHelper.createRotationalVector(-25.77f, 13.73f, -6.06f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("right_arm",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.16667f, AnimationHelper.createRotationalVector(-127.07f, 8.53f, 0.69f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("left_arm",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.08333f, AnimationHelper.createRotationalVector(19.92f, 1.3f, -7.39f),
					Transformation.Interpolations.LINEAR))).build();
		public static final AnimationDefinition SPECIAL_ATTACK = AnimationDefinition.Builder.create(0.79167f)
		.addBoneAnimation("body",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.33333f, AnimationHelper.createRotationalVector(-5f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.41667f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("cape",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.33333f, AnimationHelper.createRotationalVector(1f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.41667f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("arms",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.33333f, AnimationHelper.createRotationalVector(-75f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.41667f, AnimationHelper.createRotationalVector(-22.5f, 0f, 0f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("right_arm",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.125f, AnimationHelper.createRotationalVector(-25.02f, -22.48f, -17.4f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("right_elbow",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.08333f, AnimationHelper.createRotationalVector(-30f, 0f, 0f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("left_arm",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.125f, AnimationHelper.createRotationalVector(-25.02f, 22.48f, 17.4f),
					Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("left_elbow",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
					Transformation.Interpolations.LINEAR),
				new Keyframe(0.08333f, AnimationHelper.createRotationalVector(-30f, 0f, 0f),
					Transformation.Interpolations.LINEAR))).build();

	public static final AnimationDefinition lightning_invoke = AnimationDefinition.Builder.create(0.75F)
		.addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4583F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.5F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.375F, AnimationHelper.createRotationalVector(9.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4167F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4583F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.625F, AnimationHelper.createRotationalVector(22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.375F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4583F, AnimationHelper.createRotationalVector(-167.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition ground_corruption = AnimationDefinition.Builder.create(0.875F)
		.addBoneAnimation("arms", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.625F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.75F, AnimationHelper.createRotationalVector(95.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(65.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.2083F, AnimationHelper.createRotationalVector(95.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.3333F, AnimationHelper.createRotationalVector(100.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4167F, AnimationHelper.createRotationalVector(115.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.625F, AnimationHelper.createRotationalVector(117.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.375F, AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("left_leg", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(42.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.625F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(-60.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.2083F, AnimationHelper.createRotationalVector(-95.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("right_leg", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.625F, AnimationHelper.createRotationalVector(-10.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.75F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.125F, AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.2083F, AnimationHelper.createRotationalVector(-92.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition SHOCKWAVE_INVOKE = AnimationDefinition.Builder.create(0.5417F)
		.addBoneAnimation("arms", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(-87.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -52.5F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4583F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 27.5F), Transformation.Interpolations.LINEAR)
		))
		.addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 45.0F), Transformation.Interpolations.LINEAR),
			new Keyframe(0.4583F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -17.5F), Transformation.Interpolations.LINEAR)
		))
		.build();
}
