package gg.yvaine.decor.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import gg.yvaine.decor.network.WearBlockPayload;
import org.lwjgl.glfw.GLFW;

public class SlimesDecorClient {
    private static KeyBinding wearKeyBind;

    public static void registerKeybinds() {
        wearKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.slimes-decor.wear_block", // Translation key
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H, // Default key: 'H'
                "category.slimes-decor.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (wearKeyBind.wasPressed()) {
                // Send packet to server when key is pressed
                ClientPlayNetworking.send(new WearBlockPayload());
            }
        });
    }
}