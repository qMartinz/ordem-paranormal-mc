package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import com.guga.ordemparanormal.api.paranormaldamage.ParanormalDamageSource;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TieredItem;

public class PunhoEnraivecido2 extends PlayerPower {
    public PunhoEnraivecido2(ResourceLocation id, ParanormalElement element, int nex, int[] attributes, PlayerPower... powers) {
        super(id, false, element, 0, nex, attributes, powers);
    }
    @Override
    public float onAttack(Player player, float amount, LivingEntity target, DamageSource source) {
        if (player.getMainHandItem().getItem() instanceof TieredItem && !(source instanceof IndirectEntityDamageSource)) return amount;
        return amount + 4
                * (ParanormalDamageSource.isEntityWeakTo(target, OPPowers.PUNHO_ENRAIVECIDO.getElement().getDamage()) ? 2f : 1f)
                / (ParanormalDamageSource.isEntityResistant(target, OPPowers.PUNHO_ENRAIVECIDO.getElement().getDamage()) ? 2f : 1f);
    }
}
