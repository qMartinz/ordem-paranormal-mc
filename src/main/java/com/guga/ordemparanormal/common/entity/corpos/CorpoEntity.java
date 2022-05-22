package com.guga.ordemparanormal.common.entity.corpos;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.level.Level;

public abstract class CorpoEntity extends AmbientCreature{
	public CorpoEntity(EntityType<? extends AmbientCreature> type, Level level) {
		super(type, level);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		if (source != DamageSource.IN_WALL) {
			return SoundEvents.DOLPHIN_EAT;
		} else {
			return null;
		}
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		if (this.isOnFire()) {
			return SoundEvents.GENERIC_EXTINGUISH_FIRE;
		} else {
		return SoundEvents.TURTLE_EGG_BREAK;
		}
	}
	
	// Atributos
		public static AttributeSupplier.Builder createCorpseAttributes() {
			return AmbientCreature.createMobAttributes()
					.add(Attributes.MAX_HEALTH, 3.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 40.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.1D);
		}
}
