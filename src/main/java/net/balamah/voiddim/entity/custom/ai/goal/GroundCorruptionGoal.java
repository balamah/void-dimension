package net.balamah.voiddim.entity.custom.ai.goal;

import net.balamah.voiddim.entity.custom.ai.goal.base.SlowMovementGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.balamah.voiddim.entity.custom.Entity303;
import net.balamah.voiddim.custom.McCodeHelper;

import java.util.Random;

import net.balamah.voiddim.block.ModBlocks;

// TODO: Finish some time
public class GroundCorruptionGoal<T extends Entity303> extends SlowMovementGoal<T> {
	protected boolean corruptedGroundPredicate;

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
	public boolean canUse() {
		LivingEntity target = this.entity.getTarget();

		return target != null && this.entity.attackCount >= 4 &&
			// this.entity.getGroundCorruptionCooldown() == 0 &&
			target.distanceTo(this.entity) <= 10;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.corruptedGroundPredicate;
	}

	@Override
	public void start() {
		super.start();

		this.addSpeedModifier();
		this.entity.makeSound(SoundEvents.ANVIL_BREAK);

		this.corruptedGroundPredicate = false;

		// TODO: Add animation
	}

	@Override
	public void stop() {
		super.stop();

		this.removeSpeedModifier();
		// this.entity.setGroundCorruptionCooldown(100);
	}

	@Override
	public void tick() {
		super.tick();

		System.out.println("Prediate --> " + this.corruptedGroundPredicate);

		if (this.tick == this.groundCorruptionTick) {
			// TODO: Implement corrupt block spreading
			this.corruptBlocksUnderEntity();
			this.corruptBlocksAround(this.corruptionRadius, this.corruptBlocksCount);

			this.corruptedGroundPredicate = true;
		}
	}

	protected void corruptBlocksUnderEntity() {
		for (int i = 0; i < 4; i++) {
			BlockPos blockPos = McCodeHelper.getBlockPosUnderEntity(this.entity, i);

			this.entity.corruptBlock(this.world, blockPos);
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

			this.entity.corruptBlock(this.world, new BlockPos(randomX, randomY, randomZ));
		}
	}

	protected int getRandomCoordinate(int startCoordinate, int endCoordinate) {
		return this.random.nextInt((endCoordinate - startCoordinate) + 1) + endCoordinate;
	}
}
