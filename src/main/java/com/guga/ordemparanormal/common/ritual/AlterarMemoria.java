package com.guga.ordemparanormal.common.ritual;

import javax.annotation.Nullable;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.entity.monster.piglin.Piglin;

public class AlterarMemoria extends AbstractRitual {
    public AlterarMemoria() {
        super("alterar_memoria", ParanormalElement.CONHECIMENTO, 3, 6, true, 3D, true);
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {

        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        // 1125
        target.setLastHurtByPlayer(null);
    }
}
