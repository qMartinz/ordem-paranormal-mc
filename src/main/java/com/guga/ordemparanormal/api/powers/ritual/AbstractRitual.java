package com.guga.ordemparanormal.api.powers.ritual;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.powers.ParanormalElement;
import com.guga.ordemparanormal.common.CommonComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
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
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRitual{
    private final String id;
    private final ParanormalElement element;
    private final int tier;
    private final int effortCost;
    private final boolean hasEntityTarget;
    private final double range;
    private final Item ingredient;
    public AbstractRitual(String id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget, double range, @Nullable Item ingredient) {
        this.id = id;
        this.element = element;
        this.tier = Math.max(1, Math.min(4, tier));
        this.effortCost = effortCost;
        this.hasEntityTarget = hasEntityTarget;
        this.range = range;
        this.ingredient = ingredient;
    }
    public String getId() { return id; }
    public String getTranslationKey(){
        return "ordemparanormal.ritual." + getId();
    }
    public Component getDisplayName(){
        return new TranslatableComponent(getTranslationKey());
    }
    public ParanormalElement getElement() {
        return element;
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
        lines.add(CommonComponents.INGREDIENT.plainCopy().append(": ")
                .append(getIngredient().getName(getIngredient().getDefaultInstance())).withStyle(ChatFormatting.GRAY));
        lines.add(TextComponent.EMPTY);

        lines.add(new TranslatableComponent(this.getTranslationKey() + ".description").withStyle(ChatFormatting.GRAY));
        lines.add(CommonComponents.CONSUMES.plainCopy().append(" " + this.effortCost + " " + CommonComponents.EFFORT_POINTS_FULL_NAME.getString())
                .withStyle(ChatFormatting.GRAY));

        return lines;
    }
    public int getTier() { return tier; }
    public int getNexRequired(){
        return switch (tier) {
            case 1 -> 1;
            case 2 -> 5;
            case 3 -> 10;
            case 4 -> 15;
            default -> 0;
        };
    }
    public int getEffortCost() { return effortCost; }

    public boolean hasEntityTarget() {
        return hasEntityTarget;
    }

    public double getRange() {
        return range;
    }
    public Item getIngredient() {
        return ingredient;
    }
    /**
     * Chamado quando o ritual é utilizado
     *
     * @param rayTraceResult o que foi atingido
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    public void onUse(@Nullable HitResult rayTraceResult, Level world, @Nullable LivingEntity caster){
        if (caster instanceof Player player){
            player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playernex -> {
                if (player.isCreative()){
                    // ritual é usado no criativo
                    if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                        if (hasEntityTarget()){
                            onUseSelf(world, caster);
                        } else {
                            onUseBlock((BlockHitResult) rayTraceResult, world, caster);
                        }
                    } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                        onUseEntity((EntityHitResult) rayTraceResult, world, caster);
                    } else {
                        onUseSelf(world, caster);
                    }
                } else {
                    if (playernex.getCurrentEffort() >= getEffortCost()) {
                        if (getIngredient() != null && caster.getOffhandItem().getItem() == getIngredient()) {
                            // ritual que necessita de ingrediente é usado
                            playernex.setCurrentEffort(playernex.getCurrentEffort() - getEffortCost());
                            if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                                if (hasEntityTarget()){
                                    onUseSelf(world, caster);
                                } else {
                                    onUseBlock((BlockHitResult) rayTraceResult, world, caster);
                                }
                            } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                                onUseEntity((EntityHitResult) rayTraceResult, world, caster);
                            } else {
                                onUseSelf(world, caster);
                            }
                            caster.getOffhandItem().shrink(1);
                        } else if (getIngredient() == null) {
                            // ritual que não necessita de ingrediente é usado
                            playernex.setCurrentEffort(playernex.getCurrentEffort() - getEffortCost());
                            if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                                if (hasEntityTarget()){
                                    onUseSelf(world, caster);
                                } else {
                                    onUseBlock((BlockHitResult) rayTraceResult, world, caster);
                                }
                            } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                                onUseEntity((EntityHitResult) rayTraceResult, world, caster);
                            } else {
                                onUseSelf(world, caster);
                            }
                        } else {
                            // se o player não tiver ingrediente suficiente
                            player.level.playSound(null, player.blockPosition(), SoundEvents.BEE_POLLINATE, SoundSource.PLAYERS, 1f, 1f);
                        }
                    } else {
                        // se o player não tiver ponto de esforço suficiente
                        player.level.playSound(null, player.blockPosition(), SoundEvents.BEE_POLLINATE, SoundSource.PLAYERS, 1f, 1f);
                    }
                }
            });
        }
    }

    /**
     * Chamado quando o ritual é utilizado em uma entidade como alvo
     *
     * @param rayTraceResult a entidade que foi atingido
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster){}
    /**
     * Chamado quando o ritual é utilizado em um bloco como alvo
     *
     * @param rayTraceResult o bloco que foi atingido
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster){}

    /**
     * Chamado quando o ritual não possui alvo específico (não é uma entidade ou um bloco)
     *
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    public void onUseSelf(Level world, LivingEntity caster){}
}
