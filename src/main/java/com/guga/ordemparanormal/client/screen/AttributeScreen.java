package com.guga.ordemparanormal.client.screen;

import com.guga.ordemparanormal.common.abilities.ParanormalAttribute;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;

public class AttributeScreen extends Screen {
    protected AttributeScreen(ParanormalAttribute attribute) {
        super(new TranslatableComponent(attribute.name));
    }
}
