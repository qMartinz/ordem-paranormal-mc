package com.guga.ordemparanormal.common.entity.zumbissangue;

import com.teamabnormals.blueprint.core.endimator.Endimatable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class ZumbiEspinhento extends ZumbiSangue implements Endimatable {
	public ZumbiEspinhento(EntityType<? extends ZumbiSangue> type, Level level) {
		super(type, level);
	}

	// Atributos
	public static AttributeSupplier.Builder createZumbiEspinhentoAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.MAX_HEALTH, 20.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3F)
				.add(Attributes.ATTACK_DAMAGE, 11.0D)
				.add(Attributes.ARMOR, 4.0D);
	}
}
