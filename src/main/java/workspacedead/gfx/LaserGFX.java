package workspacedead.gfx;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import workspacedead.WorkspaceDead;

public class LaserGFX {
    private static final ResourceLocation BEAM_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID,
            "textures/gfx/saturator_beam.png");

    public static void renderLaser(PoseStack pPoseStack, MultiBufferSource pBufferSource, float pPartialTick,
            float pTextureScale, long pGameTime, float length, float[] pColors, float pBeamRadius, float pGlowRadius) {

        var pBeamLocation = BEAM_LOCATION; // texture

        float i = length;
        pPoseStack.pushPose();
        float f = (float) Math.floorMod(pGameTime, 40) + pPartialTick;
        float f1 = length < 0 ? f : -f;
        float f2 = Mth.frac(f1 * 0.2F - (float) Mth.floor(f1 * 0.1F));
        float f3 = pColors[0];
        float f4 = pColors[1];
        float f5 = pColors[2];

        pPoseStack.pushPose();
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(f * 2.25F - 45.0F));
        float f6 = 0.0F;
        float f8 = 0.0F;
        float f9 = -pBeamRadius;
        // float f10 = 0.0F;
        // float f11 = 0.0F;
        float f12 = -pBeamRadius;
        // float f13 = 0.0F;
        // float f14 = 1.0F;
        float f15 = -1.0F + f2;
        float f16 = length * pTextureScale * (0.5F / pBeamRadius) + f15;
        renderPart(pPoseStack, pBufferSource.getBuffer(RenderType.beaconBeam(pBeamLocation, false)), f3, f4, f5, 0.5F,
                i, 0.0F, pBeamRadius, pBeamRadius, 0.0F, f9, 0.0F, 0.0F, f12, 0.0F, 1.0F, f16, f15);
        pPoseStack.popPose();
        f6 = -pGlowRadius;
        float f7 = -pGlowRadius;
        f8 = -pGlowRadius;
        f9 = -pGlowRadius;
        // f13 = 0.0F;
        // f14 = 1.0F;
        f15 = -1.0F + f2;
        f16 = length * pTextureScale + f15;
        renderPart(pPoseStack, pBufferSource.getBuffer(RenderType.beaconBeam(pBeamLocation, true)), f3, f4, f5, 0.2F, i,
                f6, f7, pGlowRadius, f8, f9, pGlowRadius, pGlowRadius, pGlowRadius, 0.0F, 1.0F, f16, f15);
        pPoseStack.popPose();
    }

    private static void renderPart(PoseStack pPoseStack, VertexConsumer pConsumer, float pRed, float pGreen,
            float pBlue, float pAlpha, float pMaxY, float pX0, float pZ0, float pX1, float pZ1, float pX2, float pZ2,
            float pX3, float pZ3, float pMinU, float pMaxU, float pMinV, float pMaxV) {
        PoseStack.Pose posestack$pose = pPoseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        renderQuad(matrix4f, matrix3f, pConsumer, pRed, pGreen, pBlue, pAlpha, 0, pMaxY, pX0, pZ0, pX1, pZ1, pMinU,
                pMaxU, pMinV, pMaxV);
        renderQuad(matrix4f, matrix3f, pConsumer, pRed, pGreen, pBlue, pAlpha, 0, pMaxY, pX3, pZ3, pX2, pZ2, pMinU,
                pMaxU, pMinV, pMaxV);
        renderQuad(matrix4f, matrix3f, pConsumer, pRed, pGreen, pBlue, pAlpha, 0, pMaxY, pX1, pZ1, pX3, pZ3, pMinU,
                pMaxU, pMinV, pMaxV);
        renderQuad(matrix4f, matrix3f, pConsumer, pRed, pGreen, pBlue, pAlpha, 0, pMaxY, pX2, pZ2, pX0, pZ0, pMinU,
                pMaxU, pMinV, pMaxV);
    }

    private static void renderQuad(Matrix4f pPose, Matrix3f pNormal, VertexConsumer pConsumer, float pRed, float pGreen,
            float pBlue, float pAlpha, float yMin, float yMax, float xMin, float zMin, float xMax, float zMax,
            float pMinU, float pMaxU, float pMinV, float pMaxV) {
        addVertex(pPose, pNormal, pConsumer, pRed, pGreen, pBlue, pAlpha, yMax, xMin, zMin, pMaxU, pMinV);
        addVertex(pPose, pNormal, pConsumer, pRed, pGreen, pBlue, pAlpha, yMin, xMin, zMin, pMaxU, pMaxV);
        addVertex(pPose, pNormal, pConsumer, pRed, pGreen, pBlue, pAlpha, yMin, xMax, zMax, pMinU, pMaxV);
        addVertex(pPose, pNormal, pConsumer, pRed, pGreen, pBlue, pAlpha, yMax, xMax, zMax, pMinU, pMinV);
    }

    private static void addVertex(Matrix4f pPose, Matrix3f pNormal, VertexConsumer pConsumer, float pRed, float pGreen,
            float pBlue, float pAlpha, float pY, float pX, float pZ, float pU, float pV) {
        pConsumer.vertex(pPose, pX, (float) pY, pZ).color(pRed, pGreen, pBlue, pAlpha).uv(pU, pV)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(pNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }
}
