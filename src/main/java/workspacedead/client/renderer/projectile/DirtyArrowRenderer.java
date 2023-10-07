package workspacedead.client.renderer.projectile;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import workspacedead.entity.projectile.DirtyArrow;
import workspacedead.registry.MyItems;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;

public class DirtyArrowRenderer extends EntityRenderer<DirtyArrow> {

    // public static final ResourceLocation TEXTURE = new
    // ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/dirtyarrow.png");
    private final ItemRenderer itemRenderer;
    private final float scale;

    public DirtyArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
        this.scale = .5f;
    }

    @Override
    public void render(DirtyArrow pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(pEntity) < 12.25D)) {
            pMatrixStack.pushPose();
            pMatrixStack.scale(this.scale, this.scale, this.scale);
            pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            this.itemRenderer.renderStatic(new ItemStack(MyItems.DIRTY_ARROW.get(), 1), ItemTransforms.TransformType.GROUND, pPackedLight, OverlayTexture.NO_OVERLAY, pMatrixStack, pBuffer, pEntity.getId());
            pMatrixStack.popPose();
            super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(DirtyArrow pEntity) {
        return InventoryMenu.BLOCK_ATLAS;
    }

}