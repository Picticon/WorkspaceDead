// package workspacedead.recipe;

// import com.google.gson.JsonElement;
// import java.util.Set;
// import com.google.common.collect.Sets;
// import net.minecraft.tags.Tag;

// import net.minecraft.advancements.critereon.NbtPredicate;
// import net.minecraft.advancements.critereon.BlockPredicate;
// import net.minecraft.advancements.critereon.StatePropertiesPredicate;
// import net.minecraft.network.FriendlyByteBuf;
// import net.minecraft.resources.ResourceLocation;
// import net.minecraft.world.level.block.Block;
// import net.minecraftforge.registries.ForgeRegistries;

// public class BlockPredicateSerializer {

//     public static BlockPredicate fromJson(JsonElement jsonElement) {
//         if (jsonElement != null && jsonElement.isJsonPrimitive()) {
//             String id = jsonElement.getAsString();
//             Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(id));
//             return new BlockPredicate(null, Set.of(block), StatePropertiesPredicate.ANY, NbtPredicate.ANY);
//         }
//         return BlockPredicate.fromJson(jsonElement);
//     }

//     public static BlockPredicate fromNetwork(FriendlyByteBuf buf) {
//         int blockCount = buf.readVarInt();
//         if (blockCount == -1) {
//             return BlockPredicate.ANY;
//         }
//         Set<Block> blocks = null;
//         if (blockCount > 0) {
//             blocks = Sets.newHashSet();
//             for (int i = 0; i < blockCount; i++) {
//                 blocks.add(ForgeRegistries.BLOCKS.getValue(buf.readResourceLocation()));
//             }
//         }
//         Tag<Block> tag = null;
//         ResourceLocation tagId = buf.readResourceLocation();
//         if (tagId != null) {
//             tag = SerializationTags.getInstance().getOrEmpty(Registry.BLOCK_REGISTRY).getTag(tagId);
//         }
//         StatePropertiesPredicate propertiesPredicate = PropertiesPredicateHelper.fromNetwork(pBuffer);
//         NbtPredicate nbtPredicate = pBuffer.readBoolean() ? NBT_PREDICATE_DUMMY : NbtPredicate.ANY;
//         return new BlockPredicate(tag, blocks, propertiesPredicate, nbtPredicate);
//     }
//     public static void toNetwork(BlockPredicate predicate, FriendlyByteBuf pBuffer) {
// 		if (predicate == BlockPredicate.ANY) {
// 			pBuffer.writeVarInt(-1);
// 			return;
// 		}
// 		BlockPredicateAccess access = (BlockPredicateAccess) predicate;
// 		Set<Block> blocks = access.getBlocks();
// 		if (blocks == null) {
// 			pBuffer.writeVarInt(0);
// 		} else {
// 			pBuffer.writeVarInt(blocks.size());
// 			for (Block block : blocks) {
// 				LUtil.writeRegistryId(ForgeRegistries.BLOCKS, block, pBuffer);
// 			}
// 		}
// 		ResourceLocation tagId = null;
// 		Tag<Block> tag = access.getTag();
// 		if (tag != null)
// 			tagId = SerializationTags.getInstance().getOrEmpty(Registry.BLOCK_REGISTRY).getId(tag);
// 		LUtil.writeNullableRL(tagId, pBuffer);
// 		PropertiesPredicateHelper.toNetwork(access.getProperties(), pBuffer);
// 		NbtPredicate nbtPredicate = access.getNbt();
// 		pBuffer.writeBoolean(nbtPredicate != NbtPredicate.ANY);
// 	}    

// }
