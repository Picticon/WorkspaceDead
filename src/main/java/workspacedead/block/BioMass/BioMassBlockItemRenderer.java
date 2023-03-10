package workspacedead.block.BioMass;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class BioMassBlockItemRenderer extends GeoItemRenderer<BioMassBlockItem> {
    public BioMassBlockItemRenderer() {
        super(new BioMassBlockItemModel());
    }
}