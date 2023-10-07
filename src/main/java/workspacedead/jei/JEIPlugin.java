package workspacedead.jei;

import java.util.List;
import java.util.Objects;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import workspacedead.WorkspaceDead;
import workspacedead.recipe.FullMetalAlchemiserRecipe;
import workspacedead.recipe.SaturatorRecipe;
import workspacedead.registry.MyBlocks;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    public static final RecipeType<SaturatorRecipe> SATURATOR_CRAFT = RecipeType.create(WorkspaceDead.MOD_ID,
            "saturator_craft", SaturatorRecipe.class);
    public static final RecipeType<FullMetalAlchemiserRecipe> FULLMETALALCHEMISER_CRAFT = RecipeType.create(WorkspaceDead.MOD_ID,
            "fullmetalalchemiser_craft", FullMetalAlchemiserRecipe.class);
    private static final ResourceLocation PLUGIN_ID = new ResourceLocation(WorkspaceDead.MOD_ID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        var helper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new SaturatorRecipeCategory(helper));
        registry.addRecipeCategories(new FullMetalAlchemiserRecipeCategory(helper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(MyBlocks.SATURATOR_BLOCK.get()), JEIPlugin.SATURATOR_CRAFT);
        registration.addRecipeCatalyst(new ItemStack(MyBlocks.DESATURATOR_BLOCK.get()), JEIPlugin.SATURATOR_CRAFT);
        registration.addRecipeCatalyst(new ItemStack(MyBlocks.FULLMETALALCHEMISER_BLOCK.get()), JEIPlugin.FULLMETALALCHEMISER_CRAFT);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<SaturatorRecipe> recipes = rm.getAllRecipesFor(SaturatorRecipe.Type.INSTANCE);
        registration.addRecipes(JEIPlugin.SATURATOR_CRAFT, recipes);
        List<FullMetalAlchemiserRecipe> recipes2 = rm.getAllRecipesFor(FullMetalAlchemiserRecipe.Type.INSTANCE);
        registration.addRecipes(JEIPlugin.FULLMETALALCHEMISER_CRAFT, recipes2);
    }

}
