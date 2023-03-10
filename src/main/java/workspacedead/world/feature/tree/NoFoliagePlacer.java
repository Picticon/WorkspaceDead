package workspacedead.world.feature.tree;

import java.util.Random;
import java.util.function.BiConsumer;

import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

public class NoFoliagePlacer extends BlobFoliagePlacer {

    public NoFoliagePlacer(IntProvider pRadius, IntProvider pOffset) {
        super(pRadius, pOffset, 0);
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliagePlacer.FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        // DO NOTHING
    }

    @Override
    protected void placeLeavesRow(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeConfiguration pConfig, BlockPos pPos, int pRange, int pYOffset, boolean pLarge) {
        // DO NOTHING
        //
        // int i = pLarge ? 1 : 0;
        // BlockPos.MutableBlockPos blockpos$mutableblockpos = new
        // BlockPos.MutableBlockPos();

        // for (int j = -pRange; j <= pRange + i; ++j) {
        // for (int k = -pRange; k <= pRange + i; ++k) {
        // if (!this.shouldSkipLocationSigned(pRandom, j, pYOffset, k, pRange, pLarge))
        // {
        // blockpos$mutableblockpos.setWithOffset(pPos, j, pYOffset, k);
        // tryPlaceLeaf(pLevel, pBlockSetter, pRandom, pConfig,
        // blockpos$mutableblockpos);
        // }
        // }
        // }

    }

}