package workspacedead.block.Saturator;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LightLayer;
import software.bernie.geckolib3.core.util.Color;
import workspacedead.gfx.LaserGFX;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class SaturatorBlockEntityRenderer implements BlockEntityRenderer<SaturatorBlockEntity> {

    public SaturatorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SaturatorBlockEntity entity, float partialTicks, PoseStack poseStack,
            MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        ItemStack itemStack = entity.getDisplayedItemStack();
        if (itemStack == null || itemStack.getCount() == 0)
            return;

        int bLight = entity.getLevel().getBrightness(LightLayer.BLOCK, entity.getBlockPos());
        int sLight = entity.getLevel().getBrightness(LightLayer.SKY, entity.getBlockPos());
        var light = LightTexture.pack(bLight, sLight);

        double boop = Util.getMillis() / 500D;
        poseStack.pushPose();
        var pcomp = entity.percentageDone();
        poseStack.translate(0.5, 0.7f + .3 * pcomp, 0.5);
        poseStack.translate(0D, Math.sin(boop % (Math.PI * 2)) * 0.05, 0D);
        float boop2 = ((float) boop * 40F) % 360F;
        var q = new Quaternion(Vector3f.YP, boop2, true);
        poseStack.mulPose(q);
        poseStack.scale(.5f + pcomp * .35f, .5f + pcomp * .35f, .5f + pcomp * .35f);
        // poseStack.scale(.75f, .75f, .75f);

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

        if (entity.isCrafting()) {
            poseStack.pushPose();
            poseStack.translate(0.5D, 0.57D, 0.5D);

            long gametime = entity.getLevel().getGameTime();
            var texscale = 1;

            float degree = (float) Math.floorMod(gametime, 360) + partialTicks;
            var rgb = Color.HSBtoRGB(degree / 360f, 1, 1);
            float[] colors = new float[] { rgb >> 16 & 0xFF, rgb >> 8 & 0xFF, rgb & 0xFF, .25f };

            poseStack.pushPose();
            poseStack.translate(-0.5f, 0.0f, 0.0f);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(79.9f));
            LaserGFX.renderLaser(poseStack, bufferSource, partialTicks, texscale, gametime, 2.06f, colors, .05f, .07f);
            poseStack.popPose();

            poseStack.pushPose();
            poseStack.mulPose(Vector3f.YP.rotationDegrees(90f));
            poseStack.translate(-0.5f, 0.0f, 0.0f);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(79.9f));
            LaserGFX.renderLaser(poseStack, bufferSource, partialTicks, texscale, gametime, 2.06f, colors, .05f, .07f);
            poseStack.popPose();

            poseStack.pushPose();
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180f));
            poseStack.translate(-0.5f, 0.0f, 0.0f);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(79.9f));
            LaserGFX.renderLaser(poseStack, bufferSource, partialTicks, texscale, gametime, 2.06f, colors, .05f, .07f);
            poseStack.popPose();

            poseStack.pushPose();
            poseStack.mulPose(Vector3f.YP.rotationDegrees(270f));
            poseStack.translate(-0.5f, 0.0f, 0.0f);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(79.9f));
            LaserGFX.renderLaser(poseStack, bufferSource, partialTicks, texscale, gametime, 2.06f, colors, .05f, .07f);
            poseStack.popPose();
            poseStack.popPose();
        }

    }

}