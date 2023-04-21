package workspacedead.client.armor;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import workspacedead.item.custom.PotatoArmorItem;

public class PotatoArmorRenderer extends GeoArmorRenderer<PotatoArmorItem> {
    public PotatoArmorRenderer() {
        super(new PotatoArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}