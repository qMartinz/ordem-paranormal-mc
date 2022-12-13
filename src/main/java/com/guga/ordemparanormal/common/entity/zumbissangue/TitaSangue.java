package com.guga.ordemparanormal.common.entity.zumbissangue;

import com.guga.ordemparanormal.common.goals.BiteGoal;
import com.guga.ordemparanormal.core.registry.OPEndimations;
import com.guga.ordemparanormal.core.registry.OPSounds;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TitaSangue extends Bestial{
    public TitaSangue(EntityType<? extends Bestial> type, Level level) {
        super(type, level);
    }
    // AI e comportamento
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new BiteGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
    }
    // Atributos
    public static AttributeSupplier.Builder createTitaSangueAttributes() {
        return ZumbiSangue.createZumbiSangueAttributes()
                .add(Attributes.MAX_HEALTH, 94.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 15.0D)
                .add(Attributes.ARMOR, 3.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
    }
    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource source) {
        return OPSounds.TITA_HURT.get();
    }
    @Override
    protected SoundEvent getDeathSound() {
        return OPSounds.TITA_DEATH.get();
    }
    @Override
    protected SoundEvent getAmbientSound() {
        return OPSounds.TITA_IDLE.get();
    }
    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(OPSounds.TITA_STEP.get(), 0.3F, 1.0F);
    }
    @Override
    public boolean doHurtTarget(Entity pEntity) {
        if (this.isNoEndimationPlaying()) {
            NetworkUtil.setPlayingAnimation(this, OPEndimations.TITA_ATTACK);
            playSound(OPSounds.TITA_ATTACK.get(), 0.7f, 1.0f);
            return super.doHurtTarget(pEntity);
        } else return false;
    }
}
