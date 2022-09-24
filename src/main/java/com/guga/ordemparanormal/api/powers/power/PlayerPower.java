package com.guga.ordemparanormal.api.powers.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.common.CommonComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.*;

public class PlayerPower {
    public static final PlayerPower EMPTY = new PlayerPower("", false, ParanormalElement.NONE, 0, 0, new int[]{});
    private final String id;
    private final boolean isActivePower;
    private final ParanormalElement element;
    private final int effortCost;
    private final Set<PlayerPower> powerRequirements;
    private final int nexRequired;
    private final int[] attributesRequired;
    public int tick;
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
    public List<Component> getDescription(){
        List<Component> lines = new ArrayList<>();

        ChatFormatting formatting = ChatFormatting.WHITE;
        switch (element){
            case SANGUE -> formatting = ChatFormatting.DARK_RED;
            case CONHECIMENTO -> formatting = ChatFormatting.GOLD;
            case ENERGIA -> formatting = ChatFormatting.DARK_PURPLE;
            case MORTE -> formatting = ChatFormatting.DARK_GRAY;
            case MEDO -> formatting = ChatFormatting.WHITE;
            case NONE -> formatting = ChatFormatting.GRAY;
        }
        Component title = getDisplayName().plainCopy().withStyle(formatting);
        lines.add(title);
        lines.add(TextComponent.EMPTY);

        lines.add(new TranslatableComponent(this.getTranslationKey() + ".description.line_1").withStyle(ChatFormatting.GRAY));
        lines.add(new TranslatableComponent(this.getTranslationKey() + ".description.line_2").withStyle(ChatFormatting.GRAY));
        if (this.isActivePower) {
            lines.add(CommonComponents.CONSUMES.plainCopy().append(" " + this.effortCost + " " + CommonComponents.EFFORT_POINTS_FULL_NAME.getString())
                    .withStyle(ChatFormatting.GRAY));
        }
        if (!powerRequirements.isEmpty() || !Arrays.equals(attributesRequired, new int[]{0, 0, 0}) || nexRequired != 0) {
            List<MutableComponent> requisites = new ArrayList<>();

            if (!powerRequirements.isEmpty()) powerRequirements.forEach(req -> requisites.add(req.getDisplayName().plainCopy()));

            if (nexRequired != 0) requisites.add(CommonComponents.NEX_ABBREVIATION.plainCopy().append(" " + this.nexRequired*5 + "%"));

            if (attributesRequired[ParanormalAttribute.STRENGTH.index] != 0)
                requisites.add(ParanormalAttribute.STRENGTH.getDisplayName().plainCopy().append(" " + attributesRequired[ParanormalAttribute.STRENGTH.index]));

            if (attributesRequired[ParanormalAttribute.VIGOR.index] != 0)
                requisites.add(ParanormalAttribute.VIGOR.getDisplayName().plainCopy().append(" " + attributesRequired[ParanormalAttribute.VIGOR.index]));

            if (attributesRequired[ParanormalAttribute.PRESENCE.index] != 0)
                requisites.add(ParanormalAttribute.PRESENCE.getDisplayName().plainCopy().append(" " + attributesRequired[ParanormalAttribute.PRESENCE.index]));

            Iterator<MutableComponent> iterator = requisites.iterator();
            MutableComponent requisitesComponent = CommonComponents.REQUISITES.plainCopy().append(": ").append(iterator.next());
            iterator.forEachRemaining(req -> requisitesComponent.append(", " + req.getString()));

            lines.add(TextComponent.EMPTY);
            lines.add(requisitesComponent.withStyle(ChatFormatting.GRAY));
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