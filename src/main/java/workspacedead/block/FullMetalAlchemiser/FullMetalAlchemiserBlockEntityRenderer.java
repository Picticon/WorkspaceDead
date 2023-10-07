package workspacedead.block.FullMetalAlchemiser;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.DirectionalBlock;
import workspacedead.gfx.LaserGFX;

public class FullMetalAlchemiserBlockEntityRenderer implements BlockEntityRenderer<FullMetalAlchemiserBlockEntity> {
    public FullMetalAlchemiserBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(FullMetalAlchemiserBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
            MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if (pBlockEntity.isFiring()) {
            float[] pColors = new float[] { 0f, .5f, 1f, 0f };
            var matrix = new Matrix4f();
            matrix.setIdentity();
            var laserdirection = Direction.rotate(matrix,
                    pBlockEntity.getBlockState().getValue(DirectionalBlock.FACING));
            pPoseStack.pushPose();
            pPoseStack.translate(.5f, .5f, .5f);
            pPoseStack.mulPose(laserdirection.getRotation());
            pPoseStack.translate(0, -.4f, 0);
            long gametime = pBlockEntity.getLevel().getGameTime();
            LaserGFX.renderLaser(pPoseStack, pBufferSource, pPartialTick, 1, gametime,
                    pBlockEntity.getBeamLength() + .9f, pColors, .05f, .08f);
            pPoseStack.popPose();
        }
    }

    @Override
    public boolean shouldRenderOffScreen(FullMetalAlchemiserBlockEntity tile) {
        return tile.isFiring();
    }
}