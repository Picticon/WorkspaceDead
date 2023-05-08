package workspacedead.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import workspacedead.WorkspaceDead;

public class ModTags {
    public static class Items {
        // public static final TagKey<Item> CITRINE_GEMS = forgeTag("gems/citrine");
        // public static final TagKey<

        // private static TagKey<Item> tag(String name) {
        // return ItemTags.create(new ResourceLocation(WorkspaceDead.MOD_ID, name));
        // }

        // private static TagKey<Item> forgeTag(String name) {
        // return ItemTags.create(new ResourceLocation("forge", name));
        // }
    }

    public static class Blocks {
        // public static final TagKey<Block> DOWSING_ROD_VALUABLES =
        // tag("dowsing_rod_valuables");
        // public static final TagKey<Block> PORTAL_FRAME_BLOCKS =
        // tag("portal_frame_blocks");
        public static final TagKey<Block> DEAD_ANIMAL_SPAWN_BLOCKS = tag("dead_animal_spawn_blocks");
        public static TagKey<Block> POTATO_PORTAL_FRAME = BlockTags
                .create(new ResourceLocation(WorkspaceDead.MOD_ID, "potato_portal_frame"));

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(WorkspaceDead.MOD_ID, name));
        }

        // private static TagKey<Block> forgeTag(String name) {
        // return BlockTags.create(new ResourceLocation("forge", name));
        // }
    }
}
