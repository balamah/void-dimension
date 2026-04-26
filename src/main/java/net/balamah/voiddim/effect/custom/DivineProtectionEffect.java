package net.balamah.voiddim.effect.custom;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DivineProtectionEffect extends MobEffect {
	public DivineProtectionEffect() {
		super(MobEffectCategory.BENEFICIAL, 0xFFADD8E6);
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
}
