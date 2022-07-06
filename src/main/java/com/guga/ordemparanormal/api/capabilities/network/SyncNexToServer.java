package com.guga.ordemparanormal.api.capabilities.network;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncNexToServer {
    public int nexPercent;
    public double nexXp;
    public int abilityPoints;
    public double maxEffort;
    public double currentEffort;
    public int[] attributes;
    public SyncNexToServer(FriendlyByteBuf buf){
        nexPercent = buf.readInt();
        nexXp = buf.readDouble();
        abilityPoints = buf.readInt();
        maxEffort = buf.readDouble();
        currentEffort = buf.readDouble();
        attributes = buf.readVarIntArray();
    }
    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(nexPercent);
        buf.writeDouble(nexXp);
        buf.writeInt(abilityPoints);
        buf.writeDouble(maxEffort);
        buf.writeDouble(currentEffort);
        buf.writeVarIntArray(attributes);
    }
    public SyncNexToServer(int nexPercent, double nexXp, int abilityPoints, double maxEffort, double currentEffort, int[] attributes){
        this.nexPercent = nexPercent;
        this.nexXp = nexXp;
        this.abilityPoints = abilityPoints;
        this.maxEffort = maxEffort;
        this.currentEffort = currentEffort;
        this.attributes = attributes;
    }
    public void handle(Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> {
           if (context.get().getSender() == null) return;
           context.get().getSender().getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(nex -> {
               nex.setNexPercent(nexPercent);
               nex.setNexXp(nexXp);
               nex.setAbilityPoints(abilityPoints);
               nex.setMaxEffort(maxEffort);
               nex.setCurrentEffort(currentEffort);
               nex.setAttributes(attributes);
           });
        });
        context.get().setPacketHandled(true);
    }
}
