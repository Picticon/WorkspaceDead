package workspacedead.jei;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import workspacedead.WorkspaceDead;
import workspacedead.recipe.SaturatorRecipe;
import workspacedead.registry.MyBlocks;

public class SaturatorRecipeCategory implements IRecipeCategory<SaturatorRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(WorkspaceDead.MOD_ID, "saturator_craft");

    private final IDrawable background;
    private final IDrawable icon;

    public SaturatorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(
                new ResourceLocation(WorkspaceDead.MOD_ID, "textures/gui/jei/saturator_recipe.png"), 0, 0, 138, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(MyBlocks.SATURATOR_BLOCK.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends SaturatorRecipe> getRecipeClass() {
        return SaturatorRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("container." + JEIPlugin.SATURATOR_CRAFT + ".name");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull SaturatorRecipe recipe,
            @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 33, 33).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 33, 3).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 63, 33).addIngredients(recipe.getIngredients().get(2));
        builder.addSlot(RecipeIngredientRole.INPUT, 33, 63).addIngredients(recipe.getIngredients().get(3));
        builder.addSlot(RecipeIngredientRole.INPUT, 3, 33).addIngredients(recipe.getIngredients().get(4));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 78, 60).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(SaturatorRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX,
            double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, poseStack, mouseX, mouseY);
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.font.draw(poseStack, Integer.toString(recipe.getPower()) + " FE", 70, 3, 0xFF808080);
        minecraft.font.draw(poseStack, Integer.toString(recipe.getCraftingTicks() / 20) + "s", 70, 18, 0xFF808080);
    }

}
