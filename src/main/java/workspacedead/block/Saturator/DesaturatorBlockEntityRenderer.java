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
import net.minecraft.world.phys.Vec3;
import workspacedead.gfx.ElectricityGFX;

public class DesaturatorBlockEntityRenderer implements BlockEntityRenderer<DesaturatorBlockEntity> {
    float[] _colors = new float[] { .3f, .7f, 1f, 1f };

    public DesaturatorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(DesaturatorBlockEntity entity, float partialTicks, PoseStack poseStack,
            MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {

        // BlockPos pos = tile.getPos();

        var iscrafting = entity.isCrafting();

        var colors = entity.getColors();
        _colors[0] = colors[0];
        _colors[1] = colors[1];
        _colors[2] = colors[2];

        var level = entity.getLevel();
        var random = level.getRandom();

        if (entity.bolts == null) {
            entity.bolts = new ElectricityGFX[4];
            var off = .43f;
            var inoff = .1f;
            var cx = entity.getX() + .5f;
            var cy = entity.getY() + 1.05f;
            var cy2 = entity.getY() + .932f;
            var cz = entity.getZ() + .5f;

            entity.bolts[0] = new ElectricityGFX(random, //
                    new Vec3(cx + inoff, cy, cz), //
                    new Vec3(cx + off, cy2, cz));
            entity.bolts[1] = new ElectricityGFX(random, //
                    new Vec3(cx - inoff, cy, cz), //
                    new Vec3(cx - off, cy2, cz));
            entity.bolts[2] = new ElectricityGFX(random, //
                    new Vec3(cx, cy, cz + inoff), //
                    new Vec3(cx, cy2, cz + off));
            entity.bolts[3] = new ElectricityGFX(random, //
                    new Vec3(cx, cy, cz - inoff), //
                    new Vec3(cx, cy2, cz - off));
        }

        ItemStack itemStack = entity.getDisplayedItemStack();
        if (itemStack == null || itemStack.getCount() == 0)
            return;

        int bLight = level.getBrightness(LightLayer.BLOCK, entity.getBlockPos());
        int sLight = level.getBrightness(LightLayer.SKY, entity.getBlockPos());
        var light = LightTexture.pack(bLight, sLight);

        double boop = Util.getMillis() / 500D;
        poseStack.pushPose();
        poseStack.translate(0.5, 1.05f, 0.5);
        poseStack.translate(0D, Math.sin(boop % Math.PI * 2) * 0.02f, 0D);
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

        if (iscrafting && (!entity.getStarved() || random.nextFloat() > .85f)) {
            poseStack.pushPose();
            var e1 = level.getBlockEntity(entity.getBlockPos().north(3));
            if (e1 instanceof SaturatorBlockEntity)
                entity.bolts[3].drawAnimation(entity.getX(), entity.getY(), entity.getZ(), _colors, 1f, bufferSource,
                        poseStack);
            var e2 = level.getBlockEntity(entity.getBlockPos().south(3));
            if (e2 instanceof SaturatorBlockEntity)
                entity.bolts[2].drawAnimation(entity.getX(), entity.getY(), entity.getZ(), _colors, 1f, bufferSource,
                        poseStack);
            var e3 = level.getBlockEntity(entity.getBlockPos().east(3));
            if (e3 instanceof SaturatorBlockEntity)
                entity.bolts[0].drawAnimation(entity.getX(), entity.getY(), entity.getZ(), _colors, 1f, bufferSource,
                        poseStack);
            var e4 = level.getBlockEntity(entity.getBlockPos().west(3));
            if (e4 instanceof SaturatorBlockEntity)
                entity.bolts[1].drawAnimation(entity.getX(), entity.getY(), entity.getZ(), _colors, 1f, bufferSource,
                        poseStack);

            //for (var i = 0; i < 4; i++)

            poseStack.popPose();
        }

        /*        // lame attempt at lightning arcs //
        poseStack.pushPose();
        long gametime = entity.getLevel().getGameTime();
        float[] black = new float[] { .3f, 1f, 1f, .25f };
        
        var r = new Random(gametime);
        poseStack.translate(0.5, .95f, 0.5); // move to center, top, in the middle of the item
        for (var iter = 0; iter <= 270; iter += 90) {
            poseStack.pushPose();
            poseStack.mulPose(Vector3f.YP.rotationDegrees(iter)); // rotate to NSEW
            poseStack.translate(.45f, 0, 0); // move to lens
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(70f)); // point towards center
            for (var segment = 0; segment < 4; segment++) {
                for (var beams = 0; beams <= segment; beams++) {
                    poseStack.pushPose();
                    poseStack.mulPose(Vector3f.YP.rotationDegrees((float) (r.nextDouble() * 40 - 20))); // random Y
                    poseStack.mulPose(Vector3f.ZP.rotationDegrees((float) (r.nextDouble() * 40 - 20))); // random Z
                    LaserGFX.renderLaser(poseStack, bufferSource, partialTicks, 1, gametime, .105f, black, .007f, 0);
                    poseStack.popPose();
                }
                poseStack.translate(0, .1f, 0);
            }
            poseStack.popPose();
        }
        poseStack.translate(-0f, 0.0f, 0.0f);
        // poseStack.mulPose(Vector3f.ZP.rotationDegrees(79.9f));
        poseStack.popPose();
         */

    }

}
