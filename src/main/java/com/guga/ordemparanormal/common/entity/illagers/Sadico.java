package com.guga.ordemparanormal.common.entity.illagers;

import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.common.goals.CastDefensiveRitualGoal;
import com.guga.ordemparanormal.core.registry.OPCurses;
import com.guga.ordemparanormal.core.registry.OPRituals;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class Sadico extends AbstractIllager {
    static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (p_34082_) -> p_34082_ == Difficulty.NORMAL || p_34082_ == Difficulty.HARD;
    public Sadico(EntityType<? extends AbstractIllager> p_32105_, Level p_32106_) {
        super(p_32105_, p_32106_);
    }
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new Sadico.SadicoBreakDoorGoal(this));
        this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
        this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
        this.goalSelector.addGoal(4, new CastDefensiveRitualGoal(this, 1250, OPRituals.APRIMORAMENTO_FISICO));
        this.goalSelector.addGoal(5, new Sadico.SadicoMeleeAttackGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Raider.class)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.FOLLOW_RANGE, 12.0D)
                .add(Attributes.MAX_HEALTH, 24.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }
    @Override
    public void applyRaidBuffs(int pWave, boolean p_37845_) {
    }
    public SoundEvent getCelebrateSound() {
        return SoundEvents.ILLUSIONER_AMBIENT;
    }
    @Override
    public IllagerArmPose getArmPose() {
        return IllagerArmPose.NEUTRAL;
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
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
        this.setItemSlot(EquipmentSlot.MAINHAND, CurseHelper.addCurse(new ItemStack(Items.IRON_AXE), OPCurses.SADICA));
    }
    protected static class SadicoBreakDoorGoal extends BreakDoorGoal {
        public SadicoBreakDoorGoal(Mob p_34112_) {
            super(p_34112_, 6, Sadico.DOOR_BREAKING_PREDICATE);
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            Sadico sadico = (Sadico) this.mob;
            return sadico.hasActiveRaid() && super.canContinueToUse();
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            Sadico sadico = (Sadico) this.mob;
            return sadico.hasActiveRaid() && sadico.random.nextInt(reducedTickDelay(10)) == 0 && super.canUse();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            super.start();
            this.mob.setNoActionTime(0);
        }
    }
    static class SadicoMeleeAttackGoal extends MeleeAttackGoal {
        public SadicoMeleeAttackGoal(Sadico p_34123_) {
            super(p_34123_, 1.0D, false);
        }
        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            if (this.mob.getVehicle() instanceof Ravager) {
                float f = this.mob.getVehicle().getBbWidth() - 0.1F;
                return f * 2.0F * f * 2.0F + pAttackTarget.getBbWidth();
            } else {
                return super.getAttackReachSqr(pAttackTarget);
            }
        }
    }
}
