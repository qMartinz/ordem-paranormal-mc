package com.guga.ordemparanormal.api.capabilities.network;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncNexToClient {
    public CompoundTag tag;
    public SyncNexToClient(FriendlyByteBuf buf){
        tag = buf.readNbt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(tag);
    }
    public SyncNexToClient(CompoundTag tag){
        this.tag = tag;
    }
    public void handle(Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> {
           if (Minecraft.getInstance().player == null) return;
           Minecraft.getInstance().player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(nex -> {
               nex.loadNBTData(tag);
           });
        });
        context.get().setPacketHandled(true);
    }
}
