package net.balamah.voiddim.entity.custom.ai.goal;

import java.util.Random;

import net.balamah.voiddim.entity.custom.ai.goal.base.TickingGoal;
import net.balamah.voiddim.entity.custom.base.CorruptedHostileEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;
import net.minecraft.world.World;

public class PlaceSignsGoal<T extends CorruptedHostileEntity> extends TickingGoal<T> {
	protected final Random random = new Random();
	protected final String[] signLines;
	protected final int upperBondChance;

	protected int finalTick;

	/**
	 * @param signLines is an String[] array of 4 elements max. Which looks like {"First", "second", "third", "fourth"}
	 */
	public PlaceSignsGoal(T entity, String[] signLines, int upperBondChance) {
		super(entity);

		this.signLines = signLines;
		this.upperBondChance = upperBondChance;
	}

	@Override
	public boolean canStart() {
		int randomNumber = this.random.nextInt(this.upperBondChance);

		return randomNumber == this.upperBondChance - 1;
	}

	@Override
	public boolean shouldContinue() {
		return this.tick <= this.finalTick;
	}

	@Override
	public void start() {
		this.finalTick = this.random.nextInt(15);
	}

	@Override
	public void tick() {
		super.tick();

		int randomSignCount = this.random.nextInt(5);
		int signCount = Math.max(1, randomSignCount);

		for (int i = 0; i < signCount; i++) {
			BlockPos tableBlockPos = this.getRandomBlockPos(this.world, 2);
			// TODO: adjust facing for the tables according to this.entity facing
			this.world.setBlockState(tableBlockPos, Blocks.PALE_OAK_SIGN.getDefaultState());

			SignBlockEntity signBlockEntity = (SignBlockEntity) this.world.getBlockEntity(tableBlockPos);
			this.setSignText(signBlockEntity, this.signLines);
		}
	}

	protected BlockPos getRandomBlockPos(World world, int diameter) {
		int x = this.getRandomCoordinate(this.entity.getX(), diameter);
		int y = (int) this.entity.getY();
		int z = this.getRandomCoordinate(this.entity.getZ(), diameter);

		return new BlockPos(x, y, z);
	}

	// TODO: give method getRandomCoordinate better name
	protected int getRandomCoordinate(double number, int diameter) {
		return (int) (number + this.random.nextInt(diameter) * 3);
	}

	protected void setSignText(SignBlockEntity signBlockEntity, String[] signLines) {
		SignText signText = signBlockEntity.getFrontText();

		for (int i = 0; i < signLines.length; i++) {
			String signLineText = signLines[i];
			signText = signText.withMessage(i, Text.literal(signLineText));
		}

		signBlockEntity.setText(signText, true);
	}
}
