package com.guga.ordemparanormal.api.curses;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CurseHelper {

    public static String getCurseId(CompoundTag pCompoundTag) {
        return pCompoundTag.getString("id");
    }
    public static int getCurseUses(CompoundTag pCompoundTag) {
        return pCompoundTag.getInt("uses");
    }
    public static Set<CurseInstance> getCurses(ItemStack pStack) {
        ListTag listtag = pStack.getTag() != null ? pStack.getTag().getList("Curses", 10) : new ListTag();
        return deserializeCurses(listtag);
    }
    public static CurseInstance getCurse(ItemStack stack, AbstractCurse curse){
        ListTag listtag = stack.getTag().getList("Curses", Tag.TAG_COMPOUND);
        for (int i = 0; i < listtag.size(); i++){
            CompoundTag curseTag = listtag.getCompound(i);
            if (getCurseId(curseTag).equals(curse.getId())) {
                return new CurseInstance(OrdemParanormalAPI.getInstance().getCurse(getCurseId(curseTag)), getCurseUses(curseTag));
            }
        }
        return null;
    }
    public static Set<CurseInstance> deserializeCurses(ListTag pSerialized) {
        Set<CurseInstance> set = new HashSet<>();

        for(int i = 0; i < pSerialized.size(); ++i) {
            CompoundTag compoundtag = pSerialized.getCompound(i);
            set.add(new CurseInstance(OrdemParanormalAPI.getInstance().getCurse(getCurseId(compoundtag)), getCurseUses(compoundtag)));
        }

        return set;
    }
    public static void setCurses(Collection<CurseInstance> pCurses, ItemStack pStack) {
        ListTag listtag = new ListTag();

        for (CurseInstance curseInstance : pCurses) listtag.add(curseInstance.serializeNBT());

        if (listtag.isEmpty()) {
            pStack.removeTagKey("Curses");
        } else {
            pStack.addTagElement("Curses", listtag);
        }
    }
    public static void addCurse(ItemStack pStack, CurseInstance pCurse){
        Set<CurseInstance> curses = CurseHelper.getCurses(pStack);
        curses.removeIf(c -> c.getCurse().getId().equals(pCurse.getCurse().getId()));
        curses.add(pCurse);
        CurseHelper.setCurses(curses, pStack);
    }
    public static void addCurse(ItemStack pStack, AbstractCurse pCurse){
        Set<CurseInstance> curses = CurseHelper.getCurses(pStack);
        curses.removeIf(c -> c.getCurse().getId().equals(pCurse.getId()));
        curses.add(new CurseInstance(pCurse));
        CurseHelper.setCurses(curses, pStack);
    }

    public static void removeCurse(ItemStack pStack, AbstractCurse pCurse){
        Set<CurseInstance> curses = CurseHelper.getCurses(pStack);
        curses.removeIf(c -> c.getCurse().getId().equals(pCurse.getId()));
        CurseHelper.setCurses(curses, pStack);
    }
    public static void doPostAttackEffects(LivingEntity pAttacker, Entity pTarget){
        if (pAttacker != null) {
            for (CurseInstance curse : getCurses(pAttacker.getMainHandItem())) {
                if (curse != null) {
                    curse.getCurse().doPostAttack(pAttacker.getMainHandItem(), pAttacker, pTarget);
                    pTarget.hurt(curse.getCurse().getElement().getDamage(), curse.getCurse().getDamageBonus());
                }
            }
        }
    }
    public static void doPostHurtEffects(LivingEntity pTarget, Entity pAttacker, float pAmount, DamageSource source){
        if (pTarget != null) {
            for (ItemStack item : pTarget.getAllSlots()) {
                for (CurseInstance curseInstance : getCurses(item)) {
                    if (curseInstance != null) {
                        curseInstance.getCurse().doPostHurt(item, pTarget, pAttacker);
                        pTarget.heal(Math.min(curseInstance.getCurse().getDamageProtection(source), pAmount));
                    }
                }
            }
        }
    }
    public static void doTickEffects(LivingEntity pUser){
        if (pUser != null) {
            for (ItemStack item : pUser.getAllSlots()) {
                for (CurseInstance curse : getCurses(item)) {
                    if (curse != null) curse.getCurse().doTick(item, pUser);
                }
            }
        }
    }
}
