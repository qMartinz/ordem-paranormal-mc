package com.guga.ordemparanormal.common.events;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.IEffectsCap;
import com.guga.ordemparanormal.api.capabilities.data.ParanormalEffectsProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.paranormaldamage.EntityParanormalDamageSource;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPEffects;
import com.guga.ordemparanormal.core.registry.OPItems;
import com.guga.ordemparanormal.core.registry.OPPowers;
import com.guga.ordemparanormal.core.registry.OPRituals;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID)
public class AbilityEvents {
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event){
        float amount = event.getAmount();

        // Aplica dano extra com o poder punho enraivecido
        if (event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof LivingEntity living) {
            IAbilitiesCap cap = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
            if (cap != null) {
                if (cap.hasPower(OPPowers.PUNHO_ENRAIVECIDO_2) && !(player.getMainHandItem().getItem() instanceof TieredItem)) {
                    living.hurt((new EntityParanormalDamageSource("powerPunhoEnraivecido", player)).setElement(ParanormalElement.SANGUE), 4);
                } else if (player.hasEffect(OPEffects.ENRAGED_FIST.get()) && !(player.getMainHandItem().getItem() instanceof TieredItem)) {
                    living.hurt((new EntityParanormalDamageSource("powerPunhoEnraivecido", player)).setElement(ParanormalElement.SANGUE), 3);
                }
            }
        }

        // pega a capability com efeitos paranormais
        IEffectsCap effects = event.getEntity().getCapability(ParanormalEffectsProvider.PARANORMAL_EFFECTS).orElse(null);
        if (effects == null) return;

        // altera o dano com base nos efeitos paranormais
        amount = !effects.unappliableBloodArmorDamageSources().contains(event.getSource()) ||
                (event.getSource() instanceof ParanormalDamageSource s && s.element == ParanormalElement.MORTE) ?
                Math.max(amount - effects.getBloodArmorPoints()/2f, 1) : amount;

        float deathHealthApplied = Math.max(amount - effects.getDeathHealthPoints(), 0);
        effects.setDeathHealthPoints(effects.getDeathHealthPoints() - (int) Math.min(effects.getDeathHealthPoints(), amount));
        amount = deathHealthApplied;

        // altera o dano com base no poder medo tangivel
        if (event.getEntity() instanceof LivingEntity entity && entity.hasEffect(OPEffects.TANGIBLE_FEAR.get()) &&
                (!(event.getSource() instanceof ParanormalDamageSource) || event.getSource() == DamageSource.OUT_OF_WORLD)) amount = 0;

        event.setAmount(amount);
    }
    @SubscribeEvent
    public static void onLivingUseItemFinish(LivingEntityUseItemEvent.Finish event){
        // executa se for um player usando um item
        if (event.getEntity() instanceof Player player){
            player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(abi -> {
                // aplica os efeitos do poder dieta adaptada I e II
                if (abi.hasPower(OPPowers.DIETA_ADAPTADA) &&
                    event.getItem().getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.HUNGER)){
                    player.removeEffect(MobEffects.HUNGER);
                    if (abi.hasPower(OPPowers.DIETA_ADAPTADA_2)) player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600));
                }
                if (abi.hasPower(OPPowers.DIETA_ADAPTADA) &&
                        event.getItem().getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.POISON)){
                    player.removeEffect(MobEffects.POISON);
                    if (abi.hasPower(OPPowers.DIETA_ADAPTADA_2)) player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600));
                }
                if (abi.hasPower(OPPowers.DIETA_ADAPTADA) &&
                        event.getItem().getFoodProperties(player).getEffects().stream().anyMatch(pair -> pair.getFirst().getEffect() == MobEffects.CONFUSION)){
                    player.removeEffect(MobEffects.CONFUSION);
                    if (abi.hasPower(OPPowers.DIETA_ADAPTADA_2)) player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600));
                }
            });
        }
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        event.player.getInventory().items.stream()
                // filtra os itens no inventario para pegar só laminas do medo ativas
                .filter(itemStack -> itemStack.is(OPItems.RITUAL_LAMINA_MEDO.get()) && itemStack.getOrCreateTag().getBoolean("active"))
                // executa para cada lamina
                .forEach(itemStack -> {
                    // desativa as laminas do medo se o jogador n estiver segurando
                    if (event.player.getMainHandItem() != itemStack) itemStack.getOrCreateTag().putBoolean("active", false);

                    // desativa quaisquer laminas do medo se o jogador n souber o ritual
                    event.player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).ifPresent(cap -> {
                        if (!cap.knowsRitual(OPRituals.LAMINA_MEDO)) itemStack.getOrCreateTag().putBoolean("active", false);
                    });
        });
    }
}
