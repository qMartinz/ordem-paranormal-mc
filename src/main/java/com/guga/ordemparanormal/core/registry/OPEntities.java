package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.entity.ZumbiSangue;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPEntities {
	private static final EntitySubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getEntitySubHelper();
	
	// Registrar entidades
	public static final RegistryObject<EntityType<ZumbiSangue>> ZUMBI_SANGUE = HELPER.createLivingEntity(
			"zumbi_sangue", ZumbiSangue::new, MobCategory.MONSTER, 1.0F, 1.0F);
	
	// Entidades
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(OPEntities.ZUMBI_SANGUE.get(), Monster.createMonsterAttributes().build());
	}
}
