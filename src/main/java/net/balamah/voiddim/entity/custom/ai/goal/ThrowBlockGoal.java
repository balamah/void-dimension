package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.ModEntities;
import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ThrownBlockEntity;
import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.ThrowBlockUser;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.balamah.voiddim.custom.McCodeHelper;

public class ThrowBlockGoal<T extends CorruptedHostileEntity & ThrowBlockUser>
	extends SlowMovementGoal<T>
{
	protected boolean blockThrown = false;

	protected final int executionTick;
	protected final int minHeight;

	public ThrowBlockGoal(T entity, int executionTick, int minHeight) {
		super(entity);

		this.executionTick = executionTick;
		this.minHeight = minHeight;
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.entity.getTarget();

		return target != null && target.getY() - this.entity.getY() >= this.minHeight;
	}

	@Override
	public void start() {
		this.addSpeedModifier();

		this.sendEntityStatus(ModEntityStatuses.THROW_BLOCK);
		this.entity.setStopAttacks(true);
	}

	@Override
	public void tick() {
		super.tick();

		Level world = this.entity.level();
		LivingEntity target = this.entity.getTarget();
		if (!(world instanceof ServerLevel serverWorld)) {
			return;
		}

		if (this.tick == this.executionTick) {
			BlockPos blockPos = McCodeHelper.getRandomBlockRightOf(entity, 4, 0);

			this.throwBlock(serverWorld, blockPos, target);
		}
	}

	@Override
	public void stop() {
		super.stop();

		this.removeSpeedModifier();

		this.sendEntityStatus(ModEntityStatuses.THROW_BLOCK_STOP);
	}

	protected void throwBlock(Level world, BlockPos blockPos, LivingEntity target) {
		double heightScale = target.isPassenger() ? 0.8 : 0.3;
		double dx = target.getX() - this.entity.getX();
		double dy = target.getZ(heightScale) - this.entity.getChargeY();
		double dz = target.getZ() - this.entity.getZ();

		this.breakBlock(world, blockPos);
		this.spawnThrownBlock(world, blockPos, dx, dy, dz);

		this.blockThrown = true;
	}

	protected void spawnThrownBlock(
		Level world, BlockPos blockPos, double dx, double dy, double dz
	) {
		double length = Math.sqrt(dx * dx + dy * dy + dz * dy);
		if (length > 0) {
			dx /= length;
			dy /= length;
			dz /= length;
		}

		BlockState blockState = world.getBlockState(blockPos);

		ThrownBlockEntity fallingBlock = new ThrownBlockEntity(
			ModEntities.THROWN_BLOCK, world, blockState
		);

		fallingBlock.setOwner(this.entity);
		fallingBlock.setPos(blockPos.getX(), blockPos.getY() + 4, blockPos.getZ());
		fallingBlock.shoot(dx, dy, dz, 0.7f, 1f);

		world.addFreshEntity(fallingBlock);
	}

	protected void breakBlock(Level world, BlockPos blockPos) {
		world.destroyBlock(blockPos, false);
		McCodeHelper.playSound(
			world, SoundEvents.STONE_BREAK, blockPos.getCenter(), null, 1f, 1f
		);
	}
}
