package com.guga.ordemparanormal.common.entity;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.util.EntityUtil;
import com.guga.ordemparanormal.common.capabilities.expentities.ExpModel;
import com.guga.ordemparanormal.common.entity.corpos.CorpoEntity;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiEspinhento;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSangue;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSeco;
import com.guga.ordemparanormal.core.registry.OPEntities;
import com.guga.ordemparanormal.core.registry.OPParticles;
import com.guga.ordemparanormal.core.registry.OPSounds;
import com.guga.ordemparanormal.core.registry.OPTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Nevoa extends Entity {
	private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(Nevoa.class,
			EntityDataSerializers.FLOAT);
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
		this.getEntityData().define(DATA_RADIUS, 7f);
		this.getEntityData().define(DATA_INTENSITY, 1);
		this.getEntityData().define(DATA_LIFE, 80);
	}

	// Setters, para setar certos atributos da névoa
	public void setRadius(float size) {
		this.getEntityData().set(DATA_RADIUS, Mth.clamp(size, 1f, 45f));
	}
	public void setIntensity(int intensity) {
		this.getEntityData().set(DATA_INTENSITY, Mth.clamp(intensity, 1, 5));
	}

	public void setLife(int life) {
		this.getEntityData().set(DATA_LIFE, Math.min(life, 80));
	}

	// Getters, para pegar certos atributos da névoa
	public float getRadius() {
		return this.getEntityData().get(DATA_RADIUS);
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
		AABB area = this.getBoundingBox().inflate(radius);
		for (int i = 1; i <= Math.pow(this.getIntensity(), 3) + radius - (5 * this.getIntensity()); i++) {
			double randX = Mth.nextDouble(random, area.minX, area.maxX);
			double randZ = Mth.nextDouble(random, area.minZ, area.maxZ);
			double randY = Mth.nextDouble(random, area.minY, area.maxY);
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
					ExpModel expModel = ExpModel.get(corpo);

					if (corpo.isAlive() && !expModel.isMaxExp()) {
						float exp = expModel.getExposure();
						expModel.setExposure(exp + (float) this.getIntensity() / 2);
					} else if (corpo.isAlive() && expModel.isMaxExp()) {
						List<Mob> zumbis = new ArrayList<>();
						zumbis.add(new ZumbiSangue(OPEntities.ZUMBI_SANGUE.get(), corpo.level));
						zumbis.add(new ZumbiSeco(OPEntities.ZUMBI_SECO.get(), corpo.level));
						zumbis.add(new ZumbiEspinhento(OPEntities.ZUMBI_ESPINHENTO.get(), corpo.level));
						Collections.shuffle(zumbis);

						EntityUtil.transformMob(corpo, zumbis.get(0));
						corpo.playSound(OPSounds.CORPSE_CONVERT.get(), 1.0F, 1.0F);
					}
				}

				for (Monster monstro : monstros) {
					ExpModel expModel = ExpModel.get(monstro);

					if (monstro.isAlive() && monstro instanceof Zombie) {
						float exp = expModel.getExposure();
						expModel.setExposure(exp + (float) this.getIntensity() / 2);
						if (expModel.isMaxExp()) {
							EntityUtil.transformMob(monstro, new ZumbiSangue(OPEntities.ZUMBI_SANGUE.get(), monstro.level));
							monstro.playSound(OPSounds.ZOMBIE_CONVERT.get(), 1.0F, 1.0F);
						}
					}

					if (monstro.isAlive() && monstro instanceof Skeleton){
						float exp = expModel.getExposure();
						expModel.setExposure(exp + (float) this.getIntensity() / 2);
						if (expModel.isMaxExp()){
							EntityUtil.transformMob(monstro, new ZumbiSeco(OPEntities.ZUMBI_SECO.get(), monstro.level));
							monstro.playSound(OPSounds.SKELETON_CONVERT.get(), 1.0F, 1.0F);
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

			for (Player player : players) {
				player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(playerNex -> {
					if (playerNex.getNex() == 0) playerNex.addNexXp(10);
				});
				if (player instanceof ServerPlayer serverPlayer) OPTriggers.ENTER_FOG.trigger(serverPlayer);
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
	public @NotNull Packet<?> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

}
