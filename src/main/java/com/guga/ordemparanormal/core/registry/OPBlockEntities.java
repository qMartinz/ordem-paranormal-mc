package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.block.entities.BloodTableBlockEntity;
import com.guga.ordemparanormal.common.block.entities.DeathTableBlockEntity;
import com.guga.ordemparanormal.common.block.entities.EnergyTableBlockEntity;
import com.guga.ordemparanormal.common.block.entities.KnowledgeTableBlockEntity;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OPBlockEntities {
    public static final BlockEntitySubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getBlockEntitySubHelper();
    public static final RegistryObject<BlockEntityType<BloodTableBlockEntity>> BLOOD_TABLE_ENTITY = HELPER.createBlockEntity("blood_table",
            BloodTableBlockEntity::new, () -> new Block[]{OPBlocks.MESA_SANGUE.get()});
    public static final RegistryObject<BlockEntityType<EnergyTableBlockEntity>> ENERGY_TABLE_ENTITY = HELPER.createBlockEntity("energy_table",
            EnergyTableBlockEntity::new, () -> new Block[]{OPBlocks.MESA_ENERGIA.get()});
    public static final RegistryObject<BlockEntityType<DeathTableBlockEntity>> DEATH_TABLE_ENTITY = HELPER.createBlockEntity("death_table",
            DeathTableBlockEntity::new, () -> new Block[]{OPBlocks.MESA_MORTE.get()});
    public static final RegistryObject<BlockEntityType<KnowledgeTableBlockEntity>> KNOWLEDGE_TABLE_ENTITY = HELPER.createBlockEntity("knowledge_table",
            KnowledgeTableBlockEntity::new, () -> new Block[]{OPBlocks.MESA_CONHECIMENTO.get()});
}
