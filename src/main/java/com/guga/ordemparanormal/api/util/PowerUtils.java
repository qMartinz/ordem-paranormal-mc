package com.guga.ordemparanormal.api.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class PowerUtils {
    public static Rarity getRarity(int tier) {
        return switch (tier) {
            case 2 -> Rarity.UNCOMMON;
            case 3 -> Rarity.RARE;
            case 4 -> Rarity.EPIC;
            default -> Rarity.COMMON;
        };
    }
    public static HitResult rayTrace(Entity entity, double length, float lookOffset, boolean hitLiquids){
        HitResult result = entity.pick(length, lookOffset, hitLiquids);
        EntityHitResult entityLookedAt = MathUtils.getLookedAtEntity(entity, 25);
        return entityLookedAt == null ? result : entityLookedAt;
    }
}
