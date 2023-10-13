package workspacedead;

import com.mojang.logging.LogUtils;

import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.GeckoLib;
import workspacedead.advancement.ModCriteriaTriggers;
import workspacedead.client.ClientSetup;
import workspacedead.config.CommonConfig;
import workspacedead.datagen.ModItemTagsProvider;
import workspacedead.effect.DeadInsideEffect;
import workspacedead.effect.ModEffects;
import workspacedead.effect.ModEnchantments;
import workspacedead.entity.ModEntityTypes;
import workspacedead.entity.mob.SkeletonAnimal;
import workspacedead.entity.mob.SkeletonSlime;
import workspacedead.entity.projectile.DeadArrow;
import workspacedead.entity.projectile.DirtyArrow;
import workspacedead.fluid.ModFluids;
import workspacedead.network.MyMessages;
import workspacedead.particle.ModParticles;
import workspacedead.potion.ModPotions;
import workspacedead.registry.MySounds;
import workspacedead.registry.MyBlockEntities;
import workspacedead.registry.MyBlocks;
import workspacedead.registry.MyContainers;
import workspacedead.registry.MyItems;
import workspacedead.registry.MyRecipes;
import workspacedead.util.Chatter;
import workspacedead.world.ModDimensions;
import workspacedead.world.ModPOIs;
import workspacedead.world.feature.ModConfiguredFeatures;
import workspacedead.world.feature.ModPlacedFeatures;
import workspacedead.world.structure.ModStructures;

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

        MyItems.register(eventBus);
        MyBlocks.register(eventBus);
        ModFluids.register(eventBus);
        MyContainers.register(eventBus);
        MyRecipes.register(eventBus);

        ModEffects.register(eventBus);
        ModEnchantments.register(eventBus);
        ModPotions.register(eventBus);

        ModParticles.register(eventBus);
        MySounds.register(eventBus);

        ModEntityTypes.register(eventBus); // needs to go before "Items", because Items registers spawn eggs.
        MyBlockEntities.register(eventBus);

        ModPOIs.register(eventBus);

        ModPlacedFeatures.register(eventBus);
        ModConfiguredFeatures.register(eventBus);
        ModDimensions.register();
        ModStructures.register(eventBus);

        GeckoLib.initialize();

        ModCriteriaTriggers.register();

        // eventBus.addListener(this::setup);
        // eventBus.addListener(this::clientSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> eventBus.addListener(ClientSetup::init));

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC, "workspacedead-common.toml");

        MinecraftForge.EVENT_BUS.addListener(this::onEntitySpawn);
        MinecraftForge.EVENT_BUS.addListener(this::addReloadListenerEvent);
    }

    private void setup(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {
            SpawnPlacements.register(ModEntityTypes.SKELETONCOW.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonAnimal::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKELETONCHICKEN.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonAnimal::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKELETONSHEEP.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonAnimal::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKELETONPIG.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonAnimal::checkAnimalSpawnRules);
            SpawnPlacements.register(ModEntityTypes.SKELETONSLIME.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SkeletonSlime::checkSlimeSpawnRules2);
            SpawnPlacements.register(ModEntityTypes.SKELETONSPIDER.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

            ComposterBlock.COMPOSTABLES.put(MyItems.POOP.get().asItem(), .5f);
            ComposterBlock.COMPOSTABLES.put(MyItems.COW_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(MyItems.PHANTOM_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(MyItems.CHICKEN_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(MyItems.SHEEP_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(MyItems.PIG_POOP.get().asItem(), .75f);
            ComposterBlock.COMPOSTABLES.put(MyItems.CREEPER_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.SKELETON_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.ENDER_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.ZOMBIE_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.VILLAGER_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.SPIDER_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.GOLEM_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.DRAGON_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.WITCH_POOP.get().asItem(), 1);
            ComposterBlock.COMPOSTABLES.put(MyItems.BLAZE_POOP.get().asItem(), 1);

            DispenserBlock.registerBehavior(MyItems.DIRTY_ARROW.get(), new AbstractProjectileDispenseBehavior() {
                protected Projectile getProjectile(Level level, Position position, ItemStack p_123409_) {
                    var arrow = new DirtyArrow(level, position);
                    arrow.pickup = AbstractArrow.Pickup.ALLOWED;
                    return arrow;
                }
            });

            DispenserBlock.registerBehavior(MyItems.DEAD_ARROW.get(), new AbstractProjectileDispenseBehavior() {
                protected Projectile getProjectile(Level level, Position position, ItemStack p_123409_) {
                    var arrow = new DeadArrow(level, position);
                    arrow.pickup = AbstractArrow.Pickup.ALLOWED;
                    return arrow;
                }
            });

        });
        MyMessages.register(); // supposedly does not work inside the enqueueWork call.
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
        Chatter.staticServer = event.getServer();
        CommonConfig.buildCaches();
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

    // Prevent items with custom tags from burning
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

    public void addReloadListenerEvent(AddReloadListenerEvent e) {
      DeadInsideEffect.buildCaches();  
    }

    // reload config caches when config file changes
    @SubscribeEvent
    static void onFileChange(final ModConfigEvent.Reloading configEvent) {
        if (configEvent.getConfig().getSpec() == CommonConfig.SPEC) {
            CommonConfig.buildCaches();
        }
    }
}
