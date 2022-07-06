package com.guga.ordemparanormal.api.ritual;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public abstract class AbstractRitual{
    private final String name;
    private final int tier;
    private final int effortCost;
    private final Item ingredients;
    public AbstractRitual(String name, int tier, int effortCost, @Nullable Item ingredients) {
        this.name = name;
        this.tier = Math.max(1, Math.min(4, tier));
        this.effortCost = effortCost;
        this.ingredients = ingredients;
    }
    public String getName() { return name; }
    public String getTranslationKey(){
        return "ordemparanormal.ritual." + getName();
    }
    public Component getDisplayName(){
        return new TranslatableComponent(getTranslationKey());
    }
    public int getTier() { return tier; }
    public int getNexRequired(){
        switch (tier){
            case 1:
                return 5;
            case 2:
                return 25;
            case 3:
                return 50;
            case 4:
                return 75;
            default:
                return 0;
        }
    }
    public int getEffortCost() { return effortCost; }
    public void onUse(HitResult rayTraceResult, Level world, @Nullable LivingEntity caster){
        if (caster instanceof Player player){
            player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playernex -> {
                if (player.isCreative()){
                    if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                        onUseBlock((BlockHitResult) rayTraceResult, world, caster);
                    } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                        onUseEntity((EntityHitResult) rayTraceResult, world, caster);
                    }
                    System.out.println("Ritual used");
                } else {
                    if (playernex.getCurrentEffort() >= effortCost) {
                        playernex.setCurrentEffort(playernex.getCurrentEffort() - effortCost);
                        if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                            onUseBlock((BlockHitResult) rayTraceResult, world, caster);
                        } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                            onUseEntity((EntityHitResult) rayTraceResult, world, caster);
                        }
                        System.out.println("Ritual used");
                    } else {
                        player.level.playSound(null, player.blockPosition(), SoundEvents.BEE_POLLINATE, SoundSource.PLAYERS, 1f, 1f);
                        System.out.println("Not enough Effort Points");
                    }
                }
            });
        }
    }
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, @Nullable LivingEntity caster){}
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, @Nullable LivingEntity caster){}
}
