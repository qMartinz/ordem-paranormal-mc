package com.guga.ordemparanormal.common.entity.zumbissangue;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import com.guga.ordemparanormal.core.registry.OPSounds;
import com.teamabnormals.blueprint.core.endimator.Endimatable;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
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
import org.jetbrains.annotations.NotNull;

public class ZumbiSangue extends ParanormalCreature implements Endimatable {
	public ZumbiSangue(EntityType<? extends Monster> type, Level level) {
		super(type, level, 20, ParanormalElement.SANGUE);
	}

	// AI e comportamento
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
	}

	// Atributos
	public static AttributeSupplier.Builder createZumbiSangueAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.MAX_HEALTH, 22.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.35D)
				.add(Attributes.ATTACK_DAMAGE, 9.0D)
				.add(Attributes.ARMOR, 3.0D);
	}
	@Override
	protected SoundEvent getHurtSound(@NotNull DamageSource source) {
		return OPSounds.ZUMBI_SANGUE_HURT.get();
	}
	@Override
	protected SoundEvent getDeathSound() {
		return OPSounds.ZUMBI_SANGUE_DEATH.get();
	}
	@Override
	protected SoundEvent getAmbientSound() {
		return OPSounds.ZUMBI_SANGUE_GROWL.get();
	}
}
