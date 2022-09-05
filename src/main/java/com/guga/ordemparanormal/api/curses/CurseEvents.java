package com.guga.ordemparanormal.api.curses;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class CurseEvents {
    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingUpdateEvent event){
        CurseHelper.doTickEffects(event.getEntityLiving());
        CurseHelper.tickCurses(event.getEntityLiving());
    }
    @SubscribeEvent
    public static void onEntityAttack(LivingAttackEvent event){
        CurseHelper.doPostAttackEffects((LivingEntity) event.getSource().getEntity(), event.getEntity());
        CurseHelper.doPostHurtEffects(event.getEntityLiving(), event.getSource().getEntity(), event.getAmount(), event.getSource());
    }
    @SubscribeEvent
    public static void onRenderTooltips(ItemTooltipEvent event){
        if (!CurseHelper.getCurses(event.getItemStack()).isEmpty() && CurseHelper.getCurses(event.getItemStack()).stream().noneMatch(Objects::isNull)){
            List<Component> components = new ArrayList<>();

            CurseHelper.getCurses(event.getItemStack()).forEach(curse -> {
                components.add(curse.getDisplayName());

                for (EquipmentSlot slot : curse.getSlots()){
                    if (slot == EquipmentSlot.MAINHAND && curse.getDamageBonus() > 0) {
                        event.getToolTip().add(ElementDamage.elementDmgTypeName(curse.getElement()).plainCopy()
                                .append(": ").append(Integer.toString(curse.getDamageBonus())).withStyle(ChatFormatting.BLUE));
                    }
                }
            });
            event.getToolTip().addAll(1, components);
        }
    }
    @SubscribeEvent
    public static void onCalculateAttributes(ItemAttributeModifierEvent event){
        if (!CurseHelper.getCurses(event.getItemStack()).isEmpty() && CurseHelper.getCurses(event.getItemStack()).stream().noneMatch(Objects::isNull)){
            for (AbstractCurse curse : CurseHelper.getCurses(event.getItemStack())){
                curse.getAttributeModifiers().forEach((modifier, attribute) -> {
                    if (Arrays.stream(curse.getSlots()).anyMatch(slot -> slot == event.getSlotType())) event.addModifier(attribute, modifier);
                });
            }
        }
    }
}
