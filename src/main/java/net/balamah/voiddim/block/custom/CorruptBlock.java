package net.balamah.voiddim.block.custom;

import net.balamah.voiddim.particle.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.balamah.voiddim.effect.ModEffects;

public class CorruptBlock extends Block {
	protected Direction direction;

	public CorruptBlock(Properties settings) {
		super(settings);

		this.direction = Direction.UP;
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof LivingEntity livingEntity) {
			MobEffectInstance effect = new MobEffectInstance(ModEffects.CORRUPTION, 60, 1);

			livingEntity.addEffect(effect);
		}
	}

	@Override
	public void animateTick(
		BlockState state, Level world, BlockPos blockPos, RandomSource random
	) {
		if (!world.isClientSide()) {
			return;
		}

		double x = blockPos.getX() + 0.2 + random.nextDouble() * 0.6;
		double y = blockPos.getY() + random.nextDouble();
		double z = blockPos.getZ() + 0.2 + random.nextDouble() * 0.6;

		double vx = (random.nextDouble() - 0.5) * 0.01;
		double vy = 0.05 + random.nextDouble() * 0.03;
		double vz = (random.nextDouble() - 0.5) * 0.01;

		if (random.nextInt(12) == 0) {
			world.addParticle(ModParticleTypes.CORRUPTION, x, y, z, vx, vy, vz);
		}
	}
}
