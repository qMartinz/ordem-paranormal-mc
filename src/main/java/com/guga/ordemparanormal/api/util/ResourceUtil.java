package com.guga.ordemparanormal.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

public class ResourceUtil {
    public static boolean hasResource(Minecraft minecraft, ResourceLocation resourceLocation){
        return minecraft.getResourceManager().listPacks().anyMatch(p -> p.hasResource(PackType.CLIENT_RESOURCES, resourceLocation));
    }
}
