package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.DefensiveRitual;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class CurseRitual extends AbstractRitual implements DefensiveRitual {
    private final AbstractCurse curse;
    public CurseRitual(ResourceLocation id, AbstractCurse curse, int tier, int effortCost) {
        super(id, curse.element, tier, effortCost, 0, false);
        this.curse = curse;
    }
    @Override
    public void onUse(@Nullable HitResult rayTraceResult, Level world, LivingEntity caster) {
        ItemStack offHand = caster.getOffhandItem();
        ItemStack mainHand = caster.getMainHandItem();

        if (curse.getCategory().canCurse(offHand.getItem()) &&
                CurseHelper.getCurses(offHand).stream().allMatch(curse -> curse.getCurse().getElement().isCompatible(this.getElement()))){
            super.onUse(rayTraceResult, world, caster);

        } else if (curse.getCategory().canCurse(mainHand.getItem()) &&
                CurseHelper.getCurses(mainHand).stream().allMatch(curse -> curse.getCurse().getElement().isCompatible(this.getElement()))){
            super.onUse(rayTraceResult, world, caster);
        } else {
            ritualFail(caster);
        }
    }
    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        ItemStack cursedItem = null;

        if (curse.getCategory().canCurse(caster.getMainHandItem().getItem())){
            cursedItem = caster.getMainHandItem();
        } else if (curse.getCategory().canCurse(caster.getOffhandItem().getItem())){
            cursedItem = caster.getOffhandItem();
        }

        if (cursedItem != null) {
            CurseHelper.addCurse(cursedItem, curse);
        }
    }
}
