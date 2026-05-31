package net.balamah.voiddim.block.custom;

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
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.phys.BlockHitResult;

public class BedrockBombBlock extends Block {

    public BedrockBombBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        if (!level.isClientSide() && !player.isCreative()) {
            prime((ServerLevel) level, pos, player);
            level.removeBlock(pos, false);
        }
    }

    private boolean prime(Level level, BlockPos pos, LivingEntity igniter) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return false;
        }

        BedrockBombEntity bomb = new BedrockBombEntity(
            level,
            pos.getX() + 0.5,
            pos.getY(),
            pos.getZ() + 0.5,
            igniter
        );

        level.addFreshEntity(bomb);

        level.playSound(
            null,
            bomb.getX(),
            bomb.getY(),
            bomb.getZ(),
            SoundEvents.TNT_PRIMED,
            SoundSource.BLOCKS,
            1.0F,
            1.0F
        );

        return true;
    }

    @Override
    public void neighborChanged(
        BlockState state,
        Level level,
        BlockPos pos,
        Block neighborBlock,
        BlockPos neighborPos,
        boolean movedByPiston
    ) {
        if (level.hasNeighborSignal(pos)) {
            if (!level.isClientSide()) {
                prime(level, pos, null);
                level.removeBlock(pos, false);
            }
        }
    }

    @Override
    public InteractionResult useItemOn(
        ItemStack stack,
        BlockState state,
        Level level,
        BlockPos pos,
        Player player,
        InteractionHand hand,
        BlockHitResult hit
    ) {
        if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
            return super.useItemOn(stack, state, level, pos, player, hand, hit);
        }

        if (this.prime(level, pos, null)) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);

            Item item = stack.getItem();

            if (stack.is(Items.FLINT_AND_STEEL)) {
                stack.hurtAndBreak(1, player, hand);
            } else {
                stack.shrink(1);
            }

            player.awardStat(Stats.ITEM_USED.get(item));

            return InteractionResult.SUCCESS;
        }

        if (level instanceof ServerLevel serverLevel &&
            !serverLevel.getGameRules().getBoolean(GameRules.RULE_TNT_EXPLODES)) {

            player.displayClientMessage(
                Component.translatable("block.minecraft.tnt.disabled"),
                true
            );

            return InteractionResult.PASS;
        }

        return InteractionResult.SUCCESS;
    }
}
