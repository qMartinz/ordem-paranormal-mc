package com.guga.ordemparanormal.common.power;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class Sobrecarga extends PlayerPower {
    public Sobrecarga(ResourceLocation id, ParanormalElement element, int nexRequired, int[] attributesRequired, PlayerPower... powerRequirements) {
        super(id, false, element, 0, nexRequired, attributesRequired, powerRequirements);
    }
    @Override
    public float onHurt(Player player, float amount, @Nullable Entity attacker, DamageSource source) {
        if (amount > player.getMaxHealth()/2f){
            CompoundTag tag = new CompoundTag();
            ListTag explosions = new ListTag();

            tag.putInt("LifeTime", 0);
            tag.put("FireworksItem", new CompoundTag());
            tag.getCompound("FireworksItem").put("tag", new CompoundTag());
            tag.getCompound("FireworksItem").getCompound("tag").put("Fireworks", new CompoundTag());

            CompoundTag explosion1 = new CompoundTag();
            explosion1.putByte("Type", (byte)1);
            explosion1.putIntArray("Colors", new int[]{0x69329F, 0x69329F});
            explosion1.putIntArray("FadeColors", new int[]{0xB732C8, 0xB732C8});
            CompoundTag explosion2 = new CompoundTag();
            explosion2.putByte("Type", (byte)4);
            explosion2.putIntArray("Colors", new int[]{0x69329F, 0x69329F});
            explosion2.putIntArray("FadeColors", new int[]{0xB732C8, 0xB732C8});

            explosions.add(explosion1);
            explosions.add(explosion2);

            tag.getCompound("FireworksItem").getCompound("tag").getCompound("Fireworks").put("Explosions", explosions);

            System.out.println("help");
            player.level.createFireworks(player.getX(), player.getY() + player.getEyeHeight()/2d, player.getZ(),
                   0d, 0d, 0d, tag);
        }
        return super.onHurt(player, amount, attacker, source);
    }
}
