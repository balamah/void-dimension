package net.balamah.voiddim.block.custom;

import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

// NOTE: Most of code has been copied from WallTorchBlock
public class WallDimmedTorchBlock extends DimmedTorchBlock {
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	private static final Map<Direction, VoxelShape> SHAPES =
		Shapes.rotateHorizontal(Block.boxZ(5.0, 3.0, 13.0, 11.0, 16.0));

	public WallDimmedTorchBlock(Properties properties) {
		super(properties);
	}

	
	@Override
	protected VoxelShape getShape(
		final BlockState state, final BlockGetter level, final
		BlockPos pos, final CollisionContext context
	) {
		return getShape(state);
	}

	public static VoxelShape getShape(final BlockState state) {
		return SHAPES.get(state.getValue(FACING));
	}

	@Override
	protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
		return canSurvive(level, pos, state.getValue(FACING));
	}

	public static boolean canSurvive(
		final LevelReader level, final BlockPos pos, final Direction facing
	) {
		BlockPos relativePos = pos.relative(facing.getOpposite());
		BlockState relativeState = level.getBlockState(relativePos);
		return relativeState.isFaceSturdy(level, relativePos, facing);
	}

	@Override
	public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
		BlockState state = this.defaultBlockState();
		LevelReader level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Direction[] directions = context.getNearestLookingDirections();

		for (Direction direction : directions) {
			if (direction.getAxis().isHorizontal()) {
				Direction facing = direction.getOpposite();
				state = state.setValue(FACING, facing);
				if (state.canSurvive(level, pos)) {
					return state;
				}
			}
		}

		return null;
	}

	@Override
	protected BlockState updateShape(
		final BlockState state,
		final LevelReader level,
		final ScheduledTickAccess ticks,
		final BlockPos pos,
		final Direction directionToNeighbour,
		final BlockPos neighbourPos,
		final BlockState neighbourState,
		final RandomSource random
	) {
		return directionToNeighbour.getOpposite() == state.getValue(FACING) &&
			!state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : state;
	}

	@Override
	protected BlockState rotate(final BlockState state, final Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	protected BlockState mirror(final BlockState state, final Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(
		final StateDefinition.Builder<Block, BlockState> builder
	) {
		builder.add(FACING);
	}
}
