package com.guga.ordemparanormal.common.entity.corpos;

import com.guga.ordemparanormal.core.registry.OPEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class VillagerCorpo extends CorpoEntity {

	public VillagerCorpo(EntityType<? extends CorpoEntity> type, Level level) {
		super(type, level);
	}
	
	public VillagerCorpo(Level level, double x, double y, double z) {
		this(OPEntities.VILLAGER_CORPO.get(), level);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}
	
	public VillagerCorpo(PlayMessages.SpawnEntity spawnEntity, Level level) {
		this(OPEntities.VILLAGER_CORPO.get(), level);
	}
}
