package com.guga.ordemparanormal.core.events;

import com.guga.ordemparanormal.common.entity.ZumbiSangue;
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
			ZumbiSangue zumbi = OPEntities.ZUMBI_SANGUE.get().create(entity.level);
			
			zumbi.copyPosition(entity);
			level.addFreshEntity(zumbi);
		}
	}
}
