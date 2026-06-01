package net.balamah.voiddim.block.custom;

import org.jetbrains.annotations.Nullable;
import net.balamah.voiddim.entity.custom.BedrockBombEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.redstone.Orientation;

public class BedrockBombBlock extends Block {
    public BedrockBombBlock(Settings settings) {
        super(settings);
    }

	@Override
	public void onBlockBreakStart(
		BlockState state, World world, BlockPos pos, PlayerEntity player
	) {
        if (!world.isClient() && !player.isCreative()) {
            prime((ServerWorld) world, pos, player);
            world.removeBlock(pos, false);
        }
    }

    private boolean prime(World world, BlockPos pos, @Nullable LivingEntity igniter) {
		if (!(world instanceof ServerWorld serverWorld)) {
			return false;
		}

        BedrockBombEntity bomb = new BedrockBombEntity(
            world,
            pos.getX() + 0.5,
            pos.getY(),
            pos.getZ() + 0.5,
            igniter
        );

        world.spawnEntity(bomb);
        world.playSound(null,
            bomb.getX(), bomb.getY(), bomb.getZ(),
            SoundEvents.ENTITY_TNT_PRIMED,
            SoundCategory.BLOCKS,
            1.0F, 1.0F
		);

		return true;
    }

    @Override
	protected void neighborChanged(
		BlockState state, World world, BlockPos pos, Block sourceBlock,
		@org.jspecify.annotations.Nullable Orientation wireOrientation,
		boolean notify
	) {
        if (world.isReceivingRedstonePower(pos)) {
            if (!world.isClient()) {
                prime((ServerWorld) world, pos, null);
                world.removeBlock(pos, false);
            }
        }
	}

	@Override
	protected ActionResult useItemOn(
		ItemStack stack, BlockState state, World world,
		BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit
	) {
		if (!stack.isOf(Items.FLINT_AND_STEEL) && !stack.isOf(Items.FIRE_CHARGE)) {
			return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
		} else {
			if (this.prime(world, pos, null)) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL_AND_REDRAW);
				Item item = stack.getItem();
				if (stack.isOf(Items.FLINT_AND_STEEL)) {
					stack.damage(1, player, hand.asEquipmentSlot());
				} else {
					stack.decrementUnlessCreative(1, player);
				}

				player.incrementStat(Stats.USED.getOrCreateStat(item));
				} else if (world instanceof ServerWorld serverWorld &&
							!serverWorld.getGameRules().get(GameRules.TNT_EXPLODES)
				) {
					player.sendOverlayMessage(Text.translatable("block.minecraft.tnt.disabled"));
					return ActionResult.PASS;
				}

			return ActionResult.SUCCESS;
		}
	}
}
