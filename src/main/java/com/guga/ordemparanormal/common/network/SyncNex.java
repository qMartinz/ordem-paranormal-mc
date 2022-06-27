package com.guga.ordemparanormal.common.network;

import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class SyncNex {
    private final CompoundTag nexModel;
    public SyncNex(CompoundTag nexModel) {
        this.nexModel = nexModel;
    }
    public SyncNex(FriendlyByteBuf buffer){
        nexModel = buffer.readNbt();
    }
    public void encode(FriendlyByteBuf buffer){
        buffer.writeNbt(nexModel);
    }

    public void handle(Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> NexModel.get().deserializeNBT(nexModel));
        context.get().setPacketHandled(true);
    }

    public static void send(Player player){
        OrdemParanormal.network.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new SyncNex(NexModel.get(player).serializeNBT()));
    }
}
