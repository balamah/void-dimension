package net.balamah.voiddim.entity.custom.ai.goal.base;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;

public abstract class TickingGoal<T extends CorruptedHostileEntity> extends Goal {
	protected final T entity;
	protected final World world;
	protected int tick;

	public TickingGoal(T entity) {
		this.entity = entity;

		this.world = entity.getEntityWorld();
	}

	@Override
	public void start() {
		this.tick = 0;
	}

	@Override
	public abstract boolean canStart();

	@Override
	public void tick() {
		super.tick();

		this.tick++;
	}

	protected void sendEntityStatus(byte status) {
		this.world.sendEntityStatus(this.entity, status);
	}
}
