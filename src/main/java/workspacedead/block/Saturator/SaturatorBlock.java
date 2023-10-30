package workspacedead.block.Saturator;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import workspacedead.registry.MyBlockEntities;

public class SaturatorBlock extends BaseEntityBlock {
    public SaturatorBlock() {
        super(BlockBehaviour.Properties.of(Material.STONE).strength(1f).sound(SoundType.STONE));
    }

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 8, 16);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new SaturatorBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState,
            BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, MyBlockEntities.SATURATOR_BLOCK_ENTITY.get(),
                SaturatorBlockEntity::tick);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn,
            BlockHitResult hit) {
        if (handIn != InteractionHand.MAIN_HAND)
            return InteractionResult.PASS;
        if (!world.isClientSide && world.getBlockEntity(pos) instanceof SaturatorBlockEntity tile) {
            // boolean insert = false;
            // regardless of what the player has, pop anything out of the block, be it input
            // or output then, insert the item the player has.
            // if output has something in it, we're going to pop it out.
            if (tile.getOutputStack() != null && !tile.getOutputStack().isEmpty()) {
                ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(),
                        tile.getOutputStack());
                item.setNoPickUpDelay();
                world.addFreshEntity(item);
                tile.setOutputStack(ItemStack.EMPTY);
            }
            // if input has something in it, we're going to pop it out.
            if (tile.getInputStack() != null && !tile.getInputStack().isEmpty()) {
                ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(),
                        tile.getInputStack());
                item.setNoPickUpDelay();
                world.addFreshEntity(item);
                tile.setInputStack(ItemStack.EMPTY);
            }
            if (!player.getItemInHand(handIn).isEmpty() && !player.getInventory().getSelected().isEmpty()) {
                tile.setInputStack(player.getInventory().removeItem(player.getInventory().selected, 1));
            }
            world.sendBlockUpdated(pos, state, state, 2);
        }
        return InteractionResult.SUCCESS;

    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(worldIn, pos, state, player);
        if (worldIn.getBlockEntity(pos) instanceof SaturatorBlockEntity tile && tile.getInputStack() != null) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.getInputStack()));
        }
        if (worldIn.getBlockEntity(pos) instanceof SaturatorBlockEntity tile && tile.getOutputStack() != null) {
            worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), tile.getOutputStack()));
        }
    }
}
