package workspacedead.recipe;

/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
//package vazkii.botania.common.crafting;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

//import vazkii.botania.api.recipe.StateIngredient;
//import vazkii.botania.common.helper.ItemNBTHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.*;

public class StateIngredientHelper {
	// public static StateIngredient of(Block block) {
	// 	return new StateIngredientBlock(block);
	// }

	// public static StateIngredient of(BlockState state) {
	// 	return new StateIngredientBlockState(state);
	// }

	public static StateIngredient of(TagKey<Block> tag) {
		return of(tag.location());
	}

	public static StateIngredient of(ResourceLocation id) {
		return new StateIngredientTag(id);
	}

	public static StateIngredient of(Collection<Block> blocks) {
		return new StateIngredientBlocks(blocks);
	}

	// Can't be 'of' because of type erasure.
	// public static StateIngredient compound(Collection<StateIngredient> ingredients) {
	// 	return new StateIngredientCompound(ingredients);
	// }

	// public static StateIngredient combine(StateIngredient firstIngredient, StateIngredient secondIngredient) {
	// 	List<StateIngredient> ingredients = new ArrayList<>();
	// 	// Flatten the ingredients
	// 	if (firstIngredient instanceof StateIngredientCompound compound) {
	// 		ingredients.addAll(compound.getIngredients());
	// 	} else {
	// 		ingredients.add(firstIngredient);
	// 	}
	// 	if (secondIngredient instanceof StateIngredientCompound compound) {
	// 		ingredients.addAll(compound.getIngredients());
	// 	} else {
	// 		ingredients.add(secondIngredient);
	// 	}

	// 	return new StateIngredientCompound(ingredients);
	// }

	// public static StateIngredient tagExcluding(TagKey<Block> tag, StateIngredient... excluded) {
	// 	return new StateIngredientTagExcluding(tag.location(), List.of(excluded));
	// }

	public static StateIngredient deserialize(JsonObject object) {
		switch (GsonHelper.getAsString(object, "type")) {
			case "tag":
				return new StateIngredientTag(new ResourceLocation(GsonHelper.getAsString(object, "tag")));
			 case "block":
			 	return new StateIngredientBlock(Registry.BLOCK.get(new ResourceLocation(GsonHelper.getAsString(object, "block"))));
			// case "state":
			// 	return new StateIngredientBlockState(readBlockState(object));
			case "blocks":
				List<Block> blocks = new ArrayList<>();
				for (JsonElement element : GsonHelper.getAsJsonArray(object, "blocks")) {
					blocks.add(Registry.BLOCK.get(new ResourceLocation(element.getAsString())));
				}
				return new StateIngredientBlocks(blocks);
			// case "tag_excluding":
			// 	ResourceLocation tag = new ResourceLocation(GsonHelper.getAsString(object, "tag"));
			// 	List<StateIngredient> ingr = new ArrayList<>();
			// 	for (JsonElement element : GsonHelper.getAsJsonArray(object, "exclude")) {
			// 		ingr.add(deserialize(GsonHelper.convertToJsonObject(element, "exclude entry")));
			// 	}
			// 	return new StateIngredientTagExcluding(tag, ingr);
			// case "compound":
			// 	List<StateIngredient> stateIngredients = new ArrayList<>();
			// 	for (JsonElement ingredient : GsonHelper.getAsJsonArray(object, "ingredients")) {
			// 		if (!ingredient.isJsonObject()) {
			// 			throw new JsonParseException("Unknown ingredient in compound state ingredient: " + ingredient);
			// 		}
			// 		stateIngredients.add(deserialize(ingredient.getAsJsonObject()));
			// 	}
			// 	return new StateIngredientCompound(stateIngredients);
			default:
				throw new JsonParseException("Unknown type!");
		}
	}

	/**
	 * Deserializes a state ingredient, but removes air from its data,
	 * and returns null if the ingredient only matched air.
	 */

	// @Nullable
	// public static StateIngredient tryDeserialize(JsonObject object) {
	// 	return clearTheAir(deserialize(object));
	// }

	// public static StateIngredient clearTheAir(StateIngredient ingredient) {
	// 	if (ingredient instanceof StateIngredientTag sit) {
	// 		if (sit.resolve().findAny().isEmpty()) {
	// 			return null;
	// 		}
	// 		return ingredient;
	// 	}
	// 	if (ingredient instanceof StateIngredientBlock || ingredient instanceof StateIngredientBlockState) {
	// 		if (ingredient.test(Blocks.AIR.defaultBlockState())) {
	// 			return null;
	// 		}
	// 	} else if (ingredient instanceof StateIngredientBlocks sib) {
	// 		Collection<Block> blocks = sib.blocks;
	// 		List<Block> list = new ArrayList<>(blocks);
	// 		if (list.removeIf(b -> b == Blocks.AIR)) {
	// 			if (list.size() == 0) {
	// 				return null;
	// 			}
	// 			return of(list);
	// 		}
	// 	} else if (ingredient instanceof StateIngredientCompound sic) {
	// 		List<StateIngredient> newIngredients = sic.getIngredients().stream().map(StateIngredientHelper::clearTheAir).filter(Objects::nonNull).toList();
	// 		if (newIngredients.isEmpty()) {
	// 			return null;
	// 		}
	// 		return compound(newIngredients);
	// 	}
	// 	return ingredient;
	// }

	public static StateIngredient read(FriendlyByteBuf buffer) {
		switch (buffer.readVarInt()) {
			case 0: // StateIngredientBlocks
				int count = buffer.readVarInt();
				Set<Block> set = new HashSet<>();
				for (int i = 0; i < count; i++) {
					int id = buffer.readVarInt();
					Block block = Registry.BLOCK.byId(id);
					set.add(block);
				}
				return new StateIngredientBlocks(set);
			// case 1:
			// 	return new StateIngredientBlock(Registry.BLOCK.byId(buffer.readVarInt()));
			// case 2:
			// 	return new StateIngredientBlockState(Block.stateById(buffer.readVarInt()));
			// case 3:
			// 	int ingredientCount = buffer.readVarInt();
			// 	Set<StateIngredient> ingredientSet = new HashSet<>();
			// 	for (int i = 0; i < ingredientCount; i++) {
			// 		ingredientSet.add(read(buffer));
			// 	}
			// 	return new StateIngredientCompound(ingredientSet);
			default:
				throw new IllegalArgumentException("Unknown input discriminator!");
		}
	}

	/**
	 * Writes data about the block state to the provided json object.
	 */
	// public static JsonObject serializeBlockState(BlockState state) {
	// 	CompoundTag nbt = NbtUtils.writeBlockState(state);
	// 	ItemNBTHelper.renameTag(nbt, "Name", "name");
	// 	ItemNBTHelper.renameTag(nbt, "Properties", "properties");
	// 	Dynamic<net.minecraft.nbt.Tag> dyn = new Dynamic<>(NbtOps.INSTANCE, nbt);
	// 	return dyn.convert(JsonOps.INSTANCE).getValue().getAsJsonObject();
	// }

	/**
	 * Reads the block state from the provided json object.
	 */
	// public static BlockState readBlockState(JsonObject object) {
	// 	CompoundTag nbt = (CompoundTag) new Dynamic<>(JsonOps.INSTANCE, object).convert(NbtOps.INSTANCE).getValue();
	// 	ItemNBTHelper.renameTag(nbt, "name", "Name");
	// 	ItemNBTHelper.renameTag(nbt, "properties", "Properties");
	// 	String name = nbt.getString("Name");
	// 	ResourceLocation id = ResourceLocation.tryParse(name);
	// 	if (id == null || !Registry.BLOCK.getOptional(id).isPresent()) {
	// 		throw new IllegalArgumentException("Invalid or unknown block ID: " + name);
	// 	}
	// 	return NbtUtils.readBlockState(nbt);
	// }

	@Deprecated
	@Nonnull
	public static List<ItemStack> toStackList(StateIngredient input) {
		return input.getDisplayedStacks();
	}
}
