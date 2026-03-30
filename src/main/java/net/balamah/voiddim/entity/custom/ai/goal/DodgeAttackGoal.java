package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.Block;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.interfaces.DodgeAttackUser;
import net.balamah.voiddim.custom.McCodeHelper;

public class DodgeAttackGoal<T extends CorruptedHostileEntity & DodgeAttackUser>
	extends SlowMovementGoal<T>
{
	protected final int counterAttackDelay;
	protected boolean dodgedAndHit;

	public DodgeAttackGoal(T entity, int counterAttackDelay) {
		super(entity);

		this.counterAttackDelay = counterAttackDelay;
	}

	@Override
	public boolean canStart() {
		return this.entity.getTarget() != null && this.entity.getDodgeAttackTicks() == 0 &&
			this.entity.attackCount >= 4;
	}

	@Override
	public boolean shouldContinue() {
		return !this.dodgedAndHit && this.entity.getTarget() != null;
	}

	@Override
	public void start() {
		super.start();

		Vec3d targetVelocity = this.entity.getTarget().getVelocity();
		Vec3d newVelocity = targetVelocity.add(0, 0, 1);
		if (!this.isDodgingPositionSafe(newVelocity.x, newVelocity.y, newVelocity.z)) {
			newVelocity = targetVelocity.add(1, 0, 0);
		} else {
			newVelocity = targetVelocity.add(-1, 0, -1);
		}

		BlockPos newBlockPos = new BlockPos(
			(int) newVelocity.x, (int) newVelocity.y, (int) newVelocity.z
		);

		int newVelocityElevations = 0;
		while (!this.world.getBlockState(newBlockPos).isAir() && newVelocityElevations < 3) {
			newVelocity.add(0, 1, 0);

			newVelocityElevations++;
		}

		this.entity.setVelocity(newVelocity);
		this.addSpeedModifier();
	}

	@Override
	public void stop() {
		super.stop();

		this.removeSpeedModifier();
		this.dodgedAndHit = false;
		this.entity.attackCount = 0;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.tick == this.counterAttackDelay) {
			this.entity.setVelocity(this.entity.getTarget().getVelocity());

			this.dodgedAndHit = true;
		}
	}

	/*
	 * Check if it is safe by lowering mutable down.
	 * If future position is a deep hole, or has lava or a cobweb,
	   then the position is not safe
	 */
	@SuppressWarnings("deprecation")
	protected boolean isDodgingPositionSafe(double x, double y, double z) {
		BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

		if (!this.isDodgingToWall(x, y, z)) {
			return false;
		}

		while (mutable.getY() > this.world.getBottomY() &&
				!this.world.getBlockState(mutable).blocksMovement())
		{
			double heightDifference = this.entity.getY() - mutable.getY();
			Block block = this.world.getBlockState(mutable).getBlock();

			if (heightDifference < 30) {
				mutable.move(Direction.DOWN);
			} else if (heightDifference >= 30 || McCodeHelper.dangerousBlocks.contains(block)) {
				return false;
			}
		}

		return true;
	}

	protected boolean isDodgingToWall(double x, double y, double z) {
		BlockPos upperBlockPosition = new BlockPos((int) x, (int) y + 1, (int) z);

		if (this.world.getBlockState(upperBlockPosition).isAir()) {
			return false;
		}

		return true;
	}
}
