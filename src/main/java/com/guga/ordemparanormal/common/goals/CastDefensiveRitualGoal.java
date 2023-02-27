package com.guga.ordemparanormal.common.goals;

import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.DefensiveRitual;
import com.guga.ordemparanormal.api.util.PowerUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class CastDefensiveRitualGoal extends Goal {
    private final Mob mob;
    private final int castInterval;
    private final AbstractRitual ritual;
    private final DefensiveRitual defensiveRitual;
    private int castTime = 0;
    public CastDefensiveRitualGoal(Mob mob, int castInterval, DefensiveRitual ritual){
        if (!(ritual instanceof AbstractRitual)) {
            throw new IllegalArgumentException("CastDefensiveRitualGoal requires AbstractRitual implements DefensiveRitual");
        } else {
            this.mob = mob;
            this.castInterval = castInterval;
            this.ritual = (AbstractRitual) ritual;
            this.defensiveRitual = ritual;
        }

    }
    @Override
    public boolean canUse() {
        if (castTime < 0) castTime = 0;
        return this.castTime-- == 0 && this.mob.getTarget() != null && this.mob.isAlive();
    }

    @Override
    public void start() {
        defensiveRitual.onUseSelf(PowerUtils.rayTrace(this.mob, ritual.getRange(), 0f, false),
                this.mob.level, this.mob, null, null);
        ritual.ritualSuccess((ServerLevel) this.mob.level, this.mob);
        System.out.println("y");
    }

    @Override
    public void stop() {
        this.castTime = castInterval;
        System.out.println("n");
    }
}
