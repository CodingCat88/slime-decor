package gg.yvaine.decor.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import gg.yvaine.decor.Essential.WearableBlocks;

public class ServerPacketHandlers {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(WearBlockPayload.ID, (payload, context) -> {
            var player = context.player();

            context.server().execute(() -> {
                ItemStack mainHandItem = player.getMainHandStack();
                ItemStack helmetItem = player.getEquippedStack(EquipmentSlot.HEAD);

                // Check if the main hand item is allowed on the head
                if (WearableBlocks.isAllowed(mainHandItem.getItem())) {
                    // Swap main hand and head slot items
                    player.equipStack(EquipmentSlot.HEAD, mainHandItem);
                    player.setStackInHand(player.getActiveHand(), helmetItem);
                }
            });
        });
    }
}