package com.guga.ordemparanormal.client;

import com.guga.ordemparanormal.api.ParanormalElement;
import com.guga.ordemparanormal.api.util.EntityUtil;
import com.guga.ordemparanormal.client.gui.*;
import com.guga.ordemparanormal.client.layers.BidenteRiptideLayer;
import com.guga.ordemparanormal.client.layers.CurseArmorLayer;
import com.guga.ordemparanormal.client.screen.AttributeScreen;
import com.guga.ordemparanormal.common.block.blocks.AltarTranscender;
import com.guga.ordemparanormal.common.item.RitualItem;
import com.guga.ordemparanormal.core.OrdemParanormal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = OrdemParanormal.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        if (event.getEntity().level.getBlockState(event.getHitVec().getBlockPos()).getBlock() instanceof AltarTranscender){
            if (event.getSide() == LogicalSide.CLIENT && !(event.getItemStack().getItem() instanceof RitualItem)) {
                Minecraft.getInstance().setScreen(new AttributeScreen());
            }
            event.setUseItem(Event.Result.DENY);
        }
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    @OnlyIn(Dist.CLIENT)
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        addCurseArmorLayers(event, ParanormalElement.SANGUE);
        addCurseArmorLayers(event, ParanormalElement.CONHECIMENTO);
        addCurseArmorLayers(event, ParanormalElement.ENERGIA);
        addCurseArmorLayers(event, ParanormalElement.MORTE);
        EntityUtil.addPlayerLayer(event, "slim", (r) -> new BidenteRiptideLayer(r, event.getEntityModels()));
        EntityUtil.addPlayerLayer(event, "default", (r) -> new BidenteRiptideLayer(r, event.getEntityModels()));
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void addCurseArmorLayers(EntityRenderersEvent.AddLayers event, ParanormalElement element){
        Function zombie = (r) -> new CurseArmorLayer((RenderLayerParent) r,
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR)),
                element
        );
        Function piglin = (r) -> new CurseArmorLayer((RenderLayerParent) r,
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.PIGLIN_INNER_ARMOR)),
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.PIGLIN_OUTER_ARMOR)),
                element
        );
        Function skeleton = (r) -> new CurseArmorLayer((RenderLayerParent) r,
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.SKELETON_INNER_ARMOR)),
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.SKELETON_OUTER_ARMOR)),
                element
        );

        EntityUtil.addPlayerLayer(event, "default", (r) -> new CurseArmorLayer(r,
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)),
                element
        ));
        EntityUtil.addPlayerLayer(event, "slim", (r) -> new CurseArmorLayer(r,
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.PLAYER_SLIM_INNER_ARMOR)),
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.PLAYER_SLIM_OUTER_ARMOR)),
               element
        ));
        EntityUtil.addLayer(event, EntityType.ARMOR_STAND, (r) -> new CurseArmorLayer(r,
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.ARMOR_STAND_INNER_ARMOR)),
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.ARMOR_STAND_OUTER_ARMOR)),
                element
        ));
        EntityUtil.addLayer(event, EntityType.ZOMBIE_VILLAGER, (r) -> new CurseArmorLayer(r,
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.ZOMBIE_VILLAGER_INNER_ARMOR)),
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.ZOMBIE_VILLAGER_OUTER_ARMOR)),
                element
        ));
        EntityUtil.addLayer(event, EntityType.ZOMBIE, zombie);
        EntityUtil.addLayer(event, EntityType.HUSK, zombie);
        EntityUtil.addLayer(event, EntityType.DROWNED, zombie);
        EntityUtil.addLayer(event, EntityType.SKELETON, skeleton);
        EntityUtil.addLayer(event, EntityType.WITHER_SKELETON, (r) -> new CurseArmorLayer(r,
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.WITHER_SKELETON_INNER_ARMOR)),
                new HumanoidModel<>(event.getEntityModels().bakeLayer(ModelLayers.WITHER_SKELETON_OUTER_ARMOR)),
                element));
        EntityUtil.addLayer(event, EntityType.STRAY, skeleton);
        EntityUtil.addLayer(event, EntityType.PIGLIN, piglin);
        EntityUtil.addLayer(event, EntityType.PIGLIN_BRUTE, piglin);
    }
    @SubscribeEvent
    public static void registerOverlays(final RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("nex_hud", GuiNexHUD.OVERLAY);
        event.registerAboveAll("powers_hud", GuiActivePowersHUD.OVERLAY);
        event.registerAbove(VanillaGuiOverlay.FOOD_LEVEL.id(), "effort_hud", GuiEffortHUD.OVERLAY);
        event.registerAbove(VanillaGuiOverlay.PLAYER_HEALTH.id(), "death_health_hud", GuiDeathHeartsHUD.OVERLAY);
        event.registerAbove(VanillaGuiOverlay.ARMOR_LEVEL.id(), "blood_armor_hud", GuiBloodArmorHUD.OVERLAY);
    }
}
