package com.guga.ordemparanormal.api.capabilities.network;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncNexToServer {
    public CompoundTag tag;
    public SyncNexToServer(FriendlyByteBuf buf){
        tag = buf.readNbt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(tag);
    }
    public SyncNexToServer(CompoundTag tag){
        this.tag = tag;
    }
    public void handle(Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> {
           if (context.get().getSender() == null) return;
           context.get().getSender().getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(nex -> {
               nex.deserializeNBT(tag);
           });
        });
        context.get().setPacketHandled(true);
    }
}
