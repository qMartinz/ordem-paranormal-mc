package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.client.particles.*;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
			.create(ForgeRegistries.PARTICLE_TYPES, OrdemParanormal.MOD_ID);
	public static final RegistryObject<SimpleParticleType> NEVOA_PARTICLE = PARTICLE_TYPES.register("nevoa_particle",
			() -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> SIGILOS_PARTICLE = PARTICLE_TYPES.register("sigilos_particle",
			() -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> PURGATORIO_PARTICLE = PARTICLE_TYPES.register("purgatorio_particle",
			() -> new SimpleParticleType(true));
	public static final RegistryObject<ParticleType<ColoredParticleOptions>> ABILITIES_PARTICLE =
			PARTICLE_TYPES.register(AbilitiesParticleOptions.NAME, AbilitiesParticleType::new);
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleTypes(RegisterParticleProvidersEvent event) {
		ParticleEngine manager = Minecraft.getInstance().particleEngine;

		manager.register(NEVOA_PARTICLE.get(), NevoaParticle.Provider::new);
		manager.register(SIGILOS_PARTICLE.get(), SigilosParticle.Provider::new);
		manager.register(PURGATORIO_PARTICLE.get(), PurgatorioParticle.Provider::new);
		manager.register(ABILITIES_PARTICLE.get(), AbilitiesParticleOptions::new);
	}
}
