package com.guga.ordemparanormal.api.powers.ritual;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.powers.ParanormalElement;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRitual{
    private final String id;
    private final ParanormalElement element;
    private final int tier;
    private final int effortCost;
    private final Item ingredient;
    public AbstractRitual(String id, ParanormalElement element, int tier, int effortCost, @Nullable Item ingredient) {
        this.id = id;
        this.element = element;
        this.tier = Math.max(1, Math.min(4, tier));
        this.effortCost = effortCost;
        this.ingredient = ingredient;
    }
    public String getId() { return id; }
    public String getTranslationKey(){
        return "ordemparanormal.ritual." + getId();
    }
    public Component getDisplayName(){
        return new TranslatableComponent(getTranslationKey());
    }
    public List<Component> getDescription(){
        List<Component> lines = new ArrayList<>();

        ChatFormatting formatting = ChatFormatting.WHITE;
        switch (element){
            case BLOOD -> formatting = ChatFormatting.DARK_RED;
            case KNOWLEDGE -> formatting = ChatFormatting.GOLD;
            case ENERGY -> formatting = ChatFormatting.DARK_PURPLE;
            case DEATH -> formatting = ChatFormatting.DARK_GRAY;
            case FEAR -> formatting = ChatFormatting.WHITE;
            case NONE -> formatting = ChatFormatting.GRAY;
        }
        Component title = getDisplayName().plainCopy().withStyle(formatting);
        lines.add(title);
        lines.add(TextComponent.EMPTY);
        for (int i = 1; i < 3; i++){
            lines.add(new TranslatableComponent(this.getTranslationKey() + ".description.line_" + i).withStyle(ChatFormatting.GRAY));
        }
        return lines;
    }
    public int getTier() { return tier; }
    public int getNexRequired(){
        switch (tier){
            case 1:
                return 1;
            case 2:
                return 5;
            case 3:
                return 10;
            case 4:
                return 15;
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
                    }
                }
            });
        }
    }
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, @Nullable LivingEntity caster){}
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, @Nullable LivingEntity caster){}
}
