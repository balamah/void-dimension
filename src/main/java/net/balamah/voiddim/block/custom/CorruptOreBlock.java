package net.balamah.voiddim.block.custom;

import net.balamah.voiddim.particle.ModParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.balamah.voiddim.effect.ModEffects;

public class CorruptOreBlock extends DropExperienceBlock {
	protected Direction direction;

	public CorruptOreBlock(IntProvider experienceDropped, Properties settings) {
		super(experienceDropped, settings);
	}
	
	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof LivingEntity livingEntity) {
			MobEffectInstance effect =
				new MobEffectInstance(ModEffects.CORRUPTION, 60, 1);

			livingEntity.addEffect(effect);
		}
	}

	@Override
	public void animateTick(
		BlockState state, Level world, BlockPos pos, RandomSource random
	) {
		if (!world.isClientSide()) return;

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
