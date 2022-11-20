package com.guga.ordemparanormal.api.curses;

import com.guga.ordemparanormal.api.ParanormalElement;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCurse {
    protected final ResourceLocation id;
    private final EquipmentSlot[] slots;
    public final ParanormalElement element;
    public final CurseCategory category;

    public AbstractCurse(ResourceLocation id, ParanormalElement element, CurseCategory category, EquipmentSlot... slots) {
        this.id = id;
        this.slots = slots;
        this.element = element;
        this.category = category;
    }
    public ResourceLocation getId() {
        return id;
    }
    public EquipmentSlot[] getSlots() {
        return slots;
    }
    public ParanormalElement getElement() {
        return element;
    }
    public CurseCategory getCategory() {
        return category;
    }
    public final boolean isCompatibleWith(AbstractCurse pOther) {
        return this.checkCompatibility(pOther) && pOther.checkCompatibility(this);
    }
    protected boolean checkCompatibility(AbstractCurse pOther) {
        return this != pOther && this.getElement().isCompatible(pOther.getElement());
    }
    public boolean canCurse(ItemStack stack){
        boolean flag1 = CurseHelper.getCurses(stack).stream().allMatch(inst -> inst.getCurse().isCompatibleWith(this));
        boolean flag2 = this.category.canCurse(stack.getItem());
        return flag1 && flag2;
    }
    public String getTranslationKey(){
        return this.getId().getNamespace() + ".curse." + this.getId().getPath();
    }
    public Component getDisplayName() {
        MutableComponent name = new TranslatableComponent(getTranslationKey());
        switch (element){
            case SANGUE -> name.withStyle(ChatFormatting.DARK_RED);
            case CONHECIMENTO -> name.withStyle(ChatFormatting.GOLD);
            case ENERGIA -> name.withStyle(ChatFormatting.DARK_PURPLE);
            case MORTE -> name.withStyle(ChatFormatting.DARK_GRAY);
            case MEDO -> name.withStyle(ChatFormatting.WHITE);
            case NONE -> name.withStyle(ChatFormatting.GRAY);
        }

        return name;
    }
    /**
     * Realiza seu efeito quando o usuário ataca.
     * @return a quantidade de dano após o efeito da maldição.
     */
    public float doPostAttack(ItemStack pStack, LivingEntity pAttacker, LivingEntity pTarget, float amount, DamageSource source) {
        CurseHelper.getCurse(pStack, this).useOrRemove(pStack);
        return amount;
    }
    /**
     * Realiza seu efeito quando o usuário é atacado.
     * @return a quantidade de dano após o efeito da maldição.
     */
    public float doPostHurt(ItemStack pStack, LivingEntity pTarget, @Nullable Entity pAttacker, float amount, DamageSource source) {
        CurseHelper.getCurse(pStack, this).useOrRemove(pStack);
        return amount;
    }
    /**
     * Realiza seu efeito a cada tick.
     */
    public void doTick(ItemStack pStack, LivingEntity pUser) {}
    /**
     * Chamado quando o usuário quebra um bloco
     *
     * @param entity quem quebrou
     * @param level o level onde o bloco foi quebrado
     * @param pos a posição do bloco
     * @param state o bloco quebrado
     * @param exp o exp ganho por quebrar o bloco
     * @return o exp ganho após a maldição ser ativa
     */
    public int doBlockBreak(Player entity, LevelAccessor level, BlockPos pos, BlockState state, int exp){
        return exp;
    }
    /**
     * Mantenha os usos máximos como 0 para uma maldição permanente.
     * @return quantos usos essa maldição pode ter.
     */
    public int getMaxUses() {
        return 0;
    }
    public boolean isTemporary(){
        return getMaxUses() > 0;
    }
    public int getDamageProtection(DamageSource pSource) {
        return 0;
    }
    public int getDamageBonus() {
        return 0;
    }
    public Map<AttributeModifier, Attribute> getAttributeModifiers(){
        return new HashMap<>();
    }
}
