package gg.yvaine.decor.Essential;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import gg.yvaine.decor.SlimesDecor;

public class SlimesDecorItems {
    public static final Item FOX_PLUSH = register("fox_plush", new BlockItem(SlimesDecorBlocks.FOX_PLUSH, new Item.Settings()));
    public static final Item REDSTONE_LANTERN = register("redstone_lantern", new BlockItem(SlimesDecorBlocks.REDSTONE_LANTERN, new Item.Settings()));
    public static final Item SLIME_PLUSH = register("slime_plush", new BlockItem(SlimesDecorBlocks.SLIME_PLUSH, new Item.Settings()));
    public static final Item PROTO_TOASTER = register("proto_toaster", new BlockItem(SlimesDecorBlocks.PROTO_TOASTER, new Item.Settings()));
    public static final Item N_PLUSH = register("n_plush", new BlockItem(SlimesDecorBlocks.N_PLUSH, new Item.Settings()));
    public static final Item CREEPER_MUG = register("creeper_mug", new BlockItem(SlimesDecorBlocks.CREEPER_MUG, new Item.Settings()));
    public static final Item UZI_PLUSHIE = register("uzi_plushie", new BlockItem(SlimesDecorBlocks.UZI_PLUSHIE, new Item.Settings()));
    public static final Item MONKEY_D_FOXY = register("monkey_d_foxy", new BlockItem(SlimesDecorBlocks.MONKEY_D_FOXY, new Item.Settings()));
    public static final Item KIRBY_PLUSHIE = register("kirby_plushie", new BlockItem(SlimesDecorBlocks.KIRBY_PLUSHIE, new Item.Settings()));
    public static final Item MONKEY_D_LUFFY_PLUSHIE = register("monkey_d_luffy_plushie", new BlockItem(SlimesDecorBlocks.MONKEY_D_LUFFY_PLUSHIE, new Item.Settings()));
    public static final Item REKSTAR_PLUSHIE = register("rekstar_plushie", new BlockItem(SlimesDecorBlocks.REKSTAR_PLUSHIE, new Item.Settings()));
    public static final Item TNT_MUG = register("tnt_mug", new BlockItem(SlimesDecorBlocks.TNT_MUG, new Item.Settings()));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SlimesDecor.MODID, name), item);
    }

    public static void registerSlimesItems() {
        // Called from your ModInitializer to ensure static fields are loaded and registered
    }
}