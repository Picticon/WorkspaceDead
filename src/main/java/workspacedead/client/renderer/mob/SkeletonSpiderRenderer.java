package workspacedead.client.renderer.mob;

import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.SkeletonSpider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

@OnlyIn(Dist.CLIENT)
public class SkeletonSpiderRenderer extends SpiderRenderer<SkeletonSpider> {

    private static final ResourceLocation SKELETONSPIDER_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/mob/skeletonspider.png");
 
    public SkeletonSpiderRenderer(Context p_174401_) {
        super(p_174401_);
    }

    public ResourceLocation getTextureLocation(SkeletonSpider pEntity) {
        return SKELETONSPIDER_LOCATION;
     }
      
}
