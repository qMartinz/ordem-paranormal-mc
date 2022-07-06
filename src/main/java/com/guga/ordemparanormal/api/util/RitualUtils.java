package com.guga.ordemparanormal.api.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class RitualUtils {
    public static HitResult rayTrace(Entity entity, double length, float lookOffset, boolean hitLiquids){
        HitResult result = entity.pick(length, lookOffset, hitLiquids);
        EntityHitResult entityLookedAt = MathUtils.getLookedAtEntity(entity, 25);
        return entityLookedAt == null ? result : entityLookedAt;
    }
}
