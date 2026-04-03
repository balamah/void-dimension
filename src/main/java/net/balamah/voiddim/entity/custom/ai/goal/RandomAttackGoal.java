package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.Random;

import net.minecraft.entity.mob.PathAwareEntity;

public class RandomAttackGoal extends VoidHostileEntityAttackGoal {
	protected int upperBondChance;

	public RandomAttackGoal(PathAwareEntity entity, int upperBondChance) {
		super(entity, 1.0, false);

		this.upperBondChance = upperBondChance;
	}

	@Override
	public boolean canStart() {
		int randomNumber = new Random().nextInt(this.upperBondChance);

		return randomNumber == this.upperBondChance - 1;
	}
}
