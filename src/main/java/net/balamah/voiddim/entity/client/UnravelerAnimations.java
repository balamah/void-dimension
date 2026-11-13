package net.balamah.voiddim.entity.client;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class UnravelerAnimations {
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

	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1.2083F).looping()
		.addAnimation("right_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.3333F, -30.0F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("body", position(
			pos(0.0F, 0.0F, 0.0F, 0.0F),
			pos(0.3333F, 0.0F, 1.0F, 0.0F),
			pos(0.4167F, 0.0F, -0.75F, 0.0F),
			pos(0.5417F, 0.0F, 0.0F, 0.0F),
			pos(0.9167F, 0.0F, 1.0F, 0.0F),
			pos(1.0417F, 0.0F, -1.0F, 0.0F),
			pos(1.1667F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("right_lower_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.3333F, 32.5F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.5417F, 10.0F, 0.0F, 0.0F),
			rot(0.9167F, -30.0F, 0.0F, 0.0F),
			rot(1.0417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("left_lower_leg", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.9167F, 25.0F, 0.0F, 0.0F),
			rot(1.0417F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition TERRAIN_SHIFT_1 = AnimationDefinition.Builder.withLength(0.7917F)
		.addAnimation("claw_left_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, 13.9764F, -5.8849F, 118.904F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, 0.0F, 0.0F, 110.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claws_left", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 0.0F, 20.0F),
			rot(0.375F, 0.0F, 0.0F, 155.0F),
			rot(0.5417F, 0.0F, 0.0F, 170.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claws_right", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 0.0F, 12.5F),
			rot(0.375F, 0.0F, 0.0F, 180.0F),
			rot(0.5417F, 0.0F, 0.0F, 202.5F),
			rot(0.625F, 0.0F, 0.0F, 0.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, 0.0F, 0.0F, -10.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, -4.5674F, -14.3006F, -106.6713F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, 1.0179F, -16.1198F, -35.2218F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, 0.0F, 0.0F, 47.5F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_1_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, 0.0F, 0.0F, 30.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, 0.0F, -2.5F, -40.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end1_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, 0.0F, 0.0F, 82.5F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end2_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.0417F, -51.5822F, 22.4727F, 30.7287F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end2_tip", position(
			pos(0.0F, 0.0F, 0.0F, 0.0F),
			pos(0.0417F, 0.0F, 0.75F, 0.25F),
			pos(0.7083F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition TERRAIN_SHIFT_2 = AnimationDefinition.Builder.withLength(1.1667F)
		.addAnimation("claw_left_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, 12.5F),
			rot(0.4583F, 0.0F, 0.0F, -15.0F),
			rot(0.9167F, 0.0F, 0.0F, -22.5F),
			rot(1.0F, 0.0F, 0.0F, -15.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, 32.5F),
			rot(0.4583F, 0.0F, 0.0F, -20.0F),
			rot(0.9167F, 0.0F, 0.0F, -25.0F),
			rot(1.0F, 0.0F, 0.0F, -20.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, 42.5F),
			rot(1.0F, 0.0F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4167F, -2.103F, 42.0057F, 57.5374F),
			rot(0.4583F, -16.8243F, -22.1303F, -24.7558F),
			rot(1.0F, -16.8243F, -22.1303F, -24.7558F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, -22.5F),
			rot(0.4583F, 0.0F, 0.0F, 17.5F),
			rot(0.9167F, 0.0F, 0.0F, 30.0F),
			rot(1.0F, 0.0F, 0.0F, 17.5F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, -50.0F),
			rot(0.4583F, 0.0F, 0.0F, 20.0F),
			rot(0.9167F, 0.0F, 0.0F, 37.5F),
			rot(1.0F, 0.0F, 0.0F, 20.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("torso", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.4167F, 0.0F, 0.0F, -15.0F),
			rot(0.4583F, 0.0F, 0.0F, 180.0F),
			rot(0.9167F, 0.0F, 0.0F, 202.5F),
			rot(1.0F, 0.0F, 0.0F, 180.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end1_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.9167F, 0.0F, 0.0F, -40.0F),
			rot(1.0F, 0.0F, 0.0F, 0.0F),
			rot(1.0833F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition ATTACK_1 = AnimationDefinition.Builder.withLength(0.625F)
		.addAnimation("body", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -10.0F, 0.0F, 0.0F),
			rot(0.3333F, 5.0F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.125F, 0.0F, 0.0F, 32.5F),
			rot(0.1667F, -145.1836F, 33.4862F, -121.4143F),
			rot(0.25F, -174.6616F, 35.6743F, -154.3431F),
			rot(0.3333F, -52.444F, -8.7862F, -44.7157F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 20.0F, 0.0F),
			rot(0.3333F, 0.0F, -40.0F, 0.0F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -57.5F, 0.0F, 0.0F),
			rot(0.3333F, -1.3348F, -16.6082F, -52.2159F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -32.5F, 0.0F, 0.0F),
			rot(0.3333F, 12.887F, 66.9219F, 66.4421F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 0.0F, -20.0F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, -37.5F, 0.0F),
			rot(0.3333F, -50.9268F, 15.3455F, 27.9267F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -27.5F, 0.0F, 0.0F),
			rot(0.3333F, -29.9561F, -5.962F, -8.3986F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("chain_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 10.0F, 0.0F, 0.0F),
			rot(0.3333F, -52.5F, 0.0F, 0.0F),
			rot(0.5833F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -7.5F, 0.0F, 0.0F),
			rot(0.3333F, -70.0F, 0.0F, 0.0F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_1_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.1667F, 0.0F, 0.0F, -20.0F),
			rot(0.5417F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("chain_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, -30.0F, 0.0F),
			rot(0.3333F, -120.0F, -30.0F, 0.0F),
			rot(0.5833F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition ATTACK_2 = AnimationDefinition.Builder.withLength(0.75F)
		.addAnimation("body", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -5.0F, 0.0F, 0.0F),
			rot(0.4583F, 12.5F, 0.0F, 0.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, -70.0F, 0.0F),
			rot(0.375F, -82.7251F, -52.3507F, 83.1661F),
			rot(0.4167F, 85.647F, -37.4222F, -86.4352F),
			rot(0.5F, 120.6223F, -30.7491F, -130.1783F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2083F, 0.0F, 0.0F, 127.5F),
			rot(0.5F, 0.0F, 0.0F, 87.5F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 25.0F, 0.0F),
			rot(0.375F, 0.0F, 52.5F, 0.0F),
			rot(0.5F, 27.5F, 7.5F, 0.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -35.0F, 0.0F, 0.0F),
			rot(0.375F, -75.0F, 0.0F, 0.0F),
			rot(0.5F, -65.4937F, -38.9106F, -18.5404F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -15.0F, 0.0F, 0.0F),
			rot(0.375F, -27.5932F, 6.1224F, 22.2037F),
			rot(0.5F, -3.4185F, -43.7552F, -11.5826F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.5F, 0.0F, 0.0F, -12.5F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 0.0F, 10.0F)))
		.addAnimation("claw_right_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2083F, 0.0F, -12.5F, 0.0F),
			rot(0.375F, 0.0F, -37.5F, 0.0F),
			rot(0.4167F, 40.0F, 17.5F, 0.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2083F, -75.0F, 0.0F, 0.0F),
			rot(0.4583F, -73.9945F, -17.3245F, 2.6776F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, -22.5F, 0.0F),
			rot(0.375F, 0.0F, -80.0F, 0.0F),
			rot(0.4583F, -37.5F, 22.5F, 0.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -42.5F, 0.0F, 0.0F),
			rot(0.4583F, -38.6801F, 9.3663F, 9.1238F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("chain_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 17.5F, 0.0F, 0.0F),
			rot(0.4167F, -20.0F, 0.0F, 0.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.5F, 0.0F, 0.0F, 17.5F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_1_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.1667F, 0.0F, 0.0F, 0.38F),
			rot(0.4167F, 0.0F, 0.0F, 32.5F),
			rot(0.5F, 0.0F, 0.0F, 0.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("chain_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 42.2087F, -29.1895F, -7.3429F),
			rot(0.4167F, -55.2913F, -29.1895F, -7.3429F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 0.0F, 0.0F, -45.0F),
			rot(0.4167F, 0.0F, 0.0F, -2.5F),
			rot(0.4583F, 0.0F, 0.0F, -27.5F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end1_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 0.0F, 20.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end2_tip", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 0.0F, 10.0F),
			rot(0.5F, 180.0F, -87.5F, -160.0F),
			rot(0.7083F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition ATTACK_3 = AnimationDefinition.Builder.withLength(0.6667F)
		.addAnimation("body", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, -30.0F, 0.0F),
			rot(0.3333F, 0.0F, -35.0F, 0.0F),
			rot(0.375F, 0.0F, 30.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -45.0F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -45.0F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claws_left", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 7.5F, 0.0F),
			rot(0.375F, 0.0F, -46.25F, 0.0F),
			rot(0.4167F, 0.0F, -37.5F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -45.0F, 0.0F, 0.0F),
			rot(0.375F, -65.0F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_left_3_end2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -54.2352F, -17.5611F, -57.4635F)))
		.addAnimation("claws_right", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 0.0F, 30.0F, 0.0F)))
		.addAnimation("claw_right_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -60.0F, 0.0F, 0.0F),
			rot(0.3333F, -60.7275F, -17.408F, -1.9405F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -87.5F, 0.0F, 0.0F),
			rot(0.3333F, -87.1823F, -19.9215F, 1.8073F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("chain_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -25.0F, 0.0F, 0.0F),
			rot(0.4167F, -120.0F, 0.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("chain_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.2917F, 0.0F, 5.0F, 0.0F),
			rot(0.4167F, -32.5F, 30.0F, 0.0F),
			rot(0.625F, 0.0F, 0.0F, 0.0F)))
		.build();

	public static final AnimationDefinition ATTACK_4 = AnimationDefinition.Builder.withLength(0.8333F)
		.addAnimation("body", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, 0.0F, 25.0F, 0.0F),
			rot(0.375F, 0.0F, 35.0F, 0.0F),
			rot(0.4167F, 0.0F, -52.5F, 0.0F),
			rot(0.6667F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claws_left", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.25F, -70.0F, 0.0F, 0.0F),
			rot(0.4167F, -55.8863F, -52.4225F, -28.2294F),
			rot(0.6667F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claws_right", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, 0.0F, -15.0F, 0.0F),
			rot(0.5417F, 0.0F, 75.0F, 0.0F),
			rot(0.6667F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_1_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -54.1908F, -9.3735F, -3.876F),
			rot(0.6667F, -55.2415F, -1.3391F, -0.5537F),
			rot(0.8333F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("claw_right_2_end", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.375F, -72.5F, 0.0F, 0.0F),
			rot(0.6667F, -87.86F, 0.0F, 0.0F),
			rot(0.8333F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("chain_1", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.3333F, 15.0F, 0.0F, 0.0F),
			rot(0.4167F, -30.0F, 0.0F, 0.0F),
			rot(0.6667F, 0.0F, 0.0F, 0.0F)))
		.addAnimation("chain_2", rotation(
			rot(0.0F, 0.0F, 0.0F, 0.0F),
			rot(0.3333F, 60.0F, 0.0F, 0.0F),
			rot(0.4167F, -17.5F, 0.0F, 0.0F),
			rot(0.6667F, 0.0F, 0.0F, 0.0F)))
		.build();
}
