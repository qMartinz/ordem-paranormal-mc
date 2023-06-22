package com.guga.ordemparanormal.common.goals;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPVillagers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;

import java.awt.*;
import java.util.List;

public class AreaRitualGoal extends MeleeAttackGoal {
    public AreaRitualGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
    }
    @Override
    public boolean canUse() {
        if (mob instanceof Villager villager &&
                (villager.getVillagerData().getProfession() == OPVillagers.OCULTISTA_SANGUE.get() ||
                        villager.getVillagerData().getProfession() == OPVillagers.OCULTISTA_ENERGIA.get() ||
                        villager.getVillagerData().getProfession() == OPVillagers.OCULTISTA_CONHECIMENTO.get() ||
                        villager.getVillagerData().getProfession() == OPVillagers.OCULTISTA_MORTE.get())) {
            return super.canUse();
        } else return false;
    }
    @Override
    public void start() {
        super.start();
        Item item = switch (getElement()){
            case SANGUE -> OPItems.COMPONENTE_SANGUE.get();
            case ENERGIA -> OPItems.COMPONENTE_ENERGIA.get();
            case MORTE -> OPItems.COMPONENTE_MORTE.get();
            case CONHECIMENTO -> OPItems.COMPONENTE_CONHECIMENTO.get();
            default -> OPItems.COMPONENTE_VAZIO.get();
        };

        this.mob.setItemInHand(InteractionHand.MAIN_HAND, item.getDefaultInstance());
    }
    @Override
    public void stop() {
        super.stop();
        this.mob.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
    }
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = 4d;
        if (pDistToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);

            if (this.mob.level instanceof ServerLevel level) {
                for (int i = 0; i < 360; i++){
                    if (i % 20 == 0){
                        level.sendParticles(AbilitiesParticleOptions.createData(getElement().getParticleColor(), getElement() != ParanormalElement.MORTE),
                                mob.getX() + Math.cos(i) * 0.4d, mob.getY() + 0.1d, mob.getZ() + Math.sin(i) * 0.4d,
                                0, 0d, 0d, 0d, 1d);
                        level.sendParticles(AbilitiesParticleOptions.createData(getElement().getParticleColor(), getElement() != ParanormalElement.MORTE),
                                mob.getX() + Math.cos(i) * 0.6d, mob.getY() + 0.1d, mob.getZ() + Math.sin(i) * 0.6d,
                                0, 0d, 0d, 0d, 1d);

                        Color color = getElement().getParticleColor().brighter().brighter().brighter();
                        if (getElement() == ParanormalElement.MEDO) color = new Color(195, 249, 255);

                        level.sendParticles(AbilitiesParticleOptions.createData(color, getElement() != ParanormalElement.MORTE),
                                mob.getX() + Math.cos(i) * 0.3d, mob.getY() + 0.2d, mob.getZ() + Math.sin(i) * 0.3d,
                                0, 0d, 0.08d, 0d, 1d);
                    }
                }
            }

            AABB boundingBox = this.mob.getBoundingBox().inflate(d0);

            List<LivingEntity> targets = this.mob.level.getEntitiesOfClass(LivingEntity.class, boundingBox);

            targets.forEach(target -> {
                float damageModifier = 1;
                if (mob instanceof Villager villager) damageModifier = villager.getVillagerData().getLevel();

                if (target == pEnemy || (target instanceof Mob && ((Mob) target).getTarget() == mob))
                    target.hurt(ParanormalDamageSource.mobRitualAttack(this.mob, getElement()), 5f * damageModifier);
            });
        }
    }
    private ParanormalElement getElement(){
        if (mob instanceof Villager villager && villager.getVillagerData().getProfession() == OPVillagers.OCULTISTA_SANGUE.get())
            return ParanormalElement.SANGUE;
        if (mob instanceof Villager villager && villager.getVillagerData().getProfession() == OPVillagers.OCULTISTA_MORTE.get())
            return ParanormalElement.MORTE;
        if (mob instanceof Villager villager && villager.getVillagerData().getProfession() == OPVillagers.OCULTISTA_ENERGIA.get())
            return ParanormalElement.ENERGIA;
        if (mob instanceof Villager villager && villager.getVillagerData().getProfession() == OPVillagers.OCULTISTA_CONHECIMENTO.get())
            return ParanormalElement.CONHECIMENTO;

        return ParanormalElement.NONE;
    }
    protected double getAttackReachSqr(LivingEntity pAttackTarget) {
        return 4d;
    }
}
