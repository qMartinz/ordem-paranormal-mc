package com.guga.ordemparanormal.datagen.server;

import com.guga.ordemparanormal.core.registry.OPBlocks;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLoot extends BlockLoot {
    @Override
    protected void addTables() {
        dropSelf(OPBlocks.ALTAR_TRANSCENDER.get());
    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return OPBlocks.HELPER.getDeferredRegister().getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
