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

public class KirbyPlushieBlock extends Block implements Waterloggable {
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	public KirbyPlushieBlock() {
		super(AbstractBlock.Settings.create()
				.sounds(BlockSoundGroup.CORAL)
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
			case NORTH -> union(box(4, 0, 3, 6, 3, 5), box(10, 0, 3, 12, 3, 5), box(3, 0, 5, 13, 11, 13), box(1, 2, 8, 3, 6, 10), box(13, 2, 8, 15, 6, 10));
			case EAST -> union(box(11, 0, 4, 13, 3, 6), box(11, 0, 10, 13, 3, 12), box(3, 0, 3, 11, 11, 13), box(6, 2, 1, 8, 6, 3), box(6, 2, 13, 8, 6, 15));
			case WEST -> union(box(3, 0, 10, 5, 3, 12), box(3, 0, 4, 5, 3, 6), box(5, 0, 3, 13, 11, 13), box(8, 2, 13, 10, 6, 15), box(8, 2, 1, 10, 6, 3));
			default -> union(box(10, 0, 11, 12, 3, 13), box(4, 0, 11, 6, 3, 13), box(3, 0, 3, 13, 11, 11), box(13, 2, 6, 15, 6, 8), box(1, 2, 6, 3, 6, 8)); // South / Default
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