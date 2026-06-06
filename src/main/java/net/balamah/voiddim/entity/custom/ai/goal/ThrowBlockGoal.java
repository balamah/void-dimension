package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.ModEntityStatuses;
import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.interfaces.ThrowBlockUser;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.balamah.voiddim.custom.McCodeHelper;

public class ThrowBlockGoal<T extends CorruptedHostileEntity & ThrowBlockUser>
	extends SlowMovementGoal<T>
{
	protected boolean blockThrown = false;

	protected final int executionTick;
	protected final int minDistance;

	public ThrowBlockGoal(T entity, int executionTick, int minDistance) {
		super(entity);

		this.executionTick = executionTick;
		this.minDistance = minDistance;
	}

	@Override
	public boolean canUse() {
		LivingEntity target = this.entity.getTarget();

		return target != null && target.distanceTo(this.entity) >= this.minDistance;
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

	protected void throwBlock(ServerLevel world, BlockPos blockPos, LivingEntity target) {
		FallingBlockEntity fallingBlock = new FallingBlockEntity(EntityType.FALLING_BLOCK, world);

		this.breakBlock(world, blockPos);

		this.blockThrown = true;
	}

	protected void breakBlock(ServerLevel world, BlockPos blockPos) {
		world.destroyBlock(blockPos, false);
		McCodeHelper.playSound(
			world, SoundEvents.STONE_BREAK, blockPos.getCenter(), null, 1f, 1f
		);
	}
}
