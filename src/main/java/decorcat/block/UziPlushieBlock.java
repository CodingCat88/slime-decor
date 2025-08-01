
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

public class UziPlushieBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public UziPlushieBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
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
			default -> Shapes.or(box(9, 0, 9, 11, 2, 11), box(5, 0, 9, 7, 2, 11), box(5, 0, 6, 11, 6, 9), box(11, 2, 6, 12, 5, 8), box(4, 2, 7, 5, 5, 9), box(4, 6, 4, 12, 13, 11), box(4, 13, 4, 12, 15, 11), box(5, 15, 5, 11, 16, 10),
					box(7, 16, 6.5, 9, 18, 8.5));
			case NORTH -> Shapes.or(box(5, 0, 5, 7, 2, 7), box(9, 0, 5, 11, 2, 7), box(5, 0, 7, 11, 6, 10), box(4, 2, 8, 5, 5, 10), box(11, 2, 7, 12, 5, 9), box(4, 6, 5, 12, 13, 12), box(4, 13, 5, 12, 15, 12), box(5, 15, 6, 11, 16, 11),
					box(7, 16, 7.5, 9, 18, 9.5));
			case EAST -> Shapes.or(box(9, 0, 5, 11, 2, 7), box(9, 0, 9, 11, 2, 11), box(6, 0, 5, 9, 6, 11), box(6, 2, 4, 8, 5, 5), box(7, 2, 11, 9, 5, 12), box(4, 6, 4, 11, 13, 12), box(4, 13, 4, 11, 15, 12), box(5, 15, 5, 10, 16, 11),
					box(6.5, 16, 7, 8.5, 18, 9));
			case WEST -> Shapes.or(box(5, 0, 9, 7, 2, 11), box(5, 0, 5, 7, 2, 7), box(7, 0, 5, 10, 6, 11), box(8, 2, 11, 10, 5, 12), box(7, 2, 4, 9, 5, 5), box(5, 6, 4, 12, 13, 12), box(5, 13, 4, 12, 15, 12), box(6, 15, 5, 11, 16, 11),
					box(7.5, 16, 7, 9.5, 18, 9));
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
