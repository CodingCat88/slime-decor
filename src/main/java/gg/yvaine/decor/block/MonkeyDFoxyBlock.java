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

public class MonkeyDFoxyBlock extends Block implements Waterloggable {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public MonkeyDFoxyBlock() {
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
			case NORTH -> union(box(8, 4, 5, 10, 6, 7), box(5, 0, 7, 10, 6, 10), box(5, 0, 5, 7, 2, 7), box(8, 0, 5, 10, 2, 7), box(7, 0, 9, 12, 3, 12), box(5, 6, 7, 10, 10, 10), box(5, 12, 8, 7, 13, 9), box(8, 12, 8, 10, 13, 9), box(6, 6, 6, 9, 8, 7), box(5, 4, 5, 7, 6, 7), box(5, 10, 7, 10, 12, 10));
			case EAST -> union(box(9, 4, 8, 11, 6, 10), box(6, 0, 5, 9, 6, 10), box(9, 0, 5, 11, 2, 7), box(9, 0, 8, 11, 2, 10), box(4, 0, 7, 7, 3, 12), box(6, 6, 5, 9, 10, 10), box(7, 12, 5, 8, 13, 7), box(7, 12, 8, 8, 13, 10), box(9, 6, 6, 10, 8, 9), box(9, 4, 5, 11, 6, 7), box(6, 10, 5, 9, 12, 10));
			case WEST -> union(box(5, 4, 6, 7, 6, 8), box(7, 0, 6, 10, 6, 11), box(5, 0, 9, 7, 2, 11), box(5, 0, 6, 7, 2, 8), box(9, 0, 4, 12, 3, 9), box(7, 6, 6, 10, 10, 11), box(8, 12, 9, 9, 13, 11), box(8, 12, 6, 9, 13, 8), box(6, 6, 7, 7, 8, 10), box(5, 4, 9, 7, 6, 11), box(7, 10, 6, 10, 12, 11));
			default -> union(box(6, 4, 9, 8, 6, 11), box(6, 0, 6, 11, 6, 9), box(9, 0, 9, 11, 2, 11), box(6, 0, 9, 8, 2, 11), box(4, 0, 4, 9, 3, 7), box(6, 6, 6, 11, 10, 9), box(9, 12, 7, 11, 13, 8), box(6, 12, 7, 8, 13, 8), box(7, 6, 9, 10, 8, 10), box(9, 4, 9, 11, 6, 11), box(6, 10, 6, 11, 12, 9)); // South / Default
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