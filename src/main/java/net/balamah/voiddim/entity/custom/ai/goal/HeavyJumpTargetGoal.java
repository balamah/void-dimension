package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.balamah.voiddim.entity.custom.Entity303;
import net.balamah.voiddim.custom.McCodeHelper;

public class HeavyJumpTargetGoal<T extends Entity303> extends TickingGoal<T> {
	protected boolean jumpedPredicate;

	protected double xtarget;
	protected double ytarget;
	protected double ztarget;

	public HeavyJumpTargetGoal(T entity) {
		super(entity);
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.entity.getTarget();
		return target != null && target.distanceTo(this.entity) >= 25;
	}

	@Override
	public boolean canContinueToUse() {
		return this.entity.getTarget() != null && !this.jumpedPredicate;
	}

	@Override
	public void start() {
		super.start();

		LivingEntity target = this.entity.getTarget();

		this.xtarget = target.getX();
		this.ytarget = target.getY();
		this.ztarget = target.getZ();

		this.entity.jumpFromGround();
		this.entity.setDeltaMovement(xtarget, ytarget + 20, ztarget);
		this.entity.setInvulnerable(true);
	}

	@Override
	public void stop() {
		super.stop();

		this.jumpedPredicate = false;
		this.entity.setInvulnerable(false);
	}

	@Override
	public void tick() {
		super.tick();

		if (this.entity.getY() == this.ytarget) {
			McCodeHelper.createShockWave((ServerLevel) this.world, entity, 25f);

			this.jumpedPredicate = true;
		}
	}
}
