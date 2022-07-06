package com.guga.ordemparanormal.api.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;

public class MathUtils {
    public static int Oscillate(int input, int min, int max) {
        int range = max - min ;
        return min + Math.abs(((input + range) % (range * 2)) - range);
    }
    public static @Nullable EntityHitResult getLookedAtEntity(Entity entity, int range){
        Vec3 vec3d = entity.getEyePosition(1.0f);
        Vec3 vec3d1 = entity.getViewVector(1.0F);
        Vec3 vec3d2 = vec3d.add(vec3d1.x * range, vec3d1.y * range, vec3d1.z * range);
        AABB axisalignedbb = entity.getBoundingBox().expandTowards(vec3d1.scale(range)).inflate(1.0D, 1.0D, 1.0D);
        return MathUtils.traceEntities(entity, vec3d, vec3d2, axisalignedbb, (e) -> !e.isSpectator() && e.isPickable(), range);
    }
    public static @Nullable EntityHitResult traceEntities(Entity shooter, Vec3 startVec, Vec3 endVec, AABB boundingBox, Predicate<Entity> filter, double distance){
        Level world = shooter.level;
        double d0 = distance;
        Entity entity = null;
        Vec3 vec3d = null;

        for(Entity entity1 : world.getEntities(shooter, boundingBox, filter)) {
            AABB axisalignedbb = entity1.getBoundingBox().inflate(entity1.getPickRadius());
            Optional<Vec3> optional = axisalignedbb.clip(startVec, endVec);
            if (axisalignedbb.contains(startVec)) {
                if (d0 >= 0.0D) {
                    entity = entity1;
                    vec3d = optional.orElse(startVec);
                    d0 = 0.0D;
                }
            } else if (optional.isPresent()) {
                Vec3 vec3d1 = optional.get();
                double d1 = startVec.distanceToSqr(vec3d1);
                if (d1 < d0 || d0 == 0.0D) {
                    if (entity1.getRootVehicle() == shooter.getRootVehicle() && !entity1.canRiderInteract()) {
                        if (d0 == 0.0D) {
                            entity = entity1;
                            vec3d = vec3d1;
                        }
                    } else {
                        entity = entity1;
                        vec3d = vec3d1;
                        d0 = d1;
                    }
                }
            }
        }

        return entity == null ? null : new EntityHitResult(entity, vec3d);
    }
}
