package workspacedead.gfx;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;

public class EntityGFX {
        public static void renderEntity(PoseStack matrixstack, int pPosX, int pPosY, float pScale,
            LivingEntity pLivingEntity, float pMouseX, float pMouseY) {

        var f = (float) Math.atan(pMouseX / 40.0F);
        var f1 = (float) Math.atan(pMouseY / 40.0F);
        var posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate(pPosX, pPosY, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        matrixstack.translate(0.0D, 0.0D, 1000.0D);
        matrixstack.scale(pScale, pScale, pScale);
        var quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        var quaternion1 = Vector3f.XP.rotationDegrees(f1 * 8.0F);
        quaternion.mul(quaternion1);
        matrixstack.mulPose(quaternion);
        var f2 = pLivingEntity.yBodyRot;
        var f3 = pLivingEntity.getYRot();
        var f4 = pLivingEntity.getXRot();
        var f5 = pLivingEntity.yHeadRotO;
        var f6 = pLivingEntity.yHeadRot;
        pLivingEntity.yBodyRot = 180.0F + f * 10.0F;
        pLivingEntity.setYRot(180.0F + f * 40.0F);
        pLivingEntity.setXRot(-f1 * 20.0F);
        pLivingEntity.yHeadRot = pLivingEntity.getYRot();
        pLivingEntity.yHeadRotO = pLivingEntity.getYRot();
        Lighting.setupForEntityInInventory();
        var entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderermanager.overrideCameraOrientation(quaternion1);
        entityrenderermanager.setRenderShadow(false);
        var buff = Minecraft.getInstance().renderBuffers().bufferSource();
        // RenderSystem.runAsFancy(() -> {
        entityrenderermanager.render(pLivingEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixstack, buff, 15728880);
        // });
        buff.endBatch();
        entityrenderermanager.setRenderShadow(true);
        pLivingEntity.yBodyRot = f2;
        pLivingEntity.setYRot(f3);
        pLivingEntity.setXRot(f4);
        pLivingEntity.yHeadRotO = f5;
        pLivingEntity.yHeadRot = f6;
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();

    }

}
