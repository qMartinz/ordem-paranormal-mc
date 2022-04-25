package com.guga.ordemparanormal.common.entity.zumbissangue;

import net.minecraft.world.Difficulty;
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
		
		// Comportamento de invocação
		protected boolean shouldDespawnInPeaceful() {
			return true;
		}

		public void checkDespawn() {
			if (this.level.getDifficulty() == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
				this.discard();
			} else if (!this.isPersistenceRequired() && !this.requiresCustomPersistence()) {
				Entity entity = this.level.getNearestPlayer(this, -1.0D);
				net.minecraftforge.eventbus.api.Event.Result result = net.minecraftforge.event.ForgeEventFactory
						.canEntityDespawn(this);
				if (result == net.minecraftforge.eventbus.api.Event.Result.DENY) {
					noActionTime = 0;
					entity = null;
				} else if (result == net.minecraftforge.eventbus.api.Event.Result.ALLOW) {
					this.discard();
					entity = null;
				}
				if (entity != null) {
					double d0 = entity.distanceToSqr(this);
					int i = this.getType().getCategory().getDespawnDistance();
					int j = i * i;
					if (d0 > (double) j && this.removeWhenFarAway(d0)) {
						this.discard();
					}

					int k = this.getType().getCategory().getNoDespawnDistance();
					int l = k * k;
					if (this.noActionTime > 600 && this.random.nextInt(800) == 0 && d0 > (double) l
							&& this.removeWhenFarAway(d0)) {
						this.discard();
					} else if (d0 < (double) l) {
						this.noActionTime = 0;
					}
				}

			} else {
				this.noActionTime = 0;
			}
		}

		public boolean isPersistenceRequired() {
			return true;
		}

		public boolean removeWhenFarAway(double distance) {
			return true;
		}
		
	//Atributos
		public static AttributeSupplier.Builder createBestialAttributes() {
			return ZumbiSangue.createZumbiSangueAttributes()
					.add(Attributes.MOVEMENT_SPEED, 0.4F)
					.add(Attributes.ATTACK_DAMAGE, 10.0D)
					.add(Attributes.ARMOR, 5.0D);
		}
}
