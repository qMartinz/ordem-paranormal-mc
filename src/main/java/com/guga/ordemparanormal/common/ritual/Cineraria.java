package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.DefensiveRitual;
import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.core.registry.OPEntities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class Cineraria extends AbstractRitual implements DefensiveRitual {
    public Cineraria(ResourceLocation id, ParanormalElement element, int tier, int effortCost,
                     double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }
    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, @org.jetbrains.annotations.Nullable ItemStack ritualItem, @org.jetbrains.annotations.Nullable InteractionHand hand) {
        Nevoa nevoa = OPEntities.NEVOA.get().create(caster.level);
        nevoa.copyPosition(caster);
        nevoa.setIntensity(1);
        nevoa.setRadius(10);
        world.addFreshEntity(nevoa);
    }
}
