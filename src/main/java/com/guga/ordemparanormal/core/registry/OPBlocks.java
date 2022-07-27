package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.block.AltarTranscender;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPBlocks {
    public static final BlockSubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getBlockSubHelper();
    public static final RegistryObject<Block> ALTAR_TRANSCENDER = HELPER.createBlock("altar_transcender",
            AltarTranscender::new, OrdemParanormal.OP_TAB);
}
