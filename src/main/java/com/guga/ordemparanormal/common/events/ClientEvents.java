package com.guga.ordemparanormal.common.events;

import com.guga.ordemparanormal.client.screen.AttributeScreen;
import com.guga.ordemparanormal.common.block.AltarTranscender;
import com.guga.ordemparanormal.common.item.RitualItem;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        if (event.getEntityLiving().level.getBlockState(event.getHitVec().getBlockPos()).getBlock() instanceof AltarTranscender){
            if (event.getSide() == LogicalSide.CLIENT && !(event.getItemStack().getItem() instanceof RitualItem))
                Minecraft.getInstance().setScreen(new AttributeScreen());
        }
    }
}
