package com.guga.ordemparanormal.api.abilities.ritual.events;

import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import org.jetbrains.annotations.Nullable;

@Cancelable
public class RitualUsedEvent extends LivingEvent {
    private final AbstractRitual ritual;
    private final @Nullable HitResult hitResult;

    /**
     * RitualUsedEvent is fired when a LivingEntity uses a ritual. <br>
     * This event is fired whenever a ritual is cast in
     * {@link AbstractRitual#onUse(HitResult, Level, LivingEntity)}. <br>
     * <br>
     * This event is {@link Cancelable}.<br>
     * If this event is canceled, the Ritual fails and is not cast.<br>
     * <br>
     * This event does not have a result. {@link HasResult}<br>
     * <br>
     * This event is fired on the {@link MinecraftForge#EVENT_BUS}.
     */
    public RitualUsedEvent(LivingEntity entity, AbstractRitual ritual, @Nullable HitResult hitResult) {
        super(entity);
        this.ritual = ritual;
        this.hitResult = hitResult;
    }
    public AbstractRitual getRitual() {
        return ritual;
    }
    public @Nullable HitResult getHitResult() {
        return hitResult;
    }
}
