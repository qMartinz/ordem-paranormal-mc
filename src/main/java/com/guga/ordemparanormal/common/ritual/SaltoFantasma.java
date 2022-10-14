package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class SaltoFantasma extends AbstractRitual {
    public SaltoFantasma() {
        super("salto_fantasma", ParanormalElement.ENERGIA, 3, 6, false, 10D, true);
    }

    @Override
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster) {
        // caster.getOnPos()
        Vec3 blockPos = rayTraceResult.getLocation();
        caster.moveTo(blockPos);
    }
}
