package com.guga.ordemparanormal.common.entity;

import com.guga.ordemparanormal.api.ritual.ParanormalElement;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ParanormalCreature extends Monster {
    protected double rewardedXP;
    protected ParanormalElement element;
    protected ParanormalCreature(EntityType<? extends Monster> type, Level level, double rewardedXP, ParanormalElement element) {
        super(type, level);
        this.rewardedXP = rewardedXP;
        this.element = element;
    }
    public ParanormalElement getMainElement() {
        return this.element;
    }
    @Override
    public void die(DamageSource source){
        super.die(source);
        if (this.getLastHurtByMob() instanceof Player player){
            NexModel.get(player).giveNexXP(rewardedXP);
        }
    }
}
