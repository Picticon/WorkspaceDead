package workspacedead.client.renderer.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.DraconicBlaze;

@OnlyIn(Dist.CLIENT)
public class DraconicBlazeRenderer extends MobRenderer<DraconicBlaze, DraconicBlazeModel<DraconicBlaze>> {

    public DraconicBlazeRenderer(Context pContext) {
        super(pContext, new DraconicBlazeModel<>(pContext.bakeLayer(DraconicBlazeModel.LAYER_LOCATION)), 0.5F);
    }
    
    private static final ResourceLocation DRACONICBLAZE_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/mob/draconicblaze.png");

    @Override
    public ResourceLocation getTextureLocation(DraconicBlaze pEntity) {
        return DRACONICBLAZE_LOCATION;
    }

}
