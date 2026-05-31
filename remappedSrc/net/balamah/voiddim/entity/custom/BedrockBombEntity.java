package net.balamah.voiddim.entity.custom;

import org.jetbrains.annotations.Nullable;
import net.balamah.voiddim.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedrockBombEntity extends TntEntity {
	public BedrockBombEntity(EntityType<? extends BedrockBombEntity> entityType, World world) {
		super(entityType, world);
	}

	public BedrockBombEntity(
		World world, double x, double y, double z, @Nullable LivingEntity igniter
	) {
		super(world, x, y, z, igniter);
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.getWorld().isClient() && this.getFuse() <= 0) {
			World world = this.getWorld();
			BlockPos center = this.getBlockPos();
			int radius = 4;

			this.breakBlocks(world, center, radius);
		}
	}

	@Override
	public BlockState getBlockState() {
		return ModBlocks.BEDROCK_BOMB.getDefaultState();
	}

	protected void breakBlocks(World world, BlockPos center, int radius) {
		for (BlockPos pos : BlockPos.iterateOutwards(center, radius, radius, radius)) {
			if (pos.getSquaredDistance(center) <= radius * radius) {
				world.breakBlock(pos, false);
			}
		}
	}
}
