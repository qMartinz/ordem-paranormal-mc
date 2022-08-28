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
    public static CompoundTag storeCurse(String pId) {
        CompoundTag compoundtag = new CompoundTag();

        compoundtag.putString("id", pId);

        return compoundtag;
    }
    public static CompoundTag storeCurse(String pId, int pTicks) {
        CompoundTag compoundtag = new CompoundTag();

        compoundtag.putString("id", pId);
        compoundtag.putInt("ticks", pTicks);

        return compoundtag;
    }
    public static void setTicks(CompoundTag pCompoundTag, int pTicks){
        pCompoundTag.putInt("ticks", pTicks);
    }
    public static String getCurseId(CompoundTag pCompoundTag) {
        return pCompoundTag.getString("id");
    }
    public static Collection<AbstractCurse> getCurses(ItemStack pStack) {
        ListTag listtag = pStack.getTag() != null ? pStack.getTag().getList("Curses", 10) : new ListTag();
        return deserializeCurses(listtag);
    }
    public static CompoundTag getCurseTag(ItemStack stack, String pId){
        ListTag listtag = stack.getTag().getList("Curses", Tag.TAG_COMPOUND);
        for (int i = 0; i < listtag.size(); i++){
            CompoundTag curseTag = listtag.getCompound(i);
            if (curseTag.getString("id").equals(pId)) return curseTag;
        }
        return null;
    }
    public static Collection<AbstractCurse> deserializeCurses(ListTag pSerialized) {
        Set<AbstractCurse> set = new HashSet<>();

        for(int i = 0; i < pSerialized.size(); ++i) {
            CompoundTag compoundtag = pSerialized.getCompound(i);
            set.add(OrdemParanormalAPI.getInstance().getCurse(getCurseId(compoundtag)));
        }

        return set;
    }
    public static void setCurses(Collection<AbstractCurse> pCurses, ItemStack pStack) {
        ListTag listtag = new ListTag();

        for (AbstractCurse curse : pCurses){
            if (!curse.isTemporary()) listtag.add(storeCurse(curse.getId()));
            if (curse.isTemporary()) listtag.add(storeCurse(curse.getId(), curse.getMaxTicks()));
        }

        if (listtag.isEmpty()) {
            pStack.removeTagKey("Curses");
        } else {
            pStack.addTagElement("Curses", listtag);
        }
    }
    public static void addCurse(ItemStack pStack, AbstractCurse pCurse){
        Collection<AbstractCurse> curses = CurseHelper.getCurses(pStack);
        curses.add(pCurse);
        CurseHelper.setCurses(curses, pStack);
    }
    public static void removeCurse(ItemStack pStack, AbstractCurse pCurse){
        Collection<AbstractCurse> curses = CurseHelper.getCurses(pStack);
        curses.remove(pCurse);
        CurseHelper.setCurses(curses, pStack);
    }
    public static void doPostAttackEffects(LivingEntity pAttacker, Entity pTarget){
        if (pAttacker != null) {
            for (AbstractCurse curse : getCurses(pAttacker.getMainHandItem())) {
                curse.doPostAttack(pAttacker, pTarget);
                pTarget.hurt(curse.getElement().getEquivalentDamage(), curse.getDamageBonus());
            }
        }
    }
    public static void doPostHurtEffects(LivingEntity pTarget, Entity pAttacker, float pAmount, DamageSource source){
        if (pTarget != null) {
            for (ItemStack item : pTarget.getAllSlots()) {
                for (AbstractCurse curse : getCurses(item)) {
                    curse.doPostHurt(pTarget, pAttacker);
                    pTarget.heal(Math.min(curse.getDamageProtection(source), pAmount));
                }
            }
        }
    }
    public static void doTickEffects(LivingEntity pUser){
        if (pUser != null) {
            for (ItemStack item : pUser.getAllSlots()) {
                for (AbstractCurse curse : getCurses(item)) {
                    curse.doTick(pUser);
                }
            }
        }
    }
    public static void tickCurses(LivingEntity pUser){
        if (pUser != null) {
            for (ItemStack item : pUser.getAllSlots()) {
                for (AbstractCurse curse : getCurses(item)) {
                    if (curse.isTemporary()) {
                        int newTicks = getCurseTag(item, curse.getId()).getInt("ticks") - 1;
                        CurseHelper.setTicks(getCurseTag(item, curse.getId()), newTicks);
                        item.setPopTime(0);
                    }
                    if (getCurseTag(item, curse.getId()).getInt("ticks") <= 0) {
                        Collection<AbstractCurse> curses = CurseHelper.getCurses(item);
                        curses.remove(curse);
                        CurseHelper.setCurses(curses, item);
                    }
                }
            }
        }
    }
}
