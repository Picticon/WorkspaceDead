package workspacedead.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import workspacedead.WorkspaceDead;
import workspacedead.entity.SpawnEggs;
import workspacedead.item.ModItems;

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
        simpleItem(ModItems.DEADWATER_BUCKET.get());

        handheldItem(ModItems.DIRTY_SWORD.get());
        handheldItem(ModItems.DIRTY_BOW.get());

        simpleItem(ModItems.POOP.get());
        simpleItem(ModItems.SEEDED_POOP.get());
        simpleItem(ModItems.CREEPER_POOP.get());
        simpleItem(ModItems.SKELETON_POOP.get());
        simpleItem(ModItems.ZOMBIE_POOP.get());
        simpleItem(ModItems.ENDER_POOP.get());
        simpleItem(ModItems.SPIDER_POOP.get());
        simpleItem(ModItems.SLIME_POOP.get());
        simpleItem(ModItems.DRAGON_POOP.get());
        simpleItem(ModItems.WITCH_POOP.get());
        simpleItem(ModItems.BLAZE_POOP.get());
        simpleItem(ModItems.COW_POOP.get());
        simpleItem(ModItems.CHICKEN_POOP.get());
        simpleItem(ModItems.SHEEP_POOP.get());
        simpleItem(ModItems.PIG_POOP.get());
        simpleItem(ModItems.GOLEM_POOP.get());
        simpleItem(ModItems.VILLAGER_POOP.get());
        simpleItem(ModItems.POTATO_PORTAL_CATALYST.get());

        simpleItem(ModItems.ENDER_ROD.get());

        simpleItem(ModItems.PURIFYCRYSTAL.get());
        simpleItem(ModItems.WEAKPURIFYCRYSTAL.get());
        simpleItem(ModItems.PURIFYSHARD.get());

        simpleItem(ModItems.DEADCOOKIE.get());
        simpleItem(ModItems.DIRTYBREAD.get());

        simpleItem(ModItems.DIRTY_ARROW.get());
        simpleItem(ModItems.DEAD_ARROW.get());

        simpleItem(ModItems.POTATO_BOOTS.get());
        simpleItem(ModItems.POTATO_CHESTPLATE.get());
        simpleItem(ModItems.POTATO_HELMET.get());
        simpleItem(ModItems.POTATO_LEGGING.get());

        simpleItem(ModItems.KUBE_WAND.get());

        simpleItem(ModItems.GRASSYPOTATO_SEEDS.get());

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