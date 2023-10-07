package workspacedead.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.RenderProperties;
import workspacedead.WorkspaceDead;

public class ItemButton extends Button {

    private ItemStack _itemstack;
    private boolean _selected;

    public ItemButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, Item item, OnPress pOnPress) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress);
        _itemstack = new ItemStack(item);
    }

    public boolean getSelected() {
        return _selected;
    }

    public void setSelected(boolean selected) {
        _selected = selected;
    }

    @Override
    public boolean isHoveredOrFocused() {
        // return (this._selected != super.isHoveredOrFocused());
        return super.isHoveredOrFocused();
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        // Minecraft.getInstance().getItemRenderer().renderStatic(null, _itemstack,
        // null, active, pPoseStack, null, null, pMouseY, pMouseX, pMouseY);(_itemstack,
        // 0, 0);
        PoseStack modelViewStack = RenderSystem.getModelViewStack();
        modelViewStack.pushPose();
        modelViewStack.mulPoseMatrix(pPoseStack.last().pose());
        modelViewStack.translate(this.x + 2, this.y + 2, pPartialTick);
        RenderSystem.enableDepthTest();

        Minecraft minecraft = Minecraft.getInstance();
        Font fontRenderer = RenderProperties.get(_itemstack).getFont(_itemstack);
        if (fontRenderer == null) {
            fontRenderer = minecraft.font;
        }
        ItemRenderer itemRenderer = minecraft.getItemRenderer();
        itemRenderer.renderAndDecorateFakeItem(_itemstack, 0, 0);
        itemRenderer.renderGuiItemDecorations(fontRenderer, _itemstack, 0, 0, null);
        RenderSystem.disableBlend();

        modelViewStack.popPose();
        // Restore model-view matrix now that the item has been rendered
        RenderSystem.applyModelViewMatrix();

        if (_selected) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0,
                    new ResourceLocation(WorkspaceDead.MOD_ID, "textures/gui/imagebuttons.png"));
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            GuiComponent.blit(pPoseStack, this.x, this.y, 0, 60, 20, 20, 128, 128);
        }
    }
}
