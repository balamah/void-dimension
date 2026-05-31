package net.balamah.voiddim.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class CorpseBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {

    public CorpseBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
            .setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        boolean water = ctx.getLevel().getFluidState(ctx.getClickedPos()).is(Fluids.WATER);
        return this.defaultBlockState()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite())
            .setValue(BlockStateProperties.WATERLOGGED, water);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED)
            ? Fluids.WATER.getSource(false)
            : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(
        BlockState state,
        LevelAccessor level,
        BlockPos pos,
        Direction direction,
        BlockPos neighborPos,
        BlockState neighborState,
        RandomSource random
    ) {
        if (state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, level, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(
            BlockStateProperties.HORIZONTAL_FACING,
            rotation.rotate(state.getValue(BlockStateProperties.HORIZONTAL_FACING))
        );
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(
            mirror.getRotation(state.getValue(BlockStateProperties.HORIZONTAL_FACING))
        );
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return simpleCodec(CorpseBlock::new);
    }
}
