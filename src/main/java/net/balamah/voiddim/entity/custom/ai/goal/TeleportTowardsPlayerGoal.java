package net.balamah.voiddim.entity.custom.ai.goal;

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
		float middleHP = this.entity.getMaxHealth() / 2;
		float currentHP = this.entity.getHealth();

		return this.entity.getTarget() != null && currentHP < middleHP;
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

		double targetDistance = this.entity.distanceTo(target);
		int attackCount = this.entity.attackCount;

		if (targetDistance < 5 || targetDistance > 10 ||
			(attackCount > 0 && attackCount % 4 == 0)
		) {
			System.out.println("Void harbinger: goal teleporting");
			this.entity.teleportTo(target);
		}
	}
}
