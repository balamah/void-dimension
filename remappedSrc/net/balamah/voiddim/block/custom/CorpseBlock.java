package net.balamah.voiddim.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.level.ScheduledTickAccess;

public class CorpseBlock extends HorizontalFacingBlock implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public CorpseBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
            .with(Properties.HORIZONTAL_FACING, Direction.NORTH)
            .with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean water = ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER);
        return this.getDefaultState()
            .with(Properties.HORIZONTAL_FACING, ctx.getHorizontalPlayerFacing().getOpposite())
            .with(WATERLOGGED, water);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return (state.get(WATERLOGGED)) ?
			Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
	protected BlockState updateShape(
		BlockState state, WorldView world, ScheduledTickAccess tickView,
		BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState,
		Random random
	) {
        if (state.get(WATERLOGGED)) {
            tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

		return super.getStateForNeighborUpdate(
			state, world, tickView, pos, direction, neighborPos, neighborState, random
		);
	}

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(
			Properties.HORIZONTAL_FACING, rotation.rotate(state.get(Properties.HORIZONTAL_FACING))
		);
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(Properties.HORIZONTAL_FACING)));
    }

	@Override
	protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
		return createCodec(CorpseBlock::new);
	}
}
