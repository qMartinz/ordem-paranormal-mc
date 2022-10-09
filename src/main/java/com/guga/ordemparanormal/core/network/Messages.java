package com.guga.ordemparanormal.core.network;

import com.guga.ordemparanormal.api.capabilities.network.Packets;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.util.thread.EffectiveSide;
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
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(OrdemParanormal.MOD_ID, "network"), () -> "1.0", s->true, s->true);

        INSTANCE.registerMessage(id(), Packets.SyncAbilities.class,
                Packets.SyncAbilities::toBytes,
                Packets.SyncAbilities::new,
                Packets.SyncAbilities::handle);
        INSTANCE.registerMessage(id(), Packets.SyncNexToClient.class,
                Packets.SyncNexToClient::toBytes,
                Packets.SyncNexToClient::new,
                Packets.SyncNexToClient::handle);
        INSTANCE.registerMessage(id(), Packets.SyncNexToServer.class,
                Packets.SyncNexToServer::toBytes,
                Packets.SyncNexToServer::new,
                Packets.SyncNexToServer::handle);
        INSTANCE.registerMessage(id(), Packets.UpdatePowers.class,
                Packets.UpdatePowers::toBytes,
                Packets.UpdatePowers::new,
                Packets.UpdatePowers::handle);
        INSTANCE.registerMessage(id(), Packets.RequestPowerUse.class,
                Packets.RequestPowerUse::toBytes,
                Packets.RequestPowerUse::new,
                Packets.RequestPowerUse::handle);
        INSTANCE.registerMessage(id(), Packets.SyncEffects.class,
                Packets.SyncEffects::toBytes,
                Packets.SyncEffects::new,
                Packets.SyncEffects::handle);
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static void sendToPlayer(Object msg, Player player) {
        if (EffectiveSide.get() == LogicalSide.SERVER) {
            INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), msg);
        }
    }
}
