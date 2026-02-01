package net.balamah.voiddim.block.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.random.Random;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import net.balamah.voiddim.particle.ModParticleTypes;
import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.effect.ModEffects;

public class CorruptBlock extends Block {
	protected Direction direction;

	public CorruptBlock(Settings settings) {
		super(settings);

		this.direction = Direction.UP;
	}

	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof LivingEntity livingEntity) {
			StatusEffectInstance effect = new StatusEffectInstance(ModEffects.CORRUPTION, 60, 1);

			livingEntity.addStatusEffect(effect);
		}
	}

	@Override
	public void randomDisplayTick(
		BlockState state, World world, BlockPos blockPos, Random random
	) {
		if (!world.isClient() || this.isUnderBlock(world, blockPos)) {
			return;
		}

		double x = blockPos.getX() + 0.2 + random.nextDouble() * 0.6;
		double y = blockPos.getY() + random.nextDouble();
		double z = blockPos.getZ() + 0.2 + random.nextDouble() * 0.6;

		double vx = (random.nextDouble() - 0.5) * 0.01;
		double vy = 0.05 + random.nextDouble() * 0.03;
		double vz = (random.nextDouble() - 0.5) * 0.01;

		if (random.nextInt(3) == 0) {
			world.addParticleClient(ModParticleTypes.CORRUPTION, x, y, z, vx, vy, vz);
		}
	}

	protected boolean isUnderBlock(World world, BlockPos blockPos) {
		BlockPos upperBlockPos = blockPos.up();
		BlockState upperBlockState = world.getBlockState(upperBlockPos);

		if (upperBlockState.isAir()) {
			return false;
		}

		return true;
	}
}
