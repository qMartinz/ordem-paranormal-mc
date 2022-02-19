package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimationManager;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPEndimations {
	// Animações
	public static final PlayableEndimation ZUMBI_SANGUE_IDLE = register("zumbisangue/idle", 3, PlayableEndimation.LoopType.LOOP);
	public static final PlayableEndimation ZUMBI_SANGUE_WALKING = register("zumbisangue/walking", 3, PlayableEndimation.LoopType.LOOP);
	
	// Tipos de Loop
	private static PlayableEndimation register(String name, int duration, PlayableEndimation.LoopType loopType) {
		return PlayableEndimationManager.INSTANCE.registerPlayableEndimation(
				new PlayableEndimation(
						new ResourceLocation(OrdemParanormal.MOD_ID, name),
						duration, loopType));
	}
}
