package net.balamah.voiddim.entity.custom.ai.goal;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvents;
import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.balamah.voiddim.entity.custom.HerobrineEntity;
import net.balamah.voiddim.custom.McCodeHelper;

import java.util.Random;

import net.balamah.voiddim.block.ModBlocks;

public class GroundCorruptionGoal<T extends HerobrineEntity> extends SlowMovementGoal<T> {
	protected final Random random = new Random();

	protected final int groundCorruptionTick;
	protected final int corruptionRadius;
	protected final int corruptBlocksCount;

	public GroundCorruptionGoal(
		T entity, int groundCorruptionTick, int corruptionRadius, int corruptBlocksCount
	) {
		super(entity);

		this.groundCorruptionTick = groundCorruptionTick;
		this.corruptionRadius = corruptionRadius;
		this.corruptBlocksCount = corruptBlocksCount;
	}

	@Override
	public boolean canStart() {
		return this.entity.attackCount >= 4 && this.entity.getGroundCorruptionCooldown() == 0;
	}

	@Override
	public boolean shouldContinue() {
		return this.entity.getTarget() != null;
	}

	@Override
	public void start() {
		super.start();

		this.entity.setInvulnerable(true);
		this.addSpeedModifier();
		this.entity.playSound(SoundEvents.BLOCK_ANVIL_BREAK);

		// TODO: Add animation
	}

	@Override
	public void stop() {
		super.stop();

		this.entity.setInvulnerable(false);
		this.removeSpeedModifier();
	}

	@Override
	public void tick() {
		super.tick();

		if (this.tick == this.groundCorruptionTick) {
			// TODO: Implement corrupt block spreading
			this.corruptBlocksUnderEntity();
			this.corruptBlocksAround(this.corruptionRadius, this.corruptBlocksCount);
		}
	}

	protected void corruptBlock(BlockPos blockPos) {
		Block block = McCodeHelper.getBlock(this.world, blockPos);

		if (McCodeHelper.isBlockReplaceable(block)) {
			this.world.setBlockState(blockPos, ModBlocks.CORRUPT_BLOCK.getDefaultState());
		}
	}

	protected void corruptBlocksUnderEntity() {
		for (int i = 0; i < 4; i++) {
			BlockPos blockPos = McCodeHelper.getBlockPosUnderEntity(this.entity, i);

			this.corruptBlock(blockPos);
		}
	}

	protected void corruptBlocksAround(int radius, int corruptBlocksCount) {
		double entityX = this.entity.getX();
		double entityY = this.entity.getY();
		double entityZ = this.entity.getZ();
		
		int maxX = (int) entityX + radius;
		int maxY = (int) entityY;
		int maxZ = (int) entityZ + radius;
		int minX = (int) entityX - radius;
		int minY = (int) entityY - 1;
		int minZ = (int) entityZ - radius;

		for (int i = 0; i < corruptBlocksCount; i++) {
			int randomX = this.getRandomCoordinate(minX, maxX);
			int randomY = this.getRandomCoordinate(minY, maxY);
			int randomZ = this.getRandomCoordinate(minZ, maxZ);

			this.corruptBlock(new BlockPos(randomX, randomY, randomZ));
		}
	}

	protected int getRandomCoordinate(int startCoordinate, int endCoordinate) {
		return this.random.nextInt((endCoordinate - startCoordinate) + 1) + endCoordinate;
	}
}
