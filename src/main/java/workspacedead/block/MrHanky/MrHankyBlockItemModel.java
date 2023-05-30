package workspacedead.block.MrHanky;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import workspacedead.WorkspaceDead;

public class MrHankyBlockItemModel extends AnimatedGeoModel<MrHankyBlockItem> {
    @Override
    public ResourceLocation getModelLocation(MrHankyBlockItem object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "geo/mrhanky.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MrHankyBlockItem object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/block/mrhanky_texture.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MrHankyBlockItem animatable) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "animations/mrhanky.animation.json");
    }
}