package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.*;
import com.guga.ordemparanormal.common.power.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

import static com.guga.ordemparanormal.api.ParanormalElement.MORTE;
import static com.guga.ordemparanormal.api.ParanormalElement.SANGUE;

public class OPPowers {
    // Poderes
    public static final PlayerPower AFINIDADE_SANGUE = new Afinidade(ParanormalElement.SANGUE);
    public static final PlayerPower AFINIDADE_MORTE = new Afinidade(ParanormalElement.MORTE);
    public static final PlayerPower AFINIDADE_ENERGIA = new Afinidade(ParanormalElement.ENERGIA);
    public static final PlayerPower AFINIDADE_CONHECIMENTO = new Afinidade(ParanormalElement.CONHECIMENTO);
    public static final PlayerPower SANGUE_FERRO = new AttributeModPower("sangue_ferro", Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "sangue_ferro_mod", 4, AttributeModifier.Operation.ADDITION), ParanormalElement.SANGUE, 1, new int[]{0, 1, 0});
    public static final PlayerPower SANGUE_FERRO_2 = new AttributeModPower("sangue_ferro_2", Attributes.MAX_HEALTH, new AttributeModifier(UUID.randomUUID(), "sangue_ferro_2_mod", 6, AttributeModifier.Operation.ADDITION), ParanormalElement.SANGUE, 10, new int[]{0, 5, 0}, OPPowers.SANGUE_FERRO, OPPowers.AFINIDADE_SANGUE);
    public static final PlayerPower PUNHO_ENRAIVECIDO = new PunhoEnraivecido();
    public static final PlayerPower PUNHO_ENRAIVECIDO_2 = new PunhoEnraivecido2();
    public static final PlayerPower ADRENALINA = new Adrenalina();
    public static final PlayerPower ADRENALINA_2 = new PlayerPower("adrenalina_2", false, ParanormalElement.SANGUE, 0, 10, new int[]{2, 2, 0}, OPPowers.ADRENALINA, OPPowers.AFINIDADE_SANGUE);
    public static final PlayerPower SANGUE_VIVO = new SangueVivo();
    public static final PlayerPower SANGUE_VISCERAL = new SangueVisceral();
    public static final PlayerPower FLAGELO = new Flagelo();
    public static final PlayerPower ABSORVER_AGONIA = new AbsorverAgonia();
    public static final PlayerPower DIETA_ADAPTADA = new DietaAdaptada();
    public static final PlayerPower DIETA_ADAPTADA_2 = new DietaAdaptada2();
    public static final PlayerPower POTENCIAL_APRIMORADO = new EffortModPower("potencial_aprimorado", new AttributeModifier(UUID.randomUUID(), "potencial_aprimorado_mod", 2, AttributeModifier.Operation.ADDITION), ParanormalElement.MORTE, 0, new int[]{0, 0, 1});
    public static final PlayerPower VAMPIRISMO = new Vampirismo();
    public static final PlayerPower VAMPIRISMO_2 = new PlayerPower("vampirismo_2", false, SANGUE, 0, 10, new int[]{4, 0, 0}, VAMPIRISMO, AFINIDADE_SANGUE);
    public static final PlayerPower MEDO_TANGIVEL = new RitualPower("medo_tangivel", 20, new int[]{0, 4, 4}, OPRituals.MEDO_TANGIVEL, ABSORVER_AGONIA, AFINIDADE_SANGUE);
    public static final PlayerPower POTENCIAL_APRIMORADO_2 = new EffortModPower("potencial_aprimorado_2", new AttributeModifier(UUID.randomUUID(), "potencial_aprimorado_2_mod", 3, AttributeModifier.Operation.ADDITION), ParanormalElement.MORTE, 10, new int[]{0, 0, 4}, POTENCIAL_APRIMORADO, AFINIDADE_MORTE);
    public static final PlayerPower PUTREFATO = new Putrefato();
    public static final PlayerPower PUTREFATO_2 = new PlayerPower("putrefato_2", false, MORTE, 0, 10, new int[]{4, 0, 0}, PUTREFATO, AFINIDADE_MORTE);
    public static final PlayerPower POTENCIAL_REAPROVEITADO = new PotencialReaproveitado();
    public static final PlayerPower ESCAPAR_MORTE = new MobEffectPower("escapar_morte", new MobEffectInstance(OPEffects.SWERVE_DEATH.get(), 800, 0, false, false), MORTE, 3, 3, new int[]{0, 2, 0}, POTENCIAL_APRIMORADO);
    public static final PlayerPower SURTO_TEMPORAL = new MobEffectPower("surto_temporal", new MobEffectInstance(OPEffects.TEMPORAL_SURGE.get(), 600, 0, false, false), MORTE, 3, 5, new int[]{2, 0, 1});
    public static final PlayerPower PERIMETRO_ESPIRAL = new PerimetroEspiral();
    public static final PlayerPower LAMINA_MEDO = new RitualPower("lamina_do_medo", 20, new int[]{4, 0, 4}, OPRituals.LAMINA_MEDO, PERIMETRO_ESPIRAL);
    public static final PlayerPower ABSORVER_ENTROPIA = new AbsorverEntropia();
    public static final PlayerPower LEMBRAR_DA_MORTE = new LembrarDaMorte();

    /**
     * Registra os poderes
     */
    public static void setup(){
        registerPower(AFINIDADE_CONHECIMENTO);
        registerPower(AFINIDADE_SANGUE);
        registerPower(AFINIDADE_MORTE);
        registerPower(AFINIDADE_ENERGIA);

        // Sangue
        registerPower(SANGUE_FERRO);
        registerPower(SANGUE_FERRO_2);
        registerPower(PUNHO_ENRAIVECIDO);
        registerPower(PUNHO_ENRAIVECIDO_2);
        registerPower(ADRENALINA);
        registerPower(ADRENALINA_2);
        registerPower(SANGUE_VIVO);
        registerPower(SANGUE_VISCERAL);
        registerPower(FLAGELO);
        registerPower(ABSORVER_AGONIA);
        registerPower(DIETA_ADAPTADA);
        registerPower(DIETA_ADAPTADA_2);
        registerPower(VAMPIRISMO);
        registerPower(VAMPIRISMO_2);
        registerPower(MEDO_TANGIVEL);

        // Energia

        // Morte
        registerPower(POTENCIAL_APRIMORADO);
        registerPower(POTENCIAL_APRIMORADO_2);
        registerPower(PUTREFATO);
        registerPower(PUTREFATO_2);
        registerPower(POTENCIAL_REAPROVEITADO);
        registerPower(ESCAPAR_MORTE);
        registerPower(SURTO_TEMPORAL);
        registerPower(PERIMETRO_ESPIRAL);
        registerPower(LAMINA_MEDO);
        registerPower(ABSORVER_ENTROPIA);
        registerPower(LEMBRAR_DA_MORTE);

        // Conhecimento

        // Medo
    }
    public static void registerPower(PlayerPower power){
        OrdemParanormalAPI.getInstance().registerPower(power.getId(), power);
    }
}
