package x.quiekel.decorcat.block.custom;

import net.minecraft.block.*;
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

public class KirbyPlushieBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public KirbyPlushieBlock(Settings settings) {
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
            default -> VoxelShapes.union(createCuboidShape(10, 0, 11, 12, 3, 13), createCuboidShape(4, 0, 11, 6, 3, 13), createCuboidShape(3, 0, 3, 13, 11, 11), createCuboidShape(13, 2, 6, 15, 6, 8), createCuboidShape(1, 2, 6, 3, 6, 8));
            case NORTH -> VoxelShapes.union(createCuboidShape(4, 0, 3, 6, 3, 5), createCuboidShape(10, 0, 3, 12, 3, 5), createCuboidShape(3, 0, 5, 13, 11, 13), createCuboidShape(1, 2, 8, 3, 6, 10), createCuboidShape(13, 2, 8, 15, 6, 10));
            case EAST -> VoxelShapes.union(createCuboidShape(11, 0, 4, 13, 3, 6), createCuboidShape(11, 0, 10, 13, 3, 12), createCuboidShape(3, 0, 3, 11, 11, 13), createCuboidShape(6, 2, 1, 8, 6, 3), createCuboidShape(6, 2, 13, 8, 6, 15));
            case WEST -> VoxelShapes.union(createCuboidShape(3, 0, 10, 5, 3, 12), createCuboidShape(3, 0, 4, 5, 3, 6), createCuboidShape(5, 0, 3, 13, 11, 13), createCuboidShape(8, 2, 13, 10, 6, 15), createCuboidShape(8, 2, 1, 10, 6, 3));
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
