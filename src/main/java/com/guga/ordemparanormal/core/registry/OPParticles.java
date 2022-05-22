package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.client.particles.NevoaParticle;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
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

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
		ParticleEngine manager = Minecraft.getInstance().particleEngine;

		manager.register(NEVOA_PARTICLE.get(), NevoaParticle.Provider::new);
	}
}
