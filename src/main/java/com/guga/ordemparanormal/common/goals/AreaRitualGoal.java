package com.guga.ordemparanormal.common.goals;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPProfessions;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
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

import java.util.List;

public class AreaRitualGoal extends MeleeAttackGoal {
    public AreaRitualGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
    }
    @Override
    public boolean canUse() {
        if (mob instanceof Villager villager &&
                (villager.getVillagerData().getProfession() == OPProfessions.OCULTISTA_SANGUE.get() ||
                        villager.getVillagerData().getProfession() == OPProfessions.OCULTISTA_ENERGIA.get() ||
                        villager.getVillagerData().getProfession() == OPProfessions.OCULTISTA_CONHECIMENTO.get() ||
                        villager.getVillagerData().getProfession() == OPProfessions.OCULTISTA_MORTE.get())) {
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
                        level.sendParticles(new DustParticleOptions(getElement().getParticleVec3fColor(), 2f),
                                this.mob.getX() + Math.cos(i) * 3d, this.mob.getY() + 0.1d, this.mob.getZ() + Math.sin(i) * 3d,
                                0, 0d, 0d, 0d, 1d);
                        level.sendParticles(new DustParticleOptions(getElement().getParticleVec3fColor(), 1f),
                                this.mob.getX() + Math.cos(i) * 1.5, this.mob.getY() + 0.1d, this.mob.getZ() + Math.sin(i) * 1.5d,
                                0, 0d, 0d, 0d, 1d);
                        level.sendParticles(ParticleTypes.INSTANT_EFFECT,
                                this.mob.getX(), this.mob.getY() + 0.1d, this.mob.getZ(),
                                0, Math.cos(i) * 3d, 0d, Math.sin(i) * 3d, 1d);
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
        if (mob instanceof Villager villager && villager.getVillagerData().getProfession() == OPProfessions.OCULTISTA_SANGUE.get())
            return ParanormalElement.SANGUE;
        if (mob instanceof Villager villager && villager.getVillagerData().getProfession() == OPProfessions.OCULTISTA_MORTE.get())
            return ParanormalElement.MORTE;
        if (mob instanceof Villager villager && villager.getVillagerData().getProfession() == OPProfessions.OCULTISTA_ENERGIA.get())
            return ParanormalElement.ENERGIA;
        if (mob instanceof Villager villager && villager.getVillagerData().getProfession() == OPProfessions.OCULTISTA_CONHECIMENTO.get())
            return ParanormalElement.CONHECIMENTO;

        return ParanormalElement.NONE;
    }
    protected double getAttackReachSqr(LivingEntity pAttackTarget) {
        return 4d;
    }
}
