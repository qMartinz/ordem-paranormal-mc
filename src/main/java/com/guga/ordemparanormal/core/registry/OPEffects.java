package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.effects.BleedEffect;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public final class OPEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OrdemParanormal.MOD_ID);

    public static final RegistryObject<MobEffect> BLEED = MOB_EFFECTS.register("bleed",
            () -> new BleedEffect(MobEffectCategory.HARMFUL, 8978432));

    public static void register(IEventBus bus){
        MOB_EFFECTS.register(bus);
    }
}
