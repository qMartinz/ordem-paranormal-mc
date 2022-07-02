package com.guga.ordemparanormal.common.network;

import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestAttrIncrease {
    private final int attribute;
    public RequestAttrIncrease(ParanormalAttribute attribute) {
        this.attribute = attribute.index;
    }
    public RequestAttrIncrease(FriendlyByteBuf buffer){
        attribute = buffer.readInt();
    }

    public void encode(FriendlyByteBuf buffer){
        buffer.writeInt(attribute);
    }

    public void handle(Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            NexModel model = NexModel.get(player);
            ParanormalAttribute attr = ParanormalAttribute.values()[this.attribute];

            if (model.getAbilityPoints() > 0){
                model.increaseAttribute(attr);
                model.setAbilityPoints(model.getAbilityPoints() - 1);

                SyncNex.send(player);
            }
        });

        context.get().setPacketHandled(true);
    }

    public static void send(ParanormalAttribute attribute){
        OrdemParanormal.network.sendToServer(new RequestAttrIncrease(attribute));
    }
}