package com.guga.ordemparanormal.common.entity.zumbissangue;

import com.teamabnormals.blueprint.core.endimator.Endimatable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class ZumbiSeco extends ZumbiSangue implements Endimatable {
	public ZumbiSeco(EntityType<? extends ZumbiSangue> type, Level level) {
		super(type, level);
	}

	// Atributos
	public static AttributeSupplier.Builder createZumbiSecoAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.MAX_HEALTH, 18.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.4F)
				.add(Attributes.ATTACK_DAMAGE, 11.0D)
				.add(Attributes.ARMOR, 3.0D);
	}
}
