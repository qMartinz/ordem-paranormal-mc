package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public class Sobrecarga extends PlayerPower {
    public Sobrecarga(ResourceLocation id, ParanormalElement element, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, false, element, 0, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public float onHurt(Player player, float amount, @Nullable Entity attacker, DamageSource source) {
        if (amount > player.getMaxHealth()/3f){
            ItemStack fireworkItem = new ItemStack(Items.FIREWORK_ROCKET);
            CompoundTag tag = fireworkItem.getOrCreateTag();
            ListTag explosions = new ListTag();

            CompoundTag explosion1 = new CompoundTag();
            explosion1.putByte("Type", (byte)1);
            explosion1.putIntArray("Colors", new int[]{0x69329F, 0x69329F});
            explosion1.putIntArray("FadeColors", new int[]{0xB732C8, 0xB732C8});
            CompoundTag explosion2 = new CompoundTag();
            explosion2.putByte("Type", (byte)1);
            explosion2.putIntArray("Colors", new int[]{0x69329F, 0x69329F});
            explosion2.putIntArray("FadeColors", new int[]{0xB732C8, 0xB732C8});

            explosions.add(explosion1);
            explosions.add(explosion2);

            CompoundTag fireworksTag = new CompoundTag();
            fireworksTag.putInt("Flight", -128);
            fireworksTag.put("Explosions", explosions);

            tag.put("Fireworks", fireworksTag);

            FireworkRocketEntity rocket = new FireworkRocketEntity(player.level, player.getX(), player.getY() + player.getEyeHeight()/2f, player.getZ(), fireworkItem);

            CompoundTag fireworkEntityTag = new CompoundTag();
            fireworkEntityTag.putInt("LifeTime", 0);
            rocket.addAdditionalSaveData(fireworkEntityTag);

            player.level.addFreshEntity(rocket); //Spawn the rocket entity
        }
        return super.onHurt(player, amount, attacker, source);
    }
}
