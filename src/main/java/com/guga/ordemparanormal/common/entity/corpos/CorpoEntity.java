package com.guga.ordemparanormal.common.entity.corpos;

import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.sounds.SoundEvent;
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
			return OPSounds.CORPSE_HURT.get();
		} else {
			return null;
		}
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return OPSounds.CORPSE_DEATH.get();
	}
	
	// Atributos
		public static AttributeSupplier.Builder createCorpseAttributes() {
			return AmbientCreature.createMobAttributes()
					.add(Attributes.MAX_HEALTH, 3.0D)
					.add(Attributes.KNOCKBACK_RESISTANCE, 40.0D)
					.add(Attributes.MOVEMENT_SPEED, 0.1D);
		}
}
