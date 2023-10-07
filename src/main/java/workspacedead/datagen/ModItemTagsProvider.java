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
import workspacedead.registry.MyItems;

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

        this.tag(ItemTags.ARROWS).add(MyItems.DEAD_ARROW.get());

        this.tag(POOP)//
                .add(MyItems.POOP.get())//
                .add(MyItems.SEEDED_POOP.get())//
                .add(MyItems.ENDER_POOP.get())//
                .add(MyItems.SKELETON_POOP.get())//
                .add(MyItems.ZOMBIE_POOP.get())//
                .add(MyItems.CREEPER_POOP.get())//
                .add(MyItems.SPIDER_POOP.get())//
                .add(MyItems.SLIME_POOP.get())//
                .add(MyItems.DRAGON_POOP.get())//
                .add(MyItems.WITCH_POOP.get())//
                .add(MyItems.BLAZE_POOP.get())//
                .add(MyItems.COW_POOP.get())//
                .add(MyItems.PHANTOM_POOP.get())//
                .add(MyItems.PIG_POOP.get())//
                .add(MyItems.CHICKEN_POOP.get())//
                .add(MyItems.SHEEP_POOP.get())//
                .add(MyItems.GOLEM_POOP.get())//
                .add(MyItems.VILLAGER_POOP.get())//
        ;
        //this.tag(FIREPROOFITEM).add(ModItems.POOP.get());
    }
}
