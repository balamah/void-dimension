package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.custom.DarkGraspEntity;
import net.balamah.voiddim.interfaces.DarkGraspUser;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class DarkGraspInvokeGoal<T extends CorruptedHostileEntity & DarkGraspUser>
	extends SlowMovementGoal<T>
{
	protected final int executionTick;

	protected boolean didInvokeGrasp;

	public DarkGraspInvokeGoal(T entity, int executionTick) {
	    super(entity);

		this.executionTick = executionTick;
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null && target.distanceTo(this.entity) >= 6 &&
			this.entity.getDarkGraspInvokeTicks() == 0;
	}

	@Override
	public void start() {
		super.start();

		this.addSpeedModifier();

		this.entity.setStopAttacks(true);
		this.sendEntityStatus(ModEntityStatuses.SPECIAL_ATTACK);
	}

	@Override
	public void stop() {
		super.stop();

		this.didInvokeGrasp = false;

		this.removeSpeedModifier();
		this.entity.setStopAttacks(false);

		// TODO: Change 20 to this.entity.getDarkGraspInvokeCooldown()
		this.entity.setDarkGraspInvokeTicks(20);

		this.sendEntityStatus(ModEntityStatuses.STOP_SPECIAL_ATTACK);
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null && !this.didInvokeGrasp;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.tick == this.executionTick) {
			this.cast(world, this.entity.getTarget());
		}
	}

	protected void cast(World world, LivingEntity target) {
		double d = Math.min(target.getY(), this.entity.getY());
		double e = Math.max(target.getY(), this.entity.getY()) + 1.0;
		float f = (float)MathHelper.atan2(target.getZ() - this.entity.getZ(), target.getX() - this.entity.getX());

		if (this.entity.squaredDistanceTo(target) < 9.0) {
			for (int i = 0; i < 5; i++) {
				float g = f + i * (float) Math.PI * 0.4F;
				this.invokeDarkGrasp(
					world,
					this.entity.getX() + MathHelper.cos(g) * 1.5,
					this.entity.getZ() + MathHelper.sin(g) * 1.5,
					d, e, g, 0
				);
			}

			for (int i = 0; i < 8; i++) {
				float g = f + i * (float) Math.PI * 2.0F / 8.0F + (float) (Math.PI * 2.0 / 5.0);
				this.invokeDarkGrasp(
					world,
					this.entity.getX() + MathHelper.cos(g) * 2.5,
					this.entity.getZ() + MathHelper.sin(g) * 2.5, d, e, g, 3
				);
			}
		} else {
			for (int i = 0; i < 16; i++) {
				double h = 1.25 * (i + 1);
				int j = 1 * i;
				this.invokeDarkGrasp(
					world,
					this.entity.getX() + MathHelper.cos(f) * h,
					this.entity.getZ() + MathHelper.sin(f) * h, d, e, f, j
				);
			}
		}

		this.didInvokeGrasp = true;
	}

	protected void invokeDarkGrasp(
		World world, double x, double z, double maxY, double y, float yaw, int warmup
	) {
		BlockPos blockPos = BlockPos.ofFloored(x, y, z);

		world.spawnEntity(
			new DarkGraspEntity(world, x, blockPos.getY(), z, yaw, warmup, this.entity)
		);
	}
}
