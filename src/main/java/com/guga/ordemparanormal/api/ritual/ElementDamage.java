package com.guga.ordemparanormal.api.ritual;

import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ElementDamage {
    public static final DamageSource BLOOD_DAMAGE = new DamageSource("paranormalBlood").bypassArmor().setMagic();
    public static final DamageSource DEATH_DAMAGE = new DamageSource("paranomalDeath").bypassArmor().setMagic();
    public static final DamageSource ENERGY_DAMAGE = new DamageSource("paranormalEnergy").bypassArmor().setMagic();
    public static final DamageSource KNOWLEDGE_DAMAGE = new DamageSource("paranormalKnowledge").bypassArmor().setMagic();
    public static final DamageSource FEAR_DAMAGE = new DamageSource("paranormalFear").bypassArmor().setMagic();
    public static DamageSource bloodRitual(LivingEntity caster){
        return new EntityDamageSource("paranormalBlood", caster);
    }
    public static DamageSource deathRitual(LivingEntity caster){
        return new EntityDamageSource("paranomalDeath", caster);
    }
    public static DamageSource energyRitual(LivingEntity caster){
        return new EntityDamageSource("paranormalEnergy", caster);
    }
    public static DamageSource knowledgeRitual(LivingEntity caster){
        return new EntityDamageSource("paranormalKnowledge", caster);
    }
    public static DamageSource fearRitual(LivingEntity caster){
        return new EntityDamageSource("paranormalFear", caster);
    }
    public static boolean isEntityResistant(LivingEntity entity, DamageSource damage){
        List<EntityType<?>> nonOrganicEntities = List.of(new EntityType<?>[]{EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM, EntityType.SHULKER, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN});
        if (entity instanceof ParanormalCreature creature){
            switch (creature.getMainElement()){
                case BLOOD -> {
                    return damage == BLOOD_DAMAGE;
                }
                case DEATH -> {
                    return damage == DEATH_DAMAGE;
                }
                case ENERGY -> {
                    return damage == ENERGY_DAMAGE;
                }
                case KNOWLEDGE -> {
                    return damage == KNOWLEDGE_DAMAGE;
                }
                default -> {
                    return false;
                }
            }
        } else {
            return damage == BLOOD_DAMAGE && nonOrganicEntities.contains(entity.getType());
        }
    }
    public static boolean isEntityWeakTo(LivingEntity entity, DamageSource damage){
        List<EntityType<?>> nonOrganicEntities = List.of(new EntityType<?>[]{EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM, EntityType.SHULKER, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN});
        if (entity instanceof ParanormalCreature creature){
            switch (creature.getMainElement()){
                case BLOOD -> {
                    return damage == DEATH_DAMAGE;
                }
                case DEATH -> {
                    return damage == ENERGY_DAMAGE;
                }
                case ENERGY -> {
                    return damage == KNOWLEDGE_DAMAGE;
                }
                case KNOWLEDGE -> {
                    return damage == BLOOD_DAMAGE;
                }
                default -> {
                    return false;
                }
            }
        } else {
            return damage == DEATH_DAMAGE && !nonOrganicEntities.contains(entity.getType());
        }
    }
}
