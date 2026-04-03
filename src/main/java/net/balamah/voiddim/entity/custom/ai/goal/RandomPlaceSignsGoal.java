package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.Random;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;

import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.balamah.voiddim.custom.McCodeHelper;

public class RandomPlaceSignsGoal<T extends CorruptedHostileEntity> extends TickingGoal<T> {
	protected boolean placedSigns;

	protected final Random random = new Random();
	protected final String[] signLines;
	protected final int upperBondChance;
	protected final int maxPlacingRadius;

	protected int finalTick;

	public RandomPlaceSignsGoal(
		T entity, String[] signLines, int upperBondChance, int maxPlacingRadius
	) {
		super(entity);

		this.signLines = signLines;
		this.upperBondChance = upperBondChance;
		this.maxPlacingRadius = maxPlacingRadius;
	}

	@Override
	public boolean canStart() {
		int randomNumber = this.random.nextInt(this.upperBondChance);

		return randomNumber == this.upperBondChance - 1;
	}

	@Override
	public boolean shouldContinue() {
		return !this.placedSigns;
	}

	@Override
	public void stop() {
		super.stop();

		this.placedSigns = false;
	}

	@Override
	public void tick() {
		super.tick();

		int randomSignCount = this.random.nextInt(2);
		int signCount = Math.max(1, randomSignCount);

		for (int i = 0; i < signCount; i++) {
			BlockPos tableBlockPos = this.getRandomBlockPos(this.world, this.maxPlacingRadius);
			this.world.setBlockState(tableBlockPos, Blocks.PALE_OAK_SIGN.getDefaultState());

			BlockEntity blockEntity = this.world.getBlockEntity(tableBlockPos);
			SignBlockEntity signBlockEntity = (SignBlockEntity) blockEntity;
			if (signBlockEntity == null) {
				continue;
			}

			McCodeHelper.setSignText(signBlockEntity, this.signLines);

			this.placedSigns = true;
		}
	}

	protected BlockPos getRandomBlockPos(World world, int diameter) {
		int x = this.getRandomizedCoordinate(this.entity.getX(), diameter);
		int y = (int) this.entity.getY();
		int z = this.getRandomizedCoordinate(this.entity.getZ(), diameter);

		return new BlockPos(x, y, z);
	}

	protected int getRandomizedCoordinate(double baseCoordinate, int maxDiameter) {
		return (int) (baseCoordinate + this.random.nextInt(maxDiameter));
	}
}
