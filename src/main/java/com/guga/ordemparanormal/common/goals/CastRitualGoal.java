package com.guga.ordemparanormal.common.goals;

import com.guga.ordemparanormal.common.entity.RitualCasterMob;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class CastRitualGoal extends Goal {
    private final Mob mob;
    private final RitualCasterMob ritualCasterMob;
    @Nullable
    private LivingEntity target;
    private int castTime = -1;
    private final double speedModifier;
    private int seeTime;
    private final int castIntervalMin;
    private final int castIntervalMax;
    private final float castRadius;
    private final float castRadiusSqr;

    public CastRitualGoal(RitualCasterMob pRitualCasterMob, double pSpeedModifier, int pCastInterval) {
        this(pRitualCasterMob, pSpeedModifier, pCastInterval, pCastInterval);
    }

    public CastRitualGoal(RitualCasterMob pRitualCasterMob, double pSpeedModifier, int pCastIntervalMin, int pCastIntervalMax) {
        if (!(pRitualCasterMob instanceof LivingEntity)) {
            throw new IllegalArgumentException("CastRitualGoal requires Mob implements RitualCasterMob");
        } else {
            this.ritualCasterMob = pRitualCasterMob;
            this.mob = (Mob)pRitualCasterMob;
            this.speedModifier = pSpeedModifier;
            this.castIntervalMin = pCastIntervalMin;
            this.castIntervalMax = pCastIntervalMax;
            this.castRadius = 7.5f;
            this.castRadiusSqr = castRadius * castRadius;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        LivingEntity livingentity = this.mob.getTarget();
        if (livingentity != null && livingentity.isAlive()) {
            this.target = livingentity;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.canUse() || !this.mob.getNavigation().isDone();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.target = null;
        this.seeTime = 0;
        this.castTime = -1;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        double d0 = this.mob.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        boolean flag = this.mob.getSensing().hasLineOfSight(this.target);
        if (flag) {
            ++this.seeTime;
        } else {
            this.seeTime = 0;
        }

        if (!(d0 > (double)this.castRadiusSqr) && this.seeTime >= 5) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().moveTo(this.target, this.speedModifier);
        }

        this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        if (--this.castTime == 0) {
            if (!flag) {
                return;
            }

            float f = (float)Math.sqrt(d0) / this.castRadius;
            float f1 = Mth.clamp(f, 0.1F, 1.0F);
            this.ritualCasterMob.performRitualCast(this.target, f1);
            this.castTime = Mth.floor(f * (float)(this.castIntervalMax - this.castIntervalMin) + (float)this.castIntervalMin);
        } else if (this.castTime < 0) {
            this.castTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double)this.castRadius, this.castIntervalMin, this.castIntervalMax));
        }

    }
}
