package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.block.entities.BloodTableBlockEntity;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OPBlockEntities {
    public static final BlockEntitySubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getBlockEntitySubHelper();
    public static final RegistryObject<BlockEntityType<BloodTableBlockEntity>> BLOOD_TABLE_ENTITY = HELPER.createBlockEntity("blood_table",
            (BlockPos pWorldPosition, BlockState pBlockState) -> new BloodTableBlockEntity(pWorldPosition, pBlockState), () -> new Block[]{OPBlocks.MESA_SANGUE.get()});
}
