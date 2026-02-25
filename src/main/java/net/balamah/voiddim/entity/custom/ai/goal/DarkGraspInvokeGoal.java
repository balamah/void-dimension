package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.CorruptedWarriorEntity;
import net.balamah.voiddim.entity.custom.DarkGraspEntity;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class DarkGraspInvokeGoal extends SlowMovementGoal {
	protected boolean didInvokeGrasp;

	public DarkGraspInvokeGoal(CorruptedWarriorEntity entity) {
	    super(entity);
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		return target != null && target.distanceTo(this.entity) >= 6;
	}

	@Override
	public void start() {
		this.addSpeedModifier();

		this.entity.setStopAttacks(true);
		this.sendEntityStatus(ModEntityStatuses.CORRUPTED_WARRIOR_SPECIAL_ATTACK);
	}

	@Override
	public void stop() {
		super.stop();

		this.removeSpeedModifier();;
		this.entity.setStopAttacks(false);
		this.sendEntityStatus(ModEntityStatuses.CORRUPTED_WARRIOR_ATTACKS_STOP);
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null && !this.didInvokeGrasp;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.tick == 15) {
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
