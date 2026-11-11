package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.VoidDimension;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.TeleportUser;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class TeleportTowardsPlayerGoal<T extends CorruptedHostileEntity & TeleportUser>
	extends Goal
{
	protected final T entity;

	public TeleportTowardsPlayerGoal(T entity) {
		this.entity = entity;
	}

	@Override
	public boolean canUse() {
		return this.entity.getTarget() != null && this.entity.isSecondPhase();
	}

	@Override
	public boolean canContinueToUse() {
		return this.entity.getTarget() != null;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void tick() {
		LivingEntity target = this.entity.getTarget();

		if (target == null) {
			return;
		}

		int attackCount = this.entity.attackCount;
		if (attackCount % 4 == 0) {
			this.entity.teleportTo(target);
		}
	}
}
