package workspacedead.block.generators;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class PinkGeneratorBlock extends BaseGeneratorBlock {
    // make it so it can only look NESW
    // private static final VoxelShape RENDER_SHAPE = Shapes.box(0.1, 0.1, 0.1, 0.9,
    // 0.9, 0.9);

    public PinkGeneratorBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    // //@SuppressWarnings("deprecation")
    // @Override
    // public VoxelShape getOcclusionShape(BlockState state, BlockGetter reader,
    // BlockPos pos) {
    // return RENDER_SHAPE;
    // }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PinkGeneratorBlockEntity(blockPos, blockState);
    }

}
