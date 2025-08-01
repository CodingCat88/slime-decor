
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

public class FoxPlushBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public FoxPlushBlock() {
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
			default -> Shapes.or(box(6, 0, 7, 8, 2, 9), box(6, 2, 2, 11, 5, 9), box(9, 0, 3, 11, 2, 5), box(6, 0, 3, 8, 2, 5), box(7, 2, -2, 10, 5, 2), box(6, 2, 9, 11, 6, 12), box(9, 6, 11, 11, 7, 12), box(6, 6, 11, 8, 7, 12),
					box(7, 2, 12, 10, 4, 13), box(9, 0, 7, 11, 2, 9));
			case NORTH -> Shapes.or(box(8, 0, 7, 10, 2, 9), box(5, 2, 7, 10, 5, 14), box(5, 0, 11, 7, 2, 13), box(8, 0, 11, 10, 2, 13), box(6, 2, 14, 9, 5, 18), box(5, 2, 4, 10, 6, 7), box(5, 6, 4, 7, 7, 5), box(8, 6, 4, 10, 7, 5),
					box(6, 2, 3, 9, 4, 4), box(5, 0, 7, 7, 2, 9));
			case EAST -> Shapes.or(box(7, 0, 8, 9, 2, 10), box(2, 2, 5, 9, 5, 10), box(3, 0, 5, 5, 2, 7), box(3, 0, 8, 5, 2, 10), box(-2, 2, 6, 2, 5, 9), box(9, 2, 5, 12, 6, 10), box(11, 6, 5, 12, 7, 7), box(11, 6, 8, 12, 7, 10),
					box(12, 2, 6, 13, 4, 9), box(7, 0, 5, 9, 2, 7));
			case WEST -> Shapes.or(box(7, 0, 6, 9, 2, 8), box(7, 2, 6, 14, 5, 11), box(11, 0, 9, 13, 2, 11), box(11, 0, 6, 13, 2, 8), box(14, 2, 7, 18, 5, 10), box(4, 2, 6, 7, 6, 11), box(4, 6, 9, 5, 7, 11), box(4, 6, 6, 5, 7, 8),
					box(3, 2, 7, 4, 4, 10), box(7, 0, 9, 9, 2, 11));
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
