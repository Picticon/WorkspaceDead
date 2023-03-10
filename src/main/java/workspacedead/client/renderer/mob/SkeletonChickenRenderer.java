package workspacedead.client.renderer.mob;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.SkeletonChicken;

@OnlyIn(Dist.CLIENT)
public class SkeletonChickenRenderer extends MobRenderer<SkeletonChicken, SkeletonChickenModel<SkeletonChicken>> {
    private static final ResourceLocation CHICKEN_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/mob/skeletonchicken.png");

    public SkeletonChickenRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SkeletonChickenModel<>(pContext.bakeLayer(ModelLayers.CHICKEN)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonChicken pEntity) {
        return CHICKEN_LOCATION;
    }

   /**
    * Defines what float the third param in setRotationAngles of ModelBase is
    */
    protected float getBob(SkeletonChicken pLivingBase, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pLivingBase.oFlap, pLivingBase.flap);
        float f1 = Mth.lerp(pPartialTicks, pLivingBase.oFlapSpeed, pLivingBase.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
     }
  
}
