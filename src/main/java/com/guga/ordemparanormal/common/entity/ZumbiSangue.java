package com.guga.ordemparanormal.common.entity;

import javax.annotation.Nullable;

import com.guga.ordemparanormal.core.registry.OPEntities;
import com.teamabnormals.blueprint.core.endimator.Endimatable;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.ServerLevelAccessor;

public class ZumbiSangue extends Monster implements Endimatable {
	protected int paranormalExp;

	public ZumbiSangue(EntityType<? extends Monster> type, Level level) {
		super(type, level);
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
	
	// Finalizar Spawn
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34297_, DifficultyInstance p_34298_, MobSpawnType p_34299_, @Nullable SpawnGroupData p_34300_, @Nullable CompoundTag p_34301_) {
		p_34300_ = super.finalizeSpawn(p_34297_, p_34298_, p_34299_, p_34300_, p_34301_);
		return p_34300_;
	}


	// Transformação em Bestial
	public int getParanormalExp() {
		return this.paranormalExp;
	}
	
	public void setParanormalExp(int exp) {
		int i = this.paranormalExp;
		this.paranormalExp = exp;
		if (i > 4) {
			this.growUp();
		}
	}
	
	public void killed(ServerLevel level, LivingEntity entity) {
		super.killed(level, entity);
		int i = this.getParanormalExp();
		i += 1;
		if (i < 0) {
			i = 0;
		}
		this.setParanormalExp(i);
	}
	
	public void addAdditionalSaveData(CompoundTag data) {
	      super.addAdditionalSaveData(data);
	      data.putInt("ParanormalExp", this.getParanormalExp());
	   }
	
	public void readAdditionalSaveData(CompoundTag data) {
	      super.readAdditionalSaveData(data);
	      this.setParanormalExp(data.getInt("ParanormalExp"));
	   }
	
	public Monster growUp() {
		if (this.isAlive()) {
			Bestial bestial = OPEntities.BESTIAL.get().create(this.level);
			bestial.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());

			if (this.hasCustomName()) {
				bestial.setCustomName(this.getCustomName());
				bestial.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isLeashed()) {
				bestial.setLeashedTo(this.getLeashHolder(), true);
				this.dropLeash(true, false);
			}

			if (this.getVehicle() != null) {
				bestial.startRiding(this.getVehicle());
			}

			bestial.setHealth(bestial.getMaxHealth());
			this.level.addFreshEntity(bestial);
			this.discard();

			return bestial;
		}
		return this;
	}

	// Atributos
	public static AttributeSupplier.Builder createZumbiSangueAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 22.0D).add(Attributes.ARMOR, 3.0D)
				.add(Attributes.ATTACK_DAMAGE, 9.0D).add(Attributes.MOVEMENT_SPEED, 0.24F)
				.add(Attributes.FOLLOW_RANGE, 40.0D);
	}
}
