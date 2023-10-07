package workspacedead.world.feature;

import java.util.List;

import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.*;
import workspacedead.registry.MyBlocks;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = //
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, WorkspaceDead.MOD_ID);

            public static final RegistryObject<PlacedFeature> DEADTREE_CHECKED = PLACED_FEATURES.register("deadtree_checked",
            () -> new PlacedFeature(ModConfiguredFeatures.DEADTREE.getHolder().get(),
                    List.of(PlacementUtils.filteredByBlockSurvival(MyBlocks.DEADSAPLING.get()))));

    public static final RegistryObject<PlacedFeature> DEADTREE_PLACED = PLACED_FEATURES.register("deadtree_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.DEADTREE_SPAWN.getHolder().get(), VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(3, 0.1f, 2))));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
