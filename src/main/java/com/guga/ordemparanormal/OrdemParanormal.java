package com.guga.ordemparanormal;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("ordemparanormal")
public class OrdemParanormal {
	
	public OrdemParanormal() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
