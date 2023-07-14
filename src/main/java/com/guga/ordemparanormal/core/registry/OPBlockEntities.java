package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.common.block.entities.*;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OPBlockEntities {
    public static final BlockEntitySubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getBlockEntitySubHelper();
    public static final RegistryObject<BlockEntityType<MesaSangueBlockEntity>> BLOOD_CURSE_TABLE_BLOCK_ENTITY = HELPER.createBlockEntity("mesa_sangue",
            MesaSangueBlockEntity::new, () -> new Block[]{
                    OPBlocks.MESA_SANGUE.get()
            });
    public static final RegistryObject<BlockEntityType<MesaConhecimentoBlockEntity>> KNOWLEDGE_CURSE_TABLE_BLOCK_ENTITY = HELPER.createBlockEntity("mesa_conhecimento",
            MesaConhecimentoBlockEntity::new, () -> new Block[]{
                    OPBlocks.MESA_CONHECIMENTO.get()
            });
    public static final RegistryObject<BlockEntityType<MesaMorteBlockEntity>> DEATH_CURSE_TABLE_BLOCK_ENTITY = HELPER.createBlockEntity("mesa_morte",
            MesaMorteBlockEntity::new, () -> new Block[]{
                    OPBlocks.MESA_MORTE.get()
            });
    public static final RegistryObject<BlockEntityType<MesaEnergiaBlockEntity>> ENERGY_CURSE_TABLE_BLOCK_ENTITY = HELPER.createBlockEntity("mesa_energia",
            MesaEnergiaBlockEntity::new, () -> new Block[]{
                    OPBlocks.MESA_ENERGIA.get()
            });
    public static final RegistryObject<BlockEntityType<LuzBlockEntity>> LUZ_ENTITY = HELPER.createBlockEntity("luz",
            LuzBlockEntity::new, () -> new Block[]{OPBlocks.LUZ_BLOCK.get()});
}
