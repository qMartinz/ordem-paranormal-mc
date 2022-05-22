package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimation;
import com.teamabnormals.blueprint.core.endimator.PlayableEndimationManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPEndimations {
    public static final PlayableEndimation ZUMBI_SANGUE_WALKING = register("zumbisangue/walking", 1, PlayableEndimation.LoopType.LOOP);
    public static final PlayableEndimation ZUMBI_SECO_WALKING = register("zumbiseco/walking", 1, PlayableEndimation.LoopType.LOOP);
    public static final PlayableEndimation ZUMBI_ESPINHENTO_WALKING = register("zumbiespinhento/walking", 1, PlayableEndimation.LoopType.LOOP);

    private static PlayableEndimation register(String name, int duration, PlayableEndimation.LoopType loopType){
        return PlayableEndimationManager.INSTANCE.registerPlayableEndimation(new PlayableEndimation(new ResourceLocation(OrdemParanormal.MOD_ID, name), duration, loopType));
    }
}
