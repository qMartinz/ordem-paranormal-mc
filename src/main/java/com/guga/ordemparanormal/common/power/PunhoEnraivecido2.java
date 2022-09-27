package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PunhoEnraivecido2 extends PlayerPower {
    public PunhoEnraivecido2() {
        super("punho_enraivecido_2", false, ParanormalElement.SANGUE, 0, 10, new int[]{5, 0, 0}, OPPowers.PUNHO_ENRAIVECIDO, OPPowers.AFINIDADE_SANGUE);
    }
    @Override
    public void attack(Player player, LivingEntity target) {
        if (player.getMainHandItem() == ItemStack.EMPTY){
            target.hurt(ElementDamage.DANO_SANGUE, 5);
        }
    }
}
