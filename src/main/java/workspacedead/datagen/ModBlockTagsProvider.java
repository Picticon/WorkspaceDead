package workspacedead.datagen;

import java.nio.file.Path;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import workspacedead.registry.MyBlocks;
import workspacedead.util.ModTags;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator pGenerator, String modId,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
    }

    //
    // REMEMBER TO RUN "RUNDATA" TO REGENERATE
    // REMEMBER TO RUN "RUNDATA" TO REGENERATE
    // REMEMBER TO RUN "RUNDATA" TO REGENERATE
    //

    // public static TagKey<Block> IGNORE_TILE = BlockTags.create(new
    // ResourceLocation(WorkspaceDead.MOD_ID, "ignore_tile"));

    @Override
    protected void addTags() {
        this.tag(BlockTags.DOORS).add(MyBlocks.DEADDOOR.get(), MyBlocks.BURNTDOOR.get());
        this.tag(BlockTags.TRAPDOORS).add(MyBlocks.DEADTRAPDOOR.get(), MyBlocks.BURNTTRAPDOOR.get());
        this.tag(BlockTags.SLABS).add(MyBlocks.DEADSLAB.get(), MyBlocks.BURNTSLAB.get());
        this.tag(BlockTags.FENCES).add(MyBlocks.DEADFENCE.get(), MyBlocks.BURNTFENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(MyBlocks.DEADFENCE_GATE.get(), MyBlocks.BURNTFENCE_GATE.get());
        this.tag(BlockTags.PLANKS).add(MyBlocks.BURNTPLANKS.get(), MyBlocks.DEADPLANKS.get());
        this.tag(BlockTags.STAIRS).add(MyBlocks.BURNTSTAIRS.get(), MyBlocks.DEADSTAIRS.get());

        this.tag(BlockTags.LOGS).add(MyBlocks.DEADLOG.get(), MyBlocks.DEADLOG_STRIPPED.get(),
                MyBlocks.DEADWOOD.get(), MyBlocks.DEADWOOD_STRIPPED.get());
        this.tag(BlockTags.LOGS_THAT_BURN).add(MyBlocks.DEADLOG.get(), MyBlocks.DEADLOG_STRIPPED.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(MyBlocks.DEADLOG.get(), MyBlocks.DEADLOG_STRIPPED.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(MyBlocks.DEADWOOD.get(), MyBlocks.DEADWOOD_STRIPPED.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(MyBlocks.DEADPLANKS.get(), MyBlocks.BURNTPLANKS.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(MyBlocks.DEADTRAPDOOR.get(), MyBlocks.BURNTTRAPDOOR.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(MyBlocks.DEADSLAB.get(), MyBlocks.BURNTSLAB.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(MyBlocks.DEADDOOR.get(), MyBlocks.BURNTDOOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.DEADSTONE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.DEADSLATE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.DEADORE.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MyBlocks.DEADSAND.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MyBlocks.DEADDIRT.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MyBlocks.DEADGRAVEL.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MyBlocks.DEADCLAY.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MyBlocks.POOP_O_LANTERN.get(), MyBlocks.CARVED_POOPBLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MyBlocks.POOPBLOCK.get(), MyBlocks.POOPBLOCK2X.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MyBlocks.DEAD_FARMLAND.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(MyBlocks.MUTATING_FARMLAND.get());
        this.tag(BlockTags.SAPLINGS).add(MyBlocks.DEADSAPLING.get());
        this.tag(BlockTags.LEAVES).add(MyBlocks.DEADLEAVES.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.YELLOW_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.BLACK_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.BLUE_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.BROWN_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.CYAN_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.GRAY_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.GREEN_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.LIGHTBLUE_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.LIGHTGRAY_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.LIME_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.MAGENTA_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.ORANGE_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.PINK_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.PURPLE_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.RED_GENERATOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MyBlocks.WHITE_GENERATOR.get());

        this.tag(ModTags.Blocks.POTATO_PORTAL_FRAME).add(MyBlocks.POTATOBLOCK.get());

        // this.tag(SUPERMAN).add(ModBlocks.POOPBLOCK2X.get());
    }

    protected Path getPath(ResourceLocation p_126514_) {
        return this.generator.getOutputFolder()
                .resolve("data/" + p_126514_.getNamespace() + "/tags/blocks/" + p_126514_.getPath() + ".json");
    }
}
