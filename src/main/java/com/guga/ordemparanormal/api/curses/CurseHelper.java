package com.guga.ordemparanormal.api.curses;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.api.util.NBTUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CurseHelper {
    private static final String PREFIX = "curse_";
    public static ResourceLocation getCurseId(CompoundTag pCompoundTag) {
        return ResourceLocation.tryParse(pCompoundTag.getString("id"));
    }
    public static int getCurseUses(CompoundTag pCompoundTag) {
        return pCompoundTag.getInt("uses");
    }
    public static Set<CurseInstance> getCurses(ItemStack pStack) {
        CompoundTag compoundTag = pStack.getTag() != null ? pStack.getTag().getCompound("Curses") : new CompoundTag();
        return deserializeCurses(compoundTag);
    }
    public static CurseInstance getCurse(ItemStack stack, AbstractCurse curse){
        CompoundTag compoundTag = stack.getTag() != null ? stack.getTag().getCompound("Curses") : new CompoundTag();
        for (int i = 0; i < compoundTag.size(); i++){
            CompoundTag curseTag = compoundTag.getCompound(PREFIX + i);
            if (getCurseId(curseTag).equals(curse.getId())) {
                return new CurseInstance(OrdemParanormalAPI.getInstance().getCurse(getCurseId(curseTag)), getCurseUses(curseTag));
            }
        }
        return null;
    }
    public static Set<CurseInstance> deserializeCurses(CompoundTag pSerialized) {
        Set<CurseInstance> set = new HashSet<>();

        for(CompoundTag c : NBTUtil.readCurses(pSerialized)){
            set.add(new CurseInstance(OrdemParanormalAPI.getInstance().getCurse(ResourceLocation.tryParse(c.getString("id"))), c.getInt("uses")));
        }

        return set;
    }
    public static void setCurses(Collection<CurseInstance> pCurses, ItemStack pStack) {
        CompoundTag compoundTag = new CompoundTag();

        for (int i = 0; i < pCurses.size(); i++) compoundTag.put(PREFIX + i, pCurses.stream().toList().get(i).serializeNBT());

        if (compoundTag.isEmpty()) {
            pStack.removeTagKey("Curses");
        } else {
            pStack.addTagElement("Curses", compoundTag);
        }
    }
    public static ItemStack addCurse(ItemStack pStack, CurseInstance pCurse){
        Set<CurseInstance> curses = CurseHelper.getCurses(pStack);
        curses.removeIf(c -> c.getCurse().getId().equals(pCurse.getCurse().getId()));
        if (curses.stream().filter(inst -> !inst.getCurse().isTemporary()).toList().size() < 4) {
            curses.add(pCurse);
        }
        CurseHelper.setCurses(curses, pStack);
        return pStack;
    }
    public static ItemStack addCurse(ItemStack pStack, AbstractCurse pCurse){
        Set<CurseInstance> curses = CurseHelper.getCurses(pStack);
        curses.removeIf(c -> c.getCurse().getId().equals(pCurse.getId()));
        if (curses.stream().filter(inst -> !inst.getCurse().isTemporary()).toList().size() < 4) {
            curses.add(new CurseInstance(pCurse));
        }
        CurseHelper.setCurses(curses, pStack);
        return pStack;
    }

    public static ItemStack removeCurse(ItemStack pStack, AbstractCurse pCurse){
        Set<CurseInstance> curses = CurseHelper.getCurses(pStack);
        curses.removeIf(c -> c.getCurse().getId().equals(pCurse.getId()));
        CurseHelper.setCurses(curses, pStack);
        return pStack;
    }
    public static float doPostAttackEffects(LivingEntity pAttacker, LivingEntity pTarget, float pAmount, DamageSource source){
        float f = pAmount;
        if (pAttacker != null) {
            for (CurseInstance curse : getCurses(pAttacker.getMainHandItem())) {
                if (curse != null) {
                    f = curse.getCurse().doPostAttack(pAttacker.getMainHandItem(), pAttacker, pTarget, f, source);

                    f += curse.getCurse().getDamageBonus()
                            * (ParanormalDamageSource.isEntityWeakTo(pTarget, curse.getCurse().getElement().getDamage()) ? 2f : 1f)
                            / (ParanormalDamageSource.isEntityResistant(pTarget, curse.getCurse().getElement().getDamage()) ? 2f : 1f);
                }
            }
        }
        return f;
    }
    public static float doPostHurtEffects(LivingEntity pTarget, @Nullable Entity pAttacker, float pAmount, DamageSource source){
        float f = pAmount;
        if (pTarget != null) {
            for (ItemStack item : pTarget.getAllSlots()) {
                for (CurseInstance curseInstance : getCurses(item)) {
                    if (curseInstance != null) {
                        f = curseInstance.getCurse().doPostHurt(item, pTarget, pAttacker, f, source);

                        f -= curseInstance.getCurse().getDamageProtection(source);
                    }
                }
            }
        }
        return f;
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
    public static int doBlockBreakEffects(Player pUser, int pXP, BlockPos pPos, BlockState pState){
        if (pUser != null) {
            for (ItemStack item : pUser.getAllSlots()) {
                for (CurseInstance curse : getCurses(item)) {
                    if (curse != null) pXP = curse.getCurse().doBlockBreak(pUser, pUser.level, pPos, pState, pXP);
                }
            }
        }
        return pXP;
    }
}
