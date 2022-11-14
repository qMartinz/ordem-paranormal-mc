package com.guga.ordemparanormal.core.registry;

import com.guga.ordemparanormal.common.block.blocks.*;
import com.guga.ordemparanormal.core.OrdemParanormal;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class OPBlocks {
    public static final BlockSubRegistryHelper HELPER = OrdemParanormal.REGISTRY_HELPER.getBlockSubHelper();
    public static final RegistryObject<Block> ALTAR_TRANSCENDER = HELPER.createBlock("altar_transcender",
            AltarTranscender::new, OrdemParanormal.OP_TAB);
    public static final RegistryObject<Block> MESA_SANGUE = HELPER.createRareBlock("mesa_sangue",
            MesaSangue::new, Rarity.RARE, OrdemParanormal.OP_TAB);
    public static final RegistryObject<Block> MESA_ENERGIA = HELPER.createRareBlock("mesa_energia",
            MesaEnergia::new, Rarity.RARE, OrdemParanormal.OP_TAB);
    public static final RegistryObject<Block> MESA_CONHECIMENTO = HELPER.createRareBlock("mesa_conhecimento",
            MesaConhecimento::new, Rarity.RARE, OrdemParanormal.OP_TAB);
    public static final RegistryObject<Block> MESA_MORTE = HELPER.createRareBlock("mesa_morte",
            MesaMorte::new, Rarity.RARE, OrdemParanormal.OP_TAB);
    public static final RegistryObject<Block> LUZ_BLOCK = HELPER.createBlock("luz_block",
            LuzBlock::new, (CreativeModeTab) null);
}
