// package workspacedead.recipe;

// import com.google.common.base.Objects;
// import com.google.common.base.Predicate;
// import com.google.common.collect.ImmutableSet;
// import com.google.gson.JsonArray;
// import com.google.gson.JsonObject;
// import java.util.List;
// import java.util.Collection;
// import java.util.stream.Collectors;

// import javax.annotation.Nonnull;

// import net.minecraft.advancements.critereon.BlockPredicate;
// import net.minecraft.core.Registry;
// import net.minecraft.network.FriendlyByteBuf;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.world.item.Items;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.state.BlockState;
// import net.minecraftforge.registries.ForgeRegistries;

// public class BlockStateList extends  BlockPredicate {
//     protected final ImmutableSet<Block> blocks;

//     public BlockStateList(Collection<Block> blocks) {
//         this.blocks = ImmutableSet.copyOf(blocks);
//     }

//     @Override
//     public boolean test(BlockState state) {
//         return blocks.contains(state.getBlock());
//     }

//     // @Override
//     // public BlockState pick(Random random) {
//     //     return blocks.asList().get(random.nextInt(blocks.size())).defaultBlockState();
//     // }

//     public JsonObject serialize() {
//         JsonObject object = new JsonObject();
//         object.addProperty("type", "blocks");
//         JsonArray array = new JsonArray();
//         for (Block block : blocks) {
//             array.add(ForgeRegistries.BLOCKS.getKey(block).toString());
//         }
//         object.add("blocks", array);
//         return object;
//     }

//     public void write(FriendlyByteBuf buffer) {
//         List<Block> blocks = getBlocks();
//         buffer.writeVarInt(0);
//         buffer.writeVarInt(blocks.size());
//         for (Block block : blocks) {
//             buffer.writeVarInt(Registry.BLOCK.getId(block));
//         }
//     }

//     public List<ItemStack> getDisplayedItemStacks() {
//         return blocks.stream()
//                 .filter(b -> b.asItem() != Items.AIR)
//                 .map(ItemStack::new)
//                 .collect(Collectors.toList());
//     }

//     public List<BlockState> getDisplayedBlockStates() {
//         return blocks.stream().map(Block::defaultBlockState).collect(Collectors.toList());
//     }

//     @Nonnull
//     public List<Block> getBlocks() {
//         return blocks.asList();
//     }

//     @Override
//     public String toString() {
//         return "BlockStateList{" + blocks.toString() + "}";
//     }

//     @Override
//     public boolean equals(Object o) {
//         if (this == o) {
//             return true;
//         }
//         if (o == null || getClass() != o.getClass()) {
//             return false;
//         }
//         return blocks.equals(((BlockStateList) o).blocks);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hashCode(blocks);
//     }

//     @Override
//     public boolean apply(BlockState input) {
//         return test(input); // unsure about this
//     }

// }
