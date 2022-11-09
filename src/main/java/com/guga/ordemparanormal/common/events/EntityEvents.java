package com.guga.ordemparanormal.common.events;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.common.capabilities.expentities.ExpModel;
import com.guga.ordemparanormal.common.capabilities.expentities.ExpProvider;
import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import com.guga.ordemparanormal.common.entity.corpos.CorpoEntity;
import com.guga.ordemparanormal.common.entity.corpos.VillagerCorpo;
import com.guga.ordemparanormal.common.entity.zumbissangue.Bestial;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiEspinhento;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSangue;
import com.guga.ordemparanormal.common.goals.AreaRitualGoal;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
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
			// Checa se há corpos o suficiente para spawnar névoa no local
			if (list1.size() >= 3 && list2.isEmpty()) {
				Nevoa nevoa = OPEntities.NEVOA.get().create(corpo.level);
				nevoa.copyPosition(corpo);
				nevoa.setIntensity(1);
				nevoa.setRadius(10);
				level.addFreshEntity(nevoa);
				// Checa se já há uma névoa por perto e fortalece ela no lugar de criar uma névoa nova
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
			if (ExpModel.get(zumbissangue).isMaxExp()){
				if (zumbissangue.isAlive()) {
					Bestial entityOut = new Bestial(OPEntities.BESTIAL.get(), zumbissangue.level);

					entityOut.moveTo(zumbissangue.getX(), zumbissangue.getY(), zumbissangue.getZ(), zumbissangue.getYRot(), zumbissangue.getXRot());

					if (zumbissangue.hasCustomName()) {
						entityOut.setCustomName(zumbissangue.getCustomName());
						entityOut.setCustomNameVisible(zumbissangue.isCustomNameVisible());
					}

					if (zumbissangue.isLeashed()) {
						entityOut.setLeashedTo(zumbissangue.getLeashHolder(), true);
						zumbissangue.dropLeash(true, false);
					}

					if (zumbissangue.getVehicle() != null) {
						entityOut.startRiding(zumbissangue.getVehicle());
					}

					entityOut.setHealth(entityOut.getMaxHealth());
					zumbissangue.level.addFreshEntity(entityOut);
					zumbissangue.discard();

					entityOut.playSound(OPSounds.ZUMBI_SANGUE_CONVERT.get(), 1.0F, 1.0F);
				}
			}
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

		float amount = event.getAmount();

		// Aumenta ou diminui o dano para certos danos elementais
		if (event.getEntity() instanceof LivingEntity entity && event.getSource() instanceof ParanormalDamageSource source) {
			if (ParanormalDamageSource.isEntityWeakTo(entity, source)) amount = amount * 2;
			if (ParanormalDamageSource.isEntityResistant(entity, source)) amount = amount / 2;
		}

		if (event.getSource().getEntity() instanceof LivingEntity living) {
			for (MobEffectInstance effect : living.getActiveEffects()){
				if (effect.getEffect() instanceof OPEffects.ParanormalEffect opeffect) {
					amount = opeffect.onAttack(living, event.getEntityLiving(), amount, event.getSource());
				}
			}
		}

		for (MobEffectInstance effect : event.getEntityLiving().getActiveEffects()){
			if (effect.getEffect() instanceof OPEffects.ParanormalEffect opeffect) {
				amount = opeffect.onHurt(event.getEntityLiving(), event.getEntityLiving(), amount, event.getSource());
			}
		}

		event.setAmount(amount);
	}
	@SubscribeEvent
	public static void addTrades(VillagerTradesEvent event){
		// SANGUE
		if (event.getType() == OPProfessions.OCULTISTA_SANGUE.get()){
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(OPItems.ORGAO.get(), 6), new ItemStack(Items.EMERALD, 1),
					16, 2, 0.05f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 13), new ItemStack(OPItems.COMPONENTE_SANGUE.get(), 1),
					12, 8, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 19), new ItemStack(OPItems.RITUAL_ARMA_ATROZ.get(), 1),
					1, 30, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 24), new ItemStack(OPItems.RITUAL_DESCARNAR.get(), 1),
					1, 30, 0.02f
			));

			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 31), new ItemStack(OPItems.RITUAL_APRIMORAMENTO_FISICO.get(), 1),
					1, 40, 0.02f
			));
			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.ROTTEN_FLESH, 22), new ItemStack(Items.EMERALD, 1),
					16, 2, 0.05f
			));
			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 35), new ItemStack(OPItems.RITUAL_HEMOFAGIA.get(), 1),
					1, 45, 0.02f
			));
			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 31), new ItemStack(OPItems.RITUAL_ARMADURA_SANGUE.get(), 1),
					1, 40, 0.02f
			));
			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 35), new ItemStack(OPItems.RITUAL_TRANSFERENCIA_VITAL.get(), 1),
					1, 45, 0.02f
			));
		}

		// MORTE
		if (event.getType() == OPProfessions.OCULTISTA_MORTE.get()){
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(OPItems.CINZAS.get(), 6), new ItemStack(Items.EMERALD, 1),
					16, 2, 0.05f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 13), new ItemStack(OPItems.COMPONENTE_MORTE.get(), 1),
					12, 8, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 19), new ItemStack(OPItems.RITUAL_DECADENCIA.get(), 1),
					1, 30, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 24), new ItemStack(OPItems.RITUAL_CICATRIZACAO.get(), 1),
					1, 30, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 24), new ItemStack(OPItems.RITUAL_ESPIRAIS_DA_PERDICAO.get(), 1),
					1, 30, 0.02f
			));

			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 31), new ItemStack(OPItems.RITUAL_VELOCIDADE_MORTAL.get(), 1),
					1, 45, 0.02f
			));
			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.BONE, 22), new ItemStack(Items.EMERALD, 1),
					16, 2, 0.05f
			));
			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 31), new ItemStack(OPItems.RITUAL_CONSUMIR_MANANCIAL.get(), 1),
					1, 40, 0.02f
			));
		}

		// ENERGIA
		if (event.getType() == OPProfessions.OCULTISTA_ENERGIA.get()){
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.AMETHYST_SHARD, 8), new ItemStack(Items.EMERALD, 1),
					16, 2, 0.05f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 13), new ItemStack(OPItems.COMPONENTE_ENERGIA.get(), 1),
					12, 8, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 19), new ItemStack(OPItems.RITUAL_ARMA_VELOZ.get(), 1),
					1, 30, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 24), new ItemStack(OPItems.RITUAL_LUZ.get(), 1),
					1, 30, 0.02f
			));

			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.RAW_COPPER, 12), new ItemStack(Items.EMERALD, 1),
					12, 4, 0.05f
			));

			trades.get(3).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 41), new ItemStack(OPItems.RITUAL_SALTO_FANTASMA.get(), 1),
					1, 65, 0.02f
			));

			trades.get(4).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 52), new ItemStack(OPItems.RITUAL_TELEPORTE.get(), 1),
					1, 75, 0.02f
			));
		}

		// CONHECIMENTO
		if (event.getType() == OPProfessions.OCULTISTA_CONHECIMENTO.get()){
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.GLOWSTONE, 8), new ItemStack(Items.EMERALD, 1),
					16, 2, 0.05f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 13), new ItemStack(OPItems.COMPONENTE_CONHECIMENTO.get(), 1),
					12, 8, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 19), new ItemStack(OPItems.RITUAL_AMALDICOAR_ARMA.get(), 1),
					1, 30, 0.02f
			));
			trades.get(1).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 24), new ItemStack(OPItems.RITUAL_PERTUBACAO.get(), 1),
					1, 30, 0.02f
			));

			trades.get(2).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.RAW_GOLD, 12), new ItemStack(Items.EMERALD, 1),
					12, 4, 0.05f
			));

			trades.get(5).add((trader, rand) -> new MerchantOffer(
					new ItemStack(Items.EMERALD, 61), new ItemStack(OPItems.RITUAL_INEXISTIR.get(), 1),
					1, 80, 0.02f
			));
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
	@SubscribeEvent
	public static void onEntityRegisterGoals(EntityJoinWorldEvent event){
		if (event.getEntity() instanceof Villager villager){
			villager.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(villager, ParanormalCreature.class, true));
			villager.targetSelector.addGoal(2, new HurtByTargetGoal(villager));
			villager.goalSelector.addGoal(3, new AreaRitualGoal(villager, 1d, false));
			villager.goalSelector.addGoal(4, new AvoidEntityGoal<ParanormalCreature>(villager, ParanormalCreature.class, 10f, 1d, 1d));
		}
	}
}