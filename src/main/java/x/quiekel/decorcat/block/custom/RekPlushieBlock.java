package x.quiekel.decorcat.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RekPlushieBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public RekPlushieBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            default -> VoxelShapes.union(createCuboidShape(10.5, 0, 8, 12.5, 2, 10), createCuboidShape(4.5, 0, 8, 6.5, 2, 10), createCuboidShape(10.5, 0, 3, 12.5, 2, 5), createCuboidShape(4.5, 0, 3, 6.5, 2, 5), createCuboidShape(6.5, 0, 2, 10.5, 4, 11), createCuboidShape(6, 1, 11, 11, 5, 15), createCuboidShape(10, 5, 11, 11, 6, 13),
                    createCuboidShape(6, 5, 11, 7, 6, 13), createCuboidShape(10.5, 0, 1, 13.5, 1, 2), createCuboidShape(13, 0, 1, 14, 1, 5), createCuboidShape(10.5, 1, 1, 11.5, 2, 2), createCuboidShape(10, 0, 1, 10.5, 0.5, 2), createCuboidShape(8, 1, 1, 10.5, 2, 2), createCuboidShape(7, 5, 11, 10, 7, 14), createCuboidShape(7, 5, 14, 10, 6, 16));
            case NORTH -> VoxelShapes.union(createCuboidShape(3.5, 0, 6, 5.5, 2, 8), createCuboidShape(9.5, 0, 6, 11.5, 2, 8), createCuboidShape(3.5, 0, 11, 5.5, 2, 13), createCuboidShape(9.5, 0, 11, 11.5, 2, 13), createCuboidShape(5.5, 0, 5, 9.5, 4, 14), createCuboidShape(5, 1, 1, 10, 5, 5), createCuboidShape(5, 5, 3, 6, 6, 5), createCuboidShape(9, 5, 3, 10, 6, 5),
                    createCuboidShape(2.5, 0, 14, 5.5, 1, 15), createCuboidShape(2, 0, 11, 3, 1, 15), createCuboidShape(4.5, 1, 14, 5.5, 2, 15), createCuboidShape(5.5, 0, 14, 6, 0.5, 15), createCuboidShape(5.5, 1, 14, 8, 2, 15), createCuboidShape(6, 5, 2, 9, 7, 5), createCuboidShape(6, 5, 0, 9, 6, 2));
            case EAST -> VoxelShapes.union(createCuboidShape(8, 0, 3.5, 10, 2, 5.5), createCuboidShape(8, 0, 9.5, 10, 2, 11.5), createCuboidShape(3, 0, 3.5, 5, 2, 5.5), createCuboidShape(3, 0, 9.5, 5, 2, 11.5), createCuboidShape(2, 0, 5.5, 11, 4, 9.5), createCuboidShape(11, 1, 5, 15, 5, 10), createCuboidShape(11, 5, 5, 13, 6, 6),
                    createCuboidShape(11, 5, 9, 13, 6, 10), createCuboidShape(1, 0, 2.5, 2, 1, 5.5), createCuboidShape(1, 0, 2, 5, 1, 3), createCuboidShape(1, 1, 4.5, 2, 2, 5.5), createCuboidShape(1, 0, 5.5, 2, 0.5, 6), createCuboidShape(1, 1, 5.5, 2, 2, 8), createCuboidShape(11, 5, 6, 14, 7, 9), createCuboidShape(14, 5, 6, 16, 6, 9));
            case WEST -> VoxelShapes.union(createCuboidShape(6, 0, 10.5, 8, 2, 12.5), createCuboidShape(6, 0, 4.5, 8, 2, 6.5), createCuboidShape(11, 0, 10.5, 13, 2, 12.5), createCuboidShape(11, 0, 4.5, 13, 2, 6.5), createCuboidShape(5, 0, 6.5, 14, 4, 10.5), createCuboidShape(1, 1, 6, 5, 5, 11), createCuboidShape(3, 5, 10, 5, 6, 11),
                    createCuboidShape(3, 5, 6, 5, 6, 7), createCuboidShape(14, 0, 10.5, 15, 1, 13.5), createCuboidShape(11, 0, 13, 15, 1, 14), createCuboidShape(14, 1, 10.5, 15, 2, 11.5), createCuboidShape(14, 0, 10, 15, 0.5, 10.5), createCuboidShape(14, 1, 8, 15, 2, 10.5), createCuboidShape(2, 5, 7, 5, 7, 10), createCuboidShape(0, 5, 7, 2, 6, 10));
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
}
