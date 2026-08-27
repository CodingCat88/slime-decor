package x.quiekel;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import x.quiekel.block.ModBlocks;

public class SlimeDecorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(
                ModBlocks.MONKEY_D_LUFFY_PLUSHIE,
                RenderType.translucent()
        );
    }
}
