package x.quiekel.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import x.quiekel.SlimeDecor;
import x.quiekel.block.ModBlocks;

public class ModItemGroups {

    public static CreativeModeTab PLUSHES = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.tryBuild(SlimeDecor.MOD_ID, "plushes"),
                FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.FOX_PLUSH))
                        .title(Component.translatable("item_group.slime_decor.plushes"))
                        .displayItems((displayContext, entries) -> {
                            entries.accept(ModBlocks.FOX_PLUSH);
                            entries.accept(ModBlocks.SLIME_PLUSH);
                            entries.accept(ModBlocks.PROTO_TOASTER);
                            entries.accept(ModBlocks.N_PLUSH);
                            entries.accept(ModBlocks.UZI_PLUSHIE);
                            entries.accept(ModBlocks.MONKEY_D_FOXY);
                            entries.accept(ModBlocks.KIRBY_PLUSHIE);
                            entries.accept(ModBlocks.MONKEY_D_LUFFY_PLUSHIE);
                            entries.accept(ModBlocks.REKSTAR_PLUSHIE);
                        }).build());

    public static CreativeModeTab DECOR = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.tryBuild(SlimeDecor.MOD_ID, "decor"),
                FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.CREEPER_MUG))
                        .title(Component.translatable("item_group.slime_decor.decor"))
                        .displayItems((displayContext, entries) -> {
                            entries.accept(ModBlocks.CREEPER_MUG);
                            entries.accept(ModBlocks.TNT_MUG);
                        }).build());


    public static void registerItemGroups() {
        SlimeDecor.LOGGER.info("Registering Item Groups for " + SlimeDecor.MOD_ID);
    }
}
