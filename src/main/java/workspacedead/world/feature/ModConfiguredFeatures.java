package workspacedead.world.feature;

import net.minecraft.core.Registry;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
//import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.WorkspaceDead;
import workspacedead.registry.MyBlocks;
import workspacedead.world.feature.tree.NoFoliagePlacer;

import java.util.List;

public class ModConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = //
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, WorkspaceDead.MOD_ID);

    // public static final DeferredRegister<FoliagePlacerType<?>>
    // FOLIAGE_PLACER_TYPES = //
    // DeferredRegister.create(Registry.FOLIAGE_PLACER_TYPE_REGISTRY,
    // WorkspaceDead.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEADTREE = //
            CONFIGURED_FEATURES.register("deadtree", () -> //
            new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(//
                    BlockStateProvider.simple(MyBlocks.DEADLOG.get()), //
                    new StraightTrunkPlacer(1, 1, 3), //
                    BlockStateProvider.simple(MyBlocks.DEADLEAVES.get()), //
                    new NoFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), //
                    new TwoLayersFeatureSize(1, 0, 2))//
                            .dirt(BlockStateProvider.simple(MyBlocks.DEADDIRT.get()))//
                            .build()));

    public static final RegistryObject<ConfiguredFeature<?, ?>> DEADTREE_SPAWN = //
            CONFIGURED_FEATURES.register("deadtree_spawn", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, //
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(//
                            ModPlacedFeatures.DEADTREE_CHECKED.getHolder().get(), //
                            0.5F)), ModPlacedFeatures.DEADTREE_CHECKED.getHolder().get())));

    // public static final RegistryObject<FoliagePlacerType<?>> NOLEAVES = //
    // FOLIAGE_PLACER_TYPES.register("nofoliage", () -> new
    // FoliagePlacerType<>(NoFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
        // FOLIAGE_PLACER_TYPES.register(eventBus);
    }

}
