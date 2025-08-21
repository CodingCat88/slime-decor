package x.quiekel.decorcat.block;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import x.quiekel.decorcat.SlimeDecorMod;

public class ModItemGroup {

    public static final ItemGroup PLUSHES  = FabricItemGroupBuilder.build(
            new Identifier(SlimeDecorMod.MOD_ID, "plushes"),() -> new ItemStack(ModBlocks.FOX_PLUSH_BLOCK));

    public static final ItemGroup DECOR  = FabricItemGroupBuilder.build(
            new Identifier(SlimeDecorMod.MOD_ID, "decor"),() -> new ItemStack(ModBlocks.CREEPER_MUG_BLOCK));

}
