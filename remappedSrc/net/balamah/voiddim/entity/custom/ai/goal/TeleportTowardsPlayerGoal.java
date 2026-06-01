package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.TeleportUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

public class TeleportTowardsPlayerGoal<T extends CorruptedHostileEntity & TeleportUser>
	extends Goal
{
	protected final T entity;

	public TeleportTowardsPlayerGoal(T entity) {
		this.entity = entity;
	}

	@Override
	public boolean canStart() {
		return this.entity.getTarget() != null && this.entity.isSecondPhase();
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null;
	}

	@Override
	public boolean shouldRunEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		LivingEntity target = this.entity.getTarget();

		if (target == null) {
			return;
		}

		double targetDistance = this.entity.distanceTo(target);
		int attackCount = this.entity.attackCount;
		if (targetDistance < 5 || targetDistance > 10 ||
			(attackCount > 0 && attackCount % 4 == 0))
		{
			this.entity.teleportTo(target);
		}
	}
}
