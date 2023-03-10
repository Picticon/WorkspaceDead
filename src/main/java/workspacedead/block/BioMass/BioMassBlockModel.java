package workspacedead.block.BioMass;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import workspacedead.WorkspaceDead;

public class BioMassBlockModel extends AnimatedGeoModel<BioMassBlockEntity> {
    @Override
    public ResourceLocation getModelLocation(BioMassBlockEntity object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "geo/biomass_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BioMassBlockEntity object) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/block/biomass.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BioMassBlockEntity animatable) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "animations/biomass_block.animation.json");
    }
}
