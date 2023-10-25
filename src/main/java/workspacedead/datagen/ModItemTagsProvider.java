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
                .add(MyItems.POOP_SEEDED.get())//
                .add(MyItems.POOP_ENDERMAN.get())//
                .add(MyItems.POOP_SKELETON.get())//
                .add(MyItems.POOP_ZOMBIE.get())//
                .add(MyItems.POOP_CREEPER.get())//
                .add(MyItems.POOP_SPIDER.get())//
                .add(MyItems.POOP_SLIME.get())//
                .add(MyItems.POOP_DRAGON.get())//
                .add(MyItems.POOP_WITCH.get())//
                .add(MyItems.POOP_BLAZE.get())//
                .add(MyItems.POOP_COW.get())//
                .add(MyItems.POOP_PHANTOM.get())//
                .add(MyItems.POOP_PIG.get())//
                .add(MyItems.POOP_CHICKEN.get())//
                .add(MyItems.POOP_SHEEP.get())//
                .add(MyItems.POOP_IRON_GOLEM.get())//
                .add(MyItems.POOP_TURTLE.get())//
                .add(MyItems.POOP_WITHER.get())//
                .add(MyItems.POOP_WITHER_SKELETON.get())//
                .add(MyItems.POOP_ZOGLIN.get())//
                .add(MyItems.POOP_HOGLIN.get())//
                .add(MyItems.POOP_CAT.get())//
                .add(MyItems.POOP_BEE.get())//
                .add(MyItems.POOP_BAT.get())//
                .add(MyItems.POOP_AXOLOTL.get())//
                .add(MyItems.POOP_MAGMACUBE.get())//
                .add(MyItems.POOP_PIGLIN.get())//
                .add(MyItems.POOP_ZOMBIFIEDPIGLIN.get())//
                .add(MyItems.POOP_GHAST.get())//
                .add(MyItems.POOP_SHULKER.get())//
                .add(MyItems.POOP_MOOSHROOM.get())//
                .add(MyItems.POOP_SQUID.get())//
                .add(MyItems.POOP_GUARDIAN.get())//
        ;
        //this.tag(FIREPROOFITEM).add(ModItems.POOP.get());
    }
}
