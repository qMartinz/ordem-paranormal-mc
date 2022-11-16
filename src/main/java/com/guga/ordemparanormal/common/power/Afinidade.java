package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import net.minecraft.resources.ResourceLocation;

import static com.guga.ordemparanormal.core.OrdemParanormal.MOD_ID;

public class Afinidade extends PlayerPower {
    public Afinidade(ParanormalElement element) {
        super(new ResourceLocation(MOD_ID, "afinidade_" + element.name), false, element, 0, 10, new int[]{0, 0, 0});
    }
}
