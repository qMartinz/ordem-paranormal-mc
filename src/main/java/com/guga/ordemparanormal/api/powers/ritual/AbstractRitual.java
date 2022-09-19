package com.guga.ordemparanormal.api.powers.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.common.CommonComponents;
import com.guga.ordemparanormal.common.item.RitualComponent;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
    private final boolean mustHoldIngredient;
    public AbstractRitual(String id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget, double range, boolean mustHoldIngredient) {
        this.id = id;
        this.element = element;
        this.tier = Math.max(1, Math.min(4, tier));
        this.effortCost = effortCost;
        this.hasEntityTarget = hasEntityTarget;
        this.range = range;
        this.mustHoldIngredient = mustHoldIngredient;
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
            case SANGUE -> formatting = ChatFormatting.DARK_RED;
            case CONHECIMENTO -> formatting = ChatFormatting.GOLD;
            case ENERGIA -> formatting = ChatFormatting.DARK_PURPLE;
            case MORTE -> formatting = ChatFormatting.DARK_GRAY;
            case MEDO -> formatting = ChatFormatting.WHITE;
            case NONE -> formatting = ChatFormatting.GRAY;
        }
        Component title = getDisplayName().plainCopy().withStyle(formatting);
        lines.add(title);

        lines.add(TextComponent.EMPTY);

        lines.add(new TranslatableComponent(this.getTranslationKey() + ".description").withStyle(ChatFormatting.GRAY));
        lines.add(CommonComponents.CONSUMES.plainCopy().append(" " + this.effortCost + " " + CommonComponents.EFFORT_POINTS_FULL_NAME.getString())
                .withStyle(ChatFormatting.GRAY));

        return lines;
    }
    public int getTier() { return tier; }
    public int getPresenceRequired(){
        return switch (tier) {
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 8;
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
    public boolean hasIngredient() {
        return element != ParanormalElement.MEDO && element != ParanormalElement.NONE;
    }
    public boolean mustHoldIngredient() {
        return mustHoldIngredient;
    }

    /**
     * Chamado quando o ritual é utilizado
     *
     * @param rayTraceResult o que foi atingido
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    public void onUse(@Nullable HitResult rayTraceResult, Level world, LivingEntity caster){
        if (caster instanceof Player player){
            player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playernex -> {
                boolean canCast = player.isCreative();

                if (!player.isCreative() && playernex.getCurrentEffort() >= getEffortCost()){
                    if (hasIngredient()){
                        if (mustHoldIngredient()){
                            if (player.getOffhandItem().getItem() instanceof RitualComponent component &&
                                component.element == this.element) canCast = true;
                        } else {
                            if (player.getInventory().items.stream().anyMatch(stack ->
                                    stack.getItem() instanceof RitualComponent component &&
                                    component.element == this.element)) canCast = true;
                        }
                    } else {
                        canCast = true;
                    }
                }

                if (canCast){
                    if (!player.isCreative()){
                        playernex.setCurrentEffort(playernex.getCurrentEffort() - getEffortCost());
                        if (hasIngredient()){
                            ItemStack ingredient = mustHoldIngredient() ? player.getOffhandItem() :
                                    player.getInventory().items.stream().filter(stack -> stack.getItem() instanceof RitualComponent comp &&
                                            comp.element == element).findFirst().get();

                            if (ingredient.getDamageValue() + 1 >= ingredient.getMaxDamage()){
                                if (mustHoldIngredient) {
                                    player.getOffhandItem().shrink(1);
                                    player.setItemSlot(EquipmentSlot.OFFHAND, OPItems.COMPONENTE_VAZIO.get().getDefaultInstance());
                                } else {
                                    player.getInventory().setItem(player.getInventory().items.indexOf(ingredient),
                                            OPItems.COMPONENTE_VAZIO.get().getDefaultInstance());
                                }
                            } else {
                                ingredient.setDamageValue(ingredient.getDamageValue() + 1);
                            }
                        }
                    }

                    if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                        if (hasEntityTarget()){
                            onUseSelf(world, caster);
                        } else {
                            onUseBlock(blockHitResult, world, caster);
                        }
                    } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                        onUseEntity(entityHitResult, world, caster);
                    } else {
                        onUseSelf(world, caster);
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
