package com.guga.ordemparanormal.common.entity.zumbissangue;

import com.guga.ordemparanormal.common.capabilities.nexplayer.NexModel;
import net.minecraft.world.damagesource.DamageSource;
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

public class Bestial extends ZumbiSangue {

	public Bestial(EntityType<? extends ZumbiSangue> type, Level level) {
		super(type, level);
	}
	
	// AI e comportamento
		@Override
		protected void registerGoals() {
		    this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
			this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
			this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
			this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
		}
		
	//Atributos
		public static AttributeSupplier.Builder createBestialAttributes() {
			return ZumbiSangue.createZumbiSangueAttributes()
					.add(Attributes.MAX_HEALTH, 30.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.4F)
					.add(Attributes.ATTACK_DAMAGE, 10.0D)
					.add(Attributes.ARMOR, 5.0D);
		}

	// Exposição paranormal concedido ao jogador
	public void die(DamageSource source){
		super.die(source);
		if (this.getLastHurtByMob() instanceof Player){
			Player player = (Player) this.getLastHurtByMob();
			NexModel.get(player).increaseXP(12D);
		}
	}
}
