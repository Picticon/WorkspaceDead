package workspacedead.client.renderer.mob;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.client.renderer.entity.layers.SheepFurLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.WorkspaceDead;
import workspacedead.entity.mob.SkeletonSheep;

@OnlyIn(Dist.CLIENT)
public class SkeletonSheepRenderer extends MobRenderer<SkeletonSheep, SkeletonSheepModel<SkeletonSheep>> {
    private static final ResourceLocation SHEEP_LOCATION = new ResourceLocation(WorkspaceDead.MOD_ID, "textures/entity/mob/skeletonsheep.png");

    public SkeletonSheepRenderer(EntityRendererProvider.Context p_174366_) {
        super(p_174366_, new SkeletonSheepModel<>(p_174366_.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        // this.addLayer(new SheepFurLayer(this, p_174366_.getModelSet()));
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes().add(Attributes.MAX_HEALTH, 20).add(Attributes.ATTACK_DAMAGE, 2).add(Attributes.ATTACK_SPEED, 4).add(Attributes.MOVEMENT_SPEED, .2f).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).add(Attributes.FOLLOW_RANGE, 15.0D).build();
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(SkeletonSheep pEntity) {
        return SHEEP_LOCATION;
    }
}