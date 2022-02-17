package com.guga.ordemparanormal.common.entity;

import com.guga.ordemparanormal.core.registry.OPEndimations;
import com.teamabnormals.blueprint.core.endimator.Endimatable;
import com.teamabnormals.blueprint.core.endimator.TimedEndimation;
import com.teamabnormals.blueprint.core.endimator.effects.EndimationEffectHandler;
import com.teamabnormals.blueprint.core.util.NetworkUtil;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZumbiSangue extends Monster implements Endimatable {
	@OnlyIn(Dist.CLIENT)
	public EndimationEffectHandler idleEffectHandler;
	public TimedEndimation hurt = new TimedEndimation(5, 0);
	
	public ZumbiSangue(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		if (level.isClientSide) {
			this.idleEffectHandler = new EndimationEffectHandler(this);
		}
		this.hurt.setDecrementing(true);
	}
	
	// AI e comportamento
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}
	
	// Funções para animações
	@Override
	public void tick() {
		super.tick();
		if (this.isNoEndimationPlaying()) {
			NetworkUtil.setPlayingAnimation(this, OPEndimations.ZUMBI_SANGUE_IDLE);
		}
		if (this.level.isClientSide) {
			TimedEndimation hurt = this.hurt;
			hurt.tick();
			if (hurt.isMaxed()) {
				hurt.setDecrementing(true);
			}
		}
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			this.level.broadcastEntityEvent(this, (byte) 8);
			return true;
		}
		return false;
	}
	
	public static AttributeSupplier.Builder createZumbiSangueAttributes() {
	      return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 22.0D).add(Attributes.ARMOR, 3.0D).add(Attributes.ATTACK_DAMAGE, 5.0D).add(Attributes.MOVEMENT_SPEED, 0.24F).add(Attributes.FOLLOW_RANGE, 40.0D);
	   }

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 8) {
			this.hurt.setDecrementing(false);
		} else {
			super.handleEntityEvent(id);
		}
	}
}
