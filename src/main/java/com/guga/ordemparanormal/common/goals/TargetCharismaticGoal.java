package com.guga.ordemparanormal.common.goals;

import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;

public class TargetCharismaticGoal extends TargetGoal {
    public TargetCharismaticGoal(Mob pMob, boolean pMustSee) {
        super(pMob, pMustSee);
    }
    @Override
    public boolean canUse() {
        return mob.getTarget() != null;
    }
    @Override
    public void tick() {
        IAbilitiesCap cap = this.mob.getTarget().getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (cap != null && cap.hasPower(OPPowers.CARISMATICO_2) && this.mob.getMaxHealth() <= this.mob.getTarget().getMaxHealth()/2f) {
            this.mob.setTarget(null);
        } else if (this.mob.getTarget().hasEffect(OPEffects.CHARISMATIC.get()) && this.mob.getMaxHealth() <= this.mob.getTarget().getMaxHealth()) {
            this.mob.setTarget(null);
        }

        super.tick();
    }
}
