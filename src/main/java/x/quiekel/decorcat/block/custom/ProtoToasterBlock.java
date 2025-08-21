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

public class ProtoToasterBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public ProtoToasterBlock(Settings settings) {
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
            default -> VoxelShapes.union(createCuboidShape(4, 0, 5, 12, 7, 13), createCuboidShape(4, 7, 12, 12, 8, 13), createCuboidShape(4, 7, 5, 12, 8, 6), createCuboidShape(10, 7, 6, 12, 8, 12), createCuboidShape(4, 7, 6, 6, 8, 12), createCuboidShape(9, 7, 7, 10, 9, 11), createCuboidShape(6, 7, 7, 7, 9, 11), createCuboidShape(7, 7, 6, 9, 8, 12),
                    createCuboidShape(12, 2, 7, 13, 5, 10), createCuboidShape(12, 3, 6, 13, 4, 7), createCuboidShape(3, 2, 6, 4, 5, 9), createCuboidShape(3, 3, 5, 4, 4, 6), createCuboidShape(7, 2, 2, 9, 4, 5), createCuboidShape(7, 3, 0, 9, 6, 2), createCuboidShape(7, 6, 0, 9, 7, 1), createCuboidShape(7, 2, 1, 9, 3, 2));
            case NORTH -> VoxelShapes.union(createCuboidShape(4, 0, 3, 12, 7, 11), createCuboidShape(4, 7, 3, 12, 8, 4), createCuboidShape(4, 7, 10, 12, 8, 11), createCuboidShape(4, 7, 4, 6, 8, 10), createCuboidShape(10, 7, 4, 12, 8, 10), createCuboidShape(6, 7, 5, 7, 9, 9), createCuboidShape(9, 7, 5, 10, 9, 9), createCuboidShape(7, 7, 4, 9, 8, 10),
                    createCuboidShape(3, 2, 6, 4, 5, 9), createCuboidShape(3, 3, 9, 4, 4, 10), createCuboidShape(12, 2, 7, 13, 5, 10), createCuboidShape(12, 3, 10, 13, 4, 11), createCuboidShape(7, 2, 11, 9, 4, 14), createCuboidShape(7, 3, 14, 9, 6, 16), createCuboidShape(7, 6, 15, 9, 7, 16), createCuboidShape(7, 2, 14, 9, 3, 15));
            case EAST -> VoxelShapes.union(createCuboidShape(5, 0, 4, 13, 7, 12), createCuboidShape(12, 7, 4, 13, 8, 12), createCuboidShape(5, 7, 4, 6, 8, 12), createCuboidShape(6, 7, 4, 12, 8, 6), createCuboidShape(6, 7, 10, 12, 8, 12), createCuboidShape(7, 7, 6, 11, 9, 7), createCuboidShape(7, 7, 9, 11, 9, 10), createCuboidShape(6, 7, 7, 12, 8, 9),
                    createCuboidShape(7, 2, 3, 10, 5, 4), createCuboidShape(6, 3, 3, 7, 4, 4), createCuboidShape(6, 2, 12, 9, 5, 13), createCuboidShape(5, 3, 12, 6, 4, 13), createCuboidShape(2, 2, 7, 5, 4, 9), createCuboidShape(0, 3, 7, 2, 6, 9), createCuboidShape(0, 6, 7, 1, 7, 9), createCuboidShape(1, 2, 7, 2, 3, 9));
            case WEST -> VoxelShapes.union(createCuboidShape(3, 0, 4, 11, 7, 12), createCuboidShape(3, 7, 4, 4, 8, 12), createCuboidShape(10, 7, 4, 11, 8, 12), createCuboidShape(4, 7, 10, 10, 8, 12), createCuboidShape(4, 7, 4, 10, 8, 6), createCuboidShape(5, 7, 9, 9, 9, 10), createCuboidShape(5, 7, 6, 9, 9, 7), createCuboidShape(4, 7, 7, 10, 8, 9),
                    createCuboidShape(6, 2, 12, 9, 5, 13), createCuboidShape(9, 3, 12, 10, 4, 13), createCuboidShape(7, 2, 3, 10, 5, 4), createCuboidShape(10, 3, 3, 11, 4, 4), createCuboidShape(11, 2, 7, 14, 4, 9), createCuboidShape(14, 3, 7, 16, 6, 9), createCuboidShape(15, 6, 7, 16, 7, 9), createCuboidShape(14, 2, 7, 15, 3, 9));
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
