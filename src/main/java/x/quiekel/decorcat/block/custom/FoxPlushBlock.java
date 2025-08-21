package x.quiekel.decorcat.block.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
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

import java.util.stream.Stream;

public class FoxPlushBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public FoxPlushBlock(Settings settings) {
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
            default -> VoxelShapes.union(createCuboidShape(6, 0, 7, 8, 2, 9), createCuboidShape(6, 2, 2, 11, 5, 9), createCuboidShape(9, 0, 3, 11, 2, 5), createCuboidShape(6, 0, 3, 8, 2, 5), createCuboidShape(7, 2, -2, 10, 5, 2), createCuboidShape(6, 2, 9, 11, 6, 12), createCuboidShape(9, 6, 11, 11, 7, 12), createCuboidShape(6, 6, 11, 8, 7, 12),
                    createCuboidShape(7, 2, 12, 10, 4, 13), createCuboidShape(9, 0, 7, 11, 2, 9));
            case NORTH -> VoxelShapes.union(createCuboidShape(8, 0, 7, 10, 2, 9), createCuboidShape(5, 2, 7, 10, 5, 14), createCuboidShape(5, 0, 11, 7, 2, 13), createCuboidShape(8, 0, 11, 10, 2, 13), createCuboidShape(6, 2, 14, 9, 5, 18), createCuboidShape(5, 2, 4, 10, 6, 7), createCuboidShape(5, 6, 4, 7, 7, 5), createCuboidShape(8, 6, 4, 10, 7, 5),
                    createCuboidShape(6, 2, 3, 9, 4, 4), createCuboidShape(5, 0, 7, 7, 2, 9));
            case EAST -> VoxelShapes.union(createCuboidShape(7, 0, 8, 9, 2, 10), createCuboidShape(2, 2, 5, 9, 5, 10), createCuboidShape(3, 0, 5, 5, 2, 7), createCuboidShape(3, 0, 8, 5, 2, 10), createCuboidShape(-2, 2, 6, 2, 5, 9), createCuboidShape(9, 2, 5, 12, 6, 10), createCuboidShape(11, 6, 5, 12, 7, 7), createCuboidShape(11, 6, 8, 12, 7, 10),
                    createCuboidShape(12, 2, 6, 13, 4, 9), createCuboidShape(7, 0, 5, 9, 2, 7));
            case WEST -> VoxelShapes.union(createCuboidShape(7, 0, 6, 9, 2, 8), createCuboidShape(7, 2, 6, 14, 5, 11), createCuboidShape(11, 0, 9, 13, 2, 11), createCuboidShape(11, 0, 6, 13, 2, 8), createCuboidShape(14, 2, 7, 18, 5, 10), createCuboidShape(4, 2, 6, 7, 6, 11), createCuboidShape(4, 6, 9, 5, 7, 11), createCuboidShape(4, 6, 6, 5, 7, 8),
                    createCuboidShape(3, 2, 7, 4, 4, 10), createCuboidShape(7, 0, 9, 9, 2, 11));
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


