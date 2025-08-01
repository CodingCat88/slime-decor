
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package decorcat.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import decorcat.SlimeDecorMod;

public class SlimeDecorModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SlimeDecorMod.MODID);
	public static final RegistryObject<CreativeModeTab> PLUSHES = REGISTRY.register("plushes",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.slime_decor.plushes")).icon(() -> new ItemStack(SlimeDecorModBlocks.FOX_PLUSH.get())).displayItems((parameters, tabData) -> {
				tabData.accept(SlimeDecorModBlocks.FOX_PLUSH.get().asItem());
				tabData.accept(SlimeDecorModBlocks.SLIME_PLUSH.get().asItem());
				tabData.accept(SlimeDecorModBlocks.PROTO_TOASTER.get().asItem());
				tabData.accept(SlimeDecorModBlocks.N_PLUSH.get().asItem());
				tabData.accept(SlimeDecorModBlocks.UZI_PLUSHIE.get().asItem());
				tabData.accept(SlimeDecorModBlocks.MONKEY_D_FOXY.get().asItem());
				tabData.accept(SlimeDecorModBlocks.KIRBY_PLUSHIE.get().asItem());
				tabData.accept(SlimeDecorModBlocks.MONKEY_D_LUFFY_PLUSHIE.get().asItem());
			}).build());
	public static final RegistryObject<CreativeModeTab> DECOR = REGISTRY.register("decor",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.slime_decor.decor")).icon(() -> new ItemStack(SlimeDecorModBlocks.CREEPER_MUG.get())).displayItems((parameters, tabData) -> {
				tabData.accept(SlimeDecorModBlocks.CREEPER_MUG.get().asItem());
			}).withTabsBefore(PLUSHES.getId()).build());
}
