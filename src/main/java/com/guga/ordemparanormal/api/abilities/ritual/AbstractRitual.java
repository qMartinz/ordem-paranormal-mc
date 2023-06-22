package com.guga.ordemparanormal.api.abilities.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.events.RitualUsedEvent;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.common.entity.RitualProjectile;
import com.guga.ordemparanormal.common.item.RitualComponent;
import com.guga.ordemparanormal.common.item.RitualItem;
import com.guga.ordemparanormal.common.power.Afinidade;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;
import java.awt.*;

public abstract class AbstractRitual{
    public static final AbstractRitual EMPTY = new AbstractRitual(new ResourceLocation(OrdemParanormal.MOD_ID, "empty"), ParanormalElement.NONE, 1,  0, 0, true) {};
    private final ResourceLocation id;
    private final ParanormalElement element;
    private final int tier;
    private final int effortCost;
    private final double range;
    private final boolean mustHoldIngredient;
    public AbstractRitual(ResourceLocation id, ParanormalElement element, int tier, int effortCost, double range, boolean mustHoldIngredient) {
        this.id = id;
        this.element = element;
        this.tier = Mth.clamp(tier, 1, 4);
        this.effortCost = effortCost;
        this.range = range;
        this.mustHoldIngredient = mustHoldIngredient;
    }
    public ResourceLocation getId() { return id; }
    public String getTranslationKey(){
        return getId().getNamespace() + ".ritual." + getId().getPath();
    }
    public Component getDisplayName(){
        return  Component.translatable(getTranslationKey());
    }
    public ParanormalElement getElement() {
        return element;
    }
    public Component getDescription(){
        return Component.translatable(this.getTranslationKey() + ".description").withStyle(ChatFormatting.WHITE);
    }
    public int getTier() { return tier; }
    public int getPresenceRequired(){
        return switch (tier) {
            case 2 -> 2;
            case 3 -> 4;
            case 4 -> 6;
            default -> 0;
        };
    }
    public int getEffortCost() { return effortCost; }
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
            INexCap nex = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            IAbilitiesCap abi = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
            if (nex == null || abi == null) return;

            boolean canCast = player.isCreative();
            boolean useIngredient = hasIngredient() && abi.getPowers().stream().noneMatch(p -> p instanceof Afinidade && p.getElement() == element);

            if (!player.isCreative() && nex.getCurrentEffort() >= getEffortCost()){
                if (useIngredient){
                    if (mustHoldIngredient()) {
                        if (player.getOffhandItem().getItem() instanceof RitualComponent component &&
                                component.element == this.element) canCast = true;

                    } else {
                        if (player.getInventory().items.stream().anyMatch(stack ->
                                stack.getItem() instanceof RitualComponent component &&
                                        component.element == this.element)) canCast = true;
                    }
                } else canCast = true;
            }

            RitualUsedEvent event = new RitualUsedEvent(caster, this, rayTraceResult);
            MinecraftForge.EVENT_BUS.post(event);

            if (canCast && !event.isCanceled()){
                ItemStack ritualItem;
                InteractionHand hand;

                if (player.getMainHandItem().getItem() instanceof RitualItem item && item.ritual == this){
                    ritualItem = player.getMainHandItem();
                    hand = InteractionHand.MAIN_HAND;
                } else if (player.getOffhandItem().getItem() instanceof RitualItem item && item.ritual == this){
                    ritualItem = player.getOffhandItem();
                    hand = InteractionHand.OFF_HAND;
                } else {
                    ritualItem = null;
                    hand = null;
                }

                if ((this instanceof OffensiveRitual || this instanceof UtilityRitual) && !caster.isCrouching()){
                    RitualProjectile projectile = new RitualProjectile(world, caster, this);
                    projectile.shootFromRotation(caster, caster.getXRot(), caster.getYRot(), 0.0f, 1.5f, 0.0f);
                    world.addFreshEntity(projectile);
                    projectile.setElement(this.element.index);
                    ritualSuccess((ServerLevel) world, caster);
                } else if (this instanceof DefensiveRitual ritual && caster.isCrouching()) {
                    ritual.onUseSelf(rayTraceResult, world, caster, ritualItem, hand);
                    ritualSuccess((ServerLevel) world, caster);
                } else {
                    ritualFail(caster);
                }

                if (!player.isCreative()) useResources(useIngredient, nex, player);

                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), OPSounds.RITUAL_USED.get(),  caster.getSoundSource(), 1f, 1f);
            } else ritualFail(caster);
        } else ritualFail(caster);
    }
    //TODO particula
    public void ritualSuccess(ServerLevel level, LivingEntity caster){
        for (int i = 0; i < 360; i++){
            if (i % 20 == 0){
                level.sendParticles(AbilitiesParticleOptions.createData(getElement().getParticleColor(), getElement() != ParanormalElement.MORTE),
                        caster.getX() + Math.cos(i) * 0.4d, caster.getY() + 0.1d, caster.getZ() + Math.sin(i) * 0.4d,
                        0, 0d, 0d, 0d, 1d);
                level.sendParticles(AbilitiesParticleOptions.createData(getElement().getParticleColor(), getElement() != ParanormalElement.MORTE),
                        caster.getX() + Math.cos(i) * 0.6d, caster.getY() + 0.1d, caster.getZ() + Math.sin(i) * 0.6d,
                        0, 0d, 0d, 0d, 1d);

                Color color = getElement().getParticleColor().brighter().brighter().brighter();
                if (getElement() == ParanormalElement.MEDO) color = new Color(195, 249, 255);

                level.sendParticles(AbilitiesParticleOptions.createData(color, getElement() != ParanormalElement.MORTE),
                        caster.getX() + Math.cos(i) * 0.3d, caster.getY() + 0.2d, caster.getZ() + Math.sin(i) * 0.3d,
                        0, 0d, 0.08d, 0d, 1d);
            }
        }

        caster.level.playSound(null, caster.getX(), caster.getY(), caster.getZ(), OPSounds.RITUAL_USED.get(), caster.getSoundSource(), 1f, 1f);
    }
    protected void ritualFail(LivingEntity caster){
        caster.level.playSound(null, caster.getX(), caster.getY(), caster.getZ(), OPSounds.RITUAL_FAILED.get(),  caster.getSoundSource(), 1f, 1f);

        ItemStack ingredient;
        if (caster instanceof Player player) {
            ingredient = mustHoldIngredient() ? caster.getOffhandItem() :
                    player.getInventory().items.stream().filter(stack -> stack.getItem() instanceof RitualComponent comp &&
                    comp.element == element).findFirst().orElse(null);
        } else {
            ingredient = caster.getOffhandItem();
        }

        if (ingredient.getItem() instanceof RitualComponent comp && comp.getUsedSound() != null) {
            caster.level.playSound(null, caster.getX(), caster.getY(), caster.getZ(), comp.getUsedSound(),  caster.getSoundSource(), 1f, 1f);
        }
    }
    protected void useResources(boolean useIngredient, INexCap nex, Player player){
        nex.setCurrentEffort(nex.getCurrentEffort() - getEffortCost());
        if (useIngredient){
            ItemStack ingredient = mustHoldIngredient() ? player.getOffhandItem() :
                    player.getInventory().items.stream().filter(stack -> stack.getItem() instanceof RitualComponent comp &&
                            comp.element == element).findFirst().orElse(ItemStack.EMPTY);

            if (ingredient.getItem() instanceof RitualComponent) {
                if (ingredient.getDamageValue() + 1 >= ingredient.getMaxDamage()) {
                    if (mustHoldIngredient) {
                        player.getOffhandItem().shrink(1);
                        player.setItemSlot(EquipmentSlot.OFFHAND, OPItems.COMPONENTE_VAZIO.get().getDefaultInstance());
                    } else {
                        player.getInventory().setItem(player.getInventory().items.indexOf(ingredient),
                                OPItems.COMPONENTE_VAZIO.get().getDefaultInstance());
                    }
                } else ingredient.setDamageValue(ingredient.getDamageValue() + 1);
            }

            if (ingredient.getItem() instanceof RitualComponent comp && comp.getUsedSound() != null) {
                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), comp.getUsedSound(), SoundSource.PLAYERS, 1f, 1f);
            }
        }
    }
}
