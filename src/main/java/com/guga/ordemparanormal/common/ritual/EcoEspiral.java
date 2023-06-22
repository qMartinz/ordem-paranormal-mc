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

public class EcoEspiral extends AbstractRitual implements OffensiveRitual {

    public EcoEspiral(ResourceLocation id, ParanormalElement element, int tier, int effortCost,
                      double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster,
            @Nullable ItemStack ritualItem, @Nullable InteractionHand hand) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        /*
         * boolean crouch = caster.isCrouching();
         * float tVida = target.getHealth();
         * float cVida = caster.getHealth();
         * // target.getEntityData();
         * // String tId = target.UUID_TAG;
         * CompoundTag VidaTag = new CompoundTag();
         * VidaTag.putFloat("eco.tVida", tVida);
         * VidaTag.putFloat("eco.cVida", cVida);
         * 
         * ritualItem.getOrCreateTag().put("eco.inf", VidaTag);
         * if (crouch && cVida == caster.getHealth()) {
         * float tVidaF = tVida - target.getHealth();
         * target.hurt(ParanormalDamageSource.ritualAttack(caster, this), tVidaF);
         * 
         * }
         */
    }
}
