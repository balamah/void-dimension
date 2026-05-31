package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.Random;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.balamah.voiddim.custom.McCodeHelper;

public class RandomPlaceSignsGoal<T extends CorruptedHostileEntity> extends TickingGoal<T> {
	protected boolean placedSigns;

	protected final Random random = new Random();
	protected final String[] signLines;
	protected final int upperBondChance;
	protected final int maxPlacingRadius;
	protected final int maxSignsCount;

	protected int finalTick;

	public RandomPlaceSignsGoal(
		T entity, String[] signLines, int upperBondChance, int maxPlacingRadius, int maxSignsCount
	) {
		super(entity);

		this.signLines = signLines;
		this.upperBondChance = upperBondChance;
		this.maxPlacingRadius = maxPlacingRadius;
		this.maxSignsCount = maxSignsCount;
	}

	@Override
	public boolean canUse() {
		int randomNumber = this.random.nextInt(this.upperBondChance);

		return randomNumber == this.upperBondChance - 1 && this.entity.getTarget() != null;
	}

	@Override
	public boolean canContinueToUse() {
		return !this.placedSigns && this.entity.getTarget() != null;
	}

	@Override
	public void stop() {
		super.stop();

		this.placedSigns = false;
	}

	@Override
	public void tick() {
		super.tick();

		int randomSignCount = this.random.nextInt(this.maxSignsCount);
		int signCount = Math.max(1, randomSignCount);

		for (int i = 0; i < signCount; i++) {
			BlockPos tableBlockPos = this.getRandomBlockPos(this.world, this.maxPlacingRadius);
			if (!(McCodeHelper.getBlock(world, tableBlockPos).defaultBlockState().isAir())) {
				continue;
			}

			this.world.setBlockAndUpdate(tableBlockPos, Blocks.OAK_SIGN.defaultBlockState());
			BlockEntity blockEntity = this.world.getBlockEntity(tableBlockPos);
			SignBlockEntity signBlockEntity = (SignBlockEntity) blockEntity;
			if (signBlockEntity == null) {
				continue;
			}

			McCodeHelper.setSignText(signBlockEntity, this.signLines);

			this.placedSigns = true;
		}
	}

	protected BlockPos getRandomBlockPos(Level world, int diameter) {
		int x = this.getRandomizedCoordinate(this.entity.getX(), diameter);
		int y = (int) this.entity.getY();
		int z = this.getRandomizedCoordinate(this.entity.getZ(), diameter);

		return new BlockPos(x, y, z);
	}

	protected int getRandomizedCoordinate(double baseCoordinate, int maxDiameter) {
		return (int) (baseCoordinate + this.random.nextInt(maxDiameter));
	}
}
