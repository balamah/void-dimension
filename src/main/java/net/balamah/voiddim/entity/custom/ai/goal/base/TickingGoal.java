package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;

public abstract class TickingGoal<T extends CorruptedHostileEntity> extends Goal {
	protected final T entity;
	protected final Level world;
	protected int tick;

	public TickingGoal(T entity) {
		this.entity = entity;

		this.world = entity.level();
	}

	@Override
	public void start() {
		this.tick = 0;
	}

	@Override
	public abstract boolean canUse();

	@Override
	public void tick() {
		super.tick();

		this.tick++;
	}

	protected void sendEntityStatus(byte status) {
		this.world.broadcastEntityEvent(this.entity, status);
	}
}
