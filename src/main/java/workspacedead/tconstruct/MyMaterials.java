/*package workspacedead.tconstruct;

import javax.annotation.Nonnull;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import workspacedead.WorkspaceDead;

public class MyMaterials extends AbstractMaterialDataProvider {
    public MyMaterials(DataGenerator generator) {
        super(generator);
    }

    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(WorkspaceDead.MOD_ID);
    public static final StaticModifier<Modifier> deadInsideModifier = MODIFIERS.register("deadinside",
            DeadInsideModifier::new);

    @Nonnull
    @Override
    public String getName() {
        return "Workspace Dead Materials";
    }

    @Override
    protected void addMaterials() {
        addMaterial(MyMaterialIds.POOP_MATERIAL, 1, ORDER_GENERAL, false);
    }

}*/