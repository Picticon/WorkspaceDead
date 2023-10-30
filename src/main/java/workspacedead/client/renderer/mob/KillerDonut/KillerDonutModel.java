package workspacedead.client.renderer.mob.KillerDonut;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.KillerDonut;

public class KillerDonutModel extends AnimatedGeoModel<KillerDonut> {

    @Override
    public ResourceLocation getAnimationFileLocation(KillerDonut animatable) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "animations/killer_donut.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(KillerDonut object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "geo/killer_donut.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KillerDonut object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/killer_donut/killer_donut_1.png");
    }

}
