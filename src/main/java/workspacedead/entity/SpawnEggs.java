package workspacedead.entity;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
//import workspacedead.entity.mob.GrassyPotatoForgeSpawnEggItem;
import workspacedead.item.ModCreativeModeTab;

public class SpawnEggs {

    // CUSTOM MOBS //

    public static final DeferredRegister<Item> EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, WorkspaceDead.MOD_ID);
    public static final RegistryObject<Item> SKELETONCOW_SPAWNEGG = EGGS.register("skeletoncow_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SKELETONCOW, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SKELETONCHICKEN_SPAWNEGG = EGGS.register("skeletonchicken_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SKELETONCHICKEN, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SKELETONSHEEP_SPAWNEGG = EGGS.register("skeletonsheep_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SKELETONSHEEP, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SKELETONPIG_SPAWNEGG = EGGS.register("skeletonpig_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SKELETONPIG, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SKELETONSLIME_SPAWNEGG = EGGS.register("skeletonslime_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SKELETONSLIME, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> SKELETONSPIDER_SPAWNEGG = EGGS.register("skeletonspider_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.SKELETONSPIDER, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> BONE_GOLEM_SPAWNEGG = EGGS.register("bonegolem_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.BONE_GOLEM, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));
    public static final RegistryObject<Item> DRACONICBLAZE_SPAWNEGG = EGGS.register("draconicblaze_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.DRACONICBLAZE, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static final RegistryObject<Item> GRASSYPOTATO_SPAWN_EGG = EGGS.register("grassypotato_spawn_egg", //
            () -> new ForgeSpawnEggItem(ModEntityTypes.GRASSYPOTATO, 0x94898d, 0x3f3635, new Item.Properties().tab(ModCreativeModeTab.ITEMS_TAB)));

    public static void register(IEventBus eventBus) {
        EGGS.register(eventBus);
    }

}
