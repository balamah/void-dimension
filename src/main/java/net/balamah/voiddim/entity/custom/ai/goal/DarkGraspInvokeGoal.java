package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.custom.DarkGraspEntity;
import net.balamah.voiddim.interfaces.DarkGraspUser;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.balamah.voiddim.entity.ModEntityStatuses;

public class DarkGraspInvokeGoal<T extends CorruptedHostileEntity & DarkGraspUser>
	extends SlowMovementGoal<T>
{
	protected final int executionTick;
	protected final int minActivationDistance;
	protected final int maxActivationDistance;
	protected final SoundEvent invocationSound;

	protected boolean didInvokeGrasp;

	public DarkGraspInvokeGoal(
		T entity, int executionTick, int minActivationDistance, int maxActivationDistance,
		SoundEvent invocationSound
	) {
	    super(entity);

		this.executionTick = executionTick;
		this.minActivationDistance = minActivationDistance;
		this.maxActivationDistance = maxActivationDistance;

		this.invocationSound = invocationSound;
	}

	@Override
	public boolean canUse() {
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
		this.entity.makeSound(this.invocationSound);
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
	public boolean canContinueToUse() {
		return this.entity.getTarget() != null && !this.didInvokeGrasp;
	}

	@Override
	public void tick() {
		super.tick();

		if (this.tick == this.executionTick) {
			this.cast(world, this.entity.getTarget());
		}
	}

	protected void cast(Level world, LivingEntity target) {
		double d = Math.min(target.getY(), this.entity.getY());
		double e = Math.max(target.getY(), this.entity.getY()) + 1.0;
		float f = (float)Mth.atan2(target.getZ() - this.entity.getZ(), target.getX() - this.entity.getX());

		if (this.entity.distanceToSqr(target) < 9.0) {
			for (int i = 0; i < 15; i++) {
				float g = f + i * (float) Math.PI * 0.4F;
				this.invokeDarkGrasp(
					world,
					this.entity.getX() + Mth.cos(g) * 1.5,
					this.entity.getZ() + Mth.sin(g) * 1.5,
					d, e, g, 0
				);
			}

			for (int i = 0; i < 15; i++) {
				float g = f + i * (float) Math.PI * 2.0F / 8.0F + (float) (Math.PI * 2.0 / 5.0);
				this.invokeDarkGrasp(
					world,
					this.entity.getX() + Mth.cos(g) * 2.5,
					this.entity.getZ() + Mth.sin(g) * 2.5, d, e, g, 3
				);
			}
		} else {
			for (int i = 0; i < 16; i++) {
				double h = 1.25 * (i + 1);
				int j = 1 * i;
				this.invokeDarkGrasp(
					world,
					this.entity.getX() + Mth.cos(f) * h,
					this.entity.getZ() + Mth.sin(f) * h, d, e, f, j
				);
			}
		}

		this.didInvokeGrasp = true;
	}

	protected void invokeDarkGrasp(
		Level world, double x, double z, double maxY, double y, float yaw, int warmup
	) {
		BlockPos blockPos = BlockPos.containing(x, y, z);
		boolean foundSurface = false;
		double surfaceHeightOffset = 0.0;

		do {
			BlockPos lowBlockPos = blockPos.below();
			BlockState state = world.getBlockState(lowBlockPos);
			if (state.isFaceSturdy(world, lowBlockPos, Direction.UP))
			{
				BlockState surfaceBlockState = world.getBlockState(blockPos);
				VoxelShape voxel = surfaceBlockState.getCollisionShape(world, blockPos);
				if (!voxel.isEmpty()) {
					surfaceHeightOffset = voxel.max(Direction.Axis.Y);
				}

				foundSurface = true;
				break;
			}
			
			blockPos = blockPos.below();
		} while (blockPos.getY() >= Mth.floor(maxY) - 1);

		if (foundSurface) {
			Entity darkGrasp = new DarkGraspEntity(
				world, x, blockPos.getY() + surfaceHeightOffset, z, yaw, warmup, this.entity
			);

			world.addFreshEntity(darkGrasp);
		}
	}
}
