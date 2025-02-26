package com.guga.ordemparanormal.core.network;

import com.guga.ordemparanormal.api.capabilities.data.*;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Packets {
    public static class RequestPowerUse {
        public int slot;
        public RequestPowerUse(FriendlyByteBuf buf){
            slot = buf.readInt();
        }
        public void toBytes(FriendlyByteBuf buf){
            buf.writeInt(slot);
        }
        public RequestPowerUse(int slot){
            this.slot = slot;
        }
        public void handle(Supplier<NetworkEvent.Context> context){
            context.get().enqueueWork(() -> {
                if (context.get().getSender() == null) return;
                context.get().getSender().getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> cap.getActivePower(slot).use(context.get().getSender()));
            });
            context.get().setPacketHandled(true);
        }
    }
    public static class SyncAbilities {
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
        public void handle(Supplier<NetworkEvent.Context> context){
            context.get().enqueueWork(() -> {
                if (OrdemParanormal.proxy.getPlayer() == null) return;
                OrdemParanormal.proxy.getPlayer().getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(ab -> {
                    ab.deserializeNBT(tag);
                });
            });
            context.get().setPacketHandled(true);
        }
    }
    public static class SyncEffects {
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
               if (OrdemParanormal.proxy.getPlayer() == null) return;
               OrdemParanormal.proxy.getPlayer().getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).ifPresent(effects -> effects.deserializeNBT(tag));
            });
            context.get().setPacketHandled(true);
        }
    }
    public static class SyncNexToClient {
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
               if (OrdemParanormal.proxy.getPlayer() == null) return;
               OrdemParanormal.proxy.getPlayer().getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(nex -> {
                   nex.deserializeNBT(tag);
               });
            });
            context.get().setPacketHandled(true);
        }
    }
    public static class SyncNexToServer {
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
                   nex.syncAttributeMods(context.get().getSender());
               });
            });
            context.get().setPacketHandled(true);
        }
    }
    public static class UpdatePowers {
        public CompoundTag tag;
        public UpdatePowers(FriendlyByteBuf buf){
            tag = buf.readNbt();
        }
        public void toBytes(FriendlyByteBuf buf){
            buf.writeNbt(tag);
        }
        public UpdatePowers(CompoundTag playerAbilities){
            this.tag = playerAbilities;
        }
        public void handle(Supplier<NetworkEvent.Context> ctx){
            ctx.get().enqueueWork(()->{
                Player player = ctx.get().getSender();
                IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(new PlayerAbilities());

                if(cap != null){
                    cap.deserializeNBT(tag);
                    cap.syncAttributeMods(ctx.get().getSender());
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
    public static class ReceivePowerTrigger {
        public ReceivePowerTrigger(FriendlyByteBuf buf){}
        public void toBytes(FriendlyByteBuf buf){}
        public ReceivePowerTrigger(){}
        public void handle(Supplier<NetworkEvent.Context> ctx){
            ctx.get().enqueueWork(()->{
                OPTriggers.RECEIVE_POWER.trigger(ctx.get().getSender());
            });
            ctx.get().setPacketHandled(true);
        }
    }
}
