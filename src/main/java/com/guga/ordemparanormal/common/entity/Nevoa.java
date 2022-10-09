package com.guga.ordemparanormal.common.entity;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.common.capabilities.expentities.ExpModel;
import com.guga.ordemparanormal.common.entity.corpos.CorpoEntity;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiEspinhento;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSangue;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSeco;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		this.getEntityData().define(DATA_LIFE, 80);
	}

	// Setters, para setar certos atributos da n�voa
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
		this.getEntityData().set(DATA_INTENSITY, Math.min(intensity, 5));
	}

	public void setLife(int life) {
		this.getEntityData().set(DATA_LIFE, Math.min(life, 80));
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
		for (int i = 1; i <= Math.pow(this.getIntensity(), 3) + radius - (5 * this.getIntensity()); i++) {
			double randX = random.nextDouble(area.maxX - area.minX) + area.minX;
			double randZ = random.nextDouble(area.maxZ - area.minZ) + area.minZ;
			double randY = random.nextDouble(area.maxY - area.minY) + area.minY;
			Vec3 randomPos = new Vec3(randX, randY, randZ);
			BlockPos block = new BlockPos(randomPos);
			if (!this.level.getBlockState(block.below()).isAir() && this.level.getBlockState(block).isAir()) {
				this.level.addParticle(OPParticles.NEVOA_PARTICLE.get(), randX, randY, randZ, 0D, 0D, 0D);
			}
		}
	}

	public void tick() {
		super.tick();
		this.spawnNevoaParticles();

		if (this.tickCount % 5 == 0){
			double radius = this.getRadius();
			List<CorpoEntity> corpos = this.level.getEntitiesOfClass(CorpoEntity.class,
					this.getBoundingBox().inflate(radius), EntitySelector.LIVING_ENTITY_STILL_ALIVE);
			List<Monster> monstros = this.level.getEntitiesOfClass(Monster.class,
					this.getBoundingBox().inflate(radius), EntitySelector.LIVING_ENTITY_STILL_ALIVE);

			// Manter a névoa se houver corpos ou monstros dentro e aumentar exposição dos tais.
			int l = this.getLife();
			if (!corpos.isEmpty() || !monstros.isEmpty()) {
				++l;
				this.setLife(l);

				for (CorpoEntity corpo : corpos) {
					if (corpo.isAlive() && !ExpModel.get(corpo).isMaxExp()) {
						float i = ExpModel.get(corpo).getExposure();
						ExpModel.get(corpo).setExposure(i + (float) this.getIntensity() / 2);
					} else if (corpo.isAlive() && ExpModel.get(corpo).isMaxExp()) {
						List<Mob> zumbis = new ArrayList<>();
						zumbis.add(new ZumbiSangue(OPEntities.ZUMBI_SANGUE.get(), corpo.level));
						zumbis.add(new ZumbiSeco(OPEntities.ZUMBI_SECO.get(), corpo.level));
						zumbis.add(new ZumbiEspinhento(OPEntities.ZUMBI_ESPINHENTO.get(), corpo.level));

						transform(corpo, zumbis.get(random.nextInt(zumbis.size() - 1)));
						corpo.playSound(SoundEvents.TURTLE_EGG_CRACK, 0.4F, 1.0F);
						corpo.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 0.6F, 1.0F);
					}
				}

				for (Monster monstro : monstros) {
					ExpModel expModel = ExpModel.get(monstro);

					if (monstro instanceof Zombie) {
						float exp = expModel.getExposure();
						expModel.setExposure(exp + (float) this.getIntensity() / 2);
						if (expModel.isMaxExp()) {
							transform(monstro, new ZumbiSangue(OPEntities.ZUMBI_SANGUE.get(), monstro.level));
							monstro.playSound(SoundEvents.ZOMBIE_DEATH, 0.2F, 0.7F);
							monstro.playSound(SoundEvents.TURTLE_EGG_CRACK, 0.4F, 1.0F);
							monstro.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 0.6F, 1.0F);
						}
					}

					if (monstro instanceof Skeleton){
						float exp = expModel.getExposure();
						expModel.setExposure(exp + (float) this.getIntensity() / 2);
						if (expModel.isMaxExp()){
							transform(monstro, new ZumbiSeco(OPEntities.ZUMBI_SECO.get(), monstro.level));
							monstro.playSound(SoundEvents.SKELETON_DEATH, 0.2F, 0.7F);
							monstro.playSound(SoundEvents.TURTLE_EGG_CRACK, 0.4F, 1.0F);
							monstro.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 0.6F, 1.0F);
						}
					}
				}
			} else {
				// Remove a névoa por não haver nada que traga medo suficiente para manté-la lá.
				--l;
				this.setLife(l);
			}
			if (this.getLife() < 1){
				this.discard();
			}

			// Adiciona 10XP para players com 0% dentro da névoa
			List<Player> players = this.level.getEntitiesOfClass(Player.class,
					this.getBoundingBox().inflate(radius), EntitySelector.LIVING_ENTITY_STILL_ALIVE);
			if (!players.isEmpty()) {
				for (Player player : players) {
					player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
						if (playerNex.getNex() == 0) playerNex.addNexXp(10);
					});
				}
			}
		}
	}

	public LivingEntity transform(Mob entityIn, Mob entityOut) {
		if (entityIn.isAlive()) {
			entityOut.moveTo(entityIn.getX(), entityIn.getY(), entityIn.getZ(), entityIn.getYRot(), entityIn.getXRot());

			if (entityIn.hasCustomName()) {
				entityOut.setCustomName(entityIn.getCustomName());
				entityOut.setCustomNameVisible(entityIn.isCustomNameVisible());
			}

			if (entityIn.isLeashed()) {
				entityOut.setLeashedTo(entityIn.getLeashHolder(), true);
				entityIn.dropLeash(true, false);
			}

			if (entityIn.getVehicle() != null) {
				entityOut.startRiding(entityIn.getVehicle());
			}

			entityOut.setHealth(entityOut.getMaxHealth());
			entityIn.level.addFreshEntity(entityOut);
			entityIn.discard();
			return entityOut;
		}
		return entityIn;
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
	public @NotNull Packet<?> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

}
