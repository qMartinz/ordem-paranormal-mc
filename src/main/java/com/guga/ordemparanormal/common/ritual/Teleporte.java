package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.DefensiveRitual;
import com.guga.ordemparanormal.api.abilities.ritual.OffensiveRitual;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Teleporte extends AbstractRitual implements OffensiveRitual, DefensiveRitual {
    public Teleporte(ResourceLocation id, ParanormalElement element, int tier, int effortCost,
                     double range, boolean mustHoldIngredient) {
        super(id, element, tier, effortCost, range, mustHoldIngredient);
    }

    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem,
            InteractionHand hand) {
        if (ritualItem != null) {
            boolean crouch = caster.isCrouching();
            if (crouch) {
                CompoundTag pos = new CompoundTag();
                pos.putDouble("teleport.coordsX", caster.getX());
                pos.putDouble("teleport.coordsY", caster.getY());
                pos.putDouble("teleport.coordsZ", caster.getZ());

                ritualItem.getOrCreateTag().put("teleport.coords", pos);

            } else if (crouch == false
                    && ritualItem.getOrCreateTag().contains("teleport.coords")) {
                CompoundTag pos = ritualItem.getOrCreateTag().getCompound("teleport.coords");

                Vec3 position = new Vec3(
                        pos.getDouble("teleport.coordsX"),
                        pos.getDouble("teleport.coordsY"),
                        pos.getDouble("teleport.coordsZ"));

                caster.moveTo(position);
            }
        }
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem,
            InteractionHand hand) {
        if (ritualItem != null) {
            boolean crouch = caster.isCrouching();
            LivingEntity target = (LivingEntity) rayTraceResult.getEntity();

            if (crouch == false && ritualItem.getOrCreateTag().contains("teleport.coords")) {
                CompoundTag pos = ritualItem.getOrCreateTag().getCompound("teleport.coords");

                Vec3 position = new Vec3(
                        pos.getDouble("teleport.coordsX"),
                        pos.getDouble("teleport.coordsY"),
                        pos.getDouble("teleport.coordsZ"));

                target.moveTo(position);
            }
        }
    }
}
