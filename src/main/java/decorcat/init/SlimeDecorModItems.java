
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package decorcat.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import decorcat.SlimeDecorMod;

public class SlimeDecorModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SlimeDecorMod.MODID);
	public static final RegistryObject<Item> FOX_PLUSH = block(SlimeDecorModBlocks.FOX_PLUSH);
	public static final RegistryObject<Item> REDSTONE_LANTERN = block(SlimeDecorModBlocks.REDSTONE_LANTERN);
	public static final RegistryObject<Item> SLIME_PLUSH = block(SlimeDecorModBlocks.SLIME_PLUSH);
	public static final RegistryObject<Item> PROTO_TOASTER = block(SlimeDecorModBlocks.PROTO_TOASTER);
	public static final RegistryObject<Item> N_PLUSH = block(SlimeDecorModBlocks.N_PLUSH);
	public static final RegistryObject<Item> CREEPER_MUG = block(SlimeDecorModBlocks.CREEPER_MUG);
	public static final RegistryObject<Item> UZI_PLUSHIE = block(SlimeDecorModBlocks.UZI_PLUSHIE);
	public static final RegistryObject<Item> MONKEY_D_FOXY = block(SlimeDecorModBlocks.MONKEY_D_FOXY);
	public static final RegistryObject<Item> KIRBY_PLUSHIE = block(SlimeDecorModBlocks.KIRBY_PLUSHIE);
	public static final RegistryObject<Item> MONKEY_D_LUFFY_PLUSHIE = block(SlimeDecorModBlocks.MONKEY_D_LUFFY_PLUSHIE);
	public static final RegistryObject<Item> REKSTAR_PLUSHIE = block(SlimeDecorModBlocks.REKSTAR_PLUSHIE);
	public static final RegistryObject<Item> TNT_MUG = block(SlimeDecorModBlocks.TNT_MUG);


	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
