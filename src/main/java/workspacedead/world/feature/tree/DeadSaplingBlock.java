package workspacedead.world.feature.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.PlantType;
import workspacedead.registry.MyBlocks;

public class DeadSaplingBlock extends SaplingBlock {

    public DeadSaplingBlock(AbstractTreeGrower pTreeGrower, Properties pProperties) {
        super(pTreeGrower, pProperties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(MyBlocks.DEADDIRT.get()) || pState.is(MyBlocks.DEADSAND.get());
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return PlantType.get("customdead"); // name doesn't matter here. just to trick Forge into letting the sapling place
                                            // and grow.
    }

}
