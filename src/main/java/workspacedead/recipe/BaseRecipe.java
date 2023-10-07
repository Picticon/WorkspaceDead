package workspacedead.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public abstract class BaseRecipe implements Recipe<RecipeWrapper> {

    public final ResourceLocation name;

    public BaseRecipe(ResourceLocation name) {
        this.name = name;
    }

    @Override
    public boolean matches(RecipeWrapper inv, Level levelIn) {
        return true;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    public ResourceLocation getId() {
        return this.name;
    }

}