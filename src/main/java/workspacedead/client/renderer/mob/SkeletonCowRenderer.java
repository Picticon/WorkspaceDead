package workspacedead.client.renderer.mob;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.SkeletonCow;

@OnlyIn(Dist.CLIENT)
public class SkeletonCowRenderer extends MobRenderer<SkeletonCow, SkeletonCowModel<SkeletonCow>> {
    private static final ResourceLocation COW_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/mob/skeletoncow.png");

    public SkeletonCowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SkeletonCowModel<>(pContext.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonCow pEntity) {
        return COW_LOCATION;
    }
}
