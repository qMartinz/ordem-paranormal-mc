package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.entity.Aberrado;
import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.common.entity.corpos.VillagerCorpo;
import com.guga.ordemparanormal.common.entity.zumbissangue.Bestial;
import com.guga.ordemparanormal.common.entity.zumbissangue.ZumbiSangue;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPEntities {
	private static final EntitySubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getEntitySubHelper();
	
	// Registrar entidades
	public static final RegistryObject<EntityType<ZumbiSangue>> ZUMBI_SANGUE = HELPER.createLivingEntity(
			"zumbi_sangue", ZumbiSangue::new, MobCategory.MONSTER, 0.6F, 1.6F);
	public static final RegistryObject<EntityType<Aberrado>> ABERRADO = HELPER.createLivingEntity(
			"aberrado", Aberrado::new, MobCategory.MONSTER, 1.5F, 3.6F);
	public static final RegistryObject<EntityType<Bestial>> BESTIAL = HELPER.createLivingEntity(
			"zumbi_bestial", Bestial::new, MobCategory.MONSTER, 1.8F, 1.3F);
	public static final RegistryObject<EntityType<VillagerCorpo>> VILLAGER_CORPO = HELPER.createLivingEntity(
			"villager_corpo", VillagerCorpo::new, MobCategory.AMBIENT, 1.2F, 0.2F);
	public static final RegistryObject<EntityType<Nevoa>> NEVOA = HELPER.createEntity(
			"nevoa", Nevoa::new, Nevoa::new, MobCategory.AMBIENT, 1F, 1F);
	
	// Registrar Atributos
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangue.createZumbiSangueAttributes().build());
		event.put(OPEntities.ABERRADO.get(), Aberrado.createAberradoAttributes().build());
		event.put(OPEntities.BESTIAL.get(), Bestial.createBestialAttributes().build());
		event.put(OPEntities.VILLAGER_CORPO.get(), VillagerCorpo.createCorpseAttributes().build());
	}
}
