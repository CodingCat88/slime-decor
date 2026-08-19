package gg.yvaine.decor;

import gg.yvaine.decor.Essential.WearableBlocks;
import gg.yvaine.decor.client.SlimesDecorClient;
import gg.yvaine.decor.network.ServerPacketHandlers;
import gg.yvaine.decor.network.WearBlockPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import net.minecraft.network.PacketByteBuf;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;

import gg.yvaine.decor.Essential.SlimesDecorTabs;
import gg.yvaine.decor.Essential.SlimesDecorItems;
import gg.yvaine.decor.Essential.SlimesDecorBlocks;

public class SlimesDecor implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger(SlimesDecor.class);
	public static final String MODID = "slimes-decor";

	@Override
	public void onInitialize() {

		SlimesDecorBlocks.registerSlimesBlocks();
		SlimesDecorItems.registerSlimesItems();
		SlimesDecorTabs.registerSlimesTabs();
		PayloadTypeRegistry.playC2S().register(WearBlockPayload.ID, WearBlockPayload.CODEC);
		ServerPacketHandlers.register();
		SlimesDecorClient.registerKeybinds();








		// Server tick event handling for work queue execution
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		});

		// Start of user code block mod init
		// End of user code block mod init
	}

	// Start of user code block mod methods
	// End of user code block mod methods

	private static final String PROTOCOL_VERSION = "1";
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, PacketByteBuf> encoder, Function<PacketByteBuf, T> decoder, BiConsumer<T, Supplier<Object>> messageConsumer) {
		// Fabric 1.21.1 networking adapter placeholder (uses CustomPayload system under the hood)
		messageID++;
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable,Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
	}
}