package workspacedead.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import workspacedead.WorkspaceDead;
import workspacedead.block.BioMass.BioMassBlockRenderer;
import workspacedead.block.FullMetalAlchemiser.FullMetalAlchemiserBlockEntityRenderer;
import workspacedead.block.MrHanky.MrHankyBlockRenderer;
import workspacedead.block.Saturator.DesaturatorBlockEntityRenderer;
import workspacedead.block.Saturator.SaturatorBlockEntityRenderer;
import workspacedead.client.armor.PotatoArmorRenderer;
import workspacedead.client.renderer.mob.BoneGolemRenderer;
import workspacedead.client.renderer.mob.DraconicBlazeModel;
import workspacedead.client.renderer.mob.DraconicBlazeRenderer;
import workspacedead.client.renderer.mob.GrassyPotatoRenderer;
import workspacedead.client.renderer.mob.RottenPotatoRenderer;
import workspacedead.client.renderer.mob.SkeletonChickenRenderer;
import workspacedead.client.renderer.mob.SkeletonCowRenderer;
import workspacedead.client.renderer.mob.SkeletonPigRenderer;
import workspacedead.client.renderer.mob.SkeletonSheepRenderer;
import workspacedead.client.renderer.mob.SkeletonSlimeRenderer;
import workspacedead.client.renderer.mob.SkeletonSpiderRenderer;
import workspacedead.client.renderer.mob.KillerDonut.KillerDonutRenderer;
import workspacedead.client.renderer.projectile.DeadArrowRenderer;
import workspacedead.client.renderer.projectile.DirtyArrowRenderer;
import workspacedead.entity.ModEntityTypes;
import workspacedead.fluid.ModFluids;
import workspacedead.item.custom.PotatoArmorItem;
import workspacedead.particle.ModParticles;
import workspacedead.particle.custom.PurifyParticles;
import workspacedead.registry.MyBlockEntities;
import workspacedead.registry.MyBlocks;
import workspacedead.util.ModItemProperties;

@Mod.EventBusSubscriber(modid = WorkspaceDead.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModFluids.DEADWATER_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.DEADWATER_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.DEADWATER_FLOWING.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(ModFluids.URINE_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.URINE_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModFluids.URINE_FLOWING.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(MyBlocks.DEADLEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.DEADSAPLING.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(MyBlocks.DEADDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.DEADTRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.BURNTDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.BURNTTRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(MyBlocks.DEADGRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.GRASSYPOTATO_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.SPAWNEGG_PLANT.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(MyBlocks.POTATOPORTALBLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.SATURATOR_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.DESATURATOR_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(MyBlocks.FULLMETALALCHEMISER_BLOCK.get(), RenderType.translucent());

        ModItemProperties.addCustomItemProperties();
    }

    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntityTypes.DEAD_ARROW.get(), DeadArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.DIRTY_ARROW.get(), DirtyArrowRenderer::new);
        EntityRenderers.register(ModEntityTypes.SKELETONCOW.get(), SkeletonCowRenderer::new);
        EntityRenderers.register(ModEntityTypes.SKELETONCHICKEN.get(), SkeletonChickenRenderer::new);
        EntityRenderers.register(ModEntityTypes.SKELETONSHEEP.get(), SkeletonSheepRenderer::new);
        EntityRenderers.register(ModEntityTypes.SKELETONPIG.get(), SkeletonPigRenderer::new);
        EntityRenderers.register(ModEntityTypes.SKELETONSLIME.get(), SkeletonSlimeRenderer::new);
        EntityRenderers.register(ModEntityTypes.SKELETONSPIDER.get(), SkeletonSpiderRenderer::new);
        EntityRenderers.register(ModEntityTypes.GRASSYPOTATO.get(), GrassyPotatoRenderer::new);
        EntityRenderers.register(ModEntityTypes.ROTTENPOTATO.get(), RottenPotatoRenderer::new);
        EntityRenderers.register(ModEntityTypes.KILLER_DONUT.get(), KillerDonutRenderer::new);
        EntityRenderers.register(ModEntityTypes.BONE_GOLEM.get(), BoneGolemRenderer::new);
        EntityRenderers.register(ModEntityTypes.DRACONICBLAZE.get(), DraconicBlazeRenderer::new);
    }

    @SuppressWarnings({ "resource" }) // Minecraft.getInstance returns a singleton.... we don't close it.
    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.PURIFY_PARTICLES.get(),
                PurifyParticles.Factory::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DraconicBlazeModel.LAYER_LOCATION, DraconicBlazeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(MyBlockEntities.BIOMASS_BLOCK_ENTITY.get(), BioMassBlockRenderer::new);
        event.registerBlockEntityRenderer(MyBlockEntities.MRHANKY_BLOCK_ENTITY.get(), MrHankyBlockRenderer::new);
        event.registerBlockEntityRenderer(MyBlockEntities.SATURATOR_BLOCK_ENTITY.get(),
                SaturatorBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(MyBlockEntities.DESATURATOR_BLOCK_ENTITY.get(),
                DesaturatorBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(MyBlockEntities.FULLMETALALCHEMISER_BLOCK_ENTITY.get(),
                FullMetalAlchemiserBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(PotatoArmorItem.class, () -> new PotatoArmorRenderer());
    }
}
