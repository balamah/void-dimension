package net.balamah.voiddim.effect.custom;

import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffect;

public class DivineProtectionEffect extends StatusEffect {
	public DivineProtectionEffect() {
		super(StatusEffectCategory.BENEFICIAL, 0xFFADD8E6);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
}
