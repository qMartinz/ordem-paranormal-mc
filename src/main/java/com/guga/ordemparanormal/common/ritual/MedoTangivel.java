package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class MedoTangivel extends AbstractRitual {
    public MedoTangivel() {
        super("medo_tangivel", ParanormalElement.MEDO, 4, 8, true, 0d, false);
    }
    @Override
    public void onUseSelf(Level world, LivingEntity caster) {
        caster.addEffect(new MobEffectInstance(OPEffects.TANGIBLE_FEAR.get(), 300, 0, false, false));
    }
}
