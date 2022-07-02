package com.guga.ordemparanormal.api.attributes;

import com.guga.ordemparanormal.common.capabilities.nexplayer.NexCapability;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class AttributeEvents {
    private static int effortTickTimer;
    @SubscribeEvent
    public static void breakSpeedBonus(PlayerEvent.BreakSpeed event){
        // Aumenta velocidade de mineração
        int speedBonus = NexModel.get(event.getPlayer()).getAttribute(ParanormalAttribute.STRENGTH);
        event.setNewSpeed(event.getOriginalSpeed() + speedBonus);
    }
    @SubscribeEvent
    public static void eatSaturationBonus(LivingEntityUseItemEvent.Finish event){
        if (event.getEntity() instanceof Player player && event.getItem().isEdible()){
            player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + NexModel.get(player).getAttribute(ParanormalAttribute.VIGOR));
        }
    }

    @SubscribeEvent
    public static void regenEffort(TickEvent.PlayerTickEvent event){
        if (event.player.getCapability(NexCapability.INSTANCE).isPresent() && !event.player.isLocalPlayer()){
            double curEffort = NexModel.get(event.player).getEffortPoints();
            double maxEffort = NexModel.get(event.player).getMaxEffortPoints();

            boolean flag = event.player.level.getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION);
            if (flag && event.player.getFoodData().getSaturationLevel() > 0.0F && curEffort < maxEffort && event.player.getFoodData().getFoodLevel() >= 20) {
                ++effortTickTimer;
                if (effortTickTimer >= 10) {
                    float f = Math.min(event.player.getFoodData().getSaturationLevel(), 2.0F);
                    NexModel.get(event.player).setCurEffortPoints(NexModel.get(event.player).getEffortPoints() + f / 6f);
                    event.player.getFoodData().addExhaustion(f);
                    effortTickTimer = 0;
                }
            } else if (flag && event.player.getFoodData().getFoodLevel() >= 18 && curEffort < maxEffort) {
                ++effortTickTimer;
                if (effortTickTimer >= 80) {
                    NexModel.get(event.player).setCurEffortPoints(NexModel.get(event.player).getEffortPoints() + 1/2f);
                    event.player.getFoodData().addExhaustion(1);
                    effortTickTimer = 0;
                }
            } else if (event.player.getFoodData().getFoodLevel() <= 0) {
                ++effortTickTimer;
                if (effortTickTimer >= 80) {
                    if (curEffort > 10.0F || event.player.level.getDifficulty() == Difficulty.HARD || curEffort > 1.0F && event.player.level.getDifficulty() == Difficulty.NORMAL) {
                        NexModel.get(event.player).setCurEffortPoints(NexModel.get(event.player).getEffortPoints() - 1);
                    }
                    effortTickTimer = 0;
                }
            } else {
                effortTickTimer = 0;
            }
        }
    }
}
