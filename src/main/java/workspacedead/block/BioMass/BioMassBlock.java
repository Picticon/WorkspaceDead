package workspacedead.block.BioMass;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.BaseEntityBlock;
import workspacedead.block.ModBlockEntities;

public class BioMassBlock extends BaseEntityBlock {
    //public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public BioMassBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntities.BIOMASS_BLOCK_ENTITY.get().create(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState block) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    // protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    //     builder.add(FACING);
    // }

    // @Nullable
    // @Override
    // public BlockState getStateForPlacement(BlockPlaceContext context) {
    //     return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    // }
}
