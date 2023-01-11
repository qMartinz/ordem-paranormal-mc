package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.OrdemParanormalAPI;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.AttributeModPower;
import com.guga.ordemparanormal.api.abilities.power.EffortModPower;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.abilities.power.RitualPower;
import com.guga.ordemparanormal.common.power.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

import static com.guga.ordemparanormal.api.ParanormalElement.*;
import static com.guga.ordemparanormal.core.OrdemParanormal.MOD_ID;

public class OPPowers {
    // Poderes
    public static final PlayerPower AFINIDADE_SANGUE = new Afinidade(SANGUE);
    public static final PlayerPower AFINIDADE_MORTE = new Afinidade(MORTE);
    public static final PlayerPower AFINIDADE_ENERGIA = new Afinidade(ENERGIA);
    public static final PlayerPower AFINIDADE_CONHECIMENTO = new Afinidade(CONHECIMENTO);
    public static final PlayerPower SANGUE_FERRO = new AttributeModPower(new ResourceLocation(MOD_ID, "sangue_ferro"), SANGUE, 1, new int[]{0, 1, 0}).modifier(Attributes.MAX_HEALTH, new AttributeModifier("health_mod", 4, AttributeModifier.Operation.ADDITION));
    public static final PlayerPower SANGUE_FERRO_2 = new AttributeModPower(new ResourceLocation(MOD_ID, "sangue_ferro_2"), SANGUE, 10, new int[]{0, 5, 0}, OPPowers.SANGUE_FERRO, OPPowers.AFINIDADE_SANGUE).modifier(Attributes.MAX_HEALTH, new AttributeModifier("health_mod", 8, AttributeModifier.Operation.ADDITION));
    public static final PlayerPower PUNHO_ENRAIVECIDO = new PunhoEnraivecido(new ResourceLocation(MOD_ID, "punho_enraivecido"), true, SANGUE, 2, 1, new int[]{1, 0, 0});
    public static final PlayerPower PUNHO_ENRAIVECIDO_2 = new PunhoEnraivecido2(new ResourceLocation(MOD_ID, "punho_enraivecido_2"), SANGUE, 10, new int[]{5, 0, 0}, OPPowers.PUNHO_ENRAIVECIDO, OPPowers.AFINIDADE_SANGUE);
    public static final PlayerPower ADRENALINA = new Adrenalina(new ResourceLocation(MOD_ID, "adrenalina"), SANGUE, 3, new int[]{1, 1, 0});
    public static final PlayerPower ADRENALINA_2 = new PlayerPower(new ResourceLocation(MOD_ID, "adrenalina_2"), false, SANGUE, 0, 10, new int[]{2, 2, 0}, OPPowers.ADRENALINA, OPPowers.AFINIDADE_SANGUE);
    public static final PlayerPower SANGUE_VIVO = new SangueVivo(new ResourceLocation(MOD_ID, "sangue_vivo"), SANGUE, 5, new int[]{0, 2, 0}, OPPowers.SANGUE_FERRO, OPPowers.ADRENALINA);
    public static final PlayerPower SANGUE_VISCERAL = new SangueVisceral(new ResourceLocation(MOD_ID, "sangue_visceral"), SANGUE, 5, new int[]{2, 0, 0}, OPPowers.PUNHO_ENRAIVECIDO, OPPowers.ADRENALINA);
    public static final PlayerPower FLAGELO = new Flagelo(new ResourceLocation(MOD_ID, "flagelo"), SANGUE, 5, new int[]{0, 3, 0});
    public static final PlayerPower ABSORVER_AGONIA = new AbsorverAgonia(new ResourceLocation(MOD_ID, "absorver_agonia"), SANGUE, 10, new int[]{0, 4, 0}, OPPowers.FLAGELO);
    public static final PlayerPower DIETA_ADAPTADA = new DietaAdaptada(new ResourceLocation(MOD_ID, "dieta_adaptada"), SANGUE, 7, new int[]{0, 3, 0}, OPPowers.SANGUE_VIVO);
    public static final PlayerPower DIETA_ADAPTADA_2 = new DietaAdaptada2(new ResourceLocation(MOD_ID, "dieta_adaptada_2"), SANGUE, 10, new int[]{0, 4, 0}, OPPowers.DIETA_ADAPTADA, OPPowers.AFINIDADE_SANGUE);
    public static final PlayerPower POTENCIAL_APRIMORADO = new EffortModPower(new ResourceLocation(MOD_ID, "potencial_aprimorado"), new AttributeModifier(UUID.randomUUID(), "potencial_aprimorado_mod", 2, AttributeModifier.Operation.ADDITION), MORTE, 0, new int[]{0, 0, 1});
    public static final PlayerPower VAMPIRISMO = new Vampirismo(new ResourceLocation(MOD_ID, "vampirismo"), SANGUE, 3, 7, new int[]{3, 0, 0}, OPPowers.SANGUE_VISCERAL);
    public static final PlayerPower VAMPIRISMO_2 = new PlayerPower(new ResourceLocation(MOD_ID, "vampirismo_2"), false, SANGUE, 0, 10, new int[]{4, 0, 0}, VAMPIRISMO, AFINIDADE_SANGUE);
    public static final PlayerPower MEDO_TANGIVEL = new RitualPower(new ResourceLocation(MOD_ID, "medo_tangivel"), 20, new int[]{0, 4, 4}, OPRituals.MEDO_TANGIVEL, ABSORVER_AGONIA, AFINIDADE_SANGUE);
    public static final PlayerPower POTENCIAL_APRIMORADO_2 = new EffortModPower(new ResourceLocation(MOD_ID, "potencial_aprimorado_2"), new AttributeModifier(UUID.randomUUID(), "potencial_aprimorado_2_mod", 3, AttributeModifier.Operation.ADDITION), MORTE, 10, new int[]{0, 0, 4}, POTENCIAL_APRIMORADO, AFINIDADE_MORTE);
    public static final PlayerPower PUTREFATO = new Putrefato(new ResourceLocation(MOD_ID, "putrefato"), MORTE, 1, new int[]{1, 0, 0});
    public static final PlayerPower PUTREFATO_2 = new PlayerPower(new ResourceLocation(MOD_ID, "putrefato_2"), false, MORTE, 0, 10, new int[]{4, 0, 0}, PUTREFATO, AFINIDADE_MORTE);
    public static final PlayerPower POTENCIAL_REAPROVEITADO = new PotencialReaproveitado(new ResourceLocation(MOD_ID, "potencial_reaproveitado"), MORTE, 3, new int[]{2, 0, 0}, PUTREFATO);
    public static final PlayerPower SACRIFICAR_ENTROPIA = new SacrificarEntropia(new ResourceLocation(MOD_ID, "sacrificar_entropia"), MORTE, 5, new int[]{0, 1, 0}, POTENCIAL_REAPROVEITADO);
    public static final PlayerPower SACRIFICAR_ENTROPIA_2 = new PlayerPower(new ResourceLocation(MOD_ID, "sacrificar_entropia_2"), false, MORTE, 0, 5, new int[]{0, 2, 0}, SACRIFICAR_ENTROPIA, AFINIDADE_MORTE);
    public static final PlayerPower ESCAPAR_MORTE = new EscaparMorte(new ResourceLocation(MOD_ID, "escapar_morte"), true, MORTE, 3, 3, new int[]{0, 1, 0}, POTENCIAL_APRIMORADO);
    public static final PlayerPower CONSUMIR = new Consumir(new ResourceLocation(MOD_ID, "consumir"), MORTE, 5, new int[]{0, 3, 0}, ESCAPAR_MORTE);
    public static final PlayerPower CONSUMIR_2 = new PlayerPower(new ResourceLocation(MOD_ID, "consumir_2"), false, MORTE, 0, 10, new int[]{0, 4, 0}, CONSUMIR, AFINIDADE_MORTE);
    public static final PlayerPower SURTO_TEMPORAL = new SurtoTemporal(new ResourceLocation(MOD_ID, "surto_temporal"), true, MORTE, 3, 5, new int[]{2, 0, 1});
    public static final PlayerPower PERIMETRO_ESPIRAL = new PerimetroEspiral(new ResourceLocation(MOD_ID, "perimetro_espiral"), MORTE, 3, 10, new int[]{0, 0, 3}, OPPowers.SURTO_TEMPORAL);
    public static final PlayerPower LAMINA_MEDO = new RitualPower(new ResourceLocation(MOD_ID, "lamina_do_medo"), 20, new int[]{4, 0, 4}, OPRituals.LAMINA_MEDO, PERIMETRO_ESPIRAL, AFINIDADE_MORTE);
    public static final PlayerPower ABSORVER_ENTROPIA = new AbsorverEntropia(new ResourceLocation(MOD_ID, "absorver_entropia"), MORTE, 11, new int[]{3, 0, 0}, OPPowers.PERIMETRO_ESPIRAL);
    public static final PlayerPower LEMBRAR_DA_MORTE = new LembrarDaMorte(new ResourceLocation(MOD_ID, "lembrar_da_morte"), MORTE, 11, new int[]{0, 0, 4}, OPPowers.PERIMETRO_ESPIRAL);
    public static final PlayerPower CAMPO_PROTETOR = new CampoProtetor(new ResourceLocation(MOD_ID, "campo_protetor"), ENERGIA, 1, new int[]{0, 0, 1});
    public static final PlayerPower CAMPO_PROTETOR_2 = new PlayerPower(new ResourceLocation(MOD_ID, "campo_protetor_2"), false, ENERGIA, 0, 10, new int[]{0, 0, 4}, CAMPO_PROTETOR, AFINIDADE_ENERGIA);
    public static final PlayerPower GRAVIDADE_DISTORCIDA = new GravidadeDistorcida(new ResourceLocation(MOD_ID, "gravidade_distorcida"), true, ENERGIA, 3, 1, new int[]{0, 1, 0});
    public static final PlayerPower GRAVIDADE_DISTORCIDA_2 = new GravidadeDistorcida2(new ResourceLocation(MOD_ID, "gravidade_distorcida_2"), true, ENERGIA, 3, 10, new int[]{0, 4, 0}, GRAVIDADE_DISTORCIDA, AFINIDADE_ENERGIA);
    public static final PlayerPower CASUALIDADE_FORTUITA = new CasualidadeFortuita(new ResourceLocation(MOD_ID, "casualidade_fortuita"), ENERGIA, 3, new int[]{0, 0, 2});
    public static final PlayerPower CONHECIMENTO_AMPLIADO = new ConhecimentoAmpliado(new ResourceLocation(MOD_ID, "conhecimento_ampliado"), CONHECIMENTO, 1, new int[]{0, 0, 1});
    public static final PlayerPower CONHECIMENTO_AMPLIADO_2 = new ConhecimentoAmpliado(new ResourceLocation(MOD_ID, "conhecimento_ampliado_2"), CONHECIMENTO, 10, new int[]{0, 0, 4}, AFINIDADE_CONHECIMENTO, CONHECIMENTO_AMPLIADO);
    public static final PlayerPower PRESENCA_AVASSALADORA = new PresencaAvassaladora(new ResourceLocation(MOD_ID, "presenca_avassaladora"), CONHECIMENTO, 4, 3, new int[]{0, 0, 2});
    public static final PlayerPower PRESENCA_AVASSALADORA_2 = new PresencaAvassaladora2(new ResourceLocation(MOD_ID, "presenca_avassaladora_2"), CONHECIMENTO, 6, 10, new int[]{0, 0, 4}, AFINIDADE_CONHECIMENTO, PRESENCA_AVASSALADORA);
    public static final PlayerPower SABEDORIA = new Sabedoria(new ResourceLocation(MOD_ID, "sabedoria"), CONHECIMENTO, 3, new int[]{0, 0, 2});
    public static final PlayerPower SABEDORIA_2 = new PlayerPower(new ResourceLocation(MOD_ID, "sabedoria_2"), false, CONHECIMENTO, 0, 10, new int[]{0, 0, 4}, AFINIDADE_CONHECIMENTO, SABEDORIA);
    public static final PlayerPower CARISMATICO = new Carismatico(new ResourceLocation(MOD_ID, "carismatico"), CONHECIMENTO, 3, 1, new int[]{0, 0, 1});
    public static final PlayerPower CARISMATICO_2 = new PlayerPower(new ResourceLocation(MOD_ID, "carismatico_2"), false, CONHECIMENTO, 0, 10, new int[]{0, 0, 4}, AFINIDADE_CONHECIMENTO, CARISMATICO);
    public static final PlayerPower SOBRECARGA = new Sobrecarga(new ResourceLocation(MOD_ID, "sobrecarga"), ENERGIA, 1, new int[]{0, 0, 0});
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

        // Energia
        registerPower(CAMPO_PROTETOR);
        registerPower(CAMPO_PROTETOR_2);
        registerPower(GRAVIDADE_DISTORCIDA);
        registerPower(GRAVIDADE_DISTORCIDA_2);
        registerPower(CASUALIDADE_FORTUITA);
        registerPower(SOBRECARGA);

        // Morte
        registerPower(POTENCIAL_APRIMORADO);
        registerPower(POTENCIAL_APRIMORADO_2);
        registerPower(PUTREFATO);
        registerPower(PUTREFATO_2);
        registerPower(POTENCIAL_REAPROVEITADO);
        registerPower(ESCAPAR_MORTE);
        registerPower(SURTO_TEMPORAL);
        registerPower(PERIMETRO_ESPIRAL);
        registerPower(ABSORVER_ENTROPIA);
        registerPower(LEMBRAR_DA_MORTE);
        registerPower(CONSUMIR);
        registerPower(CONSUMIR_2);
        registerPower(SACRIFICAR_ENTROPIA);
        registerPower(SACRIFICAR_ENTROPIA_2);

        // Conhecimento
        registerPower(CONHECIMENTO_AMPLIADO);
        registerPower(CONHECIMENTO_AMPLIADO_2);
        registerPower(PRESENCA_AVASSALADORA);
        registerPower(PRESENCA_AVASSALADORA_2);
        registerPower(SABEDORIA);
        registerPower(SABEDORIA_2);
        registerPower(CARISMATICO);
        registerPower(CARISMATICO_2);

        // Medo
        registerPower(MEDO_TANGIVEL);
        registerPower(LAMINA_MEDO);
    }
    public static void registerPower(PlayerPower power){
        OrdemParanormalAPI.getInstance().registerPower(power.getId(), power);
    }
}
