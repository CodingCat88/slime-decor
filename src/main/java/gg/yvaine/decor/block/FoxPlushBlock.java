package gg.yvaine.decor.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class FoxPlushBlock extends Block implements Waterloggable {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public FoxPlushBlock() {
		super(AbstractBlock.Settings.create()
				.sounds(BlockSoundGroup.WOOL)
				.strength(1.0f, 10.0f)
				.nonOpaque()
				.solidBlock((state, world, pos) -> false)
		);
		this.setDefaultState(this.getStateManager().getDefaultState()
				.with(FACING, Direction.NORTH)
				.with(WATERLOGGED, false));
	}

	@Override
	protected VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return switch (state.get(FACING)) {
			case NORTH -> union(box(8, 0, 7, 10, 2, 9), box(5, 2, 7, 10, 5, 14), box(5, 0, 11, 7, 2, 13), box(8, 0, 11, 10, 2, 13), box(6, 2, 14, 9, 5, 18), box(5, 2, 4, 10, 6, 7), box(5, 6, 4, 7, 7, 5), box(8, 6, 4, 10, 7, 5), box(6, 2, 3, 9, 4, 4), box(5, 0, 7, 7, 2, 9));
			case EAST -> union(box(7, 0, 8, 9, 2, 10), box(2, 2, 5, 9, 5, 10), box(3, 0, 5, 5, 2, 7), box(3, 0, 8, 5, 2, 10), box(-2, 2, 6, 2, 5, 9), box(9, 2, 5, 12, 6, 10), box(11, 6, 5, 12, 7, 7), box(11, 6, 8, 12, 7, 10), box(12, 2, 6, 13, 4, 9), box(7, 0, 5, 9, 2, 7));
			case WEST -> union(box(7, 0, 6, 9, 2, 8), box(7, 2, 6, 14, 5, 11), box(11, 0, 9, 13, 2, 11), box(11, 0, 6, 13, 2, 8), box(14, 2, 7, 18, 5, 10), box(4, 2, 6, 7, 6, 11), box(4, 6, 9, 5, 7, 11), box(4, 6, 6, 5, 7, 8), box(3, 2, 7, 4, 4, 10), box(7, 0, 9, 9, 2, 11));
			default -> union(box(6, 0, 7, 8, 2, 9), box(6, 2, 2, 11, 5, 9), box(9, 0, 3, 11, 2, 5), box(6, 0, 3, 8, 2, 5), box(7, 2, -2, 10, 5, 2), box(6, 2, 9, 11, 6, 12), box(9, 6, 11, 11, 7, 12), box(6, 6, 11, 8, 7, 12), box(7, 2, 12, 10, 4, 13), box(9, 0, 7, 11, 2, 9));
		};
	}

	private VoxelShape union(VoxelShape first, VoxelShape... rest) {
		VoxelShape result = first;
		for (VoxelShape shape : rest) {
			result = VoxelShapes.union(result, shape);
		}
		return result;
	}

	private VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		return Block.createCuboidShape(minX, minY, minZ, maxX, maxY, maxZ);
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState()
				.with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
				.with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
	}

	@Override
	protected BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
}