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
import workspacedead.recipe.PurificationRecipe;
import workspacedead.recipe.CropMutationRecipe;
import workspacedead.recipe.DeadInsideEffectRecipe;
import workspacedead.recipe.FullMetalAlchemiserRecipe;
import workspacedead.recipe.SaturatorRecipe;
import workspacedead.registry.MyBlocks;
import workspacedead.registry.MyItems;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    public static final RecipeType<SaturatorRecipe> SATURATOR_CRAFT = RecipeType.create(WorkspaceDead.MOD_ID,
            "saturator_craft", SaturatorRecipe.class);
    public static final RecipeType<FullMetalAlchemiserRecipe> FULLMETALALCHEMISER_CRAFT = RecipeType
            .create(WorkspaceDead.MOD_ID, "fullmetalalchemiser_craft", FullMetalAlchemiserRecipe.class);
    public static final RecipeType<PurificationRecipe> PURIFICATION_CRAFT = RecipeType
            .create(WorkspaceDead.MOD_ID, PurificationRecipe.STATICID, PurificationRecipe.class);
    public static final RecipeType<DeadInsideEffectRecipe> DEADINSIDEEFFECT_CRAFT = RecipeType
            .create(WorkspaceDead.MOD_ID, DeadInsideEffectRecipe.STATICID, DeadInsideEffectRecipe.class);
    public static final RecipeType<CropMutationRecipe> CROPMUTATION_CRAFT = RecipeType
            .create(WorkspaceDead.MOD_ID, CropMutationRecipe.STATICID, CropMutationRecipe.class);
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
        registry.addRecipeCategories(new PurificationRecipeCategory(helper));
        registry.addRecipeCategories(new DeadInsideEffectRecipeCategory(helper));
        registry.addRecipeCategories(new CropMutationRecipeCategory(helper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(MyBlocks.SATURATOR_BLOCK.get()), JEIPlugin.SATURATOR_CRAFT);
        registration.addRecipeCatalyst(new ItemStack(MyBlocks.DESATURATOR_BLOCK.get()), JEIPlugin.SATURATOR_CRAFT);
        registration.addRecipeCatalyst(new ItemStack(MyBlocks.FULLMETALALCHEMISER_BLOCK.get()),
                JEIPlugin.FULLMETALALCHEMISER_CRAFT);
        registration.addRecipeCatalyst(new ItemStack(MyItems.PURIFYCRYSTAL.get()),
                JEIPlugin.PURIFICATION_CRAFT);
        // registration.addRecipeCatalyst(new ItemStack(MyItems.WEAKPURIFYCRYSTAL.get()),
        //         JEIPlugin.PURIFICATION_CRAFT);
        // registration.addRecipeCatalyst(new ItemStack(MyItems.PURIFYSHARD.get()),
        //         JEIPlugin.PURIFICATION_CRAFT);
        registration.addRecipeCatalyst(new ItemStack(MyBlocks.MUTATING_FARMLAND.get()),
                JEIPlugin.CROPMUTATION_CRAFT);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Minecraft minecraft = Minecraft.getInstance();
        RecipeManager rm = Objects.requireNonNull(minecraft.level).getRecipeManager();
        List<SaturatorRecipe> recipes = rm.getAllRecipesFor(SaturatorRecipe.Type.INSTANCE);
        registration.addRecipes(JEIPlugin.SATURATOR_CRAFT, recipes);
        List<FullMetalAlchemiserRecipe> recipes2 = rm.getAllRecipesFor(FullMetalAlchemiserRecipe.Type.INSTANCE);
        registration.addRecipes(JEIPlugin.FULLMETALALCHEMISER_CRAFT, recipes2);
        List<PurificationRecipe> recipes3 = rm.getAllRecipesFor(PurificationRecipe.Type.INSTANCE);
        registration.addRecipes(JEIPlugin.PURIFICATION_CRAFT, recipes3);
        List<DeadInsideEffectRecipe> recipes4 = rm.getAllRecipesFor(DeadInsideEffectRecipe.Type.INSTANCE);
        registration.addRecipes(JEIPlugin.DEADINSIDEEFFECT_CRAFT, recipes4);
        List<CropMutationRecipe> recipes5 = rm.getAllRecipesFor(CropMutationRecipe.Type.INSTANCE);
        registration.addRecipes(JEIPlugin.CROPMUTATION_CRAFT, recipes5);
    }
}
