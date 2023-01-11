package com.guga.ordemparanormal.common.goals;

import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.IRitualCaster;
import com.guga.ordemparanormal.api.abilities.ritual.RitualCaster;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class UseRitualGoal extends Goal {
    protected final Mob mob;
    protected final AbstractRitual ritual;
    private int ticksUntilNextCast;
    private final int castInterval;
    public UseRitualGoal(Mob mob, AbstractRitual ritual, int castInterval) {
        this.mob = mob;
        this.ritual = ritual;
        this.castInterval = castInterval;
    }
    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        this.ticksUntilNextCast = Math.max(this.ticksUntilNextCast - 1, 0);
        if (livingentity != null && livingentity.isAlive() && isTimeToCast()) {
            if (ritual.getRange() > 0) {
                return this.getCastReach() >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            } else return true;
        } else return false;
    }
    @Override
    public void start() {
        this.ticksUntilNextCast = 0;
        switch(ritual.getElement()){
            case SANGUE -> this.mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(OPItems.COMPONENTE_SANGUE.get()));
            case ENERGIA -> this.mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(OPItems.COMPONENTE_ENERGIA.get()));
            case MORTE -> this.mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(OPItems.COMPONENTE_MORTE.get()));
            case CONHECIMENTO -> this.mob.setItemInHand(InteractionHand.OFF_HAND, new ItemStack(OPItems.COMPONENTE_CONHECIMENTO.get()));
        }
    }
    protected void castRitual(double pDistToEnemySqr) {
        double d0 = this.getCastReach();
        if (ritual.getRange() > 0) {
            if (pDistToEnemySqr <= d0 && this.isTimeToCast()) {
                this.resetCastCooldown();

                IRitualCaster caster = getRitualCaster();
                caster.castRitual(mob.level, mob, mob.getMainHandItem(), ritual);
                this.mob.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
            }
        } else {
            if (this.isTimeToCast()) {
                this.resetCastCooldown();

                IRitualCaster caster = getRitualCaster();
                caster.castRitual(mob.level, mob, mob.getMainHandItem(), ritual);
                this.mob.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
            }
        }
    }
    @Override
    public void tick() {
        LivingEntity livingentity = this.mob.getTarget();

        if (livingentity != null && livingentity.isAlive()) {
            double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());

            this.castRitual(d0);
        }
    }
    protected int getTicksUntilNextCast() {
        return this.ticksUntilNextCast;
    }
    protected int getCastInterval() {
        return this.adjustedTickDelay(castInterval);
    }
    protected void resetCastCooldown() {
        this.ticksUntilNextCast = this.getCastInterval();
    }
    protected boolean isTimeToCast() {
        return this.getTicksUntilNextCast() <= 0;
    }
    protected double getCastReach() {
        return this.ritual.getRange();
    }
    @NotNull
    public IRitualCaster getRitualCaster() {
        return new GoalCaster();
    }
    public static class GoalCaster extends RitualCaster {
        public GoalCaster() {
            super(ItemStack.EMPTY);
        }
        public GoalCaster(CompoundTag tag) {
            super(tag);
        }
    }
}
