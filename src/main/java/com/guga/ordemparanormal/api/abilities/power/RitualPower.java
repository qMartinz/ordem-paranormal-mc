package com.guga.ordemparanormal.api.abilities.power;

import com.guga.ordemparanormal.api.abilities.power.events.PowerUsedEvent;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.api.abilities.ritual.DefensiveRitual;
import com.guga.ordemparanormal.api.abilities.ritual.OffensiveRitual;
import com.guga.ordemparanormal.api.abilities.ritual.UtilityRitual;
import com.guga.ordemparanormal.api.abilities.ritual.events.RitualUsedEvent;
import com.guga.ordemparanormal.api.capabilities.data.IAbilitiesCap;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerAbilitiesProvider;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.util.PowerUtils;
import com.guga.ordemparanormal.common.entity.RitualProjectile;
import com.guga.ordemparanormal.common.item.RitualComponent;
import com.guga.ordemparanormal.common.power.Afinidade;
import com.guga.ordemparanormal.core.registry.OPPowers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;

public class RitualPower extends PlayerPower{
    public final AbstractRitual ritual;
    public RitualPower(ResourceLocation id, int nexRequired, int[] attributesRequired, AbstractRitual ritual, PlayerPower... powerRequirements) {
        super(id, true, ritual.getElement(), 0, nexRequired, attributesRequired, powerRequirements);
        this.ritual = ritual;
    }
    public AbstractRitual getRitual() {
        return ritual;
    }
    @Override
    public void onAdded(Player player) {
        IAbilitiesCap abilities = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        INexCap nex = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        if (abilities == null || nex == null) return;

        nex.setRitualSlots(nex.getRitualSlots() + 1);
        abilities.learnRitual(ritual);
    }
    @Override
    public void use(Player player) {
        IAbilitiesCap abilities = player.getCapability(PlayerAbilitiesProvider.PLAYER_ABILITIES).orElse(null);
        INexCap nex = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
        if (abilities == null || nex == null) return;

        PowerUsedEvent event = new PowerUsedEvent(player, this);
        MinecraftForge.EVENT_BUS.post(event);

        if (!event.isCanceled()){
            if (canUse(player) && nex.getPowerCooldown() == 0) {
                double length = ritual.getRange();
                if (abilities.hasPower(OPPowers.AMPLIAR_RITUAL) && length > 0d) length += 3.5d;
                HitResult result = PowerUtils.rayTrace(player, length, 0, false);

                boolean canCast = player.isCreative();

                if (!player.isCreative() && nex.getCurrentEffort() >= getEffortCost()) canCast = true;

                if (canCast) {
                    RitualUsedEvent ritualEvent = new RitualUsedEvent(player, ritual, result);
                    MinecraftForge.EVENT_BUS.post(ritualEvent);

                    if (!ritualEvent.isCanceled()) {
                        if (ritual instanceof DefensiveRitual defensiveRitual) {
                            defensiveRitual.onUseSelf(result, player.level, player, null, null);
                            ritual.ritualSuccess((ServerLevel) player.level, player);
                        } else if ((ritual instanceof OffensiveRitual) || (ritual instanceof UtilityRitual)) {
                            RitualProjectile projectile = new RitualProjectile(player.level, player, ritual);
                            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.5f, 0.0f);
                            player.level.addFreshEntity(projectile);
                            projectile.setElement(ritual.getElement().index);
                            ritual.ritualSuccess((ServerLevel) player.level, player);
                        }

                        if (!player.isCreative()) ritual.useResources(false, nex, player);
                    } else ritual.ritualFail(player);
                } else ritual.ritualFail(player);

                nex.setPowerCooldown(15);
                this.onUse(player);
            }
        }
    }
}
