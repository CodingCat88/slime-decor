package gg.yvaine.decor.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
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

public class RekPlushieBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public RekPlushieBlock() {
        super(AbstractBlock.Settings.create()
                .sounds(BlockSoundGroup.WOOL)
                .strength(1.0f, 10.0f)
                .nonOpaque()
                .solidBlock((state, world, pos) -> false)
        );
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false));
    }

    public RekPlushieBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(WATERLOGGED, false));
    }

    @Override
    protected VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> union(box(3.5, 0, 6, 5.5, 2, 8), box(9.5, 0, 6, 11.5, 2, 8), box(3.5, 0, 11, 5.5, 2, 13), box(9.5, 0, 11, 11.5, 2, 13), box(5.5, 0, 5, 9.5, 4, 14), box(5, 1, 1, 10, 5, 5), box(5, 5, 3, 6, 6, 5), box(9, 5, 3, 10, 6, 5), box(2.5, 0, 14, 5.5, 1, 15), box(2, 0, 11, 3, 1, 15), box(4.5, 1, 14, 5.5, 2, 15), box(5.5, 0, 14, 6, 0.5, 15), box(5.5, 1, 14, 8, 2, 15), box(6, 5, 2, 9, 7, 5), box(6, 5, 0, 9, 6, 2));
            case EAST -> union(box(8, 0, 3.5, 10, 2, 5.5), box(8, 0, 9.5, 10, 2, 11.5), box(3, 0, 3.5, 5, 2, 5.5), box(3, 0, 9.5, 5, 2, 11.5), box(2, 0, 5.5, 11, 4, 9.5), box(11, 1, 5, 15, 5, 10), box(11, 5, 5, 13, 6, 6), box(11, 5, 9, 13, 6, 10), box(1, 0, 2.5, 2, 1, 5.5), box(1, 0, 2, 5, 1, 3), box(1, 1, 4.5, 2, 2, 5.5), box(1, 0, 5.5, 2, 0.5, 6), box(1, 1, 5.5, 2, 2, 8), box(11, 5, 6, 14, 7, 9), box(14, 5, 6, 16, 6, 9));
            case WEST -> union(box(6, 0, 10.5, 8, 2, 12.5), box(6, 0, 4.5, 8, 2, 6.5), box(11, 0, 10.5, 13, 2, 12.5), box(11, 0, 4.5, 13, 2, 6.5), box(5, 0, 6.5, 14, 4, 10.5), box(1, 1, 6, 5, 5, 11), box(3, 5, 10, 5, 6, 11), box(3, 5, 6, 5, 6, 7), box(14, 0, 10.5, 15, 1, 13.5), box(11, 0, 13, 15, 1, 14), box(14, 1, 10.5, 15, 2, 11.5), box(14, 0, 10, 15, 0.5, 10.5), box(14, 1, 8, 15, 2, 10.5), box(2, 5, 7, 5, 7, 10), box(0, 5, 7, 2, 6, 10));
            default -> union(box(10.5, 0, 8, 12.5, 2, 10), box(4.5, 0, 8, 6.5, 2, 10), box(10.5, 0, 3, 12.5, 2, 5), box(4.5, 0, 3, 6.5, 2, 5), box(6.5, 0, 2, 10.5, 4, 11), box(6, 1, 11, 11, 5, 15), box(10, 5, 11, 11, 6, 13), box(6, 5, 11, 7, 6, 13), box(10.5, 0, 1, 13.5, 1, 2), box(13, 0, 1, 14, 1, 5), box(10.5, 1, 1, 11.5, 2, 2), box(10, 0, 1, 10.5, 0.5, 2), box(8, 1, 1, 10.5, 2, 2), box(7, 5, 11, 10, 7, 14), box(7, 5, 14, 10, 6, 16)); // South / Default
        };
    }

    private VoxelShape union(VoxelShape first, VoxelShape... rest) {
        VoxelShape result = first;
        for (VoxelShape shape : rest) {
            result = VoxelShapes.union(result, shape);
        }
        return result;
    }

    private VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return Block.createCuboidShape(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).isOf(Fluids.WATER));
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}