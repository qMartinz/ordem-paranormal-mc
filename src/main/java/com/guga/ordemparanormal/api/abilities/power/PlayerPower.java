package com.guga.ordemparanormal.api.abilities.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.*;

public class PlayerPower {
    public static final PlayerPower EMPTY = new PlayerPower("", false, ParanormalElement.NONE, 0, 0, new int[]{0, 0, 0});
    private final String id;
    private final boolean isActivePower;
    private final ParanormalElement element;
    private final int effortCost;
    private final Set<PlayerPower> powerRequirements;
    private final int nexRequired;
    private final int[] attributesRequired;
    public PlayerPower(String id, boolean isActivePower, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements){
        this.id = id;
        this.isActivePower = isActivePower;
        this.element = element;
        this.effortCost = effortCost;
        this.powerRequirements = new HashSet<>(List.of(powerRequirements));
        this.nexRequired = nexRequired;
        this.attributesRequired = attributesRequired;
    }
    public String getId() {
        return id;
    }
    public boolean isActivePower() {
        return isActivePower;
    }
    public ParanormalElement getElement() {
        return element;
    }
    public int getEffortCost(){
        return this.effortCost;
    }
    public Collection<PlayerPower> getPowerRequirements() {
        return powerRequirements;
    }
    public int getNexRequired() {
        return nexRequired;
    }
    public int[] getAttributesRequired() {
        return attributesRequired;
    }
    public String getTranslationKey(){
        return "ordemparanormal.ability." + getId();
    }
    public Component getDisplayName(){ return new TranslatableComponent(getTranslationKey()); }
    public FormattedText getDescription(){
        return new TranslatableComponent(this.getTranslationKey() + ".description").withStyle(ChatFormatting.WHITE);
    }
    /**
     * Utilizado para checar se o jogador pode colocar este poder em um slot de poder ativo.
     *
     * @param player o jogador que equipou o poder
     */
    public boolean canEquip(Player player){
        return true;
    }
    /**
     * Utilizado para checar se o jogador pode utilizar o poder
     *
     * @param player o jogador que utilizou o poder
     */
    public boolean canUse(Player player){
        return true;
    }
    /**
     * Chamado quando o poder é adicionado ao jogador por um {@link PowerButton}
     *
     * @param player o jogador que adquiriu o poder
     */
    public void onAdded(Player player){}
    public void use(Player player) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            if (cap.getCurrentEffort() >= this.getEffortCost() && canUse(player) && cap.getPowerCooldown() == 0) {
                cap.setCurrentEffort(cap.getCurrentEffort() - this.getEffortCost());

                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), this.getSound(), SoundSource.PLAYERS, 1f, 1f);
                if (player.level instanceof ServerLevel level) usedParticles(level, player);

                cap.setPowerCooldown(15);
                onUse(player);
            }
        });
    }
    public SoundEvent getSound(){
        return switch (this.element){
            case MEDO, NONE -> OPSounds.FEAR_POWER_USED.get();
            case SANGUE -> OPSounds.BLOOD_POWER_USED.get();
            case CONHECIMENTO -> OPSounds.KNOWLEDGE_POWER_USED.get();
            case MORTE -> OPSounds.DEATH_POWER_USED.get();
            case ENERGIA -> OPSounds.ENERGY_POWER_USED.get();
        };
    }
    private void usedParticles(ServerLevel level, Player player){
        for (int i = 0; i < 360; i++){
            if (i % 20 == 0){
                for (int i2 = 1; i2 < 4; i2++){
                    level.sendParticles(new DustParticleOptions(this.element.getParticleVec3fColor(), 0.7f),
                            player.getX() + Math.cos(i) * i2/4d, player.getY() + 0.1d, player.getZ() + Math.sin(i) * i2/4d,
                            0, 0d, 0d, 0d, 1d);
                }
            }
        }
    }
    /**
     * Chamado quando o poder ativo é utilizado.
     *
     * @param player o jogador que utilizou o poder
     */
    public void onUse(Player player){

    }
    /**
     * Chamado a cada tick
     *
     * @param player o jogador que possui o poder
     */
    public void onTick(Player player){}
    /**
     * Chamado quando o usuário ataca
     *
     * @param player o jogador que possui o poder
     */
    public void onAttack(Player player, LivingEntity target){}
    /**
     * Chamado quando o usuário sofre dano
     *
     * @param player o jogador que possui o poder
     */
    public void onHurt(Player player, LivingEntity attacker, float amount){}
}