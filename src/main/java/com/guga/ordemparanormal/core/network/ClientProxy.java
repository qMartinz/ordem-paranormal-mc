package com.guga.ordemparanormal.core.network;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ClientProxy implements IProxy{
    @Override
    public void init() {}
    @Override
    public Level getClientWorld() {
        return Minecraft.getInstance().level;
    }
    @Override
    public Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }
    @Override
    public Player getPlayer() {
        return Minecraft.getInstance().player;
    }
}
