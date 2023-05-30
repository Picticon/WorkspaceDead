package workspacedead.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import workspacedead.WorkspaceDead;
import workspacedead.item.ModItems;

public class ModItemTagsProvider extends ItemTagsProvider {

    public static TagKey<Item> POOP = ItemTags.create(new ResourceLocation(WorkspaceDead.MOD_ID, "poop"));
    public static TagKey<Item> FIREPROOFITEM = ItemTags.create(new ResourceLocation(WorkspaceDead.MOD_ID, "fireproofitem"));


    public ModItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, String modId, //
            @org.jetbrains.annotations.Nullable net.minecraftforge.common.data.ExistingFileHelper existingFileHelper) {
        super(pGenerator, pBlockTagsProvider, modId, existingFileHelper);
    }

    //
    // REMEMBER TO RUN "RUNDATA" TO REGENERATE
    // REMEMBER TO RUN "RUNDATA" TO REGENERATE
    // REMEMBER TO RUN "RUNDATA" TO REGENERATE
    //

    @Override
    protected void addTags() {
        this.copy(BlockTags.LOGS, ItemTags.LOGS);
        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        this.copy(BlockTags.DOORS, ItemTags.DOORS);
        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);
        this.copy(BlockTags.SAPLINGS, ItemTags.SAPLINGS);

        this.tag(ItemTags.ARROWS).add(ModItems.DEAD_ARROW.get());

        this.tag(POOP)//
                .add(ModItems.POOP.get())//
                .add(ModItems.SEEDED_POOP.get())//
                .add(ModItems.ENDER_POOP.get())//
                .add(ModItems.SKELETON_POOP.get())//
                .add(ModItems.ZOMBIE_POOP.get())//
                .add(ModItems.CREEPER_POOP.get())//
                .add(ModItems.SPIDER_POOP.get())//
                .add(ModItems.SLIME_POOP.get())//
                .add(ModItems.DRAGON_POOP.get())//
                .add(ModItems.WITCH_POOP.get())//
                .add(ModItems.BLAZE_POOP.get())//
                .add(ModItems.COW_POOP.get())//
                .add(ModItems.PHANTOM_POOP.get())//
                .add(ModItems.PIG_POOP.get())//
                .add(ModItems.CHICKEN_POOP.get())//
                .add(ModItems.SHEEP_POOP.get())//
                .add(ModItems.GOLEM_POOP.get())//
                .add(ModItems.VILLAGER_POOP.get())//
        ;
        //this.tag(FIREPROOFITEM).add(ModItems.POOP.get());
    }
}
