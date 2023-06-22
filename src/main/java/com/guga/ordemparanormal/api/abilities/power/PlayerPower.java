package com.guga.ordemparanormal.api.abilities.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.events.PowerUsedEvent;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.client.particles.AbilitiesParticleOptions;
import com.guga.ordemparanormal.client.screen.buttons.PowerButton;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerPower {
    public static final PlayerPower EMPTY = new PlayerPower(new ResourceLocation(OrdemParanormal.MOD_ID, "empty"), false, ParanormalElement.NONE, 0, 0, new int[]{0, 0, 0});
    private final ResourceLocation id;
    private final boolean isActivePower;
    private final ParanormalElement element;
    private final int effortCost;
    private final Set<PlayerPower> powerRequirements;
    private final int nexRequired;
    private final int[] attributesRequired;
    public PlayerPower(ResourceLocation id, boolean isActivePower, ParanormalElement element, int effortCost, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements){
        this.id = id;
        this.isActivePower = isActivePower;
        this.element = element;
        this.effortCost = effortCost;
        this.powerRequirements = new HashSet<>(List.of(powerRequirements));
        this.nexRequired = nexRequired;
        this.attributesRequired = attributesRequired;
    }
    public ResourceLocation getId() {
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
        return getId().getNamespace() + ".power." + getId().getPath();
    }
    public Component getDisplayName(){ return Component.translatable(getTranslationKey()); }
    public FormattedText getDescription(){
        return Component.translatable(this.getTranslationKey() + ".description").withStyle(ChatFormatting.WHITE);
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
            PowerUsedEvent event = new PowerUsedEvent(player, this);
            MinecraftForge.EVENT_BUS.post(event);

            boolean use = cap.getCurrentEffort() >= this.getEffortCost() && canUse(player) && cap.getPowerCooldown() == 0 && this != EMPTY;

            if (use && !event.isCanceled()) {
                cap.setCurrentEffort(cap.getCurrentEffort() - this.getEffortCost());

                player.level.playSound(null, player.getX(), player.getY(), player.getZ(), this.getSound(), SoundSource.PLAYERS, 1f, 1f);
                if (player.level instanceof ServerLevel level) usedParticles(level, player.getX(), player.getY(), player.getZ());

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
    //TODO particula
    private void usedParticles(ServerLevel level, double x, double y, double z){
        for (int i = 0; i < 360; i++){
            if (i % 20 == 0){
                for (int i2 = 1; i2 < 4; i2++){
                    level.sendParticles(AbilitiesParticleOptions.createData(element.getParticleColor(), element != ParanormalElement.MORTE),
                            x + Math.cos(i) * i2/4d, y + 0.1d, z + Math.sin(i) * i2/4d,
                            1, 0d, 0d, 0d, 1d);
                }
            }
        }
    }
    /**
     * Chamado quando o poder ativo é utilizado.
     *
     * @param player o jogador que utilizou o poder
     */
    public void onUse(Player player){}
    /**
     * Chamado a cada tick
     *
     * @param player    o jogador que possui o poder
     * @param tickCount quantidade de ticks
     */
    public void onTick(Player player, int tickCount){}
    /**
     * Chamado quando o usuário ataca
     *
     * @param player quem causou dano
     * @param amount quanto dano foi causado
     * @param target o alvo do dano
     * @param source o tipo de dano causado
     * @return o dano alterado
     */
    public float onAttack(Player player, float amount, LivingEntity target, DamageSource source){
        return amount;
    }
    /**
     * Chamado quando o usuário sofre dano
     *
     * @param player quem sofreu dano
     * @param amount quanto dano foi sofrido
     * @param attacker quem causou o dano
     * @param source o tipo de dano causado
     * @return o dano alterado
     */
    public float onHurt(Player player, float amount, @Nullable Entity attacker, DamageSource source){
        return amount;
    }

    /**
     * Chamado quando o usuário começa a usar um item
     *
     * @param player quem utilizou
     * @param item o item utilizado
     * @param duration o item resultante do uso
     * @return a duração alterada pelo método
     */
    public int onStartUseItem(Player player, ItemStack item, int duration){
        return duration;
    }
    /**
     * Chamado quando o usuário usa algum item
     *
     * @param player quem utilizou
     * @param item o item que foi utilizado
     * @param result o item resultante do uso
     * @param duration a duração do uso
     * @return o item resultante do uso alterado pelo método
     */
    public ItemStack onFinishUseItem(Player player, ItemStack item, ItemStack result, int duration){
        return result;
    }

    /**
     * Chamado a cada tick quando o usuário está usando um item
     *
     * @param player quem está utilizando
     * @param item o item sendo utilizado
     * @param duration a duração da utilização
     * @return a duração alterada pelo método
     */
    public int onTickUseItem(Player player, ItemStack item, int duration){
        return duration;
    }
    /**
     * Chamado quando o usuário bloqueia um ataque com um escudo
     *
     * @param player quem bloqueou
     * @param attacker quem atacou
     * @param source o tipo de dano
     * @param originalBlockedDamage o dano bloqueado
     * @param blockedDamage o dano bloqueado
     * @return o dano bloqueado pelo escudo
     */
    public float onShieldBlock(Player player, @Nullable Entity attacker, DamageSource source, float originalBlockedDamage, float blockedDamage){
        return originalBlockedDamage;
    }
    /**
     * Chamado quando o usuário ataca, mas seu ataque é bloqueado por um escudo
     *
     * @param player quem atacou
     * @param target quem bloqueou
     * @param source o tipo de dano
     * @param originalBlockedDamage o dano bloqueado
     * @param blockedDamage o dano bloqueado
     * @return o dano bloqueado pelo escudo
     */
    public float onAttackBlocked(Player player, LivingEntity target, DamageSource source, float originalBlockedDamage, float blockedDamage){
        return originalBlockedDamage;
    }
    /**
     * Chamado quando o usuário quebra um bloco
     *
     * @param player quem quebrou
     * @param level o level onde o bloco foi quebrado
     * @param pos a posição do bloco
     * @param state o bloco quebrado
     * @param exp o exp ganho por quebrar o bloco
     * @return o exp ganho após o poder ser ativo
     */
    public int onBlockBreak(Player player, LevelAccessor level, BlockPos pos, BlockState state, int exp){
        return exp;
    }
    /**
     * Chamado quando o usuário recebe exp
     *
     * @param player quem recebeu o exp
     * @param exp o exp ganho
     * @return o exp ganho após o poder ser ativo
     */
    public int onXPChange(Player player, int exp){
        return exp;
    }
    /**
     * Chamado quando o usuário recebe levels
     *
     * @param player quem recebeu os levels
     * @param levels os levels ganho
     * @return os levels ganho após o poder ser ativo
     */
    public int onXPLevelChange(Player player, int levels){
        return levels;
    }
}