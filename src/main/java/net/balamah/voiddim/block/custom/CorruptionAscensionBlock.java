package net.balamah.voiddim.block.custom;

import net.balamah.voiddim.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CorruptionAscensionBlock extends CorruptBlock {
	public CorruptionAscensionBlock(Properties settings) {
		super(settings);
	}

	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
		if (entity instanceof LivingEntity livingEntity) {
			MobEffectInstance effect = new MobEffectInstance(
				ModEffects.CORRUPTION_ASCENSION, 60, 1
			);

			livingEntity.addEffect(effect);
		}
	}
}
