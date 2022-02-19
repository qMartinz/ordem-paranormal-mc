package com.guga.ordemparanormal.common.entity;

import com.teamabnormals.blueprint.core.endimator.Endimatable;
import com.teamabnormals.blueprint.core.endimator.TimedEndimation;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Aberrado extends Monster implements Endimatable {
	@OnlyIn(Dist.CLIENT)
	public TimedEndimation die = new TimedEndimation(5, 0);
	
	public Aberrado(EntityType<? extends Monster> type, Level level) {
		super(type, level);
		this.die.setDecrementing(true);
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
	
	// Checar se entidade morreu (vai ser utilizado no futuro)
	public void tick() {
		super.tick();
		if (this.level.isClientSide) {
			TimedEndimation die = this.die;
			die.tick();
			if (die.isMaxed()) {
				die.setDecrementing(true);
			}
		}
	}
	
	// // Checar se entidade morreu (vai ser utilizado no futuro)
	@Override
	public boolean isDeadOrDying() {
		if (super.isDeadOrDying()) {
			this.level.broadcastEntityEvent(this, (byte) 8);
			return true;
		}
		return false;
	}
	
	// Enviar resultados dos cheques
	@Override
	public void handleEntityEvent(byte id) {
		if (id == 8) {
			this.die.setDecrementing(false);
		} else {
			super.handleEntityEvent(id);
		}
	}
	
	// Atributos
	public static AttributeSupplier.Builder createAberradoAttributes() {
	      return Monster.createMonsterAttributes()
	    		  .add(Attributes.MAX_HEALTH, 40.0D)
	    		  .add(Attributes.ARMOR, 3.0D)
	    		  .add(Attributes.ATTACK_DAMAGE, 15.0D)
	    		  .add(Attributes.MOVEMENT_SPEED, 0.22F)
	    		  .add(Attributes.FOLLOW_RANGE, 15.0D);
	   }
}
