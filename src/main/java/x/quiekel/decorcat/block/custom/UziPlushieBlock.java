package x.quiekel.decorcat.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
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

public class UziPlushieBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public UziPlushieBlock(Settings settings) {
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
            default -> VoxelShapes.union(createCuboidShape(9, 0, 9, 11, 2, 11), createCuboidShape(5, 0, 9, 7, 2, 11), createCuboidShape(5, 0, 6, 11, 6, 9), createCuboidShape(11, 2, 6, 12, 5, 8), createCuboidShape(4, 2, 7, 5, 5, 9), createCuboidShape(4, 6, 4, 12, 13, 11), createCuboidShape(4, 13, 4, 12, 15, 11), createCuboidShape(5, 15, 5, 11, 16, 10),
                    createCuboidShape(7, 16, 6.5, 9, 18, 8.5));
            case NORTH -> VoxelShapes.union(createCuboidShape(5, 0, 5, 7, 2, 7), createCuboidShape(9, 0, 5, 11, 2, 7), createCuboidShape(5, 0, 7, 11, 6, 10), createCuboidShape(4, 2, 8, 5, 5, 10), createCuboidShape(11, 2, 7, 12, 5, 9), createCuboidShape(4, 6, 5, 12, 13, 12), createCuboidShape(4, 13, 5, 12, 15, 12), createCuboidShape(5, 15, 6, 11, 16, 11),
                    createCuboidShape(7, 16, 7.5, 9, 18, 9.5));
            case EAST -> VoxelShapes.union(createCuboidShape(9, 0, 5, 11, 2, 7), createCuboidShape(9, 0, 9, 11, 2, 11), createCuboidShape(6, 0, 5, 9, 6, 11), createCuboidShape(6, 2, 4, 8, 5, 5), createCuboidShape(7, 2, 11, 9, 5, 12), createCuboidShape(4, 6, 4, 11, 13, 12), createCuboidShape(4, 13, 4, 11, 15, 12), createCuboidShape(5, 15, 5, 10, 16, 11),
                    createCuboidShape(6.5, 16, 7, 8.5, 18, 9));
            case WEST -> VoxelShapes.union(createCuboidShape(5, 0, 9, 7, 2, 11), createCuboidShape(5, 0, 5, 7, 2, 7), createCuboidShape(7, 0, 5, 10, 6, 11), createCuboidShape(8, 2, 11, 10, 5, 12), createCuboidShape(7, 2, 4, 9, 5, 5), createCuboidShape(5, 6, 4, 12, 13, 12), createCuboidShape(5, 13, 4, 12, 15, 12), createCuboidShape(6, 15, 5, 11, 16, 11),
                    createCuboidShape(7.5, 16, 7, 9.5, 18, 9));
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
