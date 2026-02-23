package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.Random;

import net.minecraft.entity.mob.PathAwareEntity;

public class RandomAttackGoal extends VoidHostileEntityAttackGoal {
	public RandomAttackGoal(PathAwareEntity entity) {
		super(entity, 1.0, false);
	}

	@Override
	public boolean canStart() {
		int upperBond = 100;
		int randomNumber = new Random().nextInt(upperBond);

		return randomNumber == upperBond - 1;
	}
}
