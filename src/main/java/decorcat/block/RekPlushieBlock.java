package decorcat.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RekPlushieBlock extends Block{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public RekPlushieBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    public RekPlushieBlock(Properties p_49795_) {
        super(p_49795_);
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
            default -> Shapes.or(box(10.5, 0, 8, 12.5, 2, 10), box(4.5, 0, 8, 6.5, 2, 10), box(10.5, 0, 3, 12.5, 2, 5), box(4.5, 0, 3, 6.5, 2, 5), box(6.5, 0, 2, 10.5, 4, 11), box(6, 1, 11, 11, 5, 15), box(10, 5, 11, 11, 6, 13),
                    box(6, 5, 11, 7, 6, 13), box(10.5, 0, 1, 13.5, 1, 2), box(13, 0, 1, 14, 1, 5), box(10.5, 1, 1, 11.5, 2, 2), box(10, 0, 1, 10.5, 0.5, 2), box(8, 1, 1, 10.5, 2, 2), box(7, 5, 11, 10, 7, 14), box(7, 5, 14, 10, 6, 16));
            case NORTH -> Shapes.or(box(3.5, 0, 6, 5.5, 2, 8), box(9.5, 0, 6, 11.5, 2, 8), box(3.5, 0, 11, 5.5, 2, 13), box(9.5, 0, 11, 11.5, 2, 13), box(5.5, 0, 5, 9.5, 4, 14), box(5, 1, 1, 10, 5, 5), box(5, 5, 3, 6, 6, 5), box(9, 5, 3, 10, 6, 5),
                    box(2.5, 0, 14, 5.5, 1, 15), box(2, 0, 11, 3, 1, 15), box(4.5, 1, 14, 5.5, 2, 15), box(5.5, 0, 14, 6, 0.5, 15), box(5.5, 1, 14, 8, 2, 15), box(6, 5, 2, 9, 7, 5), box(6, 5, 0, 9, 6, 2));
            case EAST -> Shapes.or(box(8, 0, 3.5, 10, 2, 5.5), box(8, 0, 9.5, 10, 2, 11.5), box(3, 0, 3.5, 5, 2, 5.5), box(3, 0, 9.5, 5, 2, 11.5), box(2, 0, 5.5, 11, 4, 9.5), box(11, 1, 5, 15, 5, 10), box(11, 5, 5, 13, 6, 6),
                    box(11, 5, 9, 13, 6, 10), box(1, 0, 2.5, 2, 1, 5.5), box(1, 0, 2, 5, 1, 3), box(1, 1, 4.5, 2, 2, 5.5), box(1, 0, 5.5, 2, 0.5, 6), box(1, 1, 5.5, 2, 2, 8), box(11, 5, 6, 14, 7, 9), box(14, 5, 6, 16, 6, 9));
            case WEST -> Shapes.or(box(6, 0, 10.5, 8, 2, 12.5), box(6, 0, 4.5, 8, 2, 6.5), box(11, 0, 10.5, 13, 2, 12.5), box(11, 0, 4.5, 13, 2, 6.5), box(5, 0, 6.5, 14, 4, 10.5), box(1, 1, 6, 5, 5, 11), box(3, 5, 10, 5, 6, 11),
                    box(3, 5, 6, 5, 6, 7), box(14, 0, 10.5, 15, 1, 13.5), box(11, 0, 13, 15, 1, 14), box(14, 1, 10.5, 15, 2, 11.5), box(14, 0, 10, 15, 0.5, 10.5), box(14, 1, 8, 15, 2, 10.5), box(2, 5, 7, 5, 7, 10), box(0, 5, 7, 2, 6, 10));
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

