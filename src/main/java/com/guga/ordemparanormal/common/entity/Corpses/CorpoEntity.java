package com.guga.ordemparanormal.common.entity.Corpses;

import java.util.List;

import javax.annotation.Nullable;

import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.common.entity.ZumbiSangue;
import com.guga.ordemparanormal.core.registry.OPEntities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;

public abstract class CorpoEntity extends AmbientCreature{
	protected int exposure;
	@Nullable
	public Nevoa nearestNevoa;

	public CorpoEntity(EntityType<? extends AmbientCreature> type, Level level) {
		super(type, level);
	}
	
	// Transformar em criatura
	public int getExposure() {
		return this.exposure;
	}
	
	public void setExposure(int exp) {
		this.exposure = exp;
		if (exp > 799) {
			this.transform();
		}
	}
	
	public void addAdditionalSaveData(CompoundTag data) {
		super.addAdditionalSaveData(data);
		data.putInt("Exposure", this.getExposure());
	}
	
	public void readAdditionalSaveData(CompoundTag data) {
	      super.readAdditionalSaveData(data);
	      this.setExposure(data.getInt("Exposure"));
	   }
	
	private boolean checkNevoa() {
		List<Nevoa> list = this.level.getEntitiesOfClass(Nevoa.class, this.getBoundingBox().inflate(5D, 5D, 5D), EntitySelector.ENTITY_STILL_ALIVE);
		if (list.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public void aiStep() {
		super.aiStep();
		if (this.isAlive()) {
			if (this.checkNevoa()) {
				int i = this.getExposure();
				if (i < 800) {
					++i;
					this.setExposure(i);
				} else if (i > 800) {
					--i;
					this.setExposure(i);
				}
			}
		}
	}
	
	public LivingEntity transform() {
		if (this.isAlive()) {
			ZumbiSangue zumbi = OPEntities.ZUMBI_SANGUE.get().create(this.level);
			zumbi.moveTo(this.getX(), this.getY(), this.getZ());
			
			zumbi.setHealth(zumbi.getMaxHealth());
			this.level.addFreshEntity(zumbi);
			this.discard();
			
			return zumbi;
		}
		return this;
	}
	
	// Atributos
		public static AttributeSupplier.Builder createCorpseAttributes() {
			return AmbientCreature.createMobAttributes()
					.add(Attributes.MAX_HEALTH, 3.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 40.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.1D);
		}
}
