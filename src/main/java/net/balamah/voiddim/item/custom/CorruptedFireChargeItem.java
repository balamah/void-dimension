package net.balamah.voiddim.item.custom;

import net.balamah.voiddim.block.custom.CorruptedFireBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.FireChargeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

public class CorruptedFireChargeItem extends FireChargeItem {
	public CorruptedFireChargeItem(Item.Properties settings) {
		super(settings);
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos blockPos = context.getClickedPos();
		BlockState blockState = world.getBlockState(blockPos);
		boolean bl = false;
		if (!CampfireBlock.canLight(blockState) && !CandleBlock.canLight(blockState) && !CandleCakeBlock.canLight(blockState)) {
			blockPos = blockPos.relative(context.getClickedFace());
			if (CorruptedFireBlock.canBePlacedAt(world, blockPos, context.getHorizontalDirection())) {
				this.playSound(world, blockPos);
				world.setBlockAndUpdate(blockPos, CorruptedFireBlock.getState(world, blockPos));
				world.gameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, blockPos);
				bl = true;
			}
		} else {
			this.playSound(world, blockPos);
			world.setBlockAndUpdate(blockPos, blockState.setValue(BlockStateProperties.LIT, true));
			world.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockPos);
			bl = true;
		}

		if (bl) {
			context.getItemInHand().shrink(1);
			return InteractionResult.SUCCESS;
		} else {
			return InteractionResult.FAIL;
		}
	}

	private void playSound(Level world, BlockPos pos) {
		RandomSource random = world.getRandom();
		world.playSound(
			null, pos, SoundEvents.FIRECHARGE_USE, SoundSource.BLOCKS, 1.0F,
			(random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F
		);
	}
}
