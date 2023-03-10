package workspacedead.client.renderer.projectile;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import workspacedead.WorkspaceDead;
import workspacedead.entity.projectile.DeadArrow;

public class DeadArrowRenderer extends ArrowRenderer<DeadArrow> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/deadarrow.png");

    public DeadArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(DeadArrow arrow) {
        return TEXTURE;
    }
}