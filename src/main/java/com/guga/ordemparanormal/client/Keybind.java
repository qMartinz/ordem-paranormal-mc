package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.client.screen.NexScreen;
import com.guga.ordemparanormal.client.screen.TranscendScreen;
import com.guga.ordemparanormal.common.block.AltarTranscender;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Keybind {
    private final KeyMapping nexScreenKey = new KeyMapping("ordemparanormal.key.nex_screen", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, 82, "ordemparanormal.key_category");

    public Keybind(){
        ClientRegistry.registerKeyBinding(nexScreenKey);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){
        Minecraft minecraft = Minecraft.getInstance();
        if (nexScreenKey.consumeClick() && minecraft.screen == null){
            HitResult block =  minecraft.player.pick(minecraft.player.isCreative() ? 5D : 4.5D, 0.0F, false);
            BlockPos blockpos = ((BlockHitResult)block).getBlockPos();
            BlockState blockstate = minecraft.player.level.getBlockState(blockpos);
            if (block.getType() == HitResult.Type.BLOCK && blockstate.getBlock() instanceof AltarTranscender){
                minecraft.setScreen(new TranscendScreen());
            } else {
                minecraft.setScreen(new NexScreen());
            }
        }
    }
}
