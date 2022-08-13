package com.guga.ordemparanormal.core.network;

import com.guga.ordemparanormal.api.capabilities.network.*;
import com.guga.ordemparanormal.api.powers.power.network.PowerPackets;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.util.thread.EffectiveSide;
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

        net.messageBuilder(SyncPowers.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncPowers::new)
                .encoder(SyncPowers::toBytes)
                .consumer(SyncPowers::handle)
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
        net.messageBuilder(UpdatePowers.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UpdatePowers::new)
                .encoder(UpdatePowers::toBytes)
                .consumer(UpdatePowers::handle)
                .add();
        net.messageBuilder(PowerPackets.RequestPowerUse.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PowerPackets.RequestPowerUse::new)
                .encoder(PowerPackets.RequestPowerUse::toBytes)
                .consumer(PowerPackets.RequestPowerUse::handle)
                .add();
        net.messageBuilder(SyncEffects.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SyncEffects::new)
                .encoder(SyncEffects::toBytes)
                .consumer(SyncEffects::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendToPlayer(Object msg, Player player) {
        if (EffectiveSide.get() == LogicalSide.SERVER) {
            ServerPlayer serverPlayer = (ServerPlayer) player;
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), msg);
        }
    }
}
