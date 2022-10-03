package com.guga.ordemparanormal.api.abilities.power.network;

import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PowerPackets {
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
}
