package com.guga.ordemparanormal.api.abilities.ritual;

import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.core.registry.OPCurses;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class CurseRitual extends AbstractRitual{
    private final AbstractCurse curse;
    public CurseRitual(String id, AbstractCurse curse, int tier, int effortCost) {
        super(id, curse.element, tier, effortCost, true, 0, false);
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
        }
    }
    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster) {
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
