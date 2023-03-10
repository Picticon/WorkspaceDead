package workspacedead.client.renderer.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.BoneGolem;

@OnlyIn(Dist.CLIENT)
public class BoneGolemRenderer extends MobRenderer<BoneGolem, BoneGolemModel<BoneGolem>> {
    private static final ResourceLocation GOLEM_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/mob/bonegolem.png");

   public BoneGolemRenderer(EntityRendererProvider.Context p_174188_) {
      super(p_174188_, new BoneGolemModel<>(p_174188_.bakeLayer(ModelLayers.IRON_GOLEM)), 0.7F);
      //this.addLayer(new BoneGolemCrackinessLayer(this));
      //this.addLayer(new BoneGolemFlowerLayer(this));
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getTextureLocation(BoneGolem pEntity) {
      return GOLEM_LOCATION;
   }

   protected void setupRotations(BoneGolem pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
      super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
      if (!((double)pEntityLiving.animationSpeed < 0.01D)) {
        // float f = 13.0F;
         float f1 = pEntityLiving.animationPosition - pEntityLiving.animationSpeed * (1.0F - pPartialTicks) + 6.0F;
         float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
         pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
      }
   }
}