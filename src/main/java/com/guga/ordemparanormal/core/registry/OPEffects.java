package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.effects.*;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

public final class OPEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OrdemParanormal.MOD_ID);
    public static final RegistryObject<MobEffect> BLEED = MOB_EFFECTS.register("bleed",
            () -> new BleedEffect(MobEffectCategory.HARMFUL, 0x720000));
    public static final RegistryObject<MobEffect> SKIN_REINFORCED = MOB_EFFECTS.register("skin_reinforced",
            () -> new SkinReinforcedEffect(MobEffectCategory.BENEFICIAL, 0x720000));
    public static final RegistryObject<MobEffect> LIFE_ABSORBED = MOB_EFFECTS.register("life_absorbed",
            () -> new LifeAbsorbedEffect(MobEffectCategory.BENEFICIAL, 0x121212));
    public static final RegistryObject<MobEffect> DECAY = MOB_EFFECTS.register("decay",
            () -> new DecayEffect(MobEffectCategory.HARMFUL, 0x121212));
    public static final RegistryObject<MobEffect> ENHANCED_PHYSIQUE = MOB_EFFECTS.register("enhanced_physique",
            () -> new EnhancedPhysiqueEffect(MobEffectCategory.BENEFICIAL, 0x720000));
    public static final RegistryObject<MobEffect> ENRAGED_FIST = MOB_EFFECTS.register("enraged_fist",
            () -> new EnragedFistEffect(MobEffectCategory.BENEFICIAL, 0x720000));
    public static final RegistryObject<MobEffect> TANGIBLE_FEAR = MOB_EFFECTS.register("tangible_fear",
            () -> new TangibleFearEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF));
    public static void register(IEventBus bus){
        MOB_EFFECTS.register(bus);
    }
}