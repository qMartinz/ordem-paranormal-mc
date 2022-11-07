package com.guga.ordemparanormal.api.paranormaldamage;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.curses.AbstractCurse;
import com.guga.ordemparanormal.common.entity.ParanormalCreature;
import com.guga.ordemparanormal.common.power.Afinidade;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ParanormalDamageSource extends DamageSource {
    public static final ParanormalDamageSource DANO_SANGUE = (new ParanormalDamageSource("paranormalBlood")).setElement(ParanormalElement.SANGUE);
    public static final ParanormalDamageSource DANO_MORTE = (new ParanormalDamageSource("paranomalDeath")).setElement(ParanormalElement.MORTE);
    public static final ParanormalDamageSource DANO_ENERGIA = (new ParanormalDamageSource("paranormalEnergy")).setElement(ParanormalElement.ENERGIA);
    public static final ParanormalDamageSource DANO_CONHECIMENTO = (new ParanormalDamageSource("paranormalKnowledge")).setElement(ParanormalElement.CONHECIMENTO);
    public static final ParanormalDamageSource DANO_MEDO = (new ParanormalDamageSource("paranormalFear")).setElement(ParanormalElement.MEDO);
    public ParanormalElement element;
    public ParanormalDamageSource(String msgId) {
        super(msgId);
        bypassArmor();
    }
    public ParanormalDamageSource setElement(ParanormalElement element) {
        this.element = element;
        return this;
    }
    public static boolean isEntityResistant(LivingEntity entity, ParanormalDamageSource damage){
        // se for uma criatura paranormal
        if (entity instanceof ParanormalCreature creature){
            return creature.getMainElement().getDamage().element == damage.element;

        // se for um jogador
        } else if(entity instanceof Player player) {
            IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
            if (cap == null) return false;
            return cap.getPowers().stream().anyMatch(p -> p instanceof Afinidade && p.getElement().getDamage().element == damage.element);

        // se não for um dos dois
        } else {
            return false;
        }
    }
    public static boolean isEntityWeakTo(LivingEntity entity, ParanormalDamageSource damage){
        // se for uma criatura paranormal
        if (entity instanceof ParanormalCreature creature){
            return creature.getMainElement().getOpressingElement() == damage.element;

        // se for um jogador
        } else if(entity instanceof Player player) {
            IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
            if (cap == null) return false;

            if (cap.getPowers().stream().anyMatch(p -> p instanceof Afinidade)){
                ParanormalElement affElement = cap.getPowers().stream().filter(p -> p instanceof Afinidade).findFirst().get().getElement();

                return affElement.getOpressingElement() == damage.element;
            } else return false;

        // se não for um dos dois
        } else {
            return false;
        }
    }
    public static ParanormalDamageSource paranormalCreatureAttack(ParanormalCreature creature) {
        return new EntityParanormalDamageSource(creature.getMainElement().name + "Creature", creature).setElement(creature.getMainElement());
    }
    public static ParanormalDamageSource ritualAttack(LivingEntity entity, AbstractRitual ritual) {
        return new EntityParanormalDamageSource(ritual.getElement().name + "Ritual", entity).setElement(ritual.getElement());
    }
    public static ParanormalDamageSource mobRitualAttack(LivingEntity entity, ParanormalElement element) {
        return new EntityParanormalDamageSource(element.name + "Ritual", entity).setElement(element);
    }
    public static ParanormalDamageSource curseAttack(LivingEntity entity, AbstractCurse curse) {
        return new EntityParanormalDamageSource(curse.getElement().name + "Curse", entity).setElement(curse.getElement());
    }
    public static ParanormalDamageSource powerAttack(LivingEntity entity, PlayerPower power) {
        return new EntityParanormalDamageSource(power.getId(), entity).setElement(power.getElement());
    }
    public String elementDmgTranslationKey(){
        return element.getTranslationKey() + ".damageType";
    }
}
