package com.guga.ordemparanormal.common.entity.corpos;

import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSangue;
import com.guga.ordemparanormal.core.registry.OPEntities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;

public abstract class CorpoEntity extends AmbientCreature{
	protected int exposure;

	public CorpoEntity(EntityType<? extends AmbientCreature> type, Level level) {
		super(type, level);
	}
	
	// Pega a "exposição" do corpo
	public int getExposure() {
		return this.exposure;
	}
	
	// Seta a "exposição" do corpo para então transformá-lo em um zumbi quando atingir exposição maior que 149
	public void setExposure(int exp) {
		this.exposure = exp;
		if (exp > 149) {
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
	
	// Transforma o corpo em um zumbi de sangue
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
