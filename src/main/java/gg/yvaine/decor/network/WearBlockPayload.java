package gg.yvaine.decor.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import gg.yvaine.decor.SlimesDecor;

public record WearBlockPayload() implements CustomPayload {
    public static final Id<WearBlockPayload> ID = new Id<>(Identifier.of(SlimesDecor.MODID, "wear_block"));

    // Empty codec since we just need a signal that the key was pressed
    public static final PacketCodec<PacketByteBuf, WearBlockPayload> CODEC = PacketCodec.unit(new WearBlockPayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}