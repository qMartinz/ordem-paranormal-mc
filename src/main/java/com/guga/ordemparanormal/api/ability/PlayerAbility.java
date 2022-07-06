package com.guga.ordemparanormal.api.ability;

import net.minecraft.world.entity.player.Player;

public abstract class PlayerAbility {
    private final int cost;
    private final String name;
    public PlayerAbility(int cost, String name){
        this.cost = cost;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getCost(){
        return this.cost;
    }
    public void onAdded(Player player){}
    public void tick(Player player){}
    public void onRemoved(Player player){}
}