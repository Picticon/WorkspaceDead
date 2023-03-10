package workspacedead.client.renderer.mob;

import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import workspacedead.WorkspaceDead;

public class SkeletonSlimeRenderer extends SlimeRenderer {

    private static final ResourceLocation SKELETONSLIME_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/mob/skeletonslime.png");

    public SkeletonSlimeRenderer(Context p_174391_) {
        super(p_174391_);
    }

    @Override
    public ResourceLocation getTextureLocation(Slime pEntity) {
        return SKELETONSLIME_LOCATION;
    }

}
