
package decorcat.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class MonkeyDFoxyBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public MonkeyDFoxyBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(6, 4, 9, 8, 6, 11), box(6, 0, 6, 11, 6, 9), box(9, 0, 9, 11, 2, 11), box(6, 0, 9, 8, 2, 11), box(4, 0, 4, 9, 3, 7), box(6, 6, 6, 11, 10, 9), box(9, 12, 7, 11, 13, 8), box(6, 12, 7, 8, 13, 8),
					box(7, 6, 9, 10, 8, 10), box(9, 4, 9, 11, 6, 11), box(6, 10, 6, 11, 12, 9));
			case NORTH -> Shapes.or(box(8, 4, 5, 10, 6, 7), box(5, 0, 7, 10, 6, 10), box(5, 0, 5, 7, 2, 7), box(8, 0, 5, 10, 2, 7), box(7, 0, 9, 12, 3, 12), box(5, 6, 7, 10, 10, 10), box(5, 12, 8, 7, 13, 9), box(8, 12, 8, 10, 13, 9),
					box(6, 6, 6, 9, 8, 7), box(5, 4, 5, 7, 6, 7), box(5, 10, 7, 10, 12, 10));
			case EAST -> Shapes.or(box(9, 4, 8, 11, 6, 10), box(6, 0, 5, 9, 6, 10), box(9, 0, 5, 11, 2, 7), box(9, 0, 8, 11, 2, 10), box(4, 0, 7, 7, 3, 12), box(6, 6, 5, 9, 10, 10), box(7, 12, 5, 8, 13, 7), box(7, 12, 8, 8, 13, 10),
					box(9, 6, 6, 10, 8, 9), box(9, 4, 5, 11, 6, 7), box(6, 10, 5, 9, 12, 10));
			case WEST -> Shapes.or(box(5, 4, 6, 7, 6, 8), box(7, 0, 6, 10, 6, 11), box(5, 0, 9, 7, 2, 11), box(5, 0, 6, 7, 2, 8), box(9, 0, 4, 12, 3, 9), box(7, 6, 6, 10, 10, 11), box(8, 12, 9, 9, 13, 11), box(8, 12, 6, 9, 13, 8),
					box(6, 6, 7, 7, 8, 10), box(5, 4, 9, 7, 6, 11), box(7, 10, 6, 10, 12, 11));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}
}
