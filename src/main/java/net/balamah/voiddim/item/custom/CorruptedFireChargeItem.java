package net.balamah.voiddim.item.custom;

import net.minecraft.state.property.Properties;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.event.GameEvent;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.block.CampfireBlock;
import net.minecraft.item.FireChargeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.CandleBlock;
import net.minecraft.util.ActionResult;
import net.minecraft.sound.SoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.world.World;
import net.minecraft.item.Item;

import net.balamah.voiddim.block.custom.CorruptedFireBlock;

public class CorruptedFireChargeItem extends FireChargeItem {
	public CorruptedFireChargeItem(Item.Settings settings) {
		super(settings);
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockState blockState = world.getBlockState(blockPos);
		boolean bl = false;
		if (!CampfireBlock.canBeLit(blockState) && !CandleBlock.canBeLit(blockState) && !CandleCakeBlock.canBeLit(blockState)) {
			blockPos = blockPos.offset(context.getSide());
			if (CorruptedFireBlock.canPlaceAt(world, blockPos, context.getHorizontalPlayerFacing())) {
				this.playUseSound(world, blockPos);
				world.setBlockState(blockPos, CorruptedFireBlock.getState(world, blockPos));
				world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_PLACE, blockPos);
				bl = true;
			}
		} else {
			this.playUseSound(world, blockPos);
			world.setBlockState(blockPos, blockState.with(Properties.LIT, true));
			world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockPos);
			bl = true;
		}

		if (bl) {
			context.getStack().decrement(1);
			return ActionResult.SUCCESS;
		} else {
			return ActionResult.FAIL;
		}
	}

	private void playUseSound(World world, BlockPos pos) {
		Random random = world.getRandom();
		world.playSound(
			null, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F,
			(random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F
		);
	}
}
