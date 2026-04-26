package net.balamah.voiddim.block.custom;

import org.jetbrains.annotations.Nullable;
import net.balamah.voiddim.entity.custom.BedrockBombEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;

public class BedrockBombBlock extends Block {
    public BedrockBombBlock(Properties settings) {
        super(settings);
    }

	@Override
	public void attack(
		BlockState state, Level world, BlockPos pos, Player player
	) {
        if (!world.isClientSide() && !player.isCreative()) {
            prime((ServerLevel) world, pos, player);
            world.removeBlock(pos, false);
        }
    }

    private boolean prime(Level world, BlockPos pos, @Nullable LivingEntity igniter) {
		if (!(world instanceof ServerLevel serverWorld)) {
			return false;
		}

        BedrockBombEntity bomb = new BedrockBombEntity(
            world,
            pos.getX() + 0.5,
            pos.getY(),
            pos.getZ() + 0.5,
            igniter
        );

        world.addFreshEntity(bomb);
        world.playSound(null,
            bomb.getX(), bomb.getY(), bomb.getZ(),
            SoundEvents.TNT_PRIMED,
            SoundSource.BLOCKS,
            1.0F, 1.0F
		);

		return true;
    }

    @Override
	protected void neighborChanged(
		BlockState state, Level world, BlockPos pos, Block sourceBlock,
		@org.jspecify.annotations.Nullable Orientation wireOrientation,
		boolean notify
	) {
        if (world.hasNeighborSignal(pos)) {
            if (!world.isClientSide()) {
                prime((ServerLevel) world, pos, null);
                world.removeBlock(pos, false);
            }
        }
	}

	@Override
	protected InteractionResult useItemOn(
		ItemStack stack, BlockState state, Level world,
		BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit
	) {
		if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
			return super.useItemOn(stack, state, world, pos, player, hand, hit);
		} else {
			if (this.prime(world, pos, null)) {
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL_IMMEDIATE);
				Item item = stack.getItem();
				if (stack.is(Items.FLINT_AND_STEEL)) {
					stack.hurtAndBreak(1, player, hand.asEquipmentSlot());
				} else {
					stack.consume(1, player);
				}

				player.awardStat(Stats.ITEM_USED.get(item));
				} else if (world instanceof ServerLevel serverWorld &&
							!serverWorld.getGameRules().get(GameRules.TNT_EXPLODES)
				) {
					player.sendOverlayMessage(Component.translatable("block.minecraft.tnt.disabled"));
					return InteractionResult.PASS;
				}

			return InteractionResult.SUCCESS;
		}
	}
}
