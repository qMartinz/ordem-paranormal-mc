package com.guga.ordemparanormal.api.ability;

import net.minecraft.world.entity.player.Player;

public abstract class PlayerAbility {
    private final int cost;
    public PlayerAbility(int cost){
        this.cost = cost;
    }
    public int getCost(){
        return this.cost;
    }
    public void onAdded(Player player){}
    public void tick(Player player){}
    public void onRemoved(Player player){}
}