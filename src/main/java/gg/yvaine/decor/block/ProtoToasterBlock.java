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

public class ProtoToasterBlock extends Block implements Waterloggable {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public ProtoToasterBlock() {
		super(AbstractBlock.Settings.create()
				.sounds(BlockSoundGroup.METAL)
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
			case NORTH -> union(box(4, 0, 3, 12, 7, 11), box(4, 7, 3, 12, 8, 4), box(4, 7, 10, 12, 8, 11), box(4, 7, 4, 6, 8, 10), box(10, 7, 4, 12, 8, 10), box(6, 7, 5, 7, 9, 9), box(9, 7, 5, 10, 9, 9), box(7, 7, 4, 9, 8, 10), box(3, 2, 6, 4, 5, 9), box(3, 3, 9, 4, 4, 10), box(12, 2, 7, 13, 5, 10), box(12, 3, 10, 13, 4, 11), box(7, 2, 11, 9, 4, 14), box(7, 3, 14, 9, 6, 16), box(7, 6, 15, 9, 7, 16), box(7, 2, 14, 9, 3, 15));
			case EAST -> union(box(5, 0, 4, 13, 7, 12), box(12, 7, 4, 13, 8, 12), box(5, 7, 4, 6, 8, 12), box(6, 7, 4, 12, 8, 6), box(6, 7, 10, 12, 8, 12), box(7, 7, 6, 11, 9, 7), box(7, 7, 9, 11, 9, 10), box(6, 7, 7, 12, 8, 9), box(7, 2, 3, 10, 5, 4), box(6, 3, 3, 7, 4, 4), box(6, 2, 12, 9, 5, 13), box(5, 3, 12, 6, 4, 13), box(2, 2, 7, 5, 4, 9), box(0, 3, 7, 2, 6, 9), box(0, 6, 7, 1, 7, 9), box(1, 2, 7, 2, 3, 9));
			case WEST -> union(box(3, 0, 4, 11, 7, 12), box(3, 7, 4, 4, 8, 12), box(10, 7, 4, 11, 8, 12), box(4, 7, 10, 10, 8, 12), box(4, 7, 4, 10, 8, 6), box(5, 7, 9, 9, 9, 10), box(5, 7, 6, 9, 9, 7), box(4, 7, 7, 10, 8, 9), box(6, 2, 12, 9, 5, 13), box(9, 3, 12, 10, 4, 13), box(7, 2, 3, 10, 5, 4), box(10, 3, 3, 11, 4, 4), box(11, 2, 7, 14, 4, 9), box(14, 3, 7, 16, 6, 9), box(15, 6, 7, 16, 7, 9), box(14, 2, 7, 15, 3, 9));
			default -> union(box(4, 0, 5, 12, 7, 13), box(4, 7, 12, 12, 8, 13), box(4, 7, 5, 12, 8, 6), box(10, 7, 6, 12, 8, 12), box(4, 7, 6, 6, 8, 12), box(9, 7, 7, 10, 9, 11), box(6, 7, 7, 7, 9, 11), box(7, 7, 6, 9, 8, 12), box(12, 2, 7, 13, 5, 10), box(12, 3, 6, 13, 4, 7), box(3, 2, 6, 4, 5, 9), box(3, 3, 5, 4, 4, 6), box(7, 2, 2, 9, 4, 5), box(7, 3, 0, 9, 6, 2), box(7, 6, 0, 9, 7, 1), box(7, 2, 1, 9, 3, 2)); // South / Default
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