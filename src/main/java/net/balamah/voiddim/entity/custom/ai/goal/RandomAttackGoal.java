package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.Random;
import net.minecraft.world.entity.PathfinderMob;

public class RandomAttackGoal extends VoidHostileEntityAttackGoal {
	protected int upperBondChance;

	public RandomAttackGoal(PathfinderMob entity, int upperBondChance) {
		super(entity, 1.0, false);

		this.upperBondChance = upperBondChance;
	}

	@Override
	public boolean canUse() {
		int randomNumber = new Random().nextInt(this.upperBondChance);

		return randomNumber == this.upperBondChance - 1;
	}
}
