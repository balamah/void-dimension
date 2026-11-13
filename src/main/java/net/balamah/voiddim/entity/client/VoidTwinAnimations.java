package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class VoidTwinAnimations {
	// Small helpers so the keyframe tables below stay readable. Values are exactly the exported ones.
	private static Keyframe rot(float t, float x, float y, float z) {
		return new Keyframe(t, KeyframeAnimations.degreeVec(x, y, z), AnimationChannel.Interpolations.LINEAR);
	}

	private static Keyframe pos(float t, float x, float y, float z) {
		return new Keyframe(t, KeyframeAnimations.posVec(x, y, z), AnimationChannel.Interpolations.LINEAR);
	}

	private static AnimationChannel rotation(Keyframe... keyframes) {
		return new AnimationChannel(AnimationChannel.Targets.ROTATION, keyframes);
	}

	private static AnimationChannel position(Keyframe... keyframes) {
		return new AnimationChannel(AnimationChannel.Targets.POSITION, keyframes);
	}

	public static final AnimationDefinition WALK_OLD = AnimationDefinition.Builder.withLength(1.125F).looping()
		.addAnimation("shared_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -20.0F, 0.0F, 0.0F),
			rot(0.75F, 27.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 7.5F, 0.0F, 0.0F),
			rot(0.75F, -57.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 20.0F, 0.0F, 0.0F),
			rot(0.75F, -20.0F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -32.5F, 0.0F, 0.0F),
			rot(0.75F, 32.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -32.5F, 0.0F, 0.0F),
			rot(0.75F, 32.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -17.5F, 0.0F, 0.0F),
			rot(0.75F, 17.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 20.0F, 0.0F, 0.0F),
			rot(0.75F, -20.0F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("bodies", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -17.5F, 0.0F, 0.0F),
			rot(0.75F, 17.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition WALK_FAIL = AnimationDefinition.Builder.withLength(3.2917F).looping()
		.addAnimation("shared_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 47.5F, 0.0F, 0.0F),
			rot(0.75F, -40.0F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.4583F, -30.0F, 0.0F, 0.0F),
			rot(1.8333F, 15.0F, 0.0F, 0.0F),
			rot(2.1667F, 0.0F, 0.0F, 0.0F),
			rot(2.5417F, 57.5F, 0.0F, 0.0F),
			rot(2.9167F, -35.0F, 0.0F, 0.0F),
			rot(3.25F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 7.5F, 0.0F, 0.0F),
			rot(0.75F, -57.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.4583F, 7.5F, 0.0F, 0.0F),
			rot(1.8333F, -57.5F, 0.0F, 0.0F),
			rot(2.1667F, 0.0F, 0.0F, 0.0F),
			rot(2.5417F, 7.5F, 0.0F, 0.0F),
			rot(2.9167F, -57.5F, 0.0F, 0.0F),
			rot(3.25F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -88.1791F, -27.3418F, -41.4831F),
			rot(0.75F, 76.2816F, -25.0644F, 32.1671F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.4583F, 20.0F, 0.0F, 0.0F),
			rot(1.8333F, -65.0081F, -4.6219F, -44.0089F),
			rot(2.1667F, 0.0F, 0.0F, 0.0F),
			rot(2.5417F, 10.4666F, -7.8848F, -7.9019F),
			rot(2.9167F, -33.3687F, -14.2511F, -26.3945F),
			rot(3.25F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -32.5F, 0.0F, 0.0F),
			rot(0.75F, 32.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.4583F, -32.5F, 0.0F, 0.0F),
			rot(1.8333F, 32.5F, 0.0F, 0.0F),
			rot(2.1667F, 0.0F, 0.0F, 0.0F),
			rot(2.5417F, -32.5F, 0.0F, 0.0F),
			rot(2.9167F, 32.5F, 0.0F, 0.0F),
			rot(3.25F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -32.5F, 0.0F, 0.0F),
			rot(0.75F, 32.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.4583F, -32.5F, 0.0F, 0.0F),
			rot(1.8333F, 32.5F, 0.0F, 0.0F),
			rot(2.1667F, 0.0F, 0.0F, 0.0F),
			rot(2.5417F, -32.5F, 0.0F, 0.0F),
			rot(2.9167F, 32.5F, 0.0F, 0.0F),
			rot(3.25F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -17.5F, 0.0F, 0.0F),
			rot(0.75F, 17.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.4583F, -17.5F, 0.0F, 0.0F),
			rot(1.8333F, 17.5F, 0.0F, 0.0F),
			rot(2.1667F, 0.0F, 0.0F, 0.0F),
			rot(2.5417F, -17.5F, 0.0F, 0.0F),
			rot(2.9167F, 17.5F, 0.0F, 0.0F),
			rot(3.25F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 20.0F, 0.0F, 0.0F),
			rot(0.75F, -20.0F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.4583F, 20.0F, 0.0F, 0.0F),
			rot(1.8333F, 0.0F, 0.0F, 0.0F),
			rot(2.1667F, 0.0F, 0.0F, 0.0F),
			rot(2.5417F, -37.5F, 0.0F, 0.0F),
			rot(2.9167F, 40.0F, 0.0F, 0.0F),
			rot(3.25F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("bodies", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -17.5F, 0.0F, 0.0F),
			rot(0.75F, 17.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.4583F, -17.5F, 0.0F, 0.0F),
			rot(1.8333F, 17.5F, 0.0F, 0.0F),
			rot(2.1667F, 0.0F, 0.0F, 0.0F),
			rot(2.5417F, -17.5F, 0.0F, 0.0F),
			rot(2.9167F, 17.5F, 0.0F, 0.0F),
			rot(3.25F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(2.25F).looping()
		.addAnimation("shared_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -20.0F, 0.0F, 0.0F),
			rot(0.75F, 27.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.5F, 47.5F, 0.0F, 0.0F),
			rot(1.875F, -47.5F, 0.0F, 0.0F),
			rot(2.2083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("shared_leg", position(
			pos(0.0F, 0.0F, 0.0F, 0.0F),
			pos(1.5F, 0.0F, -1.25F, 0.75F),
			pos(2.2083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 7.5F, 0.0F, 0.0F),
			rot(0.75F, -57.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.5F, 7.5F, 0.0F, 0.0F),
			rot(1.875F, -57.5F, 0.0F, 0.0F),
			rot(2.2083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 20.0F, 0.0F, 0.0F),
			rot(0.75F, -20.0F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.5F, -0.4002F, -13.8855F, -9.8114F),
			rot(1.875F, 73.2558F, -16.0526F, 31.9769F),
			rot(2.2083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -32.5F, 0.0F, 0.0F),
			rot(0.75F, 32.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.5F, -32.5F, 0.0F, 0.0F),
			rot(1.875F, 32.5F, 0.0F, 0.0F),
			rot(2.2083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -32.5F, 0.0F, 0.0F),
			rot(0.75F, 32.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.5F, -32.5F, 0.0F, 0.0F),
			rot(1.875F, 32.5F, 0.0F, 0.0F),
			rot(2.2083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -17.5F, 0.0F, 0.0F),
			rot(0.75F, 17.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.5F, -17.5F, 0.0F, 0.0F),
			rot(1.875F, 17.5F, 0.0F, 0.0F),
			rot(2.2083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 20.0F, 0.0F, 0.0F),
			rot(0.75F, -20.0F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.5F, -52.5F, 0.0F, 0.0F),
			rot(1.875F, 40.0F, 0.0F, 0.0F),
			rot(2.2083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("bodies", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -17.5F, 0.0F, 0.0F),
			rot(0.75F, 17.5F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F),
			rot(1.5F, -17.5F, 0.0F, 0.0F),
			rot(1.875F, 17.5F, 0.0F, 0.0F),
			rot(2.2083F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition ATTACK_1 = AnimationDefinition.Builder.withLength(0.5F)
		.addAnimation("left_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, -30.0F, 0.0F, 0.0F),
			rot(0.25F, 12.5F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, -20.0F, 0.0F, 0.0F),
			rot(0.25F, -72.1386F, -36.3088F, -17.6961F),
			rot(0.2917F, -76.5812F, -29.9559F, -3.6674F),
			rot(0.4167F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_arm", position(
			pos(0.0F, 0.0F, 0.0F, 0.0F),
			pos(0.125F, 0.0F, 0.0F, 0.5F),
			pos(0.25F, 0.75F, -2.25F, -2.5F),
			pos(0.2917F, 3.25F, -2.25F, -2.5F),
			pos(0.4167F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, 10.0F, 0.0F, 0.0F),
			rot(0.25F, -42.5F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("bodies", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, -12.5F, 10.0F, 0.0F),
			rot(0.2917F, 15.0F, -12.5F, 0.0F),
			rot(0.3333F, 11.95F, -21.622F, -2.6034F),
			rot(0.4583F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition ATTACK_2 = AnimationDefinition.Builder.withLength(0.5F)
		.addAnimation("shared_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, 17.5F, 0.0F, 0.0F),
			rot(0.2917F, -12.5F, 0.0F, 0.0F),
			rot(0.4583F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, 20.0F, 0.0F, 0.0F),
			rot(0.2917F, -50.0F, 0.0F, 0.0F),
			rot(0.4583F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, 27.5F, 0.0F, 0.0F),
			rot(0.2917F, -68.2151F, 34.1825F, -3.2253F),
			rot(0.3333F, -80.0184F, 43.3684F, 1.8607F),
			rot(0.4583F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_arm", position(
			pos(0.0F, 0.0F, 0.0F, 0.0F),
			pos(0.125F, 0.0F, 0.5F, 0.0F),
			pos(0.2917F, 0.0F, -2.75F, 0.0F),
			pos(0.3333F, -0.5F, -2.25F, -1.0F),
			pos(0.4583F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_head", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, -32.5F, 0.0F, 0.0F),
			rot(0.2917F, 2.5F, 0.0F, 0.0F),
			rot(0.4583F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("bodies", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, -20.0F, -25.0F, 0.0F),
			rot(0.2917F, 11.8133F, 16.6302F, 8.7782F),
			rot(0.3333F, 19.5697F, 16.2782F, 10.0847F),
			rot(0.4583F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition ATTACK_3 = AnimationDefinition.Builder.withLength(0.6667F)
		.addAnimation("shared_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.1667F, 25.0F, 0.0F, 0.0F),
			rot(0.375F, -27.5F, 0.0F, 0.0F),
			rot(0.4167F, -42.5F, 0.0F, 0.0F),
			rot(0.5417F, 2.5F, 0.0F, 0.0F)))
		.addAnimation("left_body", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.1667F, 0.0F, 2.5F, 0.0F),
			rot(0.375F, 0.0F, -17.5F, 0.0F),
			rot(0.4167F, 0.0F, -25.0F, 0.0F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.1667F, 25.0F, 0.0F, 25.0F),
			rot(0.375F, -77.5014F, -21.469F, 11.8757F),
			rot(0.4167F, -82.9808F, -57.5077F, -2.321F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_arm", position(
			pos(0.0F, 0.0F, 0.0F, 0.0F),
			pos(0.1667F, 0.0F, 1.0F, 0.0F),
			pos(0.375F, 0.0F, -4.0F, 0.0F),
			pos(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_body", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.1667F, 0.0F, -2.5F, 0.0F),
			rot(0.375F, 0.0F, 20.0F, 0.0F),
			rot(0.4167F, 0.0F, 25.0F, 0.0F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_arm", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.1667F, 25.0F, 0.0F, -27.5F),
			rot(0.375F, -54.3065F, 14.888F, 6.6168F),
			rot(0.4167F, -60.6879F, 36.1798F, 26.1146F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_arm", position(
			pos(0.0F, 0.0F, 0.0F, 0.0F),
			pos(0.1667F, 0.0F, 0.75F, 0.0F),
			pos(0.375F, 0.0F, -4.5F, 0.0F),
			pos(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("bodies", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.1667F, -20.0F, 0.0F, 0.0F),
			rot(0.375F, 32.5F, 0.0F, 0.0F),
			rot(0.4167F, 40.0F, 0.0F, 0.0F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.build();
}
