package net.balamah.voiddim.entity.custom.base;

import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.custom.McCodeHelper;

public abstract class CorruptedHostileEntity extends ModHostileEntity {
	public CorruptedHostileEntity(
		EntityType<? extends Monster> entityType, Level world
	) {
		super(entityType, world);
	}

	public void corruptBlock(Level world, BlockPos blockPos) {
		Block block = McCodeHelper.getBlock(world, blockPos);

		if (McCodeHelper.isBlockReplaceable(block)) {
			world.setBlockAndUpdate(blockPos, ModBlocks.CORRUPT_BLOCK.defaultBlockState());
		}
	}

	@Override
	protected void initTargets() {
		super.initTargets();

		this.targetSelector.addGoal(2, McCodeHelper.getTargetGoal(this, AgeableMob.class));
	}
}
