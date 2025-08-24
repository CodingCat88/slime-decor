
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package decorcat.init;

import decorcat.block.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import decorcat.SlimeDecorMod;

public class SlimeDecorModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, SlimeDecorMod.MODID);
	public static final RegistryObject<Block> FOX_PLUSH = REGISTRY.register("fox_plush", () -> new FoxPlushBlock());
	public static final RegistryObject<Block> REDSTONE_LANTERN = REGISTRY.register("redstone_lantern", () -> new RedstoneLanternBlock());
	public static final RegistryObject<Block> SLIME_PLUSH = REGISTRY.register("slime_plush", () -> new SlimePlushBlock());
	public static final RegistryObject<Block> PROTO_TOASTER = REGISTRY.register("proto_toaster", () -> new ProtoToasterBlock());
	public static final RegistryObject<Block> N_PLUSH = REGISTRY.register("n_plush", () -> new NPlushBlock());
	public static final RegistryObject<Block> CREEPER_MUG = REGISTRY.register("creeper_mug", () -> new CreeperMugBlock());
	public static final RegistryObject<Block> UZI_PLUSHIE = REGISTRY.register("uzi_plushie", () -> new UziPlushieBlock());
	public static final RegistryObject<Block> MONKEY_D_FOXY = REGISTRY.register("monkey_d_foxy", () -> new MonkeyDFoxyBlock());
	public static final RegistryObject<Block> KIRBY_PLUSHIE = REGISTRY.register("kirby_plushie", () -> new KirbyPlushieBlock());
	public static final RegistryObject<Block> MONKEY_D_LUFFY_PLUSHIE = REGISTRY.register("monkey_d_luffy_plushie", () -> new MonkeyDLuffyPlushieBlock());
	public static final RegistryObject<Block> REKSTAR_PLUSHIE = REGISTRY.register("rekstar_plushie", () -> new RekPlushieBlock());
	public static final RegistryObject<Block> TNT_MUG = REGISTRY.register("tnt_mug", () -> new TNTMugBlock());

}
