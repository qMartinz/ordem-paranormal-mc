package com.guga.ordemparanormal.api.capabilities.network;

import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilities;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncAbilities {
    public CompoundTag tag;
    public SyncAbilities(FriendlyByteBuf buf){
        tag = buf.readNbt();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeNbt(tag);
    }
    public SyncAbilities(CompoundTag playerAbilities){
        this.tag = playerAbilities;
    }
    public void handle(Supplier<NetworkEvent.Context> ctx){
        ctx.get().enqueueWork(()->{
            Player player = Minecraft.getInstance().player;
            IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(new PlayerAbilities());

            if(cap != null){
                cap.deserializeNBT(tag);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
