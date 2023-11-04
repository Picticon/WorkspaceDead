package workspacedead.jei;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

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
import workspacedead.recipe.CropMutationRecipe;
import workspacedead.registry.MyBlocks;

public class CropMutationRecipeCategory implements IRecipeCategory<CropMutationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(WorkspaceDead.MOD_ID,
            CropMutationRecipe.STATICID);
    private final IDrawable background1;
    private final IDrawable icon;

    public CropMutationRecipeCategory(IGuiHelper helper) {
        var tex = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/gui/jei/cropmutation_recipe.png");
        var db = helper.drawableBuilder(tex, 128, 0, 128, 84);
        db.setTextureSize(128, 128);
        this.background1 = db.build();

        var tex2 = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/block/mutating_farmland.png");
        var db2 = helper.drawableBuilder(tex2, 0, 0, 16, 16);
        db2.setTextureSize(16, 16);
        this.icon = db2.build();
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CropMutationRecipe> getRecipeClass() {
        return CropMutationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("recipecategory." + CropMutationRecipe.STATICID + ".title");
    }

    @Override
    public IDrawable getBackground() {
        return this.background1;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull CropMutationRecipe recipe,
            @Nonnull IFocusGroup focusGroup) {

        var input1 = recipe.getInput1Block();
        if (input1 != null) {
            builder.addSlot(RecipeIngredientRole.INPUT, 14, 11)
                    .addItemStacks(input1.getDisplayedStacks())
                    .addTooltipCallback((view, tooltip) -> tooltip.addAll(input1.descriptionTooltip()));
        }
        var input2 = recipe.getInput2Block();
        if (input2 != null) {
            builder.addSlot(RecipeIngredientRole.INPUT, 100, 11)
                    .addItemStacks(input2.getDisplayedStacks())
                    .addTooltipCallback((view, tooltip) -> tooltip.addAll(input2.descriptionTooltip()));
        }
        var output = recipe.getOutputBlock();
        if (output != null) {
            builder.addSlot(RecipeIngredientRole.OUTPUT, 57, 11)
                    .addItemStacks(output.getDisplayedStacks())
                    .addTooltipCallback((view, tooltip) -> tooltip.addAll(output.descriptionTooltip()));
        }

        var catalyst = recipe.getCatalyst();
        if (catalyst != null) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 57, 62)
                    .addItemStacks(catalyst.getDisplayedStacks())
                    .addTooltipCallback((view, tooltip) -> tooltip.addAll(catalyst.descriptionTooltip()));
        }

        builder.addSlot(RecipeIngredientRole.CATALYST, 57, 39)
                .addItemStack(new ItemStack(MyBlocks.MUTATING_FARMLAND.get().asItem(), 1));
    }

    @Override
    public void draw(CropMutationRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack,
            double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, poseStack, mouseX, mouseY);
        var minecraft = Minecraft.getInstance();
        var percent = Math.round(recipe.getChance() * 100f) + "% / 5s";
        minecraft.font.drawShadow(poseStack, percent, 80, 67, 0xFFFFFF);

    }
}
