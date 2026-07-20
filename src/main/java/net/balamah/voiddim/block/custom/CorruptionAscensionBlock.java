package net.balamah.voiddim.block.custom;

import net.balamah.voiddim.custom.McCodeHelper;
import net.balamah.voiddim.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
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
		if (!(entity instanceof LivingEntity livingEntity)) {
			return;
		}

		Holder<MobEffect> effect;
		boolean isAscendable = McCodeHelper.entityCorruptionAscensionMap.containsKey(
			livingEntity.getType()
		);

		if (isAscendable) {
			effect = ModEffects.CORRUPTION_ASCENSION;
		} else {
			effect = ModEffects.CORRUPTION;
		}
		
		livingEntity.addEffect(new MobEffectInstance(effect, 60, 1));
	}
}
