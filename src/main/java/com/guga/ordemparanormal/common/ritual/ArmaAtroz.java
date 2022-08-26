package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.attributes.ParanormalAttribute;
import com.guga.ordemparanormal.api.capabilities.data.INexCap;
import com.guga.ordemparanormal.api.capabilities.data.PlayerNexProvider;
import com.guga.ordemparanormal.api.curses.CurseHelper;
import com.guga.ordemparanormal.api.powers.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPCurses;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class ArmaAtroz extends AbstractRitual {
    public ArmaAtroz() {
        super("arma_atroz", ParanormalElement.BLOOD, 1, 2, true, 0, null);
    }
    @Override
    public void onUse(@Nullable HitResult rayTraceResult, Level world, @Nullable LivingEntity caster) {
        if (caster.getOffhandItem().getItem() instanceof SwordItem) super.onUse(rayTraceResult, world, caster);
    }
    @Override
    public void onUseSelf(Level world, LivingEntity caster) {
        float presence = 0;
        if (caster instanceof Player player) {
            INexCap nexCap = player.getCapability(PlayerNexProvider.PLAYER_NEX).orElse(null);
            if (nexCap == null) return;
            presence = nexCap.getAttribute(ParanormalAttribute.PRESENCE);
        }

        CurseHelper.addCurse(caster.getOffhandItem(), OPCurses.ATROZ);
        int extraTicks = (int) (presence*100);
        int newTicks = CurseHelper.getCurseTag(caster.getOffhandItem(), OPCurses.ATROZ.getId()).getInt("ticks") + extraTicks;
        CurseHelper.setTicks(CurseHelper.getCurseTag(caster.getOffhandItem(), OPCurses.ATROZ.getId()), newTicks);
    }
}
