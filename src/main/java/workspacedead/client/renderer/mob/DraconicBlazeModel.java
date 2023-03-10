package workspacedead.client.renderer.mob;

import java.util.Arrays;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DraconicBlazeModel<T extends Entity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("workspacedead", "draconicblaze"), "main");

    private final ModelPart root;
    private final ModelPart[] upperBodyParts;
    private final ModelPart head;

    public DraconicBlazeModel(ModelPart pRoot) {
        this.root = pRoot;
        this.head = pRoot.getChild("head");
        this.upperBodyParts = new ModelPart[8];
        Arrays.setAll(this.upperBodyParts, (index) -> {
            return pRoot.getChild(getPartName(index));
        });
    }

    private static String getPartName(int pIndex) {
        return "part" + pIndex;
    }

    // I think this is called from the bake routine in the renderer?
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("part0", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("part1", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("part2", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("part3", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("part4", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("part5", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("part6", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("part7", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        var head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()//
                .texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))//
                .texOffs(0, 26).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))//
                .texOffs(0, 26).addBox(-1.0F, -5.0F, 1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))//
                .texOffs(0, 26).addBox(-1.0F, -3.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))//
                .texOffs(0, 26).addBox(-1.0F, 0.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))//
                .texOffs(0, 28).addBox(-3.0F, -6.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))//
                .texOffs(0, 28).addBox(2.0F, -6.0F, -2.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), //
                PartPose.offset(0.0F, 0.0F, 0.0F));

        head.addOrReplaceChild("lowerjaw_r1", CubeListBuilder.create().texOffs(36, 25).addBox(-3.0F, 3.0F, -8.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1309F, 0.0F, 0.0F));

         head.addOrReplaceChild("upperjaw", CubeListBuilder.create().texOffs(35, 7).addBox(-3.0F, 1.0F, -8.0F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))//
                .texOffs(48, 29).addBox(1.0F, 0.0F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))//
                .texOffs(48, 29).addBox(-2.0F, 0.0F, -7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);

        /*
         * MeshDefinition meshdefinition = new MeshDefinition(); PartDefinition
         * partdefinition = meshdefinition.getRoot();
         * partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0,
         * 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO); float f =
         * 0.0F; CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0,
         * 16).addBox(0.0F, 0.0F, 0.0F, 2.0F, 8.0F, 2.0F);
         * 
         * CubeListBuilder spine = CubeListBuilder.create().texOffs(0, 26).addBox(-1.0F,
         * -1.0F, -1.0F, 2.0F, 2.0F, 2.0F);
         * 
         * partdefinition.addOrReplaceChild("part0", spine, PartPose.offset(-1, 28,
         * -3)); partdefinition.addOrReplaceChild("part1", spine, PartPose.offset(-1,
         * 28, 1)); partdefinition.addOrReplaceChild("part2", spine, PartPose.offset(-1,
         * 25, 4)); partdefinition.addOrReplaceChild("part3", spine, PartPose.offset(-1,
         * 21, 4));
         * 
         * // upper spinners // for (int i = 0; i < 4; ++i) { // float f1 = Mth.cos(f) *
         * 9.0F; // float f2 = -2.0F + Mth.cos((float) (i * 2) * 0.25F); // float f3 =
         * Mth.sin(f) * 9.0F; // partdefinition.addOrReplaceChild(getPartName(i),
         * cubelistbuilder, // PartPose.offset(f1, f2, f3)); // ++f; // }
         * 
         * f = ((float) Math.PI / 4F);
         * 
         * for (int j = 4; j < 8; ++j) { float f4 = Mth.cos(f) * 7.0F; float f6 = 2.0F +
         * Mth.cos((float) (j * 2) * 0.25F); float f8 = Mth.sin(f) * 7.0F;
         * partdefinition.addOrReplaceChild(getPartName(j), cubelistbuilder,
         * PartPose.offset(f4, f6, f8)); ++f; }
         * 
         * f = 0.47123894F;
         * 
         * for (int k = 8; k < 12; ++k) { float f5 = Mth.cos(f) * 5.0F; float f7 = 11.0F
         * + Mth.cos((float) k * 1.5F * 0.5F); float f9 = Mth.sin(f) * 5.0F;
         * partdefinition.addOrReplaceChild(getPartName(k), cubelistbuilder,
         * PartPose.offset(f5, f7, f9)); ++f; }
         * 
         * return LayerDefinition.create(meshdefinition, 64, 32);
         */
    }

    public ModelPart root() {
        return this.root;
    }

    /**
     * Sets this entity's model rotation angles
     */
    public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float f = pAgeInTicks * (float) Math.PI * -0.1F;

        // for (int i = 0; i < 4; ++i) {
        // this.upperBodyParts[i].y = -2.0F + Mth.cos(((float) (i * 2) + pAgeInTicks) *
        // 0.25F);
        // this.upperBodyParts[i].x = Mth.cos(f) * 9.0F;
        // this.upperBodyParts[i].z = Mth.sin(f) * 9.0F;
        // ++f;
        // }

        f = ((float) Math.PI / 4F) + pAgeInTicks * (float) Math.PI * 0.03F;

        for (int j = 0; j < 4; ++j) {
            this.upperBodyParts[j].y = 6.0F + Mth.cos(((float) (j * 2) + pAgeInTicks) * 0.25F);
            this.upperBodyParts[j].x = Mth.cos(f) * 7.0F;
            this.upperBodyParts[j].z = Mth.sin(f) * 7.0F;
            f += 1.57f;
        }

        f = 0.47123894F + pAgeInTicks * (float) Math.PI * -0.05F;

        for (int k = 4; k < 8; ++k) {
            this.upperBodyParts[k].y = 11.0F + Mth.cos(((float) k * 1.5F + pAgeInTicks) * 0.5F);
            this.upperBodyParts[k].x = Mth.cos(f) * 5.0F;
            this.upperBodyParts[k].z = Mth.sin(f) * 5.0F;
            f += 1.57f;
        }

        this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float) Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        for (var i = 0; i < 8; i++) {
            upperBodyParts[i].render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        }
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

}