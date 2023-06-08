package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.worldgen.structures.*;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class OPStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, OrdemParanormal.MOD_ID);

    public static final RegistryObject<StructureType<DevilChurch>> DEVIL_CHURCH = STRUCTURE_TYPES.register("devil_church",
            () -> explicitStructureTypeTyping(DevilChurch.CODEC));
    public static final RegistryObject<StructureType<Mansion>> MANSION = STRUCTURE_TYPES.register("mansion",
            () -> explicitStructureTypeTyping(Mansion.CODEC));
    public static final RegistryObject<StructureType<CultistCabin>> CULTIST_CABIN = STRUCTURE_TYPES.register("cultist_cabin",
            () -> explicitStructureTypeTyping(CultistCabin.CODEC));
    public static final RegistryObject<StructureType<Crypt>> CRYPT = STRUCTURE_TYPES.register("crypt",
            () -> explicitStructureTypeTyping(Crypt.CODEC));
    public static final RegistryObject<StructureType<BloodLaboratory>> BLOOD_LABORATORY = STRUCTURE_TYPES.register("blood_laboratory",
            () -> explicitStructureTypeTyping(BloodLaboratory.CODEC));
    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }
}