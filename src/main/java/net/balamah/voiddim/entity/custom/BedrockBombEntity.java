package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;
import net.balamah.voiddim.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BedrockBombEntity extends PrimedTnt {
	public BedrockBombEntity(EntityType<? extends BedrockBombEntity> entityType, Level world) {
		super(entityType, world);
	}

	public BedrockBombEntity(
		Level world, double x, double y, double z, @Nullable LivingEntity igniter
	) {
		super(world, x, y, z, igniter);
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.level().isClientSide() && this.getFuse() <= 0) {
			Level world = this.level();
			BlockPos center = this.blockPosition();
			int radius = 4;

			this.breakBlocks(world, center, radius);
		}
	}

	@Override
	public BlockState getBlockState() {
		return ModBlocks.BEDROCK_BOMB.defaultBlockState();
	}

	protected void breakBlocks(Level world, BlockPos center, int radius) {
		for (BlockPos pos : BlockPos.withinManhattan(center, radius, radius, radius)) {
			if (pos.distSqr(center) <= radius * radius) {
				world.destroyBlock(pos, false);
			}
		}
	}
}
