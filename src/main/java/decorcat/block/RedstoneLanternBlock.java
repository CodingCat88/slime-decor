
package decorcat.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class RedstoneLanternBlock extends Block {
	public RedstoneLanternBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(3.5f).lightLevel(s -> 13).requiresCorrectToolForDrops().noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true)
				.isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.or(box(1, 0, 1, 3, 15, 3), box(3, 0, 1, 13, 2, 3), box(1, 0, 3, 3, 2, 13), box(13, 0, 13, 15, 15, 15), box(13, 0, 3, 15, 2, 13), box(1, 0, 13, 3, 15, 15), box(13, 0, 1, 15, 15, 3), box(3, 0, 13, 13, 2, 15),
				box(3, 0, 3, 13, 15, 13), box(1, 13, 3, 3, 15, 13), box(3, 13, 13, 13, 15, 15), box(13, 13, 3, 15, 15, 13), box(3, 13, 1, 13, 15, 3), box(4, 15, 4, 12, 18, 12));
	}
}
