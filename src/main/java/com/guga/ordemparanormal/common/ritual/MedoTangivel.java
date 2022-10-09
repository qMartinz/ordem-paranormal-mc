package com.guga.ordemparanormal.common.ritual;

import com.guga.ordemparanormal.api.ElementDamage;
import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.abilities.ritual.AbstractRitual;
import com.guga.ordemparanormal.core.registry.OPEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collection;

public class MedoTangivel extends AbstractRitual {
    public MedoTangivel() {
        super("medo_tangivel", ParanormalElement.MEDO, 4, 8, true, 0d, false);
    }
    @Override
    public void onUseSelf(Level world, LivingEntity caster) {
        caster.addEffect(new MobEffectInstance(OPEffects.TANGIBLE_FEAR.get(), 300, 0, false, false));
    }
    public static Collection<DamageSource> unappliableDamageSources(){
        ArrayList<DamageSource> unappliableDamageSources = new ArrayList<>();

        unappliableDamageSources.add(DamageSource.OUT_OF_WORLD);
        unappliableDamageSources.add(ElementDamage.DANO_MORTE);
        unappliableDamageSources.add(ElementDamage.DANO_SANGUE);
        unappliableDamageSources.add(ElementDamage.DANO_CONHECIMENTO);
        unappliableDamageSources.add(ElementDamage.DANO_ENERGIA);
        unappliableDamageSources.add(ElementDamage.DANO_MEDO);

        return unappliableDamageSources;
    }
}
