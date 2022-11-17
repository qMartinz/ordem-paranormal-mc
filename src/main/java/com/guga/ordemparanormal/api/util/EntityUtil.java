package com.guga.ordemparanormal.api.util;

import com.guga.ordemparanormal.common.entity.Nevoa;
import com.guga.ordemparanormal.core.registry.OPEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class EntityUtil {
    /**
     * @param entity a entidade a ser utilizada para checar em qual entidade de névoa ela se encontra.
     * @return a entidade de névoa cuja a entidade se encontra dentro do raio. Pode retornar null, caso não haja nenhuma névoa.
     */
    public static @Nullable Nevoa getInNevoa(Entity entity){
        List<Nevoa> nevoas = entity.level.getEntitiesOfClass(Nevoa.class, new AABB(entity.blockPosition()).inflate(45d), nevoa -> {
            List<Entity> entitiesInRadius = nevoa.level.getEntitiesOfClass(Entity.class, nevoa.getBoundingBox().inflate(nevoa.getRadius()));
            return entitiesInRadius.contains(entity);
        });
        return nevoas.stream().findAny().orElse(null);
    }
    public static Nevoa createNevoa(Level level, Vec3 position){
        Nevoa nevoa = OPEntities.NEVOA.get().create(level);
        nevoa.setPos(position);
        nevoa.setIntensity(1);
        nevoa.setRadius(10);
        level.addFreshEntity(nevoa);
        return nevoa;
    }
    public static Mob transformMob(Mob entityIn, Mob entityOut) {
        if (entityIn.isAlive()) {
            entityOut.moveTo(entityIn.getX(), entityIn.getY(), entityIn.getZ(), entityIn.getYRot(), entityIn.getXRot());

            if (entityIn.hasCustomName()) {
                entityOut.setCustomName(entityIn.getCustomName());
                entityOut.setCustomNameVisible(entityIn.isCustomNameVisible());
            }

            if (entityIn.isLeashed()) {
                entityOut.setLeashedTo(entityIn.getLeashHolder(), true);
                entityIn.dropLeash(true, false);
            }

            if (entityIn.getVehicle() != null) {
                entityOut.startRiding(entityIn.getVehicle());
            }

            entityOut.setHealth(entityOut.getMaxHealth());
            entityIn.level.addFreshEntity(entityOut);
            entityIn.discard();
            return entityOut;
        }
        return entityIn;
    }
}
