package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.effects.*;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
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
    public static final RegistryObject<MobEffect> ENRAGED_FIST = MOB_EFFECTS.register("enraged_fist",
            () -> new EnragedFistEffect(MobEffectCategory.BENEFICIAL, 0x720000));
    public static final RegistryObject<MobEffect> TANGIBLE_FEAR = MOB_EFFECTS.register("tangible_fear",
            () -> new TangibleFearEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF));
    public static final RegistryObject<MobEffect> SWERVE_DEATH = MOB_EFFECTS.register("swerve_death",
            () -> new SwerveDeathEffect(MobEffectCategory.BENEFICIAL, 0x121212));
    public static final RegistryObject<MobEffect> TEMPORAL_SURGE = MOB_EFFECTS.register("temporal_surge",
            () -> new ParanormalEffect(MobEffectCategory.BENEFICIAL, 0x121212)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, UUID.randomUUID().toString(), 1d, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> DISTORTED_GRAVITY = MOB_EFFECTS.register("distorted_gravity",
            () -> new DistortedGravityEffect(MobEffectCategory.BENEFICIAL, 0x46ebac));
    public static final RegistryObject<MobEffect> ZERO_ENTROPY = MOB_EFFECTS.register("zero_entropy",
            () -> new ZeroEntropyEffect(MobEffectCategory.HARMFUL, 0x121212)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID.randomUUID().toString(), -50, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> DISTURBED = MOB_EFFECTS.register("disturbed",
            () -> new DisturbedEffect(MobEffectCategory.HARMFUL, 0xF8D321)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, UUID.randomUUID().toString(), -50, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> WEAKENED_MIND = MOB_EFFECTS.register("weakened_mind",
            () -> new ParanormalEffect(MobEffectCategory.HARMFUL, 0xF8D321));
    public static final RegistryObject<MobEffect> CHARISMATIC = MOB_EFFECTS.register("charismatic",
            () -> new ParanormalEffect(MobEffectCategory.BENEFICIAL, 0xF8D321));
    public static void register(IEventBus bus){
        MOB_EFFECTS.register(bus);
    }
    public static class ParanormalEffect extends MobEffect {
        public ParanormalEffect(MobEffectCategory category, int color) {
            super(category, color);
        }
        public float onHurt(LivingEntity livingEntity, @Nullable Entity attacker, float amount, DamageSource source){
            return amount;
        }
        public float onAttack(LivingEntity livingEntity, LivingEntity target, float amount, DamageSource source){
            return amount;
        }
        @Override
        public List<ItemStack> getCurativeItems() {
            return new ArrayList<>();
        }
    }
}