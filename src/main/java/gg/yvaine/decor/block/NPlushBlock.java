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

public class NPlushBlock extends Block implements Waterloggable {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public NPlushBlock() {
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
			case NORTH -> union(box(5, 0, 2, 7, 2, 4), box(9, 0, 2, 11, 2, 4), box(5, 0, 4, 11, 6, 7), box(4.5, 5.5, 3.5, 11.5, 6.5, 7.5), box(5, 13.5, 0, 11, 14.5, 2), box(4, 2, 5, 5, 5, 7), box(11, 2, 4, 12, 5, 6), box(4, 13.5, 2, 12, 15.5, 9), box(9, 0, 7, 10, 1, 9), box(8, 0, 9, 9, 1, 11), box(8, 0, 11, 10, 2, 14), box(4, 6.5, 2, 12, 13.5, 9), box(4, 15.5, 2, 12, 16.5, 6));
			case EAST -> union(box(12, 0, 5, 14, 2, 7), box(12, 0, 9, 14, 2, 11), box(9, 0, 5, 12, 6, 11), box(8.5, 5.5, 4.5, 12.5, 6.5, 11.5), box(14, 13.5, 5, 16, 14.5, 11), box(9, 2, 4, 11, 5, 5), box(10, 2, 11, 12, 5, 12), box(7, 13.5, 4, 14, 15.5, 12), box(7, 0, 9, 9, 1, 10), box(5, 0, 8, 7, 1, 9), box(2, 0, 8, 5, 2, 10), box(7, 6.5, 4, 14, 13.5, 12), box(10, 15.5, 4, 14, 16.5, 12));
			case WEST -> union(box(2, 0, 9, 4, 2, 11), box(2, 0, 5, 4, 2, 7), box(4, 0, 5, 7, 6, 11), box(3.5, 5.5, 4.5, 7.5, 6.5, 11.5), box(0, 13.5, 5, 2, 14.5, 11), box(5, 2, 11, 7, 5, 12), box(4, 2, 4, 6, 5, 5), box(2, 13.5, 4, 9, 15.5, 12), box(7, 0, 6, 9, 1, 7), box(9, 0, 7, 11, 1, 8), box(11, 0, 6, 14, 2, 8), box(2, 6.5, 4, 9, 13.5, 12), box(2, 15.5, 4, 6, 16.5, 12));
			default -> union(box(9, 0, 12, 11, 2, 14), box(5, 0, 12, 7, 2, 14), box(5, 0, 9, 11, 6, 12), box(4.5, 5.5, 8.5, 11.5, 6.5, 12.5), box(5, 13.5, 14, 11, 14.5, 16), box(11, 2, 9, 12, 5, 11), box(4, 2, 10, 5, 5, 12), box(4, 13.5, 7, 12, 15.5, 14), box(6, 0, 7, 7, 1, 9), box(7, 0, 5, 8, 1, 7), box(6, 0, 2, 8, 2, 5), box(4, 6.5, 7, 12, 13.5, 14), box(4, 15.5, 10, 12, 16.5, 14)); // South / Default
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