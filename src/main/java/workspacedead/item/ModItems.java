package workspacedead.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.block.ModBlocks;
import workspacedead.block.BioMass.BioMassBlockItem;
//import workspacedead.block.animatedblock.AnimatedBlockItem;
import workspacedead.effect.ModEffects;
import workspacedead.fluid.ModFluids;
import workspacedead.item.custom.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WorkspaceDead.MOD_ID);

    public static final RegistryObject<Item> DEADCOOKIE = ITEMS.register("deadcookie", //
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)//
                    .food((new FoodProperties.Builder()).alwaysEat().fast().nutrition(0).saturationMod(0)//
                            .effect(() -> new MobEffectInstance(ModEffects.DEADINSIDE.get(), 200, 0), 1F).build())));
    public static final RegistryObject<Item> DIRTYBREAD = ITEMS.register("dirtybread", //
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)//
                    .food((new FoodProperties.Builder()).nutrition(3).saturationMod(1)//
                            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 100, 0), .05F).build())));

    // public static final RegistryObject<Item> DEADWHEATSEED =
    // ITEMS.register("deadwheatseed", () -> new Item(new
    // Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    // public static final RegistryObject<Item> DEADWHEAT =
    // ITEMS.register("deadwheat", () -> new Item(new
    // Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> POOP = ITEMS.register("poop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SEEDED_POOP = ITEMS.register("seededpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SKELETON_POOP = ITEMS.register("skeletonpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> CREEPER_POOP = ITEMS.register("creeperpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> ZOMBIE_POOP = ITEMS.register("zombiepoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> ENDER_POOP = ITEMS.register("enderpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> COW_POOP = ITEMS.register("cowpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> CHICKEN_POOP = ITEMS.register("chickenpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SHEEP_POOP = ITEMS.register("sheeppoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> PIG_POOP = ITEMS.register("pigpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> GOLEM_POOP = ITEMS.register("golempoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SPIDER_POOP = ITEMS.register("spiderpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> VILLAGER_POOP = ITEMS.register("villagerpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SLIME_POOP = ITEMS.register("slimepoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> DRAGON_POOP = ITEMS.register("dragonpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> WITCH_POOP = ITEMS.register("witchpoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> BLAZE_POOP = ITEMS.register("blazepoop", () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> ENDER_ROD = ITEMS.register("ender_rod", () -> new Item(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> PURIFYCRYSTAL = ITEMS.register("purifycrystal", () -> new PurifyCrystalItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> WEAKPURIFYCRYSTAL = ITEMS.register("weakpurifycrystal", () -> new WeakPurifyCrystalItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> PURIFYSHARD = ITEMS.register("purifyshard", () -> new PurifyShardItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> DEADWATER_BUCKET = ITEMS.register("deadwater_bucket", () -> new BucketItem(ModFluids.DEADWATER_FLUID, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB).stacksTo(1)));

    public static final RegistryObject<Item> DIRTY_SWORD = //
            ITEMS.register("dirtysword", () -> //
            new DirtySwordItem(Tiers.WOOD, 3, -2.4F, new Item.Properties() //
                    .tab(ModCreativeModeTab.ITEMS_TAB).durability(50)));
    public static final RegistryObject<Item> DIRTY_BOW = ITEMS.register("dirtybow", () -> new SlingItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB).durability(100)));

    public static final RegistryObject<Item> DIRTY_ARROW = ITEMS.register("dirtyarrow", () -> new DirtyArrowItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> DEAD_ARROW = ITEMS.register("deadarrow", () -> new DeadArrowItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> BIOMASS_BLOCK_ITEM = ITEMS.register("biomass_block", //
            () -> new BioMassBlockItem(ModBlocks.BIOMASS_BLOCK.get(), new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    // public static final RegistryObject<Item> GRASSYPOTATO_SEED = ITEMS.register("grassypotato_seeds", //
    //         () -> new PoopItem(new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> GRASSYPOTATO_SEEDS = ITEMS.register("grassypotato_seeds", //
            () -> new ItemNameBlockItem(ModBlocks.GRASSYPOTATO_PLANT.get(), new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    // public static final RegistryObject<Item> ANIMATED_BLOCK_ITEM =
    // ITEMS.register("animated_block",
    // () -> new AnimatedBlockItem(ModBlocks.ANIMATED_BLOCK.get(),
    // new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}