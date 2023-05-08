package workspacedead.client.renderer.mob;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.RottenPotato;

public class RottenPotatoModel extends AnimatedGeoModel<RottenPotato> {

    @Override
    public ResourceLocation getAnimationFileLocation(RottenPotato animatable) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "animations/rottenpotato.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(RottenPotato object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "geo/rottenpotato.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RottenPotato object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/rottenpotato/potato1.png");
    }

}
