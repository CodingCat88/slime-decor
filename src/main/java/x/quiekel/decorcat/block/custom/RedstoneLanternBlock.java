package x.quiekel.decorcat.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RedstoneLanternBlock extends Block {

    public RedstoneLanternBlock(Settings settings) {
        super(settings);
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
        return VoxelShapes.union(createCuboidShape(1, 0, 1, 3, 15, 3), createCuboidShape(3, 0, 1, 13, 2, 3), createCuboidShape(1, 0, 3, 3, 2, 13), createCuboidShape(13, 0, 13, 15, 15, 15), createCuboidShape(13, 0, 3, 15, 2, 13), createCuboidShape(1, 0, 13, 3, 15, 15), createCuboidShape(13, 0, 1, 15, 15, 3), createCuboidShape(3, 0, 13, 13, 2, 15),
                createCuboidShape(3, 0, 3, 13, 15, 13), createCuboidShape(1, 13, 3, 3, 15, 13), createCuboidShape(3, 13, 13, 13, 15, 15), createCuboidShape(13, 13, 3, 15, 15, 13), createCuboidShape(3, 13, 1, 13, 15, 3), createCuboidShape(4, 15, 4, 12, 18, 12));
    }
}
