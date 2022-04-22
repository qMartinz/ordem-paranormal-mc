package com.guga.ordemparanormal.common.entity;

import com.guga.ordemparanormal.core.registry.OPEntities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class Nevoa extends Entity {
	private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(AreaEffectCloud.class, EntityDataSerializers.FLOAT);

	public Nevoa(EntityType<? extends Nevoa> type, Level level) {
		super(type, level);
	}
	
	public Nevoa(Level level, double x, double y, double z) {
		this(OPEntities.NEVOA.get(), level);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vec3.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}
	
	public Nevoa(PlayMessages.SpawnEntity spawnEntity, Level level) {
		this(OPEntities.NEVOA.get(), level);
	}

	@Override
	protected void defineSynchedData() {
		this.getEntityData().define(DATA_RADIUS, 2.0F);
	}
	
	public void setRadius(float size) {
	      if (!this.level.isClientSide) {
	         this.getEntityData().set(DATA_RADIUS, size);
	      }
	   }
	
	public float getRadius() {
	      return this.getEntityData().get(DATA_RADIUS);
	   }

	@Override
	protected void readAdditionalSaveData(CompoundTag data) {
		this.setRadius(data.getFloat("Radius"));
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag data) {
		data.putFloat("Radius", this.getRadius());
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

}
