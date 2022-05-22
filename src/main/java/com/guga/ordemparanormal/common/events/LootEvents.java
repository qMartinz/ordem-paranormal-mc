package com.guga.ordemparanormal.common.events;

import com.guga.ordemparanormal.common.entity.corpos.CorpoEntity;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.Random;

@EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class LootEvents {

	@SubscribeEvent
	public static void addDrops(LivingDropsEvent event) {
		Random rand = event.getEntityLiving().getRandom();

		// Restos mortais do Aldeão
		if (event.getEntity() instanceof CorpoEntity) {
			Entity corpo = event.getEntity();
			int restosAmount = event.getLootingLevel() > 0 ? (rand.nextInt(3) + 1) * event.getLootingLevel()
					: rand.nextInt(2) + 1;

			ItemEntity orgao = new ItemEntity(corpo.level, corpo.getX(), corpo.getY(), corpo.getZ(),
					new ItemStack(OPItems.ORGAO.get(), restosAmount));
			ItemEntity cinzas = new ItemEntity(corpo.level, corpo.getX(), corpo.getY(), corpo.getZ(),
					new ItemStack(OPItems.CINZAS.get(), restosAmount));
			
			if (!(event.getSource() == DamageSource.ON_FIRE)) {
				event.getDrops().add(orgao);
			} else {
				event.getDrops().add(cinzas);
			}
		}
	}
}
