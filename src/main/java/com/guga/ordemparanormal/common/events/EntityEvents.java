package com.guga.ordemparanormal.common.events;

import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.common.capabilities.expentities.ExpModel;
import com.guga.ordemparanormal.common.capabilities.expentities.ExpProvider;
import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.common.entity.corpos.CorpoEntity;
import com.guga.ordemparanormal.common.entity.corpos.VillagerCorpo;
import com.guga.ordemparanormal.common.entity.zumbissangue.Bestial;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSangue;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.List;

@EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class EntityEvents {
	@SubscribeEvent
	public static void onLivingDeath(LivingDeathEvent event) {
		LevelAccessor level = event.getEntity().level;

		// Checa se um villager morreu e cria um corpo no seu lugar
		if (event.getEntity() instanceof AbstractVillager entity) {
			VillagerCorpo corpo = OPEntities.VILLAGER_CORPO.get().create(entity.level);

			corpo.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
			level.addFreshEntity(corpo);
			List<CorpoEntity> list1 = corpo.level.getEntitiesOfClass(CorpoEntity.class,
					corpo.getBoundingBox().inflate(20D, 20D, 20D), EntitySelector.ENTITY_STILL_ALIVE);
			List<Nevoa> list2 = corpo.level.getEntitiesOfClass(Nevoa.class,
					corpo.getBoundingBox().inflate(20D, 20D, 20D), EntitySelector.ENTITY_STILL_ALIVE);
			// Checa se h� corpos o suficiente para spawnar n�voa no local
			if (list1.size() >= 3 && list2.isEmpty()) {
				Nevoa nevoa = OPEntities.NEVOA.get().create(corpo.level);
				nevoa.copyPosition(corpo);
				nevoa.setIntensity(1);
				nevoa.setRadius(10);
				level.addFreshEntity(nevoa);
				// Checa se j� h� uma n�voa por perto e fortalece ela no lugar de criar uma
				// n�voa nova
			} else if (list1.size() >= 3) {
				for (Nevoa nevoaExistente : list2) {
					int i = nevoaExistente.getIntensity();
					int r = (int) nevoaExistente.getRadius();
					nevoaExistente.setIntensity(i + 1);
					nevoaExistente.setRadius(r + 5);
				}
			}
		}

		if (event.getSource().getEntity() instanceof ZumbiSangue zumbissangue
				&& !(event.getSource().getEntity() instanceof Bestial)) {
			ExpModel.get(zumbissangue).setExposure(ExpModel.get(zumbissangue).getExposure() + 20);
		}
	}
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onLivingHurt(LivingHurtEvent event) {
		// Cancela certos danos para entidade Corpo
		if (event.getEntity() instanceof CorpoEntity) {
			if (event.getSource() == DamageSource.IN_WALL || event.getSource() == DamageSource.DROWN
					|| event.getSource() == DamageSource.FALL) {
				event.setCanceled(true);
			}
		}

		// Aumenta ou diminui o dano para certos danos elementais
		if (event.getEntity() instanceof LivingEntity entity && event.getSource() instanceof ParanormalDamageSource source) {
			if (ParanormalDamageSource.isEntityWeakTo(entity, source)) event.setAmount(event.getAmount() * 2);
			if (ParanormalDamageSource.isEntityResistant(entity, source)) event.setAmount(event.getAmount() / 2);
		}
	}
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onEntityHurt(LivingHurtEvent event){
		event.getEntityLiving().getActiveEffects().forEach(effect -> {
			if (effect.getEffect() instanceof OPEffects.ParanormalEffect opeffect) {
				event.setAmount(opeffect.onHurt(event.getEntityLiving(), event.getSource().getEntity(), event.getAmount(), event.getSource()));
			}
		});

		if (event.getSource().getEntity() instanceof LivingEntity living) {
			living.getActiveEffects().forEach(effect -> {
				if (effect.getEffect() instanceof OPEffects.ParanormalEffect opeffect) {
					event.setAmount(opeffect.onAttack(living, event.getEntityLiving(), event.getAmount(), event.getSource()));
				}
			});
		}
	}
	@SubscribeEvent
	public static void onRegisterCapabilites(RegisterCapabilitiesEvent event) {
		event.register(ExpModel.class);
	}
	@SubscribeEvent
	public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof LivingEntity && !(event.getObject() instanceof Player)) {
			ExpModel expModel = new ExpModel();
			ExpProvider provider = new ExpProvider(expModel);

			event.addCapability(new ResourceLocation(OrdemParanormal.MOD_ID, "cap_exp"), provider);
		}
	}
}