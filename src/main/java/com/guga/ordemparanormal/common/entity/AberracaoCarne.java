package com.guga.ordemparanormal.common.entity;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.common.goals.AbocanharGoal;
import com.guga.ordemparanormal.core.registry.OPEndimations;
import com.guga.ordemparanormal.core.registry.OPSounds;
import com.teamabnormals.blueprint.core.endimator.Endimatable;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AberracaoCarne extends ParanormalCreature implements Endimatable {
    public AberracaoCarne(EntityType<? extends Monster> type, Level level) {
        super(type, level, 85, ParanormalElement.SANGUE);
    }
    // AI e comportamento
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(3, new AbocanharGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
    }
    //Atributos
    public static AttributeSupplier.Builder createAberracaoAttributes() {
        return ParanormalCreature.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .add(Attributes.MAX_HEALTH, 86.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 18.0D)
                .add(Attributes.ARMOR, 2.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
    }
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return OPSounds.ABERRACAO_IDLE.get();
    }
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return OPSounds.ABERRACAO_HURT.get();
    }
    @Override
    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(OPSounds.ABERRACAO_STEP.get(), 0.3F, 1.0F);
    }
    @Override
    protected SoundEvent getDeathSound() {
        return OPSounds.ABERRACAO_DEATH.get();
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        if (this.isNoEndimationPlaying()) {
            List<PlayableEndimation> animations = new ArrayList<>();
            animations.add(OPEndimations.ABERRACAO_HITLEFTLOW);
            animations.add(OPEndimations.ABERRACAO_HITLEFTUP);
            animations.add(OPEndimations.ABERRACAO_HITRIGHTLOW);
            animations.add(OPEndimations.ABERRACAO_HITRIGHTUP);
            Collections.shuffle(animations);

            NetworkUtil.setPlayingAnimation(this, animations.get(0));
            playSound(OPSounds.ABERRACAO_ATTACK.get(), 0.7f, 1.0f);
            return super.doHurtTarget(pEntity);
        } else return false;
    }

}
