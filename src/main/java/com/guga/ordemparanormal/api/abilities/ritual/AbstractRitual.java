package com.guga.ordemparanormal.api.abilities.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.events.RitualUsedEvent;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.common.item.RitualComponent;
import com.guga.ordemparanormal.common.item.RitualItem;
import com.guga.ordemparanormal.common.power.Afinidade;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nullable;

public abstract class AbstractRitual{
    private final ResourceLocation id;
    private final ParanormalElement element;
    private final int tier;
    private final int effortCost;
    private final boolean hasEntityTarget;
    private final double range;
    private final boolean mustHoldIngredient;
    public AbstractRitual(ResourceLocation id, ParanormalElement element, int tier, int effortCost, boolean hasEntityTarget, double range, boolean mustHoldIngredient) {
        this.id = id;
        this.element = element;
        this.tier = Mth.clamp(tier, 1, 4);
        this.effortCost = effortCost;
        this.hasEntityTarget = hasEntityTarget;
        this.range = range;
        this.mustHoldIngredient = mustHoldIngredient;
    }
    public ResourceLocation getId() { return id; }
    public String getTranslationKey(){
        return getId().getNamespace() + ".ritual." + getId().getPath();
    }
    public Component getDisplayName(){
        return new TranslatableComponent(getTranslationKey());
    }
    public ParanormalElement getElement() {
        return element;
    }
    public Component getDescription(){
        return new TranslatableComponent(this.getTranslationKey() + ".description").withStyle(ChatFormatting.WHITE);
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

                if (!player.isCreative()){
                    nex.setCurrentEffort(nex.getCurrentEffort() - getEffortCost());
                    if (useIngredient){
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
                        } else ingredient.setDamageValue(ingredient.getDamageValue() + 1);

                        if (ingredient.getItem() instanceof RitualComponent comp && comp.getUsedSound() != null) {
                            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), comp.getUsedSound(), SoundSource.PLAYERS, 1f, 1f);
                        }
                    }
                }

                if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                    if (hasEntityTarget()){
                        onUseSelf(rayTraceResult, world, caster, ritualItem, hand);
                        if (world instanceof ServerLevel level) usedParticles(level, caster, null);
                    } else {
                        onUseBlock(blockHitResult, world, caster, ritualItem, hand);
                        if (world instanceof ServerLevel level) usedParticles(level, caster, null);
                    }
                } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                    if (this.range > 0d) {
                        onUseEntity(entityHitResult, world, caster, ritualItem, hand);
                    } else {
                        onUseSelf(rayTraceResult, world, caster, ritualItem, hand);
                    }
                    if (world instanceof ServerLevel level) usedParticles(level, caster, entityHitResult.getEntity());
                } else {
                    onUseSelf(rayTraceResult, world, caster, ritualItem, hand);
                    if (world instanceof ServerLevel level) usedParticles(level, caster, null);
                }

                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), OPSounds.RITUAL_USED.get(), SoundSource.PLAYERS, 1f, 1f);
            } else {
                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), OPSounds.RITUAL_FAILED.get(), SoundSource.PLAYERS, 1f, 1f);

                ItemStack ingredient = mustHoldIngredient() ? player.getOffhandItem() :
                        player.getInventory().items.stream().filter(stack -> stack.getItem() instanceof RitualComponent comp &&
                                comp.element == element).findFirst().get();

                if (ingredient.getItem() instanceof RitualComponent comp && comp.getUsedSound() != null) {
                    player.level.playSound(null, player.getX(), player.getY(), player.getZ(), comp.getUsedSound(), SoundSource.PLAYERS, 1f, 1f);
                }
            }
        } else {
            RitualUsedEvent event = new RitualUsedEvent(caster, this, rayTraceResult);
            MinecraftForge.EVENT_BUS.post(event);

            if (!event.isCanceled()) {
                if (rayTraceResult instanceof BlockHitResult blockHitResult) {
                    if (hasEntityTarget()) {
                        onUseSelf(rayTraceResult, world, caster, null, null);
                        if (world instanceof ServerLevel level) usedParticles(level, caster, null);
                    } else {
                        onUseBlock(blockHitResult, world, caster, null, null);
                        if (world instanceof ServerLevel level) usedParticles(level, caster, null);
                    }
                } else if (rayTraceResult instanceof EntityHitResult entityHitResult) {
                    if (this.range > 0d) {
                        onUseEntity(entityHitResult, world, caster, null, null);
                    } else {
                        onUseSelf(rayTraceResult, world, caster, null, null);
                    }
                    if (world instanceof ServerLevel level) usedParticles(level, caster, entityHitResult.getEntity());
                } else {
                    onUseSelf(rayTraceResult, world, caster, null, null);
                    if (world instanceof ServerLevel level) usedParticles(level, caster, null);
                }

                caster.level.playSound(null, caster.getX(), caster.getY(), caster.getZ(), OPSounds.RITUAL_USED.get(), SoundSource.PLAYERS, 1f, 1f);
            }
        }
    }
    private void usedParticles(ServerLevel level, LivingEntity caster, @Nullable Entity entityTarget){
        for (int i = 0; i < 360; i++){
            if (i % 20 == 0){
                for (int i2 = 1; i2 < 4; i2++){
                    level.sendParticles(new DustParticleOptions(this.element.getParticleVec3fColor(), 0.7f),
                            caster.getX() + Math.cos(i) * i2/4d, caster.getY() + 0.1d, caster.getZ() + Math.sin(i) * i2/4d,
                            0, 0d, 0d, 0d, 1d);
                }

                level.sendParticles(ParticleTypes.INSTANT_EFFECT,
                        caster.getX() + Math.cos(i) * 0.3d, caster.getY() + 0.2d, caster.getZ() + Math.sin(i) * 0.3d,
                        0, 0d, 0d, 0d, 1d);
            }
        }

        if (entityTarget != null) {
            level.sendParticles(new DustParticleOptions(this.element.getParticleVec3fColor(), 0.7f),
                    entityTarget.getX(), entityTarget.getY() + entityTarget.getEyeHeight() / 2d, entityTarget.getZ(),
                    5, 0.3d, 0.3d, 0.3d, 1d);
        }
    }
    /**
     * Chamado quando o ritual é utilizado em uma entidade como alvo
     *
     * @param rayTraceResult a entidade que foi atingido
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster, @Nullable ItemStack ritualItem, @Nullable InteractionHand hand){}
    /**
     * Chamado quando o ritual é utilizado em um bloco como alvo
     *
     * @param rayTraceResult o bloco que foi atingido
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    public void onUseBlock(BlockHitResult rayTraceResult, Level world, LivingEntity caster, @Nullable ItemStack ritualItem, @Nullable InteractionHand hand){}

    /**
     * Chamado quando o ritual não possui alvo específico (não é uma entidade ou um bloco)
     *
     * @param world o level em que o ritual foi utilizado
     * @param caster a entidade que utilizou o ritual
     */
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, @Nullable ItemStack ritualItem, @Nullable InteractionHand hand){}
}
