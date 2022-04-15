package com.guga.ordemparanormal.core.events;

import com.guga.ordemparanormal.common.entity.Corpses.VillagerCorpo;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEntities;

import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class EntityEvents {
	
	// Checar morte
	@SubscribeEvent
	public static void villagerDeath(LivingDeathEvent event) {
		LevelAccessor level = event.getEntity().level;
		if (event.getEntity() instanceof AbstractVillager) {
			AbstractVillager entity = (AbstractVillager)event.getEntity();
			VillagerCorpo corpo = OPEntities.VILLAGER_CORPO.get().create(entity.level);
			
			corpo.copyPosition(entity);
			level.addFreshEntity(corpo);
		}
	}
}
