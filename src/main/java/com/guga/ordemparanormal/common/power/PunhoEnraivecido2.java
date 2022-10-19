package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.paranormaldamage.EntityParanormalDamageSource;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;

public class PunhoEnraivecido2 extends PlayerPower {
    public PunhoEnraivecido2() {
        super("punho_enraivecido_2", false, ParanormalElement.SANGUE, 0, 10, new int[]{5, 0, 0}, OPPowers.PUNHO_ENRAIVECIDO, OPPowers.AFINIDADE_SANGUE);
    }

    @Override
    public float onAttack(Player player, float amount, LivingEntity target, DamageSource source) {
        if (player.getMainHandItem().getItem() instanceof TieredItem) return amount;
        return amount + 4
                * (ParanormalDamageSource.isEntityWeakTo(target, OPPowers.PUNHO_ENRAIVECIDO.getElement().getDamage()) ? 2f : 1f)
                / (ParanormalDamageSource.isEntityResistant(target, OPPowers.PUNHO_ENRAIVECIDO.getElement().getDamage()) ? 2f : 1f);
    }
}
