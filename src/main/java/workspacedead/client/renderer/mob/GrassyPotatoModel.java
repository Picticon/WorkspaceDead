package workspacedead.client.renderer.mob;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.GrassyPotato;

public class GrassyPotatoModel extends AnimatedGeoModel<GrassyPotato> {

    @Override
    public ResourceLocation getAnimationFileLocation(GrassyPotato animatable) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "animations/grassypotato.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(GrassyPotato object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "geo/grassypotato.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GrassyPotato object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/grassypotato/potato1.png");
    }

}
