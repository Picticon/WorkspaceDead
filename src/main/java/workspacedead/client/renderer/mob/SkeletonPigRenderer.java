package workspacedead.client.renderer.mob;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.SkeletonPig;

@OnlyIn(Dist.CLIENT)
public class SkeletonPigRenderer extends MobRenderer<SkeletonPig, SkeletonPigModel<SkeletonPig>> {
    private static final ResourceLocation PIG_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/mob/skeletonpig.png");

    public SkeletonPigRenderer(EntityRendererProvider.Context p_174340_) {
        super(p_174340_, new SkeletonPigModel<>(p_174340_.bakeLayer(ModelLayers.PIG)), 0.7F);
        // this.addLayer(new SaddleLayer<>(this, new
        // PigModel<>(p_174340_.bakeLayer(ModelLayers.PIG_SADDLE)), new
        // ResourceLocation("textures/entity/pig/pig_saddle.png")));
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(SkeletonPig pEntity) {
        return PIG_LOCATION;
    }
}