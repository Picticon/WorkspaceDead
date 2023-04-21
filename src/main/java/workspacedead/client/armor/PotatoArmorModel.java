package workspacedead.client.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import workspacedead.WorkspaceDead;
import workspacedead.item.custom.PotatoArmorItem;

public class PotatoArmorModel extends AnimatedGeoModel<PotatoArmorItem> {
    @Override
    public ResourceLocation getModelLocation(PotatoArmorItem object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "geo/potatoarmor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PotatoArmorItem object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/models/potatoarmor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PotatoArmorItem animatable) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "animations/potatoarmor.animation.json");
    }
}