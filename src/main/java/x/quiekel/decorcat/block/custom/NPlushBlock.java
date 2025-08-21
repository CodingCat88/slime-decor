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

public class NPlushBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public NPlushBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
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
            default -> VoxelShapes.union(createCuboidShape(9, 0, 12, 11, 2, 14), createCuboidShape(5, 0, 12, 7, 2, 14), createCuboidShape(5, 0, 9, 11, 6, 12), createCuboidShape(4.5, 5.5, 8.5, 11.5, 6.5, 12.5), createCuboidShape(5, 13.5, 14, 11, 14.5, 16), createCuboidShape(11, 2, 9, 12, 5, 11), createCuboidShape(4, 2, 10, 5, 5, 12),
                    createCuboidShape(4, 13.5, 7, 12, 15.5, 14), createCuboidShape(6, 0, 7, 7, 1, 9), createCuboidShape(7, 0, 5, 8, 1, 7), createCuboidShape(6, 0, 2, 8, 2, 5), createCuboidShape(4, 6.5, 7, 12, 13.5, 14), createCuboidShape(4, 15.5, 10, 12, 16.5, 14));
            case NORTH -> VoxelShapes.union(createCuboidShape(5, 0, 2, 7, 2, 4), createCuboidShape(9, 0, 2, 11, 2, 4), createCuboidShape(5, 0, 4, 11, 6, 7), createCuboidShape(4.5, 5.5, 3.5, 11.5, 6.5, 7.5), createCuboidShape(5, 13.5, 0, 11, 14.5, 2), createCuboidShape(4, 2, 5, 5, 5, 7), createCuboidShape(11, 2, 4, 12, 5, 6), createCuboidShape(4, 13.5, 2, 12, 15.5, 9),
                    createCuboidShape(9, 0, 7, 10, 1, 9), createCuboidShape(8, 0, 9, 9, 1, 11), createCuboidShape(8, 0, 11, 10, 2, 14), createCuboidShape(4, 6.5, 2, 12, 13.5, 9), createCuboidShape(4, 15.5, 2, 12, 16.5, 6));
            case EAST -> VoxelShapes.union(createCuboidShape(12, 0, 5, 14, 2, 7), createCuboidShape(12, 0, 9, 14, 2, 11), createCuboidShape(9, 0, 5, 12, 6, 11), createCuboidShape(8.5, 5.5, 4.5, 12.5, 6.5, 11.5), createCuboidShape(14, 13.5, 5, 16, 14.5, 11), createCuboidShape(9, 2, 4, 11, 5, 5), createCuboidShape(10, 2, 11, 12, 5, 12),
                    createCuboidShape(7, 13.5, 4, 14, 15.5, 12), createCuboidShape(7, 0, 9, 9, 1, 10), createCuboidShape(5, 0, 8, 7, 1, 9), createCuboidShape(2, 0, 8, 5, 2, 10), createCuboidShape(7, 6.5, 4, 14, 13.5, 12), createCuboidShape(10, 15.5, 4, 14, 16.5, 12));
            case WEST -> VoxelShapes.union(createCuboidShape(2, 0, 9, 4, 2, 11), createCuboidShape(2, 0, 5, 4, 2, 7), createCuboidShape(4, 0, 5, 7, 6, 11), createCuboidShape(3.5, 5.5, 4.5, 7.5, 6.5, 11.5), createCuboidShape(0, 13.5, 5, 2, 14.5, 11), createCuboidShape(5, 2, 11, 7, 5, 12), createCuboidShape(4, 2, 4, 6, 5, 5), createCuboidShape(2, 13.5, 4, 9, 15.5, 12),
                    createCuboidShape(7, 0, 6, 9, 1, 7), createCuboidShape(9, 0, 7, 11, 1, 8), createCuboidShape(11, 0, 6, 14, 2, 8), createCuboidShape(2, 6.5, 4, 9, 13.5, 12), createCuboidShape(2, 15.5, 4, 6, 16.5, 12));
        };
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        boolean flag = ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER;
        return super.getPlacementState(ctx).with(FACING, ctx.getPlayerFacing().getOpposite()).with(WATERLOGGED, flag);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
