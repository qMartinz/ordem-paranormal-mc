package com.guga.ordemparanormal.api.powers;

import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class ElementDamage {
    public static final DamageSource BLOOD_DAMAGE = new DamageSource("paranormalBlood").bypassArmor().setMagic();
    public static final DamageSource DEATH_DAMAGE = new DamageSource("paranomalDeath").bypassArmor().setMagic();
    public static final DamageSource ENERGY_DAMAGE = new DamageSource("paranormalEnergy").bypassArmor().setMagic();
    public static final DamageSource KNOWLEDGE_DAMAGE = new DamageSource("paranormalKnowledge").bypassArmor().setMagic();
    public static final DamageSource FEAR_DAMAGE = new DamageSource("paranormalFear").bypassArmor().setMagic();
    public static DamageSource ritualDamage(LivingEntity caster, ParanormalElement element){
        return new EntityDamageSource(element.getEquivalentDamage().getMsgId(), caster);
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
