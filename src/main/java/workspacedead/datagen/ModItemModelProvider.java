package workspacedead.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import workspacedead.WorkspaceDead;
import workspacedead.entity.SpawnEggs;
import workspacedead.registry.MyItems;

///
/// This class creates MODEL files for ITEMS
/// This can be used instead of 100 JSON files
///

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WorkspaceDead.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(MyItems.DEADWATER_BUCKET.get());
        simpleItem(MyItems.URINE_BUCKET.get());

        handheldItem(MyItems.DIRTY_SWORD.get());
        handheldItem(MyItems.DIRTY_BOW.get());

        simpleItem(MyItems.POOP.get());
        simpleItem(MyItems.SEEDED_POOP.get());
        simpleItem(MyItems.CREEPER_POOP.get());
        simpleItem(MyItems.SKELETON_POOP.get());
        simpleItem(MyItems.ZOMBIE_POOP.get());
        simpleItem(MyItems.ENDER_POOP.get());
        simpleItem(MyItems.SPIDER_POOP.get());
        simpleItem(MyItems.SLIME_POOP.get());
        simpleItem(MyItems.DRAGON_POOP.get());
        simpleItem(MyItems.WITCH_POOP.get());
        simpleItem(MyItems.BLAZE_POOP.get());
        simpleItem(MyItems.COW_POOP.get());
        simpleItem(MyItems.PHANTOM_POOP.get());
        simpleItem(MyItems.CHICKEN_POOP.get());
        simpleItem(MyItems.SHEEP_POOP.get());
        simpleItem(MyItems.PIG_POOP.get());
        simpleItem(MyItems.GOLEM_POOP.get());
        simpleItem(MyItems.VILLAGER_POOP.get());
        simpleItem(MyItems.POTATO_PORTAL_CATALYST.get());
        simpleItem(MyItems.DEADGRASS_SCRAPS.get());
        
        simpleItem(MyItems.PLUNGER.get());
        simpleItem(MyItems.SPAWNEGG_SEEDS_EMPTY.get());
        //simpleItem(ModItems.SPAWNEGG_SEEDS.get()); // manual

        simpleItem(MyItems.ENDER_ROD.get());
        simpleItem(MyItems.ENDER_DUST.get());

        simpleItem(MyItems.DEAD_ORE_CHUNK.get());

        simpleItem(MyItems.PURIFYCRYSTAL.get());
        simpleItem(MyItems.WEAKPURIFYCRYSTAL.get());
        simpleItem(MyItems.PURIFYSHARD.get());

        simpleItem(MyItems.DEADCOOKIE.get());
        simpleItem(MyItems.DIRTYBREAD.get());

        simpleItem(MyItems.DIRTY_ARROW.get());
        simpleItem(MyItems.DEAD_ARROW.get());

        simpleItem(MyItems.POTATO_BOOTS.get());
        simpleItem(MyItems.POTATO_CHESTPLATE.get());
        simpleItem(MyItems.POTATO_HELMET.get());
        simpleItem(MyItems.POTATO_LEGGING.get());

        simpleItem(MyItems.KUBE_WAND.get());

        simpleItem(MyItems.GRASSYPOTATO_SEEDS.get());

        simpleItem(MyItems.DANIDOM_CRYSTAL.get());
        simpleItem(MyItems.DANIDOM_SATURATED.get());
        
        simpleItem(MyItems.RETDESON_CRYSTAL.get());
        simpleItem(MyItems.RETDESON_SATURATED.get());
        simpleItem(MyItems.GLOD_CRYSTAL.get());
        simpleItem(MyItems.GLOD_SATURATED.get());
        simpleItem(MyItems.EMELDAR_CRYSTAL.get());
        simpleItem(MyItems.EMELDAR_SATURATED.get());
        simpleItem(MyItems.CEPPRO_CRYSTAL.get());
        simpleItem(MyItems.CEPPRO_SATURATED.get());
        simpleItem(MyItems.CALO_CRYSTAL.get());
        simpleItem(MyItems.CALO_SATURATED.get());
        
        simpleItem(MyItems.LIPAS_CRYSTAL.get());
        simpleItem(MyItems.LIPAS_SATURATED.get());
        simpleItem(MyItems.INRO_CRYSTAL.get());
        simpleItem(MyItems.INRO_SATURATED.get());


        simpleEgg(SpawnEggs.SKELETONCHICKEN_SPAWNEGG.get());
        simpleEgg(SpawnEggs.SKELETONCOW_SPAWNEGG.get());
        simpleEgg(SpawnEggs.SKELETONPIG_SPAWNEGG.get());
        simpleEgg(SpawnEggs.SKELETONSHEEP_SPAWNEGG.get());
        simpleEgg(SpawnEggs.SKELETONSLIME_SPAWNEGG.get());
        simpleEgg(SpawnEggs.SKELETONSPIDER_SPAWNEGG.get());
        simpleEgg(SpawnEggs.GRASSYPOTATO_SPAWN_EGG.get());
        simpleEgg(SpawnEggs.ROTTENPOTATO_SPAWN_EGG.get());
        simpleEgg(SpawnEggs.BONE_GOLEM_SPAWNEGG.get());
        simpleEgg(SpawnEggs.DRACONICBLAZE_SPAWNEGG.get());
    }

    private ItemModelBuilder simpleEgg(Item item) {
        return withExistingParent(item.getRegistryName().getPath(), new ResourceLocation("item/template_spawn_egg"));
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(), new ResourceLocation("item/generated")).texture(
                "layer0", new ResourceLocation(WorkspaceDead.MOD_ID, "item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(), new ResourceLocation("item/handheld")).texture(
                "layer0", new ResourceLocation(WorkspaceDead.MOD_ID, "item/" + item.getRegistryName().getPath()));
    }
}