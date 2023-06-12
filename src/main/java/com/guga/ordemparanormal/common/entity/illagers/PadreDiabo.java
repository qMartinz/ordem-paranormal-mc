package com.guga.ordemparanormal.common.entity.illagers;

import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.common.entity.RitualCasterMob;
import com.guga.ordemparanormal.common.entity.RitualProjectile;
import com.guga.ordemparanormal.common.goals.CastRitualGoal;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPRituals;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableWitchTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestHealableRaiderTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class PadreDiabo extends AbstractIllager implements RitualCasterMob {
    private NearestHealableRaiderTargetGoal<Raider> healRaidersGoal;
    private NearestAttackableWitchTargetGoal<Player> attackPlayersGoal;
    public PadreDiabo(EntityType<? extends AbstractIllager> p_32105_, Level p_32106_) {
        super(p_32105_, p_32106_);
    }
    protected void registerGoals() {
        super.registerGoals();
        this.healRaidersGoal = new NearestHealablePadreTargetGoal<>(this, Raider.class, true, (e) ->
                e != null && e.getType() != OPEntities.PADRE_DIABO.get());
        this.attackPlayersGoal = new NearestAttackableWitchTargetGoal<>(this, Player.class, 10, true, false, null);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new CastRitualGoal(this, 1d, 50, 150));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, this.healRaidersGoal);
        this.targetSelector.addGoal(3, this.attackPlayersGoal);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.5D).add(Attributes.FOLLOW_RANGE, 12.0D).add(Attributes.MAX_HEALTH, 24.0D);
    }
    @Override
    public void applyRaidBuffs(int pWave, boolean p_37845_) {
    }
    public void aiStep() {
        if (!this.level.isClientSide && this.isAlive()) {
            this.healRaidersGoal.decrementCooldown();
            this.attackPlayersGoal.setCanAttack(this.healRaidersGoal.getCooldown() <= 0);
        }

        super.aiStep();
    }
    public SoundEvent getCelebrateSound() {
        return SoundEvents.ILLUSIONER_AMBIENT;
    }
    @Override
    public IllagerArmPose getArmPose() {
        return IllagerArmPose.NEUTRAL;
    }
    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        SpawnGroupData spawngroupdata = super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.populateDefaultEquipmentSlots(pDifficulty);
        this.populateDefaultEquipmentEnchantments(random, pDifficulty);
        return spawngroupdata;
    }
    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    protected void populateDefaultEquipmentSlots(DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(OPItems.COMPONENTE_SANGUE.get()));
    }
    @Override
    public void performRitualCast(LivingEntity pTarget, float pDistanceFactor) {
        AbstractRitual ritual = OPRituals.HEMOFAGIA;

        if (this.getTarget().getMobType() == MobType.ILLAGER){
            if (this.getHealth() > 5f) {
                ritual = OPRituals.TRANSFERENCIA_VITAL;

                RitualProjectile projectile = new RitualProjectile(this.level, this, ritual);
                projectile.shootFromRotation(this, this.getXRot(), this.getYHeadRot(), 0.0f, 1.5f, 0.0f);
                this.level.addFreshEntity(projectile);
                projectile.setElement(ritual.getElement().index);
                ritual.ritualSuccess((ServerLevel) this.level, this);
            }
        } else {
            RitualProjectile projectile = new RitualProjectile(this.level, this, ritual);
            projectile.shootFromRotation(this, this.getXRot(), this.getYHeadRot(), 0.0f, 1.5f, 1f);
            this.swing(InteractionHand.OFF_HAND);
            this.level.addFreshEntity(projectile);
            projectile.setElement(ritual.getElement().index);
            ritual.ritualSuccess((ServerLevel) this.level, this);
        }
    }
    static class NearestHealablePadreTargetGoal<T extends LivingEntity> extends NearestHealableRaiderTargetGoal<T> {
        public NearestHealablePadreTargetGoal(Raider p_26087_, Class<T> p_26088_, boolean p_26089_, @Nullable Predicate<LivingEntity> p_26090_) {
            super(p_26087_, p_26088_, p_26089_, p_26090_);
        }
        @Override
        public boolean canUse() {
            if (this.getCooldown() <= 0 && this.mob.getRandom().nextBoolean()) {
                if (this.mob.getHealth() < 6f) {
                    return false;
                } else {
                    this.findTarget();
                    return this.target != null;
                }
            } else {
                return false;
            }
        }
        protected void findTarget() {
            if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
                this.target = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(this.targetType,
                        this.getTargetSearchArea(this.getFollowDistance()), (t) -> t.getHealth() < t.getMaxHealth()),
                        this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            } else {
                this.target = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            }
        }
    }
}
