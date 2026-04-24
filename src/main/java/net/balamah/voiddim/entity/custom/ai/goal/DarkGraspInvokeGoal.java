package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
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
	protected final int minActivationDistance;
	protected final int maxActivationDistance;

	protected boolean didInvokeGrasp;

	public DarkGraspInvokeGoal(
		T entity, int executionTick, int minActivationDistance, int maxActivationDistance
	) {
	    super(entity);

		this.executionTick = executionTick;
		this.minActivationDistance = minActivationDistance;
		this.maxActivationDistance = maxActivationDistance;
	}

	@Override
	public boolean canStart() {
		LivingEntity target = this.entity.getTarget();

		if (target == null) {
			return false;
		}

		double distance = target.distanceTo(this.entity);

		return distance >= this.minActivationDistance &&
			distance <= this.maxActivationDistance &&
			this.entity.getDarkGraspInvokeTicks() == 0;
	}

	@Override
	public void start() {
		super.start();

		this.addSpeedModifier();

		this.entity.setStopAttacks(true);
		this.entity.playSound(this.entity.getDarkGraspSound());
		this.sendEntityStatus(ModEntityStatuses.SPECIAL_ATTACK);
	}

	@Override
	public void stop() {
		super.stop();

		this.didInvokeGrasp = false;

		this.removeSpeedModifier();
		this.entity.setStopAttacks(false);
		this.entity.setDarkGraspInvokeTicks(this.entity.getDarkGraspInvokeCooldown());

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
			for (int i = 0; i < 15; i++) {
				float g = f + i * (float) Math.PI * 0.4F;
				this.invokeDarkGrasp(
					world,
					this.entity.getX() + MathHelper.cos(g) * 1.5,
					this.entity.getZ() + MathHelper.sin(g) * 1.5,
					d, e, g, 0
				);
			}

			for (int i = 0; i < 15; i++) {
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
		boolean foundSurface = false;
		double surfaceHeightOffset = 0.0;

		do {
			BlockPos lowBlockPos = blockPos.down();
			BlockState state = world.getBlockState(lowBlockPos);
			if (state.isSideSolidFullSquare(world, lowBlockPos, Direction.UP))
			{
				BlockState surfaceBlockState = world.getBlockState(blockPos);
				VoxelShape voxel = surfaceBlockState.getCollisionShape(world, blockPos);
				if (!voxel.isEmpty()) {
					surfaceHeightOffset = voxel.getMax(Direction.Axis.Y);
				}

				foundSurface = true;
				break;
			}
			
			blockPos = blockPos.down();
		} while (blockPos.getY() >= MathHelper.floor(maxY) - 1);

		if (foundSurface) {
			Entity darkGrasp = new DarkGraspEntity(
				world, x, blockPos.getY() + surfaceHeightOffset, z, yaw, warmup, this.entity
			);

			world.spawnEntity(darkGrasp);
		}
	}
}
