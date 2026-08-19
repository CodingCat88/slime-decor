package gg.yvaine.decor.Essential;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import gg.yvaine.decor.SlimesDecor;

public class SlimesDecorTabs {
    public static final ItemGroup PLUSHIES = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SlimesDecor.MODID, "plushies"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("item_group.slimes-decor.plushes"))
                    .icon(() -> new ItemStack(SlimesDecorItems.FOX_PLUSH))
                    .entries((displayContext, entries) -> {
                        entries.add(SlimesDecorItems.FOX_PLUSH);
                        entries.add(SlimesDecorItems.SLIME_PLUSH);
                        entries.add(SlimesDecorItems.PROTO_TOASTER);
                        entries.add(SlimesDecorItems.N_PLUSH);
                        entries.add(SlimesDecorItems.UZI_PLUSHIE);
                        entries.add(SlimesDecorItems.MONKEY_D_FOXY);
                        entries.add(SlimesDecorItems.KIRBY_PLUSHIE);
                        entries.add(SlimesDecorItems.MONKEY_D_LUFFY_PLUSHIE);
                        entries.add(SlimesDecorItems.REKSTAR_PLUSHIE);
                    })
                    .build()
    );

    public static final ItemGroup DECOR = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SlimesDecor.MODID, "decor"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("item_group.slimes-decor.decor"))
                    .icon(() -> new ItemStack(SlimesDecorItems.CREEPER_MUG))
                    .entries((displayContext, entries) -> {
                        entries.add(SlimesDecorItems.CREEPER_MUG);
                        entries.add(SlimesDecorItems.TNT_MUG);
                    })
                    .build()
    );

    public static void registerSlimesTabs() {
        // Called from your ModInitializer to load and register the creative mode tabs
    }
}