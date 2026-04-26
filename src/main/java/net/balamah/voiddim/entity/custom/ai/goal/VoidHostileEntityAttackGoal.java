package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class VoidHostileEntityAttackGoal extends MeleeAttackGoal {
	protected final PathfinderMob entity;
	protected int ticks;

	public VoidHostileEntityAttackGoal(
		PathfinderMob entity, double speed, boolean pauseWhenMobIdle
	) {
		super(entity, speed, pauseWhenMobIdle);

		this.entity = entity;
	}

	@Override
	public void start() {
		super.start();

		this.ticks = 0;
	}

	@Override
	public void stop() {
		super.stop();

		this.entity.setAggressive(false);
	}

	@Override
	public void tick() {
		super.tick();

		this.ticks++;

		if (this.ticks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
			this.entity.setAggressive(true);
		} else {
			this.entity.setAggressive(false);
		}
	}
}
