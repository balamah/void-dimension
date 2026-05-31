package net.balamah.voiddim.block.custom;

import net.balamah.voiddim.particle.ModParticleTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.balamah.voiddim.effect.ModEffects;

public class CorruptOreBlock extends ExperienceDroppingBlock {
	protected Direction direction;

	public CorruptOreBlock(IntProvider experienceDropped, Settings settings) {
		super(experienceDropped, settings);
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof LivingEntity livingEntity) {
			StatusEffectInstance effect =
				new StatusEffectInstance(ModEffects.CORRUPTION, 60, 1);

			livingEntity.addStatusEffect(effect);
		}
	}

	@Override
	public void randomDisplayTick(
		BlockState state, World world, BlockPos pos, Random random
	) {
		if (!world.isClient()) return;

		double x = pos.getX() + 0.2 + random.nextDouble() * 0.6;
		double y = pos.getY() + random.nextDouble();
		double z = pos.getZ() + 0.2 + random.nextDouble() * 0.6;

		double vx = (random.nextDouble() - 0.5) * 0.01;
		double vy = 0.05 + random.nextDouble() * 0.03;
		double vz = (random.nextDouble() - 0.5) * 0.01;

		if (random.nextInt(3) == 0) {
			world.addParticle(ModParticleTypes.CORRUPTION, x, y, z, vx, vy, vz);
		}
	}
}
