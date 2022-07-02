package com.guga.ordemparanormal.api.ritual;

import com.guga.ordemparanormal.common.capabilities.nexplayer.NexCapability;
import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public abstract class AbstractRitual {
    private final String name;
    private final int nexRequired;
    private final int effortCost;
    public AbstractRitual(String name, int nexRequired, int effortCost) {
        this.name = name;
        this.nexRequired = nexRequired;
        this.effortCost = effortCost;
    }
    public String getName() { return name; }
    public String getTranslationKey(){
        return "ordemparanormal.ritual." + getName();
    }
    public Component getDisplayName(){
        return new TranslatableComponent(getTranslationKey());
    }
    public int getNexRequired() { return nexRequired; }
    public int getEffortCost() { return effortCost; }
    public void onUse(HitResult rayTraceResult, Level world, @Nullable LivingEntity caster){
        if (caster instanceof Player player && player.getCapability(NexCapability.INSTANCE).isPresent()){
            NexModel model = NexModel.get(player);
            if (player.isCreative()){
                if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                    onUseBlock((BlockHitResult) rayTraceResult, world, caster);
                } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                    onUseEntity((EntityHitResult) rayTraceResult, world, caster);
                }
                System.out.println("Ritual used");
            } else {
                if (model.getEffortPoints() >= effortCost) {
                    model.setCurEffortPoints(model.getEffortPoints() - effortCost);
                    if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                        onUseBlock((BlockHitResult) rayTraceResult, world, caster);
                    } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                        onUseEntity((EntityHitResult) rayTraceResult, world, caster);
                    }
                    System.out.println("Ritual used");
                } else {
                    if (player.level.isClientSide) {
                        player.playSound(SoundEvents.BEE_DEATH, 1F, 1F);
                    }
                    System.out.println("Not enough Effort Points");
                }
            }
        }
    }
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, @Nullable LivingEntity caster){}
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, @Nullable LivingEntity caster){}
}
