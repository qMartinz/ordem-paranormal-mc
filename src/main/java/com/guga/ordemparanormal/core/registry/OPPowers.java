package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.*;
import com.guga.ordemparanormal.common.power.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

import static com.guga.ordemparanormal.api.ParanormalElement.*;

public class OPPowers {
    // Poderes
    public static final PlayerPower AFINIDADE_SANGUE = new Afinidade(ParanormalElement.SANGUE);
    public static final PlayerPower AFINIDADE_MORTE = new Afinidade(ParanormalElement.MORTE);
    public static final PlayerPower AFINIDADE_ENERGIA = new Afinidade(ParanormalElement.ENERGIA);
    public static final PlayerPower AFINIDADE_CONHECIMENTO = new Afinidade(ParanormalElement.CONHECIMENTO);
    public static final PlayerPower SANGUE_FERRO = new AttributeModPower("sangue_ferro", ParanormalElement.SANGUE, 1, new int[]{0, 1, 0}).modifier(Attributes.MAX_HEALTH, new AttributeModifier("health_mod", 4, AttributeModifier.Operation.ADDITION));
    public static final PlayerPower SANGUE_FERRO_2 = new AttributeModPower("sangue_ferro_2", ParanormalElement.SANGUE, 10, new int[]{0, 5, 0}, OPPowers.SANGUE_FERRO, OPPowers.AFINIDADE_SANGUE).modifier(Attributes.MAX_HEALTH, new AttributeModifier("health_mod", 8, AttributeModifier.Operation.ADDITION));
    public static final PlayerPower PUNHO_ENRAIVECIDO = new PunhoEnraivecido("punho_enraivecido", true, ParanormalElement.SANGUE, 2, 1, new int[]{1, 0, 0});
    public static final PlayerPower PUNHO_ENRAIVECIDO_2 = new PunhoEnraivecido2("punho_enraivecido_2", ParanormalElement.SANGUE, 10, new int[]{5, 0, 0}, OPPowers.PUNHO_ENRAIVECIDO, OPPowers.AFINIDADE_SANGUE);
    public static final PlayerPower ADRENALINA = new Adrenalina("adrenalina", ParanormalElement.SANGUE, 3, new int[]{1, 1, 0});
    public static final PlayerPower ADRENALINA_2 = new PlayerPower("adrenalina_2", false, ParanormalElement.SANGUE, 0, 10, new int[]{2, 2, 0}, OPPowers.ADRENALINA, OPPowers.AFINIDADE_SANGUE);
    public static final PlayerPower SANGUE_VIVO = new SangueVivo("sangue_vivo", ParanormalElement.SANGUE, 5, new int[]{0, 2, 0}, OPPowers.SANGUE_FERRO, OPPowers.ADRENALINA);
    public static final PlayerPower SANGUE_VISCERAL = new SangueVisceral("sangue_visceral", ParanormalElement.SANGUE, 5, new int[]{2, 0, 0}, OPPowers.PUNHO_ENRAIVECIDO, OPPowers.ADRENALINA);
    public static final PlayerPower FLAGELO = new Flagelo("flagelo", ParanormalElement.SANGUE, 5, new int[]{0, 3, 0});
    public static final PlayerPower ABSORVER_AGONIA = new AbsorverAgonia("absorver_agonia", ParanormalElement.SANGUE, 10, new int[]{0, 4, 0}, OPPowers.FLAGELO);
    public static final PlayerPower DIETA_ADAPTADA = new DietaAdaptada("dieta_adaptada", SANGUE, 7, new int[]{0, 3, 0}, OPPowers.SANGUE_VIVO);
    public static final PlayerPower DIETA_ADAPTADA_2 = new DietaAdaptada2("dieta_adaptada_2", SANGUE, 10, new int[]{0, 4, 0}, OPPowers.DIETA_ADAPTADA, OPPowers.AFINIDADE_SANGUE);
    public static final PlayerPower POTENCIAL_APRIMORADO = new EffortModPower("potencial_aprimorado", new AttributeModifier(UUID.randomUUID(), "potencial_aprimorado_mod", 2, AttributeModifier.Operation.ADDITION), ParanormalElement.MORTE, 0, new int[]{0, 0, 1});
    public static final PlayerPower VAMPIRISMO = new Vampirismo("vampirismo", ParanormalElement.SANGUE, 3, 7, new int[]{3, 0, 0}, OPPowers.SANGUE_VISCERAL);
    public static final PlayerPower VAMPIRISMO_2 = new PlayerPower("vampirismo_2", false, SANGUE, 0, 10, new int[]{4, 0, 0}, VAMPIRISMO, AFINIDADE_SANGUE);
    public static final PlayerPower MEDO_TANGIVEL = new RitualPower("medo_tangivel", 20, new int[]{0, 4, 4}, OPRituals.MEDO_TANGIVEL, ABSORVER_AGONIA, AFINIDADE_SANGUE);
    public static final PlayerPower POTENCIAL_APRIMORADO_2 = new EffortModPower("potencial_aprimorado_2", new AttributeModifier(UUID.randomUUID(), "potencial_aprimorado_2_mod", 3, AttributeModifier.Operation.ADDITION), ParanormalElement.MORTE, 10, new int[]{0, 0, 4}, POTENCIAL_APRIMORADO, AFINIDADE_MORTE);
    public static final PlayerPower PUTREFATO = new Putrefato("putrefato", ParanormalElement.MORTE, 1, new int[]{1, 0, 0});
    public static final PlayerPower PUTREFATO_2 = new PlayerPower("putrefato_2", false, MORTE, 0, 10, new int[]{4, 0, 0}, PUTREFATO, AFINIDADE_MORTE);
    public static final PlayerPower POTENCIAL_REAPROVEITADO = new PotencialReaproveitado("potencial_reaproveitado", ParanormalElement.MORTE, 3, new int[]{2, 0, 0}, OPPowers.PUTREFATO);
    public static final PlayerPower ESCAPAR_MORTE = new EscaparMorte("escapar_morte", true, MORTE, 3, 3, new int[]{0, 2, 0}, POTENCIAL_APRIMORADO);
    public static final PlayerPower SURTO_TEMPORAL = new SurtoTemporal("surto_temporal", true, MORTE, 3, 5, new int[]{2, 0, 1});
    public static final PlayerPower PERIMETRO_ESPIRAL = new PerimetroEspiral("perimetro_espiral", ParanormalElement.MORTE, 3, 10, new int[]{0, 0, 3}, OPPowers.SURTO_TEMPORAL);
    public static final PlayerPower LAMINA_MEDO = new RitualPower("lamina_do_medo", 20, new int[]{4, 0, 4}, OPRituals.LAMINA_MEDO, PERIMETRO_ESPIRAL);
    public static final PlayerPower ABSORVER_ENTROPIA = new AbsorverEntropia("absorver_entropia", MORTE, 11, new int[]{3, 0, 0}, OPPowers.PERIMETRO_ESPIRAL);
    public static final PlayerPower LEMBRAR_DA_MORTE = new LembrarDaMorte("lembrar_da_morte", MORTE, 11, new int[]{0, 0, 4}, OPPowers.PERIMETRO_ESPIRAL);
    public static final PlayerPower CAMPO_PROTETOR = new CampoProtetor("campo_protetor", ParanormalElement.ENERGIA, 1, new int[]{0, 0, 1});
    public static final PlayerPower CAMPO_PROTETOR_2 = new PlayerPower("campo_protetor_2", false, ParanormalElement.ENERGIA, 0, 10, new int[]{0, 0, 4}, CAMPO_PROTETOR, AFINIDADE_ENERGIA);
    public static final PlayerPower GRAVIDADE_DISTORCIDA = new GravidadeDistorcida("gravidade_distorcida", true, ENERGIA, 3, 1, new int[]{0, 1, 0});
    public static final PlayerPower GRAVIDADE_DISTORCIDA_2 = new GravidadeDistorcida2("gravidade_distorcida_2", true, ENERGIA, 3, 10, new int[]{0, 4, 0}, GRAVIDADE_DISTORCIDA, AFINIDADE_ENERGIA);
    public static final PlayerPower CASUALIDADE_FORTUITA = new CasualidadeFortuita("casualidade_fortuita", ParanormalElement.ENERGIA, 3, new int[]{0, 0, 2});
    public static final PlayerPower CONHECIMENTO_AMPLIADO = new ConhecimentoAmpliado("conhecimento_ampliado", ParanormalElement.CONHECIMENTO, 1, new int[]{0, 0, 1});
    public static final PlayerPower CONHECIMENTO_AMPLIADO_2 = new ConhecimentoAmpliado("conhecimento_ampliado_2", ParanormalElement.CONHECIMENTO, 10, new int[]{0, 0, 4}, AFINIDADE_CONHECIMENTO, CONHECIMENTO_AMPLIADO);

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
        registerPower(CAMPO_PROTETOR);
        registerPower(CAMPO_PROTETOR_2);
        registerPower(GRAVIDADE_DISTORCIDA);
        registerPower(GRAVIDADE_DISTORCIDA_2);
        registerPower(CASUALIDADE_FORTUITA);

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
        registerPower(CONHECIMENTO_AMPLIADO);
        registerPower(CONHECIMENTO_AMPLIADO_2);

        // Medo
    }
    public static void registerPower(PlayerPower power){
        OrdemParanormalAPI.getInstance().registerPower(power.getId(), power);
    }
}
