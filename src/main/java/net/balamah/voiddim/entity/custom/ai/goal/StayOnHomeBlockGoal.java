package net.balamah.voiddim.entity.custom.ai.goal;

import org.jspecify.annotations.Nullable;

import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.balamah.voiddim.custom.McCodeHelper;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;

public class StayOnHomeBlockGoal extends RandomStrollGoal {
	protected final Block homeBlock;
	protected final int homeBlockSearchRange;
	private final boolean checkNoActionTime;

	public StayOnHomeBlockGoal(
		PathfinderMob mob, double speedModifier, int interval,
		boolean checkNoActionTime, Block homeBlock, int homeBlockSearchRange
	) {
		this.checkNoActionTime = checkNoActionTime;
		this.homeBlock = homeBlock;
		this.homeBlockSearchRange = homeBlockSearchRange;

		super(mob, speedModifier, interval, checkNoActionTime);
	}

	@Override
	public boolean canUse() {
		Level world = this.mob.level();
			
		if (McCodeHelper.getBlock(world, this.mob.getOnPos()) == this.homeBlock) {
			return false;
		}

		if (!this.forceTrigger) {
			if (this.checkNoActionTime && this.mob.getNoActionTime() >= 100) {
				return false;
			}

			if (this.mob.getRandom().nextInt(reducedTickDelay(this.interval)) != 0) {
				return false;
			}
		}

		Vec3 pos = this.getHomeBlockPosition(this.homeBlockSearchRange);
		if (pos == null) {
			return false;
		} else {
			this.wantedX = pos.x;
			this.wantedY = pos.y;
			this.wantedZ = pos.z;
			this.forceTrigger = false;

			return true;
		}
	}
	
	public @Nullable Vec3 getHomeBlockPosition(double radius) {
		for (BlockPos pos : BlockPos.betweenClosed(
			Mth.floor(this.mob.getX() - radius),
			Mth.floor(this.mob.getY() - radius),
			Mth.floor(this.mob.getZ() - radius),
			Mth.floor(this.mob.getX() + radius),
			this.mob.getBlockY(),
			Mth.floor(this.mob.getZ() + radius)
		)) {
			if (this.mob.level().getBlockState(pos).is(this.homeBlock)) {
				return pos.getCenter();
			}
		}
		
		return null;
	}

	@Override
	public void stop() {
		super.stop();

		float newMobHorizontalRotation = this.mob.getYRot() + 180f;

		this.mob.setYRot(newMobHorizontalRotation);
		this.mob.setYHeadRot(newMobHorizontalRotation);
		this.mob.setYBodyRot(newMobHorizontalRotation);
	}
}
