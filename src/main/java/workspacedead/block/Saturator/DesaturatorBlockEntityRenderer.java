package workspacedead.block.Saturator;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LightLayer;

public class DesaturatorBlockEntityRenderer implements BlockEntityRenderer<DesaturatorBlockEntity> {
    public DesaturatorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(DesaturatorBlockEntity entity, float partialTicks, PoseStack poseStack,
            MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        // BlockPos pos = tile.getPos();
        ItemStack itemStack = entity.getDisplayedItemStack();
        if (itemStack == null || itemStack.getCount() == 0)
            return;

        int bLight = entity.getLevel().getBrightness(LightLayer.BLOCK, entity.getBlockPos());
        int sLight = entity.getLevel().getBrightness(LightLayer.SKY, entity.getBlockPos());
        var light = LightTexture.pack(bLight, sLight);

        double boop = Util.getMillis() / 500D;
        poseStack.pushPose();
        poseStack.translate(0.5, 1.1f, 0.5);
        poseStack.translate(0D, Math.sin(boop % Math.PI * 2) * 0.07f, 0D);
        float boop2 = ((float) boop * 40F) % 360F;
        var q = new Quaternion(Vector3f.YP, boop2, true);
        poseStack.mulPose(q);
        poseStack.scale(.75f, .75f, .75f);

        // Quaternion rotation =
        // Minecraft.getInstance().gameRenderer.getMainCamera().rotation();
        // poseStack.mulPose(rotation);

        float scale = 0.65f;
        // if (itemStack.getItem() instanceof BlockItem) {
        // scale = 0.85f;
        // var qo = new Quaternion(Vector3f.XP, 15, true);
        // poseStack.mulPose(qo);
        // var qo2 = new Quaternion(Vector3f.ZP, 15, true);
        // poseStack.mulPose(qo2);
        // }
        poseStack.scale(scale, scale, scale);
        try {
            Minecraft.getInstance().getItemRenderer().renderStatic(itemStack, ItemTransforms.TransformType.FIXED, light,
                    combinedOverlay, poseStack, bufferSource, Item.getId(itemStack.getItem()));
        } catch (Exception e) {
        }
        poseStack.popPose();
    }
}