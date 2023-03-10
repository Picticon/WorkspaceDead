package workspacedead;

import com.mojang.logging.LogUtils;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.GeckoLib;
import workspacedead.block.ModBlockEntities;
//import workspacedead.advancement.ModCriteriaTriggers;
//import workspacedead.advancement.ModAdvancements;
import workspacedead.block.ModBlocks;
import workspacedead.config.CommonConfig;
import workspacedead.datagen.ModItemTagsProvider;
import workspacedead.effect.ModEffects;
import workspacedead.entity.ModEntityTypes;
import workspacedead.entity.mob.AngryAnimals;
import workspacedead.entity.mob.SkeletonSlime;
import workspacedead.fluid.ModFluids;
import workspacedead.item.ModItems;
import workspacedead.particle.ModParticles;
import workspacedead.potion.ModPotions;
import workspacedead.sound.ModSounds;
import workspacedead.util.WDServer;
import workspacedead.world.feature.ModConfiguredFeatures;
import workspacedead.world.feature.ModPlacedFeatures;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WorkspaceDead.MOD_ID)
public class WorkspaceDead {
    public static final String MOD_ID = "workspacedead";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public WorkspaceDead() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModFluids.register(eventBus);

        ModEffects.register(eventBus);
        ModPotions.register(eventBus);

        ModParticles.register(eventBus);
        ModSounds.register(eventBus);

        ModEntityTypes.register(eventBus); // needs to go before "Items", because Items registers spawn eggs.
        ModBlockEntities.register(eventBus);

        ModPlacedFeatures.register(eventBus);
        ModConfiguredFeatures.register(eventBus);

        GeckoLib.initialize();

        eventBus.addListener(this::setup);
        // eventBus.addListener(this::clientSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "workspacedead-common.toml");

        MinecraftForge.EVENT_BUS.addListener(this::onEntitySpawn);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.SKELETONCOW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AngryAnimals::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKELETONCHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AngryAnimals::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKELETONSHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AngryAnimals::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKELETONPIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AngryAnimals::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKELETONSLIME.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonSlime::checkSlimeSpawnRules2);
            SpawnPlacements.register(ModEntityTypes.SKELETONSPIDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

            ComposterBlock.COMPOSTABLES.put(ModItems.POOP.get().asItem(), .5f);
            ComposterBlock.COMPOSTABLES.put(ModItems.COW_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(ModItems.CHICKEN_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(ModItems.SHEEP_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(ModItems.PIG_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(ModItems.CREEPER_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.SKELETON_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.ENDER_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.ZOMBIE_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.VILLAGER_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.SPIDER_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.GOLEM_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.DRAGON_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.WITCH_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(ModItems.BLAZE_POOP.get().asItem(), 1);

            // ModCriteriaTriggers.register();
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("workspacedead", "helloworld", () -> {
            LOGGER.info("Hello world from the MDK");
            return "Hello world";
        });
    }

    private void processIMC(final InterModProcessEvent event) {
        // Some example code to receive and process InterModComms from other mods
        // LOGGER.info("Got IMC {}", event.getIMCStream().
        // map(m->m.messageSupplier().get()).
        // collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        WDServer.staticServer = event.getServer();
        // Do something when the server starts
        // LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the
    // contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // Register a new block here
            // LOGGER.info("HELLO from Register Block");
        }
    }

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof ItemEntity) {
            var tagManager = ForgeRegistries.ITEMS.tags();
            var tag = tagManager.getTag(ModItemTagsProvider.FIREPROOFITEM);
            var item = ((ItemEntity) event.getEntity()).getItem().getItem();
            if (tag.contains(item))
                event.getEntity().setInvulnerable(true);

        }
    }
}
