package com.guga.ordemparanormal.api.paranormaldamage;

import net.minecraft.network.chat.Component;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class EntityParanormalDamageSource extends ParanormalDamageSource {
    protected final Entity entity;
    public EntityParanormalDamageSource(String msgId, Entity entity) {
        super(msgId);
        this.entity = entity;
    }
    @Override
    public Entity getEntity() {
        return this.entity;
    }
    @Override
    public Component getLocalizedDeathMessage(LivingEntity pLivingEntity) {
        ItemStack itemstack = this.entity instanceof LivingEntity ? ((LivingEntity)this.entity).getMainHandItem() : ItemStack.EMPTY;
        String s = "death.attack." + this.msgId;
        return !itemstack.isEmpty() && itemstack.hasCustomHoverName() ? Component.translatable(s + ".item", pLivingEntity.getDisplayName(), this.entity.getDisplayName(), itemstack.getDisplayName()) : Component.translatable(s, pLivingEntity.getDisplayName(), this.entity.getDisplayName());
    }
    @Override
    public boolean scalesWithDifficulty() {
        return this.entity instanceof LivingEntity && !(this.entity instanceof Player);
    }
    @Override
    @Nullable
    public Vec3 getSourcePosition() {
        return this.entity.position();
    }
    @Override
    public String toString() {
        return "EntityDamageSource (" + this.entity + ")";
    }
}
