package com.guga.ordemparanormal.common.goals;

import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.common.entity.AberracaoCarne;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPEndimations;
import com.guga.ordemparanormal.core.registry.OPSounds;
import com.teamabnormals.blueprint.core.endimator.entity.EndimatedGoal;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class AbocanharGoal extends EndimatedGoal<AberracaoCarne> {
    private int ticksUntilNextAttack;
    private final int attackInterval = 650;

    public AbocanharGoal(AberracaoCarne entity) {
        super(entity, OPEndimations.ABERRACAO_ABOCANHAR);
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.entity.getTarget();
        this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        if (livingentity != null && livingentity.isAlive() && isTimeToAttack()) {
            return this.getAttackReach(livingentity) >= this.entity.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
        } else return false;
    }

    @Override
    public void start() {
        this.entity.setAggressive(true);
        this.ticksUntilNextAttack = 0;
    }

    protected void performGrab(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = this.getAttackReach(pEnemy);
        if (pDistToEnemySqr <= d0 && this.isTimeToAttack()) {
            this.resetAttackCooldown();
            // Grab
            this.playEndimation();
        }
    }

    @Override
    public void tick() {
        LivingEntity livingentity = this.entity.getTarget();

        if (livingentity != null && livingentity.isAlive()) {
            double d0 = this.entity.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
            if (this.isEndimationPastOrAtTick(5)) {
                this.entity.setDeltaMovement(0, this.entity.getDeltaMovement().y, 0);
            }

            if (this.isEndimationPastOrAtTick(5) && this.getAttackReach(livingentity) >= this.entity.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ())) {
                double motionX = (this.entity.getX() - livingentity.getX());
                double motionZ = (this.entity.getZ() - livingentity.getZ());
                livingentity.setDeltaMovement(motionX, 0d, motionZ);
            }

            if (this.isEndimationAtTick(10) && this.getAttackReach(livingentity) >= this.entity.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ())) {
                livingentity.hurt(ParanormalDamageSource.paranormalCreatureAttack(this.entity), 25f);
                livingentity.addEffect(new MobEffectInstance(OPEffects.BLEED.get(), 200, 1, false, false));
                this.entity.playSound(OPSounds.ABERRACAO_ABOCANHAR.get(), 1.0f, 1.0f);
            }

            this.performGrab(livingentity, d0);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.isEndimationPlaying();
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected int getAttackInterval() {
        return this.adjustedTickDelay(attackInterval);
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.getAttackInterval();
    }

    protected boolean isTimeToAttack() {
        return this.getTicksUntilNextAttack() <= 0;
    }

    protected double getAttackReach(LivingEntity pAttackTarget) {
        return this.entity.getBbWidth() * 2.0F * this.entity.getBbWidth() * 2.0F + pAttackTarget.getBbWidth();
    }
}
