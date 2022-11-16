package com.guga.ordemparanormal.common.ritual;

import javax.annotation.Nullable;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.core.registry.OPEntities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class Cineraria extends AbstractRitual {
    public Cineraria(ResourceLocation id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget,
            double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, hasEntityTarget, range, mustHoldIngredient);
    }

    @Override
    public void onUse(@Nullable HitResult rayTraceResult, Level world, LivingEntity caster) {
        Nevoa nevoa = OPEntities.NEVOA.get().create(caster.level);
        nevoa.copyPosition(caster);
        nevoa.setIntensity(1);
        nevoa.setRadius(10);
        world.addFreshEntity(nevoa);
    }
}
