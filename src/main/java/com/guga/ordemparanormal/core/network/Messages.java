package com.guga.ordemparanormal.core.network;

import com.guga.ordemparanormal.api.capabilities.network.SyncAbilities;
import com.guga.ordemparanormal.api.capabilities.network.SyncNexToClient;
import com.guga.ordemparanormal.api.capabilities.network.SyncNexToServer;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
public class Messages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(OrdemParanormal.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(SyncAbilities.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncAbilities::new)
                .encoder(SyncAbilities::toBytes)
                .consumer(SyncAbilities::handle)
                .add();
        net.messageBuilder(SyncNexToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncNexToClient::new)
                .encoder(SyncNexToClient::toBytes)
                .consumer(SyncNexToClient::handle)
                .add();
        net.messageBuilder(SyncNexToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SyncNexToServer::new)
                .encoder(SyncNexToServer::toBytes)
                .consumer(SyncNexToServer::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
