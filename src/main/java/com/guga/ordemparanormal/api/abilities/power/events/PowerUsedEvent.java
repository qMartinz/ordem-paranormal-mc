package com.guga.ordemparanormal.api.abilities.power.events;

import com.guga.ordemparanormal.api.abilities.power.PlayerPower;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class PowerUsedEvent extends LivingEvent {
    private final PlayerPower power;
    /**
     * PowerUsedEvent is fired when a Player uses a power. <br>
     * This event is fired whenever a power is used in
     * {@link PlayerPower#use(Player)}. <br>
     * <br>
     * This event is {@link Cancelable}.<br>
     * If this event is canceled, the Power fails and is not used.<br>
     * <br>
     * This event does not have a result. {@link HasResult}<br>
     * <br>
     * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
     */
    public PowerUsedEvent(LivingEntity entity, PlayerPower power) {
        super(entity);
        this.power = power;
    }
    public PlayerPower getPower() {
        return power;
    }
}
