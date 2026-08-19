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

public class UziPlushieBlock extends Block implements Waterloggable {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public UziPlushieBlock() {
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

	public UziPlushieBlock(AbstractBlock.Settings settings) {
		super(settings);
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
			case NORTH -> union(box(5, 0, 5, 7, 2, 7), box(9, 0, 5, 11, 2, 7), box(5, 0, 7, 11, 6, 10), box(4, 2, 8, 5, 5, 10), box(11, 2, 7, 12, 5, 9), box(4, 6, 5, 12, 13, 12), box(4, 13, 5, 12, 15, 12), box(5, 15, 6, 11, 16, 11), box(7, 16, 7.5, 9, 18, 9.5));
			case EAST -> union(box(9, 0, 5, 11, 2, 7), box(9, 0, 9, 11, 2, 11), box(6, 0, 5, 9, 6, 11), box(6, 2, 4, 8, 5, 5), box(7, 2, 11, 9, 5, 12), box(4, 6, 4, 11, 13, 12), box(4, 13, 4, 11, 15, 12), box(5, 15, 5, 10, 16, 11), box(6.5, 16, 7, 8.5, 18, 9));
			case WEST -> union(box(5, 0, 9, 7, 2, 11), box(5, 0, 5, 7, 2, 7), box(7, 0, 5, 10, 6, 11), box(8, 2, 11, 10, 5, 12), box(7, 2, 4, 9, 5, 5), box(5, 6, 4, 12, 13, 12), box(5, 13, 4, 12, 15, 12), box(6, 15, 5, 11, 16, 11), box(7.5, 16, 7, 9.5, 18, 9));
			case SOUTH, UP, DOWN -> union(box(9, 0, 9, 11, 2, 11), box(5, 0, 9, 7, 2, 11), box(5, 0, 6, 11, 6, 9), box(11, 2, 6, 12, 5, 8), box(4, 2, 7, 5, 5, 9), box(4, 6, 4, 12, 13, 11), box(4, 13, 4, 12, 15, 11), box(5, 15, 5, 11, 16, 10), box(7, 16, 6.5, 9, 18, 8.5));
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