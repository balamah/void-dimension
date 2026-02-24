package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public abstract class TickingGoal extends Goal {
	protected final MobEntity entity;
	protected final World world;
	protected int ticks;

	public TickingGoal(MobEntity entity) {
		this.entity = entity;

		this.world = entity.getEntityWorld();
	}

	@Override
	public abstract boolean canStart();

	@Override
	public void tick() {
		super.tick();

		this.ticks++;
	}

	protected void sendEntityStatus(byte status) {
		this.world.sendEntityStatus(this.entity, status);
	}
}
