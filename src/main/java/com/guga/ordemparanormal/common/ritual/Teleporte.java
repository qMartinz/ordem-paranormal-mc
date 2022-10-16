package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Teleporte extends AbstractRitual {

    public Teleporte() {
        super("teleporte", ParanormalElement.ENERGIA, 4, 8, true, 2D, true);
    }

    @Override
    public void onUseSelf(HitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        boolean crouch = caster.isCrouching();
        if (crouch == true) {
            CompoundTag pos = new CompoundTag();
            pos.putDouble("opmod.coordsX", caster.getX());
            pos.putDouble("opmod.coordsY", caster.getY());
            pos.putDouble("opmod.coordsZ", caster.getZ());

            ritualItem.getOrCreateTag().put("opmod.coords", pos);

        } else if (crouch == false
                && ritualItem.getOrCreateTag().contains("opmod.coords")) {

            Vec3 position = new Vec3(
                    ritualItem.getOrCreateTag().getCompound("opmod.coords").getDouble("opmod.coordsX"),
                    ritualItem.getOrCreateTag().getCompound("opmod.coords").getDouble("opmod.coordsY"),
                    ritualItem.getOrCreateTag().getCompound("opmod.coords").getDouble("opmod.coordsZ"));

            caster.moveTo(position);
        }
    }

    @Override
    public void onUseEntity(EntityHitResult rayTraceResult, Level world, LivingEntity caster, ItemStack ritualItem, InteractionHand hand) {
        boolean crouch = caster.isCrouching();
        ItemStack teleporte = caster.getItemInHand(InteractionHand.MAIN_HAND);

        if (crouch == false && teleporte.getOrCreateTag().contains("opmod.coords")) {
            Vec3 position = new Vec3(
                    teleporte.getOrCreateTag().getCompound("opmod.coords").getDouble("opmod.coordsX"),
                    teleporte.getOrCreateTag().getCompound("opmod.coords").getDouble("opmod.coordsY"),
                    teleporte.getOrCreateTag().getCompound("opmod.coords").getDouble("opmod.coordsZ"));

            rayTraceResult.getEntity().moveTo(position);
        }
    }
}
