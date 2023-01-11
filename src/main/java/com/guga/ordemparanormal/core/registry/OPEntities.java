package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.entity.AberracaoCarne;
import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.common.entity.ThrownBidente;
import com.guga.ordemparanormal.common.entity.corpos.VillagerCorpo;
import com.guga.ordemparanormal.common.entity.illagers.PadreDiabo;
import com.guga.ordemparanormal.common.entity.illagers.Sadico;
import com.guga.ordemparanormal.common.entity.illagers.Transtornado;
import com.guga.ordemparanormal.common.entity.zumbissangue.*;
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
	public static final EntitySubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getEntitySubHelper();
	
	// Registrar entidades
	public static final RegistryObject<EntityType<ZumbiSangue>> ZUMBI_SANGUE = HELPER.createLivingEntity(
			"zumbi_sangue", ZumbiSangue::new, MobCategory.MONSTER, 0.6F, 1.6F);
	public static final RegistryObject<EntityType<Bestial>> BESTIAL = HELPER.createLivingEntity(
			"zumbi_bestial", Bestial::new, MobCategory.MONSTER, 1.7F, 1.3F);
	public static final RegistryObject<EntityType<ZumbiSeco>> ZUMBI_SECO = HELPER.createLivingEntity(
			"zumbi_seco", ZumbiSeco::new, MobCategory.MONSTER, 0.6F, 2.0F);
	public static final RegistryObject<EntityType<ZumbiEspinhento>> ZUMBI_ESPINHENTO = HELPER.createLivingEntity(
			"zumbi_espinhento", ZumbiEspinhento::new, MobCategory.MONSTER, 0.6F, 1.6F);
	public static final RegistryObject<EntityType<VillagerCorpo>> VILLAGER_CORPO = HELPER.createLivingEntity(
			"villager_corpo", VillagerCorpo::new, MobCategory.AMBIENT, 1.2F, 0.2F);
	public static final RegistryObject<EntityType<Nevoa>> NEVOA = HELPER.createEntity(
			"nevoa", Nevoa::new, Nevoa::new, MobCategory.MISC, 0F, 0F);
	public static final RegistryObject<EntityType<AberracaoCarne>> ABERRACAO_CARNE = HELPER.createLivingEntity(
			"aberracao_carne", AberracaoCarne::new, MobCategory.MONSTER, 2f, 3f);
	public static final RegistryObject<EntityType<TitaSangue>> TITA_SANGUE = HELPER.createLivingEntity(
			"tita_sangue", TitaSangue::new, MobCategory.MONSTER, 2.2f, 3.2f);
	public static final RegistryObject<EntityType<ThrownBidente>> BIDENTE = HELPER.getDeferredRegister().register("bidente",
			() -> EntityType.Builder.<ThrownBidente>of(ThrownBidente::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("bidente"));
	public static final RegistryObject<EntityType<Transtornado>> TRANSTORNADO = HELPER.createLivingEntity(
			"transtornado", Transtornado::new, MobCategory.MONSTER, 0.6F, 1.95F);
	public static final RegistryObject<EntityType<PadreDiabo>> PADRE_DIABO = HELPER.createLivingEntity(
			"padre_diabo", PadreDiabo::new, MobCategory.MONSTER, 0.6F, 1.95F);
	public static final RegistryObject<EntityType<Sadico>> SADICO = HELPER.createLivingEntity(
			"sadico", Sadico::new, MobCategory.MONSTER, 0.6F, 1.95F);
	
	// Registrar Atributos
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(OPEntities.ZUMBI_SANGUE.get(), ZumbiSangue.createZumbiSangueAttributes().build());
		event.put(OPEntities.BESTIAL.get(), Bestial.createBestialAttributes().build());
		event.put(OPEntities.ZUMBI_SECO.get(), ZumbiSeco.createZumbiSecoAttributes().build());
		event.put(OPEntities.ZUMBI_ESPINHENTO.get(), ZumbiEspinhento.createZumbiEspinhentoAttributes().build());
		event.put(OPEntities.VILLAGER_CORPO.get(), VillagerCorpo.createCorpseAttributes().build());
		event.put(OPEntities.ABERRACAO_CARNE.get(), AberracaoCarne.createAberracaoAttributes().build());
		event.put(OPEntities.TITA_SANGUE.get(), TitaSangue.createTitaSangueAttributes().build());
		event.put(OPEntities.TRANSTORNADO.get(), Transtornado.createAttributes().build());
		event.put(OPEntities.SADICO.get(), Sadico.createAttributes().build());
		event.put(OPEntities.PADRE_DIABO.get(), PadreDiabo.createAttributes().build());
	}
}
