package com.guga.ordemparanormal.common.entity;

import com.google.common.collect.Maps;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.OffensiveRitual;
import com.guga.ordemparanormal.api.abilities.ritual.UtilityRitual;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.common.item.RitualItem;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Map;

public class RitualProjectile extends AbstractArrow {
    private static final EntityDataAccessor<Integer> DATA_ELEMENT_ID = SynchedEntityData.defineId(RitualProjectile.class, EntityDataSerializers.INT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ELEMENT = Util.make(Maps.newHashMap(), (p_28140_) -> {
        p_28140_.put(0, new ResourceLocation(OrdemParanormal.MOD_ID, "textures/entity/ritual_projectile/fear.png"));
        p_28140_.put(1, new ResourceLocation(OrdemParanormal.MOD_ID, "textures/entity/ritual_projectile/blood.png"));
        p_28140_.put(2, new ResourceLocation(OrdemParanormal.MOD_ID, "textures/entity/ritual_projectile/knowledge.png"));
        p_28140_.put(3, new ResourceLocation(OrdemParanormal.MOD_ID, "textures/entity/ritual_projectile/death.png"));
        p_28140_.put(4, new ResourceLocation(OrdemParanormal.MOD_ID, "textures/entity/ritual_projectile/energy.png"));
    });
    public AbstractRitual ritual;
    private LivingEntity target;
    private final double iX;
    private final double iY;
    private final double iZ;
    public RitualProjectile(EntityType<? extends AbstractArrow> p_36721_, Level p_36722_) {
        super(p_36721_, p_36722_);
        if (ritual == null) ritual = AbstractRitual.EMPTY;
        this.iX = this.getX();
        this.iY = this.getY();
        this.iZ = this.getZ();
    }
    public RitualProjectile(EntityType<? extends AbstractArrow> p_36711_, double p_36712_, double p_36713_, double p_36714_, Level p_36715_) {
        super(p_36711_, p_36712_,  p_36713_, p_36714_, p_36715_);
        this.iX = p_36712_;
        this.iY = p_36713_;
        this.iZ = p_36714_;
    }
    public RitualProjectile(Level level, LivingEntity owner, AbstractRitual ritual){
        super(OPEntities.RITUAL_PROJECTILE.get(), owner, level);
        this.ritual = ritual;
        setNoGravity(true);
        this.iX = owner.getX();
        this.iY = owner.getEyeY() - 0.1F;
        this.iZ = owner.getZ();
    }
    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }
    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (getOwner() instanceof LivingEntity owner) {
            ItemStack ritualItem;
            InteractionHand hand;

            if (owner.getMainHandItem().getItem() instanceof RitualItem item && item.ritual == this.ritual) {
                ritualItem = owner.getMainHandItem();
                hand = InteractionHand.MAIN_HAND;
            } else if (owner.getOffhandItem().getItem() instanceof RitualItem item && item.ritual == this.ritual) {
                ritualItem = owner.getOffhandItem();
                hand = InteractionHand.OFF_HAND;
            } else {
                ritualItem = null;
                hand = null;
            }

            if (ritual instanceof UtilityRitual ritual1){
                if (pResult instanceof BlockHitResult blockHitResult){
                    ritual1.onUseBlock(blockHitResult, level, owner, ritualItem, hand);
                } else {
                    BlockHitResult blockHitResult = BlockHitResult.miss(pResult.getLocation(), getDirection(), new BlockPos(pResult.getLocation()));
                    ritual1.onUseBlock(blockHitResult, level, owner, ritualItem, hand);
                }
            }
        }

        this.discard();
    }
    @Override
    public void tick() {
        super.tick();
        if (this.level instanceof ServerLevel level1){
            level1.sendParticles(AbilitiesParticleOptions.createData(ritual.getElement().getParticleColor(), ritual.getElement() != ParanormalElement.MORTE),
                    this.getX(), this.getY(), this.getZ(), 3, 0.05d, 0.05d, 0.05d, 0d);
        }
        if (target == null || !target.isAlive()){
            target = level.getNearestEntity(LivingEntity.class, TargetingConditions.DEFAULT,
                    null, getX(), getY(), getZ(), getBoundingBox().inflate(1.5d));
            if (getOwner() instanceof Mob mob && target != mob.getTarget()) {
                target = null;
            }
        }
        if (target != null && target != getOwner() && target.isAlive()) {
            this.setDeltaMovement(target.position().add(0d, target.getEyeHeight(), 0d).subtract(position()).scale(0.005d).normalize());
        } else {
            target = null;
        }
        if (ritual != AbstractRitual.EMPTY){
            if (Math.abs(iX - getX()) > this.getRange() ||
                    Math.abs(iY - getY()) > this.getRange() ||
                    Math.abs(iZ - getZ()) > this.getRange()) {
                this.discard();

                if (getOwner() instanceof LivingEntity owner && level.getBlockState(new BlockPos(position())).isAir()) {
                    ItemStack ritualItem;
                    InteractionHand hand;

                    if (owner.getMainHandItem().getItem() instanceof RitualItem item && item.ritual == this.ritual) {
                        ritualItem = owner.getMainHandItem();
                        hand = InteractionHand.MAIN_HAND;
                    } else if (owner.getOffhandItem().getItem() instanceof RitualItem item && item.ritual == this.ritual) {
                        ritualItem = owner.getOffhandItem();
                        hand = InteractionHand.OFF_HAND;
                    } else {
                        ritualItem = null;
                        hand = null;
                    }

                    if (ritual instanceof UtilityRitual ritual1) {
                        BlockHitResult blockHitResult = BlockHitResult.miss(position(), getDirection(), new BlockPos(position()));
                        ritual1.onUseBlock(blockHitResult, level, owner, ritualItem, hand);
                    }
                }
            }
        }
    }
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (getOwner() instanceof LivingEntity owner) {
            ItemStack ritualItem;
            InteractionHand hand;

            if (owner.getMainHandItem().getItem() instanceof RitualItem item && item.ritual == this.ritual) {
                ritualItem = owner.getMainHandItem();
                hand = InteractionHand.MAIN_HAND;
            } else if (owner.getOffhandItem().getItem() instanceof RitualItem item && item.ritual == this.ritual) {
                ritualItem = owner.getOffhandItem();
                hand = InteractionHand.OFF_HAND;
            } else {
                ritualItem = null;
                hand = null;
            }

            if (ritual instanceof OffensiveRitual ritual1){
                ritual1.onUseEntity(pResult, level, owner, ritualItem, hand);
            }
        }

        this.discard();
    }
    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (getOwner() instanceof LivingEntity owner) {
            ItemStack ritualItem;
            InteractionHand hand;

            if (owner.getMainHandItem().getItem() instanceof RitualItem item && item.ritual == this.ritual) {
                ritualItem = owner.getMainHandItem();
                hand = InteractionHand.MAIN_HAND;
            } else if (owner.getOffhandItem().getItem() instanceof RitualItem item && item.ritual == this.ritual) {
                ritualItem = owner.getOffhandItem();
                hand = InteractionHand.OFF_HAND;
            } else {
                ritualItem = null;
                hand = null;
            }

            if (ritual instanceof UtilityRitual ritual1){
                ritual1.onUseBlock(pResult, level, owner, ritualItem, hand);
            }
        }

        this.discard();
    }
    public int getElement() {
        return this.entityData.get(DATA_ELEMENT_ID);
    }
    public void setElement(int elementIndex) {
        if (elementIndex < 0 || elementIndex >= 5) {
            elementIndex = this.random.nextInt(4);
        }

        this.entityData.set(DATA_ELEMENT_ID, elementIndex);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ELEMENT_ID, 1);
    }
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Element", this.getElement());
    }
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setElement(pCompound.getInt("Element"));
    }
    public ResourceLocation getTextureLocation() {
        return TEXTURE_BY_ELEMENT.getOrDefault(this.getElement(), TEXTURE_BY_ELEMENT.get(0));
    }
    private double getRange(){
        IAbilitiesCap abilities = getOwner().getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        if (abilities == null) return ritual.getRange();

        double length = ritual.getRange();
        if (abilities.hasPower(OPPowers.AMPLIAR_RITUAL) && length > 0d) length += 3.5d;
        return length;
    }
}
