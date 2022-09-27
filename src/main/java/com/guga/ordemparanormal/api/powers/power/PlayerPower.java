package com.guga.ordemparanormal.api.powers.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.common.CommonComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
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
     * Chamado quando o poder ativo é utilizado.
     * Lembre-se de utilizar o método super antes das funcionalidades adicionais, para que os PE sejam contabilizados.
     *
     * @param player o jogador que utilizou o poder
     */
    public void use(Player player) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            if (cap.getCurrentEffort() < this.getEffortCost() && !canUse(player)) return;
            cap.setCurrentEffort(cap.getCurrentEffort() - this.getEffortCost());
        });
    }

    /**
     * Chamado a cada tick
     *
     * @param player o jogador que possui o poder
     */
    public void tick(Player player){}
    /**
     * Chamado quando o usuário ataca
     *
     * @param player o jogador que possui o poder
     */
    public void attack(Player player, LivingEntity target){}
    /**
     * Chamado quando o usuário sofre dano
     *
     * @param player o jogador que possui o poder
     */
    public void hurt(Player player, LivingEntity attacker, float amount){}
}