package workspacedead.client.renderer.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.GrassyPotato;

public class GrassyPotatoRenderer extends GeoEntityRenderer<GrassyPotato> {

    public GrassyPotatoRenderer(Context renderManager) {
        super(renderManager, new GrassyPotatoModel());
        this.shadowRadius = .2f;
    }

    

    @Override
    public float getHeightScale(GrassyPotato entity) {
        return entity.getRenderScale();
    }



    @Override
    public float getWidthScale(GrassyPotato entity) {
        return entity.getRenderScale();
    }



    @Override
    public ResourceLocation getTextureLocation(GrassyPotato animatable) {
        return new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/grassypotato/potato1.png");
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
    public void renderEarly(GrassyPotato animatable, PoseStack poseStack, float partialTick, MultiBufferSource bufferSource, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float partialTicks) {
        var t = animatable.getScale();
        this.shadowRadius = t * .4f;// * .4f;
        // var body = modelProvider.getAnimationProcessor().getBone("body");
        // if (body != null) {
        //     body.setScaleX(animatable.getRenderScale());
        //     body.setScaleY(animatable.getRenderScale());
        //     body.setScaleZ(animatable.getRenderScale());
        // }
        super.renderEarly(animatable, poseStack, partialTick, bufferSource, buffer, packedLight, packedOverlay, red, green, blue, partialTicks);
    }
}
