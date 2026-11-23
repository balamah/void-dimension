package net.balamah.voiddim.block.custom;

import java.util.Arrays;

import net.balamah.voiddim.block.ModBlocks;
import net.balamah.voiddim.custom.McCodeHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class DimmedTorchBlock extends BaseTorchBlock {
	public DimmedTorchBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected InteractionResult useItemOn(
		ItemStack itemStack, BlockState state, Level level, BlockPos pos,
		Player player, InteractionHand hand, BlockHitResult hitResult
	) {
		Item item = itemStack.getItem();
		boolean tryingToLit = Arrays.asList(McCodeHelper.littingItems).contains(item);
		Block futureBlock = (state.getBlock() == ModBlocks.DIMMED_TORCH) ?
			Blocks.TORCH : Blocks.WALL_TORCH;

		if (tryingToLit) {
			level.setBlockAndUpdate(pos, futureBlock.defaultBlockState());
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.TRY_WITH_EMPTY_HAND;
	}
}
