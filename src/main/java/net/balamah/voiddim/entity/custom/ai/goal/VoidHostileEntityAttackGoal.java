package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.PathAwareEntity;

public class VoidHostileEntityAttackGoal extends MeleeAttackGoal {
	protected final PathAwareEntity entity;
	protected int ticks;

	public VoidHostileEntityAttackGoal(
		PathAwareEntity entity, double speed, boolean pauseWhenMobIdle
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

		this.entity.setAttacking(false);
	}

	@Override
	public void tick() {
		super.tick();

		this.ticks++;

		if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
			this.entity.setAttacking(true);
		} else {
			this.entity.setAttacking(false);
		}
	}
}
