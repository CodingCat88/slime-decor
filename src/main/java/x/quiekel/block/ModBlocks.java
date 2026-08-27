package x.quiekel.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import x.quiekel.SlimeDecor;
import x.quiekel.block.custom.*;

public class ModBlocks {

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

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.tryBuild(SlimeDecor.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(SlimeDecor.MOD_ID, name),
                new BlockItem(block, new Item.Properties()));
    }

    public static void registerModBlocks() {
        SlimeDecor.LOGGER.info("Registering Mod Blocks for " + SlimeDecor.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {

        });
    }

}
