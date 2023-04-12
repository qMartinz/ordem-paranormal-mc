package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.OffensiveRitual;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;

public class AlterarMemoria extends AbstractRitual implements OffensiveRitual {
    public AlterarMemoria(ResourceLocation id, ParanormalElement element, int tier, int effortCost,
                          double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }
    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();

        // if(target.){

        // }
        /*
         * 
         * // 1125
         * target.setLastHurtByPlayer(null);
         */

    }
}
