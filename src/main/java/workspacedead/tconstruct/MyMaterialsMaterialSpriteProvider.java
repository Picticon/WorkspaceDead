/*package workspacedead.tconstruct;

import javax.annotation.Nonnull;

import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

public class MyMaterialsMaterialSpriteProvider extends AbstractMaterialSpriteProvider {

    @Nonnull
    @Override
    public String getName() {
        return "Workspace Dead Material Sprite Provider";
    }

    @Override
    protected void addAllMaterials() {
        //buildMaterial(ReforgedMaterialIds.aluminum)
        //        .meleeHarvest()
        //        .fallbacks("metal")
        //        .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF4C3D3B).addARGB(102, 0xFF675B57).addARGB(140, 0xFFB7A196).addARGB(178, 0xFFC2AEA0).addARGB(216, 0xFFD2C1B0).addARGB(255, 0xFFE4D5C1).build());
        buildMaterial(MyMaterialIds.POOP_MATERIAL).meleeHarvest().fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF9A031E).addARGB(102, 0xFFA7261D)
                        .addARGB(140, 0xFFBA3E1B).addARGB(178, 0xFFCF5218).addARGB(216, 0xFFEC751B)
                        .addARGB(255, 0xFFFB8B24).build());
    }
}*/