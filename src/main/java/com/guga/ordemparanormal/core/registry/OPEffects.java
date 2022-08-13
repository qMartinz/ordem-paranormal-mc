package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.effects.BleedEffect;
import com.guga.ordemparanormal.common.effects.BloodArmorEffect;
import com.guga.ordemparanormal.common.effects.DecayEffect;
import com.guga.ordemparanormal.common.effects.LifeAbsorptionEffect;
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
            () -> new BleedEffect(MobEffectCategory.HARMFUL, 0x890000));
    public static final RegistryObject<MobEffect> DECAY = MOB_EFFECTS.register("decay",
            () -> new DecayEffect(MobEffectCategory.HARMFUL, 0x110E0E));
    public static final RegistryObject<MobEffect> BLOOD_ARMOR_EFFECT = MOB_EFFECTS.register("blood_armor_effect",
            () -> new BloodArmorEffect(MobEffectCategory.BENEFICIAL, 0x34080B)
                    .addAttributeModifier(Attributes.ARMOR_TOUGHNESS, UUID.randomUUID().toString(), 0.0D, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> LIFE_ABSORPTION_EFFECT = MOB_EFFECTS.register("life_absorption",
            () -> new LifeAbsorptionEffect(MobEffectCategory.BENEFICIAL, 0x241819));
    public static void register(IEventBus bus){
        MOB_EFFECTS.register(bus);
    }
}