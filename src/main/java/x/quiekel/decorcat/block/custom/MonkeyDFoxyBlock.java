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

public class MonkeyDFoxyBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public MonkeyDFoxyBlock(Settings settings) {
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
            default -> VoxelShapes.union(createCuboidShape(6, 4, 9, 8, 6, 11), createCuboidShape(6, 0, 6, 11, 6, 9), createCuboidShape(9, 0, 9, 11, 2, 11), createCuboidShape(6, 0, 9, 8, 2, 11), createCuboidShape(4, 0, 4, 9, 3, 7), createCuboidShape(6, 6, 6, 11, 10, 9), createCuboidShape(9, 12, 7, 11, 13, 8), createCuboidShape(6, 12, 7, 8, 13, 8),
                    createCuboidShape(7, 6, 9, 10, 8, 10), createCuboidShape(9, 4, 9, 11, 6, 11), createCuboidShape(6, 10, 6, 11, 12, 9));
            case NORTH -> VoxelShapes.union(createCuboidShape(8, 4, 5, 10, 6, 7), createCuboidShape(5, 0, 7, 10, 6, 10), createCuboidShape(5, 0, 5, 7, 2, 7), createCuboidShape(8, 0, 5, 10, 2, 7), createCuboidShape(7, 0, 9, 12, 3, 12), createCuboidShape(5, 6, 7, 10, 10, 10), createCuboidShape(5, 12, 8, 7, 13, 9), createCuboidShape(8, 12, 8, 10, 13, 9),
                    createCuboidShape(6, 6, 6, 9, 8, 7), createCuboidShape(5, 4, 5, 7, 6, 7), createCuboidShape(5, 10, 7, 10, 12, 10));
            case EAST -> VoxelShapes.union(createCuboidShape(9, 4, 8, 11, 6, 10), createCuboidShape(6, 0, 5, 9, 6, 10), createCuboidShape(9, 0, 5, 11, 2, 7), createCuboidShape(9, 0, 8, 11, 2, 10), createCuboidShape(4, 0, 7, 7, 3, 12), createCuboidShape(6, 6, 5, 9, 10, 10), createCuboidShape(7, 12, 5, 8, 13, 7), createCuboidShape(7, 12, 8, 8, 13, 10),
                    createCuboidShape(9, 6, 6, 10, 8, 9), createCuboidShape(9, 4, 5, 11, 6, 7), createCuboidShape(6, 10, 5, 9, 12, 10));
            case WEST -> VoxelShapes.union(createCuboidShape(5, 4, 6, 7, 6, 8), createCuboidShape(7, 0, 6, 10, 6, 11), createCuboidShape(5, 0, 9, 7, 2, 11), createCuboidShape(5, 0, 6, 7, 2, 8), createCuboidShape(9, 0, 4, 12, 3, 9), createCuboidShape(7, 6, 6, 10, 10, 11), createCuboidShape(8, 12, 9, 9, 13, 11), createCuboidShape(8, 12, 6, 9, 13, 8),
                    createCuboidShape(6, 6, 7, 7, 8, 10), createCuboidShape(5, 4, 9, 7, 6, 11), createCuboidShape(7, 10, 6, 10, 12, 11));
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
