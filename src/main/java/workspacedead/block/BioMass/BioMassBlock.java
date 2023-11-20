package workspacedead.block.BioMass;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import workspacedead.registry.MyBlockEntities;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;

public class BioMassBlock extends BaseEntityBlock {
    //public static final DirectionProperty FACING = BlockStateProperties.FACING;

    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 12, 14);

    public BioMassBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return MyBlockEntities.BIOMASS_BLOCK_ENTITY.get().create(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState block) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
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
