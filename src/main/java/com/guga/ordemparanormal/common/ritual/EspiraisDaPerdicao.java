package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class EspiraisDaPerdicao extends AbstractRitual {
    public EspiraisDaPerdicao(ResourceLocation id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget, double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, hasEntityTarget, range, mustHoldIngredient);
    }
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem,
                            InteractionHand hand) {
        LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
        MobEffectInstance weakness = new MobEffectInstance(MobEffects.WEAKNESS, 2400, 1, false, true);
        target.addEffect(weakness);
    }

}
