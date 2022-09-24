package com.guga.ordemparanormal.api;

import com.guga.ordemparanormal.api.capabilities.data.IPowerCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowers;
import com.guga.ordemparanormal.api.capabilities.data.PlayerPowersProvider;
import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import com.guga.ordemparanormal.common.power.Afinidade;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ElementDamage {
    public static final DamageSource DANO_SANGUE = new DamageSource("paranormalBlood").bypassArmor().setMagic();
    public static final DamageSource DANO_MORTE = new DamageSource("paranomalDeath").bypassArmor().setMagic();
    public static final DamageSource DANO_ENERGIA = new DamageSource("paranormalEnergy").bypassArmor().setMagic();
    public static final DamageSource DANO_CONHECIMENTO = new DamageSource("paranormalKnowledge").bypassArmor().setMagic();
    public static final DamageSource DANO_MEDO = new DamageSource("paranormalFear").bypassArmor().setMagic();
    public static DamageSource ritualDamage(LivingEntity caster, ParanormalElement element){
        return new EntityDamageSource(element.getDamage().getMsgId(), caster);
    }

    /**
     * Checa se a entidade especificada é resistente ao dano elemental especificado
     *
     * @param entity a entidade que você deseja checar
     * @param damage tipo de dano, se não for dano elemental esse método sempre retornará falso
     * @return verdadeiro se ela é resistente ao dano, falso se não é
     */
    public static boolean isEntityResistant(LivingEntity entity, DamageSource damage){
        List<EntityType<?>> nonOrganicEntities = List.of(new EntityType<?>[]{EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM, EntityType.SHULKER, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN});
        if (entity instanceof ParanormalCreature creature){
            return damage == creature.getMainElement().getDamage();
        } else if(entity instanceof Player player) {
            IPowerCap cap = player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
            if (cap == null) return false;
            return cap.getPowers().stream().anyMatch(p -> p instanceof Afinidade && p.getElement().getDamage() == damage);
        } else {
            return damage == DANO_SANGUE && nonOrganicEntities.contains(entity.getType());
        }
    }

    /**
     * Checa se a entidade especificada é fraca ao dano elemental especificado
     *
     * @param entity a entidade que você deseja checar
     * @param damage tipo de dano, se não for dano elemental esse método sempre retornará falso
     * @return verdadeiro se ela é fraca ao dano, falso se ela não é
     */
    public static boolean isEntityWeakTo(LivingEntity entity, DamageSource damage){
        List<EntityType<?>> nonOrganicEntities = List.of(new EntityType<?>[]{EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM, EntityType.SHULKER, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN});
        if (entity instanceof ParanormalCreature creature){
            switch (creature.getMainElement()){
                case SANGUE -> {
                    return damage == DANO_MORTE;
                }
                case MORTE -> {
                    return damage == DANO_ENERGIA;
                }
                case ENERGIA -> {
                    return damage == DANO_CONHECIMENTO;
                }
                case CONHECIMENTO -> {
                    return damage == DANO_SANGUE;
                }
                default -> {
                    return false;
                }
            }
        } else if(entity instanceof Player player) {
            IPowerCap cap = player.getCapability(PlayerPowersProvider.PLAYER_POWERS).orElse(null);
            if (cap == null) return false;
            if (cap.getPowers().stream().anyMatch(p -> p instanceof Afinidade)){
                ParanormalElement affElement = cap.getPowers().stream().filter(p -> p instanceof Afinidade).findFirst().get().getElement();
                switch (affElement){
                    case SANGUE -> {
                        return damage == DANO_MORTE;
                    }
                    case MORTE -> {
                        return damage == DANO_ENERGIA;
                    }
                    case ENERGIA -> {
                        return damage == DANO_CONHECIMENTO;
                    }
                    case CONHECIMENTO -> {
                        return damage == DANO_SANGUE;
                    }
                    default -> {
                        return false;
                    }
                }
            }
        } else {
            return damage == DANO_MORTE && !nonOrganicEntities.contains(entity.getType());
        }
        return false;
    }
    public static String elementDmgTranslationKey(ParanormalElement element){
        return element.getTranslationKey() + ".damageType";
    }
    public static Component elementDmgTypeName(ParanormalElement element){
        return new TranslatableComponent(elementDmgTranslationKey(element));
    }
}
