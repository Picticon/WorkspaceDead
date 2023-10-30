package workspacedead.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import workspacedead.WorkspaceDead;
import workspacedead.block.generators.BasePowergenScreen;
import workspacedead.registry.MyContainers;

@Mod.EventBusSubscriber(modid = WorkspaceDead.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            MenuScreens.register(MyContainers.YELLOW_POWERGEN_CONTAINER.get(), BasePowergenScreen::new);
            //ItemBlockRenderTypes.setRenderLayer(MyBlocks.YELLOW_GENERATOR.get(), RenderType.translucent());
            //PowergenRenderer.register();
        });

        // amount of power when hovering over desaturators
        OverlayRegistry.registerOverlayAbove(ForgeIngameGui.HOTBAR_ELEMENT,"Workspace Dead Block Info", GuiEntityInfoHUD.OVERLAY);

        // MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
        // KeyBindings.init();
        // OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "name", ManaOverlay.HUD_MANA);
    }

    // @SubscribeEvent
    // public static void onModelRegistryEvent(ModelRegistryEvent event) {
    //     ModelLoaderRegistry.registerLoader(GeneratorModelLoader.GENERATOR_LOADER, new GeneratorModelLoader());
    // }

    // @SubscribeEvent
    // public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
    //     event.registerLayerDefinition(ThiefModel.THIEF_LAYER, ThiefModel::createBodyLayer);
    // }

    // @SubscribeEvent
    // public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
    //     event.registerEntityRenderer(Registration.THIEF.get(), ThiefRenderer::new);
    // }

    // @SubscribeEvent
    // public static void onTextureStitch(TextureStitchEvent.Pre event) {
    //     if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
    //         return;
    //     }
    //     event.addSprite(PowergenRenderer.HALO);
    // }
}
