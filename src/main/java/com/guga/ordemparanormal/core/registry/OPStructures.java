package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.worldgen.structures.Crypt;
import com.guga.ordemparanormal.common.worldgen.structures.CultistCabin;
import com.guga.ordemparanormal.common.worldgen.structures.DevilChurch;
import com.guga.ordemparanormal.common.worldgen.structures.Mansion;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class OPStructures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, OrdemParanormal.MOD_ID);

    public static final RegistryObject<StructureFeature<?>> DEVIL_CHURCH = STRUCTURE_FEATURES.register("devil_church", DevilChurch::new);
    public static final RegistryObject<StructureFeature<?>> MANSION = STRUCTURE_FEATURES.register("mansion", Mansion::new);
    public static final RegistryObject<StructureFeature<?>> CULTIST_CABIN = STRUCTURE_FEATURES.register("cultist_cabin", CultistCabin::new);
    //public static final RegistryObject<StructureFeature<?>> CRYPT = STRUCTURE_FEATURES.register("crypt", Crypt::new);

    public static void register(IEventBus eventBus) {
        STRUCTURE_FEATURES.register(eventBus);
    }
}