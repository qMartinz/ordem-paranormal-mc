package com.guga.ordemparanormal.common.goals;

import com.guga.ordemparanormal.common.entity.AberracaoCarne;
import com.guga.ordemparanormal.core.registry.OPEndimations;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AberracaoAttackGoal extends MeleeAttackGoal {
    public AberracaoAttackGoal(AberracaoCarne pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
    }
    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = this.getAttackReachSqr(pEnemy);
        if (pDistToEnemySqr <= d0 && this.getTicksUntilNextAttack() <= 0) {
            this.resetAttackCooldown();

            AberracaoCarne mob = (AberracaoCarne) this.mob;
            List<PlayableEndimation> animations = new ArrayList<>();
            animations.add(OPEndimations.ABERRACAO_HITLEFTLOW);
            animations.add(OPEndimations.ABERRACAO_HITLEFTUP);
            animations.add(OPEndimations.ABERRACAO_HITRIGHTLOW);
            animations.add(OPEndimations.ABERRACAO_HITRIGHTUP);
            Collections.shuffle(animations);

            NetworkUtil.setPlayingAnimation(mob, animations.get(0));

            this.mob.doHurtTarget(pEnemy);
        }
    }
}
