package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class MedoTangivel extends AbstractRitual {
    public MedoTangivel(ResourceLocation id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget, double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, hasEntityTarget, range, mustHoldIngredient);
    }
    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        caster.addEffect(new MobEffectInstance(OPEffects.TANGIBLE_FEAR.get(), 300, 0, false, false));
    }
}
