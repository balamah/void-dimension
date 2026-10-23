package net.balamah.voiddim.entity.custom.base;

import net.balamah.voiddim.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;

public abstract class AnomalyEntity extends Animal {
	public AnomalyEntity(EntityType<? extends Animal> type, Level level) {
		super(type, level);
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		return level.getBlockState(pos.below()).is(ModBlocks.ABYSSIUM)
			? 10.0F
			: super.getWalkTargetValue(pos, level);
	}
}
