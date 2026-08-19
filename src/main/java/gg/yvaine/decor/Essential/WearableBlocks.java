package gg.yvaine.decor.Essential;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import gg.yvaine.decor.Essential.SlimesDecorItems; // Replace with your actual item register class

import java.util.Set;

public class WearableBlocks {
    public static final Set<Item> ALLOWED_BLOCKS = Set.of(
            SlimesDecorItems.FOX_PLUSH,
            SlimesDecorItems.REDSTONE_LANTERN,
            SlimesDecorItems.SLIME_PLUSH,
            SlimesDecorItems.PROTO_TOASTER,
            SlimesDecorItems.N_PLUSH,
            SlimesDecorItems.CREEPER_MUG,
            SlimesDecorItems.UZI_PLUSHIE,
            SlimesDecorItems.MONKEY_D_FOXY,
            SlimesDecorItems.KIRBY_PLUSHIE,
            SlimesDecorItems.MONKEY_D_LUFFY_PLUSHIE,
            SlimesDecorItems.REKSTAR_PLUSHIE,
            SlimesDecorItems.TNT_MUG

    );

    public static boolean isAllowed(Item item) {
        return ALLOWED_BLOCKS.contains(item);
    }
}