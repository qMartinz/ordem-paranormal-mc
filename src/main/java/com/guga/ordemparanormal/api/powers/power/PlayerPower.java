package com.guga.ordemparanormal.api.powers.power;

import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerPower {
    public static final PlayerPower EMPTY = new PlayerPower("", false, PowerType.FEAR, 0, null, 0, new int[]{});
    private final String id;
    private final boolean isActivePower;
    private final PowerType type;
    private final int effortCost;
    private final PlayerPower powerRequirement;
    private final int nexRequired;
    private final int[] attributesRequired;
    public int tick;
    public PlayerPower(String id, boolean isActivePower, PowerType type, int effortCost, PlayerPower powerRequirement, int nexRequired, int[] attributesRequired){
        this.id = id;
        this.isActivePower = isActivePower;
        this.type = type;
        this.effortCost = effortCost;
        this.powerRequirement = powerRequirement;
        this.nexRequired = nexRequired;
        this.attributesRequired = attributesRequired;
    }
    public String getId() {
        return id;
    }
    public boolean isActivePower() {
        return isActivePower;
    }
    public PowerType getType() {
        return type;
    }
    public int getEffortCost(){
        return this.effortCost;
    }
    public PlayerPower getPowerRequirement() {
        if (powerRequirement != null) return powerRequirement;
        return EMPTY;
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
    public List<Component> getDescription(){
        List<Component> lines = new ArrayList<>();

        ChatFormatting formatting = ChatFormatting.WHITE;
        switch (type){
            case BLOOD -> formatting = ChatFormatting.DARK_RED;
            case KNOWLEDGE -> formatting = ChatFormatting.GOLD;
            case ENERGY -> formatting = ChatFormatting.DARK_PURPLE;
            case DEATH -> formatting = ChatFormatting.DARK_GRAY;
            case FEAR -> formatting = ChatFormatting.WHITE;
        }
        Component title = getDisplayName().plainCopy().withStyle(formatting);
        lines.add(title);
        lines.add(TextComponent.EMPTY);
        for (int i = 1; i < 3; i++){
            lines.add(new TranslatableComponent(this.getTranslationKey() + ".description.line_" + i).withStyle(ChatFormatting.GRAY));
        }
        return lines;
    }

    /**
     * Chamado quando o poder (caso seja um poder ativo) é utilizado
     *
     * @param player o jogador que utilizou o poder
     */
    public void use(Player player) {
        player.getCapability(PlayerNexProvider.PLAYER_NEX).ifPresent(cap -> {
            if (cap.getCurrentEffort() < this.getEffortCost()) return;
            cap.setCurrentEffort(cap.getCurrentEffort() - this.getEffortCost());
            System.out.println(getDisplayName().getString() + " power used!");
        });
    }

    /**
     * Chamado quando o poder (caso não seja um poder ativo, ou seja, é um poder passivo) é utilizado
     *
     * @param player o jogador que possui o poder
     */
    public void tick(Player player){
    }
}