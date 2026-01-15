package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

import net.balamah.voiddim.entity.custom.VoidHarbingerEntity;

public class VoidHarbingerTeleportTowardsPlayerGoal extends Goal {
	protected final VoidHarbingerEntity entity;

	public VoidHarbingerTeleportTowardsPlayerGoal(VoidHarbingerEntity entity) {
		this.entity = entity;
	}

	@Override
	public boolean canStart() {
		float middleHP = this.entity.getMaxHealth() / 2;
		float currentHP = this.entity.getHealth();

		return this.entity.getTarget() != null && currentHP < middleHP;
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

		double targetDistance = this.entity.distanceTo(target);
		int attackCount = this.entity.attackCount;

		if (targetDistance < 5 || targetDistance > 10 ||
			(attackCount > 0 && attackCount % 4 == 0)
		) {
			this.entity.teleportTo(target);
		}
	}

	@Override
	public void stop() {
		super.stop();
	}
}
