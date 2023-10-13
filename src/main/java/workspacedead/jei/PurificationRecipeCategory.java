package workspacedead.jei;

import java.util.HashMap;
import java.util.Map;

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
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import workspacedead.WorkspaceDead;
import workspacedead.gfx.EntityGFX;
import workspacedead.recipe.PurificationRecipe;
import workspacedead.registry.MyItems;

public class PurificationRecipeCategory implements IRecipeCategory<PurificationRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(WorkspaceDead.MOD_ID, PurificationRecipe.STATICID);
    private final IDrawable background1;
    private final IDrawable background2;
    private final IDrawable icon;
    private final Map<EntityType<?>, Entity> entityCache = new HashMap<>();

    public PurificationRecipeCategory(IGuiHelper helper) {
        var tex = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/gui/jei/purification_recipe.png");
        var db = helper.drawableBuilder(tex, 128, 0, 128, 82);
        db.setTextureSize(256, 128);
        this.background1 = db.build();
        var db2 = helper.drawableBuilder(tex, 0, 0, 128, 82);
        db2.setTextureSize(256, 128);
        this.background2 = db2.build();

        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(MyItems.PURIFYCRYSTAL.get()));
    }

    protected int ix = 32;
    protected int iy = 32;
    protected int ox = 96;
    protected int oy = 32;

    protected class IDrawableBuilder {
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends PurificationRecipe> getRecipeClass() {
        return PurificationRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("recipecategory." + PurificationRecipe.STATICID + ".title");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull PurificationRecipe recipe,
            @Nonnull IFocusGroup focusGroup) {
        ix = 43;
        iy = 42;
        ox = 90;
        oy = 42;

        if (recipe.isBlockMode()) {
            builder.addSlot(RecipeIngredientRole.INPUT, ix - 9, iy - 9)
                    .addItemStack(new ItemStack(recipe.getInputBlock().asItem()));
            builder.addSlot(RecipeIngredientRole.OUTPUT, ox - 9, oy - 9)
                    .addItemStack(new ItemStack(recipe.getOutputBlock().asItem()));
        } else {
        }
        if (recipe.getPower() <= 2) {
            builder.addSlot(RecipeIngredientRole.INPUT, 64 - 6, 0)
                    .addItemStack(new ItemStack(MyItems.PURIFYCRYSTAL.get()));
        }
        if (recipe.getPower() <= 1) {
            builder.addSlot(RecipeIngredientRole.INPUT, 64 - 6, 17)
                    .addItemStack(new ItemStack(MyItems.WEAKPURIFYCRYSTAL.get()));
        }
        if (recipe.getPower() == 0) {
            builder.addSlot(RecipeIngredientRole.INPUT, 64 - 6, 46)
                    .addItemStack(new ItemStack(MyItems.PURIFYSHARD.get()));
        }
    }

    @Override
    public void draw(PurificationRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX,
            double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, poseStack, mouseX, mouseY);
        if (recipe.isEntityMode())
            this.background2.draw(poseStack, 0, 0);

        /*
         * RenderSystem.setShader(GameRenderer::getPositionTexShader);
         * RenderSystem.setShaderTexture(0, new ResourceLocation(WorkspaceDead.MOD_ID,
         * "textures/gui/jei/purification_recipe.png"));
         * RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
         * RenderSystem.enableBlend(); RenderSystem.defaultBlendFunc();
         * RenderSystem.enableDepthTest(); if (recipe.isBlockMode()) {
         * GuiComponent.blit(poseStack, ix - 11, iy - 11, 0, 108, 20, 20, 128, 128);
         * GuiComponent.blit(poseStack, ox - 14, oy - 14, 0, 82, 26, 26, 128, 128); }
         * else { GuiComponent.blit(poseStack, ix - 21, iy - 23, 26, 82, 42, 46, 128,
         * 128); GuiComponent.blit(poseStack, ox - 21, oy - 23, 26, 82, 42, 46, 128,
         * 128); }
         */

        // Chatter.sendToAllPlayers("x:" + mouseX + " y:" + mouseY);

        if (recipe.isEntityMode()) {
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

            var entity = this.entityCache.get(recipe.outputEntity);
            if (entity == null) {
                entity = recipe.makeOutputEntity(minecraft.level, BlockPos.ZERO);
                this.entityCache.put(recipe.outputEntity, entity);
            }

            poseStack.pushPose();
            var nmx2 = -mouseX + ox - 10;// - v1.x();
            var nmy2 = -mouseY + oy;// - v1.y();
            var size = Math.max(.5F, Math.max(recipe.outputEntity.getWidth(), recipe.outputEntity.getHeight()));
            EntityGFX.renderEntity(poseStack, ox + 10, oy + 16, 100F / size * 0.3F, (LivingEntity) entity, (float) nmx2,
                    (float) nmy2);
            poseStack.popPose();

            var name = recipe.outputEntity.getDescription().getString();
            minecraft.font.drawShadow(poseStack, name, ox + 10 - minecraft.font.width(name) / 2F, 67, 0xFFFFFF);
        }
    }


}
