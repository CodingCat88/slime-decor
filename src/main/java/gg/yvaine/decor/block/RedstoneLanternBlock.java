package gg.yvaine.decor.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RedstoneLanternBlock extends Block {
	public RedstoneLanternBlock() {
		super(AbstractBlock.Settings.create()
				.sounds(BlockSoundGroup.METAL)
				.strength(3.5f)
				.luminance(state -> 13)
				.requiresTool()
				.nonOpaque()
				.postProcess((state, world, pos) -> true)
				.emissiveLighting((state, world, pos) -> true)
				.solidBlock((state, world, pos) -> false)
		);
	}

	@Override
	protected VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}

	@Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return union(
				box(1, 0, 1, 3, 15, 3),
				box(3, 0, 1, 13, 2, 3),
				box(1, 0, 3, 3, 2, 13),
				box(13, 0, 13, 15, 15, 15),
				box(13, 0, 3, 15, 2, 13),
				box(1, 0, 13, 3, 15, 15),
				box(13, 0, 1, 15, 15, 3),
				box(3, 0, 13, 13, 2, 15),
				box(3, 0, 3, 13, 15, 13),
				box(1, 13, 3, 3, 15, 13),
				box(3, 13, 13, 13, 15, 15),
				box(13, 13, 3, 15, 15, 13),
				box(3, 13, 1, 13, 15, 3),
				box(4, 15, 4, 12, 18, 12)
		);
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
}