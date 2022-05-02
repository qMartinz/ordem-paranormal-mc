package com.guga.ordemparanormal.common.entity;

import java.util.List;
import java.util.Random;

import com.guga.ordemparanormal.common.entity.corpos.CorpoEntity;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPParticles;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class Nevoa extends Entity {
	private static final EntityDataAccessor<Integer> DATA_RADIUS = SynchedEntityData.defineId(Nevoa.class,
			EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> DATA_INTENSITY = SynchedEntityData.defineId(Nevoa.class,
			EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> DATA_LIFE = SynchedEntityData.defineId(Nevoa.class,
			EntityDataSerializers.INT);

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

	protected void defineSynchedData() {
		this.getEntityData().define(DATA_RADIUS, 7);
		this.getEntityData().define(DATA_INTENSITY, 1);
		this.getEntityData().define(DATA_LIFE, 60);
	}

	// Setters, para setar certos atributos da névoa

	public void setRadius(int size) {
		if (!this.level.isClientSide) {
			if (this.getEntityData().get(DATA_RADIUS) < 45) {
				this.getEntityData().set(DATA_RADIUS, size);
			} else {
				this.getEntityData().set(DATA_RADIUS, 45);
			}
		}
	}

	public void setIntensity(int intensity) {
		if (intensity > 5) {
			this.getEntityData().set(DATA_INTENSITY, 5);
		} else {
			this.getEntityData().set(DATA_INTENSITY, intensity);
		}
	}

	public void setLife(int life) {
		if (life > 60) {
			this.getEntityData().set(DATA_LIFE, 60);
		} else {
			this.getEntityData().set(DATA_LIFE, life);
		}
	}

	// Getters, para pegar certos atributos da névoa

	public double getRadius() {
		return this.getEntityData().get(DATA_RADIUS).doubleValue();
	}

	public int getIntensity() {
		return this.getEntityData().get(DATA_INTENSITY);
	}

	public int getLife() {
		return this.getEntityData().get(DATA_LIFE);
	}

	private void spawnNevoaParticles() {
		Random random = new Random();
		double radius = this.getRadius();
		AABB area = this.getBoundingBox().inflate(radius, radius, radius);

		for (int i = 1; i <= this.getIntensity(); i++) {
			double randomX = (double) random.nextDouble(area.maxX - area.minX) + area.minX;
			double yPos = this.getY();
			double randomZ = (double) random.nextDouble(area.maxZ - area.minZ) + area.minZ;
			BlockPos blockpos = new BlockPos(randomX, yPos, randomZ);
			BlockState blockstate = this.level.getBlockState(blockpos);
			for (yPos = this.getY(); !blockstate.isAir();) {
				yPos++;
			}
			if (blockstate.isAir()) {
				this.level.addParticle(OPParticles.NEVOA_PARTICLE.get(), randomX, yPos, randomZ, 0.0D,
						(double) Math.random() * (0.20D - 0.05D) + 0.05D, 0.0D);
			}
		}
	}

	public void tick() {
		super.tick();
		spawnNevoaParticles();
		if (this.tickCount % 5 == 0) {
			double radius = this.getRadius();
			List<CorpoEntity> list = this.level.getEntitiesOfClass(CorpoEntity.class,
					this.getBoundingBox().inflate(radius, radius, radius), EntitySelector.LIVING_ENTITY_STILL_ALIVE);
			// Se tiver um corpo dentro da nevoa, transforma em um zumbi de sangue
			if (!list.isEmpty()) {
				// Mantém a névoa ali caso tenha um corpo
				int l = this.getLife();
				++l;
				this.setLife(l);
				for (CorpoEntity corpo : list) {
					if (corpo.isAlive()) {
						int i = corpo.getExposure();
						if (i < 150) {
							corpo.setExposure(i + this.getIntensity());
						}
					}
				}
			} else {
				List<Monster> list2 = this.level.getEntitiesOfClass(Monster.class,
						this.getBoundingBox().inflate(radius, radius, radius),
						EntitySelector.LIVING_ENTITY_STILL_ALIVE);
				// Checa se tem alguma criatura que gere medo dentro da névoa, se não, remove a
				// névoa após um tempo
				if (list2.isEmpty()) {
					int l = this.getLife();
					this.setLife(l - 1);
					if (l <= 0) {
						this.kill();
					}
				} else {
					int l = this.getLife();
					this.setLife(l + 1);
				}
			}
		}
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag data) {
		this.setRadius(data.getInt("Radius"));
		this.setIntensity(data.getInt("Intensity"));
		this.setLife(data.getInt("Life"));
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag data) {
		data.putDouble("Radius", this.getRadius());
		data.putInt("Intensity", this.getIntensity());
		data.putInt("Life", this.getLife());
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

}
