package workspacedead.datagen;

import java.nio.file.Path;

import org.jetbrains.annotations.Nullable;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import workspacedead.block.ModBlocks;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
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
        this.tag(BlockTags.DOORS).add(ModBlocks.DEADDOOR.get(), ModBlocks.BURNTDOOR.get());
        this.tag(BlockTags.TRAPDOORS).add(ModBlocks.DEADTRAPDOOR.get(), ModBlocks.BURNTTRAPDOOR.get());
        this.tag(BlockTags.SLABS).add(ModBlocks.DEADSLAB.get(), ModBlocks.BURNTSLAB.get());
        this.tag(BlockTags.FENCES).add(ModBlocks.DEADFENCE.get(), ModBlocks.BURNTFENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.DEADFENCE_GATE.get(), ModBlocks.BURNTFENCE_GATE.get());
        this.tag(BlockTags.PLANKS).add(ModBlocks.BURNTPLANKS.get(), ModBlocks.DEADPLANKS.get());
        this.tag(BlockTags.STAIRS).add(ModBlocks.BURNTSTAIRS.get(), ModBlocks.DEADSTAIRS.get());

        this.tag(BlockTags.LOGS).add(ModBlocks.DEADLOG.get(), ModBlocks.DEADLOG_STRIPPED.get(), ModBlocks.DEADWOOD.get(), ModBlocks.DEADWOOD_STRIPPED.get());
        this.tag(BlockTags.LOGS_THAT_BURN).add(ModBlocks.DEADLOG.get(), ModBlocks.DEADLOG_STRIPPED.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.DEADLOG.get(), ModBlocks.DEADLOG_STRIPPED.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.DEADWOOD.get(), ModBlocks.DEADWOOD_STRIPPED.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.DEADPLANKS.get(), ModBlocks.BURNTPLANKS.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.DEADTRAPDOOR.get(), ModBlocks.BURNTTRAPDOOR.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.DEADSLAB.get(), ModBlocks.BURNTSLAB.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.DEADDOOR.get(), ModBlocks.BURNTDOOR.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DEADSTONE.get());
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.DEADSLATE.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.DEADSAND.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.DEADDIRT.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.DEADGRAVEL.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.DEADCLAY.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.POOP_O_LANTERN.get(), ModBlocks.CARVED_POOPBLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.POOPBLOCK.get(), ModBlocks.POOPBLOCK2X.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.DEADSAPLING.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.DEADLEAVES.get());
        //this.tag(SUPERMAN).add(ModBlocks.POOPBLOCK2X.get());
    }

    protected Path getPath(ResourceLocation p_126514_) {
        return this.generator.getOutputFolder().resolve("data/" + p_126514_.getNamespace() + "/tags/blocks/" + p_126514_.getPath() + ".json");
    }
}
