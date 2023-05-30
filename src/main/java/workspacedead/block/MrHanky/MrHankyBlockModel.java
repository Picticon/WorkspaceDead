package workspacedead.block.MrHanky;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import workspacedead.WorkspaceDead;

public class MrHankyBlockModel extends AnimatedGeoModel<MrHankyBlockEntity> {
    @Override
    public ResourceLocation getModelLocation(MrHankyBlockEntity object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "geo/mrhanky.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MrHankyBlockEntity object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/block/mrhanky_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MrHankyBlockEntity animatable) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "animations/mrhanky.animation.json");
    }
}
