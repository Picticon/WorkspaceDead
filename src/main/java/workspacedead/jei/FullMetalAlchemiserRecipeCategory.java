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
import workspacedead.recipe.FullMetalAlchemiserRecipe;
import workspacedead.registry.MyBlocks;

public class FullMetalAlchemiserRecipeCategory implements IRecipeCategory<FullMetalAlchemiserRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(WorkspaceDead.MOD_ID, "fullmetalalchemiser_craft");
    private final IDrawable background;
    private final IDrawable icon;

    public FullMetalAlchemiserRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(
                new ResourceLocation(WorkspaceDead.MOD_ID, "textures/gui/jei/fullmetalalchemiser_recipe.png"), 0, 0, 138, 82);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(MyBlocks.FULLMETALALCHEMISER_BLOCK.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends FullMetalAlchemiserRecipe> getRecipeClass() {
        return FullMetalAlchemiserRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("recipecategory." + FullMetalAlchemiserRecipe.STATICID + ".title");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull FullMetalAlchemiserRecipe recipe,
            @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 22, 33).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 65, 33).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(FullMetalAlchemiserRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX,
            double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, poseStack, mouseX, mouseY);
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.font.draw(poseStack, Integer.toString(recipe.getPower()) + " FE", 70, 3, 0xFF808080);
    }

}
