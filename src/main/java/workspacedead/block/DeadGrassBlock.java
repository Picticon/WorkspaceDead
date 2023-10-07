package workspacedead.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import workspacedead.registry.MyBlocks;

public class DeadGrassBlock extends BushBlock {
    public DeadGrassBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);

    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(MyBlocks.DEADDIRT.get()) || pState.is(MyBlocks.DEADSAND.get());
    }

    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return false;
    }

    public boolean isBonemealSuccess(Level pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        return false;
    }

}
