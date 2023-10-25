package workspacedead.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.block.BioMass.BioMassBlockItem;
import workspacedead.block.MrHanky.MrHankyBlockItem;
//import workspacedead.block.animatedblock.AnimatedBlockItem;
import workspacedead.effect.ModEffects;
import workspacedead.fluid.ModFluids;
import workspacedead.item.ModArmorMaterials;
import workspacedead.item.ModCreativeModeTab;
import workspacedead.item.PoopItem;
import workspacedead.item.custom.*;
import net.minecraft.world.entity.EquipmentSlot;

public class MyItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            WorkspaceDead.MOD_ID);

    public static final RegistryObject<Item> DEADCOOKIE = ITEMS.register("deadcookie", //
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)//
                    .food((new FoodProperties.Builder()).alwaysEat().fast().nutrition(0).saturationMod(0)//
                            .effect(() -> new MobEffectInstance(ModEffects.DEADINSIDE.get(), 400, 0), 1F).build())));
    public static final RegistryObject<Item> DIRTYBREAD = ITEMS.register("dirtybread", //
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)//
                    .food((new FoodProperties.Builder()).nutrition(3).saturationMod(1)//
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 100, 0), .05F).build())));

    public static final RegistryObject<Item> POOP = ITEMS.register("poop",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_SEEDED = ITEMS.register("poop_seeded",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB), 6000));
    public static final RegistryObject<Item> POOP_SKELETON = ITEMS.register("poop_skeleton",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_CREEPER = ITEMS.register("poop_creeper",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_ZOMBIE = ITEMS.register("poop_zombie",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_ENDERMAN = ITEMS.register("poop_enderman",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_COW = ITEMS.register("poop_cow",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_CHICKEN = ITEMS.register("poop_chicken",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_SHEEP = ITEMS.register("poop_sheep",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_PIG = ITEMS.register("poop_pig",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_IRON_GOLEM = ITEMS.register("poop_iron_golem",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_SPIDER = ITEMS.register("poop_spider",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_VILLAGER = ITEMS.register("poop_villager",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_SLIME = ITEMS.register("poop_slime",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_DRAGON = ITEMS.register("poop_dragon",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_WITCH = ITEMS.register("poop_witch",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_BLAZE = ITEMS.register("poop_blaze",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_PHANTOM = ITEMS.register("poop_phantom",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    // new poop
    public static final RegistryObject<Item> POOP_TURTLE = ITEMS.register("poop_turtle",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_WITHER = ITEMS.register("poop_wither",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_WITHER_SKELETON = ITEMS.register("poop_wither_skeleton",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_ZOGLIN = ITEMS.register("poop_zoglin",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_HOGLIN = ITEMS.register("poop_hoglin",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_CAT = ITEMS.register("poop_cat",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_BEE = ITEMS.register("poop_bee",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_BAT = ITEMS.register("poop_bat",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_AXOLOTL = ITEMS.register("poop_axolotl",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_MAGMACUBE = ITEMS.register("poop_magmacube",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_PIGLIN = ITEMS.register("poop_piglin",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_ZOMBIFIEDPIGLIN = ITEMS.register("poop_zombifiedpiglin",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_GHAST = ITEMS.register("poop_ghast",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_SHULKER = ITEMS.register("poop_shulker",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_MOOSHROOM = ITEMS.register("poop_mooshroom",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_SQUID = ITEMS.register("poop_squid",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POOP_GUARDIAN = ITEMS.register("poop_guardian",
            () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    // end new poop

    public static final RegistryObject<Item> ENDER_ROD = ITEMS.register("ender_rod",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> ENDER_DUST = ITEMS.register("ender_dust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> DEAD_ORE_CHUNK = ITEMS.register("dead_ore_chunk",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> DANIDOM_CRYSTAL = ITEMS.register("danidom_crystal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> DANIDOM_SATURATED = ITEMS.register("danidom_saturated",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> RETDESON_CRYSTAL = ITEMS.register("retdeson_crystal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> RETDESON_SATURATED = ITEMS.register("retdeson_saturated",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> GLOD_CRYSTAL = ITEMS.register("glod_crystal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> GLOD_SATURATED = ITEMS.register("glod_saturated",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> CEPPRO_CRYSTAL = ITEMS.register("ceppro_crystal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> CEPPRO_SATURATED = ITEMS.register("ceppro_saturated",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> CALO_CRYSTAL = ITEMS.register("calo_crystal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> CALO_SATURATED = ITEMS.register("calo_saturated",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> EMELDAR_CRYSTAL = ITEMS.register("emeldar_crystal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> EMELDAR_SATURATED = ITEMS.register("emeldar_saturated",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> INRO_CRYSTAL = ITEMS.register("inro_crystal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> INRO_SATURATED = ITEMS.register("inro_saturated",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> LIPAS_CRYSTAL = ITEMS.register("lipas_crystal",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> LIPAS_SATURATED = ITEMS.register("lipas_saturated",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    // weakest
    public static final RegistryObject<Item> PURIFYSHARD = ITEMS.register("purifyshard",
            () -> new PurifyShardItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    // medium
    public static final RegistryObject<Item> WEAKPURIFYCRYSTAL = ITEMS.register("weakpurifycrystal",
            () -> new WeakPurifyCrystalItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    // full
    public static final RegistryObject<Item> PURIFYCRYSTAL = ITEMS.register("purifycrystal",
            () -> new PurifyCrystalItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> DEADWATER_BUCKET = ITEMS.register("deadwater_bucket",
            () -> new BucketItem(ModFluids.DEADWATER_FLUID,
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> URINE_BUCKET = ITEMS.register("urine_bucket",
            () -> new BucketItem(ModFluids.URINE_FLUID,
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> DIRTY_SWORD = //
            ITEMS.register("dirtysword", () -> //
            new DirtySwordItem(Tiers.WOOD, 3, -2.4F, new Item.Properties() //
                    .tab(ModCreativeModeTab.ITEMS_TAB).durability(50)));
    public static final RegistryObject<Item> DIRTY_BOW = ITEMS.register("dirtybow",
            () -> new SlingItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB).durability(100)));

    public static final RegistryObject<Item> DIRTY_ARROW = ITEMS.register("dirtyarrow",
            () -> new DirtyArrowItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> DEAD_ARROW = ITEMS.register("deadarrow",
            () -> new DeadArrowItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> BIOMASS_BLOCK_ITEM = ITEMS.register("biomass_block", //
            () -> new BioMassBlockItem(MyBlocks.BIOMASS_BLOCK.get(),
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> MRHANKY_BLOCK_ITEM = ITEMS.register("mrhanky_block", //
            () -> new MrHankyBlockItem(MyBlocks.MRHANKY_BLOCK.get(),
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    // public static final RegistryObject<Item> GRASSYPOTATO_SEED =
    // ITEMS.register("grassypotato_seeds", //
    // () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> GRASSYPOTATO_SEEDS = ITEMS.register("grassypotato_seeds", //
            () -> new ItemNameBlockItem(MyBlocks.GRASSYPOTATO_PLANT.get(),
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> PLUNGER = ITEMS.register("plunger",
            () -> new PlungerItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SPAWNEGG_SEEDS_EMPTY = ITEMS.register("spawnegg_seeds_empty",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SPAWNEGG_SEEDS = ITEMS.register("spawnegg_seeds", //
            () -> new SpawnEggSeedsItem(MyBlocks.SPAWNEGG_PLANT.get(),
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> DEADGRASS_SCRAPS = ITEMS.register("deadgrass_scraps", //
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> POTATO_HELMET = ITEMS.register("potato_helmet",
            () -> new PotatoArmorItem(ModArmorMaterials.POTATO, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POTATO_CHESTPLATE = ITEMS.register("potato_chestplate",
            () -> new PotatoArmorItem(ModArmorMaterials.POTATO, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POTATO_LEGGING = ITEMS.register("potato_leggings",
            () -> new PotatoArmorItem(ModArmorMaterials.POTATO, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> POTATO_BOOTS = ITEMS.register("potato_boots",
            () -> new PotatoArmorItem(ModArmorMaterials.POTATO, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> KUBE_WAND = ITEMS.register("kube_wand",
            () -> new KubeWand(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<PotatoPortalCatalystItem> POTATO_PORTAL_CATALYST = ITEMS
            .register("potato_portal_catalyst", PotatoPortalCatalystItem::new);

    public static final RegistryObject<Item> YELLOW_GENERATOR_BLOCK_ITEM = ITEMS.register("yellow_gen_block", //
            () -> new BlockItem(MyBlocks.YELLOW_GENERATOR.get(),
                    new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}