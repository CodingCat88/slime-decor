package gg.yvaine.decor.Essential;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import gg.yvaine.decor.SlimesDecor;
import gg.yvaine.decor.block.*;

public class SlimesDecorBlocks {

    public static final Block FOX_PLUSH = registerBlock("fox_plush", new FoxPlushBlock());
    public static final Block REDSTONE_LANTERN = registerBlock("redstone_lantern", new RedstoneLanternBlock());
    public static final Block SLIME_PLUSH = registerBlock("slime_plush", new SlimePlushBlock());
    public static final Block PROTO_TOASTER = registerBlock("proto_toaster", new ProtoToasterBlock());
    public static final Block N_PLUSH = registerBlock("n_plush", new NPlushBlock());
    public static final Block CREEPER_MUG = registerBlock("creeper_mug", new CreeperMugBlock());
    public static final Block UZI_PLUSHIE = registerBlock("uzi_plushie", new UziPlushieBlock());
    public static final Block MONKEY_D_FOXY = registerBlock("monkey_d_foxy", new MonkeyDFoxyBlock());
    public static final Block KIRBY_PLUSHIE = registerBlock("kirby_plushie", new KirbyPlushieBlock());
    public static final Block MONKEY_D_LUFFY_PLUSHIE = registerBlock("monkey_d_luffy_plushie", new MonkeyDLuffyPlushieBlock());
    public static final Block REKSTAR_PLUSHIE = registerBlock("rekstar_plushie", new RekPlushieBlock());
    public static final Block TNT_MUG = registerBlock("tnt_mug", new TNTMugBlock());

    /**
     * Registers both the Block and its corresponding BlockItem automatically.
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SlimesDecor.MODID, name), block);
    }

    /**
     * Internal method to register the BlockItem.
     */
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(SlimesDecor.MODID, name),
                new BlockItem(block, new Item.Settings()));
    }

    /**
     * Use this if you ever need a block that DOES NOT have a highly-held item
     * (like crops, tall grass, or technical blocks).
     */
    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(SlimesDecor.MODID, name), block);
    }

    public static void registerSlimesBlocks() {
        SlimesDecor.LOGGER.info("Registering Mod Blocks for " + SlimesDecor.MODID);
    }
}