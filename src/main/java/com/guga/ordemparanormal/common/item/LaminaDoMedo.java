package com.guga.ordemparanormal.common.item;

import com.guga.ordemparanormal.core.OrdemParanormal;
import com.guga.ordemparanormal.core.registry.OPItems;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.AirBlock;

public class LaminaDoMedo extends SwordItem {

    public LaminaDoMedo() {

        // new SwordItem(Tiers.NETHERITE, 3, -2.4F, (new
        // Item.Properties()).tab(CreativeModeTab.TAB_COMBAT).fireResistant()));

        super(Tiers.NETHERITE, 30, -2.4F, new Properties().tab(OrdemParanormal.OP_TAB));

    }

    // TODO PASSAR PRA ENTITYEVENTS
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity caster) {
        if (caster.getMainHandItem().getItem() == OPItems.LAMINA_DO_MEDO.get()) {
            // spawna o item na mao principal
            pStack.shrink(1);

        }
        return super.hurtEnemy(pStack, pTarget, caster);
    }
}
