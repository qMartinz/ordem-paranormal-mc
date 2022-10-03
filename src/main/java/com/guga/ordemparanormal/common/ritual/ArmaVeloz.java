package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPCurses;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class ArmaVeloz extends AbstractRitual {
    public ArmaVeloz() {
        super("arma_veloz", ParanormalElement.ENERGIA, 1, 2, true, 0, false);
    }
    @Override
    public void onUse(@Nullable HitResult rayTraceResult, Level world, LivingEntity caster) {
        if (OPCurses.VELOZ.getCategory().canCurse(caster.getOffhandItem().getItem()) &&
                CurseHelper.getCurses(caster.getOffhandItem()).stream().allMatch(curse -> curse.getElement().isCompatible(this.getElement()))) {
            super.onUse(rayTraceResult, world, caster);
        }
    }
    @Override
    public void onUseSelf(Level world, LivingEntity caster) {
        CurseHelper.addCurse(caster.getOffhandItem(), OPCurses.VELOZ);
    }
}
