package x.quiekel.decorcat.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.Direction;

import java.util.stream.Stream;

public class CreeperMugBlock extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public CreeperMugBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
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
            default -> VoxelShapes.union(createCuboidShape(6, 0, 7, 10, 1, 11), createCuboidShape(7.5, 3.5, 5, 8.5, 4.5, 7), createCuboidShape(7.5, 1, 5, 8.5, 2, 7), createCuboidShape(7.5, 2, 5, 8.5, 3.5, 6), createCuboidShape(7, 1, 7, 9, 5.5, 8), createCuboidShape(7, 1, 10, 9, 5.5, 11), createCuboidShape(6, 1, 7, 7, 5.5, 11), createCuboidShape(9, 1, 7, 10, 5.5, 11),
                    createCuboidShape(7, 1, 8, 9, 5, 10));
            case NORTH -> VoxelShapes.union(createCuboidShape(6, 0, 5, 10, 1, 9), createCuboidShape(7.5, 3.5, 9, 8.5, 4.5, 11), createCuboidShape(7.5, 1, 9, 8.5, 2, 11), createCuboidShape(7.5, 2, 10, 8.5, 3.5, 11), createCuboidShape(7, 1, 8, 9, 5.5, 9), createCuboidShape(7, 1, 5, 9, 5.5, 6), createCuboidShape(9, 1, 5, 10, 5.5, 9),
                    createCuboidShape(6, 1, 5, 7, 5.5, 9), createCuboidShape(7, 1, 6, 9, 5, 8));
            case EAST -> VoxelShapes.union(createCuboidShape(7, 0, 6, 11, 1, 10), createCuboidShape(5, 3.5, 7.5, 7, 4.5, 8.5), createCuboidShape(5, 1, 7.5, 7, 2, 8.5), createCuboidShape(5, 2, 7.5, 6, 3.5, 8.5), createCuboidShape(7, 1, 7, 8, 5.5, 9), createCuboidShape(10, 1, 7, 11, 5.5, 9), createCuboidShape(7, 1, 9, 11, 5.5, 10),
                    createCuboidShape(7, 1, 6, 11, 5.5, 7), createCuboidShape(8, 1, 7, 10, 5, 9));
            case WEST -> VoxelShapes.union(createCuboidShape(5, 0, 6, 9, 1, 10), createCuboidShape(9, 3.5, 7.5, 11, 4.5, 8.5), createCuboidShape(9, 1, 7.5, 11, 2, 8.5), createCuboidShape(10, 2, 7.5, 11, 3.5, 8.5), createCuboidShape(8, 1, 7, 9, 5.5, 9), createCuboidShape(5, 1, 7, 6, 5.5, 9), createCuboidShape(5, 1, 6, 9, 5.5, 7), createCuboidShape(5, 1, 9, 9, 5.5, 10),
                    createCuboidShape(6, 1, 7, 8, 5, 9));
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

