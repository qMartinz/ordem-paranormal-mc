package com.guga.ordemparanormal.api.capabilities.network;

import com.guga.ordemparanormal.api.capabilities.data.IPowerCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowers;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncPowers {
    public CompoundTag tag;
    public SyncPowers(FriendlyByteBuf buf){
        tag = buf.readNbt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(tag);
    }
    public SyncPowers(CompoundTag playerAbilities){
        this.tag = playerAbilities;
    }
    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            Player player = Minecraft.getInstance().player;
            IPowerCap cap = player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(new PlayerPowers());

            if(cap != null){
                cap.deserializeNBT(tag);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
