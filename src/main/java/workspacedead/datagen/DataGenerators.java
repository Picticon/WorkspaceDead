package workspacedead.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import workspacedead.WorkspaceDead;

@Mod.EventBusSubscriber(modid = WorkspaceDead.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        var b = new ModBlockTagsProvider(generator, WorkspaceDead.MOD_ID, existingFileHelper);
        generator.addProvider(b);
        generator.addProvider(new ModItemTagsProvider(generator, b, WorkspaceDead.MOD_ID, existingFileHelper));

        generator.addProvider(new ModRecipeProvider(generator));
        generator.addProvider(new ModLootTableProvider(generator));
        generator.addProvider(new ModBlocksStateProvider(generator, existingFileHelper));
        generator.addProvider(new ModItemModelProvider(generator, existingFileHelper));
        generator.addProvider(new ModAdvancmentsProvider(generator, existingFileHelper));
    }
}