package com.guga.ordemparanormal.datagen.server;

import com.guga.ordemparanormal.core.registry.OPBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLoot extends BlockLoot {
    @Override
    protected void addTables() {
        dropSelf(OPBlocks.ALTAR_TRANSCENDER.get());
        dropSelf(OPBlocks.MESA_SANGUE.get());
        dropSelf(OPBlocks.MESA_CONHECIMENTO.get());
        dropSelf(OPBlocks.MESA_ENERGIA.get());
        dropSelf(OPBlocks.MESA_MORTE.get());
        add(OPBlocks.LUZ_BLOCK.get(), noDrop());
        add(OPBlocks.PURGATORIO_BLOCK.get(), noDrop());
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return OPBlocks.HELPER.getDeferredRegister().getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
