
package decorcat.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CreeperMugBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public CreeperMugBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOD).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
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
			default -> Shapes.or(box(6, 0, 7, 10, 1, 11), box(7.5, 3.5, 5, 8.5, 4.5, 7), box(7.5, 1, 5, 8.5, 2, 7), box(7.5, 2, 5, 8.5, 3.5, 6), box(7, 1, 7, 9, 5.5, 8), box(7, 1, 10, 9, 5.5, 11), box(6, 1, 7, 7, 5.5, 11), box(9, 1, 7, 10, 5.5, 11),
					box(7, 1, 8, 9, 5, 10));
			case NORTH -> Shapes.or(box(6, 0, 5, 10, 1, 9), box(7.5, 3.5, 9, 8.5, 4.5, 11), box(7.5, 1, 9, 8.5, 2, 11), box(7.5, 2, 10, 8.5, 3.5, 11), box(7, 1, 8, 9, 5.5, 9), box(7, 1, 5, 9, 5.5, 6), box(9, 1, 5, 10, 5.5, 9),
					box(6, 1, 5, 7, 5.5, 9), box(7, 1, 6, 9, 5, 8));
			case EAST -> Shapes.or(box(7, 0, 6, 11, 1, 10), box(5, 3.5, 7.5, 7, 4.5, 8.5), box(5, 1, 7.5, 7, 2, 8.5), box(5, 2, 7.5, 6, 3.5, 8.5), box(7, 1, 7, 8, 5.5, 9), box(10, 1, 7, 11, 5.5, 9), box(7, 1, 9, 11, 5.5, 10),
					box(7, 1, 6, 11, 5.5, 7), box(8, 1, 7, 10, 5, 9));
			case WEST -> Shapes.or(box(5, 0, 6, 9, 1, 10), box(9, 3.5, 7.5, 11, 4.5, 8.5), box(9, 1, 7.5, 11, 2, 8.5), box(10, 2, 7.5, 11, 3.5, 8.5), box(8, 1, 7, 9, 5.5, 9), box(5, 1, 7, 6, 5.5, 9), box(5, 1, 6, 9, 5.5, 7), box(5, 1, 9, 9, 5.5, 10),
					box(6, 1, 7, 8, 5, 9));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}
