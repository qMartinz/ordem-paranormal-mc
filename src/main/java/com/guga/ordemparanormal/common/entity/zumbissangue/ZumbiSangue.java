package com.guga.ordemparanormal.common.entity.zumbissangue;

import com.guga.ordemparanormal.common.capabilities.NexModel;
import com.guga.ordemparanormal.core.registry.OPEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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

public class ZumbiSangue extends Monster {
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
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.MAX_HEALTH, 22.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.35F)
				.add(Attributes.ATTACK_DAMAGE, 9.0D)
				.add(Attributes.ARMOR, 3.0D);
	}

	// Exposição paranormal concedido ao jogador
	public void die(DamageSource source){
		super.die(source);
		if (this.getLastHurtByMob() instanceof Player){
			Player player = (Player) this.getLastHurtByMob();
			NexModel.get(player).increaseXP(5D);
		}
	}
}
