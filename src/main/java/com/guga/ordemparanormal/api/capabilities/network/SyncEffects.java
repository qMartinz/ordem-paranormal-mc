package com.guga.ordemparanormal.api.capabilities.network;

import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncEffects {
    public CompoundTag tag;
    public SyncEffects(FriendlyByteBuf buf){
        tag = buf.readNbt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(tag);
    }
    public SyncEffects(CompoundTag tag){
        this.tag = tag;
    }
    public void handle(Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> {
           if (Minecraft.getInstance().player == null) return;
           Minecraft.getInstance().player.getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(effects -> effects.deserializeNBT(tag));
        });
        context.get().setPacketHandled(true);
    }
}
