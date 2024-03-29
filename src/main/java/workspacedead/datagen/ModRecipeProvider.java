package workspacedead.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import workspacedead.registry.MyBlocks;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        ShapedRecipeBuilder.shaped(MyBlocks.DEADDOOR.get(), 3).define('E', MyBlocks.DEADPLANKS.get()).pattern("EE")
                .pattern("EE").pattern("EE")
                .unlockedBy("has_dead_planks",
                        inventoryTrigger(ItemPredicate.Builder.item().of(MyBlocks.DEADPLANKS.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(MyBlocks.DEADTRAPDOOR.get(), 2).define('E', MyBlocks.DEADPLANKS.get())
                .pattern("EEE").pattern("EEE")
                .unlockedBy("has_dead_planks",
                        inventoryTrigger(ItemPredicate.Builder.item().of(MyBlocks.DEADPLANKS.get()).build()))
                .save(pFinishedRecipeConsumer);
    }
}