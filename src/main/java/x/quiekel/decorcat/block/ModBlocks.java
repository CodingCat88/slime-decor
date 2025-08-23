package x.quiekel.decorcat.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import x.quiekel.decorcat.SlimeDecorMod;
import x.quiekel.decorcat.block.custom.*;


public class ModBlocks {

    public static final Block FOX_PLUSH_BLOCK = registerBlock(
            "fox_plush",
            new FoxPlushBlock(
                    FabricBlockSettings
                            .of(Material.AIR)
                            .sounds(BlockSoundGroup.WOOL)
                            .strength(1f, 10f)
                            .nonOpaque()
                            .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );

    public static final Block REDSTONE_LANTERN = registerBlockWithoutTab(
            "redstone_lantern",
            new RedstoneLanternBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.METAL)
                    .strength(3.5f)
                    .lightLevel(s -> 13)
                    .requiresTool()
                    .nonOpaque()
                    .postProcess((state, world, pos) -> true)
                    .emissiveLighting((state, world, pos) -> true)
                    .solidBlock((state, world, pos) -> false)
            )
    );

    public static final Block SLIME_PLUSH_BLOCK = registerBlock(
            "slime_plush",
            new SlimePlushBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.SLIME)
                    .strength(1f, 10f)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );

    public static final Block PROTO_TOASTER_BLOCK = registerBlock(
            "proto_toaster",
            new ProtoToasterBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.METAL)
                    .strength(1f, 10f)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );

    public static final Block N_PLUSH_BLOCK = registerBlock(
            "n_plush",
            new NPlushBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(1f, 10f)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );

    public static final Block CREEPER_MUG_BLOCK = registerBlock(
            "creeper_mug",
            new CreeperMugBlock(
                    FabricBlockSettings
                            .of(Material.AIR)
                            .sounds(BlockSoundGroup.WOOD)
                            .strength(1f, 10f)
                            .nonOpaque()
                            .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.DECOR
    );

    public static final Block UZI_PLUSHIE_BLOCK = registerBlock(
            "uzi_plushie",
            new UziPlushieBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(1f, 10f)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );

    public static final Block MONKEY_D_FOXY_BLOCK = registerBlock(
            "monkey_d_foxy",
            new MonkeyDFoxyBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(1f, 10f)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );
    public static final Block KIRBY_PLUSHIE_BLOCK = registerBlock(
            "kirby_plushie",
            new KirbyPlushieBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.CORAL)
                    .strength(1f, 10f)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );

    public static final Block MONKEY_D_LUFFY_PLUSHIE_BLOCK = registerBlock(
            "monkey_d_luffy_plushie",
            new MonkeyDLuffyPlushieBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(1f, 10f)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );

    public static final Block REKSTAR_PLUSHIE = registerBlock(
            "rekstar_plushie",
            new RekPlushieBlock(FabricBlockSettings
                    .of(Material.AIR)
                    .sounds(BlockSoundGroup.WOOL)
                    .strength(1f, 10f)
                    .nonOpaque()
                    .solidBlock((state, world, pos) -> false)
            ),
            ModItemGroup.PLUSHES
    );

    private static Block registerBlockWithoutTab(String name, Block block) {
        registerBlockItemWithoutTab(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(SlimeDecorMod.MOD_ID, name), block);
    }

    private static Item registerBlockItemWithoutTab(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(SlimeDecorMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(SlimeDecorMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(SlimeDecorMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        SlimeDecorMod.LOGGER.debug("Registering ModBlocks for " + SlimeDecorMod.MOD_ID);
    }

}
