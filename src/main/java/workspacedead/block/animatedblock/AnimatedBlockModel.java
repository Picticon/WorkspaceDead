//package workspacedead.block.animatedblock;
//
//import net.minecraft.resources.ResourceLocation;
//import software.bernie.geckolib3.model.AnimatedGeoModel;
//import workspacedead.WorkspaceDead;
//
//public class AnimatedBlockModel extends AnimatedGeoModel<AnimatedBlockEntity> {
//    @Override
//    public ResourceLocation getModelLocation(AnimatedBlockEntity object) {
//        return new ResourceLocation(WorkspaceDead.MOD_ID, "geo/biomass_block.geo.json");
//    }
//
//    @Override
//    public ResourceLocation getTextureLocation(AnimatedBlockEntity object) {
//        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/block/biomass.png");
//    }
//
//    @Override
//    public ResourceLocation getAnimationFileLocation(AnimatedBlockEntity animatable) {
//        return new ResourceLocation(WorkspaceDead.MOD_ID, "animations/biomass_block.animation.json");
//    }
//}