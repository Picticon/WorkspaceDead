package workspacedead.jei;

import java.util.HashMap;
import java.util.Map;

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
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import workspacedead.WorkspaceDead;
import workspacedead.gfx.EntityGFX;
import workspacedead.recipe.DeadInsideEffectRecipe;

public class DeadInsideEffectRecipeCategory implements IRecipeCategory<DeadInsideEffectRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(WorkspaceDead.MOD_ID,
            DeadInsideEffectRecipe.STATICID);
    private final IDrawable background1;
    private final IDrawable icon;
    private final Map<EntityType<?>, Entity> entityCache = new HashMap<>();

    public DeadInsideEffectRecipeCategory(IGuiHelper helper) {
        var tex = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/gui/jei/deadinside_recipe.png");

        var db = helper.drawableBuilder(tex, 128, 0, 128, 82);
        db.setTextureSize(128, 128);
        this.background1 = db.build();

        //this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(MyItems.POOP.get()));

        var tex2 = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/mob_effect/deadinside.png");
        var db2 = helper.drawableBuilder(tex2, 0, 0, 16, 16);
        db2.setTextureSize(16, 16);
        this.icon = db2.build();

        //new DrawableResource(
        //new ResourceLocation(WorkspaceDead.MOD_ID, "textures/mob_effect/deadinside.png"), 0, 0, 16, 16, 0, 0, 0,
        //0, 16, 16);
    }

    protected int ix = 32;
    protected int iy = 32;
    protected int ox = 96;
    protected int oy = 32;

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends DeadInsideEffectRecipe> getRecipeClass() {
        return DeadInsideEffectRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("recipecategory." + DeadInsideEffectRecipe.STATICID + ".title");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull DeadInsideEffectRecipe recipe,
            @Nonnull IFocusGroup focusGroup) {
        ix = 43;
        iy = 42;
        ox = 90;
        oy = 42;

        builder.addSlot(RecipeIngredientRole.OUTPUT, ox - 9, oy - 9).addItemStack(recipe.getResultItem());
    }

    @Override
    public void draw(DeadInsideEffectRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack,
            double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, poseStack, mouseX, mouseY);
        var minecraft = Minecraft.getInstance();

        var entity2 = this.entityCache.get(recipe.inputEntity);
        if (entity2 == null) {
            entity2 = recipe.makeInputEntity(minecraft.level, BlockPos.ZERO);
            this.entityCache.put(recipe.inputEntity, entity2);
        }

        poseStack.pushPose();
        var nmx1 = -mouseX + ix - 10;// - v1.x();
        var nmy1 = -mouseY + iy;// - v1.y();

        var size2 = Math.max(.5F, Math.max(recipe.inputEntity.getWidth(), recipe.inputEntity.getHeight()));
        EntityGFX.renderEntity(poseStack, ix - 10, iy + 16, 100F / size2 * 0.3F, (LivingEntity) entity2, (float) nmx1,
                (float) nmy1);
        poseStack.popPose();

        var name2 = recipe.inputEntity.getDescription().getString();
        minecraft.font.drawShadow(poseStack, name2, ix - 10 - minecraft.font.width(name2) / 2F, 67, 0xFFFFFF);
    }

}
