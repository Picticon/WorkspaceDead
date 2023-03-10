package workspacedead.datagen;

import java.util.function.Consumer;

import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModAdvancmentsProvider extends AdvancementProvider {

    public ModAdvancmentsProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    @Override
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        super.registerAdvancements(consumer, fileHelper);
    }

}
