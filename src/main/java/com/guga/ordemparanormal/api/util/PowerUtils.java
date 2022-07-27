package com.guga.ordemparanormal.api.util;

import com.guga.ordemparanormal.api.powers.power.PlayerPower;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.client.PowerIcon;
import com.guga.ordemparanormal.client.RitualSymbol;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PowerUtils {
    public static RitualSymbol getSymbol(AbstractRitual ritual){
        for (RitualSymbol symbol : RitualSymbol.values()){
            if (symbol.getRitual() == ritual) return symbol;
        }
        return null;
    }
    public static PowerIcon getIcon(PlayerPower ability){
        for (PowerIcon icon : PowerIcon.values()){
            if (icon.getAbility() == ability) return icon;
        }
        return null;
    }
    public static Rarity getRarity(int tier) {
        switch (tier){
            case 2:
                return Rarity.UNCOMMON;
            case 3:
                return Rarity.RARE;
            case 4:
                return Rarity.EPIC;
            default:
                return Rarity.COMMON;
        }
    }
    public static HitResult rayTrace(Entity entity, double length, float lookOffset, boolean hitLiquids){
        HitResult result = entity.pick(length, lookOffset, hitLiquids);
        EntityHitResult entityLookedAt = MathUtils.getLookedAtEntity(entity, 25);
        return entityLookedAt == null ? result : entityLookedAt;
    }
}
