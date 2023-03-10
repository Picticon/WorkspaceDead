package workspacedead.world.feature.tree;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import workspacedead.world.feature.ModConfiguredFeatures;

// ref: net.minecraft.world.level.block.grower.OakTreeGrower
public class DeadTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random pRandom, boolean pLargeHive) {
        return ModConfiguredFeatures.DEADTREE.getHolder().get();
    }
}
