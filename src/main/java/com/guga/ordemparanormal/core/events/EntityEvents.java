package com.guga.ordemparanormal.core.events;

import java.util.List;

import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.common.entity.corpos.CorpoEntity;
import com.guga.ordemparanormal.common.entity.corpos.VillagerCorpo;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEntities;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class EntityEvents {

	// Checar morte de aldeão
	@SubscribeEvent
	public static void villagerDeath(LivingDeathEvent event) {
		LevelAccessor level = event.getEntity().level;
		// Checa se um villager morreu e cria um corpo no seu lugar
		if (event.getEntity() instanceof AbstractVillager) {
			AbstractVillager entity = (AbstractVillager) event.getEntity();
			VillagerCorpo corpo = OPEntities.VILLAGER_CORPO.get().create(entity.level);

			corpo.copyPosition(entity);
			level.addFreshEntity(corpo);
			corpo.setYBodyRot(entity.getYHeadRot());
			List<CorpoEntity> list1 = corpo.level.getEntitiesOfClass(CorpoEntity.class,
					corpo.getBoundingBox().inflate(20D, 20D, 20D), EntitySelector.ENTITY_STILL_ALIVE);
			List<Nevoa> list2 = corpo.level.getEntitiesOfClass(Nevoa.class,
					corpo.getBoundingBox().inflate(20D, 20D, 20D), EntitySelector.ENTITY_STILL_ALIVE);
			// Checa se há corpos o suficiente para spawnar névoa no local
			if (list1.size() >= 3 && list2.isEmpty()) {
				Nevoa nevoa = OPEntities.NEVOA.get().create(corpo.level);
				nevoa.copyPosition(corpo);
				level.addFreshEntity(nevoa);
				nevoa.setIntensity(1);
				nevoa.setRadius(10);
			// Checa se já há uma névoa por perto e fortalece ela no lugar de criar uma névoa nova
			} else if (list1.size() >= 3 && !list2.isEmpty()) {
				for (Nevoa nevoaExistente : list2) {
					int i = nevoaExistente.getIntensity();
					int r = (int) nevoaExistente.getRadius();
					nevoaExistente.setIntensity(i + 1);
					nevoaExistente.setRadius(r + 5);
				}
			}
		}
	}
}
