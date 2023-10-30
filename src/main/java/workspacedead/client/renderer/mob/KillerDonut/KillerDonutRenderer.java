package workspacedead.client.renderer.mob.KillerDonut;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.KillerDonut;

public class KillerDonutRenderer extends GeoEntityRenderer<KillerDonut> {

    public KillerDonutRenderer(Context renderManager) {
        super(renderManager, new KillerDonutModel());
        this.shadowRadius = .2f;
    }

    // @Override
    // public float getHeightScale(KillerDonut entity) {
    //     return entity.getRenderScale();
    // }

    // @Override
    // public float getWidthScale(KillerDonut entity) {
    //     return entity.getRenderScale();
    // }

    @Override
    public ResourceLocation getTextureLocation(KillerDonut animatable) {
        if (animatable.getTexture() == 2)
            return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/killer_donut/killer_donut_3.png");
        if (animatable.getTexture() == 1)
            return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/killer_donut/killer_donut_2.png");
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/killer_donut/killer_donut_1.png");
    }

    // @Override
    // public RenderType getRenderType(GrassyPotato animatable, float partialTick,
    // PoseStack poseStack, MultiBufferSource bufferSource, VertexConsumer buffer,
    // int packedLight, ResourceLocation texture) {
    // // var t = animatable.getScale();
    // // poseStack.scale(t, t, t);
    // return super.getRenderType(animatable, partialTick, poseStack, bufferSource,
    // buffer, packedLight, texture);
    // }

    @Override
    public void renderEarly(KillerDonut animatable, PoseStack poseStack, float partialTick,
            MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, int packedOverlay, float red,
            float green, float blue, float partialTicks) {
        var t = animatable.getScale();
        this.shadowRadius = t * .4f;// * .4f;
        // var body = modelProvider.getAnimationProcessor().getBone("body");
        // if (body != null) {
        //     body.setScaleX(animatable.getRenderScale());
        //     body.setScaleY(animatable.getRenderScale());
        //     body.setScaleZ(animatable.getRenderScale());
        // }
        super.renderEarly(animatable, poseStack, partialTick, bufferSource, buffer, packedLight, packedOverlay, red,
                green, blue, partialTicks);
    }
}
