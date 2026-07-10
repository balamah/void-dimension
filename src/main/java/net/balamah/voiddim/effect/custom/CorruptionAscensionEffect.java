package net.balamah.voiddim.effect.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class CorruptionAscensionEffect extends MobEffect {
	public CorruptionAscensionEffect() {
		super(MobEffectCategory.HARMFUL, 0xFF444444);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
		mob.kill(serverLevel);

		return super.applyEffectTick(serverLevel, mob, amplification);
	}
}
